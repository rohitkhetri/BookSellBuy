package com.example.booksellbuy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksellbuy.Fragments.Account_Fragment;
import com.example.booksellbuy.R;

public class Spash_Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Intent intentHome = new Intent(Spash_Activity.this, MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intentHome);
            }
        },2000);
    }
}