package com.extraction.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static com.extraction.player.Player.*;
import com.extraction.items.Item;
import com.extraction.items.Weapon;

class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void testAnonymousPlayerName() {
        String name = player.getName();
        assertEquals(NO_NAME, name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", " "} )
    void testNamedPlayerName(String name) {
        Player named = new Player(name);
        String name_ = named.getName();
        assertEquals(name, name_);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, FULL_HEALTH/2, FULL_HEALTH-1, FULL_HEALTH, FULL_HEALTH+1})
    void testPlayerHealth(int damage) {
        int health = player.getHealth();
        assertEquals(FULL_HEALTH, health);
        player.takeDamage(damage);
        health = player.getHealth();
        assertEquals(FULL_HEALTH-damage, health);
    }

    @Test
    void testPlayerAttack() {
        int attack = player.attack();
        assertEquals(BASE_ATTACK_DAMAGE, attack);
        player.addItem(new Weapon("REVOLVER"));
        attack = player.attack();
        assertTrue(attack >= 10 && attack <= 30);
        player.addItem(new Weapon("AK47"));
        attack = player.attack();
        assertTrue(attack >= 30 && attack <= 50);
        player.addItem(new Weapon("USPSWORM"));
        attack = player.attack();
        assertTrue(attack >= 20 && attack <= 40);
    }

    @Test
    void testPlayerWeight() {
        double weight = player.getCurrentWeight_();
        assertEquals(0, weight);
    }

    @Test
    void testPlayerBag() {
        List<Item> bag = player.getBag();
        assertTrue(bag.isEmpty());
    }

    @Test
    void testPlayerInspection() {
        boolean hasWeapon = player.hasWeapon();
        assertFalse(hasWeapon);
        boolean hasShield = player.hasShield();
        assertFalse(hasShield);
    }

}