package com.extraction.items;

public class Shield extends Item {
    private int defense;

    public Shield(ItemName name, double weight, int defense) {
        super(name, weight);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    // Additional methods for Shield
}
