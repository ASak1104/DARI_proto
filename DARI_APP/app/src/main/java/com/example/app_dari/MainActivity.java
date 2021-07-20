package com.example.app_dari;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.MapPoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    MapData mapData;

    Button[] buttons = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);
        ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
        ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);
        ImageButton btn_notify = (ImageButton)findViewById(R.id.btn_notify);

        buttons[0] = findViewById(R.id.interest1);
        buttons[1] = findViewById(R.id.interest2);
        buttons[2] = findViewById(R.id.interest3);
        buttons[3] = findViewById(R.id.interest4);
        buttons[4] = findViewById(R.id.interest5);

        request();

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

    public void interestbtclr(int k){
        for (int i = 0; i < mapData.interests.size(); i++) {
            buttons[i].setBackground(this.getResources().getDrawable(R.drawable.button2));
            buttons[i].setTextColor(Color.BLACK);
        }
        buttons[k].setBackground(this.getResources().getDrawable(R.drawable.button2_001194));
        buttons[k].setTextColor(Color.WHITE);
    }

    public void interest1(View view){
        interestbtclr(0);
        //데이터를 넣어주는..리사이클러뷰
    }
    public void interest2(View view){
        interestbtclr(1);
    }
    public void interest3(View view){
        interestbtclr(2);
    }
    public void interest4(View view){
        interestbtclr(3);
    }
    public void interest5(View view){
        interestbtclr(4);
    }

    public void request(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService service1 = retrofit.create(RetrofitService.class);
        Call<MapData> call = service1.getPosts(UserStatic.userId);

        call.enqueue(new Callback<MapData>() {
            @Override
            public void onResponse(Call<MapData> call, Response<MapData> response) {

                mapData =response.body();

                for (int i = 0; i < mapData.interests.size(); i++) {
                    buttons[i].setVisibility(View.VISIBLE);
                    buttons[i].setText("# " + mapData.interests.get(i).name);
                }

                interestbtclr(0);

            }

            @Override
            public void onFailure(Call<MapData> call, Throwable t) {

            }
        });

    }


}
