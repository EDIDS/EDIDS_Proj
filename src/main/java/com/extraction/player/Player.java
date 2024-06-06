package com.extraction.player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.extraction.Graphic.UI;
import com.extraction.Graphic.VisibilityManager;
import com.extraction.items.*;
import com.extraction.map.Room;

/**
 * The Player class represents a player in the game.
 * It contains the player's health, name, bag of items, current weight, current room, weapon, shield, score, number of keys, UI, and VisibilityManager.
 */
public class Player {

    public static final int FULL_HEALTH = 100;
    public static final int BASE_ATTACK_DAMAGE = 100;
    public static final String NO_NAME = "";
    public static final int MAX_WEIGHT = 100;

    private int health_;
    private String name_;
    private List<Item> bag_;
    private double currentWeight_ = 0;
    private Room currentRoom_;
    private Weapon weapon;
    private Shield shield;
    private int score_;
    private int numKeys;
    private final UI ui_;
    private final VisibilityManager vm_;


    /**
     * Constructor for Player class with UI and VisibilityManager parameters.
     * @param ui User Interface for the game.
     * @param vm Visibility Manager for the game.
     */
    public Player(UI ui, VisibilityManager vm) {
        name_ = NO_NAME;
        health_ = FULL_HEALTH;
        bag_ = new ArrayList<Item>();
        ui_ = ui;
        vm_ = vm;
    }

    /**
     * Constructor for Player class with UI and VisibilityManager parameters.
     * @param ui User Interface for the game.
     * @param vm Visibility Manager for the game.
     */
    public Player(String name, UI ui, VisibilityManager vm) {
        name_ = name;
        health_ = FULL_HEALTH;
        bag_ = new ArrayList<Item>();
        ui_ = ui;
        vm_ = vm;
    }

    /**
     * Gets the player's health.
     * @return The player's health.
     */
    public int getHealth() { return health_; }

    /**
     * Sets the player's health.
     * @param health The new health for the player.
     */
    public void setHealth(int health) {
        health_ = health;
    }

    /**
     * Gets the player's name.
     * @return The player's name.
     */
    public String getName() { return name_; }

    /**
     * Sets the player's name.
     * @param name_ The new name for the player.
     */
    public void setName(String name_)
    {
        this.name_ = name_;
    }

    /**
     * Gets the player's bag of items.
     * @return The player's bag of items.
     */
    public List<Item> getBag() {
        return bag_;
    }

    /**
     * Sets the player's bag of items.
     * @param bag The new bag of items for the player.
     */
    public void setBag(List<Item> bag) {
        this.bag_ = bag;
    }

    /**
     * Gets the player's current weight.
     * @return The player's current weight.
     */
    public double getCurrentWeight_() { return currentWeight_; }

    /**
     * Sets the player's current weight.
     * @param currentWeight The new current weight for the player.
     */
    public void setCurrentWeight_(double currentWeight) {
        currentWeight_ = currentWeight;
    }

    /**
     * Gets the player's current room.
     * @return The player's current room.
     */
    public Room getCurrentRoom_() {
        return currentRoom_;
    }

    /**
     * Sets the player's current room.
     * @param currentRoom The new current room for the player.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom_ = currentRoom;
    }

    /**
     * Gets the player's score.
     * @return The player's score.
     */
    public int getScore() {
        return score_;
    }

    /**
     * Sets the player's score.
     * @param score The new score for the player.
     */
    public void setScore(int score) {
        score_ = score;
    }

    /**
     * Gets the player's weapon.
     * @return The player's weapon.
     */
    public Weapon getWeapon() { return weapon; }

    /**
     * Gets the player's shield.
     * @return The player's shield.
     */
    public Shield getShield() { return shield; }

    /**
     * Gets the player's number of keys.
     * @return The player's number of keys.
     */
    public int getKeys() { return numKeys; }

    /**
     * Sets the player's weapon.
     * @param newWeapon The new weapon for the player.
     */
    public void setWeapon(Weapon newWeapon) {
        double newWeight = currentWeight_ + newWeapon.getWeight();
        if (this.hasWeapon()) {
            Weapon oldWeapon = getWeapon();
            newWeight -= oldWeapon.getWeight();
        }
        if(newWeight <= MAX_WEIGHT) {
            this.weapon = newWeapon;
            this.currentWeight_ = newWeight;
        } else {
            System.out.println("You have reached the maximum weight of " + MAX_WEIGHT);
        }
    }

    /**
     * Sets the player's shield.
     * @param newShield The new shield for the player.
     */
    public void setShield(Shield newShield) {
        double newWeight = currentWeight_ + newShield.getWeight();
        if (!this.hasShield()) {
            if (newWeight <= MAX_WEIGHT) {
                this.shield = newShield;
                this.currentWeight_ = newWeight;
            } else {
                System.out.println("You have reached the maximum weight of " + MAX_WEIGHT);
            }
        } else {
            System.out.println("You already have a shield");
        }
    }

    /**
     * Removes the player's weapon.
     * This method will set the player's weapon to null.
     */
    public void throwWeapon() {
        this.weapon = null;
    }

