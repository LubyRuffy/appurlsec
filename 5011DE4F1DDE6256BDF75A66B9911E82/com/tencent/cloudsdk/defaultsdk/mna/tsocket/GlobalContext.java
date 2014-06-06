package com.tencent.cloudsdk.defaultsdk.mna.tsocket;

import android.content.Context;
import com.tencent.cloudsdk.common.record.info.Global;
import com.tencent.cloudsdk.cz;

// compiled from: SourceFile
public class GlobalContext {
    private static Context a;

    public static Context getContext() {
        return a;
    }

    public static void setContext(Context r2_Context) {
        Context r0_Context = r2_Context.getApplicationContext();
        if (a == r0_Context) {
        } else {
            a = r0_Context;
            Global.init(r2_Context);
            cz.a(r2_Context);
        }
    }
}