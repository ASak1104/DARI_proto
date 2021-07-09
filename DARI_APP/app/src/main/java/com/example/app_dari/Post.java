package com.example.app_dari;

import com.google.gson.annotations.SerializedName;

public class Post {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("password")
    private String password;
}
