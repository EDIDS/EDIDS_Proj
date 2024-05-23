package com.extraction.items;

public abstract class Item {

    /* class fields */
    /** An array of possible item types. */
    public static final String[] ITEM_NAMES = {
            "Key",
            "Weapon",
            "Medikit",
            "Shield",
    };

    private String name;
    private double weight;

    public Item(String name, double weight) { this.name = name; this.weight = weight; }

    public String getName() { return name; }

    public double getWeight() { return weight; }
}