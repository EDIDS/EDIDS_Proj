package com.extraction.aliens;

/**
 * The Runner class extends the Alien class and represents a specific type of alien in the game.
 * It overrides the attack, takeDamage, getEscapeChance, and getEludeChance methods from the Alien class.
 * The Runner has a specific health level and attack damage defined by the TIER1_HEALTH and TIER1_MAXIMUM_ATTACK_DAMAGE constants.
 */
public class Runner extends Alien{

    /**
     * Constructs a new Runner with the name "Runner" and sets its health to TIER1_HEALTH.
     */
    public Runner() {
        super("Runner");

        this.setName(this.getClass().getSimpleName());
        this.setHealth(TIER1_HEALTH);
    }

    /**
     * The Runner attacks. The damage dealt is a random number between 1 and TIER1_MAXIMUM_ATTACK_DAMAGE.
     * @return The damage dealt by the Runner.
     */
    @Override
    public int attack() {
        return RANDOM.nextInt(TIER1_MAXIMUM_ATTACK_DAMAGE)+1;
    }

    /**
     * The Runner takes damage. The health of the Runner is reduced by the amount of damage taken.
     * @param damage The amount of damage to be taken.
     */
    @Override
    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    /**
     * Gets the escape chance of the Runner.
     * @return The escape chance of the Runner, which is 0.3.
     */
    @Override
    public double getEscapeChance() {
        return 0.3;
    }

    /**
     * Gets the elude chance of the Runner.
     * @return The elude chance of the Runner, which is 0.4.
     */
    @Override
    public double getEludeChance()  { return 0.4; }
}
