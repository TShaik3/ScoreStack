package com.talhah.scorestack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {

    private int[] scoringCategories;
    private int[] finishedCategories;
    private Boolean clicked = false;

    Button acesButton;
    Button twosButton;
    Button threesButton;
    Button foursButton;
    Button fivesButton;
    Button sixesButton;

    Button toakButton;
    Button foakButton;
    Button fullButton;
    Button smallButton;
    Button largeButton;
    Button fiveoakButton;
    Button chanceButton;

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

    ImageButton nextTurnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            scoringCategories = extras.getIntArray("scoringCategories");
            finishedCategories = extras.getIntArray("finishedCategories");
        }

        acesButton = findViewById(R.id.acesButton);
        twosButton = findViewById(R.id.twosButton);
        threesButton = findViewById(R.id.threesButton);
        foursButton = findViewById(R.id.foursButton);
        fivesButton = findViewById(R.id.fivesButton);
        sixesButton = findViewById(R.id.sixesButton);

        toakButton = findViewById(R.id.threeOfAKindButton);
        foakButton = findViewById(R.id.fourOfAKindButton);
        fullButton = findViewById(R.id.fullHouseButton);
        smallButton = findViewById(R.id.smallStraightButton);
        largeButton = findViewById(R.id.largeStraightButton);
        fiveoakButton = findViewById(R.id.fiveOfAKindButton);
        chanceButton = findViewById(R.id.chanceButton);

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

        nextTurnButton = findViewById(R.id.nextTurnButton);

        updateScores();

        acesButton.setOnClickListener(v -> {
            if (finishedCategories[0] == 0 && !clicked) {
                finishedCategories[0] = scoringCategories[0];
                scoringCategories[0] = 0;
                acesScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        twosButton.setOnClickListener(v -> {
            if (finishedCategories[1] == 0 && !clicked) {
                finishedCategories[1] = scoringCategories[1];
                scoringCategories[1] = 0;
                twosScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        threesButton.setOnClickListener(v -> {
            if (finishedCategories[2] == 0 && !clicked) {
                finishedCategories[2] = scoringCategories[2];
                scoringCategories[2] = 0;
                threesScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        foursButton.setOnClickListener(v -> {
            if (finishedCategories[3] == 0 && !clicked) {
                finishedCategories[3] = scoringCategories[3];
                scoringCategories[3] = 0;
                foursScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        fivesButton.setOnClickListener(v -> {
            if (finishedCategories[4] == 0 && !clicked) {
                finishedCategories[4] = scoringCategories[4];
                scoringCategories[4] = 0;
                fivesScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        sixesButton.setOnClickListener(v -> {
            if (finishedCategories[5] == 0 && !clicked) {
                finishedCategories[5] = scoringCategories[5];
                scoringCategories[5] = 0;
                sixesScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        toakButton.setOnClickListener(v -> {
            if (finishedCategories[6] == 0 && !clicked) {
                finishedCategories[6] = scoringCategories[6];
                scoringCategories[6] = 0;
                toakScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        foakButton.setOnClickListener(v -> {
            if (finishedCategories[7] == 0 && !clicked) {
                finishedCategories[7] = scoringCategories[7];
                scoringCategories[7] = 0;
                foakScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        fullButton.setOnClickListener(v -> {
            if (finishedCategories[8] == 0 && !clicked) {
                finishedCategories[8] = scoringCategories[8];
                scoringCategories[8] = 0;
                fullScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        smallButton.setOnClickListener(v -> {
            if (finishedCategories[9] == 0 && !clicked) {
                finishedCategories[9] = scoringCategories[9];
                scoringCategories[9] = 0;
                smallScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        largeButton.setOnClickListener(v -> {
            if (finishedCategories[10] == 0 && !clicked) {
                finishedCategories[10] = scoringCategories[10];
                scoringCategories[10] = 0;
                largeScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        fiveoakButton.setOnClickListener(v -> {
            if (finishedCategories[11] == 0 && !clicked) {
                finishedCategories[11] = scoringCategories[11];
                scoringCategories[11] = 0;
                fiveoakScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        chanceButton.setOnClickListener(v -> {
            if (finishedCategories[12] == 0 && !clicked) {
                finishedCategories[12] = scoringCategories[12];
                scoringCategories[12] = 0;
                chanceScore.setTextColor(getResources().getColor(R.color.black));
                clicked = true;
            }
        });

        nextTurnButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Game.class);
            intent.putExtra("scoringCategories", scoringCategories);
            intent.putExtra("finishedCategories", finishedCategories);
            startActivity(intent);
        });
    }

    private void updateScores() {
        acesScore.setText(String.valueOf(scoringCategories[0]));
        twosScore.setText(String.valueOf(scoringCategories[1]));
        threesScore.setText(String.valueOf(scoringCategories[2]));
        foursScore.setText(String.valueOf(scoringCategories[3]));
        fivesScore.setText(String.valueOf(scoringCategories[4]));
        sixesScore.setText(String.valueOf(scoringCategories[5]));

        toakScore.setText(String.valueOf(scoringCategories[6]));
        foakScore.setText(String.valueOf(scoringCategories[7]));
        fullScore.setText(String.valueOf(scoringCategories[8]));
        smallScore.setText(String.valueOf(scoringCategories[9]));
        largeScore.setText(String.valueOf(scoringCategories[10]));
        fiveoakScore.setText(String.valueOf(scoringCategories[11]));
        chanceScore.setText(String.valueOf(scoringCategories[12]));
    }
}
