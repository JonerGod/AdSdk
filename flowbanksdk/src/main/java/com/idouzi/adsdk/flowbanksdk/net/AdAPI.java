package com.idouzi.adsdk.flowbanksdk.net;

import android.support.annotation.NonNull;

/**
 * Created by liuxueping on 2018/4/11.
 */

public interface AdAPI {
    /**
     * 获取广告链接
     * @param adUnitId  广告位id
     * @return
     */
    String getAd(@NonNull String adUnitId);

    /**
     * 广告曝光上报
     * @param adUnitId
     * @param adId
     * @return
     */
    String showAdReport(@NonNull String adUnitId,@NonNull String adId);

    /**
     * 广告点击上报
     * @param adUnitId
     * @param adId
     * @return
     */
    String adTapsReport(@NonNull String adUnitId,@NonNull String adId);

    String adCloseReport(@NonNull String adUnitId,@NonNull String adId);
}
