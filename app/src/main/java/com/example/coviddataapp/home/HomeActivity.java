package com.example.coviddataapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coviddataapp.maps.MapsActivity;
import com.example.coviddataapp.R;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    EditText editTextCountryName;
    Button buttonGetData;
    Double lat, lon;
    String confirmed, recovered, critical, death;
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomePresenter(this);
        editTextCountryName = findViewById(R.id.et_country_name);
        buttonGetData = findViewById(R.id.btn_getdata);
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationdata();
            }
        });

    }

    @Override
    public void locationdata() {

        presenter.locationdata();
    }

    @Override
    public void launchMapActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(MapsActivity.COUNTRY_NAME, editTextCountryName.getText().toString());
        intent.putExtra(MapsActivity.LATITUDE, lat);
        intent.putExtra(MapsActivity.LONGITUDE, lon);
        intent.putExtra(MapsActivity.CONFIRMED, confirmed);
        intent.putExtra(MapsActivity.RECOVERED, recovered);
        intent.putExtra(MapsActivity.CRITICAL, critical);
        intent.putExtra(MapsActivity.DEATH, death);
        startActivity(intent);
    }


    @Override
    public void setlat(Double latitude) {
        this.lat = latitude;
    }

    @Override
    public void setlon(Double longitude) {
        this.lon = longitude;
    }

    @Override
    public void setconfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public void setrecovered(String recovered) {
        this.recovered = recovered;
    }

    @Override
    public void setcritical(String critical) {
        this.critical = critical;
    }

    @Override
    public void setdeath(String death) {
        this.death = death;
    }

    @Override
    public String getCountry() {
        return editTextCountryName.getText().toString();
    }
}