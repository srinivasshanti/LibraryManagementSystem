package com.example.librarymangementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class ForgotPassword extends AppCompatActivity {

    Button signUp;
    EditText usn;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass_xml);

        signUp = findViewById(R.id.signButton);
        usn = findViewById(R.id.usnForgot);


        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usn1 = usn.getText().toString();

                if (TextUtils.isEmpty(usn1)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }



                // create new user or register new user
//                mAuth.createUserWithEmailAndPassword(usn1, pword)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task)
//                            {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(getApplicationContext(),"Registration successful!", Toast.LENGTH_LONG).show();
//
//                                    // if the user created intent to login activity
//                                    Intent intent = new Intent(ForgotPassword.this,LoginActivity.class);
//                                    startActivity(intent);
//                                }
//                                else {
//                                    // Registration failed
//                                    Toast.makeText(
//                                            getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });

                mAuth.sendPasswordResetEmail(usn1)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Please check your Mail", Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }
        });
    }
}
