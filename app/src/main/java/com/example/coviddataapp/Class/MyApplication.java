package com.example.coviddataapp.Class;

import android.app.Application;

import com.example.coviddataapp.dagger.AppComponent;
import com.example.coviddataapp.dagger.DaggerAppComponent;

public class MyApplication extends Application {
    private AppComponent appComponent;



    //dagger component creation
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }
public AppComponent getAppComponent(){
        return appComponent;
}

}
