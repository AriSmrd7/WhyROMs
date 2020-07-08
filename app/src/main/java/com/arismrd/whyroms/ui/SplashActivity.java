package com.arismrd.whyroms.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.arismrd.whyroms.R;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 01 Juli 2020, 21.00 - 01.28 WIB
 *
 * */

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent MainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(MainIntent);
            }
        }, SPLASH_TIME_OUT);

    }
}