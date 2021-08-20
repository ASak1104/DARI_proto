package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_dari.Interest.Interests_Activity_later;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Booking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent intent = getIntent();
        /*TextView othername = findViewById(R.id.otherprofilename);
        othername.setText(intent.getExtras().getString("name"));*/

        //otherUserId, OtherName, day, startTime, endTime

        Button booking = findViewById(R.id.booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //userId, name, otherUserId, OtherName, day, startTime, endTime

                String supporterId = "suppoterid";
                String otherUserId = "otherid";

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/").
                        addConverterFactory(GsonConverterFactory.create()).build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                retrofitService.bookingData(UserStatic.token,supporterId,UserStatic.userId,otherUserId).enqueue(new Callback<ProfileUpRq>() {
                    @Override
                    public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"다리 만남 서비스 예약 성공!",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileUpRq> call, Throwable t) {

                    }
                });
            }
        });
    }
}