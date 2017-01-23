package com.brady.appframe.module.loadimage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.brady.appframe.R;
import com.brady.appframe.module.common.utils.TestDataBuilder;
import com.brady.coreframe.module.loadimage.LoadImageManager;
import com.brady.corelib.base.BaseFragment;
import com.brady.corelib.fragment.bar.TabBarFragment;
import com.brady.corelib.fragment.bar.TitleBarFragment;
import com.brady.corelib.fragment.interfaces.IBindFragment2Container;
import com.brady.corelib.fragment.interfaces.ITabBarClickListener;
import com.brady.corelib.fragment.interfaces.ITitleBarClickListener;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ZhangYuanBo on 2017/1/23.
 */

public class LoadImageFragment extends BaseFragment implements ITabBarClickListener, ITitleBarClickListener {

    @BindView(R.id.iv_image)
    ImageView iv_image;
    @BindView(R.id.btn_randomImage)
    Button btn_randomImage;


    private IBindFragment2Container mBindFragment2Container;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_loadimage;
    }

    @Override
    public void initContentView(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initBind();

    }

    @OnClick({R.id.btn_randomImage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_randomImage:
                randomImage();
                break;
        }
    }

    private void randomImage() {
        LoadImageManager.instance().loadImage(iv_image,TestDataBuilder.getImageUrl());
    }


    private void initBind() {
        if(getBaseActivity() instanceof IBindFragment2Container){
            mBindFragment2Container =   (IBindFragment2Container)getBaseActivity();
            mBindFragment2Container.bindListener(this,this);
            //Init title bar ui
            TitleBarFragment titleBar = mBindFragment2Container.getTitleBar();
            if(titleBar!=null){
                titleBar.setTitle(getClass().getSimpleName());
                titleBar.setLeftText("返回");
                titleBar.setRightVisibility(true);
                titleBar.setRightText("右侧Btn");
            }
        }
    }

    @Override
    public boolean onTabClick(View view) {
        switch (view.getId()) {
            case TabBarFragment.ID_TAB_BASECODE:
                sendMsg2Activity("Click onTabClick btn"+TabBarFragment.ID_TAB_BASECODE);
                return true;
            case TabBarFragment.ID_TAB_BASECODE+1:
                sendMsg2Activity("Click onTabClick btn"+TabBarFragment.ID_TAB_BASECODE+1);
                return true;
            case TabBarFragment.ID_TAB_BASECODE+2:
                sendMsg2Activity("Click onTabClick btn"+TabBarFragment.ID_TAB_BASECODE+2);
                return true;

        }
        return false;
    }

    @Override
    public boolean onClickTitleLeft(View v) {
        sendMsg2Activity("Click left btn");
        return true;
    }

    @Override
    public boolean onClickTitleRight(View v) {
        sendMsg2Activity("Click right btn");
        return true;
    }

    @Override
    public boolean onTitleBarClick(View v) {
        return false;
    }

    private void sendMsg2Activity(String flag){
        if(mBindFragment2Container!=null){
            mBindFragment2Container.receive(this.getTag(),this.getTag()+" send message by "+flag);
        }
    }
}