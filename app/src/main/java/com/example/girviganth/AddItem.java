package com.example.girviganth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddItem extends AppCompatActivity {
    EditText item_name_input, metal_name_input, actual_weight_input, wastage_weight_input, purity_input, metal_rate_input;
    Button add_item;

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
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper (AddItem.this);
               /* myDB.addItem(customer_input.getText().toString().trim(),
                        father_input.getText().toString().trim(),
                        village_input.getText().toString().trim(),
                        Integer.parseInt(phone_input.getText().toString().trim())); Commented by Shivi*/

            }
        });
    }
}