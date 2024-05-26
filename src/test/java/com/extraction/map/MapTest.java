package com.extraction.map;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Suite
@SelectClasses({BuildingTest.class, CoordinateTest.class})
public class MapTest {
}
