package com.lgp.wanandroid.kotlin.ui.behavior;

/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/25 15:00
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
public interface AnimateHelper {
    int STATE_SHOW = 1;
    int STATE_HIDE = 0;

    void show();

    void hide();

    void setStartY(float var1);

    void setMode(int var1);

    int getState();
}
