package com.example.app_dari.Interest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.R;

import java.util.ArrayList;
import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.MyViewHolder>{
    private static final String TAG = "InterestsAdapter";
    private int position;

    //리스트는 무조건 데이터를 필요로함
    private List<Interests> items=new ArrayList<>();

    public void addItem(int position ,Interests interests){
        items.add(position,interests);
        notifyDataSetChanged();

    }
    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    //껍데기만 만듬. 1번 실행
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.text_items,parent,false);
        return new MyViewHolder(view);
    }

    //껍데기에 데이터 바인딩. 2번 실행
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        Interests interests=items.get(position);
        holder.setItem(interests);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return items.size();
    }
    public void setting(ArrayList<Interests> interests){
        items = interests;
    }

    //ViewHolder : 뷰들의 책꽂이
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //규칙1
        private TextView interests_name;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //규칙2
            interests_name=itemView.findViewById(R.id.its_name);
        }

        //규칙3
        public void setItem(Interests interests){
            Log.d(TAG, "MyViewHolder: ");
            interests_name.setText(interests.getInterests_name());

        }
    }
}