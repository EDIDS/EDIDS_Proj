package com.extraction.map;

import com.extraction.Graphic.VisibilityManager;
import com.extraction.aliens.Alien;
import com.extraction.player.Player;
import com.extraction.Graphic.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fight {
    Player player;
    Alien alien;
    UI ui;
    VisibilityManager vm;
    final Object lock = new Object();
    final int TIMER = 3500;

    volatile Boolean selected = false;

    public Fight(Player player, Alien alien, UI ui, VisibilityManager vm) {
        this.player = player;
        this.alien = alien;
        this.ui = ui;
        this.vm = vm;
    }

    public void fight() {
        ui.mainTextArea.setText("You have encountered a " + alien.getName() + "!");

        ui.actionButton1.setText("Attack");
        ui.actionButton2.setText("Leave");
        ui.actionButton3.setText("Shield");
        ui.actionButton4.setText("Elude");

        playerTurn();
    }

    public void playerTurn() {
        ui.setUnenableButtons();
        Timer timer = new Timer(TIMER, e -> {
            if (player.getHealth() <= 0) {
                ui.mainTextArea.setText("You have been defeated by the " + alien.getName() + "!");
                vm.showGameOverScreen();
                ui.titleLabel.setText("GAME OVER");
            }
            ui.setMyTurnButton();
            ui.mainTextArea.setText(displayStatus() + "\n\nYour turn! \nMake a choice");
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void alienTurn() {
        ui.setUnenableButtons();
        Timer timer = new Timer(TIMER, e -> {
            if (alien.getHealth() <= 0) {
                ui.mainTextArea.setText("You have defeated the " + alien.getName() + "!");
                vm.showTextScreen();
                ui.setEnableButtons();
            } else {
                ui.setAlienTurnButton();
                ui.mainTextArea.setText(displayStatus() + "\n\nThe alien is attacking you!\nWhat do you want to do?");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void attack() {
        int playerDamage = player.attack();
        alien.takeDamage(playerDamage);
        ui.mainTextArea.setText("You attack the " + alien.getName() + "!" +
                "\n\nYou deal " + playerDamage + " damage to the " + alien.getName() + "!");
        alienTurn();
    }


    public void run() {
        ui.mainTextArea.setText("You try to run away from the " + alien.getName() + "!");
        double escapeChance = alien.getEscapeChance();
        if (Math.random() < escapeChance) {
            ui.mainTextArea.setText("You successfully escaped from the " + alien.getName() + "!");
            vm.showTextScreen();
            ui.setEnableButtons();
        } else {
            ui.mainTextArea.setText("You failed to escape from the " + alien.getName() + "!");
            alienTurn();
        }
    }

    public void heal() {
        ui.mainTextArea.setText("You try to heal yourself!");
        player.heal();
        alienTurn();
    }

    public void tnt() {
        ui.mainTextArea.setText("You throw TNT at " + alien.getName() + "!");
        int tntDamage = player.detonateTNT();
        alien.takeDamage(tntDamage);
        ui.mainTextArea.setText("You deal " + tntDamage + " damage to the " + alien.getName() + "!");
        alienTurn();
    }

    public void defend() {
        ui.mainTextArea.setText("You defend against the " + alien.getName() + "!");
        int damage = alien.attack();
        int reducedDamage = player.defend(damage);
        player.takeDamage(reducedDamage);
        ui.topLabelCol1.setText("HP: " + player.getHealth());
        ui.mainTextArea.setText("The " + alien.getName() + " deals " + reducedDamage + " damage to you!");
        playerTurn();
    }

    public void elude() {
        ui.mainTextArea.setText("You try to elude the " + alien.getName() + "'s attack!");
        double eludeChance = alien.getEludeChance();
        if (Math.random() < eludeChance) {
            ui.mainTextArea.setText("You successfully eluded the " + alien.getName() + "'s attack!");
        } else {
            ui.mainTextArea.setText("You failed to elude the " + alien.getName() + "'s attack!");
            int harm = alien.attack();
            player.takeDamage(harm);
            ui.topLabelCol1.setText("HP: " + player.getHealth());
            ui.mainTextArea.setText("The " + alien.getName() + " deals " + harm + " damage to you!");
        }
        playerTurn();
    }

    public String displayStatus() {
        return alien.getName() + "'s Health: " + alien.getHealth();
    }
}
