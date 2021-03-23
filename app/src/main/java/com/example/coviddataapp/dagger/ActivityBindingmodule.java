package com.example.coviddataapp.dagger;


import com.example.coviddataapp.home.HomeActivity;
import com.example.coviddataapp.home.HomeContract;
import com.example.coviddataapp.home.HomeModule;
import com.example.coviddataapp.home.HomePresenter;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public class ActivityBindingmodule {

    @Provides
    HomeContract.Presenter getPresenter() {

        return new HomePresenter();
    }


}
