package com.example.app_dari.Chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.R;

import java.util.ArrayList;

public class Chat_ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Chat_List_Data> mDataSet;




    public Chat_ListAdapter(ArrayList<Chat_List_Data> myDataSet){
        mDataSet = myDataSet;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType){
        View v;
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            v = inflater.inflate(R.layout.chat_list_item,parent,false);
            return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
            ((ViewHolder) holder).name_text.setText(mDataSet.get(position).getUserNameTitle());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView msg_text;
        TextView name_text;
        TextView send_time;
        ViewHolder(View v){
            super(v);
            msg_text = v.findViewById(R.id.chat_list_text);
            name_text = v.findViewById(R.id.chat_list_name);
            send_time = v.findViewById(R.id.chat_list_time);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(v , position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v , int position);
    }
    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }


}
