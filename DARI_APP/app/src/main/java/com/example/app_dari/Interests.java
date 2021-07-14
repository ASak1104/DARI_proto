package com.example.app_dari;

public class Interests {
    public String getInterests_name() {
        return interests_name;
    }

    public int getImgResource() {
        return imgResource;
    }

    private String interests_name;
    private int imgResource;
    public Interests(String interests_name, int imgResource){
        this.interests_name = interests_name;
        this.imgResource = imgResource;
    }
}
