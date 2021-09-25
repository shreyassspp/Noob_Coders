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

public class LoginActivity extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginBtn;
    ProgressBar progressBar;
    TextView mCreate;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email_id);
        mPassword = findViewById(R.id.password_1);
        mCreate = findViewById(R.id.create_account);
        mLoginBtn = findViewById(R.id.login);

        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                //Authenticate the User

                fAuth.signInWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"User Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }

                        else
                        {
                            Toast.makeText(LoginActivity.this,"Error Occured " +task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}