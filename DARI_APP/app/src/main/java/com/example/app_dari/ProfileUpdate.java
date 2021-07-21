package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileUpdate extends AppCompatActivity {

    EditText myname;
    EditText myintroduce;
    TextView myinterests;
    TextView mylocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        myname = findViewById(R.id.mynameup);
        myname.setText(UserStatic.name);
        mylocation = findViewById(R.id.location);
        mylocation.setText(UserStatic.location);
        myintroduce = findViewById(R.id.myintroduce2up);
        myintroduce.setText(UserStatic.introduce);
        myintroduce = findViewById(R.id.myintroduce2up);
        myintroduce.setText(UserStatic.introduce);
        myinterests = findViewById(R.id.myinterest2up);
        String interests="";
        for(String interest: UserStatic.interests) {
            interests += "# " + interest + "  ";
        }
        myinterests.setText(interests);
        myinterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //관심사 누르면 화면 전환..
                //Intent intent = new Intent(getApplicationContext(), Interests_Activity_later.class);
                //startActivity(intent);
            }
        });

        Button button = findViewById(R.id.modprofileup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserStatic.name = myname.getText().toString();
                UserStatic.introduce = myintroduce.getText().toString();
                //관심사 추가..
                //서버로 보내버리기 post
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/").
                        addConverterFactory(GsonConverterFactory.create()).build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                retrofitService.putData(UserStatic.userId,UserStatic.name,UserStatic.introduce,UserStatic.interests).enqueue(new Callback<ProfileUpRq>() {
                    @Override
                    public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                        if(response.isSuccessful()) {
                            ProfileUpRq profileUpRq = response.body();
                            Toast.makeText(getApplicationContext(),"프로필 업데이트 성공!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileUpRq> call, Throwable t) {

                    }
                });

                ProfileUpdate.this.finish();
            }
        });

    }
}