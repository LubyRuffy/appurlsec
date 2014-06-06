package com.baidu.mobstat;

import android.content.Context;

public class BasicStoreTools extends BasicStoreToolsBase {
    public static final String APP_ANALYSIS_EXCEPTION = "exceptionanalysisflag";
    public static final String APP_ANALYSIS_EXCEPTION_TAG = "exceptionanalysistag";
    public static final String APP_SET_APPKEY = "mjsetappkey";
    public static final String APP_SET_CHANNEL = "setchannelwithcodevalue";
    public static final String APP_SET_CHANNEL_WITH_CODE = "setchannelwithcode";
    public static final String DEVICE_CUID = "cuid";
    public static final String DEVICE_ID = "device_id";
    public static final String LAST_SEND_TIME = "lastsendtime";
    public static final String ONLY_WIFI = "onlywifi";
    public static final String SEND_LOG_TYPE = "sendLogtype";
    public static final String TIME_INTERVAL = "timeinterval";
    static BasicStoreTools a;

    static {
        a = new BasicStoreTools();
    }

    private BasicStoreTools() {
    }

    public static BasicStoreTools getInstance() {
        return a;
    }

    protected void a(Context r2_Context, int r3i) {
        putInt(r2_Context, SEND_LOG_TYPE, r3i);
    }

    protected void a(Context r2_Context, long r3j) {
        putLong(r2_Context, LAST_SEND_TIME, r3j);
    }

    protected void a(Context r2_Context, String r3_String) {
        putString(r2_Context, DEVICE_ID, r3_String);
    }

    protected void a(Context r2_Context, boolean r3z) {
        putBoolean(r2_Context, APP_ANALYSIS_EXCEPTION, r3z);
    }

    protected boolean a(Context r3_Context) {
        return getBoolean(r3_Context, APP_ANALYSIS_EXCEPTION, false);
    }

    protected int b(Context r3_Context) {
        return getInt(r3_Context, SEND_LOG_TYPE, 0);
    }

    protected void b(Context r2_Context, int r3i) {
        putInt(r2_Context, TIME_INTERVAL, r3i);
    }

    protected void b(Context r2_Context, String r3_String) {
        putString(r2_Context, DEVICE_CUID, r3_String);
    }

    protected void b(Context r2_Context, boolean r3z) {
        putBoolean(r2_Context, ONLY_WIFI, r3z);
    }

    protected int c(Context r3_Context) {
        return getInt(r3_Context, TIME_INTERVAL, 1);
    }

    protected void c(Context r2_Context, String r3_String) {
        putString(r2_Context, APP_SET_CHANNEL, r3_String);
    }

    protected void c(Context r2_Context, boolean r3z) {
        putBoolean(r2_Context, APP_SET_CHANNEL_WITH_CODE, r3z);
    }

    protected boolean d(Context r3_Context) {
        return getBoolean(r3_Context, ONLY_WIFI, false);
    }

    protected String e(Context r3_Context) {
        return getString(r3_Context, DEVICE_ID, null);
    }

    protected String f(Context r3_Context) {
        return getString(r3_Context, DEVICE_CUID, null);
    }

    protected String g(Context r3_Context) {
        return getString(r3_Context, APP_SET_CHANNEL, null);
    }

    protected boolean h(Context r3_Context) {
        return getBoolean(r3_Context, APP_SET_CHANNEL_WITH_CODE, false);
    }
}