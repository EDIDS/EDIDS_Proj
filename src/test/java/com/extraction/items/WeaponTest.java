package com.extraction.items;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    Weapon weapon;

    @ParameterizedTest
    @ValueSource(strings = {"REVOLVER", "AK47", "USPSWORM"})
    void testWeaponType(String type) {
        weapon = new Weapon(type);
        switch (type) {
            case "REVOLVER":
                assertEquals(weapon.minDamage_, 10);
                assertEquals(weapon.maxDamage_, 30);

                break;
            case "AK47":
                assertEquals(weapon.minDamage_, 30);
                assertEquals(weapon.maxDamage_, 50);

                break;
            case "USPSWORM":
                assertEquals(weapon.minDamage_, 20);
                assertEquals(weapon.maxDamage_, 40);

                break;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"revolver", "100000", "Bach"})
    void testInvalidConstructor(String type) {
        assertThrows(IllegalArgumentException.class, () -> {new Weapon(type);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"REVOLVER", "AK47", "USPSWORM"})
    void testCalculateDamage(String type) {
        weapon = new Weapon(type);
        int damage = weapon.calculateDamage();
        assertTrue(damage >= weapon.minDamage_ && damage <= weapon.maxDamage_);
    }

}