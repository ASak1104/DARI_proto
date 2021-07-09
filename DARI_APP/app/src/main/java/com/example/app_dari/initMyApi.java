package com.example.app_dari;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface initMyApi {

    @POST("/users/sign-in")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @POST("/users/sign-up")
    Call<SignupResponse> getSignupResponse(@Body SignupRequest signupRequest);

}
