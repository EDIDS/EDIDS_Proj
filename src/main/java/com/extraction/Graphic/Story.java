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
import java.util.ArrayList;
import java.util.List;

/**
 * The Story class represents the narrative of the game.
 * It manages the game state, player actions, and game progression.
 */
public class Story {
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player;
    IntroductionDialogue introduction;
    EndingDialogue ending;

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

    /**
     * Constructs a new Story with the given parameters.
     * @param game The game instance.
     * @param ui The UI instance.
     * @param vm The VisibilityManager instance.
     * @param building The Building instance.
     * @param player The Player instance.
     */
    public Story(Game game, UI ui, VisibilityManager vm, Building building, Player player) {
        this.game = game;
        this.ui = ui;
        this.vm = vm;
        this.building = building;
        this.player = player;
    }

    /**
     * Sets up the default game state.
     */
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

        introduction = new IntroductionDialogue();
        ui.mainTextArea.setText(introduction.nextDialogue());

        ending = new EndingDialogue();

        ui.setEnableButtons();

        game.nextPosition0 = "Introduction";
    }

    /**
     * Sets the next positions for the player based on the available directions.
     * @param next1 The next position in the north direction.
     * @param next2 The next position in the east direction.
     * @param next3 The next position in the south direction.
     * @param next4 The next position in the west direction.
     */
    private void setNextPositions(String next1, String next2, String next3, String next4) {
        game.nextPosition1 = next1;
        game.nextPosition2 = next2;
        game.nextPosition3 = next3;
        game.nextPosition4 = next4;
    }

    /**
     * Selects the next position based on the given position string.
     * @param nextPosition The position string.
     */
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
            case "Map":
                map();
                break;
            case "Proceed":
                proceed();
                break;
            case "Fight":
                vm.showFightScreen();
                fightAlien(nextRoom.getAlien());
                break;
            case "ShowItems":
                showItems();
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

    /**
     * Displays the game map.
     */
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

    /**
     * Updates the player's position on the map.
     */
    private void updatePlayerPos() {
        playerX = player.getCurrentRoom_().getCoordinate().getColumn();
        playerY = player.getCurrentRoom_().getCoordinate().getRow();
    }

    /**
     * Proceeds to the next room.
     */
    private void proceed() {
        ui.setIcon(playerY, playerX, player.getCurrentRoom_().getIconPath());
        ui.setIcon(nextRow, nextColumn, ui.playerIconPath);

        player.setCurrentRoom(nextRoom);

        updatePlayerPos();

        map();
    }

    /**
     * Checks the current room.
     */
    public void checkRoom() {
        Room room = building.getRoom(new Coordinate(nextRow, nextColumn).toString());
        room.setIconPath(ui.checkIconPath);
        room.setAlien(null);
    }

    /**
     * Shows the items in the current room.
     */
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

    /**
     * Exits the item selection screen.
     */
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

        ui.setEnableButtons();

        proceed();
    }

    /**
     * Adds an ActionListener to a button for a specific item.
     * @param b The button to add the ActionListener to.
     * @param item The item associated with the ActionListener.
     * @return The ActionListener that was added to the button.
     */
    private ActionListener addListener(JButton b, String item) {
        return e -> {
            b.setEnabled(false);
            execute(item);
        };
    }

    /**
     * Throws the items in the current room.
     */
    public void throwItems() {
        ui.setEnableButtons();
        vm.showTextScreen();
        exitThrow();
        ui.exitItemButton.setEnabled(false);
        ui.throwButton.setEnabled(false);
        ui.actionButton1.setText("");
        ui.actionButton2.setText("");
        ui.actionButton3.setText("");
        ui.actionButton4.setText("");
        List<Item> items = new ArrayList<>(player.getBag());
        items.removeIf(item -> item.getName().equals("Torch") || item.getName().equals("Key"));
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

    /**
     * Exits the item throwing screen.
     */
    private void exitThrow() {
        ui.actionButton1.removeActionListener(actionListener1);
        ui.actionButton2.removeActionListener(actionListener2);
        ui.actionButton3.removeActionListener(actionListener3);
        ui.actionButton4.removeActionListener(actionListener4);
    }

    /**
     * Gets a string representation of the items in a list.
     * @param items The list of items.
     * @param str The StringBuilder to append the string representation to.
     * @return The StringBuilder with the appended string representation.
     */
    public StringBuilder getItems(List<Item> items, StringBuilder str) {
        for (Item item : items) {
            if (item instanceof Weapon) str.append("- ").append(((Weapon) item).getType()).append("\n");
            else str.append("- ").append(item.getName()).append("\n");
        }
        return str;
    }

    /**
     * Executes an action based on the given item string.
     * @param item The item string.
     */
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
                if (nextRoom.getItems().isEmpty()) {
                    exitItems();
                } else {
                    exitThrow();
                    showItems();
                }
                break;
            case "RemoveTNT":
                player.throwItem("TNT");
                if (nextRoom.getItems().isEmpty()) {
                    exitItems();
                } else {
                    exitThrow();
                    showItems();
                }
                break;
            case "RemoveShield":
                player.throwItem("Shield");
                if (nextRoom.getItems().isEmpty()) {
                    exitItems();
                } else {
                    exitThrow();
                    showItems();
                }
                break;
            case "RemoveTorch":
                player.throwItem("Torch");
                if (nextRoom.getItems().isEmpty()) {
                    exitItems();
                } else {
                    exitThrow();
                    showItems();
                }
                break;
            case "RemoveWeapon":
                player.throwItem("Weapon");
                ui.topLabelCol2.setText("Weapon: Punch");
                if (nextRoom.getItems().isEmpty()) {
                    exitItems();
                } else {
                    exitThrow();
                    showItems();
                }
                break;
            default:
        }
    }

    /**
     * Moves to the room at the given coordinates.
     * @param nextRow The row of the room.
     * @param nextColumn The column of the room.
     */
    private void room(int nextRow, int nextColumn) {
        this.nextRow = nextRow;
        this.nextColumn = nextColumn;
        Coordinate coords = new Coordinate(nextRow, nextColumn);
        nextRoom = building.getRoom(coords.toString());

        if (!nextRoom.isDark()) {
            if (nextRoom == startRoom) {
                if (hasCo) {
                    showDialog(ending.nextDialogue(), "Continue", "Extraction");
                }
                else {
                    showDialog("Location: " + nextRoom.getDescription() + "\nNOME COMPAGNEROS needs your help!",
                            "Back", "Proceed");
                }
            } else if (nextRoom == coRoom) {
                if (player.getKeys() == 2) {
                    checkRoom();
                    hasCo = true;
                    showDialog("Location: " + nextRoom.getDescription() + "\nYou found NOME COMPAGNEROS, it's time to go back home!",
                            "Back", "Proceed");
                    vm.showMessage("You found the Co.", 2000, Color.ORANGE);
                } else if (player.getKeys() == 1) {
                    vm.showMessage("You have just one Key, needed another one.", 2500, Color.ORANGE);
                } else {
                    vm.showMessage("You don't have the two Keys.", 2000, Color.ORANGE);
                }

            } else if (nextRoom.getAlien() != null) {
                showDialog("Location: " + nextRoom.getDescription() + "\nThere is something strange.\nWhat is it!",
                        "Go Closer", "Fight");
            } else if (!nextRoom.getItems().isEmpty()) {
                if (!nextRoom.getIconPath().equals(ui.checkIconPath)) checkRoom();
                showDialog("Location: " + nextRoom.getDescription() + "\nOh there are some objects in the Room.",
                        "Watch", "ShowItems");
            } else {
                if (!nextRoom.getIconPath().equals(ui.checkIconPath)) checkRoom();
                showDialog("Location: " + nextRoom.getDescription() + "\nNothing interesting here.",
                        "Go Ahead", "Proceed");
            }
        } else {
            showDialog("Location: " + nextRoom.getDescription() + "\nUnfortunately is too dark to see, use the Torch.",
                    "Use Torch", "Torch");
        }
    }

    /**
     * Displays a dialog with the given text, button text, and action command.
     * @param text The dialog text.
     * @param buttonText The button text.
     * @param actionCommand The action command.
     */
    private void showDialog(String text, String buttonText, String actionCommand) {
        ui.mainTextArea.setText(text);
        ui.dialogButton.setText(buttonText);
        game.nextPosition0 = actionCommand;
        vm.showDialogScreen();
    }

    /**
     * Turns on the light in the current room.
     */
    public void lightOn() {
        if (player.findItem("Torch") == null) {
            vm.showMessage("You don't have the Torch", 2000, Color.RED);
            map();
        }
        else {
            nextRoom.setDark(false);
            room(nextRow, nextColumn);
        }
    }

    /**
     * Fights the alien in the current room.
     * @param alien The alien to fight.
     */
    public void fightAlien(Alien alien) {
        fight = new Fight(player, alien, ui, vm, this);
        setNextPositions("Attack", "Leave", "Defend", "Elude");
        ui.dialogButton.setText("Continue");
        game.nextPosition0 = "Room" + nextRow + "_" + nextColumn;
        fight.fight();
    }
}
