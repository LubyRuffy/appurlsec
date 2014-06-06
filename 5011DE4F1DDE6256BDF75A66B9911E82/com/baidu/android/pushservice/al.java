package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class al implements Runnable {
    private final /* synthetic */ Context a;

    al(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        PushManager.tryConnect(this.a);
    }
}