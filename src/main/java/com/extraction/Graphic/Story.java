package com.extraction.Graphic;

import com.extraction.aliens.Alien;
import com.extraction.items.Item;
import com.extraction.items.Weapon;
import com.extraction.map.Building;
import com.extraction.map.Coordinate;
import com.extraction.map.Fight;
import com.extraction.map.Room;
import com.extraction.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

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

    ActionListener actionListener1, actionListener2, actionListener3, actionListener4;

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

        //introduction = new IntroductionDialog();

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
            case"ShowItems":
                showItems();
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
                //room3_1();
                room(3, 1);
                break;
            case "Room2_1":
                //room2_1();
                room(2, 1);
                break;
            case "Room2_0":
                //room2_0();
                room(2, 0);
                break;
            case "Room1_0":
                //room1_0();
                room(1, 0);
                break;
            case "Room2_2":
                //room2_2();
                room(2, 2);
                break;
            case "Room1_2":
                //room1_2();
                room(1, 2);
                break;
            case "Room0_2":
                //room0_2();
                room(0, 2);
                break;
            case "Room2_3":
                //room2_3();
                room(2, 3);
                break;
            case "Room1_3":
                //room1_3();
                room(1, 3);
                break;
            case "Room2_4":
                //room2_4();
                room(2, 4);
                break;
            case "Room3_4":
                //room3_4();
                room(3, 4);
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

        player.setCurrentRoom(nextRoom);

        updatePos();

        map();
    }

    public void checkRoom() {
        Room room = building.getRoom(new Coordinate(nextRow, nextColumn).toString());
        room.setIconPath(ui.checkIconPath);
        room.setAlien(null);
    }

    private void showItems() {
        vm.showTextScreen();
        List<Item> items = nextRoom.getItems();
        if(items.isEmpty()) proceed();
        StringBuilder str = new StringBuilder("You found:\n");
        for (Item item : items) {
            str.append("- ").append(item.getName()).append("\n");
        }
        ui.mainTextArea.setText(str.toString());

        String item1, item2, item3, item4;
        try {
            ui.actionButton1.setText("");
            ui.actionButton2.setText("");
            ui.actionButton3.setText("");
            ui.actionButton4.setText("");
            ui.setUnenableButtons();

            item1 = items.get(0) instanceof Weapon ? ((Weapon) items.get(0)).getType() : items.get(0).getName();
            ui.actionButton1.setText(item1);
            ui.actionButton1.setEnabled(true);
            ui.actionButton1.removeActionListener(ui.bHandler);
            actionListener1 = addListener(ui.actionButton1, item1);
            ui.actionButton1.addActionListener(actionListener1);

            item2 = items.get(1) instanceof Weapon ? ((Weapon) items.get(1)).getType() : items.get(1).getName();
            ui.actionButton2.setText(item2);
            ui.actionButton2.setEnabled(true);
            ui.actionButton2.removeActionListener(game.bHandler);
            actionListener2 = addListener(ui.actionButton2, item2);
            ui.actionButton2.addActionListener(actionListener2);

            item3 = items.get(2) instanceof Weapon ? ((Weapon) items.get(2)).getType() : items.get(2).getName();
            ui.actionButton3.setText(item3);
            ui.actionButton3.setEnabled(true);
            ui.actionButton3.removeActionListener(game.bHandler);
            actionListener3 = addListener(ui.actionButton3, item3);
            ui.actionButton3.addActionListener(actionListener3);

            item4 = items.get(3) instanceof Weapon ? ((Weapon) items.get(3)).getType() : items.get(3).getName();
            ui.actionButton4.setText(item4);
            ui.actionButton4.setEnabled(true);
            ui.actionButton4.removeActionListener(game.bHandler);
            actionListener4 = addListener(ui.actionButton4, item4);
            ui.actionButton4.addActionListener(actionListener4);

        } catch (Exception ignore) {}
    }

    private ActionListener addListener(JButton b, String item) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.setEnabled(false);
                execute(item);
            }
        };
    }

    private void execute (String item) {
        switch (item) {
            case "MedKit":
                break;
            case "TNT":
                break;
            case "Shield":
                break;
            case "Key":
                break;
            case "Torch":
                break;
            case "REVOLVER":
                break;
            case "USPSWORM":
                break;
            case "AK47":
                break;
            default:
        }
    }

    public void exitItems() {
        ui.actionButton1.removeActionListener(actionListener1);
        ui.actionButton2.removeActionListener(actionListener2);
        ui.actionButton3.removeActionListener(actionListener3);
        ui.actionButton4.removeActionListener(actionListener4);

        ui.actionButton1.removeActionListener(ui.bHandler);
        ui.actionButton2.removeActionListener(ui.bHandler);
        ui.actionButton3.removeActionListener(ui.bHandler);
        ui.actionButton4.removeActionListener(ui.bHandler);

        ui.actionButton1.addActionListener(ui.bHandler);
        ui.actionButton2.addActionListener(ui.bHandler);
        ui.actionButton3.addActionListener(ui.bHandler);
        ui.actionButton4.addActionListener(ui.bHandler);
        proceed();
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
        Coordinate coords = new Coordinate(nextRow, nextColumn);
        Room room = building.getRoom(coords.toString());
        nextRoom = room;
        //player.setCurrentRoom(nextRoom);

        if (room.getAlien() != null) {
            vm.showFightScreen();
            fightAlien(room.getAlien());
        } else if (!room.getItems().isEmpty()) {
            if (!room.getIconPath().equals(ui.checkIconPath)) checkRoom();
            showItems();
        } else {
            if (!room.getIconPath().equals(ui.checkIconPath)) checkRoom();
            proceed();
        }
        /*if (room.getIconPath().equals(ui.checkIconPath)) {
            ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
            ui.setIcon(nextRow, nextColumn, ui.playerIconPath);
            player.setCurrentRoom(room);
            updatePos();
            map();
        } else if (room.getAlien() != null) {
            nextRoom = room;
            vm.showTextScreen();
            fightAlien(room.getAlien());
        } else {

        }*/
    }

    public void fightAlien(Alien alien) {
        fight = new Fight(player, alien, ui, vm, this);
        setNextPositions("Attack", "Leave", "Defend", "Elude");
        game.nextPosition0 = "Room" + nextRow + "_" + nextColumn;
        fight.fight();
    }
}
