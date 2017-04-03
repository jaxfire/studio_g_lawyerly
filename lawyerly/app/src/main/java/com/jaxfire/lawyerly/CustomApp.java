package com.jaxfire.lawyerly;


import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class CustomApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Calligraphy for default font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/blogger-sans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

}