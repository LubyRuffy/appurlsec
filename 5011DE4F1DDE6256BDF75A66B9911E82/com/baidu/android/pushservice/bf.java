package com.baidu.android.pushservice;

import qsbk.app.Constants;

// compiled from: SourceFile
class bf extends Thread {
    final /* synthetic */ PushNotificationBuilder a;
    private final /* synthetic */ int b;

    bf(PushNotificationBuilder r1_PushNotificationBuilder, int r2i) {
        this.a = r1_PushNotificationBuilder;
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