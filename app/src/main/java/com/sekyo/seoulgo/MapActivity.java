package com.sekyo.seoulgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by sekyo on 2016-08-28.
 */
public class MapActivity extends BaseFragmentActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private GpsInfo gps;
    String[][] missionName = {
            {"연무당 옛터"},
            {"우정총국", "창덕궁"},
            {"경복궁", "독립문", "러시아공사관", "덕수궁"},
            {"환구단"},
            {"덕수궁 중명전", "창덕궁 인정전"},
            {"탑골공원", "덕수궁 함녕전", "서대문 형무소", "제암리 3.1운동 순국기념관"},
            {"경교장"},
            {"창원 3.15 아트 센터", "국립 4.19 민주 묘지"}
    };
    LatLng[][] missionPoint = {
            {new LatLng(37.747764, 126.476468)},
            {new LatLng(37.574540, 126.982673), new LatLng(37.579432, 126.991041)},
            {new LatLng(37.579616, 126.97), new LatLng(37.572394, 126.959527), new LatLng(37.568256, 126.971469), new LatLng(37.565804, 126.97)},
            {new LatLng(37.565062, 126.979692)},
            {new LatLng(37.566591, 126.972525), new LatLng(37.579472, 126.991026)},
            {new LatLng(37.571148, 126.988327), new LatLng(37.565899, 126.975915), new LatLng(37.574271, 126.956069), new LatLng(37.126282, 126.891573)},
            {new LatLng(37.568173, 126.968024)},
            {new LatLng(35.225929, 128.577125), new LatLng(37.648510, 127.007639)}
    };
    Marker[][] markers = {
            {null},
            {null, null},
            {null, null, null, null},
            {null},
            {null, null},
            {null, null, null, null},
            {null},
            {null, null},
    };

    static final LatLng SEOUL = new LatLng(37.56, 126.97);


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        findViewById(R.id.mapBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    protected void onRestart() {
        super.onRestart();
        markerSetting();
    }

    private void markerSetting() {
        SharedPreferences sp = getSharedPreferences("preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        googleMap.clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < missionName[i].length; j++) {
                markers[i][j] = null;
                if (sp.getInt(i + "" + j, 0) != 0) {
                    markers[i][j] = googleMap.addMarker(new MarkerOptions().position(missionPoint[i][j]).title(missionName[i][j] + " 탐방완료!"));
                    markers[i][j].setIcon(BitmapDescriptorFactory.fromResource(R.drawable.flw));
                } else {
                    if (j == 0) {
                        if (missionName[i].length > 1) {
                            if (sp.getInt(i + "" + (j + 1), 0) == 0) {
                                markers[i][j] = googleMap.addMarker(new MarkerOptions().position(missionPoint[i][j]).title(missionName[i][j]));
                                markers[i][j].setIcon(BitmapDescriptorFactory.fromResource(R.drawable.new_icon));
                            }
                        } else {
                            markers[i][j] = googleMap.addMarker(new MarkerOptions().position(missionPoint[i][j]).title(missionName[i][j]));
                            markers[i][j].setIcon(BitmapDescriptorFactory.fromResource(R.drawable.new_icon));
                        }
                    } else {
                        if (sp.getInt(i + "" + (j - 1), 0) != 0) {
                            markers[i][j] = googleMap.addMarker(new MarkerOptions().position(missionPoint[i][j]).title(missionName[i][j]));
                            markers[i][j].setIcon(BitmapDescriptorFactory.fromResource(R.drawable.new_icon));
                        }
                    }
                }
            }
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                SharedPreferences sp = getSharedPreferences("preference", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < markers[i].length; j++) {
                        if (markers[i][j] != null) {
                            if (marker.equals(markers[i][j])) {
                                if (sp.getInt(i + "" + j, 0) == 0) {
                                    if (distFrom(marker.getPosition().latitude, marker.getPosition().longitude, gps.getLatitude(), gps.getLongitude()) > 20) {
                                        Toast.makeText(getApplicationContext(), "목적지에 도착하지 않았습니다.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "목적지에 도착했습니다.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), IntroInMap.class);
                                        intent.putExtra("targetEvent", i);
                                        intent.putExtra("targetMission", j);
                                        startActivity(intent);
                                        markerSetting();
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        gps = new GpsInfo(MapActivity.this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 14f));
        map.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                setMyLocation();
                return false;
            }
        });
        setMyLocation();
        markerSetting();

    }


    public void setMyLocation() {
        if (!gps.isGetLocation()) {
            gps.getLocation();
            gps.showSettingsAlert();
        }
    }

    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) *
                Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) *
                        Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (double) (earthRadius * c);
        return dist;
    }
}


