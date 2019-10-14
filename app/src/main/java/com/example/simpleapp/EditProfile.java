package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.simpleapp.Database.DbHandler;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText username, dob, password;
    Button edit,delete,search;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        search = findViewById(R.id.searchEP);
        username = findViewById(R.id.etUsernameEP);
        dob = findViewById(R.id.etPasswordEP);
        male = findViewById(R.id.radioMaleEP);
        female = findViewById(R.id.radioFemaleEP);
        edit = findViewById(R.id.btnEditEP);
        delete = findViewById(R.id.btnDeleteEP);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHandler db = new DbHandler(getApplicationContext());
                List user = db.readAllInfo(username.getText().toString());

                if(user.isEmpty())
                {
                    Toast.makeText(EditProfile.this, "No User Found", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    username.setText(user.get(0).toString());
                    dob.setText(user.get(1).toString());
                    password.setText(user.get(2).getClass().toString());
                    if(user.get(3).toString().equals("Male"))
                    {
                        male.setChecked(true);
                    }

                    else
                    {
                        female.setChecked(true);
                    }


                }
            }



        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked())
                {
                    gender = "Male";
                }

                else
                    gender = "Female";

                DbHandler db = new DbHandler(getApplicationContext());
                boolean result = db.updateInfo(username.getText().toString(),password.getText().toString(),dob.getText().toString(),gender);

                if(result = true)
                {
                    Toast.makeText(EditProfile.this, "User update", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(EditProfile.this, "Unable to Update User", Toast.LENGTH_SHORT).show();
                }
            }
        });


    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            DbHandler db = new DbHandler(getApplicationContext());
            db.deleteInfo(username.getText().toString());

            Toast.makeText(EditProfile.this, "User deleted", Toast.LENGTH_SHORT).show();
        }
    });


    }


    
}
