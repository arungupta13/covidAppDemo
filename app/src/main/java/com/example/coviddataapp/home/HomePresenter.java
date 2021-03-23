package com.example.coviddataapp.home;


import android.widget.Toast;

import com.example.coviddataapp.Class.RetrofitClient;
import com.example.coviddataapp.CovidModel;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class HomePresenter implements HomeContract.Presenter {

    @Inject
    HomeContract.View view;

    Double lat, lon;
    String confirmed, recovered, critical, death, countryName;

    @Inject
    public HomePresenter() {

    }
    @Override
    public void locationdata() {


    RetrofitClient retrofitClient= RetrofitClient.getInstance();
    retrofitClient.getMapApiData()
            .getData(view.getCountry())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<CovidModel>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull List<CovidModel> models) {
                    try {
                        if ( models.get(0) != null) {
                            lat = models.get(0).getLatitude();
                            view.setlat(lat);
                            lon = models.get(0).getLongitude();
                            view.setlon(lon);
//                      countryName = response.body().get(0).getCountry();
                            confirmed = String.valueOf(models.get(0).getConfirmed());
                            view.setconfirmed(confirmed);
                            recovered = String.valueOf(models.get(0).getRecovered());
                            view.setrecovered(recovered);
                            critical = String.valueOf(models.get(0).getCritical());
                            view.setcritical(critical);
                            death = String.valueOf(models.get(0).getDeaths());
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
                public void onError(@NonNull Throwable e) {
                    Toast.makeText((HomeActivity)view ,e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete() {

                }
            });

    }
}
