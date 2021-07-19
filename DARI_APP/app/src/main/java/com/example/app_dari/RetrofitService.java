package com.example.app_dari;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("api/map/{post}")
    Call<MapData> getPosts(@Path("post") String post);

    @FormUrlEncoded
    @POST("user/{post}/profile/")
    Call<ProfileUpRq> postData(@Path("post") String post, @Field("introduce") String introduce,
                               @Field("interests") String[] interests,
                               @Field("latitude") double latitude,
                               @Field("longitude") double longitude);

    @FormUrlEncoded
    @PUT("user/{post}/location/") //위치 업데이트
    Call<ProfileUpRq> putData(@Path("post") String post, @Field("latitude") double latitude,
                               @Field("longitude") double longitude);

    @FormUrlEncoded
    @PUT("user/{post}/profile/") //정보 업데이트
    Call<ProfileUpRq> putData(@Path("post") String post, @Field("name") String name,
                              @Field("introduce") String introduce, @Field("interests") String[] interests);


}
