package com.qq.e.v2.managers;

import android.content.Context;
import android.os.Build.VERSION;
import com.qq.e.v2.managers.plugin.PM;
import com.qq.e.v2.managers.setting.SM;
import com.qq.e.v2.managers.status.APPStatus;
import com.qq.e.v2.managers.status.DeviceStatus;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;
import qsbk.app.share.ShareUtils;

public class GDTADManager {
    private volatile Boolean a;
    private volatile Context b;
    private volatile SM c;
    private volatile PM d;
    private volatile APPStatus e;
    private volatile DeviceStatus f;

    static final class a {
        private static GDTADManager a;

        static {
            a = new GDTADManager();
        }
    }

    private GDTADManager() {
        this.a = Boolean.valueOf(false);
    }

    public static GDTADManager getInstance() {
        return a.a;
    }

    public Context getAppContext() {
        return this.b;
    }

    public APPStatus getAppStatus() {
        return this.e;
    }

    public DeviceStatus getDeviceStatus() {
        return this.f;
    }

    public PM getPM() {
        return this.d;
    }

    public SM getSM() {
        return this.c;
    }

    public synchronized boolean initWith(Context r10_Context, String r11_String) {
        boolean r0z = true;
        synchronized (this) {
            if (this.a.booleanValue()) {
            } else {
                if (r10_Context != null) {
                    if (StringUtil.isEmpty(r11_String)) {
                        GDTLogger.e("should not initialize admanager with null activity or empty appId");
                        r0z = false;
                    } else {
                        try {
                            long r2j = System.currentTimeMillis();
                            long r4j = System.currentTimeMillis();
                            GDTLogger.d(new StringBuilder("TimeStampBeforeInitAdManager:").append(System.currentTimeMillis()).toString());
                            this.b = r10_Context.getApplicationContext();
                            GDTLogger.d(new StringBuilder("TimeStamp_before_init_SM:").append(System.currentTimeMillis()).toString());
                            this.c = new SM(this.b);
                            GDTLogger.d(new StringBuilder("TimeStamp_after_init_SM:").append(System.currentTimeMillis()).toString());
                            this.d = new PM(this.b);
                            GDTLogger.d(new StringBuilder("TimeStamp_after_init_PM:").append(System.currentTimeMillis()).toString());
                            this.e = new APPStatus(r11_String, this.b);
                            GDTLogger.d(new StringBuilder("TimeStamp_after_init_APPStatus:").append(System.currentTimeMillis()).toString());
                            this.f = new DeviceStatus(r10_Context);
                            GDTLogger.d(new StringBuilder("TimeStamp_before_send_active").append(System.currentTimeMillis()).toString());
                            if (VERSION.SDK_INT > ShareUtils.SHARE_COLLECT) {
                                com.qq.e.v2.a.a.a().a(this.b, r4j);
                            }
                            GDTLogger.d(new StringBuilder("TimeStamp_after_send_active:").append(System.currentTimeMillis()).toString());
                            GDTLogger.d(new StringBuilder("Total init Time=").append(System.currentTimeMillis() - r2j).toString());
                            this.a = Boolean.valueOf(true);
                        } catch (Throwable th) {
                            GDTLogger.report("ADManager init error", th);
                            r0z = false;
                        }
                    }
                }
                GDTLogger.e("should not initialize admanager with null activity or empty appId");
                r0z = false;
            }
        }
        return r0z;
    }
}