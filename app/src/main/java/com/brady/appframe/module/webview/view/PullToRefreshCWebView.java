package com.brady.appframe.module.webview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebView;
import com.cwebview.CWebView;
import com.handmark.pulltorefresh.library.OverscrollHelper;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;


/**
 *
 * @author Cuckoo
 * @date 2016-12-06
 * @description
 *      自定义Webview,继承{@link CWebView}
 */

public class PullToRefreshCWebView extends PullToRefreshWebView {
    public PullToRefreshCWebView(Context context) {
        super(context);
    }

    public PullToRefreshCWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshCWebView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshCWebView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    protected WebView createRefreshableView(Context context, AttributeSet attrs) {
        WebView webView;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            webView = new InternalWebViewSDK9(context, attrs);
        } else {
            webView = new CWebView(context, attrs);
        }

        webView.setId(com.handmark.pulltorefresh.library.R.id.webview);
        return webView;
    }

    @TargetApi(9)
    final class InternalWebViewSDK9 extends CWebView {

        // WebView doesn't always scroll back to it's edge so we add some
        // fuzziness
        static final int OVERSCROLL_FUZZY_THRESHOLD = 2;

        // WebView seems quite reluctant to overscroll so we use the scale
        // factor to scale it's value
        static final float OVERSCROLL_SCALE_FACTOR = 1.5f;

        public InternalWebViewSDK9(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                       int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

            final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                    scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

            // Does all of the hard work...
            OverscrollHelper.overScrollBy(PullToRefreshCWebView.this, deltaX, scrollX, deltaY, scrollY,
                    getScrollRange(), OVERSCROLL_FUZZY_THRESHOLD, OVERSCROLL_SCALE_FACTOR, isTouchEvent);

            return returnValue;
        }

        private int getScrollRange() {
            return (int) Math.max(0, (float) Math.floor(getRefreshableView().getContentHeight() * getRefreshableView().getScale())
                    - (getHeight() - getPaddingBottom() - getPaddingTop()));
        }
    }
}
