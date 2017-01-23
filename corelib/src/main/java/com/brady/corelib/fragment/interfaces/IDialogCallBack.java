package com.brady.corelib.fragment.interfaces;

/**
 * Created by ZhangYuanBo on 2016/5/30.
 *  用于DialogFragment与activity的数据
 */
public interface IDialogCallBack {
    /**用于判断的基本值*/
    int CODE_COMMON = 11000;
    int CODE_BTN_LEFT = CODE_COMMON+1;
    int CODE_BTN_RIGHT = CODE_COMMON+2;

    /**
     * 接收Fragment发送的数据
     * @param fragmentTag Fragment tag
     * @param type
     */
    boolean callBack(String fragmentTag, int type,Object data);
}
