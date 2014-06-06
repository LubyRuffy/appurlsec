package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.BasicPushNotificationBuilder;

// compiled from: SourceFile
class a extends Thread {
    final /* synthetic */ BasicPushNotificationBuilder a;
    private final /* synthetic */ Context b;

    a(BasicPushNotificationBuilder r1_BasicPushNotificationBuilder, Context r2_Context) {
        this.a = r1_BasicPushNotificationBuilder;
        this.b = r2_Context;
    }

    public void run() {
        if (LoadExecutor.loadPush(this.b)) {
            this.a.a = new BasicPushNotificationBuilder();
        }
    }
}