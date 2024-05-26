package com.extraction.map;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {BuildingTest.class, CoordinateTest.class})

public class MapTest {
}
