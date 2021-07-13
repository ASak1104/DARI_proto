package com.example.app_dari;

import com.example.app_dari.Login.LoginRequest;
import com.example.app_dari.Login.LoginResponse;
import com.example.app_dari.Signup.SignupRequest;
import com.example.app_dari.Signup.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface initMyApi {

    @POST("/users/sign-in")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @POST("/users/sign-up")
    Call<SignupResponse> getSignupResponse(@Body SignupRequest signupRequest);

}
