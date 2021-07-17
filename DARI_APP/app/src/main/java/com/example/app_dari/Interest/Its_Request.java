package com.example.app_dari.Interest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Its_Request {

    public Its_Request(List<String> interests) {
        this.interests = interests;
    }

    @SerializedName("interests")
    public List<String> interests;

}
