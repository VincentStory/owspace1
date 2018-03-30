package redroid.listener;

import android.view.animation.Animation;

/**
 * focus on animation end callback.
 * @author RobinVanYang created at 2017-06-28 19:23.
 */
public interface AnimationEndListener extends Animation.AnimationListener{
    default void onAnimationStart(Animation animation){}

    default void onAnimationRepeat(Animation animation) {}
}