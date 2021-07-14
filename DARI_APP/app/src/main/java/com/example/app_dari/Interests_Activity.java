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

import java.util.ArrayList;
import java.util.List;

public class Interests_Activity extends AppCompatActivity {

    private RecyclerView my_interests;
    private int position=0;
    private int position_a;
    private int position_b;
    private int position_c;
    private int position_d;
    private int position_e;
    private int position_f;
    InterestAdapter adapter;
    private List<String> interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        my_interests =findViewById(R.id.iterests_view);
        interests = new ArrayList<>();

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



        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if(A.isChecked()==true){
                    adapter.addItem(position, new Interests("A", R.drawable.notify));
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                   position_a = position;
                   interests.add("A");
                   position++;
                   }
                  else{
                    adapter.removeItem(position_a);
                       my_interests.scrollToPosition(adapter.getItemCount()-1);
                    if(position_a<position_b){position_b--;}
                    if(position_a<position_c){position_c--;}
                    if(position_a<position_d){position_d--;}
                    if(position_a<position_e){position_e--;}
                    if(position_a<position_f){position_f--;}
                    position--;
                    my_interests.setAdapter(adapter);
                    interests.remove(String.valueOf("A"));
                }

            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(B.isChecked()==true){
                    adapter.addItem(position, new Interests("B", R.drawable.profile));
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position_b = position;
                    position++;
                    interests.add("B");
                }
                else{
                    adapter.removeItem(position_b);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    if(position_b<position_a){position_a--;}
                    if(position_b<position_c){position_c--;}
                    if(position_b<position_d){position_d--;}
                    if(position_b<position_e){position_e--;}
                    if(position_b<position_f){position_f--;}
                    position--;
                    my_interests.setAdapter(adapter);
                    interests.remove(String.valueOf("B"));
                }
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(C.isChecked()==true){
                    adapter.addItem(position, new Interests("C", R.drawable.map));
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position_c = position;
                    position++;
                    interests.add("C");
                }
                else{
                    adapter.removeItem(position_c);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    if(position_c<position_b){position_b--;}
                    if(position_c<position_a){position_a--;}
                    if(position_c<position_d){position_d--;}
                    if(position_c<position_e){position_e--;}
                    if(position_c<position_f){position_f--;}
                    position--;
                    my_interests.setAdapter(adapter);
                    interests.remove(String.valueOf("C"));
                }
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(D.isChecked()==true){
                    adapter.addItem(position, new Interests("D", R.drawable.message));
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position_d = position;
                    position++;
                    interests.add("D");
                }
                else{
                    adapter.removeItem(position_d);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    if(position_d<position_b){position_b--;}
                    if(position_d<position_c){position_c--;}
                    if(position_d<position_a){position_a--;}
                    if(position_d<position_e){position_e--;}
                    if(position_d<position_f){position_f--;}
                    position--;
                    my_interests.setAdapter(adapter);
                    interests.remove(String.valueOf("D"));
                }
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(E.isChecked()==true){
                    adapter.addItem(position, new Interests("E", R.drawable.arrow));
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position_e = position;
                    position++;
                    interests.add("E");
                }
                else{
                    adapter.removeItem(position_e);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    if(position_e<position_b){position_b--;}
                    if(position_e<position_c){position_c--;}
                    if(position_e<position_d){position_d--;}
                    if(position_e<position_a){position_a--;}
                    if(position_e<position_f){position_f--;}
                    position--;
                    my_interests.setAdapter(adapter);
                    interests.remove(String.valueOf("E"));
                }
            }
        });
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(F.isChecked()==true){
                    adapter.addItem(position, new Interests("F", R.drawable.main));
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position_f = position;
                    position++;
                    interests.add("F");
                }
                else{
                    adapter.removeItem(position_f);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    if(position_f<position_b){position_b--;}
                    if(position_f<position_c){position_c--;}
                    if(position_f<position_d){position_d--;}
                    if(position_f<position_e){position_e--;}
                    if(position_f<position_a){position_a--;}
                    position--;
                    my_interests.setAdapter(adapter);
                    interests.remove(String.valueOf("F"));
                }
            }
        });
        String[] result = interests.toArray(new String[0]);
        ImageButton next = (ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_Activity.this, Interests_text_Activity.class);
                intent.putExtra("interests",result);
                startActivity(intent);
            }
        });

    }
}
