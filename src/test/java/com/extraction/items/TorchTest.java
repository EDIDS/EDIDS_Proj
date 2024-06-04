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
}