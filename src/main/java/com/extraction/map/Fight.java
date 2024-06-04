package com.extraction.map;

import com.extraction.Graphic.VisibilityManager;
import com.extraction.aliens.Alien;
import com.extraction.items.Item;
import com.extraction.player.Player;
import com.extraction.Graphic.UI;

public class Fight {
    Player player;
    Alien alien;
    UI ui;
    VisibilityManager vm;

    volatile Boolean selected = false;

    public Fight(Player player, Alien alien, UI ui, VisibilityManager vm) {
        this.player = player;
        this.alien = alien;
        this.ui = ui;
        this.vm = vm;
    }

    public void fight() {
        ui.mainTextArea.setText("You have encountered a " + alien.getName() + "!");
        //System.out.println("You have encountered a " + alien.getName() + "!");

        ui.actionButton1.setText("Attack");
        ui.actionButton2.setText("Leave");
        ui.actionButton3.setText("Shield");
        ui.actionButton4.setText("Elude");

        /**
         * [A] - Attacca
         * [R] - Scappa
         * [H] - Cura (se hai medikit)
         * [T] - Attacca con TNT
         * [D] - Difendi (scudo)
         * [E] - Schiva
         */

        /*while (player.getHealth() > 0 && alien.getHealth() > 0) {
            String command;
            boolean ok;
            do {

                System.out.println("What do you want to do? [A]ttack, [R]un, [H]eal, [T]NT:");
                ok = true;

                command = System.console().readLine();
                switch (command.toUpperCase()) {
                    case "A":
                        System.out.println("You attack the " + alien.getName() + "!");
                        int playerDamage = player.attack();
                        alien.takeDamage(playerDamage);
                        System.out.println("You deal " + playerDamage + " damage to the " + alien.getName() + "!");
                        break;
                    case "R":
                      System.out.println("You try to run away from the " + alien.getName() + "!");
                        double escapeChance = alien.getEscapeChance();
                        if (Math.random() < escapeChance) {
                            System.out.println("You successfully escaped from the " + alien.getName() + "!");
                            return;
                        } else {
                        System.out.println("You failed to escape from the " + alien.getName() + "!");
                       }
                        break;
                    case "H":
                        System.out.println("You try to heal yourself!");
                      player.heal();
                        break;
                    case "T":
                        System.out.println("You throw TNT at " + alien.getName() + "!");
                        int tntDamage = player.detonateTNT();
                        alien.takeDamage(tntDamage);
                       System.out.println("You deal " + tntDamage + " damage to the " + alien.getName() + "!");
                        break;
                    default:
                        System.out.println("Invalid command!");
                        ok = false;
                        break;
                }
            } while (!ok);
            this.displayStatus();
            if (alien.getHealth() <= 0) {
                System.out.println("You have defeated the " + alien.getName() + "!");
                return;
            }
            do {
                System.out.println("The alien is attacking you!\nWhat do you want to do? [D]efend, [E]lude:");
                ok = true;
                command = System.console().readLine();
                switch (command.toUpperCase()) {
                    case "D":
                        System.out.println("You defend against the " + alien.getName() + "!");
                        int damage = alien.attack();
                        int reducedDamage = player.defend(damage);
                        player.takeDamage(reducedDamage);
                        System.out.println("The " + alien.getName() + " deals " + reducedDamage + " damage to you!");
                        break;
                    case "E":
                        System.out.println("You try to elude the " + alien.getName() + "'s attack!");
                        double eludeChance = alien.getEludeChance();
                        if (Math.random() < eludeChance) {
                            System.out.println("You successfully eluded the " + alien.getName() + "'s attack!");
                        } else {
                            System.out.println("You failed to elude the " + alien.getName() + "'s attack!");
                            int harm = alien.attack();
                            player.takeDamage(harm);
                            System.out.println("The " + alien.getName() + " deals " + harm + " damage to you!");
                        }
                        break;
                    default:
                        System.out.println("Invalid command!");
                        ok = false;
                        break;
                }
            } while (!ok);
            this.displayStatus();
            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the " + alien.getName() + "!");
                return;
            }
        }*/

        //while (player.getHealth() > 0 && alien.getHealth() > 0) {
            ui.mainTextArea.setText(displayStatus() + "\n\nYour turn! \nMake a choice");
            ui.setMyTurnButton();
            while (selected) {
                //Thread.onSpinWait();
            }
            selected = false;
            if (alien.getHealth() <= 0) {
                ui.mainTextArea.setText("You have defeated the " + alien.getName() + "!");

            }
            ui.mainTextArea.setText(displayStatus() + "\n\nThe alien is attacking you!\nWhat do you want to do?");
            ui.setAlienTurnButton();
            while (selected) {
                //Thread.onSpinWait();
            }
            selected = false;
            if (player.getHealth() <= 0) {
                ui.mainTextArea.setText("You have been defeated by the " + alien.getName() + "!");
            }
        //}
    }

    public void attack() {
        ui.mainTextArea.setText("You attack the " + alien.getName() + "!");
        int playerDamage = player.attack();
        alien.takeDamage(playerDamage);
        ui.mainTextArea.setText("You deal " + playerDamage + " damage to the " + alien.getName() + "!");
        selected = true;
    }

    public void run() {
        ui.mainTextArea.setText("You try to run away from the " + alien.getName() + "!");
        double escapeChance = alien.getEscapeChance();
        if (Math.random() < escapeChance) {
            ui.mainTextArea.setText("You successfully escaped from the " + alien.getName() + "!");
        } else {
            ui.mainTextArea.setText("You failed to escape from the " + alien.getName() + "!");
        }
        selected = true;
    }

    public void heal() {
        ui.mainTextArea.setText("You try to heal yourself!");
        player.heal();
        selected = true;
    }

    public void tnt() {
        ui.mainTextArea.setText("You throw TNT at " + alien.getName() + "!");
        int tntDamage = player.detonateTNT();
        alien.takeDamage(tntDamage);
        ui.mainTextArea.setText("You deal " + tntDamage + " damage to the " + alien.getName() + "!");
        selected = true;
    }

    public void defend() {
        ui.mainTextArea.setText("You defend against the " + alien.getName() + "!");
        int damage = alien.attack();
        int reducedDamage = player.defend(damage);
        player.takeDamage(reducedDamage);
        ui.mainTextArea.setText("The " + alien.getName() + " deals " + reducedDamage + " damage to you!");
        selected = true;
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
            ui.mainTextArea.setText("The " + alien.getName() + " deals " + harm + " damage to you!");
        }
        selected = true;
    }

    public String displayStatus() {
        return "Your Health: " + player.getHealth() + "\n" + alien.getName() + "'s Health: " + alien.getHealth();
    }
}
