package com.example.app_dari.Signup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.R;
import com.example.app_dari.RetrofitClient;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    EditText edit_name;
    EditText edit_id;
    EditText edit_pw;
    EditText edit_pw2;
    private RetrofitClient retrofitClient;
    private com.example.app_dari.initMyApi initMyApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        edit_name = findViewById(R.id.edit_name);
        edit_id = findViewById(R.id.edit_id);
        edit_pw = findViewById(R.id.edit_pw);
        edit_pw2 = findViewById(R.id.edit_pw2);
        edit_id.setFilters(new InputFilter[] {filterAlphaNum});

        /*ImageButton sign_back = (ImageButton)findViewById(R.id.sign_back);
        sign_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
        Button ok = (Button)findViewById(R.id.ok_sign);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String id = edit_id.getText().toString();
            String pw = edit_pw.getText().toString();
            String name = edit_name.getText().toString();
            String pw2 = edit_pw2.getText().toString();

                if(name.length()<2){
                    Toast.makeText(getApplicationContext(), "????????? ????????? ?????? ??????????????????!", Toast.LENGTH_SHORT).show();
                } else if(id.length()<6){
                    Toast.makeText(getApplicationContext(), "???????????? 6?????? ?????? ??????????????????!", Toast.LENGTH_SHORT).show();
                } else if(pw.length()<8){
                    Toast.makeText(getApplicationContext(), "??????????????? 8?????? ?????? ??????????????????!", Toast.LENGTH_SHORT).show();
                } else if(!pw.equals(pw2)) {
                    Toast.makeText(getApplicationContext(), "??????????????? ????????? ??????????????????!", Toast.LENGTH_SHORT).show();
                } else {
                    SignupResponse();
                }
            }
        });
    }
    public void SignupResponse() {
        String userID = edit_id.getText().toString().trim();
        String userPassword = edit_pw.getText().toString().trim();
        String name = edit_name.getText().toString().trim();

        //loginRequest??? ???????????? ????????? id??? pw??? ??????
        SignupRequest signupRequest = new SignupRequest(userID, userPassword, name);

        //retrofit ??????
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();
        initMyApi.getSignupResponse(signupRequest).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()??? result??? ??????
                    SignupResponse result = response.body();

                    //?????? ?????? ??????
                    boolean resultCode = result.isResultCode();
                    if (resultCode) {


                        //?????? ????????? ?????? ?????? token ??????


                        Toast.makeText(SignupActivity.this,"??????????????? ?????????????????????.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);

                        startActivity(intent);
                        SignupActivity.this.finish();


                    } else {


                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                        builder.setTitle("??????")
                                .setMessage("?????? ???????????? ???????????? ????????????.")
                                .setPositiveButton("??????", null)
                                .create()
                                .show();

                    }
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

            }
        });
    }

        protected InputFilter filterAlphaNum = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");

            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };


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
}