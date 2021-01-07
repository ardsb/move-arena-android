package com.example.demorestful.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.demorestful.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setTitle("About Us");
    }
}