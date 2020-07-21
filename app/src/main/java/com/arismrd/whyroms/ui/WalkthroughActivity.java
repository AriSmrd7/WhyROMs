package com.arismrd.whyroms.ui;

import android.content.Intent;
import android.os.Bundle;

import com.arismrd.whyroms.R;
import com.arismrd.whyroms.utils.PrefHelper;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class WalkthroughActivity extends FancyWalkthroughActivity {
    private PrefHelper prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefHelper(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Temukan Custom ROM", "Pilihan Custom ROM terbaik untuk ponsel Xiaomi.", R.drawable.research);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Codename Xiaomi", "Memudahkan pemula untuk melihat codename ponselnya.", R.drawable.phone);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Tutorial Video", "Kumpulan tutorial seputar dunia oprek Android yang dapat ditonton langsung.", R.drawable.presentation);

        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        fancywalkthroughCard2.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        fancywalkthroughCard3.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("Mulai Sekarang");
        showNavigationControls(true);
        setColorBackground(R.color.bgui);
        //setImageBackground(R.drawable.restaurant);
        setInactiveIndicatorColor(R.color.grayicon);
        setActiveIndicatorColor(R.color.dodgerblue);
        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        launchHomeScreen();
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WalkthroughActivity.this, MainActivity.class));
        finish();
    }
}