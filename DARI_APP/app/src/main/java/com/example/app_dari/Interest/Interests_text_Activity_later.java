package com.example.app_dari.Interest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.MainActivity;
import com.example.app_dari.ProfileUpdate;
import com.example.app_dari.R;
import com.example.app_dari.RetrofitClient;
import com.example.app_dari.SetProfile;
import com.example.app_dari.UserStatic;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Interests_text_Activity_later extends AppCompatActivity {

    private RecyclerView my_interests;
    TextAdapter adapter;
    ToggleButton A;
    ToggleButton B;
    ToggleButton C;
    ToggleButton D;
    ToggleButton E;
    ToggleButton F;
    ToggleButton G;
    ToggleButton H;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests_text);

        my_interests = findViewById(R.id.iterests_view);
        adapter = new TextAdapter();
        int numberOfColumns = 3;
        my_interests.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        A = (ToggleButton) findViewById(R.id.A);
        B = (ToggleButton) findViewById(R.id.B);
        C = (ToggleButton) findViewById(R.id.C);
        D = (ToggleButton) findViewById(R.id.D);
        E = (ToggleButton) findViewById(R.id.E);
        F = (ToggleButton) findViewById(R.id.F);
        G = (ToggleButton) findViewById(R.id.G);
        H = (ToggleButton) findViewById(R.id.H);

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
                if (A.isChecked() == true) {

                    Interests_Activity_later.position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.add("러닝");

                } else {
                    Interests_Activity_later.position--;
                    Interests.interests.remove(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.remove(String.valueOf("러닝"));
                }

            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (B.isChecked() == true) {
                    Interests.interests.add(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.position++;
                    Interests_Activity_later.str_interests.add("게임");
                } else {
                    Interests.interests.remove(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.position--;
                    Interests_Activity_later.str_interests.remove(String.valueOf("게임"));
                }
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (C.isChecked() == true) {
                    Interests.interests.add(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.position++;
                    Interests_Activity_later.str_interests.add("자동차");
                } else {
                    Interests.interests.remove(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.remove(String.valueOf("자동차"));
                    Interests_Activity_later.position--;
                }
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (D.isChecked() == true) {
                    Interests.interests.add(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.add("빵만들기");
                    Interests_Activity_later.position++;
                } else {
                    Interests.interests.remove(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.remove(String.valueOf("빵만들기"));
                    Interests_Activity_later.position--;
                }
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (E.isChecked() == true) {
                    Interests.interests.add(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.add("기차");
                    Interests_Activity_later.position++;
                } else {
                    Interests.interests.remove(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.remove(String.valueOf("기차"));
                    Interests_Activity_later.position--;
                }
            }
        });
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (F.isChecked() == true) {
                    Interests.interests.add(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.add("식당투어");
                    Interests_Activity_later.position++;
                } else {
                    Interests.interests.remove(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.remove(String.valueOf("식당투어"));
                    Interests_Activity_later.position--;
                }
            }
        });
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (G.isChecked() == true) {
                    Interests.interests.add(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.add("영화");
                    Interests_Activity_later.position++;
                } else {
                    Interests.interests.remove(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.remove(String.valueOf("영화"));
                    Interests_Activity_later.position--;
                }
            }
        });
        H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (H.isChecked() == true) {
                    Interests.interests.add(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.add("자전거");
                    Interests_Activity_later.position++;
                } else {
                    Interests.interests.remove(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount() - 1);
                    Interests_Activity_later.str_interests.remove(String.valueOf("자전거"));
                    Interests_Activity_later.position--;
                }
            }
        });
        ImageButton back = (ImageButton)findViewById(R.id.interests_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_text_Activity_later.this, ProfileUpdate.class);
                startActivity(intent);
            }
        });

        ImageButton change = (ImageButton) findViewById(R.id.change_img);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_text_Activity_later.this, Interests_Activity_later.class);
                startActivity(intent);
            }
        });
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Interests_Activity_later.position >= 3 && Interests_Activity_later.position < 6) {
                    UserStatic.interests = Interests_Activity_later.str_interests.toArray(new String[Interests_Activity.str_interests.size()]);
                    Intent intent = new Intent(Interests_text_Activity_later.this, ProfileUpdate.class);
                    startActivity(intent);
                    Interests_text_Activity_later.this.finish();
                } else {
                    Toast.makeText(Interests_text_Activity_later.this, "3~5개의 관심사를 설정해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    /*
    public void onReStart(){
        super.onRestart();
        if(Interests_Activity_later.position!=0){
            for(String interest : Interests_Activity_later.str_interests) {
                if(interest.equals("러닝")&&A.isChecked()==false){
                    Interests_Activity_later.position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("러닝");
                    A.setChecked(true);
                } else if(interest.equals("게임")&&B.isChecked()==false){
                    Interests.interests.add(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.position++;
                    Interests_Activity_later.str_interests.add("게임");
                    B.setChecked(true);
                } else if(interest.equals("자동차")&&C.isChecked()==false){
                    Interests.interests.add(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.position++;
                    Interests_Activity_later.str_interests.add("자동차");
                    C.setChecked(true);
                } else if(interest.equals("빵만들기")&&D.isChecked()==false){
                    Interests.interests.add(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("빵만들기");
                    Interests_Activity_later.position++;
                    D.setChecked(true);
                } else if(interest.equals("기차")&&E.isChecked()==false){
                    Interests.interests.add(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("기차");
                    Interests_Activity_later.position++;
                    E.setChecked(true);
                } else if(interest.equals("식당투어")&&F.isChecked()==false){
                    Interests.interests.add(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("식당투어");
                    Interests_Activity_later.position++;
                    F.setChecked(true);
                } else if(interest.equals("영화")&&G.isChecked()==false){
                    Interests.interests.add(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("영화");
                    Interests_Activity_later.position++;
                    G.setChecked(true);
                } else if(interest.equals("자전거")&&H.isChecked()==false){
                    Interests.interests.add(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("자전거");
                    Interests_Activity_later.position++;
                    H.setChecked(true);
                }
            }
        } else{
            for(String interest : UserStatic.interests) {
                if(interest.equals("러닝")&&!Interests.interests.contains(Interests.a)){
                    Interests_Activity_later.position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("러닝");
                    A.setChecked(true);
                } else if(interest.equals("게임")&&!Interests.interests.contains(Interests.b)){
                    Interests.interests.add(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.position++;
                    Interests_Activity_later.str_interests.add("게임");
                    B.setChecked(true);
                } else if(interest.equals("자동차")&&!Interests.interests.contains(Interests.c)){
                    Interests.interests.add(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.position++;
                    Interests_Activity_later.str_interests.add("자동차");
                    C.setChecked(true);
                } else if(interest.equals("빵만들기")&&!Interests.interests.contains(Interests.d)){
                    Interests.interests.add(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("빵만들기");
                    Interests_Activity_later.position++;
                    D.setChecked(true);
                } else if(interest.equals("기차")&&!Interests.interests.contains(Interests.e)){
                    Interests.interests.add(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("기차");
                    Interests_Activity_later.position++;
                    E.setChecked(true);
                } else if(interest.equals("식당투어")&&!Interests.interests.contains(Interests.f)){
                    Interests.interests.add(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("식당투어");
                    Interests_Activity_later.position++;
                    F.setChecked(true);
                } else if(interest.equals("영화")&&!Interests.interests.contains(Interests.g)){
                    Interests.interests.add(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("영화");
                    Interests_Activity_later.position++;
                    G.setChecked(true);
                } else if(interest.equals("자전거")&&!Interests.interests.contains(Interests.h)){
                    Interests.interests.add(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    Interests_Activity_later.str_interests.add("자전거");
                    Interests_Activity_later.position++;
                    H.setChecked(true);
                }
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(Interests.interests.contains(Interests.a)){
            Interests.interests.remove(Interests.a);
            adapter.setting(Interests.interests);
            my_interests.setAdapter(adapter);
            my_interests.scrollToPosition(adapter.getItemCount()-1);
        }
        if(Interests.interests.contains(Interests.b)){
            Interests.interests.remove(Interests.b);
            adapter.setting(Interests.interests);
            my_interests.setAdapter(adapter);
            my_interests.scrollToPosition(adapter.getItemCount()-1);
        }
        if(Interests.interests.contains(Interests.c)){
            Interests.interests.remove(Interests.c);
            adapter.setting(Interests.interests);
            my_interests.setAdapter(adapter);
            my_interests.scrollToPosition(adapter.getItemCount()-1);
        }
        if(Interests.interests.contains(Interests.d)){
            Interests.interests.remove(Interests.d);
            adapter.setting(Interests.interests);
            my_interests.setAdapter(adapter);
            my_interests.scrollToPosition(adapter.getItemCount()-1);
        }
        if(Interests.interests.contains(Interests.e)){
            Interests.interests.remove(Interests.e);
            adapter.setting(Interests.interests);
            my_interests.setAdapter(adapter);
            my_interests.scrollToPosition(adapter.getItemCount()-1);
        }
        if(Interests.interests.contains(Interests.f)){
            Interests.interests.remove(Interests.f);
            adapter.setting(Interests.interests);
            my_interests.setAdapter(adapter);
            my_interests.scrollToPosition(adapter.getItemCount()-1);
        }
        if(Interests.interests.contains(Interests.g)){
            Interests.interests.remove(Interests.g);
            adapter.setting(Interests.interests);
            my_interests.setAdapter(adapter);
            my_interests.scrollToPosition(adapter.getItemCount()-1);
        }
        if(Interests.interests.contains(Interests.h)){
            Interests.interests.remove(Interests.h);
            adapter.setting(Interests.interests);
            my_interests.setAdapter(adapter);
            my_interests.scrollToPosition(adapter.getItemCount()-1);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Interests_Activity_later.position=0;
        Interests_Activity_later.str_interests.clear();
    }\

     */
}