package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ai implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ int b;
    private final /* synthetic */ PushNotificationBuilder c;

    ai(Context r1_Context, int r2i, PushNotificationBuilder r3_PushNotificationBuilder) {
        this.a = r1_Context;
        this.b = r2i;
        this.c = r3_PushNotificationBuilder;
    }

    public void run() {
        PushManager.setNotificationBuilder(this.a, this.b, this.c.getInner());
    }
}