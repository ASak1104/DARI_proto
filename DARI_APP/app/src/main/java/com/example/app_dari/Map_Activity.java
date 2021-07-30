package com.example.app_dari;


import android.Manifest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
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


import com.example.app_dari.Chat.Chat_List_Activity;

public class Map_Activity extends AppCompatActivity {

    MapView mapView;
    MarkerEventListener eventListener = new MarkerEventListener();
    MapData mapData;

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

        request();

        //하단 매뉴바
        ImageButton btn_main = (ImageButton)findViewById(R.id.btn_main);
        ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
        ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);

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
                Intent intent = new Intent(Map_Activity.this, Chat_List_Activity.class);
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


    }

    public void InterestMarker(int k){
        MapPOIItem[] mapPo = new MapPOIItem[mapData.interests.get(k).otherUsers.size()];
        int i=0;
        for(OtherUserData otherUserData : mapData.interests.get(k).otherUsers){
            MapPOIItem marker = new MapPOIItem();
            mapPo[i] = marker;
            if(otherUserData!=null) {
                marker.setTag(i + k * 100);
                otherUserData.tag = i + k * 100;
                mapPo[i].setMapPoint(MapPoint.mapPointWithGeoCoord(
                        otherUserData.location.coordinates[1],
                        otherUserData.location.coordinates[0]));
                mapPo[i].setItemName("프로필 보기");
                mapPo[i].setMarkerType(MapPOIItem.MarkerType.BluePin);
                mapPo[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            }
            i++;
        }
        mapView.addPOIItems(mapPo);
    }

    public void interestbtclr(int k){
        for (int i = 0; i < mapData.interests.size(); i++) {
            buttons[i].setBackground(this.getResources().getDrawable(R.drawable.button2));
            buttons[i].setTextColor(Color.BLACK);
        }
        buttons[k].setBackground(this.getResources().getDrawable(R.drawable.button2_001194));
        buttons[k].setTextColor(Color.WHITE);
    }

    public void interest1(View view){
        mapView.removeAllPOIItems();
        InterestMarker(0);
        interestbtclr(0);

    }
    public void interest2(View view){
        mapView.removeAllPOIItems();
        InterestMarker(1);
        interestbtclr(1);
    }
    public void interest3(View view){
        mapView.removeAllPOIItems();
        InterestMarker(2);
        interestbtclr(2);
    }
    public void interest4(View view){
        mapView.removeAllPOIItems();
        InterestMarker(3);
        interestbtclr(3);
    }
    public void interest5(View view){
        mapView.removeAllPOIItems();
        InterestMarker(4);
        interestbtclr(4);
    }

    /*private void Marker(int tag, double latitude, double longitude) {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("프로필 보기");
        marker.setTag(tag);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);
    }*/

    private void Circle3km() {
        MapCircle circle3km = new MapCircle(
                MapPoint.mapPointWithGeoCoord(UserStatic.latitude, UserStatic.longitude), // center, 내위치
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
            for(OtherUserData otherUser : mapData.interests.get(mapPOIItem.getTag()/100).otherUsers){
                if(otherUser.tag==mapPOIItem.getTag()){
                    intent.putExtra("name", otherUser.name);
                    String interests = "";
                    for(String interest: otherUser.interests){
                        interests += "# "+ interest + "  ";
                    }
                    intent.putExtra("interests", interests);
                    intent.putExtra("introduce", otherUser.introduce);
                    intent.putExtra("userId", otherUser.userId);
                    break;
                }
            }

            startActivity(intent);
        }
        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        }
        @Override
        public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        }
    }

    public void request(){//일단 파일로 해보고..retrofit으로 바꿔보고..
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService service1 = retrofit.create(RetrofitService.class);
        Call<MapData> call = service1.getMapData(UserStatic.token);

        call.enqueue(new Callback<MapData>() {
            @Override
            public void onResponse(Call<MapData> call, Response<MapData> response) {

                mapData =response.body();

                for (int i = 0; i < mapData.interests.size(); i++) {
                    buttons[i].setVisibility(View.VISIBLE);
                    buttons[i].setText("# " + mapData.interests.get(i).name);
                }

                mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(UserStatic.latitude, UserStatic.longitude), 4, true);
                mapView.setPOIItemEventListener(eventListener);

                Circle3km();

                InterestMarker(0);
                interestbtclr(0);

            }

            @Override
            public void onFailure(Call<MapData> call, Throwable t) {

            }
        });

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        UserStatic.userId=getPreferenceString("userId");
        UserStatic.token=getPreferenceString("token");

    }

    public void setPreference(String key, String value){
        SharedPreferences pref = getSharedPreferences( "Tfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("Tfile", MODE_PRIVATE);
        return pref.getString(key, "");
    }

}