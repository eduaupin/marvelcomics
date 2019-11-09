package com.example.desafiomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.desafiomarvel.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hander = new Handler();
        hander.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostraMainActivity();
            }
        }, 1000);

    }

    private void mostraMainActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
 }

