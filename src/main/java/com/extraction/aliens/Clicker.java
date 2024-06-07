package com.extraction.aliens;

/**
 * The Clicker class extends the Alien class and represents a specific type of alien in the game.
 * It overrides the attack, takeDamage, getEscapeChance, and getEludeChance methods from the Alien class.
 * The Clicker has a specific health level and attack damage defined by the TIER2_HEALTH and TIER2_MAXIMUM_ATTACK_DAMAGE constants.
 */
public class Clicker extends Alien{

    /**
     * Constructs a new Clicker with the name "Clicker" and sets its health to TIER2_HEALTH.
     */
    public Clicker() {
        super("Clicker");
        this.setHealth(TIER2_HEALTH);
    }

    /**
     * The Clicker attacks. The damage dealt is a random number between 1 and TIER2_MAXIMUM_ATTACK_DAMAGE.
     * @return The damage dealt by the Clicker.
     */
    @Override
    public int attack() {
        return RANDOM.nextInt(TIER2_MAXIMUM_ATTACK_DAMAGE)+1;
    }

    /**
     * The Clicker takes damage. The health of the Clicker is reduced by the amount of damage taken.
     * @param damage The amount of damage to be taken.
     */
    @Override
    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    /**
     * Gets the escape chance of the Clicker.
     * @return The escape chance of the Clicker, which is 0.2.
     */
    @Override
    public double getEscapeChance() {
        return 0.2;
    }

    /**
     * Gets the elude chance of the Clicker.
     * @return The elude chance of the Clicker, which is 0.3.
     */
    @Override
    public double getEludeChance()  { return 0.3; }
}
