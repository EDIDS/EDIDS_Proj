package com.extraction.map;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @ParameterizedTest
    @ValueSource(strings = {"0,0", "0,10", "10,0", "100,100"})
    void testValidConstructor(String value) {
        Coordinate coordinate = new Coordinate(value);
        int row = coordinate.getRow();
        int column = coordinate.getColumn();
        String sym = row + "," + column;
        assertEquals(value, sym);
    }

    @ParameterizedTest
    @ValueSource(strings = {"n,0", "0,s", "k,k"})
    void testInvalidConstructor(String value) {
        assertThrows(NumberFormatException.class, () -> new Coordinate(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"35,22", "0,7", "98,1"})
    void testEquals(String value) {
        Coordinate coordinate1 = new Coordinate(value);
        Coordinate coordinate2 = new Coordinate("0,0");
        assertFalse(coordinate1.equals(coordinate2));
        coordinate2.setRow(coordinate1.getRow());
        coordinate2.setColumn(coordinate1.getColumn());
        assertTrue(coordinate1.equals(coordinate2));
    }
}