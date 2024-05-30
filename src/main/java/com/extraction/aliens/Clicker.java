package com.extraction.aliens;

public class Clicker extends Alien{

    public Clicker() {
        super();

        this.setName(this.getClass().getSimpleName());
        this.setHealth(TIER2_HEALTH);
    }

    @Override
    public int attack() {
        return RANDOM.nextInt(TIER1_MAXIMUM_ATTACK_DAMAGE);
    }

    @Override
    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    @Override
    public double getEscapeChance() {
        return 0.8;
    }

    @Override
    public double getEludeChance()  { return 0.6; }
}
