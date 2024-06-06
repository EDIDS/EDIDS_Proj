package com.extraction.items;

/**
 * The Shield class represents a shield item in the game.
 * It extends the Item class and sets the name to "Shield", the weight to a constant value, and throwable to true.
 * It also has a defense value.
 */
public class Shield extends Item {

    /**
     * The constant weight of a shield.
     */
    public static final int SHIELD_WEIGHT = 40;

    /**
     * The constant defense value of a shield.
     */
    public static final int SHIELD_DEFENSE = 20;

    /**
     * Constructs a new Shield.
     * The name is set to "Shield", the weight is set to SHIELD_WEIGHT, and throwable is set to true.
     */
    public Shield() {
        super("Shield", SHIELD_WEIGHT, true);
    }

    /**
     * Returns the defense value of the shield.
     * @return The defense value of the shield.
     */
    public int getDefense() {
        return SHIELD_DEFENSE;
    }

}
