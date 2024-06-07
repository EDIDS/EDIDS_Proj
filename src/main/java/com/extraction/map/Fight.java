package com.extraction.map;

import com.extraction.Graphic.VisibilityManager;
import com.extraction.aliens.Alien;
import com.extraction.player.Player;
import com.extraction.Graphic.UI;
import com.extraction.Graphic.Story;

import javax.swing.*;

/**
 * The Fight class represents a fight between the player and an alien in the game.
 * It contains methods for the player's turn, the alien's turn, and various actions the player can take during their turn.
 */
public class Fight {
    Player player;
    Alien alien;
    UI ui;
    Story story;
    VisibilityManager vm;
    final Object lock = new Object();
    final int TIMER = 2000;

    volatile Boolean selected = false;

    /**
     * Constructs a new Fight with the given player, alien, UI, VisibilityManager, and Story.
     * @param player The player involved in the fight.
     * @param alien The alien involved in the fight.
     * @param ui The UI for the game.
     * @param vm The VisibilityManager for the game.
     * @param story The Story for the game.
     */
    public Fight(Player player, Alien alien, UI ui, VisibilityManager vm, Story story) {
        this.player = player;
        this.alien = alien;
        this.ui = ui;
        this.vm = vm;
        this.story = story;
    }

    /**
     * Starts a fight between the player and the alien.
     */
    public void fight() {
        ui.mainTextArea.setText("You have encountered a " + alien.getName() + "!");

        ui.setActionBText("Attack", "Leave", "Shield", "Elude");

        playerTurn();
    }

    /**
     * Handles the player's turn in the fight.
     */
    public void playerTurn() {
        ui.setUnenableButtons();
        Timer timer = new Timer(TIMER, e -> {
            if (player.getHealth() <= 0) {
                ui.mainTextArea.setText("You have been defeated by the " + alien.getName() + "!");
                vm.showEndScreen("GAME OVER");
            }
            ui.setMyTurnButton();
            ui.mainTextArea.setText(displayStatus() + "\n\nYour turn! \nMake a choice");
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Handles the alien's turn in the fight.
     */
    public void alienTurn() {
        ui.setUnenableButtons();
        Timer timer = new Timer(TIMER, e -> {
            if (alien.getHealth() <= 0) {
                ui.mainTextArea.setText("You have defeated the " + alien.getName() + "!");
                vm.showDialogScreen();
                ui.setEnableButtons();
                story.checkRoom();
            } else {
                ui.setAlienTurnButton();
                ui.mainTextArea.setText(displayStatus() + "\n\nThe alien is attacking you!\nWhat do you want to do?");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Handles the player's attack action during their turn.
     */
    public void attack() {
        int playerDamage = player.attack();
        alien.takeDamage(playerDamage);
        ui.mainTextArea.setText("You attack the " + alien.getName() + "!" +
                "\n\nYou deal " + playerDamage + " damage to the " + alien.getName() + "!");
        alienTurn();
    }

    /**
     * Handles the player's run action during their turn.
     */
    public void run() {
        ui.mainTextArea.setText("You try to run away from the " + alien.getName() + "!");
        double escapeChance = alien.getEscapeChance();
        if (Math.random() < escapeChance) {
            ui.mainTextArea.setText("You successfully escaped from the " + alien.getName() + "!");
            vm.showDialogScreen();
            ui.setEnableButtons();
            story.checkRoom();
        } else {
            ui.mainTextArea.setText("You failed to escape from the " + alien.getName() + "!");
            alienTurn();
        }
    }

    /**
     * Handles the player's heal action during their turn.
     */
    public void heal() {
        ui.mainTextArea.setText("You try to heal yourself!");
        if (player.heal()) {
            ui.setCol1(player.getHealth() + "");
            ui.mainTextArea.setText("You successfully heal yourself!");
            alienTurn();
        } else
            playerTurn();
    }

    /**
     * Handles the player's TNT action during their turn.
     */
    public void tnt() {
        int tntDamage = player.detonateTNT();
        if(tntDamage == 0){
            playerTurn();
        } else {
            ui.mainTextArea.setText("You throw TNT at " + alien.getName() + "!");
            alien.takeDamage(tntDamage);
            ui.mainTextArea.setText("You deal " + tntDamage + " damage to the " + alien.getName() + "!");
            alienTurn();
        }
    }

    /**
     * Handles the player's defend action during their turn.
     */
    public void defend() {
        ui.mainTextArea.setText("You defend against the " + alien.getName() + "!");
        int damage = alien.attack();
        int reducedDamage = player.defend(damage);
        player.takeDamage(reducedDamage);
        ui.setCol1(player.getHealth() + "");
        if(!player.hasShield()) {
            ui.mainTextArea.setText("You don't have a shield!");
            Timer timer = new Timer(TIMER, e -> {
            });
            timer.setRepeats(false);
            timer.start();
        }
        ui.mainTextArea.setText("The " + alien.getName() + " deals " + reducedDamage + " damage to you!");
        playerTurn();
    }

    /**
     * Handles the player's elude action during their turn.
     */
    public void elude() {
        ui.mainTextArea.setText("You try to elude the " + alien.getName() + "'s attack!");
        double eludeChance = alien.getEludeChance();
        if (Math.random() < eludeChance) {
            ui.mainTextArea.setText("You successfully eluded the " + alien.getName() + "'s attack!");
        } else {
            ui.mainTextArea.setText("You failed to elude the " + alien.getName() + "'s attack!");
            int harm = alien.attack();
            player.takeDamage(harm);
            ui.setCol1(player.getHealth() + "");
            ui.mainTextArea.setText("The " + alien.getName() + " deals " + harm + " damage to you!");
        }
        playerTurn();
    }

    /**
     * Displays the current status of the fight.
     * @return A string representing the current status of the fight.
     */
    public String displayStatus() {
        return alien.getName() + "'s Health: " + alien.getHealth();
    }
}
