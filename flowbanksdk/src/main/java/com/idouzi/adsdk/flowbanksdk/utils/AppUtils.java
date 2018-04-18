package com.idouzi.adsdk.flowbanksdk.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.idouzi.adsdk.flowbanksdk.FlowBankSdkManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by liuxueping on 2018/4/8.
 */

public final class AppUtils {
    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 是否前台运行
     * @return
     */
    public static boolean isAppForeground() {
        ActivityManager am =
                (ActivityManager) FlowBankSdkManager.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) return false;
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return aInfo.processName.equals(FlowBankSdkManager.getApp().getPackageName());
            }
        }
        return false;
    }

    /**
     * 获取应用包名
     * @return
     */
    public static String getAppPackageName() {
        return FlowBankSdkManager.getApp().getPackageName();
    }

    /**
     * 获取应用名称
     * @return
     */
    public static String getAppName() {
        return getAppName(FlowBankSdkManager.getApp().getPackageName());
    }

    public static String getAppName(final String packageName) {
        if (TextUtils.isEmpty(packageName)) return null;
        try {
            PackageManager pm = FlowBankSdkManager.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取版本号
     * @return
     */
    public static String getAppVersionName() {
        return getAppVersionName(FlowBankSdkManager.getApp().getPackageName());
    }
    public static String getAppVersionName(final String packageName) {
        if (TextUtils.isEmpty(packageName)) return null;
        try {
            PackageManager pm = FlowBankSdkManager.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Signature[] getAppSignature() {
        return getAppSignature(FlowBankSdkManager.getApp().getPackageName());
    }

    public static Signature[] getAppSignature(final String packageName) {
        if (TextUtils.isEmpty(packageName)) return null;
        try {
            PackageManager pm = FlowBankSdkManager.getApp().getPackageManager();
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return pi == null ? null : pi.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取应用sha1签名
     * @return
     */
    public static String getAppSignatureSHA1() {
        return getAppSignatureSHA1(FlowBankSdkManager.getApp().getPackageName());
    }

    public static String getAppSignatureSHA1(@NonNull final String packageName) {
        Signature[] signature = getAppSignature(packageName);
        if (signature == null || signature.length <= 0) return null;
        return encryptSHA1ToString(signature[0].toByteArray()).
                replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }

    private static final char HEX_DIGITS[] =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String encryptSHA1ToString(final byte[] data) {
        return bytes2HexString(encryptSHA1(data));
    }

    private static byte[] encryptSHA1(final byte[] data) {
        if (data == null || data.length <= 0) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = HEX_DIGITS[bytes[i] >>> 4 & 0x0f];
            ret[j++] = HEX_DIGITS[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

}
