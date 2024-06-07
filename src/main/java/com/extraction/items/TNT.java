package com.extraction.items;

import com.extraction.player.Player;

public class TNT extends Item{
    public static final int TNT_WEIGHT = 20;
    public static final int TNT_DAMAGE = 50;

    public TNT() {
        super("TNT", TNT_WEIGHT, true);
    }

    public int getDamage() {
        return TNT_DAMAGE;
    }
}
