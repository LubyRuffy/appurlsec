package com.tencent.mm.sdk.plugin;

import android.content.Context;

public class MMPluginUtil {
    public static IMMPluginAPI queryPluginMgr(Context r1_Context) {
        return new MMPluginAPIImpl(r1_Context);
    }
}