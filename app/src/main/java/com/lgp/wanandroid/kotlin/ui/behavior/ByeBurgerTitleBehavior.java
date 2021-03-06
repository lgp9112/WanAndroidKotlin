package com.lgp.wanandroid.kotlin.ui.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/25 15:07
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
public class ByeBurgerTitleBehavior extends ByeBurgerBehavior {
    public ByeBurgerTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (this.canInit) {
            this.mAnimateHelper = TranslateAnimateHelper.get(child);
            this.canInit = false;
        }

        return super.layoutDependsOn(parent, child, dependency);
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (this.isFirstMove) {
            this.isFirstMove = false;
            this.mAnimateHelper.setStartY(child.getY());
            this.mAnimateHelper.setMode(TranslateAnimateHelper.MODE_TITLE);
        }

        if (Math.abs(dy) > this.mTouchSlop) {
            if (dy < 0) {
                if (this.mAnimateHelper.getState() == 0) {
                    this.mAnimateHelper.show();
                }
            } else if (dy > 0 && this.mAnimateHelper.getState() == 1) {
                this.mAnimateHelper.hide();
            }
        }

    }
}
