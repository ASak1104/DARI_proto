package com.example.app_dari;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("map/{post}")
    Call<UserData> getPosts(@Path("post") String post);
}
