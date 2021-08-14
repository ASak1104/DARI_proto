package com.example.app_dari.Signup;

import android.app.DownloadManager;

import com.google.gson.annotations.SerializedName;

public class SignupRequest {
    @SerializedName("userId")
    public String inputId;

    @SerializedName("password")
    public String inputPw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @SerializedName("name")
    public String name;



    public String getInputId() {
        return inputId;
    }

    public String getInputPw() {
        return inputPw;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public void setInputPw(String inputPw) {
        this.inputPw = inputPw;
    }

    public SignupRequest(String inputId, String inputPw, String name) {
        this.inputId = inputId;
        this.inputPw = inputPw;
        this.name = name;
    }

}
