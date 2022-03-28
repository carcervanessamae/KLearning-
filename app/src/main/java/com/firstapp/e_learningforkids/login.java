package com.firstapp.e_learningforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    EditText editEmail, editPass;
    Button BtnLogin2;

    FirebaseAuth eAuth;
    FirebaseDatabase eDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        BtnLogin2 = findViewById(R.id.Login2);

        eAuth = FirebaseAuth.getInstance();
        eDatabase = FirebaseDatabase.getInstance();

    }
    public void Login(View view) {
        String email = editEmail.getText().toString().trim();
        String password = editPass.getText().toString().trim();

        eAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "Login Successfully ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, Dashboard_main.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
public void goToReg(View view) {
        Intent intent = new Intent(login.this, register.class);
        startActivity(intent);
    }
}