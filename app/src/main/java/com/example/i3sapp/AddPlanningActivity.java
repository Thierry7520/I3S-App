package com.example.i3sapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlanningActivity extends AppCompatActivity {

     Spinner mSpinnerGenres;
     TextInputLayout mChannel;
     TextInputLayout mType;
     TextInputLayout mPostDate;
     TextInputLayout mDescription;
     TextInputLayout mSponsoring;
     TextInputLayout mHachtags;
     Button mBtnValidate;

    private ProgressDialog mRegProgress;

    private Toolbar mToolbar;

    DatabaseReference databasePlanning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_planning);

        databasePlanning = FirebaseDatabase.getInstance().getReference("plannings");

        mRegProgress = new ProgressDialog(this);

        mSpinnerGenres = (Spinner) findViewById(R.id.spinnerGeneres);
        mPostDate = (TextInputLayout) findViewById(R.id.planning_post_date);
        mChannel = (TextInputLayout)  findViewById(R.id.planning_channel);
        mType = (TextInputLayout) findViewById(R.id.planning_type);
        mDescription = (TextInputLayout) findViewById(R.id.planning_description);
        mSponsoring = (TextInputLayout) findViewById(R.id.planning_sponsoring);
        mHachtags = (TextInputLayout) findViewById(R.id.Planning_hachtags);
        mBtnValidate =(Button) findViewById(R.id.btn_planning_validate);

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Planning");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addPlanning();
            }
        });

    }

    private void addPlanning()
    {
        String genre = mSpinnerGenres.getSelectedItem().toString();
        String postDate = mPostDate.getEditText().getText().toString().trim();
        String channel = mChannel.getEditText().getText().toString().trim();
        String type = mType.getEditText().getText().toString().trim();
        String description = mDescription.getEditText().getText().toString().trim();
        String sposoring = mSponsoring.getEditText().getText().toString().trim();
        String hachtags = mHachtags.getEditText().getText().toString().trim();

        if (!TextUtils.isEmpty(postDate)){

           String id = databasePlanning.push().getKey();

           Planning planning = new Planning(id, genre, postDate, channel, type, description, sposoring,hachtags);

           databasePlanning.child(id).setValue(planning);

           Toast.makeText(this, "Planning Added", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "You should enter  a name", Toast.LENGTH_SHORT).show();
        }
    }
}