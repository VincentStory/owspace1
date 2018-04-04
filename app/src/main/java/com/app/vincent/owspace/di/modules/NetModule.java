package com.app.vincent.owspace.di.modules;

import android.content.Context;

import com.app.vincent.owspace.di.annotation.RepositoryScope;
import com.app.vincent.owspace.net.Client;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 网络请求模块所需注入类
 *
 * @author RobinVanYang created at 2017-06-21 13:08.
 */

@Module
public class NetModule {
    @Provides
    @RepositoryScope
    Client provideClient(@Singleton Context context) {
        return new Client(context);
    }

//    @Provides
//    @RepositoryScope
//    TechnicianAccountApi provideTechnicianAccountApi(Client client) {
//        return client.createApi(TechnicianAccountApi.class);
//    }

//    @Provides
//    @RepositoryScope
//    OrderApi provideOrderApi(Client client) {
//        return client.createApi(OrderApi.class);
//    }
//
//    @Provides
//    @RepositoryScope
//    WalletApi provideWalletApi(Client client) {
//        return client.createApi(WalletApi.class);
//    }
}
