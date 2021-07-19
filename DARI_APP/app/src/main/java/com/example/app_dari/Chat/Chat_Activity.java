package com.example.app_dari.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_dari.R;

import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import com.google.gson.Gson;

public class Chat_Activity extends AppCompatActivity {

    private Socket mSocket;
    private Gson gson = new Gson();
    private ImageButton send;
    private String userId = "aaa";
    private String roomNumber = "1";
    private EditText send_text;
    private ImageButton left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        send = (ImageButton)findViewById(R.id.send_btn);
        send_text = (EditText)findViewById(R.id.content_edit);
        left = (ImageButton)findViewById(R.id.left);
        init();
    }
    private void init(){
        try{
            mSocket = IO.socket("http://dari-app.kro.kr/");
            Log.d("SOCKET", "Connection success : " + mSocket.id());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        send.setOnClickListener(v -> sendMessage());
        mSocket.connect();
        left.setOnClickListener(v -> onDestroy());


    }
    private void sendMessage(){
        mSocket.emit("newImage", gson.toJson(new MessageData(
                userId,
                roomNumber,
                send_text.getText().toString(),
                System.currentTimeMillis())));
        Log.d("Message", new MessageData(
                userId,
                roomNumber,
                send_text.getText().toString(),
                System.currentTimeMillis()).toString());
    }

    private String toDate(long currentMiliis) {
        return new SimpleDateFormat("hh:mm a").format(new Date(currentMiliis));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.emit("left", gson.toJson(new RoomData(userId, roomNumber)));
        mSocket.disconnect();
    }

}
