package com.extraction;

import com.extraction.aliens.AlienTest;
import com.extraction.items.ItemTest;
import com.extraction.map.MapTest;
import com.extraction.player.PlayerTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AlienTest.class, ItemTest.class, MapTest.class, PlayerTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
