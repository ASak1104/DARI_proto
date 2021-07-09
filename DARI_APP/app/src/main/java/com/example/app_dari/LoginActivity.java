package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class LoginActivity extends AppCompatActivity {
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    HashMap<String, Object> input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dari-app.kro.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitAPI = retrofit.create(RetrofitAPI.class);

        input = new HashMap<>();


        retrofitAPI.getData("aaaa").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    List<Post> data = response.body();
                    Log.d("Test", "성공");
                    Log.d("test" , data.get(0).getName());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                  t.printStackTrace();
            }
        });


        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.put("id", "aaaa");
                input.put("password", "1234");
                input.put("name", "khw");
                retrofitAPI.postData(input).enqueue(new Callback<POST>() {
                    @Override
                    public void onResponse(Call<POST> call, Response<POST> response) {
                        if(response.isSuccessful()){
                            Post data = (Post) response.body();
                            Log.d("test","post성공");
                            Log.d("test",data.getName());
                        }
                    }

                    @Override
                    public void onFailure(Call<POST> call, Throwable t) {
                        Log.d("test","post 실패");

                    }
                });
                Intent to_signup = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(to_signup);

            }
        });




    }
}