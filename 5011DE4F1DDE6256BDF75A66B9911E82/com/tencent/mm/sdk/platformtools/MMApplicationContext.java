package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;

public final class MMApplicationContext {
    private static Context a;
    private static String b;

    static {
        a = null;
        b = PluginIntent.APP_PACKAGE_PATTERN;
    }

    private MMApplicationContext() {
    }

    public static Context getContext() {
        return a;
    }

    public static String getDefaultPreferencePath() {
        return b + "_preferences";
    }

    public static String getPackageName() {
        return b;
    }

    public static void setContext(Context r3_Context) {
        a = r3_Context;
        b = r3_Context.getPackageName();
        Log.d("MicroMsg.MMApplicationContext", new StringBuilder("setup application context for package: ").append(b).toString());
    }
}