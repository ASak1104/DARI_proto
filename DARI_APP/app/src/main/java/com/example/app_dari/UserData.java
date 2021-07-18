package com.example.app_dari;

import java.util.ArrayList;

public class UserData {
    String name;
    String id;
    double latitude;
    double longitude;
    String introduce;
    String[] interests;

    ArrayList<OtherUserData> otherUsers = new ArrayList<OtherUserData>();
}
