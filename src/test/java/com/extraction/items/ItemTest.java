package com.extraction.items;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import static com.extraction.items.Key.KEY_WEIGHT;
import static com.extraction.items.MedKit.MEDKIT_WEIGHT;
import static com.extraction.items.Shield.SHIELD_DEFENSE;
import static com.extraction.items.Shield.SHIELD_WEIGHT;
import static com.extraction.items.TNT.TNT_DAMAGE;
import static com.extraction.items.TNT.TNT_WEIGHT;
import static com.extraction.items.Torch.TORCH_WEIGHT;
import static com.extraction.items.Weapon.type_weight;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Suite
@SelectClasses({WeaponTest.class})
class ItemTest {

    @Test
    void testKey() {
        Key key = new Key();
        assertEquals(key.getName(), "Key");
        assertEquals(key.getWeight(), KEY_WEIGHT);
    }

    @Test
    void testMedKit() {
        MedKit medKit = new MedKit();
        assertEquals(medKit.getName(), "MedKit");
        assertEquals(medKit.getWeight(), MEDKIT_WEIGHT);
    }

    @Test
    void testShield() {
        Shield shield = new Shield();
        assertEquals(shield.getName(), "Shield");
        assertEquals(shield.getWeight(), SHIELD_WEIGHT);
        assertEquals(shield.getDefense(), SHIELD_DEFENSE);
    }

    @Test
    void testTNT() {
        TNT tnt = new TNT();
        assertEquals(tnt.getName(), "TNT");
        assertEquals(tnt.getWeight(), TNT_WEIGHT);
        assertEquals(tnt.getDamage(), TNT_DAMAGE);
    }

    @Test
    void testTorch() {
        Torch torch = new Torch();
        assertEquals(torch.getName(), "Torch");
        assertEquals(torch.getWeight(), TORCH_WEIGHT);
    }

    @ParameterizedTest
    @ValueSource (strings = {"REVOLVER", "AK47", "USPSWORM"})
    void testWeapon(String type) {
        Weapon weapon = new Weapon(type);
        assertEquals(weapon.getName(), "Weapon");
        assertEquals(weapon.getWeight(), type_weight(type));
    }
}
