package com.example.app_dari;


import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Profile_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Intent intent = getIntent();
        TextView myname = findViewById(R.id.myname);
        myname.setText(intent.getExtras().getString("name"));
        TextView myinterests = findViewById(R.id.myinterest2);
        myinterests.setText(intent.getExtras().getString("interests"));
        TextView myintroduce = findViewById(R.id.myintroduce2);
        myintroduce.setText(intent.getExtras().getString("introduce"));

        String id =intent.getExtras().getString("id");

        ImageView img = (ImageView)findViewById(R.id.imageView);
        //img.setImageResource(R.drawable.me);

        Button modprofile = findViewById(R.id.modprofile);
        modprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileUpdate.class);
                startActivity(intent);

            }
        });

        Button gpscheck = findViewById(R.id.gpscheck);
        gpscheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView location = findViewById(R.id.location);
                //gps좌표찍기

                //post로 서버에 보내기

                //좌표->주소 변환
                Geocoder g = new Geocoder(getApplicationContext());
                List<Address> address=null;
                try {
                    address = g.getFromLocation(37.611727784292306, 126.99397368752474, 10);
                    location.setText(address.get(3).getAddressLine(0));
                } catch (Exception e){}
            }
        });



        ImageButton btn_main = (ImageButton)findViewById(R.id.btn_main);
        ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
        ImageButton btn_notify = (ImageButton)findViewById(R.id.btn_notify);
        ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);

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

}
