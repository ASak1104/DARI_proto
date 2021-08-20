package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.app_dari.Chat.ChatResponse;
import com.example.app_dari.Chat.Chat_Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        //채팅시작
        Button startchat = findViewById(R.id.startchat);
        startchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/").
                        addConverterFactory(GsonConverterFactory.create()).build();
                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                retrofitService.post_ChatList(UserStatic.token, id).enqueue(new Callback<ChatResponse>() {
                    @Override
                    public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                        ChatResponse result = response.body();
                        int status = result.getStatus();

                        if (status==201) {
                            Intent intent1 = new Intent(getApplicationContext(), Chat_Activity.class);
                            intent1.putExtra("otheruser",intent.getExtras().getString("name"));
                            intent1.putExtra("channel_id", result.getChannel_id());
                            startActivity(intent1);
                        } else if(status==202){
                            Intent intent1 = new Intent(getApplicationContext(), Chat_Activity.class);
                            intent1.putExtra("otheruser",intent.getExtras().getString("name"));
                            intent1.putExtra("channel_id", result.getChannel_id());
                            startActivity(intent1);
                        }


                        OtherProfile.this.finish();
                    }

                    @Override
                    public void onFailure(Call<ChatResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}