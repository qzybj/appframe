package com.brady.corelib.fragment.interfaces;

import android.support.v4.app.Fragment;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ZhangYuanBo on 2017/1/20.
 */
public interface IBuildParams extends Serializable {

    /**用容器来展示的 Fragment*/
    Class<? extends Fragment> getFragment();

    /**是否显示项部的TitleBar*/
    boolean isEnableTitleBar();
    /**是否显示底部的TabBar*/
    boolean isEnableBottomTabBar();
    /**底部的Item*/
    ArrayList<ITabItem> getTabItems();


}