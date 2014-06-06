package com.qq.e.v2.plugininterfaces;

import android.app.Activity;

public interface ActivityDelegateFactory {
    public static final String NAME_BANNER_POPUP = "bannerPopupActivity";

    public ActivityDelegate getDelegate(String r1_String, Activity r2_Activity);
}