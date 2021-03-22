package com.example.coviddataapp.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public interface MapsContract {


    interface View {

        Double getlat();

        Double getlon();

        String getconfirmed();

        String getrecovered();

        String getcritical();

        String getdeath();

        String getCountryName();

        void initializeSupportMapFragment();
    }

    interface Presenter {
        SupportMapFragment getSupportMapFragment();
    }
}
