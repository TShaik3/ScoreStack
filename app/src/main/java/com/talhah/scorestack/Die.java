package com.talhah.scorestack;

import java.util.Random;

public class Die {
    Random rand = new Random();
    static private int count = 0;
    private final int id;
    private final int sides;
    protected int totalRolls;
    private Boolean isHeld;
    public Die() {
        this.sides = 6;
        this.id = count++;
        this.totalRolls = 0;
        this.isHeld = false;
    }
    public Die(int n) {
        this.sides = n;
        id= count++;
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
    public int roll() {
        if (!isHeld) {
            totalRolls++;
            Random rand = null;
            int min = 1;
            int max = this.sides;
            int randomNum = this.rand.nextInt((max - min) + 1) + min;
            return randomNum;
        }
    }
}
