package com.extraction.items;

public class TNT extends Item{
    public static final int TNT_WEIGHT = 10;
    public static final int TNT_DAMAGE = 50;

    public TNT() {
        super("TNT", TNT_WEIGHT);
    }

    public int getDamage() {
        return TNT_DAMAGE;
    }
}
