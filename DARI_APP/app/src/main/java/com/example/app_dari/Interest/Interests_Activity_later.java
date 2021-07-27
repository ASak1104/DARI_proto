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
    static int position=0;
    static List<String> str_interests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        my_interests =findViewById(R.id.iterests_view);

        adapter = new InterestAdapter();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        my_interests.setLayoutManager(layoutManager);
        my_interests.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
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

        for(String interest : UserStatic.interests) {
            if(interest.equals("러닝")){
                position++;
                Interests.interests.add(Interests.a);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("러닝");
            } else if(interest.equals("게임")){
                Interests.interests.add(Interests.b);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                position++;
                str_interests.add("게임");
            } else if(interest.equals("자동차")){
                Interests.interests.add(Interests.c);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                position++;
                str_interests.add("자동차");
            } else if(interest.equals("빵만들기")){
                Interests.interests.add(Interests.d);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("빵만들기");
                position++;
            } else if(interest.equals("기차")){
                Interests.interests.add(Interests.e);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("기차");
                position++;
            } else if(interest.equals("식당투어")){
                Interests.interests.add(Interests.f);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("식당투어");
                position++;
            } else if(interest.equals("영화")){
                Interests.interests.add(Interests.g);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("영화");
                position++;
            } else if(interest.equals("자전거")){
                Interests.interests.add(Interests.h);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("자전거");
                position++;
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

                    Intent intent = new Intent(Interests_Activity_later.this, ProfileUpdate.class);
                    intent.putExtra("interests", result_interests);
                    Interests_Activity_later.this.finish();
                }
                else {
                    Toast.makeText(Interests_Activity_later.this, "3~5개의 관심사를 설정해주세요.", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

}