package com.example.app_dari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileUpdate extends AppCompatActivity {

    EditText myname;
    EditText myintroduce;
    TextView myinterests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        myname = findViewById(R.id.mynameup);
        myname.setText(UserStatic.name);
        myintroduce = findViewById(R.id.myintroduce2up);
        myintroduce.setText(UserStatic.introduce);
        myinterests = findViewById(R.id.myinterest2up);
        myinterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //관심사 누르면 화면 전환..
            }
        });

        Button button = findViewById(R.id.modprofileup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserStatic.name = myname.getText().toString();
                UserStatic.introduce = myintroduce.getText().toString();
                //관심사 추가..
                //서버로 보내버리기 post

                finish();
            }
        });

    }
}