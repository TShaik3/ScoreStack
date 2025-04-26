package com.talhah.scorestack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
//extends AppCompatActivity
public class Game {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    private Die[] groupOfDie;
    private int currentTurn;
    private int currentRoll;
    private int[] scoringCategories;
    // Aces, Twos, Threes, Fours, Fives, Sixes
    // Three of a Kind, Four of a Kind, Full House, Small Straight,
    // Large Straight, Five of a Kind, Chance
    // 13 Categories

    public Game() {
        for (Die d : groupOfDie) {
            d = new Die();
        }
        currentRoll = 0;
        currentTurn = 0;
        scoringCategories = new int[13];
    }

    public void newGame() {

    }
}
