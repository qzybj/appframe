package com.brady.corelib.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by ZhangYuanBo on 2017/1/23.
 * 用于Fragment的操作
 */
public class FragmentHelper {

    public static void showFragment(FragmentManager fm,int frameLayoutResId,Fragment source,Fragment target){
        if (target!=null&&target!= source) {
            FragmentTransaction transaction =  fm.beginTransaction();//开启Fragment事务
            if(source !=null){
                transaction.hide(source);
            }
            if (!target.isAdded()) {
                transaction.add(frameLayoutResId, target);
            } else {
                transaction.show(target);
            }
            transaction.commit();
        }
    }

    public static  void setFragmentVisible(FragmentManager fm,int frameLayoutResId,Fragment target,boolean isShow){
        if(fm!=null&&!fm.isDestroyed()&&frameLayoutResId>0&&target!=null){
            FragmentTransaction transaction = fm.beginTransaction();//开启Fragment事务
            if(isShow){
                if (!target.isAdded()) { // 隐藏当前的fragment，add下一个到Activity中
                    transaction.add(frameLayoutResId, target);
                }else{
                    transaction.show(target);
                }
            }else{
                transaction.hide(target);
            }
            transaction.commit();
        }
    }
}
