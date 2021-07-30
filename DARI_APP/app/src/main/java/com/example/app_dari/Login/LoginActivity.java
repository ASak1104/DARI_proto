package com.example.app_dari.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_dari.GetProfile;
import com.example.app_dari.Interest.Interests;
import com.example.app_dari.Interest.Interests_Activity;
import com.example.app_dari.Location;
import com.example.app_dari.MainActivity;
import com.example.app_dari.MapData;
import com.example.app_dari.Map_Activity;
import com.example.app_dari.R;
import com.example.app_dari.RetrofitClient;
import com.example.app_dari.RetrofitService;
import com.example.app_dari.SetProfile;
import com.example.app_dari.Signup.SignupActivity;
import com.example.app_dari.UserStatic;

import net.daum.android.map.MapActivity;
import net.daum.mf.map.api.MapPoint;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {


    static public String token;
    private RetrofitClient retrofitClient;
    private com.example.app_dari.initMyApi initMyApi;
    EditText idtext;
    EditText pwtext;
    CheckBox checkBox;
    static public String name;


    GetProfile getProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        idtext = (EditText)findViewById(R.id.edit_id);
        pwtext = (EditText)findViewById(R.id.edit_pw);
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();
        checkBox = (CheckBox)findViewById(R.id.auto_Login);

        //자동로그인
        if(getPreferenceString("check").equals("true")&&getPreferenceString("hastoken").equals("true")){
            checkBox.setChecked(true);
            UserStatic.token = getPreferenceString("token");
            Login();
        }

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idtext.getText().toString();
                String pw = pwtext.getText().toString();
                hideKeyboard();

                if(id.isEmpty() ==true || pw.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("알림")
                            .setMessage("로그인 정보를 입력해주세요.")
                            .setPositiveButton("확인",null)
                            .create().show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else {
                        LoginResponse();
                }
            }
        });

        Button signup = (Button)findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to_signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(to_signup);

            }
        });


    }
    public void LoginResponse() {
        String userID = idtext.getText().toString().trim();
        String userPassword = pwtext.getText().toString().trim();

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        LoginRequest loginRequest = new LoginRequest(userID,userPassword);

        /*if(getPreferenceString("hastoken").equals("true")){
            UserStatic.token = getPreferenceString("token");
            Login();
        }*/

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        initMyApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("retrofit", "Data fetch success");
                //통신 성공
                if (response.isSuccessful()) {
                    //response.body()를 result에 저장
                    LoginResponse result = response.body();
                    //받은 코드 저장
                    int resultCode = result.getResultCode();

                    UserStatic.token = result.getToken();

                    //받은 토큰 저장
                    name = result.getName();


                    int success = 201; //로그인 성공
                    int errorId = 300; //아이디 일치x
                    int errorPw = 400; //비밀번호 일치x


                    if (resultCode == success) {

                        String userID = idtext.getText().toString();
                        UserStatic.userId=userID;
                        String userPassword = pwtext.getText().toString();


                        //다른 통신을 하기 위해 token 저장
                        setPreference("token",UserStatic.token);
                        setPreference("hastoken","true");
                        if(checkBox.isChecked() ==true) {
                            setPreference("check", "true");
                        }
                        else { setPreference("check","false");}

                        GetProfile();


                    } else if(resultCode==errorId){

                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("알림")
                                .setMessage("등록되지 않은 아이디입니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();

                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("알림")
                                .setMessage("비밀번호가 일치하지 않습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    }
                }
            }

            //통신 실패
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생하였습니다.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }
    public void setPreference(String key, String value){
        SharedPreferences pref = getSharedPreferences( "Tfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("Tfile", MODE_PRIVATE);
        return pref.getString(key, "");
    }

    private void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(idtext.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(pwtext.getWindowToken(), 0);
    }

    @Override
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
    }

    public void Login(){

        initMyApi.getLogin(UserStatic.token).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse result = response.body();

                int status = result.getResultCode();

                int success = 200; //로그인 성공
                int fail = 401; //토큰 유효x
                int fail2 = 419; //토큰 기간 만료

                if(status == success){
                    GetProfile();
                }
                else {
                    setPreference("hasetoken","false");
                    Toast.makeText(getApplicationContext(),"토큰이 유효하지 않습니다.",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    public void GetProfile(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService service1 = retrofit.create(RetrofitService.class);
        Call<GetProfile> call2 = service1.getProfile(UserStatic.token);

        call2.enqueue(new Callback<GetProfile>() {
            @Override
            public void onResponse(Call<GetProfile> call2, Response<GetProfile> response) {
                getProfile=response.body();
                if(getProfile.introduce!=null) {
                    UserStatic.name = getProfile.name;
                    UserStatic.userId = getProfile.userId;
                    UserStatic.latitude = getProfile.location.coordinates[1];
                    UserStatic.longitude = getProfile.location.coordinates[0];
                    UserStatic.introduce = getProfile.introduce;
                    UserStatic.interests = getProfile.interests;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
                else{
                    UserStatic.name = getProfile.name;
                    Toast.makeText(LoginActivity.this, UserStatic.name + "님 환영합니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, Interests_Activity.class);
                    intent.putExtra("myId", UserStatic.userId);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<GetProfile> call2, Throwable t) {

            }
        });
    }
}
