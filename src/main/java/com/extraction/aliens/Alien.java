package com.extraction.aliens;

import java.util.Random;

public abstract class Alien
{
    /**
     * This abstract class represents an alien in the game.
     * It contains information about the alien's health, name, escape chance, and elude chance.
     * It also contains methods for attacking, taking damage, and getting the escape and elude chances.
     */

    /** The maximum attack damage of this enemy. */
    public static final int TIER1_MAXIMUM_ATTACK_DAMAGE = 25;
    public static final int TIER2_MAXIMUM_ATTACK_DAMAGE = 50;
    public static final int TIER3_MAXIMUM_ATTACK_DAMAGE = 75;

    /** The maximum health of this enemy. */
    public static final int TIER1_HEALTH = 50;
    public static final int TIER2_HEALTH = 75;
    public static final int TIER3_HEALTH = 100;

    /** The random number generator of this enemy. */
    public static final Random RANDOM = new Random();

    protected int health_;
    protected String name_;
    protected double escapeChance_;

    /**
     * Constructs a new alien with the given name.
     * @param name The name of the alien.
     */
    public Alien(String name)
    {
        name_ = name;
    }

    /**
     * Sets the health of the alien.
     * @param health The new health of the alien.
     */
    public void setHealth(int health) {
        this.health_ = health;
    }

    /**
     * Sets the name of the alien.
     * @param name The new name of the alien.
     */
    public void setName(String name) {
        this.name_ = name;
    }

    /**
     * The alien attacks. This method should be overridden in subclasses.
     * @return The damage dealt by the alien.
     */
    public abstract int attack();

    /**
     * The alien takes damage.
     * @param damage The amount of damage to be taken.
     */
    public abstract void takeDamage(int damage);

    /**
     * Gets the name of the alien.
     * @return The name of the alien.
     */
    public String getName()
    {
        return name_;
    }

    /**
     * Gets the health of the alien.
     * @return The health of the alien.
     */
    public int getHealth()
    {
        return health_;
    }

    /**
     * Gets the escape chance of the alien. This method should be overridden in subclasses.
     * @return The escape chance of the alien.
     */
    public abstract double getEscapeChance();

    /**
     * Gets the elude chance of the alien. This method should be overridden in subclasses.
     * @return The elude chance of the alien.
     */
    public abstract double getEludeChance();
}

