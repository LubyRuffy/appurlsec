package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ay implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ int b;

    ay(Context r1_Context, int r2i) {
        this.a = r1_Context;
        this.b = r2i;
    }

    public void run() {
        PushManager.bind(this.a, this.b);
    }
}