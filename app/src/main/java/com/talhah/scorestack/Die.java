package com.talhah.scorestack;

import java.util.Random;

public class Die {

    Random rand = new Random();
    static private int count = 0;
    private final int id;
    private final int sides;
    protected int totalRolls;
    private Boolean isHeld;
    private int currentSide;

    public Die() {
        this.sides = 6;
        this.id = count++;
        this.totalRolls = 0;
        this.isHeld = false;
        this.currentSide = 1;
    }

    public int getSides() {
        return this.sides;
    }

    public int getID() {
        return this.id;
    }

    public int getTotalRolls(){
        return this.totalRolls;
    }

    public int getCurrentSide() {
        if (currentSide == 0) {
            roll();
        }
        return this.currentSide;
    }

    public Boolean getHeld() {
        return this.isHeld;
    }

    public void setHeld(Boolean isHeld) {
        if (totalRolls == 0) {
            this.isHeld = false;
        }
        this.isHeld = isHeld;
    }

    public void roll() {
        if (!isHeld && totalRolls < 3) {
            totalRolls++;
            int min = 1;
            currentSide = this.rand.nextInt((this.sides - min) + 1);
        }
    }

}
