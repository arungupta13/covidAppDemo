package com.example.coviddataapp.Class;

import com.example.coviddataapp.Class.API.MapApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://covid-19-data.p.rapidapi.com/";
    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;


    public RetrofitClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public MapApi getMapApiData() {
        return retrofit.create(MapApi.class);
    }

}
