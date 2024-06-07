package com.extraction.items;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import static com.extraction.items.Key.KEY_WEIGHT;
import static com.extraction.items.MedKit.MEDKIT_WEIGHT;
import static com.extraction.items.Shield.*;
import static com.extraction.items.TNT.*;
import static com.extraction.items.Torch.TORCH_WEIGHT;
import static com.extraction.items.Weapon.type_weight;
import static org.junit.jupiter.api.Assertions.*;

@Suite
@SelectClasses({WeaponTest.class})
class ItemTest {

    @Test
    void testKey() {
        Key key = new Key();
        assertEquals("Key", key.getName());
        assertEquals(KEY_WEIGHT, key.getWeight());
        assertFalse(key.isThrowable());
    }

    @Test
    void testMedKit() {
        MedKit medKit = new MedKit();
        assertEquals("MedKit", medKit.getName());
        assertEquals(MEDKIT_WEIGHT, medKit.getWeight());
        assertTrue(medKit.isThrowable());
    }

    @Test
    void testShield() {
        Shield shield = new Shield();
        assertEquals("Shield", shield.getName());
        assertEquals(SHIELD_WEIGHT, shield.getWeight());
        assertEquals(SHIELD_DEFENSE, shield.getDefense());
        assertTrue(shield.isThrowable());
    }

    @Test
    void testTNT() {
        TNT tnt = new TNT();
        assertEquals("TNT", tnt.getName());
        assertEquals(TNT_WEIGHT, tnt.getWeight());
        assertEquals(TNT_DAMAGE, tnt.getDamage());
        assertTrue(tnt.isThrowable());
    }

    @Test
    void testTorch() {
        Torch torch = new Torch();
        assertEquals(torch.getName(), "Torch");
        assertEquals(torch.getWeight(), TORCH_WEIGHT);
        assertFalse(torch.isOn());
        torch.turnOn();
        assertTrue(torch.isOn());
        torch.turnOff();
        assertFalse(torch.isOn());
        assertFalse(torch.isThrowable());
    }

    @ParameterizedTest
    @ValueSource (strings = {"REVOLVER", "AK47", "USPSWORM"})
    void testWeapon(String type) {
        Weapon weapon = new Weapon(type);
        assertEquals(weapon.getName(), "Weapon");
        assertEquals(weapon.getWeight(), type_weight(type));
        assertTrue(weapon.isThrowable());
    }
}
