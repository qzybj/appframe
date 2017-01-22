package com.brady.corelib.fragment.interfaces.impl;

import android.support.v4.app.Fragment;
import com.brady.corelib.fragment.interfaces.IBuildParams;
import com.brady.corelib.fragment.interfaces.ITabItem;
import java.util.ArrayList;


/**
 * Created by ZhangYuanBo on 2017/1/22.
 */
public class BuildParams implements IBuildParams {
    private boolean isEnableTitleBar;
    private boolean isEnableBottomTabBar;
    private ArrayList<ITabItem> tabItems;
    private Class<? extends Fragment> mFragment;

    public BuildParams(Class<? extends Fragment> fragment,boolean isEnableTitleBar, boolean isEnableBottomTabBar, ArrayList<ITabItem> tabItems) {
        this.mFragment = fragment;
        this.isEnableTitleBar = isEnableTitleBar;
        this.isEnableBottomTabBar = isEnableBottomTabBar;
        this.tabItems = tabItems;
    }

    @Override
    public Class<? extends Fragment> getFragment() {
        return mFragment;
    }

    @Override
    public boolean isEnableTitleBar() {
        return isEnableTitleBar;
    }

    @Override
    public boolean isEnableBottomTabBar() {
        return isEnableBottomTabBar;
    }

    @Override
    public ArrayList<ITabItem> getTabItems() {
        return tabItems;
    }
}
