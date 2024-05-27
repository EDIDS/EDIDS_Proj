package com.extraction.map;

import com.extraction.items.Item;

import java.util.*;

public class Building {
    private Map<String, Room> rooms = new HashMap<String, Room>();
    //lista tutti gli oggetti presenti nella mappa nelle corispettive stanze, la chiave è la coordinata della stanza
    private Map<String, Item> allItems = new HashMap<String, Item>();

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

    public void setItem(Item item, Room room) {
        allItems.put(room.getCoordinate().toString(), item);
    }

    public Item getItem(Room room) {
        return allItems.get(room.getCoordinate().toString());
    }

    public void removeItem(Item item, Room room) { allItems.remove(room.getCoordinate().toString()); }

    public Map<String, Item> getAllItems() {
        return allItems;
    }

    public void setAllItems(Map<String, Item> allItems) {
        this.allItems = allItems;
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
}
