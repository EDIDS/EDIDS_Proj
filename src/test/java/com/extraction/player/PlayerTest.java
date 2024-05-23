package com.extraction.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static com.extraction.player.Player.*;
import com.extraction.items.Item;

class PlayerTest {

    @Test
    void testInitialPlayer() {
        Player player = new Player();
        String name = player.getName();
        assertEquals(NO_NAME, name);
        int health = player.getHealth();
        assertEquals(FULL_HEALTH, health);
        int attack = player.attack();
        assertEquals(BASE_ATTACK_DAMAGE, attack);
        double weight = player.getCurrentWeight_();
        assertEquals(0, weight);
        List<Item> bag = player.getBag();
        assertTrue(bag.isEmpty());
        boolean hasWeapon = player.hasWeapon();
        assertFalse(hasWeapon);
        boolean hasShield = player.hasShield();
        assertFalse(hasShield);
        Player named = new Player("Paul");
        String name_ = named.getName();
        assertEquals("Paul", name_);
        int health_ = named.getHealth();
        assertEquals(FULL_HEALTH, health_);
        int attack_ = named.attack();
        assertEquals(BASE_ATTACK_DAMAGE, attack_);
        double weight_ = named.getCurrentWeight_();
        assertEquals(0, weight_);
        List<Item> bag_ = named.getBag();
        assertTrue(bag_.isEmpty());
        boolean hasWeapon_ = named.hasWeapon();
        assertFalse(hasWeapon_);
        boolean hasShield_ = named.hasShield();
        assertFalse(hasShield_);
    }

}