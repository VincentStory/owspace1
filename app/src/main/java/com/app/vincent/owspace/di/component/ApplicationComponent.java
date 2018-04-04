package com.app.vincent.owspace.di.component;

import android.content.Context;


import com.app.vincent.owspace.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * TODO
 *
 * @author RobinVanYang
 * @createTime 2016-06-12 02:13
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context context();
}
