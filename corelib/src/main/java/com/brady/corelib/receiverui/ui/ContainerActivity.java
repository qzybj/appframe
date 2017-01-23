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
import com.brady.corelib.utils.FragmentHelper;

import static android.R.attr.data;
import static android.R.attr.tag;


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
    private IDialogCallBack mDialogCallBack;

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
            FragmentHelper.showFragment(getSupportFragmentManager(),R.id.frame_fragment_content,oldFragment,targetFragment);
            oldFragment = targetFragment;
        }
    }

    private void setTitleBarVisible(boolean isShow){
        if(isSupportTitleBar()){
            FragmentHelper.setFragmentVisible(getSupportFragmentManager(),R.id.frame_fragment_titlebar,titleBar,isShow);
        }
    }
    private void setTabBarVisible(boolean isShow){
        if(isSupportTabBar()){
            FragmentHelper.setFragmentVisible(getSupportFragmentManager(),R.id.frame_fragment_tabbar,tabBar,isShow);
        }
    }


    @Override
    public boolean callBack(String fragmentTag, int type,Object data) {
        if(mDialogCallBack!=null){
            boolean isConsume = mDialogCallBack.callBack(fragmentTag,type,data);
            if(isConsume){
                return isConsume;
            }
        }
        switch (type){
            default:
                break;
        }
        return false;
    }

    @Override
    public void bindListener(ITitleBarClickListener titleBarListener, ITabBarClickListener tabBarClickListener) {
        this.mTitleBarClickListener =titleBarListener;
        this.mTabBarClickListener =tabBarClickListener;
    }

    public void bindFragmentCallBack(IDialogCallBack callBack) {
        this.mDialogCallBack =callBack;
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