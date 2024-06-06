package com.extraction.player;

import com.extraction.items.*;
import com.extraction.Graphic.UI;
import com.extraction.Graphic.VisibilityManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static com.extraction.items.Shield.SHIELD_WEIGHT;
import static com.extraction.items.Weapon.type_weight;
import static org.junit.jupiter.api.Assertions.*;

import static com.extraction.player.Player.*;

class PlayerTest {

    Player player;
    UI ui;
    VisibilityManager vm;

    @BeforeEach
    void setUp() {
        player = new Player(ui,vm);
    }

    @Test
    void testAnonymousPlayerName() {
        assertEquals(player.getName(), NO_NAME);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", " "} )
    void testNamedPlayerName(String name) {
        Player named = new Player(name, ui, vm);
        assertEquals(named.getName(), name);
    }

    @Test
    void testSetWeapon_throwWeapon() {
        // test setWeapon()
        Weapon weapon = new Weapon("REVOLVER");
        player.setWeapon(weapon);
        assertEquals(weapon, player.getWeapon());
        assertEquals(type_weight("REVOLVER"), player.getCurrentWeight_());
        weapon = new Weapon("AK47");
        player.setWeapon(weapon);
        assertEquals(weapon, player.getWeapon());
        assertEquals(type_weight("AK47"), player.getCurrentWeight_());
        weapon = new Weapon("USPSWORM");
        player.setWeapon(weapon);
        assertEquals(weapon, player.getWeapon());
        assertEquals(type_weight("USPSWORM"), player.getCurrentWeight_());
        // test throwWeapon()
        player.throwWeapon();
        assertFalse(player.hasWeapon());
        assertEquals(0, player.getCurrentWeight_());
    }

    @Test
    void testSetShield_ThrowShield() {
        // test setShield()
        Shield shield = new Shield();
        player.setShield(shield);
        assertEquals(shield, player.getShield());
        assertEquals(SHIELD_WEIGHT, player.getCurrentWeight_());
        shield = new Shield();
        player.setShield(shield);
        assertNotEquals(shield, player.getShield());
        // test throwShield()
        player.throwShield();
        assertFalse(player.hasShield());
        assertEquals(0, player.getCurrentWeight_());
    }

    @Test
    void testAttack() {
        int attack = player.attack();
        assertEquals(BASE_ATTACK_DAMAGE, attack);
        player.setWeapon(new Weapon("REVOLVER"));
        attack = player.attack();
        assertTrue(attack >= 10 && attack <= 30);
        player.setWeapon(new Weapon("AK47"));
        attack = player.attack();
        assertTrue(attack >= 30 && attack <= 50);
        player.setWeapon(new Weapon("USPSWORM"));
        attack = player.attack();
        assertTrue(attack >= 20 && attack <= 40);
    }

    @Test
    void testHeal() {
        player.setHealth(FULL_HEALTH/2);
        player.heal();
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
    void testThrowItem() {
        Key key = new Key();
        Item item = player.throwItem(key);
        assertNull(item);
        player.addItem(key);
        item = player.throwItem(key);
        assertEquals(key, item);
        item = player.throwItem("Key");
        assertNull(item);
        player.addItem(key);
        item = player.throwItem("Key");
        assertEquals(key, item);
    }

    @Test
    void testMaxWeight() {
        player.setCurrentWeight_(MAX_WEIGHT);
        player.setWeapon(new Weapon("REVOLVER"));
        assertNull(player.getWeapon());
        player.setShield(new Shield());
        assertNull(player.getShield());
        player.addItem(new TNT());
        assertNull(player.findItem("TNT"));
    }
}