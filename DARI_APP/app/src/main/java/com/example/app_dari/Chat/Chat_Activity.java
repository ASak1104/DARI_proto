package com.example.app_dari.Chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.app_dari.RetrofitClient;
import com.google.gson.Gson;

public class Chat_Activity extends AppCompatActivity {

    private Socket mSocket;
    private Gson gson = new Gson();
    private ImageButton send;
    private String myId = "chatapp";
    private String username = "chatapp";
    private String channel_id;
    private EditText send_text;
    private ImageButton left;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ChatData> mDataset;
    private RetrofitClient retrofitClient;
    private com.example.app_dari.initMyApi initMyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mDataset = new ArrayList<>();
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();
        recyclerView = (RecyclerView)findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        channel_id = intent.getExtras().getString("channel_id");


        initMyApi.get_Chat(getPreferenceString("token"),channel_id).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if(response.isSuccessful()){
                    ArrayList<MessageData> messageData = response.body().getMessageData();
                    int size = messageData.size();
                    for(int i =0 ; i <size ; i++){
                        MessageData data = messageData.get(i);
                        Log.d("message", data.getUserName());
                        if(data.getUserName().equals(username)){
                            mDataset.add(new ChatData(data.getUserName(), data.getContent(), data.getCreatedAt().substring(11,16), "Right"));
                        }
                        else {
                            mDataset.add(new ChatData(data.getUserName(), data.getContent(), data.getCreatedAt().substring(11,16), "Left"));
                        }
                    }
                    chatAdapter = new ChatAdapter(mDataset);
                    recyclerView.setAdapter(chatAdapter);
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Log.d("message","fail");
            }
        });



        send = (ImageButton)findViewById(R.id.send_btn);
        send_text = (EditText)findViewById(R.id.content_edit);
        left = (ImageButton)findViewById(R.id.left);
        ImageButton meet = (ImageButton)findViewById(R.id.meeting);


        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alt = new AlertDialog(Chat_Activity.this);
                alt.show();
            }
        });

    }
    private void init(){
        try{
            IO.Options options = new IO.Options();
            options.transports = new String[] { WebSocket.NAME, Polling.NAME};
            options.path ="/socket.io";
            options.query = getPreferenceString("token");
            mSocket = IO.socket("http://dari-app.kro.kr/channel",options);
            Log.d("SOCKET", "Connection success : " + mSocket.id());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        mSocket.emit("enter", channel_id);

        mSocket.on("join" ,(data) -> {
           Log.d("join",data.toString());
        });

        send.setOnClickListener(v -> sendMessage());
        left.setOnClickListener(v -> onDestroy());

        mSocket.on("update", args -> {
            MessageData data = gson.fromJson(args[0].toString(), MessageData.class);
            addChat(data);
        });

    }
    private void sendMessage(){
        if(!send_text.getText().toString().isEmpty()) {
            mSocket.emit("newMessage", gson.toJson(new SendMessage(
                    channel_id,
                    myId,
                    send_text.getText().toString()
                   )));
            Log.d("Message", new SendMessage(
                    channel_id,
                    myId,
                    send_text.getText().toString()).toString());
              send_text.setText("");
        }
    }

    private String toDate(long currentMiliis) {
        return new SimpleDateFormat("hh:mm a").format(new Date(currentMiliis));
    }
    private void addChat(MessageData data){
            runOnUiThread(()-> {
                if(data.getUserName() ==null){}
                else {
                    if ( data.getUserName().equals(myId)) {
                        mDataset.add(new ChatData(data.getUserName(), data.getContent(), data.getCreatedAt(), "Right"));
                    } else {
                        mDataset.add(new ChatData(data.getUserName(), data.getContent(), data.getCreatedAt(), "Left"));
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
        mSocket.emit("left", gson.toJson(new RoomData(myId, channel_id , username)));
        mSocket.disconnect();
    }
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("Tfile", MODE_PRIVATE);
        return pref.getString(key, "");
    }
}