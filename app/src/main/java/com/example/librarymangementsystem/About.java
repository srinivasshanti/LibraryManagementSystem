package com.example.librarymangementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity {

    View home;
    View book;
    View about;


    TextView pratheek;
    TextView shanti;
    TextView akshay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        home = findViewById(R.id.homeButton);
        book = findViewById(R.id.bookButton);
        about = findViewById(R.id.aboutButton);

        pratheek = findViewById(R.id.linkPratheek);
        pratheek.setMovementMethod(LinkMovementMethod.getInstance());

        shanti = findViewById(R.id.linkSrinivas);
        shanti.setMovementMethod(LinkMovementMethod.getInstance());

        akshay = findViewById(R.id.linkAkshay);
        akshay.setMovementMethod(LinkMovementMethod.getInstance());


        home.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                Intent intent = new Intent(About.this,Main.class);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(About.this,Book.class);
                    startActivity(intent);
                }
            });
    }
}