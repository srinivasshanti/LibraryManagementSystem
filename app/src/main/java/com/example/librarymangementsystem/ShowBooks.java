package com.example.librarymangementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowBooks extends AppCompatActivity {

    View home;
    View book;
    View about;

    Spinner spinner;

    TextView textView;

    ArrayList<String> list;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);

        home = findViewById(R.id.homeButton);
        book = findViewById(R.id.bookButton);
        about = findViewById(R.id.aboutButton);

        textView = findViewById(R.id.books);

        spinner = findViewById(R.id.spinner);

//        String sem = "Information Science";
//        String clicked = "3";


        Intent old = getIntent();
        String sem = old.getStringExtra("sem");
        String clicked = old.getStringExtra("clicked");

        textView.setVisibility(View.INVISIBLE);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowBooks.this,Main.class);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowBooks.this,Book.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowBooks.this,About.class);
                startActivity(intent);
            }
        });

        Log.d("fire", "setUp is done going to firebase " + clicked + " "+sem);

        data(clicked,sem);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);

        Log.d("fire", "list has been created");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(clicked).child(sem);
        Log.d("fire", "Connected to firebase");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot :dataSnapshot.getChildren() ){
                    list.add(snapshot.getKey().toString());

                    Log.d("fire", "Accessing Data");
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowBooks.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setVisibility(View.VISIBLE);

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(clicked).child(sem).child((String) spinner.getItemAtPosition(i));
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        textView.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                textView.setText("Hello");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void data(String clicked, String sem) {

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(clicked).child(sem);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list.clear();
//                for (DataSnapshot snapshot :dataSnapshot.getChildren() ){
//                    list.add(snapshot.getKey().toString());
//
//                }
//
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }
}