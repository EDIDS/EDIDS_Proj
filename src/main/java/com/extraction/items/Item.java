package com.extraction.items;

public abstract class Item {

    /* class fields */
    /** An array of possible item types. */
    public static final String[] ITEM_NAMES = {
            "Key",
            "Medikit",
            "Shield",
            "TNT",
            "Torch",
            "Weapon"
    };

    private String name;
    private double weight;

    public Item(String name, double weight) {
        for (String itemName : ITEM_NAMES) {
            if (itemName.equals(name)) {
                this.name = name;
                break;
            }
        }
        if (this.name == null)
            throw new IllegalArgumentException();
        this.weight = weight;
    }

    public String getName() { return name; }

    public double getWeight() { return weight; }
}