package com.extraction.items;

import java.util.Random;

public class Weapon extends Item {

    /** An array of possible weapon types. */
    public static final String[] WEAPON_NAMES = {
            "REVOLVER",
            "AK47",
            "USPSWORM"
    };

    /** Returns the weight of this type's weapon. */
    public static double type_weight(String type) {
        return switch (type) {
            case "REVOLVER" -> 5;
            case "AK47" -> 10;
            case "USPSWORM" -> 15;
            default -> 0;
        };
    };

    public String type_;
    public int minDamage_;
    public int maxDamage_;

    public Weapon(String type) {
        super("Weapon", type_weight(type));
        this.type_ = type;
        switch (type) {
            case "REVOLVER":
                this.minDamage_ = 10;
                this.maxDamage_ = 30;

                break;
            case "AK47":
                this.minDamage_ = 30;
                this.maxDamage_ = 50;

                break;
            case "USPSWORM":
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
