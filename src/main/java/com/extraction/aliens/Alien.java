package com.extraction.aliens;

import java.util.Random;

public abstract class Alien
{
    /* class fields */
    /** An array of possible enemy types. */
    public static final String[] ALIEN_NAMES = {
            // TIER 1
            "Runner",
            // TIER 2
            "Clicker",
            // TIER 3
            "Shambler",
    };

    /** The maximum attack damage of this enemy. */
    public static final int TIER1_MAXIMUM_ATTACK_DAMAGE = 2;
    public static final int TIER2_MAXIMUM_ATTACK_DAMAGE = 8;
    public static final int TIER3_MAXIMUM_ATTACK_DAMAGE = 14;

    /** The maximum health of this enemy. */
    public static final int TIER1_HEALTH = 50;
    public static final int TIER2_HEALTH = 75;
    public static final int TIER3_HEALTH = 100;

    /** The random number generator of this enemy. */
    public static final Random RANDOM = new Random();

    /* instance fields */
    protected int health_;
    protected String name_;
    protected double escapeChance_;
    /**
     * Constructs a new enemy.
     */
    public Alien()
    {
        name_ = ALIEN_NAMES[RANDOM.nextInt(ALIEN_NAMES.length)];

    }

    public void setHealth(int health) {
        this.health_ = health;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public abstract int attack();

    /**
     * Reduces the HP of this enemy by a specifed value.
     *
     * @param damage the amount to reduce the
     */
    public abstract void takeDamage(int damage);

    /**
     * The name of this enemy.
     *
     * @return the name of the enemy
     */
    public String getName()
    {
        return name_;
    }

    /**
     * Returns the health of this enemy.
     *
     * @return heatlh of this enemy
     */
    public int getHealth()
    {
        return health_;
    }

    public abstract double getEscapeChance();

    public abstract double getEludeChance();
}

