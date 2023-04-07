package com.example.movieseats;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button btnRegister;
    private TextView textLogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        btnRegister  = findViewById(R.id.register);
        textLogin = findViewById(R.id.text_login);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
    @SuppressLint("NotConstructor")
    private void Register()
    {
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(user.isEmpty())
        {
            email.setError("Email can not be empty");
        }
        if(pass.isEmpty())
        {
            password.setError("Password can not be empty");
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Registration Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}