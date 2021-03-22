package com.example.coviddataapp.maps;

import android.app.Activity;

import com.example.coviddataapp.Class.CustomInfoWindowGoogleMap;
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

public class MapPresenter implements MapsContract.Presenter, OnMapReadyCallback {

    Activity activity;
    MapsContract.View view;
    GoogleMap mMap;
    SupportMapFragment mapFragment;

    public MapPresenter( MapsContract.View view) {
        this.view = view;
        activity = (MapsActivity) view;
        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(3);
        LatLng location = new LatLng(view.getlat(), view.getlon());
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle((MapsActivity) view, R.raw.mapstyle_night));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location)
                .title(view.getCountryName())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


        InfoWindowData info = new InfoWindowData();
        info.setConfirmed("Confirmed:" + view.getconfirmed());
        info.setRecovered("Recovered:" + view.getrecovered());
        info.setCritical("Critical:" +view.getcritical());
        info.setDeath("Death:" + view.getdeath());

        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap((MapsActivity) view);
        Marker m = mMap.addMarker(markerOptions);
        m.setTag(info);
        m.showInfoWindow();
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    @Override
    public SupportMapFragment getSupportMapFragment() {
        return mapFragment;
    }
}
