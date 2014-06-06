package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class at implements Runnable {
    private final /* synthetic */ Context a;

    at(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        PushManager.resumeWork(this.a);
    }
}