package com.extraction.items;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import static com.extraction.items.Key.KEY_WEIGHT;
import static com.extraction.items.Medikit.MEDIKIT_WEIGHT;
import static com.extraction.items.Shield.SHIELD_DEFENSE;
import static com.extraction.items.Shield.SHIELD_WEIGHT;
import static com.extraction.items.TNT.TNT_DAMAGE;
import static com.extraction.items.TNT.TNT_WEIGHT;
import static com.extraction.items.Torch.TORCH_WEIGHT;
import static com.extraction.items.Weapon.type_weight;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Suite
@SelectClasses({TorchTest.class, WeaponTest.class})
class ItemTest {

    @ParameterizedTest
    @ValueSource (strings = {"abcd", "0001"})
    void testKey(String code) {
        Key key = new Key(code);
        assertEquals(key.getName(), "Key");
        assertEquals(key.getWeight(), KEY_WEIGHT);
        assertEquals(key.getCode(), code);
    }

    @Test
    void testMedikit() {
        Medikit medikit = new Medikit();
        assertEquals(medikit.getName(), "Medikit");
        assertEquals(medikit.getWeight(), MEDIKIT_WEIGHT);
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

    @ParameterizedTest
    @ValueSource (ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15})
    void testTorch(int duration) {
        Torch torch = new Torch(duration);
        assertEquals(torch.getName(), "Torch");
        assertEquals(torch.getWeight(), TORCH_WEIGHT);
        assertEquals(torch.getDuration(), duration);
    }

    @ParameterizedTest
    @ValueSource (strings = {"REVOLVER", "AK47", "USPSWORM"})
    void testWeapon(String type) {
        Weapon weapon = new Weapon(type);
        assertEquals(weapon.getName(), "Weapon");
        assertEquals(weapon.getWeight(), type_weight(type));
    }
}
