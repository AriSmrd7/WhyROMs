package com.arismrd.whyroms.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.arismrd.whyroms.R;

import java.util.Objects;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * UpdateCoding : -
 *
 * */
public class AboutActivity extends BaseActivity {

    Toolbar toolbar;

    @Override
    public int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigaton_about;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }
}