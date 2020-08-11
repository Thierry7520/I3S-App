package com.example.i3sapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PartenaireRegistrationActivity extends AppCompatActivity {

    private TextInputLayout mDisplayNamePart;
    private TextInputLayout mEmailPart;
    private TextInputLayout mPasswordPart;
    private Button mCreateBtnPart;
    private Button mLoginBtnPart;
    

    private ProgressDialog mRegProgressPart;

    private Toolbar mToolbarPart;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partenaire_registration);

        mToolbarPart = (Toolbar) findViewById(R.id.register_toolbar);
   /*   setSupportActionBar(mToolbarPart);
        getSupportActionBar().setTitle(" Create Account ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  */

        mAuth = FirebaseAuth.getInstance();

        mDisplayNamePart = (TextInputLayout) findViewById(R.id.reg_part_display_name);
        mEmailPart = (TextInputLayout) findViewById(R.id.reg_part_email);
        mPasswordPart = (TextInputLayout) findViewById(R.id.reg_part_password);
        mCreateBtnPart = (Button) findViewById(R.id.reg_part_create_btn);
        mLoginBtnPart = (Button) findViewById(R.id.part_already_have_account);

        mLoginBtnPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(PartenaireRegistrationActivity.this, PartenaireLoginActivity.class);
                startActivity(intent);
            }
        });

        mRegProgressPart = new ProgressDialog(this);

        mCreateBtnPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String display_name = mDisplayNamePart.getEditText().getText().toString();
                String email = mEmailPart.getEditText().getText().toString();
                String password = mPasswordPart.getEditText().getText().toString();

                if (!TextUtils.isEmpty(display_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    mRegProgressPart.setTitle("Registering User");
                    mRegProgressPart.setMessage("Please wait while we create your account.");
                    mRegProgressPart.setCanceledOnTouchOutside(false);
                    mRegProgressPart.show();

                    register_Partenaire(display_name, email, password);

                }

            }
        });
    }

    private void register_Partenaire(String display_name, String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()){

                    mRegProgressPart.dismiss();

                    Intent mainIntent = new Intent(PartenaireRegistrationActivity.this, PartenaireHomeActivity.class);
                    startActivity(mainIntent);
                    finish();

                }else {

                    mRegProgressPart.hide();
                    Toast.makeText(PartenaireRegistrationActivity.this, "Cannot Sign in. Plase check the form and try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}