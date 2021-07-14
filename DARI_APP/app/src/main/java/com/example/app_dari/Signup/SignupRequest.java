package com.example.app_dari.Signup;

import android.app.DownloadManager;

import com.google.gson.annotations.SerializedName;

public class SignupRequest {
    @SerializedName("id")
    public String inputId;

    @SerializedName("password")
    public String inputPw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInputPw2() {
        return inputPw2;
    }

    public void setInputPw2(String inputPw2) {
        this.inputPw2 = inputPw2;
    }

    @SerializedName("name")
    public String name;

    @SerializedName("password2")
    public String inputPw2;

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

    public SignupRequest(String inputId, String inputPw, String name, String inputPw2) {
        this.inputId = inputId;
        this.inputPw = inputPw;
        this.inputPw2 = inputPw2;
        this.name = name;
    }

}
