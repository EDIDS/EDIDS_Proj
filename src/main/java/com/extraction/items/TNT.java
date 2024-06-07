package com.extraction.items;

import com.extraction.player.Player;

/**
 * The TNT class represents a TNT item in the game.
 * It extends the Item class and sets the name to "TNT", the weight to a constant value, and throwable to true.
 * It also has a damage value.
 */
public class TNT extends Item{

    /**
     * The constant weight of a TNT.
     */
    public static final int TNT_WEIGHT = 20;

    /**
     * The constant damage value of a TNT.
     */
    public static final int TNT_DAMAGE = 50;

    /**
     * Constructs a new TNT.
     * The name is set to "TNT", the weight is set to TNT_WEIGHT, and throwable is set to true.
     */
    public TNT() {
        super("TNT", TNT_WEIGHT, true);
    }

    /**
     * Returns the damage value of the TNT.
     * @return The damage value of the TNT.
     */
    public int getDamage() {
        return TNT_DAMAGE;
    }
}
