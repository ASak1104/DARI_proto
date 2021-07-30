package com.example.app_dari;

import com.example.app_dari.Chat.ChatData;
import com.example.app_dari.Chat.ChatResponse;
import com.example.app_dari.Chat.Chat_List_Activity;
import com.example.app_dari.Chat.Chat_List_Response;
import com.example.app_dari.Chat.MessageData;
import com.example.app_dari.Interest.Its_Response;
import com.example.app_dari.Interest.Its_Request;
import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.Login.LoginRequest;
import com.example.app_dari.Login.LoginResponse;
import com.example.app_dari.Signup.SignupRequest;
import com.example.app_dari.Signup.SignupResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface initMyApi {


    @POST("/api/auth/sign-in")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @GET("/api/auth/token")
    Call<LoginResponse> getLogin(@Header("authorization") String token);

    @POST("/api/auth/sign-up")
    Call<SignupResponse> getSignupResponse(@Body SignupRequest signupRequest);

    @PUT("/user/{id}/profile/interest")
    Call<Its_Response> getIts_Response(@Path("id") String id , @Body Its_Request its_resquest);

    @GET("/api/messenger/channel")
    Call<Chat_List_Response> get_ChatList(@Header("authorization") String token);

    @FormUrlEncoded
    @POST("/api/messenger/channel")
    Call<ChatResponse> post_ChatList(@Header("authorization") String token ,@Field("otherUserId") String id);

    @FormUrlEncoded
    @POST("/api/messenger/{channel_id}")
    Call<Void> post_Chat(@Header("authorization") String token , @Path("channel_id") String channel_id , @Field("content") String content);

    @GET("/api/messenger/{channel_id}")
    Call<ChatResponse> get_Chat(@Header("authorization") String token , @Path("channel_id") String channel_id);
}
