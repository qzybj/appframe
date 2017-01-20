package com.brady.corelib.fragment.bar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.brady.coreframe.module.loadimage.LoadImageManager;
import com.brady.coreframe.utils.dataprocess.ListUtils;
import com.brady.coreframe.utils.view.TextViewUtils;
import com.brady.coreframe.utils.view.ViewUtils;
import com.brady.corelib.R;
import com.brady.corelib.fragment.interfaces.ITabBarClickListener;
import com.brady.corelib.fragment.interfaces.ITabItem;
import java.util.ArrayList;


/**
 * Created by ZhangYuanBo on 2016/5/27.<br/>
 * TabBottomBar 模块：底部页面选择栏的实现(该Fragment为动态实现TabItem的)<br/>
 * 集成的activity要实现tabBottomBarClickListener接口，接收点击处理回调<br/>
 */
public class TabBarFragment extends Fragment implements View.OnClickListener {

    public static final int ID_TAB_BASECODE = 31000;

    LinearLayout tabLayout_container;

    //是否开启自定义字体颜色
    private boolean isCustomTextColor = false;
    //默认的自定义字体颜色
    int textColor_select = android.R.color.white;
    int textColor_no_select = android.R.color.black;

    ArrayList<ITabItem> mList;
    int lastSelectViewId = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_fragment_tabar, container, false);
        tabLayout_container = (LinearLayout)view.findViewById(R.id.tab_bottom_container);
        return view;
    }

    /**
     * 外部调用tabView的初始化方法
     * @param list
     */
    public void initTabView(ArrayList<ITabItem> list) {
        if(tabLayout_container!=null){
            if(ListUtils.isNotEmptyList(list)){
                mList = list;
                tabLayout_container.removeAllViews();
                for (int i = 0; i < list.size(); i++) {
                    View view = createTabView(i,list.get(i));
                    if(view!=null){
                        tabLayout_container.addView(view);
                    }
                }
            }
        }
    }

    /**生成TabView*/
    private View createTabView(int viewId,ITabItem tabItem) {
        View view = ViewUtils.inflateView(getActivity(),R.layout.frame_fragment_tabbar_item);
        if (view != null && tabItem != null) {
            view.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1));
            view.setOnClickListener(this);
            view.setId(ID_TAB_BASECODE + viewId);
            LoadImageManager.instance().loadImage((ImageView) view.findViewById(R.id.tab_item_iv), tabItem.getImageResId());
            TextViewUtils.setTextViewValue((TextView) view.findViewById(R.id.tab_item_tv), tabItem.getText());
        }
        return view;
    }

    private void setSelectTabView(View view,boolean select) {
        if(view !=null){
            view.setSelected(select);
            view.findViewById(R.id.tab_item_iv).setSelected(select);
            view.findViewById(R.id.tab_item_tv).setSelected(select);
            if(isCustomTextColor){
                ((TextView)view.findViewById(R.id.tab_item_tv)).setTextColor(select?getResources().getColor(textColor_select):getResources().getColor(textColor_no_select));
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(lastSelectViewId!=-1){
            setSelectTabView(tabLayout_container.findViewById(lastSelectViewId),false);
        }
        setSelectTabView(view,true);
        lastSelectViewId = view.getId();
        if (getActivity() instanceof ITabBarClickListener) {//将点击事件传递到主界面处理
            ITabBarClickListener listener = (ITabBarClickListener) getActivity();
            listener.onTabClick(view);
        }
    }
}