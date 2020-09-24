package com.example.girviganth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity {
    EditText customer_input, father_input, village_input, phone_input;
    Button add_customer;
    int branch_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        customer_input = findViewById(R.id.editTextCustomerName);
        father_input = findViewById(R.id.editTextFatherName);
        village_input = findViewById(R.id.editTextVillage);
        phone_input = findViewById(R.id.editTextPhone);
        add_customer = findViewById(R.id.btAddCustomer);
        getAndSetIntentData();

        add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper (AddCustomer.this);
                myDB.addCustomer(branch_id,customer_input.getText().toString().trim(),
                        father_input.getText().toString().trim(),
                        village_input.getText().toString().trim(),
                        Integer.parseInt(phone_input.getText().toString().trim()));
                loadMainActivity();
            }
        });
    }

    private void loadMainActivity()
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("branch_id")) {
            //Getting Data from Intent
            branch_id = Integer.parseInt( getIntent().getStringExtra("branch_id"));
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}