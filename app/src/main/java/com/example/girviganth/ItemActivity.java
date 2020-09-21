package com.example.girviganth;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
   // LinearLayout linearLayout;
    RecyclerView recyclerView2;
    FloatingActionButton add_button2;
    TextView textViewCustomerName3;
    TextView textViewFatherName3;
    TextView textViewVillage3;
    TextView textViewPhone3;
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
       // linearLayout=findViewById(R.id.linearLayout);
        recyclerView2 = findViewById(R.id.recyclerView2);
        add_button2 = findViewById(R.id.add_button2);
        textViewCustomerName3=(TextView)findViewById(R.id.textViewCustomerName3);
        textViewFatherName3 = (TextView)findViewById(R.id.textViewFatherName3);
        textViewVillage3 = (TextView)findViewById(R.id.textViewVillage3);
        textViewPhone3 = (TextView)findViewById(R.id.textViewPhone3);

        getAndSetIntentData();
        add_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ItemActivity.this, AddItem.class);
                intent.putExtra("customer_id",id);
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

        storeDataInArrays(Integer.parseInt(id));
         //for re-fresh Activity
       itemAdapter = new ItemAdapter(ItemActivity.this,this, id, item_id, item_name, metal_name,
                actual_weight, wastage_weight, net_weight, purity, metal_rate, today_value);
       try {
           recyclerView2.setAdapter(itemAdapter);
           recyclerView2.setLayoutManager(new LinearLayoutManager(ItemActivity.this));
       }catch(Exception e){

           Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
       }
    }
    //for re-fresh Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(int customer_id) {
        Cursor cursor = myDB.readAllItem(customer_id);
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(2));
                metal_name.add(cursor.getString(3));
                actual_weight.add(Float.parseFloat( cursor.getString(4)));
                wastage_weight.add(Float.parseFloat( cursor.getString(5)));
                net_weight.add(Float.parseFloat( cursor.getString(6)));
                purity.add(Float.parseFloat( cursor.getString(7)));
                metal_rate.add(Float.parseFloat( cursor.getString(8)));
                today_value.add(Float.parseFloat( cursor.getString(9)));

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
            textViewCustomerName3.setText(customer);
            textViewFatherName3.setText(father);
            textViewVillage3.setText(village);
            textViewPhone3.setText(phone.toString());

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