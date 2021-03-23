package com.example.coviddataapp.home;

import dagger.Binds;
import dagger.Module;

@Module
public interface HomeModule {

    @Binds
    HomeContract.View getHomeActivity(HomeActivity activity);

    @Binds
    HomeContract.Presenter getHomePresenter(HomePresenter presenter);

}
