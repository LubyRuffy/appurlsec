package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.CustomPushNotificationBuilder;

// compiled from: SourceFile
class b extends Thread {
    final /* synthetic */ CustomPushNotificationBuilder a;
    private final /* synthetic */ Context b;
    private final /* synthetic */ int c;
    private final /* synthetic */ int d;
    private final /* synthetic */ int e;
    private final /* synthetic */ int f;

    b(CustomPushNotificationBuilder r1_CustomPushNotificationBuilder, Context r2_Context, int r3i, int r4i, int r5i, int r6i) {
        this.a = r1_CustomPushNotificationBuilder;
        this.b = r2_Context;
        this.c = r3i;
        this.d = r4i;
        this.e = r5i;
        this.f = r6i;
    }

    public void run() {
        if (LoadExecutor.loadPush(this.b)) {
            this.a.a = new CustomPushNotificationBuilder(this.c, this.d, this.e, this.f);
        } else {
            this.a.b = true;
        }
    }
}