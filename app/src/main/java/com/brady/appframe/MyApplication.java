package com.brady.appframe;

import com.brady.coreframe.aosp.picasso.PicassoHelper;
import com.brady.coreframe.module.loadimage.LoadImageManager;
import com.brady.corelib.CApplication;


public class MyApplication extends CApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadImageManager.init(new PicassoHelper());
    }
}
