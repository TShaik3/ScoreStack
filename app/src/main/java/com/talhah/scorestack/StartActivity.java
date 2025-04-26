package com.talhah.scorestack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    ImageButton startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.newGameButton);
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, Game.class);
            startActivity(intent);
        });
    }
}
