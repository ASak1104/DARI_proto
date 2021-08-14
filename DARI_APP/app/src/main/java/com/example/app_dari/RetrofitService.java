package com.example.app_dari;


import com.example.app_dari.Chat.ChatResponse;
import com.example.app_dari.Login.LoginRequest;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("/api/messenger/channel")
    Call<ChatResponse> post_ChatList(@Header("authorization") String token , @Field("otherUserId") String id);

    @Multipart
    @POST("user/image")
    Call<ProfileUpRq> setloadImage(@Header("authorization") String token, @Part MultipartBody.Part image);

    @Multipart
    @PUT("user/image")
    Call<ProfileUpRq> uploadImage(@Header("authorization") String token, @Part MultipartBody.Part image);

    @GET("user/{post}/supporter")
    Call<Supporters> getSupporter(@Path("post") String post);

    @GET("user/profile")
    Call<GetProfile> getProfile(@Header("authorization") String token);

    @GET("api/map")
    Call<MapData> getMapData(@Header("authorization") String token);

    @GET("api/main")
    Call<MapData> getMainData(@Header("authorization") String token);

    @FormUrlEncoded
    @POST("user/profile/")
    Call<ProfileUpRq> postData(@Header("authorization") String token, @Field("introduce") String introduce,
                               @Field("interests") String[] interests);

    @POST("user/location/") //위치 업데이트
    Call<ProfileUpRq> postData(@Header("authorization") String token, @Body JsonObject jsonObject);


    @PUT("user/location/") //위치 업데이트
    Call<ProfileUpRq> putData(@Header("authorization") String token, @Body JsonObject jsonObject);


    @FormUrlEncoded
    @PUT("user/profile/") //정보 업데이트
    Call<ProfileUpRq> putData(@Header("authorization") String token, @Field("name") String name,
                              @Field("introduce") String introduce, @Field("interests") String[] interests);

    @GET("api/auth/sign-out")
    Call<LoginRequest> getlogout(@Header("authorization") String token);

    @FormUrlEncoded
    @POST("user/booking/")
    Call<ProfileUpRq> bookingData(@Header("authorization") String token, @Field("suppoterId") String supporterId,
                               @Field("userId") String userId, @Field("otherUserId") String otherUserId);


}
