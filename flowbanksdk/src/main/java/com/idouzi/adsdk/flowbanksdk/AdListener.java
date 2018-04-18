package com.idouzi.adsdk.flowbanksdk;

/**
 * Created by liuxueping on 2018/4/8.
 */

public interface AdListener {
    void onAdLoaded();
    void onAdLoadFailed(int errorCode,String info);
    void onAdOpened();
    void onAdClosed();
}
