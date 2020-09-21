package com.example.girviganth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public void gotoNext(View view) {
        Intent dsp = new Intent(Signin.this,MainActivity.class);
        startActivity(dsp);
    }

    }