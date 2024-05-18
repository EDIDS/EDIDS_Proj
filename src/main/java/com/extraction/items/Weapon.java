package com.extraction.items;

import java.util.Random;

public class Weapon extends Item {

    public WeaponType type_;
    public int minDamage_;
    public int maxDamage_;

    public Weapon(ItemName name, double weight, WeaponType type) {
        super(name, weight);
        this.type_ = type;
        switch (type) {
            case REVOLVER:
                this.minDamage_ = 10;
                this.maxDamage_ = 30;

                break;
            case AK47:
                this.minDamage_ = 30;
                this.maxDamage_ = 50;

                break;
            case USPSWORM:
                this.minDamage_ = 20;
                this.maxDamage_ = 40;

                break;
        }
    }

    public int calculateDamage() {
        Random rand = new Random();
        int roll = rand.nextInt(3) + 1;
        if(roll == 1) return minDamage_;
        else if(roll == 2) return (maxDamage_+minDamage_)/2;

        return maxDamage_;

    }

}
