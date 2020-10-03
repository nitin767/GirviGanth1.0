package com.example.girviganth;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {
    EditText item_name_input, actual_weight_input, wastage_weight_input, purity_input, metal_rate_input;
    TextView metal_name_input;
    Button add_item, select_metal;
    int customer_id;
    ArrayList<Float> metalRate=new ArrayList<Float>();
    Float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        item_name_input = findViewById(R.id.editTextItemName);
        metal_name_input = findViewById(R.id.editTextMetalName);
        select_metal = findViewById(R.id.btSelectMetal);
        actual_weight_input = findViewById(R.id.editTextActualWeight);
        wastage_weight_input = findViewById(R.id.editTextWastageWeight);
        purity_input = findViewById(R.id.editTextPurity);
        metal_rate_input = findViewById(R.id.editTextMetalRate);
        add_item = findViewById(R.id.btAddItem);
        getAndSetIntentData();
        select_metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper (AddItem.this);
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(AddItem.this);
                //builderSingle.setIcon(R.drawable.ic_launcher);
                builderSingle.setTitle("Select One Name:-");
                Cursor cr = myDB.readAllMetal();

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddItem.this, android.R.layout.select_dialog_singlechoice);
                if(cr.getCount()>0){
                    while(cr.moveToNext()){
                        arrayAdapter.add(cr.getString(1));
                        metalRate.add(Float.parseFloat(cr.getString(2)));
                    }

                }

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        rate=metalRate.get(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(AddItem.this);
                        metal_name_input.setText(strName);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Your Selected Item is");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                                //metal_name_input.setText(which);
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();
                    }
                });
                builderSingle.show();
            }
        });
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper (AddItem.this);
               myDB.addItem(customer_id,item_name_input.getText().toString().trim(),
                       metal_name_input.getText().toString().trim(),
                       Float.parseFloat(actual_weight_input.getText().toString().trim()),
                       Float.parseFloat(wastage_weight_input.getText().toString().trim()),
                       Float.parseFloat(purity_input.getText().toString().trim()),
                       rate
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