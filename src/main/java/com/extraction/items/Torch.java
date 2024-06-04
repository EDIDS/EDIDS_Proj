package com.extraction.items;

public class Torch extends Item {
    public static final int TORCH_WEIGHT = 5;
    private boolean isOn;

    public Torch(int duration) {
        super("Torch", TORCH_WEIGHT);
        this.isOn = false;
    }

    public void turnOn() {
            isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }
}