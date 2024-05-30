package com.extraction.map;

import com.extraction.aliens.Alien;
import com.extraction.items.Item;
import com.extraction.player.Player;

public class Fight {
    Player player;
    Alien alien;

    public Fight(Player player, Alien alien) {
        this.player = player;
        this.alien = alien;
    }

    public void fight() {
        System.out.println("You have encountered a " + alien.getName() + "!");

        /**
         * [A] - Attacca
         * [R] - Scappa
         * [H] - Cura (se hai medikit)
         * [T] - Attacca con TNT
         * [D] - Difendi (scudo)
         * [E] - Schiva
         */

        while (player.getHealth() > 0 && alien.getHealth() > 0) {
            System.out.println("What do you want to do? [A]ttack, [R]un, [H]eal, [T]NT:");
            String command = System.console().readLine();
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
            }
            if (alien.getHealth() <= 0) {
                System.out.println("You have defeated the " + alien.getName() + "!");
                return;
            }
            System.out.println("The alien is attacking you!\nWhat do you want to do? [D]efend, [E]lude:");
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
                        return;
                    } else {
                        System.out.println("You failed to elude the " + alien.getName() + "'s attack!");
                        int harm = alien.attack();
                        player.takeDamage(harm);
                        System.out.println("The " + alien.getName() + " deals " + harm + " damage to you!");
                    }
                    break;
            }
            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the " + alien.getName() + "!");
                return;
            }
        }
    }

    public void displayStatus() {
        System.out.println("Player Health: " + player.getHealth());
        System.out.println("Alien Health: " + alien.getHealth());
    }
}
