package com.extraction.items;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static com.extraction.items.Weapon.*;
import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    Weapon weapon;

    @ParameterizedTest
    @ValueSource(strings = {"REVOLVER", "AK47", "USPSWORM"})
    void testWeaponType(String type) {
        weapon = new Weapon(type);
        switch (type) {
            case "REVOLVER":
                assertEquals(20, weapon.minDamage_);
                assertEquals(40, weapon.maxDamage_);

                break;
            case "AK47":
                assertEquals(40, weapon.minDamage_);
                assertEquals(60, weapon.maxDamage_);

                break;
            case "USPSWORM":
                assertEquals(30, weapon.minDamage_);
                assertEquals(50, weapon.maxDamage_);

                break;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"revolver", "100000", "Bach"})
    void testInvalidConstructor(String type) {
        assertThrows(IllegalArgumentException.class, () -> {new Weapon(type);});
    }

    @RepeatedTest(100)
    void testRevolverCalculateDamage() {
        weapon = new Weapon("REVOLVER");
        int damage = weapon.calculateDamage();
        assertTrue(damage == weapon.minDamage_ || damage == weapon.maxDamage_ ||
                damage == (weapon.maxDamage_+weapon.minDamage_)/2);
    }

    @RepeatedTest(100)
    void testAk47CalculateDamage() {
        weapon = new Weapon("AK47");
        int damage = weapon.calculateDamage();
        assertTrue(damage == weapon.minDamage_ || damage == weapon.maxDamage_ ||
                damage == (weapon.maxDamage_+weapon.minDamage_)/2);
    }

    @RepeatedTest(100)
    void testUspswormCalculateDamage() {
        weapon = new Weapon("USPSWORM");
        int damage = weapon.calculateDamage();
        assertTrue(damage == weapon.minDamage_ || damage == weapon.maxDamage_ ||
                damage == (weapon.maxDamage_+weapon.minDamage_)/2);
    }

    @Test
    void testTypeWeight() {
        assertEquals(REVOLVER_WEIGHT, type_weight("REVOLVER"));
        assertEquals(AK47_WEIGHT, type_weight("AK47"));
        assertEquals(USPSWORM_WEIGHT, type_weight("USPSWORM"));
    }

}