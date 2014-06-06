package com.baidu.android.pushservice;

import android.content.Context;

// compiled from: SourceFile
class au extends Thread {
    private final /* synthetic */ Context a;

    au(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        LoadExecutor.loadPush(this.a);
    }
}