package com.baidu.frontia;

import android.content.Context;
import android.util.Log;
import com.baidu.frontia.api.FrontiaDeepLink;
import com.baidu.frontia.api.FrontiaPush;
import com.baidu.frontia.base.impl.FrontiaImpl;

public class Frontia {
    private static FrontiaImpl a;

    public static String getApiKey() {
        return a.getAppKey();
    }

    public static FrontiaDeepLink getDeepLink() {
        FrontiaDeepLink r0_FrontiaDeepLink = FrontiaDeepLink.newInstance(a.getAppContext());
        r0_FrontiaDeepLink.init(a.getAppKey());
        return r0_FrontiaDeepLink;
    }

    public static String getFrontiaVersion() {
        return "1";
    }

    public static FrontiaPush getPush() {
        FrontiaPush r0_FrontiaPush = FrontiaPush.newInstance(a.getAppContext());
        r0_FrontiaPush.init(a.getAppKey());
        return r0_FrontiaPush;
    }

    public static boolean init(Context r2_Context, String r3_String) {
        if (r2_Context == null || r3_String == null) {
            return false;
        }
        a = FrontiaImpl.get();
        if (a == null) {
            return false;
        }
        a.setAppContext(r2_Context.getApplicationContext());
        a.setAppKey(r3_String);
        a.start();
        Log.d("frontia", "frontia init");
        a.a(r2_Context, r3_String);
        return true;
    }

    public static void setSlientUpdateEnabled(boolean r1z) {
        a.setCheckForUpdatesEnabled(r1z);
    }
}