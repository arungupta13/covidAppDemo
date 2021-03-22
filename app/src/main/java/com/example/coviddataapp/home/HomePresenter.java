package com.example.coviddataapp.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.coviddataapp.Class.RetrofitClient;
import com.example.coviddataapp.CovidModel;
import com.example.coviddataapp.maps.MapsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    Double lat, lon;
    String confirmed, recovered, critical, death, countryName;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }
    @Override
    public void locationdata() {
        Call<List<CovidModel>> call = RetrofitClient
                .getInstance()
                .getMapApiData()
                .getData(view.getCountry());
        call.enqueue(new Callback<List<CovidModel>>() {
            @Override
            public void onResponse(Call<List<CovidModel>> call, Response<List<CovidModel>> response) {
                try {
                    Log.v("Response", ""+response.code());
                    if (response.isSuccessful() && response.body() != null) {
                        lat = response.body().get(0).getLatitude();
                        view.setlat(lat);
                        lon = response.body().get(0).getLongitude();
                        view.setlon(lon);
//                      countryName = response.body().get(0).getCountry();
                        confirmed = String.valueOf(response.body().get(0).getConfirmed());
                        view.setconfirmed(confirmed);
                        recovered = String.valueOf(response.body().get(0).getRecovered());
                        view.setrecovered(recovered);
                        critical = String.valueOf(response.body().get(0).getCritical());
                        view.setcritical(critical);
                        death = String.valueOf(response.body().get(0).getDeaths());
                        view.setdeath(death);
                        view.launchMapActivity();
                    } else {
                        Toast.makeText((HomeActivity) view, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                         Toast.makeText((HomeActivity) view, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<CovidModel>> call, Throwable t) {
                Toast.makeText((HomeActivity) view, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
