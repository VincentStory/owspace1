package com.app.vincent.owspace.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 可取消
 *
 * @author RobinVanYang created at 2018-02-06 12:59.
 */

public abstract class DisposableActivity<P extends BasePresenter> extends AppCompatActivity {
    private CompositeDisposable mCompositeDisposable;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeDisposable = new CompositeDisposable();
        //创建Presenter
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }

    public void addToDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public abstract P createPresenter();
}
