package com.example.t_008.app;

import android.app.Application;

public class App extends Application {

    private boolean isDataLoaded;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public boolean isDataLoaded() {
        return isDataLoaded;
    }

    public void setDataLoaded(boolean dataLoaded) {
        isDataLoaded = dataLoaded;
    }
}
