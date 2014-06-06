package com.tencent.mm.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.mm.sdk.plugin.IMMPluginAPI;
import com.tencent.mm.sdk.plugin.MMPluginUtil;

public final class MMAppMgr {
    private MMAppMgr() {
    }

    public static void activate(boolean r0z) {
    }

    public static IMMPluginAPI getPluginMgr(Context r1_Context) {
        return MMPluginUtil.queryPluginMgr(r1_Context);
    }

    public static SharedPreferences getSharedPreferences(Context r1_Context, int r2i) {
        return new MMSharedPreferences(r1_Context);
    }
}