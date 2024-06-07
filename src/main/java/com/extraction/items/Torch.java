package com.extraction.items;

/**
 * The Torch class represents a torch item in the game.
 * It extends the Item class and sets the name to "Torch", the weight to a constant value, and throwable to false.
 * It is used to light up dark rooms and explore the map.
 */
public class Torch extends Item {
    /**
     * The weight of the torch item.
     */
    public static final int TORCH_WEIGHT = 10;
    private boolean isOn;

    /**
     * Constructs a new Torch object.
     * Initializes the torch with a weight of 10 and turned off.
     */
    public Torch() {
        super("Torch", TORCH_WEIGHT, false);
        this.isOn = false;
    }

    /**
     * Turns on the torch.
     */
    public void turnOn() {
            isOn = true;
    }

    /**
     * Turns off the torch.
     */
    public void turnOff() {
        isOn = false;
    }

    /**
     * Returns whether the torch is turned on.
     * @return True if the torch is turned on, false otherwise.
     */
    public boolean isOn() {
        return isOn;
    }
}