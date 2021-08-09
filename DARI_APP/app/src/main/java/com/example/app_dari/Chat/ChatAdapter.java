package com.example.app_dari.Chat;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
import com.bumptech.glide.request.RequestOptions;
import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ChatData> mDataSet;
    MultiTransformation option = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));
    public Activity context;


    public ChatAdapter(ArrayList<ChatData> myDataSet, Activity context){
        mDataSet = myDataSet;
        this.context = context;

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
         else if(viewType ==2) {
             v = inflater.inflate(R.layout.chat_left,parent,false);
             return new LeftViewHolder(v);
         }
         else if(viewType ==3){
             v = inflater.inflate(R.layout.right_img,parent,false);
             return new RightImageViewHolder(v);
         }
         else{
             v = inflater.inflate(R.layout.left_img,parent,false);
             return new LeftImageViewHolder(v);
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
            GlideUrl glideUrl = new GlideUrl("http://dari-app.kro.kr/user/image/"+mDataSet.get(position).getUserId() , new LazyHeaders.Builder()
                    .addHeader("authorization", LoginActivity.token)
                    .build());
            Glide.with(context)
                    .asBitmap()
                    .apply(RequestOptions.bitmapTransform(option))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load(glideUrl)
                    .centerCrop()
                    .into(((LeftViewHolder) holder).profile_view);
        }
        if(holder instanceof RightImageViewHolder) {
            ((RightImageViewHolder) holder).send_time.setText(mDataSet.get(position).getSendTime());
            GlideUrl glideUrl = new GlideUrl("http://dari-app.kro.kr/api/messenger/image/"+mDataSet.get(position).getContent() , new LazyHeaders.Builder()
                    .addHeader("authorization", LoginActivity.token)
                    .build());
            Log.d("glide", glideUrl.toString());
            Glide.with(context)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load(glideUrl)
                    .apply(RequestOptions.bitmapTransform(option))
                    .centerCrop()
                    .into(((RightImageViewHolder) holder).img_view);
        }
        if(holder instanceof  LeftImageViewHolder){
            GlideUrl glideUrl = new GlideUrl("http://dari-app.kro.kr/api/messenger/image/"+mDataSet.get(position).getContent() , new LazyHeaders.Builder()
                    .addHeader("authorization", LoginActivity.token)
                    .build());
            Log.d("glide", glideUrl.toString());
            Glide.with(context)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load(glideUrl)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(option))
                    .into(((LeftImageViewHolder) holder).img_view);
            GlideUrl glideUrl2 = new GlideUrl("http://dari-app.kro.kr/user/image/"+mDataSet.get(position).getUserId() , new LazyHeaders.Builder()
                    .addHeader("authorization", LoginActivity.token)
                    .build());
            Glide.with(context)
                    .asBitmap()
                    .apply(RequestOptions.bitmapTransform(option))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load(glideUrl2)
                    .centerCrop()
                    .into(((LeftImageViewHolder) holder).profile_view);
            ((LeftImageViewHolder) holder).name_text.setText(mDataSet.get(position).getFrom());
            ((LeftImageViewHolder) holder).send_time.setText(mDataSet.get(position).getSendTime());
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
        else if(mDataSet.get(position).getType().equals("Left"))
        {return 2;}
        else if(mDataSet.get(position).getType().equals("Right_Image")){return 3;}
        else{return 4;}

    }
    public class LeftViewHolder extends RecyclerView.ViewHolder{
         TextView msg_text;
         TextView name_text;
         TextView send_time;
         ImageView profile_view;
         LeftViewHolder(View v){
            super(v);
            profile_view = v.findViewById(R.id.profile_view);
            msg_text = v.findViewById(R.id.msg_text);
            name_text = v.findViewById(R.id.name_text);
            send_time = v.findViewById(R.id.send_time_text);
        }
    }

    public class LeftImageViewHolder extends RecyclerView.ViewHolder{
        ImageView img_view;
        TextView name_text;
        TextView send_time;
        ImageView profile_view;
        LeftImageViewHolder(View v){
            super(v);
            img_view = v.findViewById(R.id.img_view);
            name_text = v.findViewById(R.id.name_text);
            send_time = v.findViewById(R.id.send_time_text);
            profile_view = v.findViewById(R.id.profile_view);
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
    public class RightImageViewHolder extends RecyclerView.ViewHolder{
        ImageView img_view;
        TextView send_time;
        RightImageViewHolder(View v){
            super(v);
            img_view = v.findViewById(R.id.img_view);
            send_time = v.findViewById(R.id.send_time_text);
        }
    }



}
