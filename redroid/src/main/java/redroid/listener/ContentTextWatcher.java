package redroid.listener;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * 内容变化,按钮能否点击
 * Created by Administrator on 2017/12/19.
 */

public class ContentTextWatcher implements TextWatcher, CompoundButton.OnCheckedChangeListener {
    private Button but;
    private CheckBox cb = null;
    private int mEditTextHaveInputCount = 0;
    private int EDITTEXT_AMOUNT = 0;

    /**
     *
     * @param button  能否点击的view
     * @param i EditText或者TextView监听的个数
     */
    public ContentTextWatcher(Button button, int i) {
        this.EDITTEXT_AMOUNT = i;
        this.but = button;
    }

    public ContentTextWatcher(Button button, int i, CheckBox cb) {
        this.but = button;
        this.EDITTEXT_AMOUNT = i;
        this.cb = cb;
        cb.setOnCheckedChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        /* EditText最初内容为空 改变EditText内容时 个数加一*/
        if (TextUtils.isEmpty(charSequence)) {
            mEditTextHaveInputCount++;
            /* 判断个数是否到达要求*/
            if (mEditTextHaveInputCount == EDITTEXT_AMOUNT && (cb == null ? true : cb.isChecked()))
                but.setEnabled(true);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        /* EditText内容改变之后 内容为空时 个数减一 按钮改为不可以的背景*/
        if (TextUtils.isEmpty(charSequence)) {
            mEditTextHaveInputCount--;
            but.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked && (EDITTEXT_AMOUNT == mEditTextHaveInputCount)) {
            but.setEnabled(true);
        } else {
            but.setEnabled(false);
        }
    }
}
