package com.example.app_dari.Chat;

public class RoomData {

    public RoomData(String userId, String roomNumber, String username) {
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.username = username;

    }

    private String userId;

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    private String username;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    private String roomNumber;


}
