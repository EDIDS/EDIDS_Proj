package com.extraction.aliens;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static com.extraction.aliens.Alien.*;

public class ClickerTest {

    Clicker clicker;

    @BeforeEach
    public void setUp() {
        clicker = new Clicker();
    }

    @Test
    public void TestAttributes() {
        String name = clicker.getName();
        assertEquals("Clicker", name);
        int health = clicker.getHealth();
        assertEquals(TIER2_HEALTH, health);
    }

    @RepeatedTest(5)
    public void TestAttack() {
        int attack = clicker.attack();
        assertTrue(attack < TIER1_MAXIMUM_ATTACK_DAMAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, TIER2_HEALTH/2, TIER2_HEALTH, TIER2_HEALTH+1})
    public void TestTakeDamage(int value) {
        int health_1, health_2;
        health_1 = clicker.getHealth();
        clicker.takeDamage(value);
        health_2 = clicker.getHealth();
        assertEquals(value, health_1 - health_2);
    }

    @Test
    public void TestGetEscapeChance() {
        double chance = clicker.getEscapeChance();
        assertEquals(0.8, chance);
    }
}