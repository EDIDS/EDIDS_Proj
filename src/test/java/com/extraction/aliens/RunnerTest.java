package com.extraction.aliens;

 import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.extraction.aliens.Alien.*;
import static org.junit.jupiter.api.Assertions.*;

public class RunnerTest {

    Runner runner;

    @BeforeEach
    public void setUp() {
        runner = new Runner();
    }

    @Test
    public void TestAttributes() {
        String name = runner.getName();
        assertEquals("Runner", name);
        int health = runner.getHealth();
        assertEquals(TIER1_HEALTH, health);
    }

    @RepeatedTest(5)
    public void TestAttack() {
        int attack = runner.attack();
        assertTrue(attack < TIER2_MAXIMUM_ATTACK_DAMAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, TIER1_HEALTH/2, TIER1_HEALTH, TIER1_HEALTH+1})
    public void TestTakeDamage(int value) {
        int health_1, health_2;
        health_1 = runner.getHealth();
        runner.takeDamage(value);
        health_2 = runner.getHealth();
        assertEquals(value, health_1 - health_2);
    }

    @Test
    public void TestGetEscapeChance() {
        double chance = runner.getEscapeChance();
        assertEquals(0.6, chance);
    }
}