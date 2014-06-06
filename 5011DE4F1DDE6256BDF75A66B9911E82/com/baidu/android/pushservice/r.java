package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class r implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ int b;
    private final /* synthetic */ int c;

    r(Context r1_Context, int r2i, int r3i) {
        this.a = r1_Context;
        this.b = r2i;
        this.c = r3i;
    }

    public void run() {
        PushManager.fetchMessages(this.a, this.b, this.c);
    }
}