package com.extraction.items;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.extraction.items.Weapon.type_weight;
import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    Weapon weapon = new Weapon("REVOLVER");

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

    @RepeatedTest(100)
    void testCalculateDamage() {
        int damage = weapon.calculateDamage();
        assertTrue(damage >= weapon.minDamage_ && damage <= weapon.maxDamage_);
    }

}