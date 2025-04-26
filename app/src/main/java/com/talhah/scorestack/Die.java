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
    public Die(int n) {
        this.sides = n;
        id= count++;
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
    public Boolean getHeld() {
        return this.isHeld;
    }
    public int getCurrentSide() { return this.currentSide; }
    public int roll() {
        if (!isHeld) {
            totalRolls++;
            Random rand = null;
            int min = 1;
            int max = this.sides;
            currentSide = this.rand.nextInt((max - min) + 1) + min;
            return currentSide;
        }
        return currentSide;
    }
}
