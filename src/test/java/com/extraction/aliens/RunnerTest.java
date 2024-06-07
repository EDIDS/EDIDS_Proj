package com.extraction.aliens;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static com.extraction.aliens.Alien.*;

class RunnerTest {

    Runner runner;

    @BeforeEach
    public void setUp() {
        runner = new Runner();
    }

    @Test
    void TestAttributes() {
        assertEquals("Runner", runner.getName());
        assertEquals(TIER1_HEALTH, runner.getHealth());
    }

    @RepeatedTest(100)
    void TestAttack() {
        int attack = runner.attack();
        assertTrue(attack > 0 && attack <= TIER1_MAXIMUM_ATTACK_DAMAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, TIER1_HEALTH/2, TIER1_HEALTH, TIER1_HEALTH+1})
    void TestTakeDamage(int value) {
        int health_1, health_2;
        health_1 = runner.getHealth();
        runner.takeDamage(value);
        health_2 = runner.getHealth();
        assertEquals(value, health_1 - health_2);
    }

    @Test
    void TestGetEscapeChance() {
        double chance = runner.getEscapeChance();
        assertEquals(0.3, chance);
    }

    @Test
    void TestGetEludeChance() {
        double chance = runner.getEludeChance();
        assertEquals(0.4, chance);
    }
}