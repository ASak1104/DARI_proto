package com.example.app_dari;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_dari.Chat.Chat_List_Activity;

public class Notify_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        ImageButton btn_main = (ImageButton)findViewById(R.id.btn_main);
        ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
        ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);
        ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notify_Activity.this, MainActivity.class);
                startActivity(intent);
                Notify_Activity.this.finish();
            }
        });
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notify_Activity.this, Map_Activity.class);
                startActivity(intent);
                Notify_Activity.this.finish();
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notify_Activity.this, Chat_List_Activity.class);
                startActivity(intent);
                Notify_Activity.this.finish();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notify_Activity.this, Profile_Activity.class);
                startActivity(intent);
                Notify_Activity.this.finish();
            }
        });
    }

}
