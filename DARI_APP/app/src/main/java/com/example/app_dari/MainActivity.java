package com.example.app_dari;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

        private FragmentManager fragmentManager;
        private Main_Fragment main_fragment;
        private Map_Fragment map_fragment;
        private Profile_Fragment profile_fragment;
        private Chat_Fragment chat_fragment;
        private Notify_Fragment notify_fragment;
        private FragmentTransaction transaction;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mainpage);


            main_fragment = new Main_Fragment();
            map_fragment = new Map_Fragment();
            profile_fragment = new Profile_Fragment();
            chat_fragment = new Chat_Fragment();
            notify_fragment = new Notify_Fragment();

            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frameLayout, main_fragment).commitAllowingStateLoss();




        }
    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.btn_main:
                transaction.replace(R.id.frameLayout, main_fragment).commitAllowingStateLoss();
                break;
            case R.id.btn_map:
                transaction.replace(R.id.frameLayout, map_fragment).commitAllowingStateLoss();
                break;
            case R.id.btn_profile:
                transaction.replace(R.id.frameLayout, profile_fragment).commitAllowingStateLoss();
                break;
            case R.id.btn_chat:
                transaction.replace(R.id.frameLayout, chat_fragment).commitAllowingStateLoss();
                break;
            case R.id.btn_notify:
                transaction.replace(R.id.frameLayout, notify_fragment).commitAllowingStateLoss();
                break;
        }
    }

    }
