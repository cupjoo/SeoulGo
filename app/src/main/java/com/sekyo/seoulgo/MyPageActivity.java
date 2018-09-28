package com.sekyo.seoulgo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sekyo on 2016-08-28.
 */
public class MyPageActivity extends BaseFragmentActivity{
    int numOfStar = 0;
    int numOfMission = 0;
    int getEventNum = 0;
    float aveStarsF = 0;
    final int[] missionsPerEvent = {1,2,4,1,2,4,1,2};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        findViewById(R.id.myPageBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SharedPreferences sp = getSharedPreferences("preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        TextView stars = (TextView)findViewById(R.id.starNumber);
        TextView aveStars = (TextView)findViewById(R.id.averageNumber);
        TextView missions = (TextView)findViewById(R.id.missionNumber);
        TextView events = (TextView)findViewById(R.id.eventNumber);

        for(int i = 0;i < 8;i++){
            int temp = 0;
            for(int j = 0;j < 4;j++){
                if(sp.getInt(i + "" + j, 0) != 0){
                    numOfMission++;
                    numOfStar += sp.getInt(i + "" + j, 0);
                    temp++;
                }
            }
            if(temp == missionsPerEvent[i]){
                getEventNum++;
            }
        }
        if(numOfStar == 0)
            aveStarsF =0;
        else
            aveStarsF = (float)numOfStar/numOfMission;

        stars.setText(Integer.toString(numOfStar));
        aveStars.setText(Integer.toString((int) aveStarsF));
        missions.setText(Integer.toString(numOfMission));
        events.setText(Integer.toString(getEventNum));

    }
}
