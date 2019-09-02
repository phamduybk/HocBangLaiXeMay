package vn.com.lvvu.hocbanglaixemay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.TextView;

/**
 * class xử lí animation
 * Created by levan on 7/14/2019.
 */

public class ViewAnimationUtils {

    public static Animation startRotate180Animation;
    public static Animation startRotate180AnimationRollback;
    public static Animation scaleTextViewAnimation;

    public static void startRotatingImage(View view, Context context) {
        try {
            if (startRotate180Animation == null) {
                startRotate180Animation = AnimationUtils.loadAnimation(context, R.anim.rotate_180_animation);
            }
            view.startAnimation(startRotate180Animation);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    public static void rollbackRotatingImage(View view, Context context) {
        try {
            if (startRotate180AnimationRollback == null) {
                startRotate180AnimationRollback = AnimationUtils.loadAnimation(context, R.anim.rotate_180_animation_rollback);
            }
            view.startAnimation(startRotate180AnimationRollback);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    public static void scaleTextViewAnimation(TextView textView, Context context) {
        try {
            if (scaleTextViewAnimation == null) {
                scaleTextViewAnimation = AnimationUtils.loadAnimation(context, R.anim.textview_scale_animation);
            }
            textView.startAnimation(scaleTextViewAnimation);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(500);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(500);
        v.startAnimation(a);
    }

}
