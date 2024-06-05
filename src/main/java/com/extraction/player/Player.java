package com.extraction.player;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import com.extraction.Graphic.UI;
import com.extraction.Graphic.VisibilityManager;
import com.extraction.items.*;
import com.extraction.map.Room;

public class Player {

    public static Random RANDOM = new Random();

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
    private UI ui_;
    private VisibilityManager vm_;

    public Player(UI ui, VisibilityManager vm) {
        name_ = NO_NAME;
        health_ = FULL_HEALTH;
        bag_ = new ArrayList<Item>();
        ui_ = ui;
        vm_ = vm;
    }

    public Player(String name, UI ui, VisibilityManager vm) {
        name_ = name;
        health_ = FULL_HEALTH;
        bag_ = new ArrayList<Item>();
        ui_ = ui;
        vm_ = vm;
    }

    /**
     * Getter e setter (per json)
     */

    public int getHealth() { return health_; }

    public void setHealth(int health) {
        health_ = health;
    }

    public String getName() { return name_; }

    public void setName(String name_)
    {
        this.name_ = name_;
    }

    public List<Item> getBag() {
        return bag_;
    }

    public void setBag(List<Item> bag) {
        this.bag_ = bag;
    }

    public double getCurrentWeight_() { return currentWeight_; }

    public void setCurrentWeight_(double currentWeight) {
        currentWeight_ = currentWeight;
    }

    public Room getCurrentRoom_() {
        return currentRoom_;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom_ = currentRoom;
    }

    public int getScore() {
        return score_;
    }

    public void setScore(int score) {
        score_ = score;
    }

    public Weapon getWeapon() { return weapon; }

    public Shield getShield() { return shield; }

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

    public Weapon throwWeapon() {
        Weapon weapon_ = getWeapon();
        this.currentWeight_ -= weapon.getWeight();
        this.weapon = null;
        return weapon_;
    }

    public Shield throwShield() {
        Shield shield_ = getShield();
        this.currentWeight_ -= shield.getWeight();
        this.shield = null;
        return shield_;
    }

    public boolean hasWeapon() {
        return weapon != null;
    }

    public boolean hasShield() {
        return shield != null;
    }

    public int attack() {
        if (this.hasWeapon()) return weapon.calculateDamage();

        return BASE_ATTACK_DAMAGE;
    }

    public int defend(int damage) {
        if (this.hasShield()) return damage - shield.getDefense();

        return damage;
    }

    public boolean heal() {
        MedKit medKit = (MedKit) this.findItem("MedKit");
        if (medKit != null) {
            setHealth(FULL_HEALTH);
            ui_.topLabelCol1.setText("HP: " + this.getHealth());
            throwItem(medKit);
            return true;
        }
        vm_.showMessage("No MedKit Found", 3000);
        System.out.println("No medKit found");
        return false;
    }

    public void takeDamage(int damage) {
        if (damage < 0) return;
        health_ = health_ - damage;
    } // end of method damageDealt(int damage)

    public void addItem(Item item){
        double newWeight = currentWeight_ + item.getWeight();
        if (newWeight <= MAX_WEIGHT) {
            bag_.add(item);
            currentWeight_ = newWeight;
        } else {
            System.out.println("You have reached the maximum weight of " + MAX_WEIGHT);
        }
    }




    public Item throwItem(Item itemToThrow) {
        Item item = this.findItem(itemToThrow);
            if (item != null && item.isThrowable()) {
                bag_.remove(item);
                currentWeight_ -= item.getWeight();
                return item;
            }
        return null;
    }

    public Item throwItem(String itemToThrow) {
        Item item = this.findItem(itemToThrow);
        if (item != null && item.isThrowable()) {
            bag_.remove(item);
            currentWeight_ -= item.getWeight();
            return item;
        }
        return null;
    }

    public Item findItem(Item itemToFind) {
        for (Item item : bag_) {
            if (item == itemToFind)
                return item;
        }
        return null;
    }

    public Item findItem(String itemToFind) {
        for (Item item : bag_) {
            if (item.getName().equals(itemToFind))
                return item;
        }
        return null;
    }

    public int detonateTNT() {
        TNT tnt = (TNT) this.findItem("TNT");
        if (tnt != null) {
            int explosion = tnt.getDamage();
            throwItem(tnt);
            return explosion;
        }
        vm_.showMessage("No TNT Found", 3000);
        System.out.println("No tnt found");
        return 0;
    }

    public String getData() {
        return name_ + " " + health_;
    }

    public void reset() {
        health_ = FULL_HEALTH;
    }
}
