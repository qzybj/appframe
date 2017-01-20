package com.brady.corelib.config;

import com.brady.coreframe.config.ConstantsBase;
import com.brady.coreframe.utils.app.AppUtils;

/**
 *  定义常量：界面传递key相关，及 requestcode,resultcode 值等
 */
public class Constants extends ConstantsBase {
    public static final String TAG = AppUtils.getApplicationName();


    /**基本值*/
    private static final int BASECODE = 0x021001;

    /**requestcode 基本值*/
    private static final int BASECODE_REQUEST = BASECODE+1000;
    /**resultcode 基本值*/
    private static final int BASECODE_RESULT = BASECODE+2000;
    /**Handler Message 基本值*/
    private static final int BASECODE_MESSAGEC = BASECODE+3000;


    /**提示Toast*/
    public static final int MSG_WHAT_SHOWTOAST = BASECODE_MESSAGEC+1;
    /**提示Toast*/
    public static final int MSG_WHAT_DATA_START = BASECODE_MESSAGEC+2;
    /**提示Toast*/
    public static final int MSG_WHAT_DATA_CANCEL = BASECODE_MESSAGEC+3;
    /**提示Toast*/
    public static final int MSG_WHAT_DATA_DONE = BASECODE_MESSAGEC+4;
    /**网络重试  - 状态值*/
    public static final int MSG_WHAT_RETRY_DIALOG = BASECODE_MESSAGEC+5;

}
