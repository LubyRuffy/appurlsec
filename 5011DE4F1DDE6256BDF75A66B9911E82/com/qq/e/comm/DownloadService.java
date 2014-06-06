package com.qq.e.comm;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.plugininterfaces.ServiceDelegate;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;
import qsbk.app.widget.listview.XListViewHeader;

public class DownloadService extends Service {
    public static final String V2 = "2";
    public static final String VERSION_KEY = "downloadservice.req.version";
    private ServiceDelegate a;

    private boolean a(String r3_String) {
        if (this.a == null) {
            try {
                if (GDTADManager.getInstance().initWith(getApplicationContext(), r3_String)) {
                    this.a = GDTADManager.getInstance().getPM().getServiceDelegateFactory().getAPKDownloadServiceDelegate(this);
                    this.a.onCreate();
                } else {
                    GDTLogger.report("Init GDTADManager fail in DownloadService.oncreate");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return this.a != null;
    }

    public IBinder onBind(Intent r2_Intent) {
        return this.a != null ? this.a.onBind(r2_Intent) : null;
    }

    public void onConfigurationChanged(Configuration r2_Configuration) {
        if (this.a != null) {
            this.a.onConfigurationChanged(r2_Configuration);
        }
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        if (this.a != null) {
            this.a.onDestroy();
        }
    }

    public void onLowMemory() {
        if (this.a != null) {
            this.a.onLowMemory();
        }
    }

    public void onRebind(Intent r2_Intent) {
        if (this.a != null) {
            this.a.onRebind(r2_Intent);
        }
    }

    public int onStartCommand(Intent r5_Intent, int r6i, int r7i) {
        if (r5_Intent == null) {
            stopSelf(r7i);
            return XListViewHeader.STATE_REFRESHING;
        } else {
            String r1_String = r5_Intent.getStringExtra(VERSION_KEY);
            GDTLogger.d(new StringBuilder("Download Service with Request version:").append(r1_String).toString());
            if (V2.equals(r1_String)) {
                r1_String = r5_Intent.getStringExtra("GDT_APPID");
                if (!StringUtil.isEmpty(r1_String) && a(r1_String)) {
                    return this.a.onStartCommand(r5_Intent, r6i, r7i);
                }
                GDTLogger.w("Failto Start new download Service");
                return XListViewHeader.STATE_REFRESHING;
            } else {
                GDTLogger.report("While this app is invoking old download service");
                stopSelf(r7i);
                return XListViewHeader.STATE_REFRESHING;
            }
        }
    }

    public void onTaskRemoved(Intent r2_Intent) {
        if (this.a != null) {
            this.a.onTaskRemoved(r2_Intent);
        }
    }

    public void onTrimMemory(int r2i) {
        if (this.a != null) {
            this.a.onTrimMemory(r2i);
        }
    }

    public boolean onUnbind(Intent r2_Intent) {
        return this.a != null ? this.a.onUnbind(r2_Intent) : super.onUnbind(r2_Intent);
    }
}