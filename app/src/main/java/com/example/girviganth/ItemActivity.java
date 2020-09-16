package com.example.girviganth;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    RecyclerView recyclerView2;
    FloatingActionButton add_button2;
    //TextView textViewCustomerName3
    MyDatabaseHelper myDB;
    ArrayList<String> item_id, item_name, metal_name;
    ArrayList<Float> actual_weight, wastage_weight, net_weight, purity, metal_rate, today_value;
    ItemAdapter itemAdapter;
    String id, customer, father, village;
    Integer phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); commented by Shivi
        setContentView(R.layout.activity_item);
        recyclerView2 = findViewById(R.id.recyclerView2);
        add_button2 = findViewById(R.id.add_button2);
       // Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        getAndSetIntentData();
        add_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ItemActivity.this, AddItem.class);
                startActivity(intent);
                                          }
        } );
        myDB = new MyDatabaseHelper(ItemActivity.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        metal_name = new ArrayList<>();
        actual_weight = new ArrayList<>();
        wastage_weight = new ArrayList<>();
        net_weight = new ArrayList<>();
        purity = new ArrayList<>();
        metal_rate = new ArrayList<>();
        today_value = new ArrayList<>();

        storeDataInArrays();
         //for re-fresh Activity
        itemAdapter = new ItemAdapter(ItemActivity.this,this, id,customer, father, village,phone, item_id, item_name, metal_name,
                actual_weight, wastage_weight, net_weight, purity, metal_rate, today_value);
        recyclerView2.setAdapter(itemAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(ItemActivity.this));

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
        Cursor cursor = myDB.readAllItem();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                metal_name.add(cursor.getString(2));
                actual_weight.add(Float.parseFloat( cursor.getString(3)));
                wastage_weight.add(Float.parseFloat( cursor.getString(4)));
                net_weight.add(Float.parseFloat( cursor.getString(1)));
                purity.add(Float.parseFloat( cursor.getString(2)));
                metal_rate.add(Float.parseFloat( cursor.getString(3)));
                today_value.add(Float.parseFloat( cursor.getString(4)));

            }
        }

    }
    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("customer") &&
                getIntent().hasExtra("father") && getIntent().hasExtra("village") &&
                getIntent().hasExtra("phone")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            customer = getIntent().getStringExtra("customer");
            father = getIntent().getStringExtra("father");
            village = getIntent().getStringExtra("village");
            phone = Integer.valueOf(getIntent().getStringExtra("phone"));

            //Setting Intent Data
           /* textViewCustomerName3.setText(customer);
            father_input.setText(father);
            village_input.setText(village);
            phone_input.setText(phone.toString());
*/
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}