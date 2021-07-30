package com.example.app_dari.Chat;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChatResponse {
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    @SerializedName("channel_id")
    private String channel_id;

    public ArrayList<MessageData> getMessageData() {
        return messageData;
    }

    public void setMessageData(ArrayList<MessageData> messageData) {
        this.messageData = messageData;
    }

    @SerializedName("messages")
    private ArrayList<MessageData> messageData;
}
