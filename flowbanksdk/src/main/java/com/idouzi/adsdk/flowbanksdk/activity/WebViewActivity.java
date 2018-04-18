package com.idouzi.adsdk.flowbanksdk.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.idouzi.adsdk.flowbanksdk.R;

public class WebViewActivity extends AppCompatActivity {


    public final  static String ACTION_URL = "URL";
    public final  static String AD_ID  ="adId";
    String adId;
    WebView  mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    private void initView(){
        mWebView  =findViewById(R.id.web);
        setting();
        String url  = getIntent().getStringExtra(ACTION_URL);
        if(TextUtils.isEmpty(url)){
            finish();
            return;
        }
        mWebView.loadUrl(url);


    }
    private void setting(){
        WebSettings webSettings =   mWebView .getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        mWebView.requestFocusFromTouch();

        webSettings.setJavaScriptEnabled(true);  //支持js


        webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小


        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局


        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存

        webSettings.setAllowFileAccess(true);  //设置可以访问文件

        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点


        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return super.shouldOverrideUrlLoading(view,request);

            }
        });
    }
}
