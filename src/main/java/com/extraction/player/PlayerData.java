package com.extraction.player;

import com.extraction.items.*;
import com.extraction.map.Room;

import java.util.List;

public class PlayerData {
    private int health_;
    private String name_;
    private List<Item> bag_;
    private double currentWeight_ = 0;
    private Room currentRoom_;
    private Weapon weapon;
    private Shield shield;
    private int score_;

    public PlayerData(Player player) {
        health_ = player.getHealth();
        name_ = player.getName();
        bag_ = player.getBag();
        currentWeight_ = player.getCurrentWeight_();
        currentRoom_ = player.getCurrentRoom_();
        score_ = player.getScore();
        weapon = player.getWeapon();
        shield = player.getShield();

    }

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

    public void setWeapon(Weapon newWeapon) {
        weapon = newWeapon;
    }

    public Shield getShield() { return shield; }

    public void setShield(Shield newShield) {
        shield = newShield;
    }


}
