package com.example.librarymangementsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


public class Main extends AppCompatActivity {

    TextView text;

    View home;
    View book;
    View about;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_xml);

        text = findViewById(R.id.usnText);

        home = findViewById(R.id.homeButton);
        book = findViewById(R.id.bookButton);
        about = findViewById(R.id.aboutButton);

//        Intent i = getIntent();
//        String s = i.getStringExtra("message");
//
//        s = s.substring(0,10);
//        s =s.toUpperCase();

        String s = "4NM19IS120";
        text.setText(s);

//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Main.this,Main.class);
//                startActivity(intent);
//            }
//        });

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
                for (int i = 0; i < 500; i++){
                    for (int j = 0; j < 250; j++){
                        bitmap.setPixel(i,j,bitMatrix.get(i,j)? Color.BLACK: Color.WHITE);
                    }
                }
                imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }



    }
}
