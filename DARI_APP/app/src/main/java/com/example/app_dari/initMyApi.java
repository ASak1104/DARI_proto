package com.example.app_dari;

import com.example.app_dari.Interest.Its_Response;
import com.example.app_dari.Interest.Its_Request;
import com.example.app_dari.Login.LoginRequest;
import com.example.app_dari.Login.LoginResponse;
import com.example.app_dari.Signup.SignupRequest;
import com.example.app_dari.Signup.SignupResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface initMyApi {


    @POST("/auth/sign-in")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @POST("/auth/sign-up")
    Call<SignupResponse> getSignupResponse(@Body SignupRequest signupRequest);

    @POST("/profile/asdfasdfasdf")
    Call<Its_Response> getIts_Response(@Body Its_Request its_resquest);

}
