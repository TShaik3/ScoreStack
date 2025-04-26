package com.talhah.scorestack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading); // your loading screen XML

        // Delay for 2 seconds, then move to StartActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoadingActivity.this, StartActivity.class);
            startActivity(intent);
            finish(); // Close LoadingActivity so user can't go back to it
        }, 2000); // 2000 milliseconds = 2 seconds
    }
}