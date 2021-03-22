package com.example.coviddataapp.maps;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coviddataapp.Class.CustomInfoWindowGoogleMap;
import com.example.coviddataapp.Class.RetrofitClient;
import com.example.coviddataapp.CovidModel;
import com.example.coviddataapp.InfoWindowData;
import com.example.coviddataapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements MapsContract.View {

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String CONFIRMED = "confirmed";
    public static final String RECOVERED = "recovered";
    public static final String CRITICAL = "critical";
    public static final String DEATH = "death";
    public static final String COUNTRY_NAME = "country_name";
    Double lat, lon;
    String confirmed, recovered, critical, death, countryName;
    MapsContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        countryName = intent.getStringExtra(COUNTRY_NAME);
        lat = intent.getDoubleExtra(LATITUDE, 0);
        lon = intent.getDoubleExtra(LONGITUDE, 0);
        confirmed = intent.getStringExtra(CONFIRMED);
        recovered = intent.getStringExtra(RECOVERED);
        critical = intent.getStringExtra(CRITICAL);
        death = intent.getStringExtra(DEATH);

        presenter=new MapPresenter(this);

        initializeSupportMapFragment();
    }

    @Override
    public Double getlat() {
        return lat;
    }

    @Override
    public Double getlon() {
        return lon;
    }

    @Override
    public String getconfirmed() {
        return confirmed;
    }

    @Override
    public String getrecovered() {
        return recovered;
    }

    @Override
    public String getcritical() {
        return critical;
    }

    @Override
    public String getdeath() {
        return death;
    }

    @Override
    public String getCountryName() {
        return countryName;
    }

    @Override
    public void initializeSupportMapFragment() {
        SupportMapFragment fragment = presenter.getSupportMapFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map, fragment)
                .commit();
    }
}