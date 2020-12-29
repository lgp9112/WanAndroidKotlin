package com.lgp.wanandroid.kotlin.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/25 14:58
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
public class ByeBurgerBehavior extends CoordinatorLayout.Behavior<View> {
    protected final int mTouchSlop;
    protected boolean isFirstMove = true;
    protected boolean canInit = true;
    protected AnimateHelper mAnimateHelper;

    public ByeBurgerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & 2) != 0;
    }

    public void show() {
        this.mAnimateHelper.show();
    }

    public void hide() {
        this.mAnimateHelper.hide();
    }

    public static ByeBurgerBehavior from(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        } else {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)params).getBehavior();
            if (!(behavior instanceof ByeBurgerBehavior)) {
                throw new IllegalArgumentException("The view is not associated with ByeBurgerBehavior");
            } else {
                return (ByeBurgerBehavior)behavior;
            }
        }
    }
}

