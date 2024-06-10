package com.extraction.map;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import static com.extraction.map.Coordinate.fromString;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @ParameterizedTest
    @ValueSource(strings = {"0,0", "0,10", "10,0", "100,100"})
    void testValidStringConstructor(String value) {
        Coordinate coordinate = new Coordinate(value);
        int row = coordinate.getRow();
        int column = coordinate.getColumn();
        String sym = row + "," + column;
        assertEquals(value, sym);
    }

    @ParameterizedTest
    @ValueSource(strings = {"n,0", "0,s", "k,k"})
    void testInvalidStringConstructor(String value) {
        assertThrows(NumberFormatException.class, () -> new Coordinate(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10, 3, 243})
    void testIntConstructor(int value) {
        Coordinate coordinate = new Coordinate(value, value*value);
        int row = coordinate.getRow();
        int column = coordinate.getColumn();
        String sym = "(" + row + ", " + column + ")";
        assertEquals(coordinate.toString(), sym);
    }

    @ParameterizedTest
    @ValueSource(strings = {"35,22", "0,7", "98,1"})
    void testEquals(String value) {
        Coordinate coordinate1 = new Coordinate(value);
        Coordinate coordinate2 = new Coordinate(0,0);
        assertNotEquals(coordinate1, coordinate2);
        coordinate2.setRow(coordinate1.getRow());
        coordinate2.setColumn(coordinate1.getColumn());
        assertEquals(coordinate1, coordinate2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"35,22", "0,7", "98,1"})
    void testFromString(String value) {
        Coordinate coordinate = fromString(value);
        int row = coordinate.getRow();
        int column = coordinate.getColumn();
        String sym = row + "," + column;
        assertEquals(value, sym);
    }
}