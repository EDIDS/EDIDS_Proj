package com.extraction.aliens;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static com.extraction.aliens.Alien.*;

class ClickerTest {

    Clicker clicker;

    @BeforeEach
    void setUp() {
        clicker = new Clicker();
    }

    @Test
    void TestAttributes() {
        assertEquals("Clicker", clicker.getName());
        assertEquals(TIER2_HEALTH, clicker.getHealth());
    }

    @RepeatedTest(100)
    void TestAttack() {
        int attack = clicker.attack();
        assertTrue(attack > 0 && attack <= TIER2_MAXIMUM_ATTACK_DAMAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, TIER2_HEALTH/2, TIER2_HEALTH, TIER2_HEALTH+1})
    void TestTakeDamage(int value) {
        int health_1, health_2;
        health_1 = clicker.getHealth();
        clicker.takeDamage(value);
        health_2 = clicker.getHealth();
        assertEquals(value, health_1 - health_2);
    }

    @Test
    void TestGetEscapeChance() {
        double chance = clicker.getEscapeChance();
        assertEquals(0.2, chance);
    }

    @Test
    void TestGetEludeChance() {
        double chance = clicker.getEludeChance();
        assertEquals(0.3, chance);
    }
}