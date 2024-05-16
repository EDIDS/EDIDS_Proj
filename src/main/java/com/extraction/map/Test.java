package com.extraction.map;

import com.extraction.aliens.Clicker;
import com.extraction.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Building building = new Building();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();

        GameSave gameSave;

        Room room1 = new Room("Room 1", new Coordinate(0, 2), "Title of Room 1", "Description of Room 1");
        Room room2 = new Room("Room 2", new Coordinate(1, 0), "Title of Room 2", "Description of Room 2");
        Room room3 = new Room("Room 3", new Coordinate(1, 2), "Title of Room 3", "Description of Room 3");
        Room room4 = new Room("Room 4", new Coordinate(1, 3), "Title of Room 4", "Description of Room 4");
        Room room5 = new Room("Room 5", new Coordinate(2, 0), "Title of Room 5", "Description of Room 5");
        Room room6 = new Room("Room 6", new Coordinate(2, 1), "Title of Room 6", "Description of Room 6");
        Room room7 = new Room("Room 7", new Coordinate(2, 2), "Title of Room 7", "Description of Room 7");
        Room room8 = new Room("Room 8", new Coordinate(2, 3), "Title of Room 8", "Description of Room 8");
        Room room9 = new Room("Room 9", new Coordinate(2, 4), "Title of Room 9", "Description of Room 9");
        Room room10 = new Room("Room 10", new Coordinate(3, 1), "Title of Room 10", "Description of Room 10");
        Room room11 = new Room("Room 11", new Coordinate(2, 4), "Title of Room 11", "Description of Room 11");
        Room room12 = new Room("Room 12", new Coordinate(4, 1), "Title of Room 12", "Description of Room 12");
        Room room13 = new Room("Room 13", new Coordinate(4, 2), "Title of Room 13", "Description of Room 13");

        room12.setAlien(new Clicker());
        room5.close();

        building.addRoom(room1);
        building.addRoom(room2);
        building.addRoom(room3);
        building.addRoom(room4);
        building.addRoom(room5);
        building.addRoom(room6);
        building.addRoom(room7);
        building.addRoom(room8);
        building.addRoom(room9);
        building.addRoom(room10);
        building.addRoom(room11);
        building.addRoom(room12);
        building.addRoom(room13);


        // Set the current coordinate to (2, 4)
        Coordinate currentCoordinate = new Coordinate(4, 2);
        Room currentRoom = building.getRoom(currentCoordinate.toString());
        Room temp;
        List<String> availableDirections = new ArrayList<>();


        // Main loop
        while (true) {
            System.out.println("You are currently in " + currentRoom.getTitle());
            System.out.println("Available directions: " + building.getAvailableDirections(currentRoom));
            System.out.println("Enter a command (Nord, Sud, Est, Ovest, or Esci):");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "north":
                    currentCoordinate.setRow(currentCoordinate.getRow() - 1);
                    break;
                case "south":
                    currentCoordinate.setRow(currentCoordinate.getRow() + 1);
                    break;
                case "east":
                    currentCoordinate.setColumn(currentCoordinate.getColumn() + 1);
                    break;
                case "west":
                    currentCoordinate.setColumn(currentCoordinate.getColumn() - 1);
                    break;
                case "save":
                    gameSave = new GameSave();
                    gameSave.saveGame(player, building);
                    System.out.println("Game saved.");
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Please enter north, south, east, west, or exit.");
                    continue;
            }
            // Check if the new coordinate is a valid room
            temp = currentRoom;
            currentRoom = building.getRoom(currentCoordinate.toString());
            if (currentRoom != null) {
                if (!currentRoom.isClosed()){
                    System.out.println("You are now in " + currentRoom.getTitle());
                    if (currentRoom.getAlien()!= null){
                        Fight fight = new Fight(player, currentRoom.getAlien());
                        fight.fight();
                        currentRoom.setAlien(null);
                    }
                }
                else {
                    System.out.println("The room is closed. You need a key to access it.");
                    currentRoom = temp;
                    currentCoordinate = currentRoom.getCoordinate();
                    temp = null;
                }
            } else {
                System.out.println("You cannot go that way.");
                currentRoom = temp;
                currentCoordinate = currentRoom.getCoordinate();
                temp = null;
            }
        }
    }

    public static Room createRandomRoom(String name, Coordinate coordinate) {
        String title = "Title of " + name;
        String description = "Description of " + name;
        return new Room(name, coordinate, title, description);
    }
}
