package com.jaxfire.lawyerly;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;
    ArrayList<Lawyer> lawyers;
    ScreenSlidePageFragment[] fragments;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        lawyers = Lawyer.generateDummyLawyers(context, 18);
        fragments = new ScreenSlidePageFragment[NUM_PAGES];
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
            fragments[position] = ScreenSlidePageFragment.newInstance(position, lawyers);
        }

        return fragments[position];

    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}