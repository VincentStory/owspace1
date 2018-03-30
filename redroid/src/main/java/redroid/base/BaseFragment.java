package redroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import redroid.R;
import redroid.widget.LoadingDialog;


/**
 * Fragment基类
 *
 * @author RobinVanYang created at 2017-06-26 14:24.
 */

public class BaseFragment extends Fragment {
    public Context mContext;
    private LoadingDialog mLoadingDialog;

    private CharSequence mTitle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //init toolbar in fragment level layout.
        if (null != view.findViewById(R.id.toolbar)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(view.findViewById(R.id.toolbar));
        }
    }

    public void setTitle(@StringRes int strRes) {
        mTitle = getString(strRes);
        getActivity().setTitle(mTitle);
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        getActivity().setTitle(title);
    }

    public void resetTitle() {
        getActivity().setTitle(mTitle);
    }

    public void showActionBarHome() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showActionBarHome();
        }
    }

    public void showLoadingDialog() {
        if (mLoadingDialog == null) mLoadingDialog = new LoadingDialog();

        mLoadingDialog.setText(getString(redroid.R.string.data_loading));

        mLoadingDialog.show(getChildFragmentManager());
    }

    public void hideLoadingDialog() {
        if (null != mLoadingDialog) mLoadingDialog.dismiss();
    }

}
