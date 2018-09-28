package com.sekyo.seoulgo;

import android.os.Bundle;
import android.view.View;

/**
 * Created by sekyo on 2016-10-31.
 */
public class InfoOfdeveloper extends BaseFragmentActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_developer);
        findViewById(R.id.infoBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
