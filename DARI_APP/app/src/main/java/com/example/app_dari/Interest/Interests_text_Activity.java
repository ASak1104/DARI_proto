package com.example.app_dari.Interest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.MainActivity;
import com.example.app_dari.R;
import com.example.app_dari.RetrofitClient;
import com.example.app_dari.SetProfile;
import com.example.app_dari.initMyApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Interests_text_Activity extends AppCompatActivity {

    String userID;

    private RecyclerView my_interests;
    TextAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests_text);

        Intent intent = getIntent();
        userID=intent.getExtras().getString("myId");

        my_interests =findViewById(R.id.iterests_view);
        adapter = new TextAdapter();
        int numberOfColumns = 3;
        my_interests.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        ToggleButton A = (ToggleButton) findViewById(R.id.A);
        ToggleButton B = (ToggleButton) findViewById(R.id.B);
        ToggleButton C = (ToggleButton) findViewById(R.id.C);
        ToggleButton D = (ToggleButton) findViewById(R.id.D);
        ToggleButton E = (ToggleButton) findViewById(R.id.E);
        ToggleButton F = (ToggleButton) findViewById(R.id.F);
        ToggleButton G = (ToggleButton) findViewById(R.id.G);
        ToggleButton H = (ToggleButton) findViewById(R.id.H);

        adapter.setting(Interests.interests);
        my_interests.setAdapter(adapter);
        if(Interests.interests.contains(Interests.a)){ A.setChecked(true);}
        if(Interests.interests.contains(Interests.b)){ B.setChecked(true);}
        if(Interests.interests.contains(Interests.c)){ C.setChecked(true);}
        if(Interests.interests.contains(Interests.d)){ D.setChecked(true);}
        if(Interests.interests.contains(Interests.e)){ E.setChecked(true);}
        if(Interests.interests.contains(Interests.f)){ F.setChecked(true);}
        if(Interests.interests.contains(Interests.g)){ G.setChecked(true);}
        if(Interests.interests.contains(Interests.h)){ H.setChecked(true);}






        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(A.isChecked()==true){

                    Interests_Activity.position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.add("러닝");

                }
                else{
                    Interests_Activity.position--;
                    Interests.interests.remove(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.remove(String.valueOf("러닝"));
                }

            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(B.isChecked()==true){
                    Interests.interests.add(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.position++;
                    Interests_Activity.str_interests.add("게임");
                }
                else{
                    Interests.interests.remove(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.position--;
                    Interests_Activity.str_interests.remove(String.valueOf("게임"));
                }
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(C.isChecked()==true){
                    Interests.interests.add(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.position++;
                    Interests_Activity.str_interests.add("자동차");
                }
                else{
                    Interests.interests.remove(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.remove(String.valueOf("자동차"));
                    Interests_Activity.position--;
                }
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(D.isChecked()==true){
                    Interests.interests.add(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.add("빵만들기");
                    Interests_Activity.position++;
                }
                else{
                    Interests.interests.remove(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.remove(String.valueOf("빵만들기"));
                    Interests_Activity.position--;
                }
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(E.isChecked()==true){
                    Interests.interests.add(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.add("기차");
                    Interests_Activity.position++;
                }
                else{
                    Interests.interests.remove(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.remove(String.valueOf("기차"));
                    Interests_Activity.position--;
                }
            }
        });
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(F.isChecked()==true){
                    Interests.interests.add(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.add("식당투어");
                    Interests_Activity.position++;
                }
                else{
                    Interests.interests.remove(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.remove(String.valueOf("식당투어"));
                    Interests_Activity.position--;
                }
            }
        });
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(G.isChecked()==true){
                    Interests.interests.add(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.add("영화");
                    Interests_Activity.position++;
                }
                else{
                    Interests.interests.remove(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.remove(String.valueOf("영화"));
                    Interests_Activity.position--;
                }
            }
        });
        H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(H.isChecked()==true){
                    Interests.interests.add(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.add("자전거");
                    Interests_Activity.position++;
                }
                else{
                    Interests.interests.remove(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity.str_interests.remove(String.valueOf("자전거"));
                    Interests_Activity.position--;
                }
            }
        });

        ImageButton change = (ImageButton)findViewById(R.id.change_img);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_text_Activity.this, Interests_Activity.class);
                startActivity(intent);
            }
        });
        ImageButton next = (ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Interests_Activity.position>=3 && Interests_Activity.position<6) {
                    String[] result_interests = Interests_Activity.str_interests.toArray(new String[Interests_Activity.str_interests.size()]);
                    Intent intent = new Intent(Interests_text_Activity.this, SetProfile.class);
                    intent.putExtra("interests", result_interests);
                    intent.putExtra("myId", userID);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Interests_text_Activity.this, "3~5개의 관심사를 설정해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
        ImageButton back = (ImageButton)findViewById(R.id.interests_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_text_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}