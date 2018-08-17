package com.idouzi.adsdk.flowbanksdk.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuxueping on 2018/4/28.
 */

public class DataActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {

    //管理所有activity
    private List<Activity> mActivityList;

    private Activity mCurrentActivity;

    public DataActivityLifecycleCallbacks() {

            mActivityList = new LinkedList<>();

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        synchronized (DataActivityLifecycleCallbacks.class) {
            if (!mActivityList.contains(activity)) {
                mActivityList.add(activity);
            }
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        if (mActivityList == null) {
            Logger.e("TAG", "mActivityList==null when remove activity");
            return;
        }
        synchronized (DataActivityLifecycleCallbacks.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }

    public Activity getmCurrentActivity() {
        synchronized (DataActivityLifecycleCallbacks.class) {
            int size   = mActivityList.size();
            if(size>0){
                mCurrentActivity  = mActivityList.get(size-1);
            }
        }

        return mCurrentActivity;
    }
}
