package com.sekyo.seoulgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MissionActivity extends BaseFragmentActivity {
    private int targetEvent = 0;
    int i = 0;
    protected void onRestart() {
        super.onRestart();
        finish();
        Intent intent = new Intent(getApplicationContext(), MissionActivity.class);
        intent.putExtra("targetEvent",targetEvent);
        startActivity(intent);

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        findViewById(R.id.missionBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        targetEvent = intent.getExtras().getInt("targetEvent");
        SharedPreferences sp = getSharedPreferences("preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        final String[][] missionName = {
                {"연무당 옛터"},
                {"우정총국","창덕궁"},
                {"경복궁","독립문","러시아공사관","덕수궁"},
                {"환구단"},
                {"덕수궁 중명전","창덕궁 인정전"},
                {"탑골공원","덕수궁 함녕전","서대문 형무소","제암리 3.1운동 순국기념관"},
                {"경교장"},
                {"창원 3.15 아트 센터","국립 4.19 민주 묘지"}
        };
        LinearLayout[] missionArr = {
                (LinearLayout) findViewById(R.id.misson0Btn),
                (LinearLayout) findViewById(R.id.misson1Btn),
                (LinearLayout) findViewById(R.id.misson2Btn),
                (LinearLayout) findViewById(R.id.misson3Btn)
        };
        ImageView[] imgArr = {
                (ImageView) findViewById(R.id.misson0_img),
                (ImageView) findViewById(R.id.misson1_img),
                (ImageView) findViewById(R.id.misson2_img),
                (ImageView) findViewById(R.id.misson3_img),
        };
        TextView[] textArr = {
                (TextView) findViewById(R.id.misson0_text),
                (TextView) findViewById(R.id.misson1_text),
                (TextView) findViewById(R.id.misson2_text),
                (TextView) findViewById(R.id.misson3_text)
        };
        for (i = 0; i < 4; i++) {
            if (missionName[targetEvent].length <= i) {
                missionArr[i].setVisibility(View.GONE);
            } else {
                String spKey = targetEvent + "" + i;
                if (sp.getInt(spKey, 0) == 0) {
                    imgArr[i].setImageResource(R.drawable.star0);
                    missionArr[i].setBackgroundColor(0xaaaaaaaa);
                } else if (sp.getInt(spKey, 0) == 1) {
                    imgArr[i].setImageResource(R.drawable.star1);
                } else if (sp.getInt(spKey, 0) == 2) {
                    imgArr[i].setImageResource(R.drawable.star2);
                } else if (sp.getInt(spKey, 0) == 3) {
                    imgArr[i].setImageResource(R.drawable.star3);
                } else if (sp.getInt(spKey, 0) == 4) {
                    imgArr[i].setImageResource(R.drawable.star4);
                } else if (sp.getInt(spKey, 0) == 5) {
                    imgArr[i].setImageResource(R.drawable.star5);
                }
                textArr[i].setText(missionName[targetEvent][i]);
            }
        }
        if (sp.getInt((targetEvent + "" + 0), 0) != 0)
            missionArr[0].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MissionExplaneActivity.class);
                    intent.putExtra("targetEvent", targetEvent);
                    intent.putExtra("targetMission", 0);
                    startActivity(intent);
                }
            });
        if (sp.getInt((targetEvent + "" + 1), 0) != 0) {
            missionArr[1].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MissionExplaneActivity.class);
                    intent.putExtra("targetEvent", targetEvent);
                    intent.putExtra("targetMission", 1);
                    startActivity(intent);
                }
            });
        }
        if (sp.getInt((targetEvent + "" + 2), 0) != 0) {
            missionArr[2].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MissionExplaneActivity.class);
                    intent.putExtra("targetEvent", targetEvent);
                    intent.putExtra("targetMission", 2);
                    startActivity(intent);
                }
            });
        }
        if (sp.getInt((targetEvent + "" + 3), 0) != 0) {
            missionArr[3].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MissionExplaneActivity.class);
                    intent.putExtra("targetEvent", targetEvent);
                    intent.putExtra("targetMission", 3);
                    startActivity(intent);
                }
            });
        }

    }
}