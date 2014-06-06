package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class az implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ int b;
    private final /* synthetic */ String c;
    private final /* synthetic */ int d;

    az(Context r1_Context, int r2i, String r3_String, int r4i) {
        this.a = r1_Context;
        this.b = r2i;
        this.c = r3_String;
        this.d = r4i;
    }

    public void run() {
        PushManager.sdkBind(this.a, this.b, this.c, this.d);
    }
}