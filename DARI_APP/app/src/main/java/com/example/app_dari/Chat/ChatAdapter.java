package com.example.app_dari.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ChatData> mDataSet;



    public ChatAdapter(ArrayList<ChatData> myDataSet){
        mDataSet = myDataSet;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType){
         View v;
         Context context = parent.getContext();

         LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         if(viewType ==1){
             v = inflater.inflate(R.layout.chat_right,parent,false);
             return new RightViewHolder(v);
         }
         else {
             v = inflater.inflate(R.layout.chat_left,parent,false);
             return new LeftViewHolder(v);
         }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
        if(holder instanceof RightViewHolder) {
            ((RightViewHolder) holder).msg_text.setText(mDataSet.get(position).getContent());
            ((RightViewHolder) holder).send_time.setText(mDataSet.get(position).getSendTime());
        }
        if(holder instanceof  LeftViewHolder){
            ((LeftViewHolder) holder).msg_text.setText(mDataSet.get(position).getContent());
            ((LeftViewHolder) holder).name_text.setText(mDataSet.get(position).getFrom());
            ((LeftViewHolder) holder).send_time.setText(mDataSet.get(position).getSendTime());
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(mDataSet.get(position).getType().equals("Right")){
            return 1;
        }
        else {return 2;}
    }
    public class LeftViewHolder extends RecyclerView.ViewHolder{
         TextView msg_text;
         TextView name_text;
         TextView send_time;
         LeftViewHolder(View v){
            super(v);
            msg_text = v.findViewById(R.id.msg_text);
            name_text = v.findViewById(R.id.name_text);
            send_time = v.findViewById(R.id.send_time_text);
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView msg_text;
        TextView send_time;
        RightViewHolder(View v){
            super(v);
            msg_text = v.findViewById(R.id.msg_text);
            send_time = v.findViewById(R.id.send_time_text);
        }
    }



}
