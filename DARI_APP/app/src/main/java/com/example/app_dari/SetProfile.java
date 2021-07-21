package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_dari.Interest.Interests_Activity;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SetProfile extends AppCompatActivity {

    EditText setmyintroduce;
    TextView setlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        Intent intent = getIntent();
        UserStatic.userId=intent.getExtras().getString("myId");
        UserStatic.interests= intent.getStringArrayExtra("interests");

        setlocation = findViewById(R.id.setlocation);
        locationCheck();

        TextView setmyname = findViewById(R.id.setmyname);
        setmyname.setText(UserStatic.name);
        TextView setmyinterests = findViewById(R.id.setmyinterest2);
        String interests="";
        for(String interest: UserStatic.interests) {
            interests += "# " + interest + "  ";
        }
        setmyinterests.setText(interests);
        setmyintroduce = findViewById(R.id.setmyintroduce2);

        Button setprofilebt = findViewById(R.id.setprofilebt);
        setprofilebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserStatic.introduce = setmyintroduce.getText().toString();
                //서버로 보내버리기 post
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/").
                        addConverterFactory(GsonConverterFactory.create()).build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                retrofitService.postData(UserStatic.userId,UserStatic.introduce,UserStatic.interests,
                        UserStatic.latitude,UserStatic.longitude).enqueue(new Callback<ProfileUpRq>() {
                    @Override
                    public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                        if(response.isSuccessful()) {
                            ProfileUpRq profileUpRq = response.body();
                            Toast.makeText(getApplicationContext(),"프로필 설정 성공!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ProfileUpRq> call, Throwable t) {
                        Log.d("error", t.toString());
                    }
                });

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void locationCheck(){
        int permissionChecked = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionChecked != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        //gps좌표찍기
        startLocalService();

        //좌표->주소 변환
        Geocoder g = new Geocoder(getApplicationContext());
        List<Address> address=null;
        try {
            address = g.getFromLocation(UserStatic.latitude, UserStatic.longitude, 10);
            setlocation.setText(address.get(3).getAddressLine(0).substring(5));
            UserStatic.location=address.get(3).getAddressLine(0).substring(5);
        } catch (Exception e){}
    }

    public void startLocalService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null) {
                UserStatic.latitude = location.getLatitude();
                UserStatic.longitude = location.getLongitude();
            }
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }
}