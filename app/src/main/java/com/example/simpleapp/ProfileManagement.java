package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.simpleapp.Database.DbHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText username, dob, password;
    Button update,add;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.etUsernamePM);
        dob = findViewById(R.id.etDobPM);
        password = findViewById(R.id.etPasswordPM);
        update = findViewById(R.id.btnUpdatePM);
        male = findViewById(R.id.radioMalePM);
        female = findViewById(R.id.radioMalePM);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked())
                {
                    gender = "Male";
                }

                else
                {
                    gender = "Female";
                }

                DbHandler db = new DbHandler(getApplicationContext());
                long result = db.addInfo(username.getText().toString(),password.getText().toString(),dob.getText().toString(),gender);
                Toast.makeText(ProfileManagement.this, "User inserted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);
            }
        });

    }
}
