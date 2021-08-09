package com.example.app_dari.Chat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.MainActivity;
import com.example.app_dari.Map_Activity;
import com.example.app_dari.Notify_Activity;
import com.example.app_dari.Profile_Activity;
import com.example.app_dari.R;
import com.example.app_dari.RetrofitClient;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.Polling;
import io.socket.engineio.client.transports.WebSocket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat_List_Activity extends AppCompatActivity {

    private Socket mSocket;
    private Gson gson = new Gson();
    private RetrofitClient retrofitClient;
    private com.example.app_dari.initMyApi initMyApi;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Chat_ListAdapter chat_listAdapter;
    private ArrayList<Chat_List_Data> chat_list;
    private int flag;
    private String myName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();
        recyclerView = (RecyclerView)findViewById(R.id.chat_list_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);




        ImageButton btn_main = (ImageButton)findViewById(R.id.btn_main);
        ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);
        ImageButton btn_notify = (ImageButton)findViewById(R.id.btn_notify);
        ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);




        initMyApi.get_ChatList(getPreferenceString("token")).enqueue(new Callback<Chat_List_Response>() {
            @Override
            public void onResponse(Call<Chat_List_Response> call, Response<Chat_List_Response> response) {
                if(response.isSuccessful()){
                    chat_list = response.body().getChat_list();
                    chat_listAdapter = new Chat_ListAdapter(chat_list,Chat_List_Activity.this);
                    recyclerView.setAdapter(chat_listAdapter);
                    click();
                }
            }

            @Override
            public void onFailure(Call<Chat_List_Response> call, Throwable t) {

            }
        });

        mSocket = SocketHandler.getSocket();

        mSocket.on("newMessage", args -> {
            MessageData data = gson.fromJson(args[0].toString(), MessageData.class);
            Log.d("message","List success");
            flag = 0;
            updateChatList(data);

        });



        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_List_Activity.this, MainActivity.class);
                startActivity(intent);
                Chat_List_Activity.this.finish();
            }
        });
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_List_Activity.this, Map_Activity.class);
                startActivity(intent);
                Chat_List_Activity.this.finish();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_List_Activity.this, Profile_Activity.class);
                startActivity(intent);
                Chat_List_Activity.this.finish();
            }
        });

    }

    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("Tfile", MODE_PRIVATE);
        return pref.getString(key, "");
    }

    private void updateChatList(MessageData data){
        runOnUiThread(()-> {
            int size = chat_list.size();
            for(int i =0 ; i < size; i++) {
                if(chat_list.get(i).get_id().equals(data.getChannel_id())){
                    flag =1;
                    if(data.getUserName().equals(myName))
                    {
                        chat_list.add(0,new Chat_List_Data(chat_list.get(i).getUserNameTitle(),data.getChannel_id(),data.getCreatedAt(),data.getContent()));
                    }
                    else {
                        chat_list.add(0,new Chat_List_Data(data.getUserName(),data.getChannel_id(),data.getCreatedAt(),data.getContent()));
                    }
                    chat_list.remove(i+1);
                    chat_listAdapter = new Chat_ListAdapter(chat_list,Chat_List_Activity.this);
                    recyclerView.setAdapter(chat_listAdapter);
                    click();
                }
            }
            if (flag == 0)
            {
                chat_list.add(0,new Chat_List_Data(data.getUserName(),data.getChannel_id(),data.getCreatedAt(),data.getContent()));
                chat_listAdapter = new Chat_ListAdapter(chat_list,Chat_List_Activity.this);
                recyclerView.setAdapter(chat_listAdapter);
                click();
            }
        });
    }
    private void click(){
        chat_listAdapter.setOnItemClickListener(new Chat_ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                SocketHandler.getSocket().emit("channel", chat_list.get(position).get_id());
                Intent intent = new Intent(Chat_List_Activity.this, Chat_Activity.class);
                intent.putExtra("channel_id",chat_list.get(position).get_id());
                intent.putExtra("otheruser",chat_list.get(position).getUserNameTitle());
                startActivity(intent);
                Chat_List_Activity.this.finish();
            }
        });
    }



}
