package com.tencent.cloudsdk.tsocket;

import android.content.Context;
import com.tencent.cloudsdk.a;
import com.tencent.cloudsdk.c;
import com.tencent.cloudsdk.common.record.info.Global;
import com.tencent.cloudsdk.d;

// compiled from: SourceFile
public class GlobalContext {
    private static Context a;
    private static final byte[] b;

    static {
        b = new byte[0];
    }

    public static Context getContext() {
        return a;
    }

    public static void initialize(Context r2_Context) {
        Context r0_Context = r2_Context.getApplicationContext();
        if (a == r0_Context) {
        } else {
            a = r0_Context;
            Global.init(r2_Context);
        }
    }

    public static void setContext(Context r5_Context) {
        synchronized (b) {
            initialize(r5_Context);
            c r0_c = c.a();
            a[] r2_aA = new a[1];
            r2_aA[0] = a.a;
            r0_c.a(r2_aA);
            setPluginContext(r5_Context);
        }
    }

    public static void setPluginContext(Context r6_Context) {
        Class[] r4_ClassA = new Class[1];
        r4_ClassA[0] = Context.class;
        Object[] r5_ObjectA = new Object[1];
        r5_ObjectA[0] = r6_Context;
        d.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.tsocket.GlobalContext", "com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext", "setContext", r4_ClassA, r5_ObjectA);
    }
}