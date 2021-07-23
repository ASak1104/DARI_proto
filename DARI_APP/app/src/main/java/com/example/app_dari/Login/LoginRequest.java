package com.example.app_dari.Login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
        @SerializedName("id")
        public String inputId;

        @SerializedName("password")
        public String inputPw;


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

        public LoginRequest(String inputId, String inputPw) {
            this.inputId = inputId;
            this.inputPw = inputPw;
        }
}
