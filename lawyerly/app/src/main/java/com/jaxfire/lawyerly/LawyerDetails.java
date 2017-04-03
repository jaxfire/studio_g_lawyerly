package com.jaxfire.lawyerly;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LawyerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lawyer_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.lawyer_details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Toolbar up/back arrow
        LinearLayout backArrow = (LinearLayout) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        // 'Schedule Lawyer' button
        TextView scheduleLawyerBtn = (TextView) findViewById(R.id.schedule_lawyer);
        scheduleLawyerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LawyerDetails.this, "Functionality not implemented", Toast.LENGTH_SHORT).show();
            }
        });

        // Animate the 'Schedule Lawyer' button's gradient background
        TextView animatedTextView = (TextView) findViewById(R.id.schedule_lawyer);
        animatedTextView.setBackgroundResource(R.drawable.gradient_animation);
        AnimationDrawable progressAnimation = (AnimationDrawable) animatedTextView.getBackground();
        progressAnimation.start();

        // Apply a grandient to the Trail Banner's text colour
        TextView trialTextView = (TextView) findViewById(R.id.trial_text) ;
        Shader textShader = new LinearGradient(0, 0, 220, 0, new int[]{Color.argb(255,32,234,144), Color.argb(255,220,255,93)}, null, Shader.TileMode.CLAMP);
        trialTextView.getPaint().setShader(textShader);

    }

    //Required by Calligraphy to apply a default font to this activity
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}