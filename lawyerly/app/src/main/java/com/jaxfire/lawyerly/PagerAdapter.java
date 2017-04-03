package com.jaxfire.lawyerly;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;
    ArrayList<Lawyer> lawyers;
    ScreenSlidePageFragment[] fragments;
    MainActivity mainActivity;

    public PagerAdapter(FragmentManager fm, MainActivity mainActivity) {
        super(fm);
        lawyers = Lawyer.generateTemplateLayers(mainActivity, 18);
        fragments = new ScreenSlidePageFragment[NUM_PAGES];
        this.mainActivity = mainActivity;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";

        switch (position){
            case 0:
                title = "FEATURED (5)";
                break;
            case 1:
                title = "ALL (18)";
                break;
            case 2:
                title = "FAVS (1)";
                break;
        }

        return title;
    }

    @Override
    public Fragment getItem(int position) {

        if (fragments[position] == null){
            fragments[position] = ScreenSlidePageFragment.newInstance(position, lawyers, mainActivity);
        }

        return fragments[position];

    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}
