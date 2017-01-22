package com.brady.corelib.fragment.interfaces;

import android.view.View;

/**
 * Created by ZhangYuanBo on 2016/5/30.
 * 用于底部Tab栏的点击事件监听回调
 */
public interface ITabBarClickListener {
    /**
     * 底部Tab栏的点击事件监听回调
     * @param view
     */
    boolean onTabClick(View view);
}
