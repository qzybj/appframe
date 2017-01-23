package com.brady.corelib.receiverui.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.brady.coreframe.utils.LogUtils;
import com.brady.coreframe.utils.dataprocess.IntentUtils;
import com.brady.coreframe.utils.dataprocess.ListUtils;
import com.brady.corelib.CApplication;
import com.brady.corelib.R;
import com.brady.corelib.base.BaseFragmentActivity;
import com.brady.corelib.fragment.bar.TitleBarFragment;
import com.brady.corelib.fragment.interfaces.IBindFragment2Container;
import com.brady.corelib.fragment.interfaces.IDialogCallBack;
import com.brady.corelib.fragment.interfaces.IBuildParams;
import com.brady.corelib.fragment.interfaces.ITabBarClickListener;
import com.brady.corelib.fragment.interfaces.ITitleBarClickListener;


/**
 *  用于接收Fragment的容器Activity:<br>
 *  包含标题栏 、底部动态栏
 */
public class ContainerActivity extends BaseFragmentActivity implements IBindFragment2Container,ITabBarClickListener,IDialogCallBack {
    public static final String KEY_FRAGMENT = "fragment";

    private IBuildParams mBuildParams;
    private Fragment mFragment;
    private Fragment oldFragment;

    private ITitleBarClickListener mTitleBarClickListener;
    private ITabBarClickListener mTabBarClickListener;

    @Override
    public int getLayoutResId() {
        return R.layout.frame_container_activity;
    }

    @Override
    public void initContentView(View view) {
        initUI();
    }

    private void initUI() {
        if(IntentUtils.isNotEmpty(getIntent())){
            Object obj = IntentUtils.getBundle(getIntent()).getSerializable(KEY_FRAGMENT);
            if(obj instanceof IBuildParams){
                mBuildParams = (IBuildParams)obj;
                if(isSupportTitleBar()&&mBuildParams.isEnableTitleBar()){
                    //titleBar.setRightDrawable(R.mipmap.ic_launcher,0);
                    //titleBar.setTitle("");
                }else{
                    setTitleBarVisible(false);
                }
                if(isSupportTabBar()
                        &&mBuildParams.isEnableBottomTabBar()&& ListUtils.isNotEmptyList(mBuildParams.getTabItems())){
                    tabBar.initTabView(mBuildParams.getTabItems());
                }else{
                    setTabBarVisible(false);
                }
                if(mBuildParams.getFragment()!=null){
                    try {
                        mFragment = Fragment.instantiate(CApplication.instance(), mBuildParams.getFragment().getName());
                        showFragment(mFragment);
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
            }
        }
    }
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void customClickEvent(View v) {

    }

    /**标题栏   -  左边按钮触发事件*/
    public boolean onClickTitleLeft(View v) {
        if(mTitleBarClickListener!=null){
            boolean isConsume = mTitleBarClickListener.onClickTitleLeft(v);
            if(isConsume){
                return isConsume;
            }
        }
        finish();
        return true;
    }

    /**标题栏   -  右边按钮触发事件*/
    public boolean onClickTitleRight(View v) {
        if(mTitleBarClickListener!=null){
            boolean isConsume = mTitleBarClickListener.onClickTitleRight(v);
            if(isConsume){
                return isConsume;
            }
        }
        return true;
    }

    @Override
    public boolean onTitleBarClick(View v) {
        if(mTitleBarClickListener!=null){
            boolean isConsume = mTitleBarClickListener.onTitleBarClick(v);
            if(isConsume){
                return isConsume;
            }
        }
        switch (v.getId()) {
            default:
                break;
        }
        return true;
    }

    public boolean onTabClick(View v) {
        if(mTabBarClickListener!=null){
            boolean isConsume = mTabBarClickListener.onTabClick(v);
            if(isConsume){
                return isConsume;
            }
        }
        switch (v.getId()) {
            default:
                break;
        }
        return true;
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

    private void setTitleBarVisible(boolean isShow){
        if(isSupportTitleBar()){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事务
            if(isShow){
                if (!titleBar.isAdded()) { // 隐藏当前的fragment，add下一个到Activity中
                    transaction.add(R.id.frame_fragment_titlebar, titleBar);
                }
                transaction.show(titleBar);
            }else{
                transaction.hide(titleBar);
            }
            transaction.commit();
        }
    }
    private void setTabBarVisible(boolean isShow){
        if(isSupportTabBar()){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事务
            if(isShow){
                if (!tabBar.isAdded()) { // 隐藏当前的fragment，add下一个到Activity中
                    transaction.add(R.id.frame_fragment_tabbar, tabBar);
                }
                transaction.show(tabBar);
            }else{
                transaction.hide(tabBar);
            }
            transaction.commit();
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


    @Override
    public void bindListener(ITitleBarClickListener titleBarListener, ITabBarClickListener tabBarClickListener) {
        this.mTitleBarClickListener =titleBarListener;
        this.mTabBarClickListener =tabBarClickListener;
    }
    @Override
    public void receive(String tag, Object obj) {
        if(obj instanceof String){
            showToast(obj.toString());
        }else{
            showToast("Send null");
        }
    }

    @Override
    public TitleBarFragment getTitleBar() {
        return titleBar;
    }
}