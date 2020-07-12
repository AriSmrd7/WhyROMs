package com.arismrd.whyroms.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.arismrd.whyroms.R;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * UpdateCoding : -
 *
 * */

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_rom) {
                startActivity(new Intent(this, MainActivity.class));
            }
            else if (itemId == R.id.navigaton_device) {
                startActivity(new Intent(this, DeviceActivity.class));
            }
            else if (itemId == R.id.navigaton_recovery) {
                startActivity(new Intent(this, RecoveryActivity.class));
            }
            else if (itemId == R.id.navigation_magisk) {
                startActivity(new Intent(this, MagiskActivity.class));
            }
            else if (itemId == R.id.navigaton_about) {
                startActivity(new Intent(this, AboutActivity.class));
            }

            finish();
        }, 300);
        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    protected abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

}
