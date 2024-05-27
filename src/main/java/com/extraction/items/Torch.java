package com.extraction.items;

public class Torch extends Item {
    public static final int TORCH_WEIGHT = 5;
    private boolean isOn;
    private int duration;

    public Torch(int duration) {
        super("Torch", TORCH_WEIGHT);
        this.isOn = false;
        this.duration = duration;
    }

    public void turnOn() {
        if (duration > 0) {
            isOn = true;
        }
    }

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getDuration() {
        return duration;
    }

    public void decreaseDuration() {
        if (duration > 0) {
            duration--;
        }
    }
}