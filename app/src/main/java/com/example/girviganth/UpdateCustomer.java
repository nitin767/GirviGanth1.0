package com.example.girviganth;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCustomer extends AppCompatActivity {
    EditText customer_input, father_input, village_input, phone_input;
    Button update_customer, delete_customer;

    String id, customer, father, village;
    Integer phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        customer_input = findViewById(R.id.editTextCustomerName2);
        father_input = findViewById(R.id.editTextFatherName2);
        village_input = findViewById(R.id.editTextVillage2);
        phone_input = findViewById(R.id.editTextPhone2);
        update_customer = findViewById(R.id.btUpdateCustomer);
        delete_customer = findViewById(R.id.btDeleteCustomer);
        //First call this
        getAndSetIntentData();
        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(customer);
        }
        update_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customer = customer_input.getText().toString().trim();
                father=father_input.getText().toString().trim();
                village=village_input.getText().toString().trim();
                phone=Integer.parseInt(phone_input.getText().toString().trim());

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateCustomer.this);
                //call updateData
                myDB.updateData(id, customer, father, village, phone);
            }
        });
        delete_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog ();
            }
        });

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
            customer_input.setText(customer);
            father_input.setText(father);
            village_input.setText(village);
            phone_input.setText(phone.toString());

        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
        //Confirmation Dialog before Delete
        void confirmDialog () {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete " + customer + " ?");
            builder.setMessage("Are you sure you want to delete " + customer + " ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateCustomer.this);
                    myDB.deleteOneRow(id);
                    finish(); //automatically redirect to mainactivity
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        builder.create().show();
    }
}