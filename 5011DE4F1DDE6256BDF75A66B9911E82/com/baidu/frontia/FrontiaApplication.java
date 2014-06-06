package com.baidu.frontia;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Process;
import com.baidu.android.silentupdate.SilentManager;
import java.util.Iterator;
import qsbk.app.widget.listview.XListViewFooter;

public class FrontiaApplication extends Application {
    private static boolean a(Context r3_Context) {
        SilentManager.setKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYAFbG0oYmKgh6o7BhZIHf1njBpZXqyWBnYz2ip3Wp+s97OeA/pTe8xebuGJHwq4xbsGQrJWepIbUVrdjm6JRmdvuJhar7/hC/UNnUkJgYdYl10OZKlvcFFgK3V7XGBPplXldDnhbgscna3JG8U3025WSxZCP5vy/8cfxsEoVx5QIDAQAB");
        SilentManager.enableRSA(false);
        return SilentManager.loadLib(r3_Context.getApplicationContext(), "frontia_plugin", "plugin-deploy.jar");
    }

    private static String b(Context r5_Context) {
        ServiceInfo[] r2_ServiceInfoA;
        try {
            r2_ServiceInfoA = r5_Context.getPackageManager().getPackageInfo(r5_Context.getPackageName(), XListViewFooter.STATE_NODATA).services;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            r2_ServiceInfoA = null;
        }
        if (r2_ServiceInfoA == null) {
            return null;
        }
        int r0i = 0;
        while (r0i < r2_ServiceInfoA.length) {
            if ("com.baidu.android.pushservice.PushService".equals(r2_ServiceInfoA[r0i].name)) {
                return r2_ServiceInfoA[r0i].processName;
            }
            r0i++;
        }
        return null;
    }

    public static void initFrontiaApplication(Context r4_Context) {
        int r1i = Process.myPid();
        Iterator r2_Iterator = ((ActivityManager) r4_Context.getSystemService("activity")).getRunningAppProcesses().iterator();
        while (r2_Iterator.hasNext()) {
            RunningAppProcessInfo r0_RunningAppProcessInfo = (RunningAppProcessInfo) r2_Iterator.next();
            if (r0_RunningAppProcessInfo.pid == r1i) {
                if ((!r0_RunningAppProcessInfo.processName.equalsIgnoreCase(b(r4_Context))) || a(r4_Context.getApplicationContext())) {
                } else {
                    Process.killProcess(r1i);
                }
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        initFrontiaApplication(getApplicationContext());
    }
}