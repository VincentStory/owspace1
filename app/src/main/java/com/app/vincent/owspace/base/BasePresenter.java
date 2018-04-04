package com.app.vincent.owspace.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by subuhui on 2016/8/2.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class BasePresenter<T> {

    public T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }


    public void attachView(T view) {
        this.mView = view;
    }

    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}