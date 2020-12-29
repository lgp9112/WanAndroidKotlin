package com.lgp.wanandroid.kotlin.ui.behavior;

import android.animation.ValueAnimator;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/25 15:02
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
public class TranslateAnimateHelper implements AnimateHelper {
    public View mTarget;
    public float mStartY;
    public int mCurrentState = 1;
    public int mMode;
    public static int MODE_TITLE = 233;
    public static int MODE_BOTTOM = 2333;
    private float mFirstY;
    private float mMargin;

    private TranslateAnimateHelper(View view) {
        this.mMode = MODE_TITLE;
        this.mFirstY = 0.0F;
        this.mTarget = view;
        this.mFirstY = this.mTarget.getY();
        this.mMargin = (float)(((CoordinatorLayout.LayoutParams)this.mTarget.getLayoutParams()).topMargin + ((CoordinatorLayout.LayoutParams)this.mTarget.getLayoutParams()).bottomMargin);
    }

    public static TranslateAnimateHelper get(View target) {
        return new TranslateAnimateHelper(target);
    }

    public void show() {
        if (this.mMode == MODE_TITLE) {
            this.showTitle();
        } else if (this.mMode == MODE_BOTTOM) {
            this.showBottom();
        }

    }

    private void hideTitle() {
        ValueAnimator va = ValueAnimator.ofFloat(new float[]{this.mTarget.getY(), (float)(-this.mTarget.getHeight())});
        va.setDuration(300L);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                TranslateAnimateHelper.this.mTarget.setY((Float)valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        this.mCurrentState = 0;
    }

    private void showTitle() {
        ValueAnimator va = ValueAnimator.ofFloat(new float[]{this.mTarget.getY(), 0.0F});
        va.setDuration(300L);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                TranslateAnimateHelper.this.mTarget.setY((Float)valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        this.mCurrentState = 1;
    }

    public void hide() {
        if (this.mMode == MODE_TITLE) {
            this.hideTitle();
        } else if (this.mMode == MODE_BOTTOM) {
            this.hideBottom();
        }

    }

    private void showBottom() {
        ValueAnimator va = ValueAnimator.ofFloat(new float[]{this.mTarget.getY(), this.mFirstY});
        va.setDuration(300L);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                TranslateAnimateHelper.this.mTarget.setY((Float)valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        this.mCurrentState = 1;
    }

    private void hideBottom() {
        ValueAnimator va = ValueAnimator.ofFloat(new float[]{this.mTarget.getY(), this.mFirstY + (float)this.mTarget.getHeight() + this.mMargin});
        va.setDuration(300L);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                TranslateAnimateHelper.this.mTarget.setY((Float)valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        this.mCurrentState = 0;
    }

    public void setStartY(float y) {
        this.mStartY = y;
    }

    public int getState() {
        return this.mCurrentState;
    }

    public void setMode(int mode) {
        this.mMode = mode;
    }

    private void setState(int state) {
        this.mCurrentState = state;
    }
}
