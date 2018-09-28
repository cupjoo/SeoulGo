package com.sekyo.seoulgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends BaseFragmentActivity {
    private RelativeLayout mainScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (sp.getBoolean("isFirst", true)) {
            editor.putBoolean("isFirst", false);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    editor.putInt(i + "" + j, 0);
                }
            }
        }

        editor.commit();
        mainScene = (RelativeLayout) findViewById(R.id.logoScene);
        mainScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
