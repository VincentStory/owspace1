package redroid.widget;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import redroid.R;

/**
 * 加载对话框
 *
 * @author RobinVanYang created at 2017-07-11 12:35.
 */

public class LoadingDialog extends DialogFragment {
    private static final String TAG = "loadingDialog";
    private ContentLoadingProgressBar mContentLoadingProgressBar;
    private TextView mTextView;

    private String mText;
    private boolean mTextViewVisibility;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_loading, container, false);
        mContentLoadingProgressBar = view.findViewById(R.id.contentLoadingProgressBar);
        mTextView = view.findViewById(R.id.textView1);

        setViewText();

        //set default progressbar color.
        setProgressBarColor(getResources().getColor(R.color.progressBarColor));

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        if (null != dialog && null != dialog.getWindow())
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getText() {
        if (!mTextViewVisibility) mTextViewVisibility = true;

        return mText;
    }

    public void setTextVisibility(boolean visibility) {
        mTextViewVisibility = visibility;
    }

    public void setProgressBarColor(@ColorInt int color) {
        mContentLoadingProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    /**
     * 设置对话框文本
     */
    private void setViewText() {
        if (null != mText && mTextViewVisibility) {
            mTextView.setText(mText);
            mTextView.setVisibility(View.VISIBLE);
        } else mTextView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        if (null != window) {
            ViewGroup.LayoutParams params = window.getAttributes();
            params.width = getResources().getDimensionPixelSize(R.dimen.loadingdialog_size);
            params.height = getResources().getDimensionPixelSize(R.dimen.loadingdialog_size);
            getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        }
        super.onResume();
    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }

    private void hide() {
        dismiss();
    }
}
