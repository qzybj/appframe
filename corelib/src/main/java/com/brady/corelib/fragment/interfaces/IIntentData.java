package com.brady.corelib.fragment.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ZhangYuanBo on 2017/1/20.
 */

public interface IIntentData extends Serializable {
    /**底部的Item*/
    ArrayList<ITabItem> getTabItems();
}
