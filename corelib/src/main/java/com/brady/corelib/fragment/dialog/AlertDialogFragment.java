package com.brady.corelib.fragment.dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.brady.coreframe.utils.dataprocess.StringUtils;
import com.brady.coreframe.utils.view.TextViewUtils;
import com.brady.corelib.R;
import com.brady.corelib.fragment.interfaces.IDialogCallBack;



/**
 * Created by ZhangYuanBo on 2016/5/27.<br/>
 * 弹出提示框
 */
public class AlertDialogFragment extends DialogFragment implements View.OnClickListener{
    private final String KEY_TITLE= "tv_title";
    private final String KEY_CONTENT= "tv_content";
    private final String KEY_BTN_OK= "btn_ok";
    private final String KEY_BTN_CANCEL= "btn_cancel";

    private TextView tv_title;
    private TextView tv_content;
    private Button btn_ok;
    private Button btn_cancel;
    private String title;
    private String content;
    private String btn1;
    private String btn2;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.setCancelable(false);// 设置点击屏幕Dialog不消失,方式1
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_alert, container, false);
        tv_title = (TextView)view.findViewById(android.R.id.title);
        tv_content = (TextView)view.findViewById(android.R.id.content);
        btn_ok = (Button)view.findViewById(android.R.id.button1);
        btn_cancel = (Button)view.findViewById(android.R.id.button2);
        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        initViewUI(savedInstanceState);
        return view;
    }

    private void initViewUI(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            TextViewUtils.setTextViewValue(tv_title,savedInstanceState.getString(KEY_TITLE));
            TextViewUtils.setTextViewValue(tv_content,savedInstanceState.getString(KEY_CONTENT));
            TextViewUtils.setTextViewValue(btn_ok,savedInstanceState.getString(KEY_BTN_OK));
            TextViewUtils.setTextViewValue(btn_cancel,savedInstanceState.getString(KEY_BTN_CANCEL));
        }else{
            TextViewUtils.setTextViewValue(tv_title,title);
            TextViewUtils.setTextViewValue(tv_content,content);
            TextViewUtils.setTextViewValue(btn_ok,btn1);
            TextViewUtils.setTextViewValue(btn_cancel,btn2);
        }
    }

    @Override
    public void onClick(View view) {
        if (getActivity() instanceof IDialogCallBack) {
            IDialogCallBack listener = (IDialogCallBack) getActivity();
            switch (view.getId()) {
                case android.R.id.button1:
                    listener.callBack(getTag(), IDialogCallBack.CODE_BTN_LEFT,null);
                    break;
                case android.R.id.button2:
                    listener.callBack(getTag(),IDialogCallBack.CODE_BTN_RIGHT,null);
                    break;
                default:
                    listener.callBack(getTag(),view.getId(),null);
                    break;
            }
        }
        dismiss();
    }

    public AlertDialogFragment setTitle(String titleStr) {
        title = StringUtils.isNotEmpty(titleStr)?titleStr:title;
        return this;
    }
    public AlertDialogFragment setContent(String contentStr) {
        content = StringUtils.isNotEmpty(contentStr)?contentStr:content;
        return this;
    }
    public AlertDialogFragment setBtn(String btnLeft,String btnRight) {
        btn1 = StringUtils.isNotEmpty(btnLeft)?btnLeft:btn1;
        btn2 = StringUtils.isNotEmpty(btnRight)?btnRight:btn2;
        return this;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(outState!=null){
            outState.putCharSequence(KEY_TITLE,TextViewUtils.getTextViewValue(tv_title));
            outState.putCharSequence(KEY_CONTENT,TextViewUtils.getTextViewValue(tv_content));
            outState.putCharSequence(KEY_BTN_OK,TextViewUtils.getTextViewValue(btn_ok));
            outState.putCharSequence(KEY_BTN_CANCEL,TextViewUtils.getTextViewValue(btn_cancel));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        saveViewValue();
        super.onDismiss(dialog);
    }

    private void saveViewValue() {
        title =TextViewUtils.getTextViewValue(tv_title);
        content=TextViewUtils.getTextViewValue(tv_content);
        btn1=TextViewUtils.getTextViewValue(btn_ok);
        btn2=TextViewUtils.getTextViewValue(btn_cancel);
    }
}