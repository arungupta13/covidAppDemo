package com.example.coviddataapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MapApi {


    @Headers("x-rapidapi-key:aec37b08cbmsh0545a1a6d96cc7dp167e00jsn0f0926dcaf62")
    @GET("country")
    Call<List<CovidModel>> getData(@Query("name") String country);

}
