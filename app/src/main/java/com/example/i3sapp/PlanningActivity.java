package com.example.i3sapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlanningActivity extends AppCompatActivity {

    private Button mAddPlanning;
    private Button mSeePlanning;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Nos Planning");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAddPlanning = (Button) findViewById(R.id.add_planning);
        mSeePlanning = (Button) findViewById(R.id.see_planning);

        mAddPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(PlanningActivity.this, AddPlanningActivity.class);
                startActivity(intent);
            }
        });

        mSeePlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(PlanningActivity.this, SeePlanningActivity.class);
                startActivity(intent);
            }
        });

    }
}