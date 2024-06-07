package com.extraction.items;

import java.util.Random;

/**
 * The Weapon class represents a weapon item in the game.
 * It extends the Item class and sets the name to "Weapon", the weight to a value depending on the type of weapon, and throwable to true.
 * It also has a type, minimum damage, and maximum damage.
 */
public class Weapon extends Item {

    /** An array of possible weapon types. */
    public static final String[] WEAPON_NAMES = {
            "REVOLVER",
            "AK47",
            "USPSWORM"
    };

    public static final int REVOLVER_WEIGHT = 10;
    public static final int AK47_WEIGHT = 15;
    public static final int USPSWORM_WEIGHT = 20;

    /**
     * Returns the weight of this type's weapon.
     * @param type The type of the weapon.
     * @return The weight of the weapon type.
     */
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

    /**
     * Constructs a new Weapon.
     * The name is set to "Weapon", the weight is set to the weight of the type, and throwable is set to true.
     * The type, minimum damage, and maximum damage are set depending on the type of weapon.
     * @param type The type of the weapon.
     */
    public Weapon(String type) {
        super("Weapon", type_weight(type), true);
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

    /**
     * Returns the type of the weapon.
     * @return The type of the weapon.
     */
    public String getType() { return type_; }

    /**
     * Calculates the damage of the weapon.
     * The damage is calculated randomly, it can be the minimum damage, the maximum damage, or the average of both.
     * @return The calculated damage of the weapon.
     */
    public int calculateDamage() {
        Random rand = new Random();
        int roll = rand.nextInt(3) + 1;
        if(roll == 1) return minDamage_;
        else if(roll == 2) return (maxDamage_+minDamage_)/2;

        return maxDamage_;

    }
}
