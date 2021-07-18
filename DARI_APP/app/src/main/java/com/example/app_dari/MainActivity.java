package com.example.app_dari;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);
            ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
            ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);
            ImageButton btn_notify = (ImageButton)findViewById(R.id.btn_notify);

            btn_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Map_Activity.class);

                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });
            btn_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Profile_Activity.class);

                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                });
            btn_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Chat_Activity.class);

                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                });
            btn_notify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Notify_Activity.class);

                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                });





        }


}
