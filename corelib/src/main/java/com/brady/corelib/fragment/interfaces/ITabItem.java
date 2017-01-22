package com.brady.corelib.fragment.interfaces;


import java.io.Serializable;

/**
 * Created by ZhangYuanBo on 2016/5/30.
 *  用于Fragment与activity的数据
 */
public interface ITabItem  extends Serializable {
    /**获取图片resourceid*/
    int getImageResId();
    /**获取展示用的text文本*/
    String getText();
}