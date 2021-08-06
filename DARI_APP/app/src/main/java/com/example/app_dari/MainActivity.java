package com.example.app_dari;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_dari.Chat.ChatResponse;
import com.example.app_dari.Chat.Chat_Activity;
import com.example.app_dari.Chat.Chat_List_Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RetrofitClient retrofitClient;
    private com.example.app_dari.initMyApi initMyApi;
    private String id = "610001f07d81a0521afa8fef";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);
            ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
            ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);
            ImageButton btn_notify = (ImageButton)findViewById(R.id.btn_notify);

            retrofitClient = RetrofitClient.getInstance();
            initMyApi = RetrofitClient.getRetrofitInterface();

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
                    initMyApi.post_ChatList(getPreferenceString("token"),"cococo").enqueue(new Callback<ChatResponse>() {
                        @Override
                        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                            if(response.isSuccessful()){
                                int status = response.body().getStatus();
                                String channel_id = response.body().getChannel_id();

                                if(status ==201){
                                    Intent intent = new Intent(MainActivity.this, Chat_Activity.class);
                                    intent.putExtra("channel_id",channel_id);
                                    startActivity(intent);
                                    MainActivity.this.finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ChatResponse> call, Throwable t) {

                        }
                    });
                }
            });
            btn_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Chat_List_Activity.class);

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
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("Tfile", MODE_PRIVATE);
        return pref.getString(key, "");
    }

}
