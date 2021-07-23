package com.example.app_dari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupporterList extends AppCompatActivity {

    int s=-1;
    int e=-1;
    int count = 0;

    long now;
    Date date;
    SimpleDateFormat mformat = new SimpleDateFormat("MM");
    SimpleDateFormat dformat = new SimpleDateFormat("dd");

    Button[] buttons = new Button[7];
    Button[] hbuttons = new Button[13];

    Supporters supporters;

    RecyclerView recyclerView;
    Adapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_list);

        buttons[0] = findViewById(R.id.day1);
        buttons[1] = findViewById(R.id.day2);
        buttons[2] = findViewById(R.id.day3);
        buttons[3] = findViewById(R.id.day4);
        buttons[4] = findViewById(R.id.day5);
        buttons[5] = findViewById(R.id.day6);
        buttons[6] = findViewById(R.id.day7);

        hbuttons[0] = findViewById(R.id.h09);
        hbuttons[1] = findViewById(R.id.h10);
        hbuttons[2] = findViewById(R.id.h11);
        hbuttons[3] = findViewById(R.id.h12);
        hbuttons[4] = findViewById(R.id.h13);
        hbuttons[5] = findViewById(R.id.h14);
        hbuttons[6] = findViewById(R.id.h15);
        hbuttons[7] = findViewById(R.id.h16);
        hbuttons[8] = findViewById(R.id.h17);
        hbuttons[9] = findViewById(R.id.h18);
        hbuttons[10] = findViewById(R.id.h19);
        hbuttons[11] = findViewById(R.id.h20);
        hbuttons[12] = findViewById(R.id.h21);


        now=System.currentTimeMillis();
        date=new Date(now);
        String month = mformat.format(date);
        String day = dformat.format(date);
        int dayi = Integer.parseInt(day);
        for(int i=0; i<7; i++){
            String text = month + "월 " + String.valueOf(++dayi) + "일";
            buttons[i].setText(text);
        }


        recyclerView = findViewById(R.id.supporter_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //request();
    }

    public void daybtclr(int k){
        for (int i = 0; i <7; i++) {
            buttons[i].setBackground(this.getResources().getDrawable(R.drawable.button2));
            buttons[i].setTextColor(Color.BLACK);
        }
        buttons[k].setBackground(this.getResources().getDrawable(R.drawable.button2_001194));
        buttons[k].setTextColor(Color.WHITE);
    }

    public void day1(View view){
        daybtclr(0);
        //+1일 저장
    }
    public void day2(View view){
        daybtclr(1);

    }
    public void day3(View view){
        daybtclr(2);

    }
    public void day4(View view){
        daybtclr(3);

    }
    public void day5(View view){
        daybtclr(4);

    }
    public void day6(View view){
        daybtclr(5);

    }
    public void day7(View view){
        daybtclr(6);

    }

    public void hourbt(int h){
        count++;

        if(count%2==1){
            for(int i=0; i<13; i++){
                hbuttons[i].setBackground(this.getResources().getDrawable(R.drawable.button2));
                hbuttons[i].setTextColor(Color.BLACK);
            }
            hbuttons[h].setBackground(this.getResources().getDrawable(R.drawable.button2_001194));
            hbuttons[h].setTextColor(Color.WHITE);
            s=h;
        } else {
            e=h;
            for(int i=s; i<=e; i++) {
                hbuttons[i].setBackground(this.getResources().getDrawable(R.drawable.button2_001194));
                hbuttons[i].setTextColor(Color.WHITE);
            }
            for(int i=e; i<=s; i++) {
                hbuttons[i].setBackground(this.getResources().getDrawable(R.drawable.button2_001194));
                hbuttons[i].setTextColor(Color.WHITE);
            }
        }

    }

    public void h09(View view){
        hourbt(0);
    }
    public void h10(View view){
        hourbt(1);
    }
    public void h11(View view){
        hourbt(2);
    }
    public void h12(View view){
        hourbt(3);
    }
    public void h13(View view){
        hourbt(4);
    }
    public void h14(View view){
        hourbt(5);
    }
    public void h15(View view){
        hourbt(6);
    }
    public void h16(View view){
        hourbt(7);
    }
    public void h17(View view){
        hourbt(8);
    }
    public void h18(View view){
        hourbt(9);
    }
    public void h19(View view){
        hourbt(10);
    }
    public void h20(View view){
        hourbt(11);
    }
    public void h21(View view){
        hourbt(12);
    }

    public void request(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService service1 = retrofit.create(RetrofitService.class);
        Call<Supporters> call = service1.getSupporter(UserStatic.userId);

        call.enqueue(new Callback<Supporters>() {
            @Override
            public void onResponse(Call<Supporters> call, Response<Supporters> response) {
                supporters=response.body();

                adapter = new Adapter2();
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Supporters> call, Throwable t) {

            }
        });

    }


    public class Adapter2 extends RecyclerView.Adapter<Adapter2.Holder2> {
        ArrayList<SupporterData> list;
        Adapter2() {
            this.list = supporters.supporter;
        }
        @NonNull
        @Override
        public Holder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext() ;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            View view = inflater.inflate(R.layout.supporter_item, parent, false);
            return new Holder2(view);
        }
        @Override public void onBindViewHolder(@NonNull Holder2 holder, int position) {
            holder.suname.setText(list.get(position).name);
            holder.suintroduce.setText(list.get(position).introduce);
            holder.showstar.setText(list.get(position).star);
        }
        @Override public int getItemCount() {
            return list.size();
        }

        class Holder2 extends RecyclerView.ViewHolder {
            TextView suname;
            TextView suintroduce;
            TextView showstar;

            public Holder2(@NonNull View itemView) {
                super(itemView);
                suname= itemView.findViewById(R.id.suname);
                suintroduce= itemView.findViewById(R.id.suintroduce);
                showstar= itemView.findViewById(R.id.showstar);

                /*itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION)
                        {
                            // click event
                            Intent intent = new Intent(getApplicationContext(),OtherProfile.class);
                            intent.putExtra("id", items.get(pos).userId);
                            intent.putExtra("name", items.get(pos).name);
                            //intent.putExtra("location", items.get(pos).introduce);
                            //사진도 보내?
                            intent.putExtra("interests", items.get(pos).interests);
                            startActivity(intent);

                        }
                    }
                });*/
            }

        }
    }

}
