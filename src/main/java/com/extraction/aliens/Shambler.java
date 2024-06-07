package com.extraction.aliens;

/**
 * The Shambler class extends the Alien class and represents a specific type of alien in the game.
 * It overrides the attack, takeDamage, getEscapeChance, and getEludeChance methods from the Alien class.
 * The Shambler has a specific health level and attack damage defined by the TIER3_HEALTH and TIER3_MAXIMUM_ATTACK_DAMAGE constants.
 */
public class Shambler extends Alien{

    /**
     * Constructs a new Shambler with the name "Shambler" and sets its health to TIER3_HEALTH.
     */
    public Shambler() {
        super("Shambler");

        this.setName(this.getClass().getSimpleName());
        this.setHealth(TIER3_HEALTH);
    }

    /**
     * The Shambler attacks. The damage dealt is a random number between 1 and TIER3_MAXIMUM_ATTACK_DAMAGE.
     * @return The damage dealt by the Shambler.
     */
    @Override
    public int attack() {
        return RANDOM.nextInt(TIER3_MAXIMUM_ATTACK_DAMAGE)+1;
    }

    /**
     * The Shambler takes damage. The health of the Shambler is reduced by the amount of damage taken.
     * @param damage The amount of damage to be taken.
     */
    @Override
    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    /**
     * Gets the escape chance of the Shambler.
     * @return The escape chance of the Shambler, which is 0.1.
     */
    @Override
    public double getEscapeChance() {
        return 0.1;
    }

    /**
     * Gets the elude chance of the Shambler.
     * @return The elude chance of the Shambler, which is 0.2.
     */
    @Override
    public double getEludeChance()  { return 0.2; }
}
