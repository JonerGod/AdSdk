package com.idouzi.adsdk.flowbanksdk.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;

import com.idouzi.adsdk.flowbanksdk.FlowBankSdkManager;


/**
 * Created by liuxueping on 2018/4/8.
 */

public final class ConvertUtils {
    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) FlowBankSdkManager.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return FlowBankSdkManager.getApp().getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) FlowBankSdkManager.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return FlowBankSdkManager.getApp().getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }
    public static int dp2px(final float dpValue) {
        final float scale = FlowBankSdkManager.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(final float pxValue) {
        final float scale = FlowBankSdkManager.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
