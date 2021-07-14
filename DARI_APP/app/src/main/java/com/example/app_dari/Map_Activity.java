package com.example.app_dari;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Map_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

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

}