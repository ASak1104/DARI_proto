package com.example.app_dari.Interest;

import com.google.gson.annotations.SerializedName;

public class Its_Response {
    public boolean isResultCode() {
        return resultCode;
    }

    public void setResultCode(boolean resultCode) {
        this.resultCode = resultCode;
    }

    @SerializedName("updated")
    public boolean resultCode;

}
