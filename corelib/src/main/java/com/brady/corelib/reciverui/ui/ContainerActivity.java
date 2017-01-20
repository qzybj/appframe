package com.brady.corelib.reciverui.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.brady.coreframe.utils.LogUtils;
import com.brady.coreframe.utils.dataprocess.IntentUtils;
import com.brady.corelib.CApplication;
import com.brady.corelib.R;
import com.brady.corelib.base.BaseFragmentActivity;
import com.brady.corelib.fragment.bar.TabBarFragment;
import com.brady.corelib.fragment.interfaces.IDialogCallBack;
import com.brady.corelib.fragment.interfaces.IIntentData;
import com.brady.corelib.fragment.interfaces.ISendData;
import com.brady.corelib.fragment.interfaces.ITabBarClickListener;


/**
 *  用于接收Fragment的容器Activity:<br>
 *  包含标题栏 、底部动态栏
 */
public class ContainerActivity extends BaseFragmentActivity implements ISendData,ITabBarClickListener,IDialogCallBack {
    public static final String KEY_FRAGMENT = "fragment";
    public static final String KEY_TABBAR = "tabbar";
    public static final String KEY_TABBAR_LIST = "tabbar_list";

    private Fragment mFragment;
    private Fragment oldFragment;

    @Override
    public int getLayoutResId() {
        return R.layout.frame_container_activity;
    }

    @Override
    public void initContentView(View view) {
        initBar();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        try {
            mFragment = Fragment.instantiate(CApplication.instance(), IntentUtils.getString(getIntent(), KEY_FRAGMENT));
            showFragment(mFragment);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void initBar() {
        if(isSupportTitleBar()){
            titleBar.setRightDrawable(R.mipmap.ic_launcher,0);
            titleBar.setTitle("");
        }
        if(IntentUtils.getBoolean(getIntent(), KEY_TABBAR, false)){
            if(isSupportTabBar()){
                if(IntentUtils.isNotEmpty(getIntent())){
                    Object obj = IntentUtils.getBundle(getIntent()).getSerializable(KEY_TABBAR_LIST);
                    if(obj instanceof IIntentData){
                        IIntentData intentData = (IIntentData)obj;
                        tabBar.initTabView(intentData.getTabItems());
                    }
                }
            }
        }else{
            setTabBarVisiable(false);
        }
    }

    @Override
    protected void customClickEvent(View v) {

    }

    /**标题栏   -  左边按钮触发事件*/
    public void onClickTitleLeft(View v) {
        finish();
    }

    /**标题栏   -  右边按钮触发事件*/
    public void onClickTitleRight(View v) {
        showToast("ibtn_right");
    }

    @Override
    public void onTitleBarClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }

    public void onTabClick(View view) {
        switch (view.getId()) {
            case TabBarFragment.ID_TAB_BASECODE:
                if(mFragment !=null){
                    showFragment(mFragment);
                }
                break;
        }
    }

    private void showFragment(Fragment targetFragment){
        if (targetFragment!=null&&targetFragment!= oldFragment) {

            FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();//开启Fragment事务
            if(oldFragment !=null){
                transaction.hide(oldFragment);
            }
            if (!targetFragment.isAdded()) {
                transaction.add(R.id.frame_fragment_content, targetFragment);
            } else {
                transaction.show(targetFragment);
            }
            oldFragment = targetFragment;
            transaction.commit();
        }
    }

    private void setTabBarVisiable(boolean isShow){
        if(isSupportTabBar()){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事务
            if(isShow){
                if (!tabBar.isAdded()) { // 隐藏当前的fragment，add下一个到Activity中
                    transaction.add(R.id.frame_fragment_content, tabBar);
                }
                transaction.show(tabBar);
            }else{
                transaction.hide(tabBar);
            }
            transaction.commit();
        }
    }

    @Override
    public void receive(String tag, Object obj) {
        if(obj instanceof String){
            showToast(obj.toString());
        }else{
            showToast(obj.toString());
        }
    }

    @Override
    public void btnCallBack(String tag, int type) {
        switch (type){
            case IDialogCallBack.CODE_BTN_LEFT:
                if("AlertDialogFragment".equals(tag)){
                    showToast("CODE_BTN_LEFT");
                }
                break;
            default:
                break;
        }
    }
}