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
         * [D] - Difendi - riduci i danni subiti (aggiungere scudo trovabile?)
         * [R] - Scappa
         * [H] - Cura (se hai medikit)
         */

        while (player.getHealth() > 0 && alien.getHealth() > 0) {
            System.out.println("What do you want to do? [A]ttack, [D]efend, [R]un, [H]eal:");
            String command = System.console().readLine();
            switch (command.toUpperCase()) {
                case "A":
                    System.out.println("You attack the " + alien.getName() + "!");
                    int playerDamage = player.attack();
                    int alienDamage = alien.attack();

                    alien.takeDamage(playerDamage);
                    System.out.println("You deal " + playerDamage + " damage to the " + alien.getName() + "!");
                    player.takeDamage(alienDamage);
                    System.out.println("The " + alien.getName() + " deals " + alienDamage + " damage to you!");
                    break;
                case "D":
                    System.out.println("You defend against the " + alien.getName() + "!");
                    break;
                case "R":
                    System.out.println("You try to run away from the " + alien.getName() + "!");
                    break;
                case "H":
                    boolean hasMedikit = false;
                    for (Item item : player.getBag()){
                        if(item != null){
                            if (item.getName().equals("Medikit")) {
                                player.heal();
                                System.out.println("You heal yourself with a medikit!");
                                hasMedikit = true;
                                break;
                            }
                        }
                    }
                    if (!hasMedikit) {
                        System.out.println("You don't have any medikits!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

        }

        if (player.getHealth() <= 0) {
            System.out.println("You have been defeated by the " + alien.getName() + "!");

        } else {
            System.out.println("You have defeated the " + alien.getName() + "!");
        }
    }
}
