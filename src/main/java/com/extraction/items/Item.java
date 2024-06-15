package com.extraction.items;

/**
 * The Item class represents an item in the game.
 * It is an abstract class that provides a foundation for specific types of items.
 */
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
    private final double weight;
    private final boolean throwable;

    /**
     * Constructs a new Item with the given name, weight, and throwable status.
     * @param name The name of the item.
     * @param weight The weight of the item.
     * @param throwable Whether the item is throwable.
     * @throws IllegalArgumentException If the name is not in the list of valid item names.
     */
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

    /**
     * Returns the name of the item.
     * @return The name of the item.
     */
    public String getName() { return name; }

    /**
     * Returns the weight of the item.
     * @return The weight of the item.
     */
    public double getWeight() { return weight; }

    /**
     * Returns whether the item is throwable.
     * @return True if the item is throwable, false otherwise.
     */
    public boolean isThrowable() { return throwable; }
}