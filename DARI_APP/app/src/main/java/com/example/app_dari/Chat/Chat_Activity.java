package com.example.app_dari.Chat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.R;

import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.Polling;
import io.socket.engineio.client.transports.WebSocket;

import com.google.gson.Gson;

public class Chat_Activity extends AppCompatActivity {

    private Socket mSocket;
    private Gson gson = new Gson();
    private ImageButton send;
    private String myId = "user1";
    private String username = "user1";
    private String roomNumber = "1";
    private EditText send_text;
    private Button left;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<ChatData> mDataset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        recyclerView = (RecyclerView)findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        mDataset = new ArrayList<>();
        chatAdapter = new ChatAdapter(mDataset);
        recyclerView.setAdapter(chatAdapter);

        send = (ImageButton)findViewById(R.id.send_btn);
        send_text = (EditText)findViewById(R.id.content_edit);
        left = findViewById(R.id.left);
        Button meet = findViewById(R.id.meeting);

        init();

        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alt = new AlertDialog(Chat_Activity.this);
                alt.show();
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }
    private void init(){
        try{
            IO.Options options = new IO.Options();
            options.transports = new String[] { WebSocket.NAME, Polling.NAME};
            options.path ="/socket.io";
            mSocket = IO.socket("http://dari-app.kro.kr/",options);
            Log.d("SOCKET", "Connection success : " + mSocket.id());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        mSocket.emit("enter", gson.toJson(new RoomData(myId, roomNumber, username)));

        send.setOnClickListener(v -> sendMessage());
        left.setOnClickListener(v -> onDestroy());

        mSocket.on("update", args -> {
            MessageData data = gson.fromJson(args[0].toString(), MessageData.class);
            addChat(data);
        });

    }
    private void sendMessage(){
        if(!send_text.getText().toString().isEmpty()) {
            mSocket.emit("newMessage", gson.toJson(new MessageData(
                    myId,
                    roomNumber,
                    send_text.getText().toString(),
                    System.currentTimeMillis())));
            Log.d("Message", new MessageData(
                    myId,
                    roomNumber,
                    send_text.getText().toString(),
                    System.currentTimeMillis()).toString());
              send_text.setText("");
        }
    }

    private String toDate(long currentMiliis) {
        return new SimpleDateFormat("hh:mm a").format(new Date(currentMiliis));
    }
    private void addChat(MessageData data){
            runOnUiThread(()-> {
                if(data.getFrom() ==null){}
                else {
                    if ( data.getFrom().equals(myId)) {
                        mDataset.add(new ChatData(data.getFrom(), data.getContent(), toDate(data.getSendTime()), "Right"));
                    } else {
                        mDataset.add(new ChatData(data.getFrom(), data.getContent(), toDate(data.getSendTime()), "Left"));
                    }
                }
                chatAdapter = new ChatAdapter(mDataset);
                chatAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
                recyclerView.setAdapter(chatAdapter);
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.emit("left", gson.toJson(new RoomData(myId, roomNumber , username)));
        mSocket.disconnect();
    }
}
