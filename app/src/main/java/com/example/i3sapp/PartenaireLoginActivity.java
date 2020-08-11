package com.example.i3sapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class PartenaireLoginActivity extends AppCompatActivity {

    private TextInputLayout mLoginEmailPart;
    private TextInputLayout mLoginPasswordPart;
    private Button mLogin_btnPart;

    private FirebaseAuth mAuth;

    private ProgressDialog mLoginProgressPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partenaire_login);

        mAuth = FirebaseAuth.getInstance();

       /* mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");*/

        mLoginProgressPart = new ProgressDialog(this);

        mLoginEmailPart = (TextInputLayout) findViewById(R.id.login_Part_email);
        mLoginPasswordPart = (TextInputLayout) findViewById(R.id.login_Part_password);
        mLogin_btnPart = (Button) findViewById(R.id.login_Part_btn);

        mLogin_btnPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email = mLoginEmailPart.getEditText().getText().toString();
                String password = mLoginPasswordPart.getEditText().getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password))
                {

                    mLoginProgressPart.setTitle("Login In");
                    mLoginProgressPart.setMessage("Please wait wile we check your credential");
                    mLoginProgressPart.setCanceledOnTouchOutside(false);
                    mLoginProgressPart.show();

                    loginPartenaire(email, password);
                }
            }
        });
    }

    private void loginPartenaire(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()){

                    mLoginProgressPart.dismiss();

                    Intent mainIntent = new Intent(PartenaireLoginActivity.this, PartenaireHomeActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else {

                    mLoginProgressPart.hide();
                    Toast.makeText(PartenaireLoginActivity.this, "Cannot Sign in. Please check the form and try again.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}