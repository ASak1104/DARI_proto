package com.example.app_dari;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Valued_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valued);

        ToggleButton yes1 = (ToggleButton)findViewById(R.id.yes1);
        ToggleButton yes2 = (ToggleButton)findViewById(R.id.yes2);
        ToggleButton yes3 = (ToggleButton)findViewById(R.id.yes3);
        ToggleButton no1 = (ToggleButton)findViewById(R.id.no1);
        ToggleButton no2 = (ToggleButton)findViewById(R.id.no2);
        ToggleButton no3 = (ToggleButton)findViewById(R.id.no3);
        ToggleButton star1 = (ToggleButton)findViewById(R.id.star1);
        ToggleButton star2 = (ToggleButton)findViewById(R.id.star2);
        ToggleButton star3 = (ToggleButton)findViewById(R.id.star3);
        ToggleButton star4 = (ToggleButton)findViewById(R.id.star4);
        ToggleButton star5 = (ToggleButton)findViewById(R.id.star5);

        yes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no1.setChecked(false);
            }
        });
        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    yes1.setChecked(false);
            }
        });
        yes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    no2.setChecked(false);
            }
        });
        no2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    yes2.setChecked(false);
            }
        });
        yes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    no3.setChecked(false);
            }
        });
        no3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    yes3.setChecked(false);
            }
        });
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    star2.setChecked(false);
                star3.setChecked(false);
                star4.setChecked(false);
                star5.setChecked(false);
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setChecked(false);
                star3.setChecked(false);
                star4.setChecked(false);
                star5.setChecked(false);
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star2.setChecked(false);
                star1.setChecked(false);
                star4.setChecked(false);
                star5.setChecked(false);
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star2.setChecked(false);
                star3.setChecked(false);
                star1.setChecked(false);
                star5.setChecked(false);
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star2.setChecked(false);
                star3.setChecked(false);
                star4.setChecked(false);
                star1.setChecked(false);
            }
        });

    }
    }