package com.idouzi.adsdk.flowbanksdk.net;


import android.text.TextUtils;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liuxueping on 2018/4/11.
 */

public class BaseCallBack<T> implements Callback<ResultBean> {

    private BaseEvent<T> baseEvent;

    public BaseCallBack(BaseEvent<T> baseEvent) {
        this.baseEvent = baseEvent;
    }

    @Override
    public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {
        if(response.isSuccessful()){
            baseEvent.setCode(response.code());
            ResultBean resultBean  =response.body();
            baseEvent.setReturn_code(resultBean.getReturn_code());
            if(TextUtils.equals(resultBean.getReturn_code(),ResultBean.SUCCESS)){
                Type type = super.getClass().getGenericSuperclass();
                if(type instanceof ParameterizedType){
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] types = parameterizedType.getActualTypeArguments();
                    T t  = new Gson().fromJson(resultBean.getReturn_msg().toString(),type);
                    baseEvent.setT(t);
                }

            }else {
                baseEvent.setReturn_msg(resultBean.getReturn_msg().toString());
            }
            EventBus.getDefault().post(baseEvent);
        }else {
            baseEvent.setReturn_code(ResultBean.FAIL);
            baseEvent.setReturn_msg("服务器错误+code:"+response.code());
            EventBus.getDefault().post(baseEvent);
        }

    }

    @Override
    public void onFailure(Call<ResultBean> call, Throwable t) {
        baseEvent.setReturn_code(ResultBean.FAIL);
        baseEvent.setReturn_msg(t.getMessage());
        EventBus.getDefault().post(baseEvent);
    }
}
