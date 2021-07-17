package com.example.app_dari;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Interests implements Parcelable {


    public String getInterests_name() {
        return interests_name;
    }

    public int getImgResource() {
        return imgResource;
    }

    private String interests_name;
    private int imgResource;

    public Interests(String interests_name, int imgResource) {
        this.interests_name = interests_name;
        this.imgResource = imgResource;
    }

    protected Interests(Parcel in) {
        interests_name = in.readString();
        imgResource = in.readInt();
    }

    public static final Creator<Interests> CREATOR = new Creator<Interests>() {
        @Override
        public Interests createFromParcel(Parcel source) {
            return new Interests(source);
        }

        @Override
        public Interests[] newArray(int size) {
            return new Interests[size];
        }
    };
    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest,int flags){
        dest.writeString(interests_name);
        dest.writeInt(imgResource);
    }


}
