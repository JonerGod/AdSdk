package com.idouzi.adsdk.flowbanksdk.net;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by liuxueping on 2018/4/11.
 */

public class BaseApi<Service> {
    private static Retrofit retrofit;
    private static final String BASE_URL ="";
    protected  Service service;
    private Context context;

    public BaseApi(Context context) {
        this.context = context;

    }

    private void intRetrofit(){
       if(null!=retrofit){
           return;
       }

        HttpLoggingInterceptor  httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * 添加默认参数
         */
        Interceptor pubParmInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addNetworkInterceptor(pubParmInterceptor)
                .build();
        retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
