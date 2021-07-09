package com.example.app_dari;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

        @SerializedName("sign-in")
        public boolean resultCode;

        @SerializedName("name")
        public String name;

        public boolean getResultCode() {
            return resultCode;
        }

        public void setResultCode(boolean resultCode) {
            this.resultCode = resultCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String token) {
            this.name = name;
        }

}
