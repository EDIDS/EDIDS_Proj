package com.extraction.player;

import com.extraction.items.*;
import com.extraction.Graphic.VisibilityManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static com.extraction.items.Shield.SHIELD_WEIGHT;
import static com.extraction.items.TNT.TNT_DAMAGE;
import static com.extraction.items.Weapon.type_weight;
import static org.junit.jupiter.api.Assertions.*;

import static com.extraction.player.Player.*;

class PlayerTest {

    Player player;
    VisibilityManager vm;

    /**
     * Because vm is null, every time it is called a NullPointerException is expected to be thrown.
     * Vm is called in case some exceptional situation, which needs to be displayed, occurs.
     */
    @BeforeEach
    void setUp() {
        player = new Player(vm);
    }

    @Test
    void testAdd_ThrowWeapon() {
        Weapon weapon1 = new Weapon("REVOLVER");
        player.addItem(weapon1);
        assertEquals(weapon1, player.getWeapon());
        assertEquals(type_weight("REVOLVER"), player.getCurrentWeight_());
        Weapon weapon2 = new Weapon("AK47");
        //vm.showMessage() is called to display the weapon's change
        assertThrows(NullPointerException.class, () -> player.addItem(weapon2));
        try { player.addItem(weapon2); }
        catch (NullPointerException ignored) {}
        assertEquals(weapon2, player.getWeapon());
        assertEquals(type_weight("AK47"), player.getCurrentWeight_());
        Weapon weapon3 = new Weapon("USPSWORM");
        //vm.showMessage() is called to display the weapon's change
        assertThrows(NullPointerException.class, () -> player.addItem(weapon3));
        try { player.addItem(weapon3); }
        catch (NullPointerException ignored) {}
        assertEquals(weapon3, player.getWeapon());
        assertEquals(type_weight("USPSWORM"), player.getCurrentWeight_());
        player.throwItem("Weapon");
        assertFalse(player.hasWeapon());
        assertEquals(0, player.getCurrentWeight_());
    }

    @Test
    void testSetShield_ThrowShield() {
        Shield shield1 = new Shield();
        player.addItem(shield1);
        assertEquals(shield1, player.getShield());
        assertEquals(SHIELD_WEIGHT, player.getCurrentWeight_());
        Shield shield2 = new Shield();
        //vm.showMessage() is called to display that shield is already set
        assertThrows(NullPointerException.class, () -> player.addItem(shield2));
        try { player.addItem(new Shield()); }
        catch (NullPointerException ignored) {}
        //shields are not replaced
        assertEquals(shield1, player.getShield());
        player.throwItem("Shield");
        assertFalse(player.hasShield());
        assertEquals(0, player.getCurrentWeight_());
    }

    @RepeatedTest(100)
    void testAttack() throws NullPointerException {
        int attack = player.attack();
        assertEquals(BASE_ATTACK_DAMAGE, attack);
        player.addItem(new Weapon("REVOLVER"));
        attack = player.attack();
        assertTrue(attack >= 20 && attack <= 40);
        //vm.showMessage() is called to display the weapon's change
        assertThrows(NullPointerException.class, () -> player.addItem(new Weapon("AK47")));
        try { player.addItem(new Weapon("AK47")); }
        catch (NullPointerException ignored) {}
        attack = player.attack();
        assertTrue(attack >= 40 && attack <= 60);
        //vm.showMessage()n is called to display the weapon's change
        assertThrows(NullPointerException.class, () -> player.addItem(new Weapon("USPSWORM")));
        try { player.addItem(new Weapon("USPSWORM")); }
        catch (NullPointerException ignored) {}
        attack = player.attack();
        assertTrue(attack >= 30 && attack <= 50);
    }

    @Test
    void testHeal() {
        player.setHealth(FULL_HEALTH/2);
        //vm.showMessage() is called to display that no medKit was found
        assertThrows(NullPointerException.class, () -> player.heal());
        try { player.heal(); }
        catch (NullPointerException ignored) {}
        assertEquals(FULL_HEALTH/2, player.getHealth());
        player.addItem(new MedKit());
        player.heal();
        assertEquals(FULL_HEALTH, player.getHealth());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, FULL_HEALTH/2, FULL_HEALTH-1, FULL_HEALTH, FULL_HEALTH+1})
    void testTakeDamage(int damage) {
        assertEquals(player.getHealth(), FULL_HEALTH);
        player.takeDamage(damage);
        if (damage >= 0) assertEquals(player.getHealth(), FULL_HEALTH-damage);
        else assertEquals(player.getHealth(), FULL_HEALTH);
    }

    @Test
    void testAdd_Find_Throw_NotThrowableItem() {
        assertNull(player.findItem("Key"));
        Key key = new Key();
        player.addItem(key);
        assertNotNull(player.findItem("Key"));
        player.throwItem(key);
        assertNotNull(player.findItem("Key"));
        player.throwItem("Key");
        assertNotNull(player.findItem("Key"));
    }

    @Test
    void testAdd_Find_Throw_ThrowableItem() {
        assertNull(player.findItem("TNT"));
        TNT tnt = new TNT();
        player.addItem(tnt);
        assertNotNull(player.findItem("TNT"));
        player.throwItem(tnt);
        assertNull(player.findItem("TNT"));
        player.throwItem("TNT");
        assertNull(player.findItem("TNT"));
    }

    @Test
    void testDetonateTNT() {
        //vm.showMessage() is called to display that no TNT was found
        assertThrows(NullPointerException.class, () -> player.detonateTNT());
        player.addItem(new TNT());
        assertEquals(TNT_DAMAGE, player.detonateTNT());
    }

    @Test
    void testMaxWeight() {
        player.setCurrentWeight_(MAX_WEIGHT);
        //vm.showMessage() is called to display that the bag is already full
        assertThrows(NullPointerException.class, () -> player.addItem(new Weapon("REVOLVER")));
        try { player.addItem(new Weapon("REVOLVER")); }
        catch (NullPointerException ignored) {}
        assertNull(player.getWeapon());
        //vm.showMessage() is called to report that the bag is already full
        assertThrows(NullPointerException.class, () -> player.addItem(new Shield()));
        try { player.addItem(new Shield()); }
        catch (NullPointerException ignored) {}
        assertNull(player.getShield());
        //vm.showMessage() is called to report that the bag is already full
        assertThrows(NullPointerException.class, () -> player.addItem(new TNT()));
        try { player.addItem(new TNT()); }
        catch (NullPointerException ignored) {}
        assertNull(player.findItem("TNT"));
    }
}