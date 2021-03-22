package com.example.coviddataapp.home;

public interface HomeContract {

    interface View {

        void locationdata();

        void launchMapActivity();

        void setlat(Double latitude);

        void setlon(Double longitude);

        void setconfirmed(String confirmed);

        void setrecovered(String recovered);

        void setcritical(String critical);

        void setdeath(String death);

        String getCountry();

    }

    interface Presenter {
        void locationdata();
    }

}
