package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ar implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ int c;

    ar(Context r1_Context, String r2_String, int r3i) {
        this.a = r1_Context;
        this.b = r2_String;
        this.c = r3i;
    }

    public void run() {
        PushManager.sdkStartWork(this.a, this.b, this.c);
    }
}