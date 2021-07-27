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
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.app_dari.Interest.Interests_Activity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SetProfile extends AppCompatActivity {

    EditText setmyintroduce;
    TextView setlocation;
    ImageView setimage;
    private final int GET_IMAGE_FOR_IMAGEVIEW = 201;

    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);

        int permissionChecked = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionChecked != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }

        Intent intent = getIntent();
        UserStatic.interests= intent.getStringArrayExtra("interests");

        setimage=findViewById(R.id.setimageView);
        setimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent,GET_IMAGE_FOR_IMAGEVIEW);
            }
        });

        setlocation = findViewById(R.id.setlocation);
        locationCheck();

        TextView setmyname = findViewById(R.id.setmyname);
        setmyname.setText(UserStatic.name);
        TextView setmyinterests = findViewById(R.id.setmyinterest2);
        String interests="";
        for(String interest: UserStatic.interests) {
            interests += "# " + interest + "  ";
        }
        setmyinterests.setText(interests);
        setmyintroduce = findViewById(R.id.setmyintroduce2);

        Button setprofilebt = findViewById(R.id.setprofilebt);
        setprofilebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserStatic.introduce = setmyintroduce.getText().toString();
                if(selectedImageUri==null){

                } else if(UserStatic.introduce==null){

                } else{}

                uploadImage(selectedImageUri, getApplicationContext());

                //서버로 보내버리기 post
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/").
                        addConverterFactory(GsonConverterFactory.create()).build();

                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                JsonObject point = new JsonObject();
                JsonArray coordinates = new JsonArray();
                coordinates.add(UserStatic.longitude);
                coordinates.add(UserStatic.latitude);

                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("type", "Point");
                jsonObject1.add("coordinates",coordinates);
                point.add("location", jsonObject1);

                retrofitService.postData(UserStatic.token,UserStatic.introduce,UserStatic.interests).enqueue(new Callback<ProfileUpRq>() {
                    @Override
                    public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                        if(response.isSuccessful()) {
                            ProfileUpRq profileUpRq = response.body();
                            Toast.makeText(getApplicationContext(),"프로필 설정 성공!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ProfileUpRq> call, Throwable t) {
                        Log.d("error", t.toString());
                    }
                });

                retrofitService.postData(UserStatic.token,point).enqueue(new Callback<ProfileUpRq>() {
                    @Override
                    public void onResponse(Call<ProfileUpRq> call, Response<ProfileUpRq> response) {
                        if(response.isSuccessful()) {
                            ProfileUpRq profileUpRq = response.body();
                            Toast.makeText(getApplicationContext(),"프로필 설정 성공!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ProfileUpRq> call, Throwable t) {
                        Log.d("error", t.toString());
                    }
                });

                Intent intent = new Intent(getApplicationContext(),Map_Activity.class);
                startActivity(intent);
                SetProfile.this.finish();
            }
        });
    }

    private void locationCheck(){
        int permissionChecked = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionChecked != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        //gps좌표찍기
        startLocalService();

        //좌표->주소 변환
        Geocoder g = new Geocoder(getApplicationContext());
        List<Address> address=null;
        try {
            address = g.getFromLocation(UserStatic.latitude, UserStatic.longitude, 10);
            setlocation.setText(address.get(3).getAddressLine(0).substring(5));
            UserStatic.address=address.get(3).getAddressLine(0).substring(5);
        } catch (Exception e){}
    }

    public void startLocalService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null) {
                UserStatic.latitude = location.getLatitude();
                UserStatic.longitude = location.getLongitude();
            }
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        MultiTransformation option1 = new MultiTransformation(new CenterCrop(), new RoundedCorners(30));

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            Glide.with(getApplicationContext()).load(selectedImageUri).apply(RequestOptions.bitmapTransform(option1)).into(setimage);

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
    public void uploadImage(Uri imageUri, Context context) {
        File image = new File(getRealPathFromURI(imageUri, context));
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", image.getName(), requestBody);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService retrofitClient = retrofit.create(RetrofitService.class);
        retrofitClient.setloadImage(UserStatic.token,body).enqueue(new Callback<ProfileUpRq>() {
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