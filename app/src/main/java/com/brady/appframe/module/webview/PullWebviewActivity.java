package com.brady.appframe.module.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.brady.appframe.R;
import com.brady.appframe.module.webview.utils.Util;
import com.brady.appframe.module.webview.view.PullToRefreshCWebView;
import com.cwebview.i.IChooseFileListener;
import com.cwebview.refresh.IPullRefreshWebView;
import com.cwebview.refresh.PullWebView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 *
 * @author Cuckoo
 * @date 2016-12-21
 * @description
 *      采用PullToRefresh进行下拉刷新
 */

public class PullWebviewActivity extends AppCompatActivity implements IPullRefreshWebView {

    protected PullToRefreshCWebView mPullRefreshWebView;
    protected RelativeLayout loadingLayout;
    /** WebView所在的布局 */
    private FrameLayout webviewLayout =null;
    /** 网页视频全屏布局 */
    private FrameLayout videoFullSceenLayout =null;

    private PullWebView pullWebView = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pull Webview");
        setContentView(R.layout.webview_pull2refresh);

        webviewLayout = (FrameLayout) findViewById(R.id.webview_layout);
        videoFullSceenLayout = (FrameLayout) findViewById(R.id.webview_video_fullscreen_layout);
        loadingLayout = (RelativeLayout) findViewById(R.id.webview_loadinglayout);
        mPullRefreshWebView = (PullToRefreshCWebView) findViewById(R.id.webview_pull2refresh);

        pullWebView = new PullWebView(this, this, null);
        pullWebView.setAllowZoomEnable(true);
        //禁止下拉刷新
//        pullWebView.setPull2Refreshable(false);
        //普通页面加载、
        pullWebView.loadUrl("https://www.baidu.com");
        //播放视频
//        pullWebView.loadUrl("http://www.toutiao.com/a6366418297801409025/");
    }

    @Override
    public WebView getWebview() {
        return mPullRefreshWebView.getRefreshableView();
    }

    @Override
    public ViewGroup getLoadingView() {
        return loadingLayout;
    }


    @Override
    public ViewGroup getVideoFullSceenLayout() {
        return videoFullSceenLayout;
    }

    @Override
    public ViewGroup getWebviewLayout() {
        return webviewLayout;
    }

    @Override
    public void setRefreshMode(boolean isRefreshable) {
        PullToRefreshBase.Mode mode = PullToRefreshBase.Mode.DISABLED;//禁止
        if (isRefreshable) {
            mode = PullToRefreshBase.Mode.PULL_FROM_START;//开启
        }
        mPullRefreshWebView.setMode(mode);
    }

    @Override
    public void onRefreshComplete() {
        mPullRefreshWebView.onRefreshComplete();
    }

    @Override
    public void showChooseFileDialog(final IChooseFileListener chooseFileListener) {
        Util.showChooseFileDialog(this, chooseFileListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pullWebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pullWebView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pullWebView.onDestory();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isUsed = pullWebView.onKeyDown(keyCode,event);
        if( isUsed){
            return true ;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pullWebView.onActivityResult(requestCode,resultCode,data);
    }
}
