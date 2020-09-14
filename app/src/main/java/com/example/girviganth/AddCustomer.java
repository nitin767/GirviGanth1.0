package com.example.girviganth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCustomer extends AppCompatActivity {
    EditText customer_input, father_input, village_input, phone_input;
    Button add_customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        customer_input = findViewById(R.id.editTextCustomerName);
        father_input = findViewById(R.id.editTextFatherName);
        village_input = findViewById(R.id.editTextVillage);
        phone_input = findViewById(R.id.editTextPhone);
        add_customer = findViewById(R.id.btAddCustomer);
        add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper (AddCustomer.this);
                myDB.addCustomer(customer_input.getText().toString().trim(),
                        father_input.getText().toString().trim(),
                        village_input.getText().toString().trim(),
                        Integer.parseInt(phone_input.getText().toString().trim()));

            }
        });
    }
}