package redroid.listener;

import android.text.TextWatcher;

/**
 * 文本改动后触发的回调事件
 *
 * @author RobinVanYang created at 2017-11-23 14:49.
 */

public interface AfterTextChangedListener extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {}
}
