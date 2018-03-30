package redroid.util;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * TODO
 *
 * @author zhangzheming created at 2018-03-01 16:53.
 */
public class CountDown extends CountDownTimer {
    private final Button mBtn;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     * @param mBtnGetIdentifyCode
     */
    public CountDown(long millisInFuture, long countDownInterval, Button mBtnGetIdentifyCode) {
        super(millisInFuture, countDownInterval);
        this.mBtn = mBtnGetIdentifyCode;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mBtn.setEnabled(false);
        mBtn.setText(millisUntilFinished / 1000+"秒");
    }

    @Override
    public void onFinish() {
        mBtn.setEnabled(true);
        mBtn.setText("重新获取");
    }
}
