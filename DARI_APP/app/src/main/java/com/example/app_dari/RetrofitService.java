package com.example.app_dari;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("map/{post}")
    Call<MapData> getPosts(@Path("post") String post);

    @POST("{post}")
    Call<ProfileUpRq> postData(@Path("post") String post, @FieldMap HashMap<String, Object> param);

    @PUT("{post}")
    Call<ProfileUpRq> putData(@Path("post") String post, @FieldMap HashMap<String, Object> param);


}
