package com.extraction.items;

public class Medikit extends Item {
    private int healingAmount_;

    public Medikit(double weight, int healingAmount) {
        super("Medikit", weight);
        this.healingAmount_ = healingAmount;
    }

    public int getHealingAmount() {return healingAmount_;}
}
