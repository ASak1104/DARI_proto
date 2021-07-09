package com.example.app_dari;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/users")
    Call<List<Post>> getData(@Query("id") String id);

    @FormUrlEncoded
    @POST("/users")
    Call<POST> postData(@FieldMap HashMap<String , Object> param);

}
