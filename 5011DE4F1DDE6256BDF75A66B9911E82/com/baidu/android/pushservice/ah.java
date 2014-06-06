package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ah implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ PushNotificationBuilder b;

    ah(Context r1_Context, PushNotificationBuilder r2_PushNotificationBuilder) {
        this.a = r1_Context;
        this.b = r2_PushNotificationBuilder;
    }

    public void run() {
        PushManager.setDefaultNotificationBuilder(this.a, this.b.getInner());
    }
}