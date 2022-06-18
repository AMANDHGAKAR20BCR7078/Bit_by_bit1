package com.example.bit_by_bit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class chatActivity extends AppCompatActivity {

    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        tv_title = findViewById(R.id.tv_title);

        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        tv_title.setText(fullname);

    }

    public void go_back(View view) {
        finish();
    }
}