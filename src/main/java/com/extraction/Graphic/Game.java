package com.extraction.Graphic;

import com.extraction.S3Uploader;
import com.extraction.map.Building;
import com.extraction.map.Coordinate;
import com.extraction.map.Room;
import com.extraction.player.Player;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Game {
    ButtonsHandler bHandler = new ButtonsHandler();
    UI ui = new UI(bHandler);
    VisibilityManager vm = new VisibilityManager(ui);
    Building building = new Building();
    Player player = new Player();
    Story story = new Story(this, ui, vm, building, player);

    String nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    Room room4_2, room4_1, room3_1, room2_1, room2_0, room1_0, room2_2, room1_2, room0_2, room2_3, room1_3, room2_4, room3_4;
    Room startRoom;

    public Game() {
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

        startRoom = room4_2;
        player.setCurrentRoom(startRoom);

        ui.homeScreen();
        ui.gameScreen();

        story.defaultSetup();

        vm.showHomeScreen();
    }

    public void newGame() {
        player.setCurrentRoom(startRoom);
        ui.newMap();
        for(Room room : building.getRooms()) {
            room.setIconPath("");
        }
        story.defaultSetup();
    }

    public static void main(String[] args) {
        new Game();
    }

    protected class ButtonsHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonClicked = e.getActionCommand();

            switch (buttonClicked) {
                case "Exit":
                    newGame();
                    vm.showHomeScreen();
                    break;
                case "Save":
                    try {
                        S3Uploader s3Uploader = new S3Uploader("default", "eu-north-1", "edidsgamesave");
                        s3Uploader.saveGame(player, building);
                        //@TODO: Display a message to the user that the game has been saved

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "Start":
                    vm.showTextScreen();
                    //story.map();
                    break;
                case "Load":
                    /*try {
                        S3Uploader s3Uploader = new S3Uploader("default", "eu-north-1", "edidsgamesave");
                        s3Uploader.downloadAllGames();
                        //@TODO: listare i vari salvataggi e permettere all'utente di sceglierne uno, che verrà poi caricato. I restanti non selezionati vengono eliminati localmente
                    }
                    catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }*/
                    vm.showLoadScreen();
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
                default:

            }
        }
    }
}
