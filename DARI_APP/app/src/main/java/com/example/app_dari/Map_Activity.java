package com.example.app_dari;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Map_Activity extends AppCompatActivity {

    MapView mapView;
    MarkerEventListener eventListener = new MarkerEventListener();
    static UserData userData;

    ArrayList<ArrayList<Integer>> Categry = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<MapPOIItem>> Markers = new ArrayList<ArrayList<MapPOIItem>>();

    Button[] buttons = new Button[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        int permissionChecked = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionChecked != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        buttons[0] = findViewById(R.id.interest1);
        buttons[1] = findViewById(R.id.interest2);
        buttons[2] = findViewById(R.id.interest3);
        buttons[3] = findViewById(R.id.interest4);
        buttons[4] = findViewById(R.id.interest5);


        mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        request("http://dari-app.kro.kr/map/chan11");

        //하단 매뉴바
        ImageButton btn_main = (ImageButton)findViewById(R.id.btn_main);
        ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
        ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);
        ImageButton btn_notify = (ImageButton)findViewById(R.id.btn_notify);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Activity.this, MainActivity.class);
                startActivity(intent);
                Map_Activity.this.finish();
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Activity.this, Chat_Activity.class);
                startActivity(intent);
                Map_Activity.this.finish();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Activity.this, Profile_Activity.class);
                startActivity(intent);
                Map_Activity.this.finish();
            }
        });
        btn_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Activity.this, Notify_Activity.class);
                startActivity(intent);
                Map_Activity.this.finish();
            }
        });


    }

    public void interest1(View view){
        mapView.removeAllPOIItems();
        Categry.get(0);
        MapPOIItem[] mapPo = new MapPOIItem[Markers.get(0).size()];
        for(int i=0; i<Markers.get(0).size(); i++){
            mapPo[i]=Markers.get(0).get(i);
            mapPo[i].setItemName("프로필 보기");
            mapPo[i].setMarkerType(MapPOIItem.MarkerType.BluePin);
            mapPo[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        }
        mapView.addPOIItems(mapPo);
    }
    public void interest2(View view){
        mapView.removeAllPOIItems();
        Categry.get(1);
        MapPOIItem[] mapPo = new MapPOIItem[Markers.get(1).size()];
        for(int i=0; i<Markers.get(1).size(); i++){
            mapPo[i]=Markers.get(1).get(i);
            mapPo[i].setItemName("프로필 보기");
            mapPo[i].setMarkerType(MapPOIItem.MarkerType.BluePin);
            mapPo[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        }
        mapView.addPOIItems(mapPo);
    }
    public void interest3(View view){
        mapView.removeAllPOIItems();
        Categry.get(2);
        MapPOIItem[] mapPo = new MapPOIItem[Markers.get(2).size()];
        for(int i=0; i<Markers.get(2).size(); i++){
            mapPo[i]=Markers.get(2).get(i);
            mapPo[i].setItemName("프로필 보기");
            mapPo[i].setMarkerType(MapPOIItem.MarkerType.BluePin);
            mapPo[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        }
        mapView.addPOIItems(mapPo);
    }
    public void interest4(View view){
        mapView.removeAllPOIItems();
        Categry.get(3);
        MapPOIItem[] mapPo = new MapPOIItem[Markers.get(3).size()];
        for(int i=0; i<Markers.get(3).size(); i++){
            mapPo[i]=Markers.get(3).get(i);
            mapPo[i].setItemName("프로필 보기");
            mapPo[i].setMarkerType(MapPOIItem.MarkerType.BluePin);
            mapPo[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        }
        mapView.addPOIItems(mapPo);
    }
    public void interest5(View view){
        mapView.removeAllPOIItems();
        Categry.get(4);
        MapPOIItem[] mapPo = new MapPOIItem[Markers.get(4).size()];
        for(int i=0; i<Markers.get(4).size(); i++){
            mapPo[i]=Markers.get(4).get(i);
            mapPo[i].setItemName("프로필 보기");
            mapPo[i].setMarkerType(MapPOIItem.MarkerType.BluePin);
            mapPo[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        }
        mapView.addPOIItems(mapPo);
    }

    private void Marker(int tag, double latitude, double longitude) {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("프로필 보기");
        marker.setTag(tag);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);
    }

    private void Circle3km() {
        MapCircle circle3km = new MapCircle(
                MapPoint.mapPointWithGeoCoord(userData.latitude,userData.longitude), // center, 내위치
                3000, // radius
                Color.argb(64,0, 0, 255), // strokeColor
                Color.argb(24, 0, 0, 255) // fillColor
        );
        circle3km.setTag(0);
        mapView.addCircle(circle3km);
    }

    class MarkerEventListener implements MapView.POIItemEventListener{
        @Override
        public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        }
        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
            Intent intent = new Intent(getApplicationContext(), OtherProfile.class);
            intent.putExtra("name", userData.otherUsers.get(mapPOIItem.getTag()).name);
            intent.putExtra("introduce", userData.otherUsers.get(mapPOIItem.getTag()).introduce);
            String interests = "";
            for(String interest: userData.otherUsers.get(mapPOIItem.getTag()).interests){
                interests += "# "+ interest + "  ";
            }
            intent.putExtra("interests", interests);
            intent.putExtra("id", userData.otherUsers.get(mapPOIItem.getTag()).id);
            startActivity(intent);
        }
        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        }
        @Override
        public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        }
    }

    public void request(String urlStr){//일단 파일로 해보고..retrofit으로 바꿔보고..
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService service1 = retrofit.create(RetrofitService.class);
        Call<UserData> call = service1.getPosts("chan11");

        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {

                userData=response.body();
                Log.d("관심사",userData.name);
                for (int i = 0; i < userData.interests.length; i++) {
                    buttons[i].setVisibility(View.VISIBLE);
                    buttons[i].setText("# " + userData.interests[i]);
                }

                mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(userData.latitude, userData.longitude), 4, true);
                mapView.setPOIItemEventListener(eventListener);

                Circle3km();

                for (int tagi = 0; tagi < userData.otherUsers.size(); tagi++) {
                    userData.otherUsers.get(tagi).tag = tagi;
                    Marker(tagi, userData.otherUsers.get(tagi).latitude, userData.otherUsers.get(tagi).longitude);
                }

                String[] myinterests = userData.interests;
                for (int k = 0; k < userData.interests.length; k++) {
                    ArrayList<Integer> array = new ArrayList<Integer>();
                    ArrayList<MapPOIItem> marker = new ArrayList<MapPOIItem>();
                    for (int i = 0; i < userData.otherUsers.size(); i++) {
                        String[] interests = userData.otherUsers.get(i).interests;
                        if (Arrays.asList(interests).contains(myinterests[k])) {
                            array.add(userData.otherUsers.get(i).tag);
                            MapPOIItem m = new MapPOIItem();
                            m.setTag(userData.otherUsers.get(i).tag);
                            m.setMapPoint(MapPoint.mapPointWithGeoCoord(userData.otherUsers.get(i).latitude,
                                    userData.otherUsers.get(i).longitude));
                            marker.add(m);
                        }
                    }
                    Categry.add(array);
                    Markers.add(marker);
                }

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });

    }

}