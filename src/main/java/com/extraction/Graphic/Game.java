package com.extraction.Graphic;

import com.extraction.GameSave;
import com.extraction.S3Uploader;
import com.extraction.aliens.*;
import com.extraction.map.Building;
import com.extraction.map.Coordinate;
import com.extraction.map.Room;
import com.extraction.player.Player;
import com.extraction.items.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.TypeAdapterFactory.*;
import com.google.gson.reflect.TypeToken;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The Game class is the main class for the game. It sets up the game, creates the UI, and handles user actions.
 */
public class Game {
    ButtonsHandler bHandler = new ButtonsHandler();
    UI ui = new UI(bHandler);
    VisibilityManager vm = new VisibilityManager(ui);
    Building building;
    Player player;
    Story story;

    String nextPosition0, nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    Room room4_2, room4_1, room3_1, room2_1, room2_0, room1_0, room2_2, room1_2, room0_2, room2_3, room1_3, room2_4, room3_4;

    /**
     * This is the constructor for the Game class. It sets up the game and starts the game loop.
     * @throws IOException If an I/O error occurs.
     */
    public Game() throws IOException {
        ui.homeScreen("Extraction");
        ui.gameScreen();

        newGame();
    }

    /**
     * This method is called to start a new game. It resets the game state and starts a new game.
     */
    public void newGame() {
        player = new Player(vm);
        building = new Building();
        story = new Story(this, ui, vm, building, player);

        room4_2 = new Room("Room 4_2", new Coordinate(4, 2), "Hall", "A large dark hall");
        room4_1 = new Room("Room 4_1", new Coordinate(4, 1), "Hall", "A large dark hall");
        room3_1 = new Room("Room 3_1", new Coordinate(3, 1), "Hall", "A large dark hall");
        room2_1 = new Room("Room 2_1", new Coordinate(2, 1), "Hall", "A large dark hall");
        room2_0 = new Room("Room 2_0", new Coordinate(2, 0), "Archives", "Archives, you can find some interesting documents here.");
        room1_0 = new Room("Room 1_0", new Coordinate(1, 0), "Archives", "Archives, you can find some interesting documents here.");
        room2_2 = new Room("Room 2_2", new Coordinate(2, 2), "Office", "Office, there are some tables and chairs turned upside down.");
        room1_2 = new Room("Room 1_2", new Coordinate(1, 2), "Office", "Office, there are some tables and chairs turned upside down.");
        room0_2 = new Room("Room 0_2", new Coordinate(0, 2), "?", "A room with a big door, it seems to be locked.");
        room2_3 = new Room("Room 2_3", new Coordinate(2, 3), "Office", "Office, there are some tables and chairs turned upside down.");
        room1_3 = new Room("Room 1_3", new Coordinate(1, 3), "Office", "Office, there are some tables and chairs turned upside down.");
        room2_4 = new Room("Room 2_4", new Coordinate(2, 4), "Closet", "a small room with a lot of cleaning tools.");
        room3_4 = new Room("Room 3_4", new Coordinate(3, 4), "Closet", "a small room with a lot of cleaning tools.");

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

        Runner runner = new Runner();
        Clicker clicker = new Clicker();
        Shambler shambler = new Shambler();

        Key key1 = new Key();
        Key key2 = new Key();
        MedKit medKit = new MedKit();
        Shield shield = new Shield();
        TNT tnt = new TNT();
        Torch torch = new Torch();
        Weapon revolver = new Weapon("REVOLVER");
        Weapon ak47 = new Weapon("AK47");
        Weapon uspsworm = new Weapon("USPSWORM");

        //room4_1.setAlien(runner);
        room4_1.addItem(revolver);
        room4_1.addItem(key1);

        room2_1.addItem(shield);

        room2_0.setAlien(clicker);
        room2_0.addItem(torch);

        room1_0.addItem(uspsworm);
        room1_0.addItem(key1);

        room2_2.addItem(tnt);

        room2_3.addItem(medKit);
        room2_3.addItem(ak47);

        room2_4.setAlien(shambler);

        room3_4.addItem(key2);

        room2_3.setDark(true);

        story.startRoom = room4_2;
        player.setCurrentRoom(story.startRoom);


        story.coRoom = room0_2;

        ui.newMap();
        for(Room room : building.getRooms()) {
            room.setIconPath("");
        }

        story.defaultSetup();
        vm.showHomeScreen();
    }

    /**
     * This method is called to load a game. It loads the game state from a file and resumes the game.
     * @param game The name of the game to load.
     */
    public void loadGame(String game) {

    }

    public static void main(String[] args) throws IOException {
        new Game();
    }

    /**
     * ButtonsHandler is an inner class that implements ActionListener. It handles all button click events.
     */
    protected class ButtonsHandler implements ActionListener {
        @Override
        /**
         * This method is called whenever a button is clicked. It handles the button click event.
         * @param e The event that occurred.
         */
        public void actionPerformed(ActionEvent e) {
            String buttonClicked = e.getActionCommand();

            switch (buttonClicked) {
                case "Exit":
                    ui.resetActionButtons();
                    ui.resetTitle();
                    newGame();
                    break;
                case "Save":
                    try {
                        S3Uploader s3Uploader = new S3Uploader("default", "eu-north-1", "edidsgamesave");
                        s3Uploader.saveGame(player, building);
                        //@TODO: Display a message to the user that the game has been saved
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    newGame();
                    break;
                case "Start":
                    vm.showDialogScreen();
                    //story.map();
                    break;
                case "Load":
                    try {
                        S3Uploader s3Uploader = new S3Uploader("default", "eu-north-1", "edidsgamesave");
                        s3Uploader.downloadAllGames();
                    }
                    catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    vm.showLoadScreen();
                    //tira con gson
                    e.getActionCommand();
                    break;
                case "MedKit":
                    story.fight.heal();
                    break;
                case "TNT":
                    story.fight.tnt();
                    break;
                case "NextDialog":
                    story.selectPosition(nextPosition0);
                    break;
                case "ExitItems":
                    story.exitItems();
                    break;
                case "ThrowItems":
                    story.throwItems();
                    break;
                case "TopLeft":
                    story.selectPosition(nextPosition1);
                    break;
                case "TopRight":
                    story.selectPosition(nextPosition2);
                    break;
                case "BottomLeft":
                    story.selectPosition(nextPosition3);
                    break;
                case "BottomRight":
                    story.selectPosition(nextPosition4);
                    break;
                case "LoadFile":
                    JButton button = (JButton) e.getSource();
                    String filename = button.getText();


                    Gson gson = new GsonBuilder().registerTypeAdapter(Alien.class, new AlienTypeAdapter()).setPrettyPrinting().create();
                    try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/com/extraction/states/" + filename)) {
                        GameSave gameData = new GameSave(null, null, null);
                        gameData = gson.fromJson(reader, gameData.getClass());
                        System.out.println("Game loaded");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }


                default:

            }
        }
    }

    /**
     * loadHandler is an inner class that implements ActionListener. It handles all load game events.
     */
    protected class loadHandler implements ActionListener {

        /**
         * This method is called whenever a load game event occurs. It handles the load game event.
         * @param e The event that occurred.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            loadGame(e.getActionCommand());
        }
    }
}
