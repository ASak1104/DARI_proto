package com.example.app_dari;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.app_dari.Chat.Chat_List_Activity;

public class MainActivity extends AppCompatActivity {

    MapData mapData;

    Button[] buttons = new Button[7];

    int interest_index=0;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<RecyclerItem> items = new ArrayList<RecyclerItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.mylocation2);
        textView.setText(getlocation(UserStatic.latitude,UserStatic.longitude));

        ImageView myimage = findViewById(R.id.imageView2);

        Glide.with(this)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .load("http://dari-app.kro.kr/user/"+UserStatic.userId+"/image")
                .centerCrop()
                .into(myimage);

        ImageButton btn_map = (ImageButton)findViewById(R.id.btn_map);
        ImageButton btn_chat = (ImageButton)findViewById(R.id.btn_chat);
        ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);
        ImageButton btn_notify = (ImageButton)findViewById(R.id.btn_notify);

        buttons[0] = findViewById(R.id.minterest1);
        buttons[1] = findViewById(R.id.minterest2);
        buttons[2] = findViewById(R.id.minterest3);
        buttons[3] = findViewById(R.id.minterest4);
        buttons[4] = findViewById(R.id.minterest5);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        request();


        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Map_Activity.class);

                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Profile_Activity.class);

                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });
        btn_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Chat_List_Activity.class);

                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });
        btn_notify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Notify_Activity.class);

                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });


    }

    public void makeRecyclerView(int k){
        items.clear();
        for(OtherUserData otherUser: mapData.interests.get(k).otherUsers){
            RecyclerItem recyclerItem = new RecyclerItem();
            recyclerItem.userId = otherUser.userId;
            recyclerItem.name = otherUser.name;
            recyclerItem.introduce = otherUser.introduce;
            recyclerItem.location = getlocation(otherUser.location.coordinates[1],
                    otherUser.location.coordinates[0]);
            String interests="";
            for(String inerest: otherUser.interests){
                interests += "# " + inerest + "  ";
            }
            recyclerItem.interests = interests;
            items.add(recyclerItem);

        }

        adapter = new Adapter(items);
        recyclerView.setAdapter(adapter);

    }

    public String getlocation(double latitude, double longitude){
        Geocoder g = new Geocoder(getApplicationContext());
        List<Address> address = null;
        try {
            address = g.getFromLocation(latitude, longitude, 10);
            return address.get(3).getAddressLine(0).substring(5);
        } catch (Exception e) {}
        return "error";
    }

    public void interestbtclr(int k){
        for (int i = 0; i < mapData.interests.size(); i++) {
            buttons[i].setBackground(this.getResources().getDrawable(R.drawable.button2));
            buttons[i].setTextColor(Color.BLACK);
        }
        buttons[k].setBackground(this.getResources().getDrawable(R.drawable.button2_001194));
        buttons[k].setTextColor(Color.WHITE);
    }

    public void minterest1(View view){
        interestbtclr(0);
        makeRecyclerView(0);
        interest_index=0;
    }
    public void minterest2(View view){
        interestbtclr(1);
        makeRecyclerView(1);
        interest_index=1;
    }
    public void minterest3(View view){
        interestbtclr(2);
        makeRecyclerView(2);
        interest_index=2;
    }
    public void minterest4(View view){
        interestbtclr(3);
        makeRecyclerView(3);
        interest_index=3;
    }
    public void minterest5(View view){
        interestbtclr(4);
        makeRecyclerView(4);
        interest_index=4;
    }

    public void request(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dari-app.kro.kr/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService service1 = retrofit.create(RetrofitService.class);
        Call<MapData> call = service1.getPosts(UserStatic.userId);

        call.enqueue(new Callback<MapData>() {
            @Override
            public void onResponse(Call<MapData> call, Response<MapData> response) {

                mapData =response.body();

                for (int i = 0; i < mapData.interests.size(); i++) {
                    buttons[i].setVisibility(View.VISIBLE);
                    buttons[i].setText("# " + mapData.interests.get(i).name);
                }

                interestbtclr(0);
                makeRecyclerView(0);
            }

            @Override
            public void onFailure(Call<MapData> call, Throwable t) {

            }
        });

    }

    public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        ArrayList<RecyclerItem> list;
        Adapter(ArrayList<RecyclerItem> list) {
            this.list = list;
            Log.d("rr",list.get(0).name);
        }
        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext() ;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            View view = inflater.inflate(R.layout.item_recycler, parent, false);
            return new Holder(view);
        }
        @Override public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.textView.setText(list.get(position).name);
            holder.textView2.setText(list.get(position).location);
            holder.textView3.setText(list.get(position).interests);

            Glide.with(getApplicationContext())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load("http://dari-app.kro.kr/user/"+items.get(position).userId+"/image")
                    .centerCrop()
                    .into(holder.imageView);
        }
        @Override public int getItemCount() {
            return list.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView textView;
            TextView textView2;
            TextView textView3;
            ImageView imageView;

            public Holder(@NonNull View itemView) {
                super(itemView);
                textView= itemView.findViewById(R.id.nameit);
                textView2= itemView.findViewById(R.id.locationit);
                textView3= itemView.findViewById(R.id.interestit);
                imageView= itemView.findViewById(R.id.imageView3);

                itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION)
                        {
                            // click event
                            Intent intent = new Intent(getApplicationContext(),OtherProfile.class);
                            intent.putExtra("userId", items.get(pos).userId);
                            intent.putExtra("name", items.get(pos).name);
                            intent.putExtra("introduce", items.get(pos).introduce);
                            intent.putExtra("interests", items.get(pos).interests);
                            startActivity(intent);

                        }
                    }
                });
            }

        }
    }

}
