package com.example.app_dari.Chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.R;
import com.example.app_dari.UserStatic;

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
        ((ViewHolder) holder).msg_text.setText(mDataSet.get(position).getLastMessage());
        ((ViewHolder) holder).send_time.setText(mDataSet.get(position).getUpdatedAt().substring(11,16));

        /*GlideUrl glideUrl = new GlideUrl("http://dari-app.kro.kr/user/image/"+mDataSet.get(position).getUserNameTitle() , new LazyHeaders.Builder()
                .addHeader("authorization", UserStatic.token)
                .build());
        Glide.with(((ViewHolder) holder).chat_list_img.getContext())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(glideUrl)
                .centerCrop()
                .into(((ViewHolder) holder).chat_list_img);*/

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView msg_text;
        TextView name_text;
        TextView send_time;
        ImageView chat_list_img;
        ViewHolder(View v){
            super(v);
            msg_text = v.findViewById(R.id.chat_list_text);
            name_text = v.findViewById(R.id.chat_list_name);
            send_time = v.findViewById(R.id.chat_list_time);
            chat_list_img = v.findViewById(R.id.chat_list_img);

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
