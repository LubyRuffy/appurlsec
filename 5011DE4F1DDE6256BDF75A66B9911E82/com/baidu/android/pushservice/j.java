package com.baidu.android.pushservice;

import qsbk.app.Constants;

// compiled from: SourceFile
class j extends Thread {
    final /* synthetic */ CustomPushNotificationBuilder a;
    private final /* synthetic */ long[] b;

    j(CustomPushNotificationBuilder r1_CustomPushNotificationBuilder, long[] r2_longA) {
        this.a = r1_CustomPushNotificationBuilder;
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