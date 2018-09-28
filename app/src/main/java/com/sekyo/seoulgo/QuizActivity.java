package com.sekyo.seoulgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by sekyo on 2016-10-30.
 */
public class QuizActivity extends BaseFragmentActivity {
    private String QUIZ[][] = {
            {"Q1. 강화도 조약은 평등한 조약이었다.\n",
                    "Q2. 일본은 운요호 사건을 구실로 조약을 맺으려 했다.\n",
                    "Q3. 강화도 조약에 우리나라 대표로 참석한 신헌은 정치가 출신으로 외교 경험이 풍부하다.\n",
                    "Q4. 강화도 조약 내용을 바탕으로, 일본인이 조선에서 죄를 지으면 조선에서 처벌할 수 있다.\n",
                    "Q5. 연무당 옛터라고 적힌 비석은 2개의 돌이 비석을 받들고 있다.\n"},
            {"Q1. 반대 세력을 제거하기 위한 장소로 우정국을 선택했다.\n",
                    "Q2. 우정국은 장소가 협소해서 정변을 일으키기 불리했기 때문에 많은 사람들이 반대를 한 장소였다.\n",
                    "Q3. 반대 세력을 제거하기 위해 왕과 왕비를 환경궁으로 피신시켰다\n",
                    "Q4. 4개조 개혁 정강에는 일본 간섭 배제의 내용이 있다\n",
                    "Q5. 우정국 내부에는 최초의 우표를 볼 수 있다\n",
                    "Q1. 민비는 러시아에게 지원 요청을 해서 청나라 군대 1500명이 투입되었다\n",
                    "Q2. 김옥균, 서재필, 박영효 등을 청나라로 망명되었다\n",
                    "Q3. 갑신정변을 군사적으로 도와주기로 한 일본은 끝까지 청군이랑 대립했다\n",
                    "Q4. 김옥균은 창덕궁은 너무 넓어 개화파가 이끄는 소수의 병력으로는 미구에 닥칠 역적들에게서 방어하기 극히 불리하다며 안정될 때까지는 반대한다는 의사를 보였다.\n",
                    "Q5. 창덕궁 금천교 다리 밑에는 해태가 있다\n"},
            {"Q1. 경복궁은 산지에 배치돼 있다.\n",
                    "Q2. 을미사변이 일어나자, 불안감을 느낀 고종은 경복궁을 탈출하려고 했지만, \n",
                    "눈치를 챈 일본은 더욱 엄중한 감시를 하고 있어서 쉽지는 않았다.\n",
                    "Q3. 어느 문을 지나서 갔는지에 대해서는 여러설이 존재하지만, 모든 설의 마지막은 고종이 탄 가마가 광화문 앞을 지나갔다는 것이다..\n",
                    "Q4. 근정전 출입구는 2개이다\n",
                    "Q5. 근정전은 동서남북 4곳을 지켜주는 수호신 주작, 백호, 청룡, 현무가 있다\n",
                    "Q1. 설립 초기 독립협회는 영은문(迎恩門) 자리에 독립문을 세우고 모화관을 독립관으로 개명하여 평화와 부의 상징으로 삼았다.\n",
                    "Q2. 독립협회의 첫 사업은 참정권 운동과 민권 운동으로 백성들이 자기 목소리를 내고, 자기 권리를 주장하게 하는 것이다\n",
                    "Q3. 독립협회 활동은 크게 자주 국권, 자유 민권, 자강 개혁의 세 가지로 나눌 수 있다.\n",
                    "Q4. 독립문 꼭대기에는 ‘문립독’이라는 글자가 새겨져 있는데 이 글자의 오른쪽에만 태극기가 새겨져있다\n",
                    "Q5. 독립문 겉에는 회색 벽돌로 이루어져 있으며, 독립문 안쪽에는 붉은색 벽돌로도 이루어져 있다.\n",
                    "Q1. 고종은 내각총리대신 김홍집을 면직하고 유길준을 체포하도록 명하였다.\n",
                    "Q2. 청나라 때문에 목숨의 위협을 느낀 고종은 러시아 공사관으로 피신했다\n",
                    "Q3. 이범진•이완용 등의 친러 내각이 조직됐다\n",
                    "Q4. 러시아 공사관 외벽에는 시계가 달려있다\n",
                    "Q5. 1950년 한국 전쟁으로 인해 건물의 대부분이 파괴되었지만 외벽 일부와 3층 전망탑은 남아 있다\n",
                    "Q1. 백성들은 고종황제에게 돌아올 것을 요구하였고, 파천한지 약 1년 후 고종은 경복궁으로 환궁했다 \n",
                    "Q2. 러시아 제국은 1896년 5월 니콜라이 2세의 황제 대관식 이후에 청나라와 가까워졌다 \n",
                    "Q3. 러시아 제국은 경원과 경성의 채굴권과 압록강, 두만강 및 울릉도의 채벌권과 같은 각종 이권을 요구하였다.\n",
                    "Q4. 덕수궁 안에는 자격루와 흥천사 범종을 볼 수 있다\n",
                    "Q5. 모든 궁궐의 정문은 남쪽을 향하고 있지만, 덕수궁은 중화문 남쪽에 문이 없다.\n",
                    "\n"},
            {"Q1. 환구단(圜丘壇)은 천자(天子)가 하늘에 제사를 지내는 단(壇)이다.\n",
                    "Q2. 아직까지 환구단은 잘 보존돼있다.\n",
                    "Q3. 대한 제국이 천자의 나라인 일본의 속국이 아니며 대한 제국의 황제가 하늘에 제사를 지냄으로써 완전한 자주독립국임을 선포하였다.\n",
                    "Q4. 환구단은 땅을 상징하기 위해 네모난 형태로 이루어져 있다\n",
                    "Q5. 삼문 앞에는 학문 양을 조각한 것과 용을 상징하는 용두와 계단 윗부분에는 해태가 있다\n"},
            {"Q1. 을사늑약은 일본이 대한제국의 군사권을 박탈하기 위해 체결한 조약이다.\n",
                    "Q2. 이완용, 이근택, 이지용, 박제순, 권중현은 을사늑약에 찬성한 인물로, 이들을 을사오적이라 부른다.\n",
                    "Q3. 고종은 을사늑약을 반대하기 위해 통감부를 설치해 적극적으로 해외에 이 부당함을 알리려 노력했다.\n",
                    "Q4. 중명전은 덕수궁 중앙에 위치하여, 다른 건물들과 조화를 이루고 있다.\n",
                    "Q5. 고종의 집무소인 중명전은 황제의 위엄을 뽐내기 위해 5층 높이의, 벽돌로 지어진 현대식 건물이다.\n",
                    "Q1. 한일 병합 조약은 협약이 맺은 직후 발표되어 전국에서 수많은 시위가 발생하게 되었다.\n",
                    "Q2. 현재 일본은 당시 한일 병합 조약이 강제로 집행된 조약임을 공식적으로 인정하고 있다.\n",
                    "Q3. 한일 병합 조약은 순종과 각 대신들이 수많은 회의를 거쳐 신중하게 맺어진 조약이다.\n",
                    "Q4. 인정전 내부는 전돌 바닥이 아닌 마루로 되어 있고, 현대적인 변화를 거쳐 샹들리에가 설치되어 있다.\n",
                    "Q5. 인정전의 서북쪽에는 계단 앞에 작은 우물이 조각되어 있다.\n"},
            {"Q1. 3.1 운동의 진행 과정은 점화기 – 도시 확산기 – 농촌 확산기 순으로 진행된다.\n",
                    "Q2. 점화기에는 서울에서만 만세 운동이 이루어졌다.\n",
                    "Q3. 정재용은 민족대표 33인과 함께 태화관에 모여서 독립선언문을 낭독했다.\n",
                    "Q4. 탑골공원 입구에 있는 삼일문의 간판은 한글로 작성되어 있다.\n",
                    "Q5. 팔각정의 계단은 5칸이다.\n",
                    "Q1. 순종의 독살로 인해 분노한 사람들은 서울로 올라와 시위에 대거 참여하게 된다.\n",
                    "Q2. 농촌 확산기에는 농민들이 참여하면서 이전 만세운동과 달리 비교적 소극적으로 진행되었다.\n",
                    "Q3. 도시 확산기에는 학생뿐 아니라 노동자들도 시위에 참여했다.\n",
                    "Q4. 함녕전 내에는 전등이 달려 있다\n",
                    "Q5. 함녕전의 지붕 모서리 부분에는 각각 5개의 잡상들이 있다.\n",
                    "Q1. 서대문 형무소는 1907년 한국통감부가 지은 형무소이다.\n",
                    "Q2. 민족대표 33인은 수많은 고문 끝에 모두 출소 직전 사망하게 된다.\n",
                    "Q3. 3.1운동 당시 약 3천명의 조선인이 수감되었다.\n",
                    "Q4. 서대문 형무소 건물 외관에는 2개의 커다란 태극기가 마주보고 있다.\n",
                    "Q5. 정문의 서대문형무소역사관 이라는 간판은 한문으로 작성되어 있다.\n",
                    "Q1. 제암리 학살 사건은 선교사 아라타에 의해 세상에 폭로되었다.\n",
                    "Q2. 조선에 주둔한 지 얼마 안된 일부 군인이 일본인의 희생에 흥분하여 일으킨 ‘우발적인’ 사건이다.\n",
                    "Q3. 이 사건은 당시 사회에 알려지지 않다가 광복 이후 전 세계에 만행이 드러나게 된다.\n",
                    "Q4. 순국 선열들의 혼을 담은 추모비는 크고 작은 돌기둥은 총 25개이다.\n",
                    "Q5. 기념관 내부에 박목월 시인의 제암리의 참살이라는 시가 전시되어 있다.\n",
                    "\n",
                    "Q1. 김구의 주도하에 3.1 운동이 일어나, 독립이 실현되었다.\n",
                    "Q2. 여러 국가에서 대한민국 임시 정부가 세워졌으나, 이들의 지향점은 각각 달라 통합되지 못하였다.\n",
                    "Q3. 임시 정부는 자유 민주주의와 공화정을 기본으로 한 국가 체제를 갖추고, 이승만을 초대 대통령으로 선출했다.\n",
                    "Q4. 경교장 안에는 김구가 암살 당한 상황을 보존해 놓았다\n",
                    "Q5. 경교장 지하실로 내려가면 서거 당시 입었던 김구의 피 묻은 옷을 볼 수 있다\n"},
            {"Q1. 이승만 의원은 부통령이 되기 위해 선거에 개입해 투표 조작을 하였다.\n",
                    "Q2. 제 1 공화국 자유당 정권은 약 12년 이상 장기 집권하였다.\n",
                    "Q3. 당국은 마산 시위자들을 곧바로 체포했지만, 민주적 투쟁임을 인정하여 곧바로 훈방 조치하였다.\n",
                    "Q4. 3.15 아트센터는 공연 외에 자선 행사도 시행하고 있다.\n",
                    "Q5. 3.15 아트센터는 유공자의 고귀한 희생정신을 기리고, 자유, 민주, 정의의 숭고한 3.15 정신을 계승한다.\n",
                    "Q1. 4.19 민주 항쟁은 서울에서 김주열 학생을 선두로 시작되었다.\n",
                    "Q2. 보수적 입장인 각 대학 교수진은 비교적 소극적 태도로 시위에 참여하였다.\n",
                    "Q3. 4.19 민주화 운동은 우리나라 최초의 민주화 항쟁이다.\n",
                    "Q4. 4.19 묘지에는 희생된 이들을 기리는 비석에 송욱의 소리치는 태양이라는 작품이 기재되어 있다.\n",
                    "Q5. 4.19 묘지 중심에는 3개의 기둥으로 이루어진 4월 학생혁명 기념탑이 있다.\n"},
    };
    private int ANSWER[][] = {
            {9, 0, 9, 9, 9},
            {0, 0, 0, 9, 0, 0, 9, 9, 0, 0},
            {9, 0, 0, 9, 0, 0, 0, 0, 9, 0, 0, 9, 0, 9, 0, 9, 9, 0, 0, 0},
            {0, 9, 9, 9, 0},
            {9, 0, 9, 9, 9, 9, 0, 9, 0, 0},
            {0, 9, 9, 0, 0, 9, 9, 0, 0, 9, 0, 9, 0, 0, 9, 9, 9, 9, 9, 0},
            {9, 9, 0, 0, 0},
            {9, 0, 9, 0, 0, 0, 9, 0, 0, 0}
    };
    private int STAR[] = {
            R.drawable.star0,
            R.drawable.star1,
            R.drawable.star2,
            R.drawable.star3,
            R.drawable.star4,
            R.drawable.star5,
    };
    private int targetEvent;
    private int targetMission;
    private TextView text;
    private int ans[] = new int[5];
    private int count = 0;
    private int score = 0;
    private View dialogView;
    Toast toast;
    int mWidthPixels;
    int mHeightPixels;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        findViewById(R.id.quizBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        targetEvent = intent.getExtras().getInt("targetEvent");
        targetMission = intent.getExtras().getInt("targetMission");

        int m = (targetMission) * 5;
        final String question[] = new String[5];

        for (int i = 0; i < 5; i++) {
            question[i] = QUIZ[targetEvent][m];
            ans[i] = ANSWER[targetEvent][m++];
        }

        text = (TextView) findViewById(R.id.quizText);
        text.setText(question[count]);

        Button o = (Button) findViewById(R.id.oButton);
        Button x = (Button) findViewById(R.id.xButton);


        o.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (count < 5) {
                    if (ans[count] == 0) {
                        score++;
                        toast = Toast.makeText(getApplicationContext(), "정답입니다! (" + score + "점)", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (ans[count] == 9) {
                        toast = Toast.makeText(getApplicationContext(), "틀렸습니다...ㅜㅜ (" + score + "점)", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    count++;
                    if (count == 5) {
                        isCount5();
                    }
                    if (count < 5)
                        text.setText(question[count]);
                }
            }
        });
        x.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (count < 5) {
                    if (ans[count] == 9) {
                        score++;
                        toast = Toast.makeText(getApplicationContext(), "정답입니다! (" + score + "점)", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (ans[count] == 0) {
                        toast = Toast.makeText(getApplicationContext(), "틀렸습니다...ㅜㅜ (" + score + "점)", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    count++;
                    if (count == 5) {
                        isCount5();
                    }
                    if (count < 5)
                        text.setText(question[count]);
                }
            }
        });
    }

    private void isCount5() {
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        PopupWindow popupWindow;

        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;


        SharedPreferences sp = getSharedPreferences("preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(targetEvent + "" + targetMission, score);     //점수 저장
        editor.commit();


        System.out.println("why");
        // 상태바와 메뉴바의 크기를 포함해서 재계산
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
        }
        // 상태바와 메뉴바의 크기를 포함
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        }

        LayoutInflater inflater = (LayoutInflater) QuizActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.activity_quiz_score,
                (ViewGroup) findViewById(R.id.quizLayout));

        popupWindow = new PopupWindow(layout, mWidthPixels - 100, mHeightPixels - 700, true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        Button home = (Button) layout.findViewById(R.id.homeButton);

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView text = (TextView) layout.findViewById(R.id.scoreText);
        String settext = score + "점을 획득하셨습니다!";
        text.setText(String.valueOf(settext));

        ImageView star = (ImageView) layout.findViewById(R.id.scoreImage);
        star.setImageResource(STAR[score]);

    }
}