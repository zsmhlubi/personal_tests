package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class forgot_password extends AppCompatActivity {


    EditText email;
    EditText user_number;
    EditText new_password;
    EditText confirm_new_password;
    TextView role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);


        email = (EditText) findViewById(R.id.f_enter_email);
        user_number = (EditText) findViewById(R.id.f_enter_number);
        new_password = (EditText) findViewById(R.id.f_reset_password);
        confirm_new_password = (EditText) findViewById(R.id.f_enter_corfirm_password);
        role = (TextView) findViewById(R.id.f_user_number);
    }

    public void after_reset(View view) {
        if(validate_password()){
            adding_to_databasa();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean validate_password(){
        if(!new_password.getText().toString().equals(confirm_new_password.getText().toString())){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (user_number.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter user number" , Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (email.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void back_to_login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //it creates a map which consists of all the user info and call a save function in the DataBase class to save on the database

    public void adding_to_databasa(){
        Map<String, String> map = new HashMap<>();
        map.put("email", email.getText().toString());
        map.put("user_number", user_number.getText().toString());
        map.put("password", new_password.getText().toString());

        DataBase.change_password(this, map);
    }
}