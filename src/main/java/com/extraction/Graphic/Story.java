package com.extraction.Graphic;

import com.extraction.aliens.Clicker;
import com.extraction.aliens.Runner;
import com.extraction.aliens.Shambler;
import com.extraction.map.Building;
import com.extraction.map.Coordinate;
import com.extraction.map.Fight;
import com.extraction.map.Room;
import com.extraction.player.Player;

public class Story {
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player;
    IntroductionDialog introduction;

    int playerX;
    int playerY;
    int nextColumn, nextRow;
    Room nextRoom;
    boolean hasCo = false;
    Fight fight;

    Building building;

    public Story(Game game, UI ui, VisibilityManager vm, Building building, Player player) {
        this.game = game;
        this.ui = ui;
        this.vm = vm;
        this.building = building;
        this.player = player;
    }

    public void defaultSetup() {
        game.startRoom.setIconPath(ui.exitIconPath);
        ui.setIcon(
                game.startRoom.getCoordinate().getRow(),
                game.startRoom.getCoordinate().getColumn(),
                ui.playerIconPath
        );
        updatePos();
        ui.topLabelCol1.setText("HP: " + player.getHealth());
        ui.topLabelCol2.setText("Weapon: "/* + player.getWeapon().getName()*/);
        ui.mainTextArea.setText(
                "Sei un operatore della squadra Rainbow,\n" +
                "un'élite militare specializzata in infiltrazioni e salvataggio di ostaggi. \n" +
                "Il mondo è stato invaso da parassiti alieni noti come archei, generando caos e terrore. \n" +
                "La squadra Rainbow deve intervenire per riportare la pace."
        );

        introduction = new IntroductionDialog();

        game.nextPosition0 = "Introduction";
    }

    private void setNextPositions(String next1, String next2, String next3, String next4) {
        game.nextPosition1 = next1;
        game.nextPosition2 = next2;
        game.nextPosition3 = next3;
        game.nextPosition4 = next4;
    }

    public void selectPosition(String nextPosition) {
        switch (nextPosition) {
            case "Null":
                vm.showMessage("Path Closed", 1000);
                break;
            case "Introduction":
                try {
                    ui.mainTextArea.setText(introduction.nextDialogue());
                } catch (Exception e) {
                    map();
                }
                break;
            case "Extraction":
                ui.mainTextArea.setText("");
                break;
            case "Attack":
                fight.attack();
                break;
            case "Leave":
                fight.run();
                break;
            case "Defend":
                fight.defend();
                break;
            case "Elude":
                fight.elude();
                break;
            case "Map":
                map();
                break;
            case "Proceed":
                proceed();
                break;
            case "Room4_2":
                room4_2();
                break;
            case "Room4_1":
                //room4_1();
                room(4, 1);
                break;
            case "Room3_1":
                room3_1();
                break;
            case "Room2_1":
                room2_1();
                break;
            case "Room2_0":
                room2_0();
                break;
            case "Room1_0":
                room1_0();
                break;
            case "Room2_2":
                room2_2();
                break;
            case "Room1_2":
                room1_2();
                break;
            case "Room0_2":
                room0_2();
                break;
            case "Room2_3":
                room2_3();
                break;
            case "Room1_3":
                room1_3();
                break;
            case "Room2_4":
                room2_4();
                break;
            case "Room3_4":
                room3_4();
                break;
            default:
                System.out.println("Invalid position");
                break;
        }
    }

    public void map() {
        vm.showMapScreen();
        updatePos();

        ui.actionButton1.setText("NORTH");
        ui.actionButton2.setText("EAST");
        ui.actionButton3.setText("SOUTH");
        ui.actionButton4.setText("WEST");

        setNextPositions(
                building.getAvailableDirections(player.getCurrentRoom_()).contains("North") ?
                        "Room" + (playerY - 1) + "_" + playerX :
                        "Null",
                building.getAvailableDirections(player.getCurrentRoom_()).contains("East") ?
                        "Room" + playerY + "_" + (playerX + 1) :
                        "Null",
                building.getAvailableDirections(player.getCurrentRoom_()).contains("South") ?
                        "Room" + (playerY + 1) + "_" + playerX :
                        "Null",
                building.getAvailableDirections(player.getCurrentRoom_()).contains("West") ?
                        "Room" + playerY + "_" + (playerX - 1) :
                        "Null"
        );
    }

    private void updatePos() {
        playerX = player.getCurrentRoom_().getCoordinate().getColumn();
        playerY = player.getCurrentRoom_().getCoordinate().getRow();
    }

