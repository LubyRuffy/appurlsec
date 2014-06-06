package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import com.flurry.android.FlurryAgent.FlurryDefaultExceptionHandler;
import com.google.analytics.tracking.android.ModelFields;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.utils.Base64;

// compiled from: SourceFile
public final class InstallReceiver extends BroadcastReceiver {
    private final Handler a;
    private File b;

    private InstallReceiver() {
        this.b = null;
        HandlerThread r0_HandlerThread = new HandlerThread("InstallReceiver");
        r0_HandlerThread.start();
        this.a = new Handler(r0_HandlerThread.getLooper());
    }

    private static Map a(String r8_String) {
        if (r8_String == null || r8_String.trim().equals(RContactStorage.PRIMARY_KEY)) {
            throw new IllegalArgumentException("Referrer is null or empty");
        } else {
            Map r2_Map = new HashMap();
            String[] r3_StringA = r8_String.split("&");
            int r4i = r3_StringA.length;
            int r0i = 0;
            while (r0i < r4i) {
                String[] r5_StringA = r3_StringA[r0i].split("=");
                if (r5_StringA.length != 2) {
                    i.a("InstallReceiver", "Invalid referrer Element: " + r3_StringA[r0i] + " in referrer tag " + r8_String);
                } else {
                    r2_Map.put(r5_StringA[0], r5_StringA[1]);
                }
                r0i++;
            }
            StringBuilder r0_StringBuilder = new StringBuilder();
            if (r2_Map.get("utm_source") == null) {
                r0_StringBuilder.append("Campaign Source is missing.\n");
            }
            if (r2_Map.get("utm_medium") == null) {
                r0_StringBuilder.append("Campaign Medium is missing.\n");
            }
            if (r2_Map.get("utm_campaign") == null) {
                r0_StringBuilder.append("Campaign Name is missing.\n");
            }
            if (r0_StringBuilder.length() <= 0) {
                return r2_Map;
            }
            throw new IllegalArgumentException(r0_StringBuilder.toString());
        }
    }

    private synchronized void a(Map r3_Map) {
        this.a.post(new af(this, r3_Map));
    }

    public final void onReceive(Context r5_Context, Intent r6_Intent) {
        this.b = r5_Context.getFileStreamPath(".flurryinstallreceiver." + Integer.toString(FlurryAgent.f().hashCode(), Base64.URL_SAFE));
        if (FlurryAgent.b()) {
            Thread.setDefaultUncaughtExceptionHandler(new FlurryDefaultExceptionHandler());
        }
        String r0_String = r6_Intent.getStringExtra(ModelFields.REFERRER);
        if (r0_String == null || (!"com.android.vending.INSTALL_REFERRER".equals(r6_Intent.getAction()))) {
        } else {
            try {
                a(a(r0_String));
            } catch (IllegalArgumentException e) {
                i.c("InstallReceiver", "Invalid referrer Tag: " + e.getMessage());
            }
        }
    }
}