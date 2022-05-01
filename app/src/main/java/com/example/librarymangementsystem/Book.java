package com.example.librarymangementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Book extends AppCompatActivity {

    View home;
    View book;
    View about;

    TextView isc;
    TextView csc;
    TextView cv;
    TextView mech;
    TextView eac;
    TextView eae;
    TextView bio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        home = findViewById(R.id.homeButton);
        book = findViewById(R.id.bookButton);
        about = findViewById(R.id.aboutButton);

        isc = findViewById(R.id.informationScience);
        csc = findViewById(R.id.computerScience);
        cv = findViewById(R.id.civil);
        mech = findViewById(R.id.mechanical);
        eac = findViewById(R.id.eac);
        eae = findViewById(R.id.eae);
        bio = findViewById(R.id.biotech);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Book.this,Main.class);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Book.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Book.this,About.class);
                    startActivity(intent);
                }
        });

        isc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call(isc.getText().toString());
            }
        });

        csc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call(csc.getText().toString());
            }
        });

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call(cv.getText().toString());
            }
        });

        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call(mech.getText().toString());
            }
        });

        eac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call(eac.getText().toString());
            }
        });

        eae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call(eae.getText().toString());
            }
        });

        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call(bio.getText().toString());
            }
        });


    }

    private void Call(String s){
        Intent intent = new Intent(Book.this,Sem.class);
        intent.putExtra("clickedOn",s);
        startActivity(intent);

    }


}