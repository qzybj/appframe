package com.brady.corelib.fragment.interfaces;

import com.brady.corelib.fragment.bar.TitleBarFragment;

/**
 * Created by ZhangYuanBo on 2016/5/30.
 *  用于绑定Fragment与activity的交互
 */
public interface IBindFragment2Container {
    /**
     * 用于绑定项部分栏于底部栏的点击触发事件
     * @param titleBarListener
     * @param tabBarClickListener
     */
    void bindListener(ITitleBarClickListener titleBarListener,ITabBarClickListener tabBarClickListener);

    /**
     * 用于绑定弹出类Fragment的callback事件
     * @param callBack
     */
    void bindFragmentCallBack(IDialogCallBack callBack);

    /**
     * 接收Fragment发送的数据
     * @param tag Fragment tag
     * @param obj
     */
    void receive(String tag, Object obj);

    /**
     * 获取TitleBar进行操作
     */
    TitleBarFragment getTitleBar();
}
