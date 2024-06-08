package com.extraction.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.extraction.items.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
    }

    @Test
    void testAdd_Remove_FindItem() {
        assertNull(room.findItem("Key"));
        room.addItem(new Key());
        assertNotNull(room.findItem("Key"));
        room.removeItem("Key");
        assertNull(room.findItem("Key"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"REVOLVER", "AK47", "USPSWORM"})
    void testAdd_Remove_FindWeapon(String type) {
        assertNull(room.findItem("Weapon"));
        room.addItem(new Weapon(type));
        assertNotNull(room.findItem("Weapon"));
        room.removeItem("Weapon");
        //throwable item
        assertNull(room.findItem("Weapon"));
    }
}