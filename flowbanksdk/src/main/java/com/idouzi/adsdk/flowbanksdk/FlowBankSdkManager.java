package com.idouzi.adsdk.flowbanksdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.idouzi.adsdk.flowbanksdk.utils.DataActivityLifecycleCallbacks;
import com.idouzi.adsdk.flowbanksdk.utils.PrefUtils;

/**
 * Created by liuxueping on 2018/4/8.
 */

public class FlowBankSdkManager {


    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;
    private DataActivityLifecycleCallbacks dataActivityLifecycleCallbacks;
    private String appId;
    private String appKey;

    Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;

    private static FlowBankSdkManager instance;

    private FlowBankSdkManager(@NonNull Context context) {
        this.sApplication = (Application) context.getApplicationContext();
    }

    public static FlowBankSdkManager getInstance(Context context) {
        if (instance == null) {
            synchronized (FlowBankSdkManager.class) {
                if (instance == null) {
                    instance = new FlowBankSdkManager(context);

                }

            }
        }

        return instance;
    }

    public void init(String appId, String appKey) {
        this.init(appId, appKey, false);
    }

    public void init(String appId, String appKey, boolean debug) {
        this.appId = appId;
        this.appKey = appKey;
        Config.DEBUG = debug;
        if(dataActivityLifecycleCallbacks==null){
            dataActivityLifecycleCallbacks  = new DataActivityLifecycleCallbacks();
            sApplication.registerActivityLifecycleCallbacks(dataActivityLifecycleCallbacks);
        }

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


    public static class Config {
        public static boolean DEBUG = false;

    }

    public static Application getApp() {
        if (null == sApplication) {
            throw new NullPointerException("u should init first");
        }
        return sApplication;
    }

    public Activity getCurrentActivity(){
        if(null==dataActivityLifecycleCallbacks){
            throw new NullPointerException("u should init first");
        }
       return dataActivityLifecycleCallbacks.getmCurrentActivity();
    }
    public final static class Gender {
        public final static String FEMALE = "female";

        public final static String EMALE = "male";

        protected final static String GENDER_KEY="gender";

        protected final static String AGE  = "age";

        protected  final static String LOCATION  = "location";

    }

    public static void setGender(String gender) {
        PrefUtils.getInstance(getApp()).put(Gender.GENDER_KEY,gender);
    }

    public static String  getGender(){
      return   PrefUtils.getInstance(getApp()).getString(Gender.GENDER_KEY);
    }

    public  static void  setUserAge(int age){
        PrefUtils.getInstance(getApp()).put(Gender.AGE,age);
    }

    public static int getUserAge(){
      return  PrefUtils.getInstance(getApp()).getInt(Gender.AGE);
    }


    public  static String getLocation(){
        return   PrefUtils.getInstance(getApp()).getString(Gender.LOCATION);

    }

    public  static void  setLocation(String location){
           PrefUtils.getInstance(getApp()).put(Gender.LOCATION,location);
    }
}
