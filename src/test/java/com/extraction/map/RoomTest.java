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
    void testAdd_Remove_FindKey() {
        assertNull(room.findItem("Key"));
        room.addItem(new Key());
        assertNotNull(room.findItem("Key"));
        room.removeItem("Key");
        //not throwable item
        assertNotNull(room.findItem("Key"));
    }

    @Test
    void testAdd_Remove_FindMedKit() {
        assertNull(room.findItem("MedKit"));
        room.addItem(new MedKit());
        assertNotNull(room.findItem("MedKit"));
        room.removeItem("MedKit");
        //throwable item
        assertNull(room.findItem("MedKit"));
    }

    @Test
    void testAdd_Remove_FindShield() {
        assertNull(room.findItem("Shield"));
        room.addItem(new Shield());
        assertNotNull(room.findItem("Shield"));
        room.removeItem("Shield");
        //throwable item
        assertNull(room.findItem("Shield"));
    }

    @Test
    void testAdd_Remove_FindTNT() {
        assertNull(room.findItem("TNT"));
        room.addItem(new TNT());
        assertNotNull(room.findItem("TNT"));
        room.removeItem("TNT");
        //throwable item
        assertNull(room.findItem("TNT"));
    }

    @Test
    void testAdd_Remove_FindTorch() {
        assertNull(room.findItem("Torch"));
        room.addItem(new Torch());
        assertNotNull(room.findItem("Torch"));
        room.removeItem("Torch");
        //not throwable item
        assertNotNull(room.findItem("Torch"));
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