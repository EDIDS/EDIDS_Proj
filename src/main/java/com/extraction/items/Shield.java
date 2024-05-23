package com.extraction.items;

public class Shield extends Item {
    private int defense;

    public Shield(double weight, int defense) {
        super("Shield", weight);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    // Additional methods for Shield
}
