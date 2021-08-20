package com.example.app_dari.Chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.app_dari.R;

public class Image extends AppCompatActivity {

    private String channel_id;
    private String otheruser;
    private String image;
    private int position;
    ImageView imageView;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        imageView = (ImageView)findViewById(R.id.clicked_img);
        back = (ImageButton)findViewById(R.id.image_back);

        Intent intent = getIntent();
        channel_id = intent.getExtras().getString("channel_id");
        otheruser = intent.getExtras().getString("otheruser");
        position = intent.getExtras().getInt("position");
        image = intent.getExtras().getString("image");


        GlideUrl glideUrl = new GlideUrl("http://dari-app.kro.kr/api/messenger/image/"+image , new LazyHeaders.Builder()
                .addHeader("authorization", getPreferenceString("token"))
                .build());
        Glide.with(getApplicationContext())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(glideUrl)
                .centerCrop()
                .into(imageView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Image.this, Chat_Activity.class);
                intent.putExtra("channel_id",channel_id);
                intent.putExtra("otheruser",otheruser);
                intent.putExtra("position",position);
                startActivity(intent);
                Image.this.finish();
            }
        });

}
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("Tfile", MODE_PRIVATE);
        return pref.getString(key, "");
    }


}
