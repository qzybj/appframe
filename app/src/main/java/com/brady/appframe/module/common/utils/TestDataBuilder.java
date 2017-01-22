package com.brady.appframe.module.common.utils;


import android.os.Bundle;
import com.brady.appframe.core.ui.reciverui.bean.MainItemBean;
import com.brady.coreframe.utils.dataprocess.IntentUtils;
import com.brady.coreframe.utils.dataprocess.RandomUtils;
import com.brady.coreframe.utils.jump.JumpBaseUtils;
import com.brady.corelib.fragment.interfaces.IBuildParams;
import com.brady.corelib.reciverui.ui.ContainerActivity;
import java.util.ArrayList;
import java.util.Date;


public class TestDataBuilder {
    public static final String KEY_FRAGMENT = "fragment";

    public static final String[] testDataStr = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale"};

    public static final String[] imageUrlsAll = {
            "http://yrs.yintai.com/rs/img/AppCMS/images/0c9b045b-cfa0-46ec-8a9a-751afe0ad62c.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/25739eb4-1f05-4bf3-b04f-b557690ae829.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/dced0440-344e-4928-b58c-07552786944d.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/f5d5e708-ff52-47c3-92bd-44bb99043f1a.jpg",
            "http://yrs.yintai.com/rs/img/AppCMS/images/1186f052-21cb-4f0c-bd7d-4e379efedf37.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/4b11918e-0927-4cec-a002-5b59d619109b.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/93b5703a-2a60-429d-b130-c8865523f053.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/12333f18-acdf-489d-a831-3421f6721e96.png"};

    public static final String[] imageUrls = {
            "http://yrs.yintai.com/rs/img/AppCMS/images/1186f052-21cb-4f0c-bd7d-4e379efedf37.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/4b11918e-0927-4cec-a002-5b59d619109b.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/93b5703a-2a60-429d-b130-c8865523f053.png",
            "http://yrs.yintai.com/rs/img/AppCMS/images/12333f18-acdf-489d-a831-3421f6721e96.png"};


    public  static  String getImageUrl() {
        return imageUrls[RandomUtils.getRandom(imageUrls.length-1)];
    }
    public  static  ArrayList<String> getImageUrlList() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i <6 ; i++) {
            list.add(getImageUrl());
        }
        return list;
    }


    /**以Fragment展示的界面*/
    public static MainItemBean getJumpBeanF(Class cls, String describe, Class fragment) {
        Bundle args = IntentUtils.setBString(null, KEY_FRAGMENT, fragment.getName());
        return buildJumpBean(new JumpUtils.JumpInfo(cls,describe,args));
    }

    public static MainItemBean getJumpBeanF(Class cls, String describe, Class fragment,Bundle args) {
        args = IntentUtils.setBString(args, KEY_FRAGMENT, fragment.getName());
        return buildJumpBean(new JumpUtils.JumpInfo(cls,describe,args));
    }

    public static MainItemBean getJumpBeanF(IBuildParams buildParams) {
        if(buildParams!=null){
            Bundle args = new Bundle();
            args.putSerializable(ContainerActivity.KEY_FRAGMENT,buildParams);
            return buildJumpBean(new JumpUtils.JumpInfo(ContainerActivity.class,buildParams.getFragment().getName(),args));
        }
        return null;
    }

    /**以指定Activity展示的界面*/
    public static MainItemBean getJumpBean(Class cls, String describe, Bundle args) {
        return buildJumpBean(new JumpUtils.JumpInfo(cls,describe,args));
    }
    /**以指定Activity展示的界面*/
    public static MainItemBean getJumpBean(String describe,Class cls, Bundle args) {
        return buildJumpBean(new JumpUtils.JumpInfo(cls,describe,args));
    }

    public static MainItemBean buildJumpBean(JumpBaseUtils.IJumpInfo jumpInfo) {
        MainItemBean bean = new MainItemBean();
        bean.setTitle(jumpInfo.getTitle());
        bean.setContent(jumpInfo.getTarget().getSimpleName());
        bean.setDate(new Date().toString());
        bean.setImageUrl(imageUrls[RandomUtils.getRandom(imageUrls.length-1)]);
        bean.setJumpInfo(jumpInfo);
        return bean;
    }
}