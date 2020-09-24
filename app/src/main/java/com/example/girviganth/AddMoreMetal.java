package com.example.girviganth;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddMoreMetal extends AppCompatActivity {
    EditText more_metal_input, more_metal_rate;
    Button bt_more_metal, bt_finish;
    Spinner spinner_metal;
    MyDatabaseHelper myDB;
    ArrayList<String> metal_id, metal_name, metal_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more_metal);

        more_metal_input = findViewById(R.id.editTextMoreMetalName);
        more_metal_rate = findViewById(R.id.editTextMoreMetalRate);
        bt_more_metal = findViewById(R.id.btAddMoreMetal);
        bt_finish = findViewById(R.id.btFinish);
        spinner_metal=findViewById(R.id.spinner_metal);
        metal_id = new ArrayList<>();
        metal_name = new ArrayList<>();
        metal_rate = new ArrayList<>();
        myDB = new MyDatabaseHelper (AddMoreMetal.this);
        spinner_metal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                // On selecting a spinner item
                String label = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "You selected: " + label,
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        loadMetalSpinner();

        bt_more_metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.addMetal(more_metal_input.getText().toString().trim(),
                        Float.parseFloat(more_metal_rate.getText().toString().trim()));
                loadMetalSpinner();
            }
        });
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSignin();
            }
        });
    }

    private void loadSignin()
    {
        startActivity(new Intent(this,Signin.class));
        finish();
    }

     private void loadMetalSpinner(){
        List<String> list = new ArrayList<String>();
        Cursor cursor = myDB.readAllMetal();
        metal_id.clear();
        metal_name.clear();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                metal_id.add(cursor.getString(0));
                metal_name.add(cursor.getString(1));
            }
            /*if (cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(1));//adding 2nd column data
                } while (cursor.moveToNext());
            }*/
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, metal_name);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_metal.setAdapter(dataAdapter);
    }
}
