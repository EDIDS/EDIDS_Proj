package com.extraction.Graphic;

import com.extraction.GameSave;
import com.extraction.S3Uploader;
import com.extraction.aliens.*;
import com.extraction.map.Building;
import com.extraction.map.Coordinate;
import com.extraction.map.Room;
import com.extraction.player.Player;
import com.extraction.items.*;
import com.extraction.utils.GameSaveTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Game class is the main class for the game. It sets up the game, creates the UI, and handles user actions.
 */
public class Game {
    ButtonsHandler bHandler = new ButtonsHandler();
    UI ui = UI.getInstance(bHandler);
    VisibilityManager vm = VisibilityManager.getInstance(ui);
    Building building;
    Player player;
    Story story;
    String loadedFileName = "";

    String nextPosition0, nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    Room room4_2, room4_1, room3_1, room2_1, room2_0, room1_0, room2_2, room1_2, room0_2, room2_3, room1_3, room2_4, room3_4;

    /**
     * This is the constructor for the Game class. It sets up the game and starts the game loop.
     */
    public Game() {
        ui.homeScreen("Extraction");
        ui.gameScreen();
        vm.showHomeScreen();
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

        room4_1.setAlien(runner);
        room4_1.addItem(revolver);

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
    }

    /**
     * This method is called to load a game. It loads the game state from a file and resumes the game.
     */
    public void loadGame() {
        story = new Story(this, ui, vm, building, player);
        player.setVM(this.vm);

        ui.newMap();
        for(Room room : building.getRooms()) {
            int row = room.getCoordinate().getRow();
            int col = room.getCoordinate().getColumn();
            ui.setIcon(row, col, room.getIconPath());
        }
        int row = player.getCurrentRoom_().getCoordinate().getRow();
        int col = player.getCurrentRoom_().getCoordinate().getColumn();
        ui.setIcon(row, col, ui.playerIconPath);

        ui.setCol1(player.getHealth() + "");
        if (player.getWeapon() != null)
            ui.setCol2(player.getWeapon().getType());
        ui.setCol3(player.getScore() + "");

        story.map();
    }

    public static void main(String[] args) {
        new Game();
    }

    /**
     * ButtonsHandler is an inner class that implements ActionListener. It handles all button click events.
     */
    protected class ButtonsHandler implements ActionListener {
        /**
         * This method is called whenever a button is clicked. It handles the button click event.
         * @param e The event that occurred.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonClicked = e.getActionCommand();

            switch (buttonClicked) {
                case "Exit":

                    if (!loadedFileName.isEmpty()) {
                        File fileToDelete = new File(System.getProperty("user.dir") + "/src/main/java/com/extraction/states/" + loadedFileName);
                        fileToDelete.delete();
                    }

                    ui.resetActionButtons();
                    ui.resetTitle();
                    vm.showHomeScreen();
                    break;
                case "Save":
                    try {
                        S3Uploader s3Uploader = new S3Uploader("default", "eu-north-1", "edidsgamesave");

                        if (!loadedFileName.isEmpty()) {
                            s3Uploader.deleteFile(loadedFileName);
                            s3Uploader.removeFileFromGameList(loadedFileName);
                            s3Uploader.saveGame(player, building, loadedFileName);
                        } else s3Uploader.saveGame(player, building);

                        vm.showMessage("Game Saved", 1500, Color.GREEN);
                        ui.resetActionButtons();
                        ui.resetTitle();
                        vm.showHomeScreen();
                    } catch (IOException ex) {
                        vm.showMessage("Impossible to Save", 1000, Color.RED);
                    }
                    break;
                case "Start":
                    loadedFileName = "";
                    newGame();
                    vm.showDialogScreen();
                    break;
                case "Load":
                    try {
                        S3Uploader s3Uploader =  new S3Uploader("default", "eu-north-1", "edidsgamesave");
                        s3Uploader.generateGameList();
                        if (ui.loadList()) vm.showLoadScreen();
                        else vm.showMessage("No Game Saved", 1000, Color.ORANGE);
                    }
                    catch (Exception ex) {
                        vm.showMessage("Access Denied", 1000, Color.RED);
                    }
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
                    loadedFileName = button.getText();

                    try {
                        S3Uploader s3Uploader = new S3Uploader("default", "eu-north-1", "edidsgamesave");
                        s3Uploader.downloadSaveFile(loadedFileName);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    Gson gson = new GsonBuilder().registerTypeAdapter(GameSave.class, new GameSaveTypeAdapter()).create();
                    try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/com/extraction/states/" + loadedFileName)) {
                        GameSave gameData = new GameSave(null, null);
                        gameData = gson.fromJson(reader, gameData.getClass());

                        building = gameData.getBuilding();
                        player = gameData.getPlayer();
                        loadGame();

                        System.out.println("Game loaded");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                default:
            }
        }
    }
}
