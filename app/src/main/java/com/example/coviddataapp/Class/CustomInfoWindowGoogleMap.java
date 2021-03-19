package com.example.coviddataapp.Class;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coviddataapp.InfoWindowData;
import com.example.coviddataapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.custom_info_window, null);

        TextView confirmed = view.findViewById(R.id.tv_confirmed);
        TextView recovered = view.findViewById(R.id.tv_recovered);

        TextView critical = view.findViewById(R.id.tv_critical);
        TextView death = view.findViewById(R.id.tv_death);



        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();


        confirmed.setText(infoWindowData.getConfirmed());
        recovered.setText(infoWindowData.getRecovered());
        critical.setText(infoWindowData.getCritical());
        death.setText(infoWindowData.getDeath());


        return view;
    }
}
