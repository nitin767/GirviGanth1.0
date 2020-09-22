package com.example.girviganth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    EditText user_name, password;
    Button btSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        user_name = findViewById(R.id.editTextUserNameToLogin);
        password = findViewById(R.id.editTextPasswordToLogin);

        btSignin = findViewById(R.id.btSignIn);
        btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseHelper myDB = new MyDatabaseHelper (Signin.this);
                String userName=user_name.getText().toString();
                String firstPassword=password.getText().toString();

// fetch the Password form database for respective user name
                String storedPassword=myDB.readOneLogin(userName);

// check if the Stored password matches with Password entered by user
                if(firstPassword.equals(storedPassword))
                {
                    Toast.makeText(Signin.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    gotoNext();

                }
                else
                {
                    Toast.makeText(Signin.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void gotoNext() {
        Intent dsp = new Intent(Signin.this,MainActivity.class);
        startActivity(dsp);
        finish();
    }

    }