    /**
     * Removes the player's shield.
     * This method will set the player's shield to null and return the removed shield.
     */
    public void throwShield() {
        this.shield = null;
    }

    /**
     * Checks if the player has a weapon.
     * @return true if the player has a weapon, false otherwise.
     */
    public boolean hasWeapon() {
        return weapon != null;
    }

    /**
     * Checks if the player has a shield.
     * @return true if the player has a shield, false otherwise.
     */
    public boolean hasShield() {
        return shield != null;
    }


    /**
     * Calculates the attack damage of the player.
     * @return the attack damage.
     */
    public int attack() {
        if (this.hasWeapon()) return weapon.calculateDamage();

        return BASE_ATTACK_DAMAGE;
    }


    /**
     * Calculates the damage after defense.
     * @param damage the initial damage.
     * @return the damage after defense.
     */
    public int defend(int damage) {
        if (this.hasShield()) return damage - shield.getDefense();

        return damage;
    }

    /**
     * Heals the player to full health if a MedKit is found in the bag.
     * @return true if the player was healed, false otherwise.
     */
    public boolean heal() {
        MedKit medKit = (MedKit) this.findItem("MedKit");
        if (medKit != null) {
            setHealth(FULL_HEALTH);
            ui_.topLabelCol1.setText("HP: " + this.getHealth());
            throwItem(medKit);
            return true;
        }
        vm_.showMessage("No MedKit Found", 2000, Color.ORANGE);
        return false;
    }

    /**
     * Reduces the player's health by the given damage.
     * @param damage the damage to be taken.
     */
    public void takeDamage(int damage) {
        if (damage < 0) return;
        health_ = health_ - damage;
    }

    /**
     * Adds an item to the player's bag if the total weight is under the maximum weight.
     * @param item the item to be added.
     */
    public void addItem(Item item){
        double newWeight = currentWeight_ + item.getWeight();
        if (newWeight <= MAX_WEIGHT) {
            if(item instanceof Weapon) {
                if(weapon != null) {
                    Weapon oldWeapon = weapon;
                    this.throwItem(oldWeapon);
                    vm_.showMessage("You dropped your current weapon", 2000, Color.ORANGE);
                }
                this.setWeapon((Weapon) item);
            }
            if(item instanceof Key) this.numKeys++;
            bag_.add(item);
            currentWeight_ = newWeight;
        } else {
            vm_.showMessage("You have reached the maximum weight of " + MAX_WEIGHT, 2000, Color.ORANGE);
        }
    }

    /**
     * Removes an item from the player's bag.
     * @param itemToThrow the item to be removed.
     * @return the removed item if it was found and is throwable, null otherwise.
     */
    public Item throwItem(Item itemToThrow) {
        Item item = this.findItem(itemToThrow);
            if (item != null && item.isThrowable()) {
                if(itemToThrow instanceof Weapon) this.throwWeapon();
                if(itemToThrow instanceof Key) this.numKeys--;
                bag_.remove(item);
                currentWeight_ -= item.getWeight();
                if(itemToThrow instanceof Weapon) throwWeapon();
                return item;
            }
        return null;
    }

    /**
     * Removes an item from the player's bag.
     * @param itemToThrow the name of the item to be removed.
     * @return the removed item if it was found and is throwable, null otherwise.
     */
    public Item throwItem(String itemToThrow) {
        Item item = this.findItem(itemToThrow);
        if (item != null && item.isThrowable()) {
            if(item instanceof Weapon) this.throwWeapon();
            if(item instanceof Key) this.numKeys--;
            bag_.remove(item);
            currentWeight_ -= item.getWeight();
            return item;
        }
        return null;
    }

    /**
     * Finds an item in the player's bag.
     * @param itemToFind the item to be found.
     * @return the found item if it was in the bag, null otherwise.
     */
    public Item findItem(Item itemToFind) {
        for (Item item : bag_) {
            if (item == itemToFind)
                return item;
        }
        return null;
    }

    /**
     * Finds an item in the player's bag.
     * @param itemToFind the item to be found.
     * @return the found item if it was in the bag, null otherwise.
     */
    public Item findItem(String itemToFind) {
        for (Item item : bag_) {
            if (item.getName().equals(itemToFind))
                return item;
        }
        return null;
    }

    /**
     * Detonates a TNT from the player's bag.
     * @return the damage of the explosion if a TNT was found, 0 otherwise.
     */
    public int detonateTNT() {
        TNT tnt = (TNT) this.findItem("TNT");
        if (tnt != null) {
            int explosion = tnt.getDamage();
            throwItem(tnt);
            return explosion;
        }
        vm_.showMessage("No TNT Found", 2000, Color.ORANGE);
        return 0;
    }

    /**
     * Gets the player's data.
     * @return a string containing the player's name and health.
     */
    public String getData() {
        return name_ + " " + health_;
    }

    /**
     * Resets the player's health to full health.
     */
    public void reset() {
        health_ = FULL_HEALTH;
    }
}
