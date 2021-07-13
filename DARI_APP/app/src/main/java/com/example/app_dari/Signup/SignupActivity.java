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

        ImageButton sign_back = (ImageButton)findViewById(R.id.sign_back);
        sign_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button ok = (Button)findViewById(R.id.ok_sign);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String id = edit_id.getText().toString();
            String pw = edit_pw.getText().toString();
            String name = edit_name.getText().toString();
            String pw2 = edit_pw2.getText().toString();

                if(name.length()<2){
                    Toast.makeText(getApplicationContext(), "이름을 두글자 이상 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if(id.length()<6){
                    Toast.makeText(getApplicationContext(), "아이디를 6글자 이상 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if(pw.length()<8){
                    Toast.makeText(getApplicationContext(), "비밀번호를 8글자 이상 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if(!pw.equals(pw2)) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 같은지 확인해주세요!", Toast.LENGTH_SHORT).show();
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
        String userPassword2 = edit_pw2.getText().toString().trim();

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        SignupRequest signupRequest = new SignupRequest(userID, userPassword, name ,userPassword2);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();
        initMyApi.getSignupResponse(signupRequest).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()를 result에 저장
                    SignupResponse result = response.body();

                    //받은 코드 저장
                    boolean resultCode = result.isResultCode();
                    if (resultCode) {


                        //다른 통신을 하기 위해 token 저장


                        Toast.makeText(SignupActivity.this,"회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);

                        startActivity(intent);
                        SignupActivity.this.finish();


                    } else {


                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                        builder.setTitle("알림")
                                .setMessage("이미 존재하는 아이디가 있습니다.")
                                .setPositiveButton("확인", null)
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