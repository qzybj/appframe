package com.brady.corelib.fragment.bar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.brady.coreframe.utils.dataprocess.StringUtils;
import com.brady.coreframe.utils.view.TextViewUtils;
import com.brady.corelib.R;
import com.brady.corelib.fragment.interfaces.ITitleBarClickListener;


/**
 * Created by ZhangYuanBo on 2016/5/27.
 * titlebar 模块：页面项部标题栏的实现
 */
public class TitleBarFragment extends Fragment implements View.OnClickListener{
    LinearLayout layout_leftBtn;
    TextView tv_left;
    TextView tv_title;
    LinearLayout layout_rightBtn;
    TextView tv_right;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_fragment_titlebar, container, false);
        layout_leftBtn = (LinearLayout)view.findViewById(R.id.titlebar_layout_left);
        layout_rightBtn = (LinearLayout)view.findViewById(R.id.titlebar_layout_right);
        tv_title = (TextView)view.findViewById(R.id.titlebar_tv_title);
        tv_left = (TextView)view.findViewById(R.id.titlebar_tv_left);
        tv_right = (TextView)view.findViewById(R.id.titlebar_tv_right);
        layout_leftBtn.setOnClickListener(this);
        layout_rightBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (getActivity() instanceof ITitleBarClickListener) {
            ITitleBarClickListener listener = (ITitleBarClickListener) getActivity();
            if (layout_leftBtn == view) {
                listener.onClickTitleLeft(view);
            } else if (layout_rightBtn == view) {
                listener.onClickTitleRight(view);
            } else{
                listener.onTitleBarClick(view);
            }
        }
    }

    //--------------------设置标题栏展示实现方法
    /**
     * 设置title内容
     * @param titleResid
     */
    public final void setTitle(int titleResid) {
        setTitle(getString(titleResid));
    }

    /**
     * 设置title内容
     *
     * @param titleStr
     */
    public final void setTitle(String titleStr) {
        TextViewUtils.setTextViewValue(tv_title,titleStr);
    }

    /**
     * 控制标题栏左边按钮显示或隐藏
     */
    public final void setLeftVisibility(boolean isVisibility) {
        if (layout_leftBtn != null) {
            layout_leftBtn.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置title左边按钮的显示内容
     *
     * @param titleResid
     */
    public final void setLeftText(int titleResid) {
        if (titleResid > 0) {
            setLeftText(getString(titleResid));
        }
    }

    /**
     * 设置title左边按钮的显示内容
     *
     * @param titleStr
     */
    public final void setLeftText(String titleStr) {
        if (tv_left != null && StringUtils.isNotEmpty(titleStr)) {
            TextViewUtils.setTextViewValue(tv_left,titleStr);
        }
    }

    /**
     * 设置title左边按钮的显示图片
     * @param drawableResid
     */
    public final void setLeftDrawable(int drawableResid, int padding) {
        if (tv_left != null) {
            if (drawableResid > 0) {
                Drawable drawable = getResources().getDrawable(drawableResid);
                if (drawable != null) {
                    TextViewUtils.setTextViewDrawable(tv_left, drawable, padding);
                }
            } else {
                TextViewUtils.setTextViewDrawable(tv_left, null, padding);
            }
        }
    }


    /**
     * 控制标题栏右边按钮显示或隐藏
     */
    public final void setRightVisibility(boolean isVisibility) {
        if (layout_rightBtn != null) {
            layout_rightBtn.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置title右边按钮的显示内容
     *
     * @param titleResid
     */
    public final void setRightText(int titleResid) {
        if (titleResid > 0) {
            setRightText(getString(titleResid));
        }
    }

    /**
     * 设置title右边按钮的显示内容
     *
     * @param title
     */
    public final void setRightText(String title) {
        TextViewUtils.setTextViewValue(tv_right,title);
    }

    /**
     * 设置title右边按钮的显示图片
     * @param drawableResid
     */
    public final void setRightDrawable(int drawableResid, int padding) {
        if (tv_right != null && drawableResid > 0) {
            Drawable drawable = getResources().getDrawable(drawableResid);
            if (drawable != null) {
                setRightVisibility(true);
                TextViewUtils.setTextViewDrawable(tv_right, drawable, padding);
            }
        }
    }
}