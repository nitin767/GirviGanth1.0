package com.example.girviganth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> customer_id, customer_name, customer_father, customer_village, customer_phone;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, AddCustomer.class);
                startActivity(intent);
                                          }
        } );
        myDB = new MyDatabaseHelper(MainActivity.this);
        customer_id = new ArrayList<>();
        customer_name = new ArrayList<>();
        customer_father = new ArrayList<>();
        customer_village = new ArrayList<>();
        customer_phone = new ArrayList<>();

        storeDataInArrays();
         //for re-fresh Activity
        //customAdapter = new CustomAdapter(MainActivity.this, customer_id, customer_name, customer_father, customer_village, customer_phone);
        customAdapter = new CustomAdapter(MainActivity.this,this, customer_id, customer_name, customer_father, customer_village, customer_phone);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
    //for re-fresh Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                customer_id.add(cursor.getString(0));
                customer_name.add(cursor.getString(1));
                customer_father.add(cursor.getString(2));
                customer_village.add(cursor.getString(3));
                customer_phone.add(cursor.getString(4));

            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}