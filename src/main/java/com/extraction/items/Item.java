package com.extraction.items;

public class Item {
    private ItemName name;
    private double weight;

    public Item(ItemName name, int weight) { this.name = name; this.weight = weight; }

    public ItemName getName() { return name; }

    public double getWeight() { return weight; }
}