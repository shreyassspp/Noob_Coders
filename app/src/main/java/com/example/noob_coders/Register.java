package com.example.noob_coders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText mfullName,mEmail,mPassword,mPhone;
    Button mRegisterButton;
    TextView mLoginBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore db;


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
        db= FirebaseFirestore.getInstance();


        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterButton.setOnClickListener(v -> {
            String memail = mEmail.getText().toString().trim();
            String mpassword = mPassword.getText().toString().trim();
            String m_name = mfullName.getText().toString().trim();
            String m_phone = mPhone.getText().toString().trim();

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

            fAuth.createUserWithEmailAndPassword(memail,mpassword).addOnCompleteListener(task -> {

                if(task.isSuccessful())
                {

                    Map<String,Object> user = new HashMap<>();
                    user.put("Full Name",m_name);
                    user.put("E-Mail",memail);
                    user.put("PhoneNumber",m_phone);

                    db.collection("user")
                            .add(user)
                            .addOnSuccessListener(documentReference -> {
                                Toast.makeText(Register.this,"User Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }).addOnFailureListener(e -> {

                                Toast.makeText(Register.this,"Error Occured " +task.getException(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            });
                }
                else
                {
                    Toast.makeText(Register.this,"Error Occured " +task.getException(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            });

        });

        mLoginBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),LoginActivity.class)));

    }
}