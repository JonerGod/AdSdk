package com.idouzi.adsdk.flowbanksdk.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.idouzi.adsdk.flowbanksdk.AdListener;
import com.idouzi.adsdk.flowbanksdk.R;
import com.idouzi.adsdk.flowbanksdk.activity.WebViewActivity;
import com.idouzi.adsdk.flowbanksdk.net.Ad;
import com.idouzi.adsdk.flowbanksdk.net.AdAPI;
import com.idouzi.adsdk.flowbanksdk.net.AdApiImp;
import com.idouzi.adsdk.flowbanksdk.net.BaseEvent;
import com.idouzi.adsdk.flowbanksdk.net.ResultBean;
import com.idouzi.adsdk.flowbanksdk.utils.ConvertUtils;
import com.idouzi.adsdk.flowbanksdk.utils.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;


/**
 * Created by liuxueping on 2018/4/8.
 */

public class BannerView extends AppCompatImageView implements Position {
    public final static String TAG = "BannerView";
    //状态
    private final static String EVENT_AD_LOAD = "ad_load";
    private final static String EVENT_AD_SHOW = "ad_show";
    private final static String EVENT_AD_TAPS = "ad_taps";
    AdListener adListener = null;
    String adUnitId = null;
    int refreshTime;
    AdAPI adAPI;
    Ad ad;
    HashMap<String, String> eventMap = new HashMap<>();

    private boolean  isText  =false;


    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.BannerView);
        adUnitId = typedArray.getString(R.styleable.BannerView_adUnitId);
        refreshTime = typedArray.getInteger(R.styleable.BannerView_refreshTime, 5 * 60);
        typedArray.recycle();

        adAPI = new AdApiImp(context);
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = ConvertUtils.px2dp(ConvertUtils.getScreenHeight());
        int defaultHeightSize = 0;
        if (height <= 400) {
            defaultHeightSize = 32;
        } else if (400 < height && height <= 720) {
            defaultHeightSize = 50;
        } else {
            defaultHeightSize = 90;
        }
        int screenWidth = ConvertUtils.getScreenWidth();
        int width = getMeasureSize(screenWidth, widthMeasureSpec);
        setMeasuredDimension(width, ConvertUtils.dp2px(defaultHeightSize));
    }

    private int getMeasureSize(int defaultSize, int measureSpec) {
        int dSize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
                if (size > dSize / 2) {
                    dSize = size;
                }
                break;
            case MeasureSpec.EXACTLY:
                if (size > dSize / 2) {
                    dSize = size;
                }
                break;
        }

        return dSize;
    }

    @Override
    public void loadAd() {

        //验证广告id是否为空
        if (!TextUtils.isEmpty(adUnitId)) {
            eventMap.put(adAPI.getAd(adUnitId), EVENT_AD_LOAD);

        } else {
            throw new NullPointerException("adUnitId is null");
        }
        adAPI.getAd(adUnitId);
    }

    @Override
    public void adLoadSuccesed(Ad ad) {
        if (null != adListener) {
            adListener.onAdLoaded();
        }
        this.ad = ad;
        adShow();
    }

    @Override
    public void adLoadFailed(int code, String info) {
        if (null != adListener) {
            adListener.onAdLoadFailed(code, info);
        }
    }

    @Override
    public void adShow() {
        if(!TextUtils.isEmpty(ad.getImgUrl())){
            isText =false;
            Glide.with(getContext()).load(ad.getImgUrl()).into(this);
        }else {
          isText =true;
          setBackgroundColor(Color.parseColor("88596371"));
        }
        if (ConvertUtils.isCover(this)) {
            Logger.i(TAG, "广告内容被遮挡！");
            return;
        }
        adShowReport();
    }

    @Override
    public void adShowReport() {
       String uuid  =  adAPI.showAdReport(adUnitId, ad.getId());
       eventMap.put(uuid,EVENT_AD_SHOW);
    }

    @Override
    public void adShowReportOk() {
        Logger.d(TAG,"adShowReportOk");

    }

    @Override
    public void adShowReportFailed(int code,String info) {
        Logger.d(TAG,"adShowReportFailed"+"code:"+code+"info："+info);
    }

    @Override
    public void adTaps() {
        if (null == ad) {
            return;
        }
        if (null != adListener) {
            adListener.onAdOpened();
        }

        adTapsReport();
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.ACTION_URL, ad.getUrl());
        getContext().startActivity(intent);

    }

    @Override
    public void adTapsReport() {
        String uuid  =adAPI.adTapsReport(adUnitId, ad.getId());
        eventMap.put(uuid,EVENT_AD_SHOW);
    }

    @Override
    public void adTapsReportOk() {
        Logger.d(TAG,"adTapsReportOk");

    }

    @Override
    public void adTapsReportFailed(int code,String info) {
        Logger.d(TAG,"adTapsReportFailed+code+"+code+" info:"+info);
    }

    @Override
    public void setAdListener(AdListener adListener) {
        this.adListener = adListener;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            adTaps();
        }
        return super.onTouchEvent(event);
    }



    @Override
    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvent baseEvent) {
        handleEvent(baseEvent);
    }

    private void handleEvent(BaseEvent event) {
        String uuid = event.getUuid();
        String eventFlag = eventMap.get(uuid);
        if (TextUtils.isEmpty(eventFlag)) {
            return;
        }
        eventMap.remove(uuid);
        switch (eventFlag) {
            case EVENT_AD_LOAD:
                if (TextUtils.equals(event.getReturn_code(), ResultBean.SUCCESS)) {
                    adLoadSuccesed((Ad) event.getT());
                } else {
                    adLoadFailed(event.getCode(), event.getReturn_msg());
                }
                break;
            case EVENT_AD_SHOW:
                if (TextUtils.equals(event.getReturn_code(), ResultBean.SUCCESS)) {
                    adShowReportOk();
                } else {
                    adShowReportFailed(event.getCode(), event.getReturn_msg());
                }
                break;
            case EVENT_AD_TAPS:
                if (TextUtils.equals(event.getReturn_code(), ResultBean.SUCCESS)) {
                    adTapsReportOk();
                } else {
                    adTapsReportFailed(event.getCode(), event.getReturn_msg());
                }
                break;

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isText&&null!=ad){
            String text = ad.getText();
            drawText(canvas,text);
        }
    }

    private void drawText(Canvas canvas,String text){
        Paint paint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(60);
        paint.setColor(Color.WHITE);
        int x = getWidth()/2;
        Paint.FontMetricsInt mFontMetricsInt  =paint.getFontMetricsInt();
        int y = (getHeight() - mFontMetricsInt.ascent - mFontMetricsInt.descent) / 2;
        canvas.drawText(text,x,y,paint);
    }
}
