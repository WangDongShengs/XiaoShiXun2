package com.wds.common;

import android.app.Application;

public class MyApp extends Application {
    private static MyApp app;

    public static MyApp getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
