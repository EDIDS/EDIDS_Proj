package com.extraction.aliens;

public class Shambler extends Alien{

    public Shambler() {
        super();

        this.setName(this.getClass().getSimpleName());
        this.setHealth(TIER3_HEALTH);
    }

    @Override
    public int attack() {
        return RANDOM.nextInt(TIER3_MAXIMUM_ATTACK_DAMAGE);
    }

    @Override
    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    public double getEscapeChance() {
        return 0.4;
    }
}
