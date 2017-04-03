package com.jaxfire.lawyerly;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private ArrayList<TextView> tabScrollerTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_a_lawyer);

        // Set the Status Bar's colour
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        //Set up the toolbar for custom styling
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialize the ViewPager and set an adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(pagerAdapter);

        //Called when user swipes to a new ViewPager element
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                styleTabScrollerTitles(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // No Action
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // No Action
            }

        });

        initialiseTabScroller();

        styleTabScrollerTitles(0);

        LinearLayout footerNav = (LinearLayout) findViewById(R.id.footer);
        footerNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Functionality not implemented", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initialiseTabScroller(){

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        //Customise the sliding tab-scroller
        tabs.setDividerColorResource(R.color.white);
        tabs.setUnderlineColor(R.color.transparent);
        tabs.setUnderlineHeight(0);
        tabs.setIndicatorColorResource(R.color.gradient_yellow);
        tabs.setIndicatorHeight(7);
        tabs.setShouldExpand(true);
        tabs.setAllCaps(true);
        tabs.setTabPaddingLeftRight(18);
        tabs.setTextColor(R.color.title_grey);

        tabs.setViewPager(pager);

        //Get references to the TextViews inside the tab-scroller
        LinearLayout tabsLayout = (LinearLayout) tabs.getChildAt(0);

        tabScrollerTitles = new ArrayList<>(3);

        //Apply styling to the tab-scroller's TextViews
        for (int i = 0; i < 3; i++) {
            TextView textView = (TextView) tabsLayout.getChildAt(i);
            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/blogger-sans.ttf");
            textView.setTypeface(face);
            textView.setTextSize(15);
            tabScrollerTitles.add(textView);
        }

    }

    //Highlight the tab-scroller title of the currently active ViewPager element
    private void styleTabScrollerTitles(int position) {

        for (int i = 0; i < 3; i++) {

            TextView selectedTitle = tabScrollerTitles.get(i);

            if (i == position) {
                selectedTitle.setTextColor(ContextCompat.getColor(this, R.color.black));
            } else {
                selectedTitle.setTextColor(ContextCompat.getColor(this, R.color.light_grey));

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Functionality not implemented", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    //Required by Calligraphy to apply a default font to this activity
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}