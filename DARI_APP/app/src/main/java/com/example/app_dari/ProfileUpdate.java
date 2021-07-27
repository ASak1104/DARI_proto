package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileUpdate extends AppCompatActivity {

    EditText myname;
    EditText myintroduce;
    TextView myinterests;
    TextView mylocation;
    ImageView myimage;
    private final int GET_IMAGE_FOR_IMAGEVIEW = 201;

    Uri selectedImageUri;

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    RetrofitService retrofitService = retrofit.create(RetrofitService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }

        myimage = findViewById(R.id.imageViewup);

        GlideUrl glideUrl = new GlideUrl("http://dari-app.kro.kr/user/image/"+UserStatic.userId , new LazyHeaders.Builder()
                .addHeader("authorization",UserStatic.token)
                .build());

        Glide.with(this)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .load(glideUrl)
                .into(myimage);

        myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent,GET_IMAGE_FOR_IMAGEVIEW);
            }
        });

        myname = findViewById(R.id.mynameup);
        myname.setText(UserStatic.name);
        mylocation = findViewById(R.id.location);
        mylocation.setText(UserStatic.address);
        myintroduce = findViewById(R.id.myintroduce2up);
        myintroduce.setText(UserStatic.introduce);
        myintroduce = findViewById(R.id.myintroduce2up);
        myintroduce.setText(UserStatic.introduce);
        myinterests = findViewById(R.id.myinterest2up);
        String interests="";
        for(String interest: UserStatic.interests) {
            interests += "# " + interest + "  ";
        }
        myinterests.setText(interests);
        myinterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //관심사 누르면 화면 전환..
                //Intent intent = new Intent(getApplicationContext(), Interests_Activity_later.class);
                //startActivity(intent);
            }
        });

        Button button = findViewById(R.id.modprofileup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedImageUri!=null){
                    upload(selectedImageUri, getApplicationContext());
                }

                UserStatic.name = myname.getText().toString();
                UserStatic.introduce = myintroduce.getText().toString();
                //관심사 추가..
                //서버로 보내버리기 post



                /*Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/").
                        addConverterFactory(GsonConverterFactory.create()).build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);*/

                retrofitService.putData(UserStatic.token,UserStatic.name,UserStatic.introduce,UserStatic.interests).enqueue(new Callback<ProfileUpRq>() {
                    @Override
                    public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                        if(response.isSuccessful()) {
                            ProfileUpRq profileUpRq = response.body();
                            Toast.makeText(getApplicationContext(),"프로필 업데이트 성공!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileUpRq> call, Throwable t) {

                    }
                });

                ProfileUpdate.this.finish();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        MultiTransformation option1 = new MultiTransformation(new CenterCrop(), new RoundedCorners(30));

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            Glide.with(getApplicationContext()).load(selectedImageUri).apply(RequestOptions.bitmapTransform(option1)).into(myimage);

        }
    }
    // 이미지 uri로부터 실제 파일 경로를 알아냄
    private String getRealPathFromURI(Uri contentUri, Context context) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        Log.d("uri",result);
        return result;
    }


    // Node.js 서버에 이미지를 업로드
    public void upload(Uri imageUri, Context context) {
        File image = new File(getRealPathFromURI(imageUri, context));
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", image.getName(), requestBody);


        retrofitService.uploadImage(UserStatic.token,body).enqueue(new Callback<ProfileUpRq>() {
            @Override
            public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {

            }

            @Override
            public void onFailure(Call<ProfileUpRq> call, Throwable t) {
                Log.d("PHOTO", "Upload failed : " + t.getMessage());
            }
        });
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        UserStatic.userId=getPreference("userId");

    }

    @Override
    protected void onPause(){
        super.onPause();
        setPreference("userId", UserStatic.userId);

    }


    public void setPreference(String key, String value){
        SharedPreferences pref = getSharedPreferences("UserStatic", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();

    }
    public String getPreference(String key) {
        SharedPreferences pref = getSharedPreferences("UserStatic", MODE_PRIVATE);
        return pref.getString(key, "");
    }
}