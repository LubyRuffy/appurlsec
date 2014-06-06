package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ao implements Runnable {
    private final /* synthetic */ Context a;

    ao(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        PushManager.disableLbs(this.a);
    }
}