package com.extraction.Graphic;

import com.extraction.aliens.Alien;
import com.extraction.items.*;
import com.extraction.map.Building;
import com.extraction.map.Coordinate;
import com.extraction.map.Fight;
import com.extraction.map.Room;
import com.extraction.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Story {
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player;
    IntroductionDialog introduction;
    EndingDialog ending;

    int playerX;
    int playerY;
    int nextColumn, nextRow;
    Room nextRoom;
    Room startRoom;
    Room coRoom;
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
        startRoom.setIconPath(ui.exitIconPath);
        ui.setIcon(
                startRoom.getCoordinate().getRow(),
                startRoom.getCoordinate().getColumn(),
                ui.playerIconPath
        );
        updatePlayerPos();
        ui.topLabelCol1.setText("HP: " + player.getHealth());
        Weapon weapon = player.getWeapon();
        if (weapon == null) ui.topLabelCol2.setText("Weapon: Punch");
        else ui.topLabelCol2.setText("Weapon: " + player.getWeapon().getType());

        introduction = new IntroductionDialog();
        ui.mainTextArea.setText(introduction.nextDialogue());

        ending = new EndingDialog();

        ui.setEnableButtons();

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
                vm.showMessage("Path Closed", 1000, Color.RED);
                break;
            case "Introduction":
                try {
                    ui.mainTextArea.setText(introduction.nextDialogue());
                } catch (Exception e) {
                    map();
                }
                break;
            case "Extraction":
                try {
                    ui.mainTextArea.setText(ending.nextDialogue());
                } catch (Exception e) {
                    vm.showEndScreen("YOU WIN");
                }
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
            case "ShowItems":
                showItems();
                break;
            case "Map":
                map();
                break;
            case "Proceed":
                proceed();
                break;
            case "Room4_2":
                room(4, 2);
                break;
            case "Room4_1":
                room(4, 1);
                break;
            case "Room3_1":
                room(3, 1);
                break;
            case "Room2_1":
                room(2, 1);
                break;
            case "Room2_0":
                room(2, 0);
                break;
            case "Room1_0":
                room(1, 0);
                break;
            case "Room2_2":
                room(2, 2);
                break;
            case "Room1_2":
                room(1, 2);
                break;
            case "Room0_2":
                room(0, 2);
                break;
            case "Room2_3":
                room(2, 3);
                break;
            case "Room1_3":
                room(1, 3);
                break;
            case "Room2_4":
                room(2, 4);
                break;
            case "Room3_4":
                room(3, 4);
                break;
            default:
                System.out.println("Invalid position");
                break;
        }
    }

    public void map() {
        vm.showMapScreen();
        updatePlayerPos();

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

    private void updatePlayerPos() {
        playerX = player.getCurrentRoom_().getCoordinate().getColumn();
        playerY = player.getCurrentRoom_().getCoordinate().getRow();
    }

    private void proceed() {
        ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
        ui.setIcon(nextRow, nextColumn, ui.playerIconPath);

        player.setCurrentRoom(nextRoom);

        updatePlayerPos();

        map();
    }

    public void checkRoom() {
        Room room = building.getRoom(new Coordinate(nextRow, nextColumn).toString());
        room.setIconPath(ui.checkIconPath);
        room.setAlien(null);
    }

    private void showItems() {
        vm.showTextScreen();
        ui.exitItemButton.setEnabled(true);
        ui.throwButton.setEnabled(true);
        List<Item> items = nextRoom.getItems();
        if (items.isEmpty()) proceed();
        StringBuilder str = new StringBuilder("You found:\n");
        str = getItems(items, str);
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

        } catch (Exception ignore) {
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

    private ActionListener addListener(JButton b, String item) {
        return e -> {
            b.setEnabled(false);
            execute(item);
        };
    }

    public void throwItems() {
        vm.showTextScreen();
        exitThrow();
        ui.exitItemButton.setEnabled(false);
        ui.throwButton.setEnabled(false);
        List<Item> items = player.getBag();
        if (items.isEmpty()) {
            ui.setUnenableButtons();
            ui.mainTextArea.setText("No items Found in your Bag");
            Timer timer = new Timer(2000, e -> showItems());
            timer.setRepeats(false);
            timer.start();
            return;
        }
        StringBuilder str = new StringBuilder("You have:\n");
        str = getItems(items, str);
        ui.mainTextArea.setText(str.toString());

        String item1, item2, item3, item4;
        try {
            ui.actionButton1.setText("");
            ui.actionButton2.setText("");
            ui.actionButton3.setText("");
            ui.actionButton4.setText("");
            ui.setUnenableButtons();

            item1 = items.get(0).getName();
            ui.actionButton1.setText(item1);
            ui.actionButton1.setEnabled(true);
            ui.actionButton1.removeActionListener(ui.bHandler);
            actionListener1 = addListener(ui.actionButton1, "Remove" + item1);
            ui.actionButton1.addActionListener(actionListener1);

            item2 = items.get(1).getName();
            ui.actionButton2.setText(item2);
            ui.actionButton2.setEnabled(true);
            ui.actionButton2.removeActionListener(game.bHandler);
            actionListener2 = addListener(ui.actionButton2, "Remove" + item2);
            ui.actionButton2.addActionListener(actionListener2);

            item3 = items.get(2).getName();
            ui.actionButton3.setText(item3);
            ui.actionButton3.setEnabled(true);
            ui.actionButton3.removeActionListener(game.bHandler);
            actionListener3 = addListener(ui.actionButton3, "Remove" + item3);
            ui.actionButton3.addActionListener(actionListener3);

            item4 = items.get(3).getName();
            ui.actionButton4.setText(item4);
            ui.actionButton4.setEnabled(true);
            ui.actionButton4.removeActionListener(game.bHandler);
            actionListener4 = addListener(ui.actionButton4, "Remove" + item4);
            ui.actionButton4.addActionListener(actionListener4);

        } catch (Exception ignore) {
        }
    }

    private void exitThrow() {
        ui.actionButton1.removeActionListener(actionListener1);
        ui.actionButton2.removeActionListener(actionListener2);
        ui.actionButton3.removeActionListener(actionListener3);
        ui.actionButton4.removeActionListener(actionListener4);
    }

    public StringBuilder getItems(List<Item> items, StringBuilder str) {
        for (Item item : items) {
            if (item instanceof Weapon) str.append("- ").append(((Weapon) item).getType()).append("\n");
            else str.append("- ").append(item.getName()).append("\n");
        }
        return str;
    }

    private void execute(String item) {
        switch (item) {
            case "MedKit":
                MedKit med = new MedKit();
                player.addItem(med);
                nextRoom.removeItem("MedKit");
                break;
            case "TNT":
                TNT tnt = new TNT();
                player.addItem(tnt);
                nextRoom.removeItem("TNT");
                break;
            case "Shield":
                Shield shield = new Shield();
                player.addItem(shield);
                nextRoom.removeItem("Shield");
                break;
            case "Key":
                Key key = new Key();
                player.addItem(key);
                nextRoom.removeItem("Key");
                break;
            case "Torch":
                Torch torch = new Torch();
                player.addItem(torch);
                nextRoom.removeItem("Torch");
                break;
            case "REVOLVER":
                Weapon revolver = new Weapon("REVOLVER");
                player.addItem(revolver);
                ui.topLabelCol2.setText("Weapon: " + player.getWeapon().getType());
                nextRoom.removeItem("Weapon");
                break;
            case "USPSWORM":
                Weapon usp = new Weapon("USPSWORM");
                player.addItem(usp);
                ui.topLabelCol2.setText("Weapon: " + player.getWeapon().getType());
                nextRoom.removeItem("Weapon");
                break;
            case "AK47":
                Weapon ak = new Weapon("AK47");
                player.addItem(ak);
                ui.topLabelCol2.setText("Weapon: " + player.getWeapon().getType());
                nextRoom.removeItem("Weapon");
                break;
            case "RemoveMedKit":
                player.throwItem("MedKit");
                exitThrow();
                showItems();
                break;
            case "RemoveTNT":
                player.throwItem("TNT");
                exitThrow();
                showItems();
                break;
            case "RemoveShield":
                player.throwItem("Shield");
                exitThrow();
                showItems();
                break;
            case "RemoveTorch":
                player.throwItem("Torch");
                exitThrow();
                showItems();
                break;
            case "RemoveWeapon":
                player.throwWeapon();
                ui.topLabelCol2.setText("Weapon: Punch");
                exitThrow();
                showItems();
                break;
            default:
        }
    }

    private void room(int nextRow, int nextColumn) {
        this.nextRow = nextRow;
        this.nextColumn = nextColumn;
        Coordinate coords = new Coordinate(nextRow, nextColumn);
        nextRoom = building.getRoom(coords.toString());

        if (!nextRoom.isDark()) {
            if (nextRoom == startRoom) {
                if (hasCo) {
                    game.nextPosition0 = "Extraction";
                    ui.mainTextArea.setText(ending.nextDialogue());
                    vm.showDialogScreen();
                }
                else proceed();
            } else if (nextRoom == coRoom) {
                checkRoom();
                hasCo = true;
                vm.showMessage("You found the Co", 2000, Color.GREEN);
                proceed();
            } else if (nextRoom.getAlien() != null) {
                vm.showFightScreen();
                fightAlien(nextRoom.getAlien());
            } else if (!nextRoom.getItems().isEmpty()) {
                if (!nextRoom.getIconPath().equals(ui.checkIconPath)) checkRoom();
                showItems();
            } else {
                if (!nextRoom.getIconPath().equals(ui.checkIconPath)) checkRoom();
                proceed();
            }
        } else {
            ui.mainTextArea.setText(nextRoom.getDescription() + "\nUnfortunately is too dark to see, use the Torch");
            ui.setUnenableButtons();
            ui.itemButton2.setEnabled(true);
        }
    }

    public void lightOn() {
        if (player.findItem("Torch") != null) {
            vm.showMessage("You don't have the Torch", 2000, Color.RED);
            map();
        }
        else {
            nextRoom.setDark(false);
            room(nextRow, nextColumn);
        }
    }

    public void fightAlien(Alien alien) {
        fight = new Fight(player, alien, ui, vm, this);
        setNextPositions("Attack", "Leave", "Defend", "Elude");
        game.nextPosition0 = "Room" + nextRow + "_" + nextColumn;
        fight.fight();
    }
}
