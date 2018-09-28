package com.sekyo.seoulgo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by 4F on 2016-10-29.
 */

public class BaseFragmentActivity extends FragmentActivity{
    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));

    }
}