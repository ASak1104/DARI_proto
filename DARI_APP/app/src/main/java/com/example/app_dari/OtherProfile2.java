package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

public class OtherProfile2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile2);

        Intent intent = getIntent();
        TextView othername = findViewById(R.id.otherprofilename);
        othername.setText(intent.getExtras().getString("name"));
        TextView interests = findViewById(R.id.interest);
        interests.setText(intent.getExtras().getString("interests"));
        TextView introduce = findViewById(R.id.introduce2);
        introduce.setText(intent.getExtras().getString("introduce"));

        String id =intent.getExtras().getString("userId");
        ImageView otherimage = findViewById(R.id.view);

        GlideUrl glideUrl = new GlideUrl("http://dari-app.kro.kr/user/image/"+id , new LazyHeaders.Builder()
                .addHeader("authorization",UserStatic.token)
                .build());

        Glide.with(this)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .load(glideUrl)
                .centerCrop()
                .circleCrop()
                .into(otherimage);
    }
}