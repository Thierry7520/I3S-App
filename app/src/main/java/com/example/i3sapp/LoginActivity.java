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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Users;

public class LoginActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private Button mLogin_btn;

    private TextView AdminLink, NotAdminLink;

    private String parentDbName = "Users";

    private FirebaseAuth mAuth;

    private ProgressDialog mLoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mLoginProgress = new ProgressDialog(this);

        mLoginEmail = (TextInputLayout) findViewById(R.id.login_Email);
        mLoginPassword = (TextInputLayout) findViewById(R.id.login_password);
        mLogin_btn = (Button) findViewById(R.id.login_btn);
        AdminLink = (TextView) findViewById(R.id.admin_pannel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_pannel_link);

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                mLogin_btn.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";

            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mLogin_btn.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });

        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                loginUser();
            }
        });

    }



    private void loginUser()
    {
        String password = mLoginPassword.getEditText().getText().toString();
        String phone = mLoginEmail.getEditText().getText().toString();

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mLoginProgress.setTitle("Login Account");
            mLoginProgress.setMessage("Please wait while we create your account.");
            mLoginProgress.setCanceledOnTouchOutside(false);
            mLoginProgress.show();

            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getmPhone().equals(phone))
                    {
                        if (usersData.getmPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                            mLoginProgress.dismiss();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else 
                        {
                            mLoginProgress.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                            
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Account with this " + "number do not exists.", Toast.LENGTH_SHORT).show();
                    mLoginProgress.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
