package com.example.girviganth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText user_name, password, confirm_password;
    Button create_account;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user_name = findViewById(R.id.editTextUserName);
        password = findViewById(R.id.editTextPassword);
        confirm_password = findViewById(R.id.editTextConfirmPassword);
        create_account = findViewById(R.id.btCreateAccount);
        create_account.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        MyDatabaseHelper myDB = new MyDatabaseHelper (Signup.this);
        long result = myDB.addLogin(user_name.getText().toString().trim(),
                password.getText().toString().trim());
        if (result != -1) {
                Toast.makeText(Signup.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(Signup.this, Signin.class);
                //intent.putExtra("customer_id",id);
                startActivity(intent);

        }
        else{
                Toast.makeText(Signup.this, "Failed", Toast.LENGTH_SHORT).show();
        }

        }
        });
        }


}