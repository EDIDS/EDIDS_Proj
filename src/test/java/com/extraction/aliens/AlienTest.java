package com.extraction.aliens;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ClickerTest.class, RunnerTest.class, ShamblerTest.class})
class AlienTest {
}
