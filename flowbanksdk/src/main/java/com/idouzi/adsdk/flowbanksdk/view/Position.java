package com.idouzi.adsdk.flowbanksdk.view;

import com.idouzi.adsdk.flowbanksdk.AdListener;
import com.idouzi.adsdk.flowbanksdk.net.Ad;

/**
 * Created by liuxueping on 2018/4/8.
 */

interface Position {
    void loadAd();
    void adLoadSuccesed(Ad ad);
    void adLoadFailed(int code,String info);
    void adShow();
    void adShowReport();
    void adShowReportOk();
    void adShowReportFailed(int code,String errinfo);
    void adTaps();
    void adTapsReport();
    void adTapsReportOk();
    void adTapsReportFailed(int code,String errinfo);
    void setAdListener(AdListener adListener);

}
