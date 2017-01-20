package com.brady.appframe.module.common.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.brady.appframe.R;
import com.brady.appframe.core.ui.fragment.dialog.InputDialogFragment;
import com.brady.appframe.core.ui.fragment.dialog.LoginDialogFragment;
import com.brady.corelib.base.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ZhangYuanBo on 2016/5/27.
 */
public class SimpleFragment extends BaseFragment {

    @BindView(R.id.tv_fragment_content)
    TextView tv_content;
    @BindView(R.id.btn_inputdialog)
    Button btn_inputdialog;
    @BindView(R.id.btn_logindialog)
    Button btn_logindialog;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_simple;
    }

    @Override
    public void initContentView(View view) {
        tv_content.setText("this is " + this.getClass().getName());
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_inputdialog, R.id.btn_logindialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_inputdialog:
                InputDialogFragment inputDialogFragment = new InputDialogFragment();
                showFragment(inputDialogFragment);
                break;
            case R.id.btn_logindialog:
                LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
                showFragment(loginDialogFragment);
                break;
        }
    }

    private void showFragment(DialogFragment targetFragment){
        if(getActivity()!=null&&!getActivity().isFinishing()&&targetFragment!=null){
            targetFragment.show(getFragmentManager(), "loginDialog");
        }
    }
}