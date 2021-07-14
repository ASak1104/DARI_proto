package com.example.app_dari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Interests_text_Activity extends AppCompatActivity {

    private RecyclerView my_interests;
    private int position=0;
    private int position_a;
    private int position_b;
    private int position_c;
    private int position_d;
    private int position_e;
    private int position_f;
    TextAdapter adapter;
    private List<String> interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests_text);

        my_interests =findViewById(R.id.iterests_view);
        interests = new ArrayList<>();

        adapter = new TextAdapter();
        int numberOfColumns = 3;
        my_interests.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
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
                    adapter.addItem(position, new Text_Interests("#A"));
                    my_interests.setAdapter(adapter);
                    position_a = position;
                    interests.add("A");
                    position++;
                }
                else{
                    adapter.removeItem(position_a);
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
                    adapter.addItem(position, new Text_Interests("#B"));
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position_b = position;
                    position++;
                    interests.add("B");
                }
                else{
                    adapter.removeItem(position_b);
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
                    adapter.addItem(position, new Text_Interests("#C"));
                    my_interests.setAdapter(adapter);
                    position_c = position;
                    position++;
                    interests.add("C");
                }
                else{
                    adapter.removeItem(position_c);
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
                    adapter.addItem(position, new Text_Interests("#D"));
                    my_interests.setAdapter(adapter);
                    position_d = position;
                    position++;
                    interests.add("D");
                }
                else{
                    adapter.removeItem(position_d);
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
                    adapter.addItem(position, new Text_Interests("#E"));
                    my_interests.setAdapter(adapter);
                    position_e = position;
                    position++;
                    interests.add("E");
                }
                else{
                    adapter.removeItem(position_e);
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
                    adapter.addItem(position, new Text_Interests("#F"));
                    my_interests.setAdapter(adapter);
                    position_f = position;
                    position++;
                    interests.add("F");
                }
                else{
                    adapter.removeItem(position_f);
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
                Intent intent = new Intent(Interests_text_Activity.this, MainActivity.class);
                intent.putExtra("interests",result);
                startActivity(intent);
            }
        });

    }
}