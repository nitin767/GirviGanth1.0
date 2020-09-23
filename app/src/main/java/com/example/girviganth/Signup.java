package com.example.girviganth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity implements  View.OnClickListener {
    EditText user_name, password, confirm_password;
    Button btcreate_account;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(new PreferenceManager(this).checkPreference())
        {
                loadSignin();
        }
        setContentView(R.layout.activity_signup);

        user_name = findViewById(R.id.editTextUserName);
        password = findViewById(R.id.editTextPassword);
        confirm_password = findViewById(R.id.editTextConfirmPassword);
        btcreate_account = findViewById(R.id.btCreateAccount);
        btcreate_account.setOnClickListener(this);

}
        /*@Override
        protected void onDestroy() {
// TODO Auto-generated method stub
                MyDatabaseHelper myDB = new MyDatabaseHelper (Signup.this);
                super.onDestroy();

                myDB.close();
        }*/
        private void loadSignin()
        {
                startActivity(new Intent(this,Signin.class));
                finish();
        }

        private void loadAddBranch()
        {
                startActivity(new Intent(this,AddBranch.class));
                finish();
        }

        @Override
        public void onClick(View v) {
                switch (v.getId())
                {
                        case R.id.btCreateAccount:
                                if(ValidateUser()){
                                 loadAddBranch();
                                new PreferenceManager(this).writePreference();
                                break;
                                }
                }
        }
        private boolean ValidateUser(){
        boolean status=false;

                String userName=user_name.getText().toString();
                String firstPassword=password.getText().toString();
                String confirmPassword=confirm_password.getText().toString();
                // check if any of the fields are vaccant
                if(userName.equals("")||firstPassword.equals("")||confirmPassword.equals(""))
                {
                        Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();

                }
// check if both password matches
                if(!firstPassword.equals(confirmPassword))
                {
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();

                }
                else
                {
                        if(addUser(userName,firstPassword)!=-1){

                                status=true;
                                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                        }
                        else{
                                Toast.makeText(getApplicationContext(), "Account Creation Failed ", Toast.LENGTH_LONG).show();

                        }



                }
                return  status;

        }
        private long addUser(String UserName,String firstPassword){
                MyDatabaseHelper myDB = new MyDatabaseHelper (Signup.this);
                long result=myDB.addLogin(UserName, firstPassword);
                return result;
        }

//@Override
//public void onClick(View view) {
//        MyDatabaseHelper myDB = new MyDatabaseHelper (Signup.this);
//        long result = myDB.addLogin(user_name.getText().toString().trim(),
//                password.getText().toString().trim());
//        if (result != -1) {
//                Toast.makeText(Signup.this, "Added Successfully", Toast.LENGTH_SHORT).show();
//                Intent intent =new Intent(Signup.this, Signin.class);
//                //intent.putExtra("customer_id",id);
//                startActivity(intent);
//
//        }
//        else{
//                Toast.makeText(Signup.this, "Failed", Toast.LENGTH_SHORT).show();
//        }
//
//        }
        }