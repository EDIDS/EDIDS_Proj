package com.extraction.items;

/**
 * The Key class represents a key item in the game.
 * It extends the Item class and sets the name to "Key", the weight to a constant value, and throwable to false.
 */
public class Key extends Item {
    /**
     * The constant weight of a key.
     */
    public static final int KEY_WEIGHT = 1;

    /**
     * Constructs a new Key.
     * The name is set to "Key", the weight is set to KEY_WEIGHT, and throwable is set to false.
     */
    public Key() {
        super("Key", KEY_WEIGHT, false);
    }
}