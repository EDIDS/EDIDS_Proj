package com.extraction.items;

/**
 * The MedKit class represents a medkit item in the game.
 * It extends the Item class and sets the name to "MedKit", the weight to a constant value, and throwable to true.
 */
public class MedKit extends Item {

    /**
     * The constant weight of a medkit.
     */
    public static final int MEDKIT_WEIGHT = 30;

    /**
     * Constructs a new MedKit.
     * The name is set to "MedKit", the weight is set to MEDKIT_WEIGHT, and throwable is set to true.
     */
    public MedKit() {
        super("MedKit", MEDKIT_WEIGHT, true);
    }
}
