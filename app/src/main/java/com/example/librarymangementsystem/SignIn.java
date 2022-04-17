package com.example.librarymangementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {



    Button signUp;
    EditText usn;
    EditText password;
    EditText confirmPasword;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);


        signUp = findViewById(R.id.signButton);
        usn = findViewById(R.id.usnForgot);
        password = findViewById(R.id.password);
        confirmPasword = findViewById(R.id.confirmPassword);


        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usn1 = usn.getText().toString();
                String pword = password.getText().toString();
                String conPword = confirmPasword.getText().toString();

                if (TextUtils.isEmpty(usn1)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if (TextUtils.isEmpty(pword)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(conPword)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (pword == conPword) {
                    Toast.makeText(getApplicationContext(),
                            "Passwords don't match!!",
                            Toast.LENGTH_LONG)
                            .show();
                }



                // create new user or register new user
                mAuth.createUserWithEmailAndPassword(usn1, pword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Registration successful!", Toast.LENGTH_LONG).show();

                                    // if the user created intent to login activity
                                    Intent intent = new Intent(SignIn.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    // Registration failed
                                    Toast.makeText(
                                            getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });
    }
}