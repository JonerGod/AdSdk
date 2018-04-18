package com.idouzi.adsdk.flowbanksdk.net;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Created by liuxueping on 2018/4/11.
 */

public class AdApiImp extends BaseApi<AdService> implements AdAPI {
    public AdApiImp(Context context) {
        super(context);
    }

    @Override
    public String getAd(@NonNull String adUnitId) {
        String uuid = UUID.randomUUID().toString();
        service.getAd(adUnitId).enqueue(new BaseCallBack<List<Ad>>(new BaseEvent<List<Ad>>(uuid)));
        return uuid;
    }

    @Override
    public String showAdReport(@NonNull String adUnitId, @NonNull String adId) {
        String uuid = UUID.randomUUID().toString();
        service.showAdReport(adUnitId, adId).enqueue(new BaseCallBack<String >(new BaseEvent<String>(uuid)));
        return uuid;
    }

    @Override
    public String adTapsReport(@NonNull String adUnitId, @NonNull String adId) {
        String uuid = UUID.randomUUID().toString();
        service.adTapsRepor(adUnitId, adId).enqueue(new BaseCallBack<String >(new BaseEvent<String>(uuid)));
        return uuid;
    }

    @Override
    public String adCloseReport(@NonNull String adUnitId, @NonNull String adId) {
        String uuid = UUID.randomUUID().toString();
        service.adClose(adUnitId, adId).enqueue(new BaseCallBack<String >(new BaseEvent<String>(uuid)));
        return uuid;
    }
}
