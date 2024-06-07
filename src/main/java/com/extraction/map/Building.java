package com.extraction.map;

import com.extraction.items.Item;

import java.util.*;

public class Building {
    private Map<String, Room> rooms = new HashMap<String, Room>();

    public Building() {
    }

    public void addRoom(Room room) {
        rooms.put(room.getCoordinate().toString(), room);
    }

    public Room getRoom(String coordinate) {
        return rooms.get(coordinate);
    }

    public void removeRoom(String coordinate) {
        rooms.remove(coordinate);
    }

    public List<Room> getRooms() {
        return new ArrayList<Room>(rooms.values());
    }


    public String getAvailableDirections(Room currentRoom) {
        List<String> availableDirections = new ArrayList<>();

        Coordinate currentCoordinate = currentRoom.getCoordinate();

        // Controlla Nord
        if (getRoom(new Coordinate(currentCoordinate.getRow() - 1, currentCoordinate.getColumn()).toString()) != null) {
            availableDirections.add("North");
        }

        // Controlla Sud
        if (getRoom(new Coordinate(currentCoordinate.getRow() + 1, currentCoordinate.getColumn()).toString()) != null) {
            availableDirections.add("South");
        }

        // Controlla Est
        if (getRoom(new Coordinate(currentCoordinate.getRow(), currentCoordinate.getColumn() + 1).toString()) != null) {
            availableDirections.add("East");
        }

        // Controlla Ovest
        if (getRoom(new Coordinate(currentCoordinate.getRow(), currentCoordinate.getColumn() - 1).toString()) != null) {
            availableDirections.add("West");
        }

        return availableDirections.toString();
    }

    public Map<String, Room> getRoomsEntry() {
        return rooms;
    }
}
