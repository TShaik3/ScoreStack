package com.talhah.scorestack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Game extends AppCompatActivity {

    private Die[] groupOfDie;
    private int currentTurn;
    private int currentRoll;
    private int[] scoringCategories = new int[13];
    private int[] finishedCategories = new int[13];

    ImageButton rollButton;
    ImageButton endTurnButton;

    ImageButton dice1;
    ImageButton dice2;
    ImageButton dice3;
    ImageButton dice4;
    ImageButton dice5;

    TextView add_aces_score;
    TextView add_twos_score;
    TextView add_threes_score;
    TextView add_fours_score;
    TextView add_fives_score;
    TextView add_sixes_score;

    TextView add_three_of_a_kind_score;
    TextView add_four_of_a_kind_score;
    TextView add_full_house_score;
    TextView add_small_straight_score;
    TextView add_large_straight_score;
    TextView add_five_of_a_kind_score;
    TextView add_chance_score;

    TextView acesScore;
    TextView twosScore;
    TextView threesScore;
    TextView foursScore;
    TextView fivesScore;
    TextView sixesScore;

    TextView toakScore;
    TextView foakScore;
    TextView fullScore;
    TextView smallScore;
    TextView largeScore;
    TextView fiveoakScore;
    TextView chanceScore;

    ImageView checkbox1;
    ImageView checkbox2;
    ImageView checkbox3;

    TextView[] scoreLeft;
    TextView[] scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        if (getIntent().getExtras() != null) {
            scoringCategories = getIntent().getExtras().getIntArray("scoringCategories");
            finishedCategories = getIntent().getExtras().getIntArray("finishedCategories");
            updateScores();
        } else {
            for (int i : scoringCategories) {
                i = 0;
            }
            for (int i : finishedCategories) {
                i = 0;
            }
        }

        groupOfDie = new Die[5];
        for (int i = 0; i < groupOfDie.length; i++) {
            groupOfDie[i] = new Die();
        }

        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);

        rollButton = findViewById(R.id.playButton);
        endTurnButton = findViewById(R.id.endTurnButton);

        add_aces_score = findViewById(R.id.add_aces_score);
        add_twos_score = findViewById(R.id.add_twos_score);
        add_threes_score = findViewById(R.id.add_threes_score);
        add_fours_score = findViewById(R.id.add_fours_score);
        add_fives_score = findViewById(R.id.add_fives_score);
        add_sixes_score = findViewById(R.id.add_sixes_score);

        scoreLeft = new TextView[]{add_aces_score, add_twos_score, add_threes_score,
                                    add_fours_score, add_fives_score, add_sixes_score};

        add_three_of_a_kind_score = findViewById(R.id.add_three_of_a_kind_score);
        add_four_of_a_kind_score = findViewById(R.id.add_four_of_a_kind_score);
        add_full_house_score = findViewById(R.id.add_full_house_score);
        add_small_straight_score = findViewById(R.id.add_small_straight_score);
        add_large_straight_score = findViewById(R.id.add_large_straight_score);
        add_five_of_a_kind_score = findViewById(R.id.add_five_of_a_kind_score);
        add_chance_score = findViewById(R.id.add_chance_score);

        scoreList = new TextView[]{ acesScore, twosScore, threesScore, foursScore, fivesScore,
                                    sixesScore, toakScore, foakScore, fullScore, smallScore,
                                    largeScore, fiveoakScore, chanceScore };

        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);

        currentRoll = 0;
        currentTurn = 0;

        rollButton.setOnClickListener(v -> {
            if (currentRoll < 3 && currentTurn < 13) {
                rollAllDice();
                currentRoll++;
                switch (currentRoll) {
                    case 1:
                        checkbox1.setImageResource(R.drawable.yellow_check);
                        break;
                    case 2:
                        checkbox2.setImageResource(R.drawable.yellow_check);
                        break;
                    case 3:
                        checkbox3.setImageResource(R.drawable.yellow_check);
                        break;
                }
                calculateScores();
            }
        });

        endTurnButton.setOnClickListener(v -> {
            if (currentRoll > 0 && currentTurn < 13) {
                Intent intent = new Intent(Game.this, Results.class);
                intent.putExtra("scoringCategories", scoringCategories);
                intent.putExtra("finishedCategories", finishedCategories);
                startActivity(intent);
            }
        });

        dice1.setOnClickListener(v -> {
            if (currentRoll > 0) {
                groupOfDie[0].setHeld(!groupOfDie[0].getHeld());
                setDiceImage(dice1, groupOfDie[0]);
            }
        });

        dice2.setOnClickListener(v -> {
            if (currentRoll > 0) {
                groupOfDie[1].setHeld(!groupOfDie[1].getHeld());
                setDiceImage(dice2, groupOfDie[1]);
            }
        });

        dice3.setOnClickListener(v -> {
            if (currentRoll > 0) {
                groupOfDie[2].setHeld(!groupOfDie[2].getHeld());
                setDiceImage(dice3, groupOfDie[2]);
            }
        });

        dice4.setOnClickListener(v -> {
            if (currentRoll > 0) {
                groupOfDie[3].setHeld(!groupOfDie[3].getHeld());
                setDiceImage(dice4, groupOfDie[3]);
            }
        });

        dice5.setOnClickListener(v -> {
            if (currentRoll > 0) {
                groupOfDie[4].setHeld(!groupOfDie[4].getHeld());
                setDiceImage(dice5, groupOfDie[4]);
            }
        });
    }

    private void updateScores() {
        for (int i = 0; i < finishedCategories.length; i++) {
            scoreList[i].setText(String.valueOf(finishedCategories[i]));
            if (finishedCategories[i] != 0) {
                scoreLeft[i].setTextColor(getResources().getColor(R.color.red));
            }
        }
    }

    private void resetScores() {
        for (int i = 0; i < 6; i++) {
            scoringCategories[i] = 0;
        }
        add_aces_score.setText("0");
        add_twos_score.setText("0");
        add_threes_score.setText("0");
        add_fours_score.setText("0");
        add_fives_score.setText("0");
        add_sixes_score.setText("0");
    }

    private void calculateScores() {
        resetScores();
        int[] dieValues = new int[5];
        for (int i = 0; i < groupOfDie.length; i++) {
            dieValues[i] = groupOfDie[i].getCurrentSide();
            switch (dieValues[i]) {
                case 1:
                    scoringCategories[0] += 1;
                    add_aces_score.setText(String.valueOf(scoringCategories[0]));
                    break;
                case 2:
                    scoringCategories[1] += 2;
                    add_twos_score.setText(String.valueOf(scoringCategories[1]));
                    break;
                case 3:
                    scoringCategories[2] += 3;
                    add_threes_score.setText(String.valueOf(scoringCategories[2]));
                    break;
                case 4:
                    scoringCategories[3] += 4;
                    add_fours_score.setText(String.valueOf(scoringCategories[3]));
                    break;
                case 5:
                    scoringCategories[4] += 5;
                    add_fives_score.setText(String.valueOf(scoringCategories[4]));
                    break;
                case 6:
                    scoringCategories[5] += 6;
                    add_sixes_score.setText(String.valueOf(scoringCategories[5]));
                    break;
            }
        }

        // Calculate Three of a Kind
        if (isThreeOfAKind(dieValues)) {
            scoringCategories[6] = sumOfDice(dieValues);
            add_three_of_a_kind_score.setText(String.valueOf(sumOfDice(dieValues)));
        } else {
            add_three_of_a_kind_score.setText("0");
        }

        // Calculate Four of a Kind
        if (isFourOfAKind(dieValues)) {
            scoringCategories[7] = sumOfDice(dieValues);
            add_four_of_a_kind_score.setText(String.valueOf(sumOfDice(dieValues)));
        } else {
            add_four_of_a_kind_score.setText("0");
        }

        // Calculate Full House
        if (isFullHouse(dieValues)) {
            scoringCategories[8] = 25;
            add_full_house_score.setText(String.valueOf(25));
        } else {
            add_full_house_score.setText("0");
        }

        // Calculate Small Straight
        if (isSmallStraight(dieValues)) {
            scoringCategories[9] = 30;
            add_small_straight_score.setText(String.valueOf(30));
        } else {
            add_small_straight_score.setText("0");
        }

        // Calculate Large Straight
        if (isLargeStraight(dieValues)) {
            scoringCategories[10] = 40;
            add_large_straight_score.setText(String.valueOf(40));
        } else {
            add_large_straight_score.setText("0");
        }

        // Calculate Five of a Kind
        if (isFiveOfAKind(dieValues)) {
            scoringCategories[11] = 50;
            add_five_of_a_kind_score.setText(String.valueOf(50));
        } else {
            add_five_of_a_kind_score.setText("0");
        }

        // Calculate Chance
        scoringCategories[12] = sumOfDice(dieValues);
        add_chance_score.setText(String.valueOf(sumOfDice(dieValues)));
    }

    private int sumOfDice(int[] dieValues) {
        int sum = 0;
        for (int value : dieValues) {
            sum += value;
        }
        return sum;
    }

    private boolean isFourOfAKind(int[] dieValues) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : dieValues) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        return counts.containsValue(4);
    }

    private boolean isThreeOfAKind(int[] dieValues) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : dieValues) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        return counts.containsValue(3);
    }

    private boolean isFullHouse(int[] dieValues) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : dieValues) {

            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        return counts.size() == 2 && (counts.containsValue(3) && counts.containsValue(2));
    }

    private boolean isSmallStraight(int[] dieValues) {
        Arrays.sort(dieValues);
        int count = 1;
        for (int i = 0; i < dieValues.length - 1; i++) {
            if (dieValues[i + 1] - dieValues[i] == 1) {
                count++;
            } else if (dieValues[i + 1] - dieValues[i] > 1) {
                count = 1;
            }
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isLargeStraight(int[] dieValues) {
        Arrays.sort(dieValues);
        for (int i = 0; i < dieValues.length - 1; i++) {
            if (dieValues[i + 1] - dieValues[i] != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isFiveOfAKind(int[] dieValues) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : dieValues) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        return counts.containsValue(5);
    }

    private void rollAllDice() {
        for (Die d : groupOfDie) {
            d.roll();
        }

//        // Animation Needs work
//        dice1.setBackgroundResource(R.drawable.dice_animation);
//        dice2.setBackgroundResource(R.drawable.dice_animation);
//        dice3.setBackgroundResource(R.drawable.dice_animation);
//        dice4.setBackgroundResource(R.drawable.dice_animation);
//        dice5.setBackgroundResource(R.drawable.dice_animation);
//
//        AnimationDrawable dice1Animation = (AnimationDrawable) dice1.getBackground();
//        AnimationDrawable dice2Animation = (AnimationDrawable) dice2.getBackground();
//        AnimationDrawable dice3Animation = (AnimationDrawable) dice3.getBackground();
//        AnimationDrawable dice4Animation = (AnimationDrawable) dice4.getBackground();
//        AnimationDrawable dice5Animation = (AnimationDrawable) dice5.getBackground();
//
//        dice1Animation.start();
//        dice2Animation.start();
//        dice3Animation.start();
//        dice4Animation.start();
//        dice5Animation.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }

        updateDice();
    }

    private void updateDice() {
        setDiceImage(dice1, groupOfDie[0]);
        setDiceImage(dice2, groupOfDie[1]);
        setDiceImage(dice3, groupOfDie[2]);
        setDiceImage(dice4, groupOfDie[3]);
        setDiceImage(dice5, groupOfDie[4]);
    }

    private void setDiceImage(ImageButton dice, Die die) {
        int diceValue = die.getCurrentSide();
        switch (diceValue) {
            case 1:
                if (die.getHeld()) {
                    dice.setBackgroundResource(R.drawable.one_hold);
                } else {
                    dice.setBackgroundResource(R.drawable.one);
                }
                break;
            case 2:
                if (die.getHeld()) {
                    dice.setBackgroundResource(R.drawable.two_hold);
                } else {
                    dice.setBackgroundResource(R.drawable.two);
                }
                break;
            case 3:
                if (die.getHeld()) {
                    dice.setBackgroundResource(R.drawable.three_hold);
                } else {
                    dice.setBackgroundResource(R.drawable.three);
                }
                break;
            case 4:
                if (die.getHeld()) {
                    dice.setBackgroundResource(R.drawable.four_hold);
                } else {
                    dice.setBackgroundResource(R.drawable.four);
                }
                break;
            case 5:
                if (die.getHeld()) {
                    dice.setBackgroundResource(R.drawable.five_hold);
                } else {
                    dice.setBackgroundResource(R.drawable.five);
                }
                break;
            case 6:
                if (die.getHeld()) {
                    dice.setBackgroundResource(R.drawable.six_hold);
                } else {
                    dice.setBackgroundResource(R.drawable.six);
                }
                break;
        }
    }

    public int[] getScoringCategories() {
        return scoringCategories;
    }

    public void setScoringCategories(int[] scoringCategories) {
        this.scoringCategories = scoringCategories;
    }
}
