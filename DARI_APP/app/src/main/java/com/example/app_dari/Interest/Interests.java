package com.example.app_dari.Interest;

import com.example.app_dari.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Interests implements Serializable {


    public String getInterests_name() {
        return interests_name;
    }

    public int getImgResource() {
        return imgResource;
    }

    private String interests_name;
    private int imgResource;
    static public ArrayList<Interests> interests = new ArrayList<>();
    static public Interests a = new Interests("러닝", R.drawable.running);
    static public Interests b = new Interests("게임",R.drawable.game);
    static public Interests c = new Interests("자동차",R.drawable.car);
    static public Interests d = new Interests("빵만들기",R.drawable.baking);
    static public Interests e = new Interests("기차",R.drawable.train);
    static public Interests f = new Interests("식당투어",R.drawable.eat);
    static public Interests g = new Interests("영화",R.drawable.movie);
    static public Interests h = new Interests("자전거",R.drawable.cycle);

    public Interests(String interests_name, int imgResource) {
        this.interests_name = interests_name;
        this.imgResource = imgResource;
    }


}