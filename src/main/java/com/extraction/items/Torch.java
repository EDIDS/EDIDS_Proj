package com.extraction.items;

public class Torch extends Item {
    public static final int TORCH_WEIGHT = 5;
    private boolean isOn;

    public Torch() {
        super("Torch", TORCH_WEIGHT, false);
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