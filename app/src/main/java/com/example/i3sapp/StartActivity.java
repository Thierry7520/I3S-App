package com.example.i3sapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button mRegBtn;
    private Button mLogBtn;
    private TextView mPartenaireBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mAuth = FirebaseAuth.getInstance();

        mRegBtn = (Button) findViewById(R.id.start_reg_btn);
        mLogBtn = (Button) findViewById(R.id.start_log_btn);
        mPartenaireBegin = (TextView) findViewById(R.id.partenaire_begin);

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent reg_intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(reg_intent);
            }
        });

        mLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log_intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(log_intent);
            }
        });

        mPartenaireBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg_part_intent = new Intent(StartActivity.this, PartenaireRegistrationActivity.class);
                startActivity(reg_part_intent);
            }
        });


    }
}