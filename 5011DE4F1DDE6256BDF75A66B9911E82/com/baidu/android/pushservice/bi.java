package com.baidu.android.pushservice;

import qsbk.app.Constants;

// compiled from: SourceFile
class bi extends Thread {
    final /* synthetic */ PushNotificationBuilder a;
    private final /* synthetic */ long[] b;

    bi(PushNotificationBuilder r1_PushNotificationBuilder, long[] r2_longA) {
        this.a = r1_PushNotificationBuilder;
        this.b = r2_longA;
    }

    public void run() {
        while (this.a.a == null && !this.a.b) {
            this.a.a((int)Constants.CommentCount);
        }
        if (this.a.b) {
        } else {
            this.a.a.setNotificationVibrate(this.b);
        }
    }
}