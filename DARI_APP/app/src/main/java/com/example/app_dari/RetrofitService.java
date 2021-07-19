package com.example.app_dari;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("map/{post}")
    Call<MapData> getPosts(@Path("post") String post);

    @FormUrlEncoded
    @POST("/posts")
    Call<ProfileUpRq> postData(@FieldMap HashMap<String, Object> param);


}
