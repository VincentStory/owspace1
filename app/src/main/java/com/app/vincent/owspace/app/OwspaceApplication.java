package com.app.vincent.owspace.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public class OwspaceApplication extends Application{

    private static OwspaceApplication sInstance;
    private String cacheDiaPath;



    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
//        mApplicationComponent = initDagger(this);


        cacheDiaPath = getCacheDir().toString();
//        if (BuildConfig.DEBUG) {
//            Timber.plant(new FileLoggingTree());
//        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



    public static OwspaceApplication getInstance() {
        return sInstance;
    }
}
