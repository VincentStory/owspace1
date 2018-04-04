package com.app.vincent.owspace.di.component;

import com.app.vincent.owspace.di.modules.SplashModule;
import com.app.vincent.owspace.di.scopes.UserScope;
import com.app.vincent.owspace.view.activity.SplashActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/25
 * owspace
 */
@UserScope
@Component(modules = SplashModule.class,dependencies = NetComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
