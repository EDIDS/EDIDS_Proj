package com.extraction.items;

public class Medikit extends Item {
    private int healingAmount_;

    public Medikit(ItemName name, double weight, int healingAmount) {
        super(name, weight);
        this.healingAmount_ = healingAmount;
    }

    public int getHealingAmount() {return healingAmount_;}
}