    private void proceed() {
        ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
        ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
        building.getRoom(new Coordinate(nextRow, nextColumn).toString()).setIconPath(ui.checkIconPath);

        player.setCurrentRoom(nextRoom);
        updatePos();

        map();
    }

    public void room4_2() {
        nextRow = 4;
        nextColumn = 2;

        playerX = player.getCurrentRoom_().getCoordinate().getColumn();
        playerY = player.getCurrentRoom_().getCoordinate().getRow();

        if (hasCo) {
            System.out.println("You Win");
        }
        else {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room4_2);
            updatePos();
            map();
        }
    }

    public void room(int nextRow, int nextColumn) {
        this.nextRow = nextRow;
        this.nextColumn = nextColumn;
        Coordinate temp = new Coordinate(nextRow, nextColumn);
        Room room = building.getRoom(temp.toString());

        if (room.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(room);
            updatePos();
            map();
        } else if (room.getAlien() != null) {
            nextRoom = room;
            vm.showFightScreen();

            switch (room.getAlien().getName()) {
                case "Clicker":
                    fightClicker();
                    break;
                case "Runner":
                    fightRunner();
                    break;
                case "Shambler":
                    fightShambler();
                    break;
                default:
                    break;
            }
        } else {
            vm.showFightScreen();
            fightClicker();
        }
    }

    public void room4_1() {
        nextRow = 4;
        nextColumn = 1;

        if (game.room4_1.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room4_1);
            updatePos();
            map();
        } else {
            nextRoom = game.room4_1;
            fightClicker();

            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";*/
        }
    }

    public void fightClicker() {
        Clicker clicker = new Clicker();
        fight = new Fight(player, clicker, ui, vm);
        setNextPositions("Attack", "Leave", "Defend", "Elude");
        fight.fight();
    }

    public void fightRunner() {
        Runner runner = new Runner();
        fight = new Fight(player, runner, ui, vm);
        setNextPositions("Attack", "Leave", "Defend", "Elude");
        fight.fight();
    }

    public void fightShambler() {
        Shambler shambler = new Shambler();
        fight = new Fight(player, shambler, ui, vm);
        setNextPositions("Attack", "Leave", "Defend", "Elude");
        fight.fight();
    }

    public void room3_1() {
        nextRow = 3;
        nextColumn = 1;

        if (game.room3_1.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room3_1);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";*/

            nextRoom = game.room3_1;
            proceed();
        }
    }

    public void room2_1() {
        nextRow = 2;
        nextColumn = 1;

        if (game.room2_1.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room2_1);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";*/

            nextRoom = game.room2_1;
            proceed();
        }
    }

    public void room2_0() {
        nextRow = 2;
        nextColumn = 0;

        if (game.room2_0.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room2_0);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room2_0;
            proceed();
        }
    }

    public void room1_0() {
        nextRow = 1;
        nextColumn = 0;

        if (game.room1_0.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room1_0);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room1_0;
            proceed();
        }
    }

    public void room2_2() {
        nextRow = 2;
        nextColumn = 2;

        if (game.room2_2.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room2_2);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room2_2;
            proceed();
        }
    }

    public void room1_2() {
        nextRow = 1;
        nextColumn = 2;

        if (game.room1_2.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room1_2);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room1_2;
            proceed();
        }
    }

    public void room0_2() {
        nextRow = 0;
        nextColumn = 2;

        if (game.room0_2.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room0_2);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room0_2;
            proceed();
        }
    }

    public void room2_3() {
        nextRow = 2;
        nextColumn = 3;

        if (game.room2_3.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room2_3);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room2_3;
            proceed();
        }
    }

    public void room1_3() {
        nextRow = 1;
        nextColumn = 3;

        if (game.room1_3.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room1_3);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room1_3;
            proceed();
        }
    }

    public void room2_4() {
        nextRow = 2;
        nextColumn = 4;

        if (game.room2_4.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room2_4);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room2_4;
            proceed();
        }
    }

    public void room3_4() {
        nextRow = 3;
        nextColumn = 4;

        if (game.room3_4.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(game.room3_4);
            updatePos();
            map();
        } else {
            /*vm.showTextScreen();

            ui.mainTextArea.setText("Room 4_1");

            ui.actionButton1.setText("TopLeft 4_1");
            ui.actionButton2.setText("TopRight 4_1");
            ui.actionButton3.setText("Proceed");
            ui.actionButton4.setText("Map");

            game.nextPosition1 = "Null";
            game.nextPosition2 = "Null";
            game.nextPosition3 = "Proceed";
            game.nextPosition4 = "Map";

            nextRoom = game.room4_1;*/

            nextRoom = game.room3_4;
            proceed();
        }
    }
}
