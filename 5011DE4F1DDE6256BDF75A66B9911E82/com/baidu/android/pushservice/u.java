package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class u implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String[] b;

    u(Context r1_Context, String[] r2_StringA) {
        this.a = r1_Context;
        this.b = r2_StringA;
    }

    public void run() {
        PushManager.deleteMessages(this.a, this.b);
    }
}