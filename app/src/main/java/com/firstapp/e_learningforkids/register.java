package com.firstapp.e_learningforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    EditText editFname, editLname, editEmail, editPass;
    Button Login, BtnReg;
    FirebaseAuth eAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editFname = findViewById(R.id.editFname);
        editLname = findViewById(R.id.editLname);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        Login = findViewById(R.id.Login);
        BtnReg = findViewById(R.id.BtnReg);

        eAuth = FirebaseAuth.getInstance();


    }

    public void Register(View view) {


       String fname = editFname.getText().toString().trim();
       String lname = editLname.getText().toString().trim();
       String email = editEmail.getText().toString().trim();
       String password = editPass.getText().toString().trim();


        if(TextUtils.isEmpty(lname)) {
            editLname.setError("Last Name is Required.");
            return;
        }

        if(TextUtils.isEmpty(password)){
            editPass.setError("Password is Required.");
            return;
        }
        if(password.length() <6){
            editPass.setError("Password must be >= 6 Characters");
            return;
        }

        eAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    User user = new User (fname, lname, email);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(register.this, login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(register.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }); // end
                } else {
                    Toast.makeText(register.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }); // end curly braces
    }///

    public void goToLogin(View view) {
        Intent intent = new Intent(register.this, login.class);
        startActivity(intent);
    }
}
