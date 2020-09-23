package com.example.girviganth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddBranch extends AppCompatActivity {
    EditText branch_input;
    Button bt_branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        branch_input = findViewById(R.id.editTextBranchName);

        bt_branch = findViewById(R.id.btAddBranch);
        bt_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper (AddBranch.this);
                myDB.addBranch(branch_input.getText().toString().trim());
                loadAddMoreBranch();
            }
        });
    }

    private void loadAddMoreBranch()
    {
        startActivity(new Intent(this,AddMoreBranch.class));
        finish();
    }
}
