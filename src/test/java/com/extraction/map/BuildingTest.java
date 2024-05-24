package com.extraction.map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    static Building building;
    static Room room4_2, room4_1, room3_1, room2_1, room2_0, room1_0, room2_2, room1_2, room0_2, room2_3, room1_3, room2_4, room3_4;

    @BeforeAll
    static void setUp() {
        building = new Building();
        room4_2 = new Room("Room 4_2", new Coordinate(4, 2), "Title of Room 4_2", "Description of Room 4_2");
        room4_1 = new Room("Room 4_1", new Coordinate(4, 1), "Title of Room 4_1", "Description of Room 4_1");
        room3_1 = new Room("Room 3_1", new Coordinate(3, 1), "Title of Room 3_1", "Description of Room 3_1");
        room2_1 = new Room("Room 2_1", new Coordinate(2, 1), "Title of Room 2_1", "Description of Room 2_1");
        room2_0 = new Room("Room 2_0", new Coordinate(2, 0), "Title of Room 2_0", "Description of Room 2_0");
        room1_0 = new Room("Room 1_0", new Coordinate(1, 0), "Title of Room 1_0", "Description of Room 1_0");
        room2_2 = new Room("Room 2_2", new Coordinate(2, 2), "Title of Room 2_2", "Description of Room 2_2");
        room1_2 = new Room("Room 1_2", new Coordinate(1, 2), "Title of Room 1_2", "Description of Room 1_2");
        room0_2 = new Room("Room 0_2", new Coordinate(0, 2), "Title of Room 0_2", "Description of Room 0_2");
        room2_3 = new Room("Room 2_3", new Coordinate(2, 3), "Title of Room 2_3", "Description of Room 2_3");
        room1_3 = new Room("Room 1_3", new Coordinate(1, 3), "Title of Room 1_3", "Description of Room 1_3");
        room2_4 = new Room("Room 2_4", new Coordinate(2, 4), "Title of Room 2_4", "Description of Room 2_4");
        room3_4 = new Room("Room 3_4", new Coordinate(3, 4), "Title of Room 3_4", "Description of Room 3_4");

        building.addRoom(room4_2);
        building.addRoom(room4_1);
        building.addRoom(room3_1);
        building.addRoom(room2_1);
        building.addRoom(room2_0);
        building.addRoom(room1_0);
        building.addRoom(room2_2);
        building.addRoom(room1_2);
        building.addRoom(room0_2);
        building.addRoom(room2_3);
        building.addRoom(room1_3);
        building.addRoom(room2_4);
        building.addRoom(room3_4);
    }

    @Test
    void testAddRoom() {
        Building b = new Building();
        b.addRoom(room4_2);
        assertEquals(b.getRoom("(4, 2)"), room4_2);
        b.addRoom(room4_1);
        assertEquals(b.getRoom("(4, 1)"), room4_1);
        b.addRoom(room3_1);
        assertEquals(b.getRoom("(3, 1)"), room3_1);
        b.addRoom(room2_1);
        assertEquals(b.getRoom("(2, 1)"), room2_1);
        b.addRoom(room2_0);
        assertEquals(b.getRoom("(2, 0)"), room2_0);
        b.addRoom(room1_0);
        assertEquals(b.getRoom("(1, 0)"), room1_0);
        b.addRoom(room2_2);
        assertEquals(b.getRoom("(2, 2)"), room2_2);
        b.addRoom(room1_2);
        assertEquals(b.getRoom("(1, 2)"), room1_2);
        b.addRoom(room0_2);
        assertEquals(b.getRoom("(0, 2)"), room0_2);
        b.addRoom(room2_3);
        assertEquals(b.getRoom("(2, 3)"), room2_3);
        b.addRoom(room1_3);
        assertEquals(b.getRoom("(1, 3)"), room1_3);
        b.addRoom(room2_4);
        assertEquals(b.getRoom("(2, 4)"), room2_4);
        b.addRoom(room3_4);
        assertEquals(b.getRoom("(3, 4)"), room3_4);
    }

    @ParameterizedTest
    @ValueSource(strings = {"(4, 2)", "(4, 1)", "(3, 1)", "(2, 1)", "(2, 0)", "(1, 0)",
            "(2, 2)", "(1, 2)", "(0, 2)", "(2, 3)", "(1, 3)", "(2, 4)", "(3, 4)"})
    void testGetAvailableDirections(String coordinate) {
        building.getAvailableDirections(building.getRoom(coordinate));
    }

    @ParameterizedTest
    @ValueSource(strings = {"(4, 2)", "(4, 1)", "(3, 1)", "(2, 1)", "(2, 0)", "(1, 0)",
            "(2, 2)", "(1, 2)", "(0, 2)", "(2, 3)", "(1, 3)", "(2, 4)", "(3, 4)"})
    void testGetAvailableDirections_nullBuilding(String coordinate) {
        Building b = new Building();
        assertThrows(NullPointerException.class, () -> b.getAvailableDirections(b.getRoom(coordinate)));
    }

    @Test
    void testGetRooms() {
        List<Room> rooms = building.getRooms();
        assertEquals(13, rooms.size());
    }
}