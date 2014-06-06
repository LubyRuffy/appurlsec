package qsbk.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import qsbk.app.utils.SharePreferenceUtils;

public class ConfigService extends Service {
    private static String a;
    private static String b;
    private Handler c;

    static {
        a = "lastVerifyConfigTime";
        b = "config_interval";
    }

    public ConfigService() {
        this.c = new a(this);
    }

    private void c() {
        new b(this, "qbk-ConfigSrv").start();
    }

    public IBinder onBind(Intent r2_Intent) {
        return null;
    }

    public void onStart(Intent r9_Intent, int r10i) {
        super.onStart(r9_Intent, r10i);
        String r0_String = SharePreferenceUtils.getSharePreferencesValue(a);
        String r1_String = SharePreferenceUtils.getSharePreferencesValue(b);
        if (TextUtils.isEmpty(r0_String) || TextUtils.isEmpty(r1_String)) {
            c();
        } else {
            long r2j = Long.valueOf(r0_String).longValue();
            if (System.currentTimeMillis() / 1000 - r2j > Long.valueOf(r1_String).longValue()) {
                c();
            } else {
                stopSelf();
            }
        }
    }
}