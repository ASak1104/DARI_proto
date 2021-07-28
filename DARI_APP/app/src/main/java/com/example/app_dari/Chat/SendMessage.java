package com.example.app_dari.Chat;

public class SendMessage {
    private String channel_id;

    public SendMessage(String channel_id, String from, String content) {
        this.channel_id = channel_id;
        this.from = from;
        this.content = content;
    }

    private String from;
    private String content;
}
