package com.example.app_dari;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.Login.LoginActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Interests_Activity extends AppCompatActivity {

    private RecyclerView my_interests;
    InterestAdapter adapter;
    private ArrayList<Interests> interests;
    private List<String> str_interests;
    private int position=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        my_interests =findViewById(R.id.iterests_view);
        interests = new ArrayList<>();
        str_interests = new ArrayList<>();

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
        Interests a = new Interests("러닝",R.drawable.running);
        Interests b = new Interests("게임",R.drawable.game);
        Interests c = new Interests("자동차",R.drawable.car);
        Interests d = new Interests("빵만들기",R.drawable.baking);
        Interests e = new Interests("기차",R.drawable.train);
        Interests f = new Interests("식당투어",R.drawable.eat);
        Interests g = new Interests("영화",R.drawable.movie);
        Interests h = new Interests("자전거",R.drawable.cycle);

        Intent intent =getIntent();
        interests =intent.getParcelableArrayListExtra("interests");
        if(interests ==null){
            interests = new ArrayList<>();
        }
        if(interests!=null) {
            adapter.setting(interests);
            my_interests.setAdapter(adapter);
            if(interests.indexOf(a) != -1){A.setChecked(true);}
            if(interests.contains(b)){B.setChecked(true);}
            if(interests.contains(c)){C.setChecked(true);}
            if(interests.contains(d)){D.setChecked(true);}
            if(interests.contains(e)){E.setChecked(true);}
            if(interests.contains(f)){F.setChecked(true);}
            if(interests.contains(g)){G.setChecked(true);}
            if(interests.contains(h)){H.setChecked(true);}
        }




        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(A.isChecked()==true){

                    position++;
                    interests.add(a);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);

                }
                else{
                    position--;
                    interests.remove(a);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                }

            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(B.isChecked()==true){
                    interests.add(b);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position++;
                }
                else{
                    interests.remove(b);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position--;
                }
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(C.isChecked()==true){
                    interests.add(c);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position++;
                }
                else{
                    interests.remove(c);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position--;
                }
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(D.isChecked()==true){
                    interests.add(d);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position++;
                }
                else{
                    interests.remove(d);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position--;
                }
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(E.isChecked()==true){
                    interests.add(e);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position++;
                }
                else{
                    interests.remove(e);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position--;
                }
            }
        });
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(F.isChecked()==true){
                    interests.add(f);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position++;
                }
                else{
                    interests.remove(f);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    position--;
                }
            }
        });
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(G.isChecked()==true){
                    interests.add(g);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                }
                else{
                    interests.remove(g);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                }
            }
        });
        H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(H.isChecked()==true){
                    interests.add(h);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                }
                else{
                    interests.remove(h);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                }
            }
        });
        String[] result = str_interests.toArray(new String[0]);
        ImageButton change = (ImageButton)findViewById(R.id.change_text);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_Activity.this, Interests_text_Activity.class);
                if(interests==null){intent.putExtra("interests",interests= new ArrayList<>());}
                else{
                    intent.putParcelableArrayListExtra("interests",interests);
                    }
                startActivity(intent);
            }
        });
        ImageButton next = (ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>=3 && position<6) {
                    Intent intent = new Intent(Interests_Activity.this, MainActivity.class);
                    intent.putExtra("interests", result);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Interests_Activity.this, "3~5개의 관심사를 설정해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
