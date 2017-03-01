package uca.desapp;

import android.app.Application;

import com.tumblr.remember.Remember;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Remember.init(getApplicationContext(), "uca.desapp");
    }
}
