package com.example.librarymangementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


public class Main extends AppCompatActivity {

    TextView text;

    Button logout;

    View home;
    View book;
    View about;

    ImageView imageView;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_xml);

        text = findViewById(R.id.usnText);

        logout = findViewById(R.id.logout);

        home = findViewById(R.id.homeButton);
        book = findViewById(R.id.bookButton);
        about = findViewById(R.id.aboutButton);

        Intent i = getIntent();

        SharedPreferences pos = getSharedPreferences("storage.xml", 0);
        String s = pos.getString("pwd", "");

        s =s.toUpperCase();

        text.setText(s);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this,Book.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this,About.class);
                startActivity(intent);
            }
        });

            imageView = findViewById(R.id.barCode);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(s, BarcodeFormat.CODE_128, 500, 250);
                Bitmap bitmap = Bitmap.createBitmap(500, 250, Bitmap.Config.RGB_565);
                for (int x = 0; x < 500; x++){
                    for (int j = 0; j < 250; j++){
                        bitmap.setPixel(x,j,bitMatrix.get(x,j)? Color.BLACK: Color.WHITE);
                    }
                }
                imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        Intent intent = new Intent(Main.this,LoginActivity.class);
                        startActivity(intent);
                        System.exit(0);
                    }
                });
    }
}
