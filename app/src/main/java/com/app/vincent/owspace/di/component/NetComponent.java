package com.app.vincent.owspace.di.component;

import com.app.vincent.owspace.di.modules.NetModule;
import com.app.vincent.owspace.model.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by czxyl171151 on 2018/4/4.
 */

@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {
    ApiService getApiService();
    OkHttpClient getOkHttp();
    Retrofit getRetrofit();
}
