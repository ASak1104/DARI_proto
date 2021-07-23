package com.example.app_dari;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile_Activity extends AppCompatActivity {



    TextView myname;
    TextView myinterests;
    TextView myintroduce;
    TextView mylocation;
    ImageView myimage;
    List<Address> address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        mylocation = findViewById(R.id.location);
        Geocoder g = new Geocoder(getApplicationContext());
        try {
            address = g.getFromLocation(UserStatic.latitude, UserStatic.longitude, 10);
            mylocation.setText(address.get(3).getAddressLine(0).substring(5));
            UserStatic.location = address.get(3).getAddressLine(0).substring(5);
        } catch (Exception e) {
        }
        mylocation.setText(UserStatic.location);
        myname = findViewById(R.id.myname);
        myname.setText(UserStatic.name);
        myinterests = findViewById(R.id.myinterest2);
        String interests = "";
        for (String interest : UserStatic.interests) {
            interests += "# " + interest + "  ";
        }
        myinterests.setText(interests);
        myintroduce = findViewById(R.id.myintroduce2);
        myintroduce.setText(UserStatic.introduce);


        myimage = (ImageView) findViewById(R.id.imageView);
        /*Glide.with(this)
                .asBitmap()
                .load("http://dari-app.kro.kr/user/"+UserStatic.userId+"/image")
                .centerCrop()
                .into(myimage);*/

        Button modprofile = findViewById(R.id.modprofile);
        modprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileUpdate.class);
                startActivity(intent);
            }
        });

        Button gpscheck = findViewById(R.id.gpscheck);
        gpscheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView location = findViewById(R.id.location);
                //gps좌표찍기
                startLocalService();
                //post로 서버에 보내기
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/").
                        addConverterFactory(GsonConverterFactory.create()).build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                retrofitService.putData(UserStatic.userId, UserStatic.latitude, UserStatic.longitude).enqueue(new Callback<ProfileUpRq>() {
                    @Override
                    public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                        if (response.isSuccessful()) {
                            ProfileUpRq profileUpRq = response.body();
                            Toast.makeText(getApplicationContext(), "동네인증 완료!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileUpRq> call, Throwable t) {

                    }
                });

                //좌표->주소 변환
                Geocoder g = new Geocoder(getApplicationContext());
                try {
                    address = g.getFromLocation(UserStatic.latitude, UserStatic.longitude, 10);
                    location.setText(address.get(3).getAddressLine(0).substring(5));
                    UserStatic.location = address.get(3).getAddressLine(0).substring(5);
                } catch (Exception e) {
                }
            }
        });


        ImageButton btn_main = (ImageButton) findViewById(R.id.btn_main);
        ImageButton btn_chat = (ImageButton) findViewById(R.id.btn_chat);
        ImageButton btn_notify = (ImageButton) findViewById(R.id.btn_notify);
        ImageButton btn_map = (ImageButton) findViewById(R.id.btn_map);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Activity.this, MainActivity.class);
                startActivity(intent);
                Profile_Activity.this.finish();
            }
        });
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Activity.this, Map_Activity.class);
                startActivity(intent);
                Profile_Activity.this.finish();
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Activity.this, Chat_Activity.class);
                startActivity(intent);
                Profile_Activity.this.finish();
            }
        });
        btn_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Activity.this, Notify_Activity.class);
                startActivity(intent);
                Profile_Activity.this.finish();
            }
        });
    }

    public void startLocalService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                UserStatic.latitude = location.getLatitude();
                UserStatic.longitude = location.getLongitude();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        myname = findViewById(R.id.myname);
        myname.setText(UserStatic.name);
        myinterests = findViewById(R.id.myinterest2);
        String interests="";
        for(String interest: UserStatic.interests) {
            interests += "# " + interest + "  ";
        }
        myinterests.setText(interests);
        myintroduce = findViewById(R.id.myintroduce2);
        myintroduce.setText(UserStatic.introduce);


        Glide.with(this)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .load("http://dari-app.kro.kr/user/"+UserStatic.userId+"/image")
                .into(myimage);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UserStatic.userId=getPreferenceString("userId");

    }
    @Override
    protected void onPause(){
        super.onPause();
        setPreference("userId", UserStatic.userId);

    }
    public void setPreference(String key, String value){
        try{
            FileOutputStream fos = openFileOutput("myFile.dat", MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(value);
            dos.flush();
            dos.close();
        }catch (Exception e){}


    }
    public String getPreferenceString(String key) {
        String data2 = new String();
        try{
            FileInputStream fis = openFileInput("myFile.dat");
            DataInputStream dis = new DataInputStream(fis);
            data2= dis.readUTF();
            dis.close();
        }catch (Exception e){}
        return data2;
    }
}
