package com.tencent.mm.sdk.openapi;

import android.os.Bundle;

public final class WXAppLaunchData {
    public static final String ACTION_HANDLE_WXAPPLAUNCH = ".ACTION_HANDLE_WXAPPLAUNCH";
    public static final String ACTION_HANDLE_WXAPP_RESULT = ".ACTION_HANDLE_WXAPP_RESULT";
    public static final String ACTION_HANDLE_WXAPP_SHOW = ".ACTION_HANDLE_WXAPP_SHOW";
    public int launchType;
    public String message;

    public static class Builder {
        public static WXAppLaunchData fromBundle(Bundle r2_Bundle) {
            WXAppLaunchData r0_WXAppLaunchData = new WXAppLaunchData();
            r0_WXAppLaunchData.launchType = r2_Bundle.getInt("_wxapplaunchdata_launchType");
            r0_WXAppLaunchData.message = r2_Bundle.getString("_wxapplaunchdata_message");
            return r0_WXAppLaunchData;
        }

        public static Bundle toBundle(WXAppLaunchData r3_WXAppLaunchData) {
            Bundle r0_Bundle = new Bundle();
            r0_Bundle.putInt("_wxapplaunchdata_launchType", r3_WXAppLaunchData.launchType);
            r0_Bundle.putString("_wxapplaunchdata_message", r3_WXAppLaunchData.message);
            return r0_Bundle;
        }
    }
}