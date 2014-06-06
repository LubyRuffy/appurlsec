package com.baidu.android.pushservice;

import qsbk.app.Constants;

// compiled from: SourceFile
class bd extends Thread {
    final /* synthetic */ PushNotificationBuilder a;
    private final /* synthetic */ String b;

    bd(PushNotificationBuilder r1_PushNotificationBuilder, String r2_String) {
        this.a = r1_PushNotificationBuilder;
        this.b = r2_String;
    }

    public void run() {
        while (this.a.a == null && !this.a.b) {
            this.a.a((int)Constants.CommentCount);
        }
        if (this.a.b) {
        } else {
            this.a.a.setNotificationTitle(this.b);
        }
    }
}