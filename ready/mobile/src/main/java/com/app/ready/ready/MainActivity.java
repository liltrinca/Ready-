package com.app.ready.ready;


import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    static int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.bmvHome, R.drawable.img_home, R.color.colorPrimaryDark);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bmvPromo, R.drawable.img_promo, R.color.colorPrimaryDark);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.bmvProfile, R.drawable.img_profile, R.color.colorPrimaryDark);

        bottomNavigation = (AHBottomNavigation)findViewById(R.id.bottom_navigation);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#000000"));

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#0BACDB"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

// Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        HomeFragment initFragment = new HomeFragment();
        FragmentTransaction ft0 = getSupportFragmentManager().beginTransaction();
        ft0.replace(R.id.frame, initFragment);
        ft0.commit();

// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        currentItem = 0;
                        HomeFragment homeFragment = new HomeFragment();
                        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.frame, homeFragment);
                        ft1.commit();
                        return true;
                    case 1:
                        currentItem = 1;
                        PromoFragment promoFragment = new PromoFragment();
                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.frame, promoFragment);
                        ft2.commit();
                        return true;
                    case 2:
                        currentItem = 2;
                        ProfileLoginFragment profileFragment = new ProfileLoginFragment();
                        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                        ft3.replace(R.id.frame, profileFragment);
                        ft3.commit();
                        return true;
                }
                return false;
            }
        });
    }
}
