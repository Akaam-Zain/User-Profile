package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {


    EditText username,password;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = findViewById(R.id.etUsernameH);
        password = findViewById(R.id.etPasswordH);
        register = findViewById(R.id.btnRegisterH);
        login = findViewById(R.id.btnLoginH);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileManagement.class);
                startActivity(intent);
            }
        });

    }
}
