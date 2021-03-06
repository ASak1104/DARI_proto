package com.example.app_dari.Interest;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    public static List<String> str_interests = null;

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

        List<String> str_interests = new ArrayList<>();
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
            if(interest.equals("??????")&&!Interests.interests.contains(Interests.a)){
                position++;
                Interests.interests.add(Interests.a);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("??????");
                A.setChecked(true);
            } else if(interest.equals("??????")&&!Interests.interests.contains(Interests.b)){
                Interests.interests.add(Interests.b);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                position++;
                str_interests.add("??????");
                B.setChecked(true);
            } else if(interest.equals("?????????")&&!Interests.interests.contains(Interests.c)){
                Interests.interests.add(Interests.c);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                position++;
                str_interests.add("?????????");
                C.setChecked(true);
            } else if(interest.equals("????????????")&&!Interests.interests.contains(Interests.d)){
                Interests.interests.add(Interests.d);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("????????????");
                position++;
                D.setChecked(true);
            } else if(interest.equals("??????")&&!Interests.interests.contains(Interests.e)){
                Interests.interests.add(Interests.e);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("??????");
                position++;
                E.setChecked(true);
            } else if(interest.equals("????????????")&&!Interests.interests.contains(Interests.f)){
                Interests.interests.add(Interests.f);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("????????????");
                position++;
                F.setChecked(true);
            } else if(interest.equals("??????")&&!Interests.interests.contains(Interests.g)){
                Interests.interests.add(Interests.g);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("??????");
                position++;
                G.setChecked(true);
            } else if(interest.equals("?????????")&&!Interests.interests.contains(Interests.h)){
                Interests.interests.add(Interests.h);
                adapter.setting(Interests.interests);
                my_interests.setAdapter(adapter);
                my_interests.scrollToPosition(adapter.getItemCount()-1);
                str_interests.add("?????????");
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
                    str_interests.add("??????");
                }
                else{
                    position--;
                    Interests.interests.remove(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("??????"));
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
                    str_interests.add("??????");
                }
                else{
                    Interests.interests.remove(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position--;
                    str_interests.remove(String.valueOf("??????"));
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
                    str_interests.add("?????????");
                }
                else{
                    Interests.interests.remove(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("?????????"));
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
                    str_interests.add("????????????");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("????????????"));
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
                    str_interests.add("??????");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("??????"));
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
                    str_interests.add("????????????");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("????????????"));
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
                    str_interests.add("??????");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("??????"));
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
                    str_interests.add("?????????");
                    position++;
                }
                else{
                    Interests.interests.remove(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("?????????"));
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
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>=3 && position<6) {
                    String[] result_interests = str_interests.toArray(new String[str_interests.size()]);

                    Interests_Activity_later.this.finish();
                }
                else {
                    Toast.makeText(Interests_Activity_later.this, "3~5?????? ???????????? ??????????????????.", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void onReStart(){
        super.onRestart();
        if(position!=0){
            for(String interest : str_interests) {
                if(interest.equals("??????")&&A.isChecked()==false){
                    position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("??????");
                    A.setChecked(true);
                } else if(interest.equals("??????")&&B.isChecked()==false){
                    Interests.interests.add(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("??????");
                    B.setChecked(true);
                } else if(interest.equals("?????????")&&C.isChecked()==false){
                    Interests.interests.add(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("?????????");
                    C.setChecked(true);
                } else if(interest.equals("????????????")&&D.isChecked()==false){
                    Interests.interests.add(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("????????????");
                    position++;
                    D.setChecked(true);
                } else if(interest.equals("??????")&&E.isChecked()==false){
                    Interests.interests.add(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("??????");
                    position++;
                    E.setChecked(true);
                } else if(interest.equals("????????????")&&F.isChecked()==false){
                    Interests.interests.add(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("????????????");
                    position++;
                    F.setChecked(true);
                } else if(interest.equals("??????")&&G.isChecked()==false){
                    Interests.interests.add(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("??????");
                    position++;
                    G.setChecked(true);
                } else if(interest.equals("?????????")&&H.isChecked()==false){
                    Interests.interests.add(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("?????????");
                    position++;
                    H.setChecked(true);
                }
            }
        } else{
            for(String interest : UserStatic.interests) {
                if(interest.equals("??????")&&!Interests.interests.contains(Interests.a)){
                    position++;
                    Interests.interests.add(Interests.a);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("??????");
                    A.setChecked(true);
                } else if(interest.equals("??????")&&!Interests.interests.contains(Interests.b)){
                    Interests.interests.add(Interests.b);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("??????");
                    B.setChecked(true);
                } else if(interest.equals("?????????")&&!Interests.interests.contains(Interests.c)){
                    Interests.interests.add(Interests.c);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("?????????");
                    C.setChecked(true);
                } else if(interest.equals("????????????")&&!Interests.interests.contains(Interests.d)){
                    Interests.interests.add(Interests.d);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("????????????");
                    position++;
                    D.setChecked(true);
                } else if(interest.equals("??????")&&!Interests.interests.contains(Interests.e)){
                    Interests.interests.add(Interests.e);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("??????");
                    position++;
                    E.setChecked(true);
                } else if(interest.equals("????????????")&&!Interests.interests.contains(Interests.f)){
                    Interests.interests.add(Interests.f);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("????????????");
                    position++;
                    F.setChecked(true);
                } else if(interest.equals("??????")&&!Interests.interests.contains(Interests.g)){
                    Interests.interests.add(Interests.g);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("??????");
                    position++;
                    G.setChecked(true);
                } else if(interest.equals("?????????")&&!Interests.interests.contains(Interests.h)){
                    Interests.interests.add(Interests.h);
                    adapter.setting(Interests.interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("?????????");
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