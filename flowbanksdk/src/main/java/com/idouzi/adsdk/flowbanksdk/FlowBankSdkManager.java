package com.idouzi.adsdk.flowbanksdk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by liuxueping on 2018/4/8.
 */

public class FlowBankSdkManager {

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;
    private String  appId;
    private String  appKey;

    private static FlowBankSdkManager  instance;

    private FlowBankSdkManager(@NonNull Context context) {
        this.sApplication = (Application) context.getApplicationContext();
    }

    public static FlowBankSdkManager getInstance(Context context) {
        if(instance == null){
            synchronized (FlowBankSdkManager.class){
                if(instance ==null){
                    instance = new FlowBankSdkManager(context);
                }

            }
        }

        return instance;
    }
    public void init(String appId,String appKey){
        this.init(appId,appKey,false);
    }

    public void init(String appId,String appKey,boolean debug){
        this.appId =appId;
        this.appKey =appKey;
        Config.DEBUG = debug;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }


    public static class Config{
        public static boolean DEBUG  = false;

    }
    public static Application getApp(){
        if(null==sApplication){
            throw new NullPointerException("u should init first");
        }
        return sApplication;
    }
}
