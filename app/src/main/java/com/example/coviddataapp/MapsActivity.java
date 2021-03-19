package com.example.coviddataapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coviddataapp.Class.CustomInfoWindowGoogleMap;
import com.example.coviddataapp.Class.RetrofitClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double lat, lon;
    String confirmed, recovered, critical, death, countryName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        locationData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(3);
        LatLng location = new LatLng(lat, lon);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_night));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location)
                .title(countryName)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


        InfoWindowData info = new InfoWindowData();
        info.setConfirmed("Confirmed:" + confirmed);
        info.setRecovered("Recovered:" + recovered);
        info.setCritical("Critical:" + critical);
        info.setDeath("Death:" + death);

        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
        Marker m = mMap.addMarker(markerOptions);
        m.setTag(info);
        m.showInfoWindow();
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    private void locationData() {
        Intent intent = getIntent();
        String country = intent.getStringExtra("COUNTRY");
        Call<List<CovidModel>> call = RetrofitClient
                .getInstance()
                .getMapApiData()
                .getData(country);
        call.enqueue(new Callback<List<CovidModel>>() {
            @Override
            public void onResponse(Call<List<CovidModel>> call, Response<List<CovidModel>> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        lat = response.body().get(0).getLatitude();
                        lon = response.body().get(0).getLongitude();
                        countryName = response.body().get(0).getCountry();
                        confirmed = String.valueOf(response.body().get(0).getConfirmed());
                        recovered = String.valueOf(response.body().get(0).getRecovered());
                        critical = String.valueOf(response.body().get(0).getCritical());
                        death = String.valueOf(response.body().get(0).getDeaths());
                    } else {
                        Toast.makeText(MapsActivity.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MapsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<CovidModel>> call, Throwable t) {
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}