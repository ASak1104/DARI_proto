package com.example.app_dari.Signup;

import com.google.gson.annotations.SerializedName;

public class SignupResponse {

    public boolean isResultCode() {
        return resultCode;
    }

    public void setResultCode(boolean resultCode) {
        this.resultCode = resultCode;
    }

    @SerializedName("sign-up")
    public boolean resultCode;
}
