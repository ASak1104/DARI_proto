package com.example.app_dari.Chat;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Chat_List_Response {
    public ArrayList<Chat_List_Data> getChat_list() {
        return chat_list;
    }

    public void setChat_list(ArrayList<Chat_List_Data> chat_list) {
        this.chat_list = chat_list;
    }

    @SerializedName("channels")
    ArrayList<Chat_List_Data> chat_list;

}
