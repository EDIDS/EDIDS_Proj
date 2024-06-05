package com.extraction.aliens;

public class Clicker extends Alien{

    public Clicker() {
        super("Clicker");
        this.setHealth(TIER2_HEALTH);
    }

    @Override
    public int attack() {
        return RANDOM.nextInt(TIER2_MAXIMUM_ATTACK_DAMAGE)+1;
    }

    @Override
    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    @Override
    public double getEscapeChance() {
        return 0.2;
    }

    @Override
    public double getEludeChance()  { return 0.3; }
}
