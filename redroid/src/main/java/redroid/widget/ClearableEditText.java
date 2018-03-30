package redroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.InputType;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import redroid.R;
import redroid.listener.AfterTextChangedListener;

/**
 * 带可清空文本按钮的EditText
 *
 * @author RobinVanYang created at 2017-11-23 11:51.
 */

public class ClearableEditText extends LinearLayout implements View.OnClickListener {
    private EditText mEditText;
    private LinearLayout mLlClearContainer;

    private CharSequence mText;
    private float mTextSize;
    private String mHintText;
    private int mHintTextColor;
    private KeyListener mEditTextKeyListener;

    public ClearableEditText(Context context) {
        super(context);
        init(context);
    }

    public ClearableEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(context, attrs, 0, 0);
    }

    public ClearableEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttrs(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClearableEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initAttrs(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_clearable_edittext, this);
        mLlClearContainer = view.findViewById(R.id.ll_clear_container);
        mLlClearContainer.setOnClickListener(this);
        mLlClearContainer.setVisibility(GONE);

        mEditText = view.findViewById(R.id.editText1);

        mEditTextKeyListener = mEditText.getKeyListener();

        mEditText.setOnFocusChangeListener((v, hasFocus) -> {
            mLlClearContainer.setVisibility(judgeClearContainerVisibility(mEditText.getText().toString(), hasFocus));
        });

        mEditText.addTextChangedListener((AfterTextChangedListener) s ->
                mLlClearContainer.setVisibility(judgeClearContainerVisibility(s.toString(), isEnabled()))
        );
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClearableEditText, defStyleAttr, defStyleRes);

        try {
            mText = typedArray.getString(R.styleable.ClearableEditText_text);
            mHintText = typedArray.getString(R.styleable.ClearableEditText_hint);
            mHintTextColor = typedArray.getColor(R.styleable.ClearableEditText_hintColor, Color.GRAY);
            mTextSize = typedArray.getDimensionPixelSize(R.styleable.ClearableEditText_textSize, 0);
            int inputType = typedArray.getInt(R.styleable.ClearableEditText_android_inputType, InputType.TYPE_CLASS_TEXT);

            mEditText.setText(mText);

            if (0 != mTextSize) mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);

            mEditText.setInputType(inputType);
            mEditText.setHint(mHintText);
            mEditText.setHintTextColor(mHintTextColor);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_clear_container)
            mEditText.getText().clear();
    }

    public Editable getText() {
        return mEditText.getText();
    }

    public void setText(CharSequence text) {
        mEditText.setText(text);
    }

    public void setText(@StringRes int resId) {
        mEditText.setHint(resId);
    }

    public void setTextColor(int color) {
        mEditText.setTextColor(color);
    }

    public void setHint(CharSequence hint) {
        mEditText.setHint(hint);
    }

    public void setHint(@StringRes int resId) {
        mEditText.setHint(resId);
    }

    public void setInputType(int inputType) {
        mEditText.setInputType(inputType);
        //fix inputType bug.
        mEditTextKeyListener = mEditText.getKeyListener();
    }

    public EditText getEditText() {
        return mEditText;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mEditText.setEnabled(enabled);
        mEditText.setFocusable(enabled);
        mEditText.setKeyListener(enabled ? mEditTextKeyListener : null);
    }

    /**
     * 获取清空控件下阶段显示状态
     *
     * @param editText 输入控件文本
     * @param hasFocus 输入控件当前是否获取到焦点
     * @return 清空按钮下阶段显示状态.
     */
    private int judgeClearContainerVisibility(String editText, boolean hasFocus) {
        if (hasFocus) {
            if (editText.length() > 0 && mLlClearContainer.getVisibility() != VISIBLE) {
                return VISIBLE;
            }

            if (editText.length() == 0 && mLlClearContainer.getVisibility() == VISIBLE) {
                return GONE;
            }
        } else {
            return GONE;
        }

        return mLlClearContainer.getVisibility();
    }
}
