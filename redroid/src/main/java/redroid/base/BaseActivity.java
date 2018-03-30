package redroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import redroid.R;


/**
 *
 * @author RobinVanYang
 * @createTime 2016-06-16 00:43
 */
public abstract class BaseActivity extends AppCompatActivity {
    public final String TAG = getClass().getName();
    protected Context mContext;
    protected Toolbar mToolbar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setSupportActionBar(findViewById(R.id.toolbar));
    }

    public void showActionBarHome() {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void hideActionBarHome() {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (null != mToolbar) {
            mToolbar.setTitle(titleId);
        } else {
            super.setTitle(titleId);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (null != mToolbar) {
            mToolbar.setTitle(title);
        } else {
            super.setTitle(title);
        }
    }

    public void setTitleDrawableRight(@DrawableRes int drawableRight) {
        ((redroid.view.Toolbar)mToolbar).setTitleDrawableRight(drawableRight);
    }

    public void setTitleOnSelectListener(redroid.view.Toolbar.OnTitleSelectListener listener) {
        ((redroid.view.Toolbar)mToolbar).setOnTitleSelectListener(listener);
    }

    public void performTitleSelected(boolean isSelected) {
        ((redroid.view.Toolbar)mToolbar).performTitleSelected(isSelected);
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        if (null == mToolbar && null != toolbar) {
            super.setSupportActionBar(toolbar);
            mToolbar = toolbar;
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public void setHomeAsUpIndicator(@DrawableRes int drawableRes) {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setHomeAsUpIndicator(drawableRes);
        }
    }

    /**
     * 不能后退到上一个Fragment
     *
     * @param fragment
     */
    public void replaceFragment(final Fragment fragment) {
        findViewById(android.R.id.content).post(() -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(android.R.id.content, fragment);
            transaction.commit();
        });
    }

    public void replaceFragment(final Fragment fragment, final int contentId) {
        findViewById(contentId).post(() -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(contentId, fragment);
            transaction.commit();
        });
    }

    /**
     * 可以后退到上一个Fragment
     *
     * @param fragment
     */
    public void addFragment(final Fragment fragment, final @IdRes int containerId) {
        findViewById(android.R.id.content).post(() -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment);
            transaction.addToBackStack(((Object) fragment).getClass().getName());
            transaction.commit();
        });
    }


    /**
     * 后退到后退栈中的某个Fragment
     *
     * @param fragmentName
     */
    public void backToFragment(String fragmentName) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStackImmediate(fragmentName, 0);
        transaction.commit();
    }

    /**
     * 后退到第一个Fragment
     */
    public void backToTop() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
        transaction.commit();
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
