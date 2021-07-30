package com.example.app_dari.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_dari.MainActivity;
import com.example.app_dari.Map_Activity;
import com.example.app_dari.Notify_Activity;
import com.example.app_dari.Profile_Activity;
import com.example.app_dari.R;

public class Chat_List_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        ImageButton btn_main = (ImageButton)findViewById(R.id.btn_main);
        ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);
        ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_List_Activity.this, MainActivity.class);
                startActivity(intent);
                Chat_List_Activity.this.finish();
            }
        });
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_List_Activity.this, Map_Activity.class);
                startActivity(intent);
                Chat_List_Activity.this.finish();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_List_Activity.this, Profile_Activity.class);
                startActivity(intent);
                Chat_List_Activity.this.finish();
            }
        });
    }

}