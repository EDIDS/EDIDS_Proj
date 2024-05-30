package com.extraction.aliens;

public class Runner extends Alien{

    public Runner() {
        super();

        this.setName(this.getClass().getSimpleName());
        this.setHealth(TIER1_HEALTH);
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
        return 0.6;
    }

    @Override
    public double getEludeChance()  { return 0.4; }
}
