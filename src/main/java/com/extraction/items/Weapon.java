package com.extraction.items;

import java.util.Random;

public class Weapon extends Item {

    /** An array of possible weapon types. */
    public static final String[] WEAPON_NAMES = {
            "REVOLVER",
            "AK47",
            "USPSWORM"
    };

    public static final int REVOLVER_WEIGHT = 5;
    public static final int AK47_WEIGHT = 10;
    public static final int USPSWORM_WEIGHT = 15;

    /** Returns the weight of this type's weapon. */
    public static double type_weight(String type) {
        return switch (type) {
            case "REVOLVER" -> REVOLVER_WEIGHT;
            case "AK47" -> AK47_WEIGHT;
            case "USPSWORM" -> USPSWORM_WEIGHT;
            default -> 0;
        };
    }

    public String type_;
    public int minDamage_;
    public int maxDamage_;

    public Weapon(String type) {
        super("Weapon", type_weight(type));
        for (String weaponType : WEAPON_NAMES) {
            if (weaponType.equals(type)) {
                this.type_ = type;
                break;
            }
        }
        if (this.type_ == null)
            throw new IllegalArgumentException();

        switch (type) {
            case "REVOLVER":
                this.minDamage_ = 20;
                this.maxDamage_ = 40;

                break;
            case "AK47":
                this.minDamage_ = 40;
                this.maxDamage_ = 60;

                break;
            case "USPSWORM":
                this.minDamage_ = 30;
                this.maxDamage_ = 50;

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
