package com.example.app_dari.Chat;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.app_dari.R;

import java.util.Objects;


public class AlertDialog extends Dialog {

    private Context mContext;
    private LinearLayout yes;
    private LinearLayout no;
    private LinearLayout other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertdialog);

        yes = (LinearLayout)findViewById(R.id.yes);
        no = (LinearLayout)findViewById(R.id.no);
        other = (LinearLayout)findViewById(R.id.other);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }
    public AlertDialog(Context mContext){
        super(mContext);
        this.mContext = mContext;
    }
}