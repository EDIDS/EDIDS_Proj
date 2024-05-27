package com.extraction.items;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TorchTest {
    Torch torch;

    @BeforeEach
    void setUp() {
        torch = new Torch(10);
    }

    @RepeatedTest(100)
    void testTurnOn_Off() {
        assertFalse(torch.isOn());
        torch.turnOn();
        assertTrue(torch.isOn());
        torch.turnOff();
        assertFalse(torch.isOn());
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 22, 3, 14, 55})
    void testDecreaseDuration(int duration) {
        Torch t = new Torch(duration);
        t.decreaseDuration();
        assertEquals(duration-1, t.getDuration());
    }

    @Test
    void testDeadBattery() {
        while (torch.getDuration() != 0) {
            torch.decreaseDuration();
        }
        assertEquals(0, torch.getDuration());
        assertFalse(torch.isOn());
        torch.decreaseDuration();
        assertEquals(0, torch.getDuration());
        torch.turnOn();
        assertFalse(torch.isOn());
    }
}