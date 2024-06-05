package com.extraction.items;

public abstract class Item {

    /* class fields */
    /** An array of possible item types. */
    public static final String[] ITEM_NAMES = {
            "Key",
            "MedKit",
            "Shield",
            "TNT",
            "Torch",
            "Weapon"
    };

    private String name;
    private double weight;
    private boolean throwable;

    public Item(String name, double weight, boolean throwable) {
        for (String itemName : ITEM_NAMES) {
            if (itemName.equals(name)) {
                this.name = name;
                break;
            }
        }
        if (this.name == null)
            throw new IllegalArgumentException();
        this.weight = weight;

        this.throwable = throwable;
    }

    public String getName() { return name; }

    public double getWeight() { return weight; }

    public boolean isThrowable() { return throwable; }
}