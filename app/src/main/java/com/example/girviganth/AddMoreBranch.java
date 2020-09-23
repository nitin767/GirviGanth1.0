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

public class AddMoreBranch extends AppCompatActivity {
    EditText more_branch_input;
    Button bt_more_branch, bt_move_to_metal;
    Spinner spinner_branch;
    MyDatabaseHelper myDB;
    ArrayList<String> branch_id, branch_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more_branch);

        more_branch_input = findViewById(R.id.editTextMoreBranchName);

        bt_more_branch = findViewById(R.id.btAddMoreBranch);
        bt_move_to_metal = findViewById(R.id.btMoveToMetal);
        spinner_branch=findViewById(R.id.spinner_branch);
        branch_id = new ArrayList<>();
        branch_name = new ArrayList<>();
        myDB = new MyDatabaseHelper (AddMoreBranch.this);
        spinner_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        loadBranchSpinner();

        bt_more_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.addBranch(more_branch_input.getText().toString().trim());
                loadBranchSpinner();
                //loadAddMoreBranch();
            }
        });
        bt_move_to_metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAddMetal();
            }
        });
    }

    private void loadAddMetal()
    {
        startActivity(new Intent(this,AddMetal.class));
        finish();
    }
    private void loadBranchSpinner(){
        List<String> list = new ArrayList<String>();
        Cursor cursor = myDB.readAllBranch();
        branch_id.clear();
        branch_name.clear();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                branch_id.add(cursor.getString(0));
                branch_name.add(cursor.getString(1));
            }
            /*if (cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(1));//adding 2nd column data
                } while (cursor.moveToNext());
            }*/
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, branch_name);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_branch.setAdapter(dataAdapter);
    }
}
