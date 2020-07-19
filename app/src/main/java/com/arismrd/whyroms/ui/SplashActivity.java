package com.arismrd.whyroms.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.arismrd.whyroms.R;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 01 Juli 2020, 21.00 - 01.28 WIB
 *
 * */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(() -> {
            Intent MainIntent = new Intent(SplashActivity.this, WalkthroughActivity.class);
            startActivity(MainIntent);
        }, SPLASH_TIME_OUT);

    }
}