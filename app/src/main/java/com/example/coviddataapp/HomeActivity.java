package com.example.coviddataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    EditText editTextCountryName;
    Button buttonGetData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        editTextCountryName=findViewById(R.id.et_country_name);
        buttonGetData=findViewById(R.id.btn_getdata);



        checkCovidData();

    }

    private void checkCovidData() {



        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,MapsActivity.class);
                i.putExtra("COUNTRY",editTextCountryName.getText().toString());
                startActivity(i);
            }
        });
    }
}