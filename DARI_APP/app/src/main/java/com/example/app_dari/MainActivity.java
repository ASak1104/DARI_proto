package com.example.app_dari;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.Signup.SignupActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);
            ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
            ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);
            ImageButton btn_notify = (ImageButton)findViewById(R.id.btn_notify);


            getHashKey();


        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Map_Activity.class);

                startActivity(intent);
                MainActivity.this.finish();
            }
        });
            btn_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Profile_Activity.class);

                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });
            btn_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Chat_Activity.class);

                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });
            btn_notify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Notify_Activity.class);

                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });


        }

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

}
