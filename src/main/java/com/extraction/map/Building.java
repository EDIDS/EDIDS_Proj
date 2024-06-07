package com.extraction.map;

import com.extraction.items.Item;

import java.util.*;

/**
 * The Building class represents a building in the game.
 * It contains a collection of rooms, each identified by a unique coordinate.
 */
public class Building {

    /**
     * A map of rooms in the building, each identified by a unique coordinate.
     */
    private Map<String, Room> rooms = new HashMap<String, Room>();

    /**
     * Constructs a new Building with no rooms.
     */
    public Building() {
    }

    /**
     * Adds a room to the building.
     * @param room The room to be added.
     */
    public void addRoom(Room room) {
        rooms.put(room.getCoordinate().toString(), room);
    }

    /**
     * Retrieves a room from the building based on its coordinate.
     * @param coordinate The coordinate of the room.
     * @return The room at the given coordinate, or null if no such room exists.
     */
    public Room getRoom(String coordinate) {
        return rooms.get(coordinate);
    }

    /**
     * Removes a room from the building.
     * @param coordinate The coordinate of the room to be removed.
     */
    public void removeRoom(String coordinate) {
        rooms.remove(coordinate);
    }

    /**
     * Retrieves a list of all rooms in the building.
     * @return A list of all rooms in the building.
     */
    public List<Room> getRooms() {
        return new ArrayList<Room>(rooms.values());
    }

    /**
     * Retrieves a list of all available directions from a given room.
     * @param currentRoom The room from which to retrieve available directions.
     * @return A string representation of a list of all available directions from the given room.
     */
    public String getAvailableDirections(Room currentRoom) {
        List<String> availableDirections = new ArrayList<>();

        Coordinate currentCoordinate = currentRoom.getCoordinate();

        if (getRoom(new Coordinate(currentCoordinate.getRow() - 1, currentCoordinate.getColumn()).toString()) != null) {
            availableDirections.add("North");
        }

        if (getRoom(new Coordinate(currentCoordinate.getRow() + 1, currentCoordinate.getColumn()).toString()) != null) {
            availableDirections.add("South");
        }

        if (getRoom(new Coordinate(currentCoordinate.getRow(), currentCoordinate.getColumn() + 1).toString()) != null) {
            availableDirections.add("East");
        }

        if (getRoom(new Coordinate(currentCoordinate.getRow(), currentCoordinate.getColumn() - 1).toString()) != null) {
            availableDirections.add("West");
        }

        return availableDirections.toString();
    }

    public Map<String, Room> getRoomsEntry() {
        return rooms;
    }
}
