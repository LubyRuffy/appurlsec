package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.Internal;

// compiled from: SourceFile
class k implements Runnable {
    private final /* synthetic */ Context a;

    k(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        Internal.enablePushService(this.a);
    }
}