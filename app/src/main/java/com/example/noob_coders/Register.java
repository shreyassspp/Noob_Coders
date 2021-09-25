package com.example.noob_coders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText mfullName,mEmail,mPassword,mPhone;
    Button mRegisterButton;
    TextView mLoginBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mfullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email_id);
        mPassword = findViewById(R.id.password_1);
        mPhone = findViewById(R.id.phone);

        mRegisterButton = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar2);
        mLoginBtn = findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterButton.setOnClickListener(v -> {
            String memail = mEmail.getText().toString().trim();
            String mpassword = mPassword.getText().toString().trim();

            if(TextUtils.isEmpty(memail))
            {
                mEmail.setError("Email is Required");
                return ;
            }

            if(TextUtils.isEmpty(mpassword))
            {
                mPassword.setError("Password is Required");
                return ;
            }
            if(mpassword.length()<6)
            {
                mPassword.setError("Password must be atleast 6 characters long");
                return ;
            }

            progressBar.setVisibility(View.VISIBLE);

            //Register the User in Firebase

            fAuth.createUserWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(Register.this,"User Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Register.this,"Error Occured " +task.getException(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }

                }
            });

        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }
}