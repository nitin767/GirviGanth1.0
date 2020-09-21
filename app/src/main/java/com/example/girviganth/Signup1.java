package com.example.girviganth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Signup1 extends Fragment {
    EditText user_name, password, confirm_password;
    Button create_account;
        public static Signup1 newInstance() {
                Signup1 fragment = new Signup1();
                return fragment;
        }

        public Signup1() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.activity_signup, container,
                        false);
                return rootView;
        }
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
                // TODO Auto-generated method stub
                super.onActivityCreated(savedInstanceState);
                create_account = (Button) getView().findViewById(R.id.btCreateAccount);
                create_account.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                // TODO Auto-generated method stub
                                ((AccSetupActivity)getActivity()).onButtonClick();

                                // Here I want to get the value of edittext which is in fragment1.
                        }
                });
        }
/*@Override
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
        MyDatabaseHelper myDB = new MyDatabaseHelper (Signup1.this);
        long result = myDB.addLogin(user_name.getText().toString().trim(),
                password.getText().toString().trim());
        if (result != -1) {
                Toast.makeText(Signup1.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(Signup1.this, Signin.class);
                //intent.putExtra("customer_id",id);
                startActivity(intent);

        }
        else{
                Toast.makeText(Signup1.this, "Failed", Toast.LENGTH_SHORT).show();
        }

        }
        });
        }*/


}