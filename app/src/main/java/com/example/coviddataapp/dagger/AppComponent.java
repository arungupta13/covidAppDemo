package com.example.coviddataapp.dagger;


import android.app.Application;

import com.example.coviddataapp.home.HomeActivity;

import dagger.Component;
import dagger.android.ContributesAndroidInjector;

@Component (modules = ActivityBindingmodule.class)
public interface AppComponent {

    void inject(HomeActivity homeActivity);
}



