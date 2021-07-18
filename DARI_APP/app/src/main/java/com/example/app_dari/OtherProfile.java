package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OtherProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        Intent intent = getIntent();
        TextView othername = findViewById(R.id.otherprofilename);
        othername.setText(intent.getExtras().getString("name"));
        TextView interests = findViewById(R.id.interest);
        interests.setText(intent.getExtras().getString("interests"));
        TextView introduce = findViewById(R.id.introduce2);
        introduce.setText(intent.getExtras().getString("introduce"));

        String id =intent.getExtras().getString("id");

        ImageView img = (ImageView)findViewById(R.id.view);
        if(id.equals("kang12"))
            img.setImageResource(R.drawable.p1);
        else if(id.equals("ASak1104"))
            img.setImageResource(R.drawable.p2);
        else if(id.equals("lim123"))
            img.setImageResource(R.drawable.p3);
        else if(id.equals("kim123"))
            img.setImageResource(R.drawable.p4);

        //채팅시작
        Button startchat = findViewById(R.id.startchat);
        startchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}