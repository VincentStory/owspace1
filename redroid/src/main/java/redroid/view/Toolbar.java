package redroid.view;


import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import redroid.R;

/**
 * 自定义Toolbar
 *
 * @author RobinVanYang
 * @createTime 2016-06-16 00:38
 */
public class Toolbar extends android.support.v7.widget.Toolbar {
    private static final String TAG = "Toolbar";
    private Context mContext;

    public Toolbar(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }

    public Toolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        //mContext.
        //infla
    }

    @Override
    public void setTitle(int resId) {
        if (null != findViewById(R.id.tv_toolbar_title))
            ((TextView) findViewById(R.id.tv_toolbar_title)).setText(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (null != findViewById(R.id.tv_toolbar_title))
            ((TextView) findViewById(R.id.tv_toolbar_title)).setText(title);
    }

    public void setTitleDrawableRight(@DrawableRes int drawableRight) {
        if (null != findViewById(R.id.tv_toolbar_title)) {
            ((TextView) findViewById(R.id.tv_toolbar_title))
                    .setCompoundDrawablesWithIntrinsicBounds(null, null,
                            getResources().getDrawable(drawableRight), null);
        }
    }

    public void setOnTitleSelectListener(OnTitleSelectListener listener) {
        if (null != findViewById(R.id.tv_toolbar_title)) {
            View view = findViewById(R.id.tv_toolbar_title);
            view.setOnClickListener(v -> {
                listener.onSelected(view.isSelected());
                view.setSelected(!view.isSelected());
            });
        }
    }

    public void performTitleSelected(boolean isSelected) {
        View view = findViewById(R.id.tv_toolbar_title);
        if (null != view && isSelected != view.isSelected()) {
            findViewById(R.id.tv_toolbar_title).performClick();
        }
    }

    public interface OnTitleSelectListener {
        void onSelected(boolean isSelected);
    }
}
