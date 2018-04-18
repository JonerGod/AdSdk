package com.idouzi.adsdk.flowbanksdk.utils;

import android.util.Log;

import com.idouzi.adsdk.flowbanksdk.FlowBankSdkManager;

/**
 * Created by liuxueping on 2018/4/13.
 */

public class Logger {

    public static void v(String tag,String message){
        if(!FlowBankSdkManager.Config.DEBUG){
            return;
        }
        Log.v(tag,message);
    }


    public static void i(String tag,String message){
        if(!FlowBankSdkManager.Config.DEBUG){
            return;
        }
        Log.i(tag,message);
    }


    public static void d(String tag,String message){
        if(!FlowBankSdkManager.Config.DEBUG){
            return;
        }
        Log.d(tag,message);
    }

    public static void e(String tag,String message){
        if(!FlowBankSdkManager.Config.DEBUG){
            return;
        }
        Log.e(tag,message);
    }

    public static void w(String tag,String message){
        if(!FlowBankSdkManager.Config.DEBUG){
            return;
        }
        Log.w(tag,message);
    }
}
