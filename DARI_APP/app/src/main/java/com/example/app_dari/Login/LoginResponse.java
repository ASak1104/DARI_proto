package com.example.app_dari.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

        @SerializedName("status")
        public int resultCode;

        @SerializedName("name")
        public String name;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @SerializedName("token")
        public String token;

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String token) {
            this.name = name;
        }

        public boolean isResultCode() {
        return resultCode2;
    }

        public void setResultCode(boolean resultCode) {
        this.resultCode2 = resultCode;
    }

        @SerializedName("isSignedUp")
        public boolean resultCode2;
}
