package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushSettings;

// compiled from: SourceFile
class bj extends Thread {
    private final /* synthetic */ Context a;
    private final /* synthetic */ boolean b;

    bj(Context r1_Context, boolean r2z) {
        this.a = r1_Context;
        this.b = r2z;
    }

    public void run() {
        if (LoadExecutor.loadPush(this.a)) {
            PushSettings.enableDebugMode(this.a, this.b);
        }
    }
}