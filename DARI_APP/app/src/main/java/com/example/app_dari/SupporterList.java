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

    long now;
    Date date;
    SimpleDateFormat mformat = new SimpleDateFormat("MM");
    SimpleDateFormat dformat = new SimpleDateFormat("dd");

    Button[] buttons = new Button[7];

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
