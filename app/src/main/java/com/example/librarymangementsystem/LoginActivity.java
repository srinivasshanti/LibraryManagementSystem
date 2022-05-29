package com.example.librarymangementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;



public class LoginActivity extends AppCompatActivity {


    SignInButton signInButton;


    FirebaseAuth mAuth;

    SharedPreferences pos;
    public String fileName = "storage.xml";
    GoogleSignInClient mGoogleSignInClient;

    private static int RC_SIGN_IN;

//    private BeginSignInRequest signInRequest;
//
//    private SignInClient oneTapClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();




        signInButton = findViewById(R.id.sign_in_button);




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        signInButton.setSize(SignInButton.SIZE_STANDARD);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn1();
            }
        });
    }

    private void signIn1() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }



    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personEmail = acct.getEmail();

                if (personEmail.endsWith("@nmamit.in")){
                    personEmail = personEmail.substring(0,10);

                    pos = getSharedPreferences(fileName, 0);
                    SharedPreferences.Editor editor = pos.edit();
                    editor.putString("pwd",personEmail);
                    editor.commit();


                    Intent intent = new Intent(LoginActivity.this, Main.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Please use College Mail ID", Toast.LENGTH_SHORT).show();
                    signOut();
                }


            }
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("Message", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(this, "To exit the app Please press home button", Toast.LENGTH_SHORT).show();
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }
}