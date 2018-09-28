package com.sekyo.seoulgo;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by 4F on 2016-10-29.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/BMJUA_ttf.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/BMJUA_ttf.ttf"));
    }
}