package com.baidu.android.pushservice;

import android.content.Context;
import android.util.Log;
import com.baidu.android.silentupdate.SilentManager;

// compiled from: SourceFile
public class LoadExecutor {
    public static void excuteMethod(Runnable r2_Runnable, Context r3_Context) {
        Log.d("YYY", "excuteMethod 111");
        if (isPushLibLoaded(r3_Context)) {
            Log.d("YYY", "excuteMethod 222");
            r2_Runnable.run();
        } else {
            new p(r3_Context, r2_Runnable).start();
        }
    }

    public static boolean isPushLibLoaded(Context r3_Context) {
        try {
            r3_Context.getClassLoader().loadClass("com.baidu.android.pushservice.internal.PushManager");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static synchronized boolean loadPush(Context r4_Context) {
        boolean r0z;
        synchronized (LoadExecutor.class) {
            try {
                Log.d("YYY", "loadPush 111");
                r4_Context.getClassLoader().loadClass("com.baidu.android.pushservice.internal.PushManager");
                Log.d("YYY", "loadPush 222");
                r0z = true;
            } catch (ClassNotFoundException e) {
                SilentManager.setKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYAFbG0oYmKgh6o7BhZIHf1njBpZXqyWBnYz2ip3Wp+s97OeA/pTe8xebuGJHwq4xbsGQrJWepIbUVrdjm6JRmdvuJhar7/hC/UNnUkJgYdYl10OZKlvcFFgK3V7XGBPplXldDnhbgscna3JG8U3025WSxZCP5vy/8cfxsEoVx5QIDAQAB");
                Log.d("YYY", "loadPush 333");
                r0z = SilentManager.loadLib(r4_Context.getApplicationContext(), "frontia_plugin", "plugin-deploy.jar");
            }
        }
        return r0z;
    }
}