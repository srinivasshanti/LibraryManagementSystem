package com.example.librarymangementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText username;
    EditText password;
    TextView forgotPassword;
    TextView signIn;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.loginButton);
        username = findViewById(R.id.usnForgot);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);

        forgotPassword = findViewById(R.id.forgotPassword);



//        usn = usn+"@nmamit.in";

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignIn.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usn = username.getText().toString();
                String pword = password.getText().toString();

                if (TextUtils.isEmpty(usn)) {
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


                mAuth.signInWithEmailAndPassword(usn, pword)
                        .addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(
                                            @NonNull Task<AuthResult> task)
                                    {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_LONG).show();

                                            // if sign-in is successful
                                            // intent to home activity
                                            Intent intent = new Intent(LoginActivity.this, Main.class);
                                            intent.putExtra("message", usn);
                                            startActivity(intent);
                                        }

                                        else {

                                            // sign-in failed
                                            Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

            }
        });
    }
}