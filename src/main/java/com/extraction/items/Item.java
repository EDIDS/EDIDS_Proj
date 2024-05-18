package com.extraction.items;

public abstract class Item {
    private ItemName name;
    private double weight;

    public Item(ItemName name, double weight) { this.name = name; this.weight = weight; }

    public ItemName getName() { return name; }

    public double getWeight() { return weight; }
}