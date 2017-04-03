package com.jaxfire.lawyerly;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ArrayList<TextView> tabScrollerTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        //Prevents the recyclerview from scrolling once its y position is equal to that of the tabslider view
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        //Fixes toolbar visual bug on recyclerview fling
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset <= -100) {
                    ViewCompat.setElevation(appBarLayout, -100);
                }
            }
        });*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialize the ViewPager and set an adapter
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(pagerAdapter);

        //Called when user swipes to a new page
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                styleTitles(position);

                //AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        //Customise the sliding tab strip
        //tabs.setDividerColor(R.color.white);
        tabs.setDividerColorResource(R.color.white);
        tabs.setUnderlineColor(R.color.transparent);
        tabs.setUnderlineHeight(0);
        //TODO Allow for gradients
        tabs.setIndicatorColorResource(R.color.gradient_yellow);
        //tabs.setIndicatorColor(R.color.gradient_green);
        tabs.setIndicatorHeight(7);
        tabs.setShouldExpand(true);
        tabs.setAllCaps(true);
        tabs.setTabPaddingLeftRight(18);
        tabs.setTextColor(R.color.title_grey);

        tabs.setViewPager(pager);

        //Get references to the TextViews inside the tabScroller
        LinearLayout tabsLayout = (LinearLayout) tabs.getChildAt(0);

        tabScrollerTitles = new ArrayList<>(3);

        for (int i = 0; i < 3; i++) {
            TextView textView = (TextView) tabsLayout.getChildAt(i);
            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/blogger-sans.ttf");
            textView.setTypeface(face);
            //TODO Update the text size for the pixel
            textView.setTextSize(15);
            tabScrollerTitles.add(textView);
        }

        styleTitles(0);

        LinearLayout footerNav = (LinearLayout) findViewById(R.id.footer);
        footerNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Functionality not implemented", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void styleTitles(int position) {

        for (int i = 0; i < 3; i++) {

            TextView selectedTitle = tabScrollerTitles.get(i);

            if (i == position) {
                selectedTitle.setTextColor(ContextCompat.getColor(this, R.color.black));
            } else {

                //Reset the unselected titles to a light grey
                selectedTitle.setTextColor(ContextCompat.getColor(this, R.color.light_grey));

            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Functionality not implemented", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //Required by Calligraphy for default font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}