package com.baidu.android.pushservice;

import qsbk.app.Constants;

// compiled from: SourceFile
class g extends Thread {
    final /* synthetic */ CustomPushNotificationBuilder a;
    private final /* synthetic */ int b;

    g(CustomPushNotificationBuilder r1_CustomPushNotificationBuilder, int r2i) {
        this.a = r1_CustomPushNotificationBuilder;
        this.b = r2i;
    }

    public void run() {
        while (this.a.a == null && !this.a.b) {
            this.a.a((int)Constants.CommentCount);
        }
        if (this.a.b) {
        } else {
            this.a.a.setNotificationFlags(this.b);
        }
    }
}