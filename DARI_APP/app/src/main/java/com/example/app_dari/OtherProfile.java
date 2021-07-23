package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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

        String id =intent.getExtras().getString("userId");
        ImageView otherimage = findViewById(R.id.view);
        Glide.with(this)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .load("http://dari-app.kro.kr/user/"+id+"/image")
                .centerCrop()
                .into(otherimage);

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