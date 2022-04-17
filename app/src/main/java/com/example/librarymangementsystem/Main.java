package com.example.librarymangementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_xml);

        text = findViewById(R.id.usnText);

        Intent i = getIntent();
        String s = i.getStringExtra("message");

        s = s.substring(0,10);
        s =s.toUpperCase();
        text.setText(s);


    }
}
