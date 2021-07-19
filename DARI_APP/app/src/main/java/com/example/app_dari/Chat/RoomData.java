package com.example.app_dari.Chat;

public class RoomData {

    public RoomData(String userId, String roomNumber) {
        this.userId = userId;
        this.roomNumber = roomNumber;
    }

    private String userId;

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
