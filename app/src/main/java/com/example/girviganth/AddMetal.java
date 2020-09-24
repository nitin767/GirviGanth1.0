package com.example.girviganth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMetal extends AppCompatActivity {
    EditText metal_input, metal_rate;
    Button bt_metal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_metal);

        metal_input = findViewById(R.id.editTextMetalName);
        metal_rate = findViewById(R.id.editTextMetalRate);
        bt_metal = findViewById(R.id.btAddMetal);
        bt_metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper (AddMetal.this);
                myDB.addMetal(metal_input.getText().toString().trim(),
                        Float.parseFloat(metal_rate.getText().toString().trim()));
                loadAddMoreMetal();
            }
        });
    }

    private void loadAddMoreMetal()
    {
        startActivity(new Intent(this,AddMoreMetal.class));
        finish();
    }
}
