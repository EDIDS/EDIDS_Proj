package com.extraction.aliens;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static com.extraction.aliens.Alien.*;

class ShamblerTest {

    Shambler shambler;

    @BeforeEach
    public void setUp() {
        shambler = new Shambler();
    }

    @Test
    void TestAttributes() {
        String name = shambler.getName();
        assertEquals("Shambler", name);
        int health = shambler.getHealth();
        assertEquals(TIER3_HEALTH, health);
    }

    @RepeatedTest(100)
    void TestAttack() {
        int attack = shambler.attack();
        assertTrue(attack > 0 && attack <= TIER3_MAXIMUM_ATTACK_DAMAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, TIER3_HEALTH/2, TIER3_HEALTH, TIER3_HEALTH+1})
    void TestTakeDamage(int value) {
        int health_1, health_2;
        health_1 = shambler.getHealth();
        shambler.takeDamage(value);
        health_2 = shambler.getHealth();
        assertEquals(value, health_1 - health_2);
    }

    @Test
    void TestGetEscapeChance() {
        double chance = shambler.getEscapeChance();
        assertEquals(0.4, chance);
    }
}