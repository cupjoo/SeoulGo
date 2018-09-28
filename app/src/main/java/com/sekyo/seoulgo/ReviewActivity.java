package com.sekyo.seoulgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * Created by sekyo on 2016-08-28.
 */
public class ReviewActivity extends BaseFragmentActivity{



    private LinearLayout event0;
    private LinearLayout event1;
    private LinearLayout event2;
    private LinearLayout event3;
    private LinearLayout event4;
    private LinearLayout event5;
    private LinearLayout event6;
    private LinearLayout event7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        findViewById(R.id.reviewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        event0 = (LinearLayout)findViewById(R.id.event0);
        event1 = (LinearLayout)findViewById(R.id.event1);
        event2 = (LinearLayout)findViewById(R.id.event2);
        event3 = (LinearLayout)findViewById(R.id.event3);
        event4 = (LinearLayout)findViewById(R.id.event4);
        event5 = (LinearLayout)findViewById(R.id.event5);
        event6 = (LinearLayout)findViewById(R.id.event6);
        event7 = (LinearLayout)findViewById(R.id.event7);

        event0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventIntro.class);
                intent.putExtra("targetEvent",0);
                startActivity(intent);
            }
        });
        event1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventIntro.class);
                intent.putExtra("targetEvent",1);
                startActivity(intent);
            }
        });
        event2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventIntro.class);
                intent.putExtra("targetEvent",2);
                startActivity(intent);
            }
        });
        event3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventIntro.class);
                intent.putExtra("targetEvent",3);
                startActivity(intent);
            }
        });
        event4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventIntro.class);
                intent.putExtra("targetEvent",4);
                startActivity(intent);
            }
        });
        event5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventIntro.class);
                intent.putExtra("targetEvent",5);
                startActivity(intent);
            }
        });
        event6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventIntro.class);
                intent.putExtra("targetEvent",6);
                startActivity(intent);
            }
        });
        event7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventIntro.class);
                intent.putExtra("targetEvent",7);
                startActivity(intent);
            }
        });
    }
}
