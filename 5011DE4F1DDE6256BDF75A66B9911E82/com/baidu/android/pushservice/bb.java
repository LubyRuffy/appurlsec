package com.baidu.android.pushservice;

import android.content.Context;

// compiled from: SourceFile
class bb extends Thread {
    final /* synthetic */ PushNotificationBuilder a;
    private final /* synthetic */ Context b;

    bb(PushNotificationBuilder r1_PushNotificationBuilder, Context r2_Context) {
        this.a = r1_PushNotificationBuilder;
        this.b = r2_Context;
    }

    public void run() {
        this.a.b = !LoadExecutor.loadPush(this.b);
    }
}