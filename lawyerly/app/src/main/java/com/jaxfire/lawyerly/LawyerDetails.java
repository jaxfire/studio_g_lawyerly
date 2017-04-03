package com.jaxfire.lawyerly;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LawyerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lawyer_details_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.lawyer_details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayout backArrow = (LinearLayout) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        TextView scheduleLawyerBtn = (TextView) findViewById(R.id.schedule_lawyer);
        scheduleLawyerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LawyerDetails.this, "Functionality not implemented", Toast.LENGTH_SHORT).show();
            }
        });

        TextView animatedTextView = (TextView) findViewById(R.id.schedule_lawyer);
        animatedTextView.setBackgroundResource(R.drawable.gradient_animation);

        AnimationDrawable progressAnimation = (AnimationDrawable) animatedTextView.getBackground();
        progressAnimation.start();

        //TODO apply this gradient to the '1st free consultation available' banner instead
        TextView trialTextView = (TextView) findViewById(R.id.trial_text) ;
        Shader textShader = new LinearGradient(0, 0, 220, 0, new int[]{Color.argb(255,32,234,144), Color.argb(255,220,255,93)}, null, Shader.TileMode.CLAMP);
        trialTextView.getPaint().setShader(textShader);

    }

    //Required by Calligraphy for default font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}