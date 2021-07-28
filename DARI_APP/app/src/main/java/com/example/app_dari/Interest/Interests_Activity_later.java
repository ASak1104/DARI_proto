package com.example.app_dari.Interest;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Interests_Activity_later extends AppCompatActivity {

    private RecyclerView my_interests;
    InterestAdapter adapter;
    public static int position=0;
    public static List<String> str_interests = new ArrayList<>();

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
        setContentView(R.layout.activity_interests);

        my_interests =findViewById(R.id.iterests_view);

        adapter = new InterestAdapter();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        my_interests.setLayoutManager(layoutManager);
        my_interests.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
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

        if(position==0){
        for(String interest : UserStatic.interests) {
            if(interest.equals("러닝")&&!Interests.interests.contains(Interests.a)){
                position++;
                Interests.interests.add(Interests.a);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("러닝");
                A.setChecked(true);
            } else if(interest.equals("게임")&&!Interests.interests.contains(Interests.b)){
                Interests.interests.add(Interests.b);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                position++;
                str_interests.add("게임");
                B.setChecked(true);
            } else if(interest.equals("자동차")&&!Interests.interests.contains(Interests.c)){
                Interests.interests.add(Interests.c);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                position++;
                str_interests.add("자동차");
                C.setChecked(true);
            } else if(interest.equals("빵만들기")&&!Interests.interests.contains(Interests.d)){
                Interests.interests.add(Interests.d);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("빵만들기");
                position++;
                D.setChecked(true);
            } else if(interest.equals("기차")&&!Interests.interests.contains(Interests.e)){
                Interests.interests.add(Interests.e);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("기차");
                position++;
                E.setChecked(true);
            } else if(interest.equals("식당투어")&&!Interests.interests.contains(Interests.f)){
                Interests.interests.add(Interests.f);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("식당투어");
                position++;
                F.setChecked(true);
            } else if(interest.equals("영화")&&!Interests.interests.contains(Interests.g)){
                Interests.interests.add(Interests.g);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("영화");
                position++;
                G.setChecked(true);
            } else if(interest.equals("자전거")&&!Interests.interests.contains(Interests.h)){
                Interests.interests.add(Interests.h);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("자전거");
                position++;
                H.setChecked(true);
            }
        }
        }

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(A.isChecked()==true){
                    position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("러닝");
                }
                else{
                    position--;
                    Interests.interests.remove(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("러닝"));
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
                    position++;
                    str_interests.add("게임");
                }
                else{
                    Interests.interests.remove(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position--;
                    str_interests.remove(String.valueOf("게임"));
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
                    position++;
                    str_interests.add("자동차");
                }
                else{
                    Interests.interests.remove(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("자동차"));
                    position--;
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
                    str_interests.add("빵만들기");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("빵만들기"));
                    position--;
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
                    str_interests.add("기차");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("기차"));
                    position--;
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
                    str_interests.add("식당투어");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("식당투어"));
                    position--;
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
                    str_interests.add("영화");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("영화"));
                    position--;
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
                    str_interests.add("자전거");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("자전거"));
                    position--;
                }
            }
        });


        ImageButton change = (ImageButton)findViewById(R.id.change_text);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_Activity_later.this, Interests_text_Activity.class);
                startActivity(intent);
            }
        });
        ImageButton next = (ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>=3 && position<6) {
                    String[] result_interests = str_interests.toArray(new String[str_interests.size()]);

                    Interests_Activity_later.this.finish();
                }
                else {
                    Toast.makeText(Interests_Activity_later.this, "3~5개의 관심사를 설정해주세요.", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void onReStart(){
        super.onRestart();
        if(position!=0){
            for(String interest : str_interests) {
                if(interest.equals("러닝")&&A.isChecked()==false){
                    position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("러닝");
                    A.setChecked(true);
                } else if(interest.equals("게임")&&B.isChecked()==false){
                    Interests.interests.add(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("게임");
                    B.setChecked(true);
                } else if(interest.equals("자동차")&&C.isChecked()==false){
                    Interests.interests.add(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("자동차");
                    C.setChecked(true);
                } else if(interest.equals("빵만들기")&&D.isChecked()==false){
                    Interests.interests.add(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("빵만들기");
                    position++;
                    D.setChecked(true);
                } else if(interest.equals("기차")&&E.isChecked()==false){
                    Interests.interests.add(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("기차");
                    position++;
                    E.setChecked(true);
                } else if(interest.equals("식당투어")&&F.isChecked()==false){
                    Interests.interests.add(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("식당투어");
                    position++;
                    F.setChecked(true);
                } else if(interest.equals("영화")&&G.isChecked()==false){
                    Interests.interests.add(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("영화");
                    position++;
                    G.setChecked(true);
                } else if(interest.equals("자전거")&&H.isChecked()==false){
                    Interests.interests.add(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("자전거");
                    position++;
                    H.setChecked(true);
                }
            }
        } else{
            for(String interest : UserStatic.interests) {
                if(interest.equals("러닝")&&!Interests.interests.contains(Interests.a)){
                    position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("러닝");
                    A.setChecked(true);
                } else if(interest.equals("게임")&&!Interests.interests.contains(Interests.b)){
                    Interests.interests.add(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("게임");
                    B.setChecked(true);
                } else if(interest.equals("자동차")&&!Interests.interests.contains(Interests.c)){
                    Interests.interests.add(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("자동차");
                    C.setChecked(true);
                } else if(interest.equals("빵만들기")&&!Interests.interests.contains(Interests.d)){
                    Interests.interests.add(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("빵만들기");
                    position++;
                    D.setChecked(true);
                } else if(interest.equals("기차")&&!Interests.interests.contains(Interests.e)){
                    Interests.interests.add(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("기차");
                    position++;
                    E.setChecked(true);
                } else if(interest.equals("식당투어")&&!Interests.interests.contains(Interests.f)){
                    Interests.interests.add(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("식당투어");
                    position++;
                    F.setChecked(true);
                } else if(interest.equals("영화")&&!Interests.interests.contains(Interests.g)){
                    Interests.interests.add(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("영화");
                    position++;
                    G.setChecked(true);
                } else if(interest.equals("자전거")&&!Interests.interests.contains(Interests.h)){
                    Interests.interests.add(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("자전거");
                    position++;
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
        position=0;
        str_interests.clear();
    }
}