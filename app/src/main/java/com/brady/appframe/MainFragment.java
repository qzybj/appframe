package com.brady.appframe;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.brady.appframe.core.ui.reciverui.adapter.MainAdapter;
import com.brady.appframe.core.ui.reciverui.bean.MainItemBean;
import com.brady.appframe.module.common.ui.SimpleFragment;
import com.brady.appframe.module.common.utils.TabBottomBarUtils;
import com.brady.appframe.module.common.utils.TestDataBuilder;
import com.brady.appframe.module.webview.PullWebviewActivity;
import com.brady.appframe.module.webview.SwipeWebviewActivity;
import com.brady.coreframe.ui.base.FrameBaseFragment;
import com.brady.coreframe.utils.jump.JumpBaseUtils;
import com.brady.corelib.fragment.interfaces.IBuildParams;
import com.brady.corelib.fragment.interfaces.ITabItem;
import com.brady.corelib.fragment.interfaces.impl.BuildParams;
import com.brady.corelib.reciverui.ui.ContainerActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


public class MainFragment extends FrameBaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{
    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

    @BindView(R.id.srlayout_common)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.rv_common)
    public RecyclerView mRecyclerView;

    protected MainAdapter adapter;

    public MainFragment() {}

    @Override
    public void initConstant(Bundle savedInstanceState) {}

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initContentView(View view) {
        initSwipeRefreshLayout();
        mRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(adapter!=null){
                    MainItemBean item = (MainItemBean)adapter.getItem(position);
                    JumpBaseUtils.goActivity(getBaseActivity(),item.getJumpInfo());
                }
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getBaseActivity(), "" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getBaseActivity(), "" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getBaseActivity(), "" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        loadData(getShowBeanList());
    }

    protected void loadData(List<MainItemBean> list) {
        if (adapter == null){
            adapter = new MainAdapter(mRecyclerView,list);
            mRecyclerView.setAdapter(adapter);
        }else{
            adapter.addData(list);
        }
        adapter.notifyDataSetChanged();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_ffffff, R.color.color_33a6ff,R.color.color_ff0000);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                onRefreshData();
            }
        }, 1000);
    }
    public void onRefreshData() {
        //adapter.setNewData(getTestData());
        //adapter.notifyDataSetChanged();
    }
    @Override
    public void onLoadMoreRequested() {

    }

    public static ArrayList<MainItemBean> getShowBeanList() {
        ArrayList<MainItemBean> list = new ArrayList<>();
        list.add(TestDataBuilder.getJumpBean("LiveAnimation - 测试View动画",PullWebviewActivity.class,null));
        list.add(TestDataBuilder.getJumpBean("SwipeBackHelper - 滑动返回",SwipeWebviewActivity.class,null));

        list.add(TestDataBuilder.getJumpBeanF(new BuildParams(SimpleFragment.class,true,true,TabBottomBarUtils.getTestTabItemList())));
        return list;
    }
}