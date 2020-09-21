package com.example.girviganth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItem extends AppCompatActivity {
    EditText item_name_input, metal_name_input, actual_weight_input, wastage_weight_input, purity_input, metal_rate_input;
    Button add_item;
    int customer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        item_name_input = findViewById(R.id.editTextItemName);
        metal_name_input = findViewById(R.id.editTextMetalName);
        actual_weight_input = findViewById(R.id.editTextActualWeight);
        wastage_weight_input = findViewById(R.id.editTextWastageWeight);
        purity_input = findViewById(R.id.editTextPurity);
        metal_rate_input = findViewById(R.id.editTextMetalRate);
        add_item = findViewById(R.id.btAddItem);
        getAndSetIntentData();
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper (AddItem.this);
               myDB.addItem(customer_id,item_name_input.getText().toString().trim(),
                       metal_name_input.getText().toString().trim(),
                       Float.parseFloat(actual_weight_input.getText().toString().trim()),
                       Float.parseFloat(wastage_weight_input.getText().toString().trim()),
                       Float.parseFloat(purity_input.getText().toString().trim()),
                       Float.parseFloat(metal_rate_input.getText().toString().trim())
                       );

            }
        });
    }
    void getAndSetIntentData() {
        if (getIntent().hasExtra("customer_id")) {
            //Getting Data from Intent
            customer_id = Integer.parseInt( getIntent().getStringExtra("customer_id"));


        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}