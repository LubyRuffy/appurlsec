package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ba implements Runnable {
    private final /* synthetic */ Context a;

    ba(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        PushManager.unbind(this.a);
    }
}