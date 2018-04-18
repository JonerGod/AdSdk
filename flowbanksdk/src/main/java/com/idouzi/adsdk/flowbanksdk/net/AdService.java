package com.idouzi.adsdk.flowbanksdk.net;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liuxueping on 2018/4/11.
 */

public interface AdService{
    @POST
    @FormUrlEncoded
    Call<ResultBean> getAd(@NonNull @Field("adUnitId")String adUnitId);

    @POST
    @FormUrlEncoded
    Call<ResultBean> showAdReport(@NonNull @Field("adUnitId") String adUnitId, @NonNull @Field("adId") String adId);

    @POST
    @FormUrlEncoded
    Call<ResultBean> adTapsRepor(@NonNull @Field("adUnitId") String adUnitId, @NonNull @Field("adId") String adId);

    @POST
    @FormUrlEncoded
    Call<ResultBean> adClose(@NonNull @Field("adUnitId") String adUnitId, @NonNull @Field("adId") String adId);

}
