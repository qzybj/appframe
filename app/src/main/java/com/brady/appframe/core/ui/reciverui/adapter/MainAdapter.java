package com.brady.appframe.core.ui.reciverui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.brady.appframe.MyApplication;
import com.brady.appframe.R;
import com.brady.appframe.core.ui.reciverui.bean.MainItemBean;
import com.brady.appframe.module.common.decoration.GridDecoration;
import com.brady.coreframe.module.loadimage.LoadImageManager;
import com.brady.coreframe.utils.jump.JumpBaseUtils;
import com.brady.corelib.CApplication;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import java.util.List;


public class MainAdapter extends BaseQuickAdapter<MainItemBean> {

    public MainAdapter(RecyclerView recyclerView, List data) {
        super(R.layout.rvadatper_main_item,data);
        initAdapter(recyclerView);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainItemBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setVisible(R.id.tv_date,false);
        LoadImageManager.instance().loadImage((ImageView)(helper.getView(R.id.iv_icon)),item.getImageUrl());
    }

    private void initAdapter(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.instance(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new GridDecoration(getOptimizeContext(), GridDecoration.STYLE_VERTICAL));//设定分隔线
        openLoadAnimation();
    }
    private Context getOptimizeContext(){
        return CApplication.instance();
    }
}