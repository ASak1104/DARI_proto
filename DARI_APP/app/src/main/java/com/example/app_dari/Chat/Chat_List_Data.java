package com.example.app_dari.Chat;

public class Chat_List_Data {

    public String[] getOtherUserIds() {
        return otherUserIds;
    }

    public void setOtherUserIds(String[] otherUserIds) {
        this.otherUserIds = otherUserIds;
    }

    public String[] otherUserIds;


    public String getUserNameTitle() {
        return userNameTitle;
    }

    public Chat_List_Data(String userNameTitle, String _id, String updatedAt, String lastMessage, String[] otherUserIds) {
        this.userNameTitle = userNameTitle;
        this._id = _id;
        this.updatedAt = updatedAt;
        this.lastMessage = lastMessage;
        this.otherUserIds = otherUserIds;
    }

    public void setUserNameTitle(String userNameTitle) {
        this.userNameTitle = userNameTitle;
    }

    private String userNameTitle;



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
    private String _id;

    private String updatedAt;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    private String lastMessage;
}
