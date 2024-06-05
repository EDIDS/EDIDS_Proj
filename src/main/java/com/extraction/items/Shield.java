package com.extraction.items;

public class Shield extends Item {

    public static final int SHIELD_WEIGHT = 15;
    public static final int SHIELD_DEFENSE = 20;

    public Shield() {
        super("Shield", SHIELD_WEIGHT, true);
    }

    public int getDefense() {
        return SHIELD_DEFENSE;
    }

    // Additional methods for Shield
}
