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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        UserStatic.id=intent.getExtras().getString("myId");
        UserStatic.interests=intent.getExtras().getStringArray("interests");

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

                HashMap<String, Object> input = new HashMap<>();
                input.put("introduce",UserStatic.introduce);
                input.put("interests",UserStatic.interests);
                retrofitService.postData(input).enqueue(new Callback<ProfileUpRq>() {
                    @Override
                    public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                        if(response.isSuccessful()) {
                            ProfileUpRq profileUpRq = response.body();
                            Toast.makeText(getApplicationContext(),"프로필 업데이트 성공!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileUpRq> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                SetProfile.this.finish();
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
        //post로 서버에 보내기
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/").
                addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        HashMap<String, Object> input = new HashMap<>();
        input.put("latitude",UserStatic.latitude);
        input.put("longitude",UserStatic.longitude);
        retrofitService.postData(input).enqueue(new Callback<ProfileUpRq>() {
            @Override
            public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                if(response.isSuccessful()) {
                    ProfileUpRq profileUpRq = response.body();
                    Toast.makeText(getApplicationContext(),"동네설정 완료!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileUpRq> call, Throwable t) {

            }
        });

        //좌표->주소 변환
        Geocoder g = new Geocoder(getApplicationContext());
        List<Address> address=null;
        try {
            address = g.getFromLocation(UserStatic.latitude, UserStatic.longitude, 10);
            setlocation.setText(address.get(3).getAddressLine(0));
        } catch (Exception e){}
    }

    public void startLocalService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null) {
                UserStatic.latitude = location.getLatitude();
                UserStatic.longitude = location.getLongitude();
            }
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }
}