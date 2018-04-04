package com.app.vincent.owspace.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.app.vincent.owspace.di.component.NetComponent;
import com.app.vincent.owspace.di.modules.NetModule;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public class OwspaceApplication extends Application{

    private static OwspaceApplication sInstance;
    private String cacheDiaPath;
    private NetComponent netComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        initNet();
//        mApplicationComponent = initDagger(this);


        cacheDiaPath = getCacheDir().toString();
//        if (BuildConfig.DEBUG) {
//            Timber.plant(new FileLoggingTree());
//        }
    }
    public static OwspaceApplication get(Context context){
        return (OwspaceApplication)context.getApplicationContext();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private void initNet(){
//        netComponent = DaggerNetComponent.builder()
//                .netModule(new NetModule())
//                .build();
    }


    public NetComponent getNetComponent() {
        return netComponent;
    }


    public static OwspaceApplication getInstance() {
        return sInstance;
    }
}
