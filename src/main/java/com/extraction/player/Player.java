package com.extraction.player;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import com.extraction.items.*;
import com.extraction.map.Room;

public class Player {

    public static Random RANDOM = new Random();

    public static final int FULL_HEALTH = 100;

    public static final int BASE_ATTACK_DAMAGE = 25;

    public static final String NO_NAME = "";

    public static final int MAX_WEIGHT = 100;

    private int health_;
    private String name_;
    private List<Item> bag_;
    private double currentWeight_ = 0;
    private Room currentRoom_;
    private Weapon weapon;
    private int score_;

    public Player()
    {
        name_ = NO_NAME;
        health_ = FULL_HEALTH;
        bag_ = new ArrayList<Item>();
    }

    public Player(String name)
    {
        name_ = name;
        health_ = FULL_HEALTH;
        bag_ = new ArrayList<Item>();
    }
    /**
     * Getter e setter (per json)
     */

    public int getHealth() {

        return health_;
    }

    public void setHealth(int health) {
        health_ = health;
    }

    public String getName() {

        return name_;
    }

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

    public double getCurrentWeight_() {

        return currentWeight_;
    }

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

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    /**
     * Metodi
     */
    public boolean hasWeapon() {
        for (Item item : bag_) {
            return item instanceof Weapon;
        }
        return false;
    }    // end of method hasWeapon()


    public int attack()
    {
        if (this.hasWeapon()) return weapon.calculateDamage();

        return BASE_ATTACK_DAMAGE;
    }

    public void addItem(Item item){
        if(currentWeight_ + item.getWeight() <= MAX_WEIGHT){

            bag_.add(item);
            currentWeight_ += item.getWeight();
        }

        else
            System.out.println("You have reached the maximum weight of " + MAX_WEIGHT);
    }

    public Item throwItem(Item itemToThrow) {
        for (Item item : bag_) {
            if (item.getName() == itemToThrow.getName()) {
                bag_.remove(item);
                //currentRoom_.setItem(itemToThrow); --> da fixare, non esiste il metodo setItem in Room, ma in Building
                return item;
            }
        }
        return null;
    }

    public void heal() {
        for (Item item : bag_) {
            if (item instanceof Medikit) {
                Medikit medikit = (Medikit) item;
                setHealth(medikit.getHealingAmount());
                throwItem(medikit);
                return;
            }
        }
        System.out.println("No medikit found");
    }

    public void takeDamage(int damage) { health_ = health_ - damage;} // end of method damageDealt(int damage)

    public String getData() {

        return
                name_ + " " + health_;
    }

    public void reset() {

        health_ = FULL_HEALTH;
    }
}
