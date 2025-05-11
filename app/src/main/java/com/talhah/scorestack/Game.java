package com.talhah.scorestack;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game extends AppCompatActivity {

    private Die[] groupOfDie;
    private int currentTurn;
    private int currentRoll;
    private int[] scoringCategories = new int[13];
    private int[] finishedCategories = new int[13];

    ImageButton rollButton;
    ImageButton playButton;

    ImageButton dice1;
    ImageButton dice2;
    ImageButton dice3;
    ImageButton dice4;
    ImageButton dice5;

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

    ImageButton acesButton;
    ImageButton twosButton;
    ImageButton threesButton;
    ImageButton foursButton;
    ImageButton fivesButton;
    ImageButton sixesButton;

    ImageButton toakButton;
    ImageButton foakButton;
    ImageButton fullButton;
    ImageButton smallButton;
    ImageButton largeButton;
    ImageButton fiveoakButton;
    ImageButton chanceButton;

    ImageView star1;
    ImageView star2;
    ImageView star3;

    TextView[] scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        for (int i : scoringCategories) {
            i = 0;
        }
        for (int i : finishedCategories) {
            i = 0;
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

        rollButton = findViewById(R.id.rollButton);
        playButton = findViewById(R.id.playButton);

        scoreList = new TextView[]{ acesScore, twosScore, threesScore, foursScore, fivesScore,
                                    sixesScore, toakScore, foakScore, fullScore, smallScore,
                                    largeScore, fiveoakScore, chanceScore };

        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);

        acesButton = findViewById(R.id.acesButton);
        twosButton = findViewById(R.id.twosButton);
        threesButton = findViewById(R.id.threesButton);
        foursButton = findViewById(R.id.foursButton);
        fivesButton = findViewById(R.id.fivesButton);
        sixesButton = findViewById(R.id.sixesButton);
        toakButton = findViewById(R.id.toakButton);
        foakButton = findViewById(R.id.foakButton);
        fullButton = findViewById(R.id.fhButton);
        smallButton = findViewById(R.id.ssButton);
        largeButton = findViewById(R.id.lsButton);
        fiveoakButton = findViewById(R.id.fiveoakButton);
        chanceButton = findViewById(R.id.chanceButton);

        List<ImageButton> scoresList = List.of(acesButton, twosButton, threesButton, foursButton, fivesButton,
                                                sixesButton, toakButton, foakButton, fullButton, smallButton,
                                                largeButton, fiveoakButton, chanceButton);

        acesScore = findViewById(R.id.aces_score);
        twosScore = findViewById(R.id.twos_score);
        threesScore = findViewById(R.id.threes_score);
        foursScore = findViewById(R.id.fours_score);
        fivesScore = findViewById(R.id.fives_score);
        sixesScore = findViewById(R.id.sixes_score);

        toakScore = findViewById(R.id.toak_score);
        foakScore = findViewById(R.id.foak_score);
        fullScore = findViewById(R.id.fh_score);
        smallScore = findViewById(R.id.ss_score);
        largeScore = findViewById(R.id.ls_score);
        fiveoakScore = findViewById(R.id.fiveoak_score);
        chanceScore = findViewById(R.id.chance_score);

        currentRoll = 0;
        currentTurn = 0;

        rollButton.setOnClickListener(v -> {
            if (currentRoll < 3 && currentTurn < 13) {
                rollAllDice();
                currentRoll++;
                switch (currentRoll) {
                    case 1:
                        star1.setImageResource(R.drawable.filled_star);
                        break;
                    case 2:
                        star2.setImageResource(R.drawable.filled_star);
                        break;
                    case 3:
                        star3.setImageResource(R.drawable.filled_star);
                        break;
                }
                calculateScores();
                swapButtons(true);
            }
        });

        int index = 0;
        for (ImageButton button : scoresList) {
            button.setTransitionName(String.valueOf(index++));
            button.setOnClickListener(v -> {
                if (currentRoll > 0 && currentTurn < 13) {

                }
            });
        }

        playButton.setOnClickListener(v -> {
            if (currentRoll > 0 && currentTurn < 13) {
                currentRoll = 0;
                star1.setImageResource(R.drawable.empty_star);
                star2.setImageResource(R.drawable.empty_star);
                star3.setImageResource(R.drawable.empty_star);
                currentTurn++;
                swapButtons(false);
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

    private void swapButtons(boolean isActive) {
        if (isActive) {
            acesButton.setBackgroundResource(R.drawable.category_button);
            twosButton.setBackgroundResource(R.drawable.category_button);
            threesButton.setBackgroundResource(R.drawable.category_button);
            foursButton.setBackgroundResource(R.drawable.category_button);
            fivesButton.setBackgroundResource(R.drawable.category_button);
            sixesButton.setBackgroundResource(R.drawable.category_button);

            toakButton.setBackgroundResource(R.drawable.category_button);
            foakButton.setBackgroundResource(R.drawable.category_button);
            fullButton.setBackgroundResource(R.drawable.category_button);
            smallButton.setBackgroundResource(R.drawable.category_button);
            largeButton.setBackgroundResource(R.drawable.category_button);
            fiveoakButton.setBackgroundResource(R.drawable.category_button);
            chanceButton.setBackgroundResource(R.drawable.category_button);
        } else {
            acesButton.setBackgroundResource(R.drawable.inactive_button);
            twosButton.setBackgroundResource(R.drawable.inactive_button);
            threesButton.setBackgroundResource(R.drawable.inactive_button);
            foursButton.setBackgroundResource(R.drawable.inactive_button);
            fivesButton.setBackgroundResource(R.drawable.inactive_button);
            sixesButton.setBackgroundResource(R.drawable.inactive_button);

            toakButton.setBackgroundResource(R.drawable.inactive_button);
            foakButton.setBackgroundResource(R.drawable.inactive_button);
            fullButton.setBackgroundResource(R.drawable.inactive_button);
            smallButton.setBackgroundResource(R.drawable.inactive_button);
            largeButton.setBackgroundResource(R.drawable.inactive_button);
            fiveoakButton.setBackgroundResource(R.drawable.inactive_button);
            chanceButton.setBackgroundResource(R.drawable.inactive_button);
        }
    }

    private void resetScores() {
        for (int i = 0; i < 6; i++) {
            scoringCategories[i] = 0;
        }
    }

    private void calculateScores() {
        resetScores();
        int[] dieValues = new int[5];
        for (int i = 0; i < groupOfDie.length; i++) {
            dieValues[i] = groupOfDie[i].getCurrentSide();
            switch (dieValues[i]) {
                case 1:
                    if (scoringCategories[0] == 0) {
                        scoringCategories[0] += 1;
                        acesScore.setText(String.valueOf(scoringCategories[0]));
                    }
                    break;
                case 2:
                    if (scoringCategories[1] == 0) {
                        scoringCategories[1] += 2;
                        twosScore.setText(String.valueOf(scoringCategories[1]));
                    }
                    break;
                case 3:
                    if (scoringCategories[2] == 0) {
                        scoringCategories[2] += 3;
                        threesScore.setText(String.valueOf(scoringCategories[2]));
                    }
                    break;
                case 4:
                    if (scoringCategories[3] == 0) {
                        scoringCategories[3] += 4;
                        foursScore.setText(String.valueOf(scoringCategories[4]));
                    }
                    break;
                case 5:
                    if (scoringCategories[4] == 0) {
                        scoringCategories[4] += 5;
                        fivesScore.setText(String.valueOf(scoringCategories[4]));
                    }
                    break;
                case 6:
                    if (scoringCategories[5] == 0) {
                        scoringCategories[5] += 6;
                        sixesScore.setText(String.valueOf(scoringCategories[5]));
                    }
                    break;
            }
        }

        // Calculate Three of a Kind
        if (isThreeOfAKind(dieValues) && scoringCategories[6] == 0) {
            scoringCategories[6] = sumOfDice(dieValues);
            toakScore.setText(String.valueOf(sumOfDice(dieValues)));
        } else {
            toakScore.setText("0");
        }

        // Calculate Four of a Kind
        if (isFourOfAKind(dieValues) && scoringCategories[7] == 0) {
            scoringCategories[7] = sumOfDice(dieValues);
            foakScore.setText(String.valueOf(sumOfDice(dieValues)));
        } else {
            foakScore.setText("0");
        }

        // Calculate Full House
        if (isFullHouse(dieValues) && scoringCategories[8] == 0) {
            scoringCategories[8] = 25;
            fullScore.setText(String.valueOf(25));
        } else {
            fullScore.setText("0");
        }

        // Calculate Small Straight
        if (isSmallStraight(dieValues) && scoringCategories[9] == 0) {
            scoringCategories[9] = 30;
            smallScore.setText(String.valueOf(30));
        } else {
            smallScore.setText("0");
        }

        // Calculate Large Straight
        if (isLargeStraight(dieValues) && scoringCategories[10] == 0) {
            scoringCategories[10] = 40;
            largeScore.setText(String.valueOf(40));
        } else {
            largeScore.setText("0");
        }

        // Calculate Five of a Kind
        if (isFiveOfAKind(dieValues) && scoringCategories[11] == 0) {
            scoringCategories[11] = 50;
            fiveoakScore.setText(String.valueOf(50));
        } else {
            fiveoakScore.setText("0");
        }

        // Calculate Chance
        if (scoringCategories[12] == 0) {
            scoringCategories[12] = sumOfDice(dieValues);
            chanceScore.setText(String.valueOf(sumOfDice(dieValues)));
        }
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
