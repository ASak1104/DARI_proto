package com.example.app_dari.Chat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.MainActivity;
import com.example.app_dari.R;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.Polling;
import io.socket.engineio.client.transports.WebSocket;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.app_dari.RetrofitClient;
import com.example.app_dari.UserStatic;
import com.google.gson.Gson;

public class Chat_Activity extends AppCompatActivity {

    private Socket mSocket;
    private Gson gson = new Gson();
    private ImageButton send;
    private String myId = UserStatic.userId;
    private String myName = UserStatic.name;
    private String channel_id;
    private EditText send_text;
    private Button chat_back;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ChatData> mDataset;
    private RetrofitClient retrofitClient;
    private com.example.app_dari.initMyApi initMyApi;
    private String otheruser;
    TextView other_user;
    private ImageButton image_btn;
    private final int SELECT_IMAGE = 100;
    private int position;


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
        other_user = (TextView)findViewById(R.id.other_user);

        Intent intent = getIntent();
        channel_id = intent.getExtras().getString("channel_id");
        otheruser = intent.getExtras().getString("otheruser");
        position = intent.getExtras().getInt("position");
        other_user.setText(otheruser);


        initMyApi.get_Chat(getPreferenceString("token"),channel_id).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if(response.isSuccessful()){
                    ArrayList<MessageData> messageData = response.body().getMessageData();
                    int size = messageData.size();
                    for(int i =0 ; i <size ; i++){
                        MessageData data = messageData.get(i);
                        if(data.getImage() ==null) {
                            Log.d("image content","not image");
                            if (data.getUserName() == null) {
                            } else {
                                if (data.getUserName().equals(myName) && data.getUserId().equals(myId)) {
                                    mDataset.add(new ChatData(data.getUserName(),data.getUserId(), data.getContent(), data.getCreatedAt().substring(11, 16), "Right"));
                                } else {
                                    mDataset.add(new ChatData(data.getUserName(),data.getUserId(), data.getContent(), data.getCreatedAt().substring(11, 16), "Left"));
                                }
                            }
                        }
                        else {
                            Log.d("image content","image success");
                            if (data.getUserName().equals(myName) && data.getUserId().equals(myId)) {
                                mDataset.add(new ChatData(data.getUserName(),data.getUserId(), data.getImage(), data.getCreatedAt().substring(11, 16), "Right_Image"));
                            } else {
                                mDataset.add(new ChatData(data.getUserName(),data.getUserId(), data.getImage(), data.getCreatedAt().substring(11, 16), "Left_Image"));
                            }

                        }
                    }
                    chatAdapter = new ChatAdapter(mDataset,Chat_Activity.this);
                    if(position == 0){
                        recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
                    }
                    else {

                        recyclerView.scrollToPosition(position-1);
                    }
                    recyclerView.setAdapter(chatAdapter);
                    click();
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Log.d("message","fail");
            }
        });



        send = (ImageButton)findViewById(R.id.send_btn);
        send_text = (EditText)findViewById(R.id.content_edit);
        chat_back = findViewById(R.id.back);
        Button meet = findViewById(R.id.meeting);
        image_btn = (ImageButton)findViewById(R.id.image_btn);


        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent_m = new Intent(getApplicationContext(), SelectMeeting.class);
                startActivity(intent_m);*/
            }
        });

        image_btn.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                }
            }
            Intent imageIntent = new Intent(Intent.ACTION_PICK);
            imageIntent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(imageIntent, SELECT_IMAGE);
        });



        init();

    }
    private void init(){
        mSocket = SocketHandler.getSocket();

        mSocket.on("system" , args-> {
            Log.d("join", "join success!");
        });


        send.setOnClickListener(v -> sendMessage());
        chat_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_Activity.this, Chat_List_Activity.class);
                startActivity(intent);
                Chat_Activity.this.finish();

            }
        });

        mSocket.on("newMessage", args -> {
            MessageData data = gson.fromJson(args[0].toString(), MessageData.class);
            Log.d("message","success");
            addChat(data);

        });

    }
    private void sendMessage(){
        if(!send_text.getText().toString().equals("")) {
            initMyApi.post_Chat(getPreferenceString("token"), channel_id, send_text.getText().toString()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
            send_text.setText("");
        }
    }

    private void addChat(MessageData data){

        runOnUiThread(()-> {
            if(data.getImage() ==null) {

                if (data.getUserName() == null) {
                } else {
                    if (data.getUserName().equals(myName) && data.getUserId().equals(myId)) {
                        mDataset.add(new ChatData(data.getUserName(),data.getUserId(), data.getContent(), data.getCreatedAt().substring(11, 16), "Right"));
                    } else {
                        mDataset.add(new ChatData(data.getUserName(),data.getUserId(), data.getContent(), data.getCreatedAt().substring(11, 16), "Left"));
                    }
                }
            }
            else {
                if (data.getUserName() == null) {}
                else{
                    if (data.getUserName().equals(myName) && data.getUserId().equals(myId)) {
                        mDataset.add(new ChatData(data.getUserName(),data.getUserId(), data.getImage(), data.getCreatedAt().substring(11, 16), "Right_Image"));
                    } else {
                        mDataset.add(new ChatData(data.getUserName(),data.getUserId(), data.getImage(), data.getCreatedAt().substring(11, 16), "Left_Image"));
                    }
                }

            }

            chatAdapter = new ChatAdapter(mDataset,Chat_Activity.this);
            recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
            recyclerView.setAdapter(chatAdapter);
            click();
        });

    }


    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("Tfile", MODE_PRIVATE);
        return pref.getString(key, "");
    }
    /*@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }*/

    private String getRealPathFromURI(Uri contentUri, Context context) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();

        return result;
    }

    public void uploadImage(Uri imageUri, Context context) {
        File image = new File(getRealPathFromURI(imageUri, context));
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", image.getName(), requestBody);

        RequestBody Image_channel = RequestBody.create(MediaType.parse("text/plain"),channel_id);
        initMyApi.post_Image(getPreferenceString("token"),Image_channel,body).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("PHOTO", "Upload success" );
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("PHOTO", "Upload failed : " + t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            uploadImage(selectedImageUri, getApplicationContext());
        }
    }
    private void click(){
        chatAdapter.setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(Chat_Activity.this, Image.class);
                intent.putExtra("channel_id",channel_id);
                intent.putExtra("otheruser",otheruser);
                intent.putExtra("image",mDataset.get(position).getContent());
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
    }
}