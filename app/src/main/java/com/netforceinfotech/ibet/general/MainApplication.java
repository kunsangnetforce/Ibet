package com.netforceinfotech.ibet.general;

import android.app.Application;

/**
 * When your application is launched this class is loaded before all of your activies.
 * And the instance of this class will live through whole application lifecycle.
 * <p/>
 * Created by gunhansancar on 16/02/15.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UserSessionManager userSessionManager = new UserSessionManager(getApplicationContext());
        LocaleHelper.onCreate(this, userSessionManager.getLanguage());/*
        switch (userSessionManager.getLanguage()) {
            case "en":

                break;
            case "iw":
                break;
            case "es":
                break;
        }*/
    }
}