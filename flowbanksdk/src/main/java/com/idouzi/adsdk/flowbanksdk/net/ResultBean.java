package com.idouzi.adsdk.flowbanksdk.net;

/**
 * Created by liuxueping on 2018/4/12.
 */

public class ResultBean<T> {
    public final static String FAIL ="FAIL";
    public final static String SUCCESS ="SUCCESS";
    private String return_code;
    private T return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public T getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(T return_msg) {
        this.return_msg = return_msg;
    }
}
