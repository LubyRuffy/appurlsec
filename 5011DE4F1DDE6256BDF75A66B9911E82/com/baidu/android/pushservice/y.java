package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class y implements Runnable {
    private final /* synthetic */ Context a;

    y(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        PushManager.listTags(this.a);
    }
}