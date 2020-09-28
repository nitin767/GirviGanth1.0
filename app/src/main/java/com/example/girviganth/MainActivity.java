package com.example.girviganth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> customer_id, customer_name, customer_father, customer_village, customer_phone,customer_branch;
    CustomerAdapter customerAdapter;
    Spinner spinner_branch;
    ArrayList<String> branch_id, branch_name;
    int selectedVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        spinner_branch=findViewById(R.id.spinner);
        branch_id = new ArrayList<>();
        branch_name = new ArrayList<>();
        myDB = new MyDatabaseHelper(MainActivity.this);
        customer_id = new ArrayList<>();
        customer_name = new ArrayList<>();
        customer_father = new ArrayList<>();
        customer_village = new ArrayList<>();
        customer_phone = new ArrayList<>();
        customer_branch=new ArrayList<>();

        spinner_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String label = parent.getItemAtPosition(position).toString();
                selectedVal =Integer.parseInt( branch_id.get(position));
                // Showing selected spinner item
               //Toast.makeText(parent.getContext(), "You selected: " + label +" "+selectedVal,
                        //Toast.LENGTH_LONG).show();
                storeDataInArrays(selectedVal);
                //for re-fresh Activity
               /* Toast.makeText(parent.getContext(), parent.getContext().toString(),
                        Toast.LENGTH_LONG).show();*/
                customerAdapter = new CustomerAdapter(MainActivity.this,parent.getContext(), customer_id, customer_name, customer_father, customer_village, customer_phone);
                recyclerView.setAdapter(customerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        loadBranchSpinner();
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, AddCustomer.class);
                intent.putExtra("branch_id",String.valueOf(selectedVal));
                startActivity(intent);
                                          }
        } );
    }
    //for re-fresh Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(int branch_id) {
        customer_id.clear();
        customer_name.clear();
        customer_father.clear();
        customer_village.clear();
        customer_phone.clear();
        customer_branch.clear();
        Cursor cursor = myDB.readAllData(branch_id);
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                customer_id.add(cursor.getString(0));
                customer_branch.add(cursor.getString(1));
                customer_name.add(cursor.getString(2));
                customer_father.add(cursor.getString(3));
                customer_village.add(cursor.getString(4));
                customer_phone.add(cursor.getString(5));
            }
        }
    }

    void storeDataInArraysbySearch(int branch_id,String search) {
        customer_id.clear();
        customer_name.clear();
        customer_father.clear();
        customer_village.clear();
        customer_phone.clear();
        customer_branch.clear();
        Cursor cursor = myDB.getCustomerListByKeyword(branch_id,search);
        String count=String.valueOf( cursor.getCount());
        //Toast.makeText(this,String.valueOf( cursor.getCount()), Toast.LENGTH_SHORT).show();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        } else {
//            boolean b = cursor.moveToNext();
/*
            customer_id.add("1");
            customer_branch.add("1");
            customer_name.add("hduey");
            customer_father.add("test");
            customer_village.add("test");
            customer_phone.add("1235");*/
            while (cursor.moveToNext()){
                customer_id.add(cursor.getString(0));
                customer_branch.add(cursor.getString(1));
                customer_name.add(cursor.getString(2));
                customer_father.add(cursor.getString(3));
                customer_village.add(cursor.getString(4));
                customer_phone.add(cursor.getString(5));
            }
        }
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
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, branch_name);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_branch.setAdapter(dataAdapter);
    }

    /*@Override
    public void onResume(){
        super.onResume();

    }*/

    @Override
    //@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
         //   SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
           // searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint("Search Customer");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    //Log.d(TAG, "onQueryTextSubmit ");
              /*  Cursor cursor=myDB.getCustomerListByKeyword(selectedVal, s);
                if (cursor==null){
                    Toast.makeText(MainActivity.this,"No records found!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
                }*/

                    storeDataInArraysbySearch(selectedVal, s);
                    //Toast.makeText(MainActivity.this, searchView.getContext().toString(),Toast.LENGTH_LONG).show();
                    //for re-fresh Activity
                    customerAdapter = new CustomerAdapter(MainActivity.this,searchView.getContext() , customer_id, customer_name, customer_father, customer_village, customer_phone);
             /*       customerAdapter = new CustomerAdapter(MainActivity.this, getParent().getBaseContext()
                            , customer_id, customer_name, customer_father, customer_village, customer_phone);*/
                    recyclerView.setAdapter(customerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    // customerAdapter.swapCursor(cursor);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    //Log.d(TAG, "onQueryTextChange ");
                  /*  storeDataInArraysbySearch(selectedVal, s);
                 //   Toast.makeText(MainActivity.this, searchView.getContext().toString(),Toast.LENGTH_LONG).show();
                    //for re-fresh Activity
                    customerAdapter = new CustomerAdapter(MainActivity.this, searchView.getContext(), customer_id, customer_name, customer_father, customer_village, customer_phone);
                    recyclerView.setAdapter(customerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));*/
                    return false;
                }
            });
            return super.onCreateOptionsMenu(menu);
        //}
        //return  true;
    }
}