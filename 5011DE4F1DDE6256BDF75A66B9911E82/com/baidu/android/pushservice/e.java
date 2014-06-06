package com.baidu.android.pushservice;

import qsbk.app.Constants;

// compiled from: SourceFile
class e extends Thread {
    final /* synthetic */ CustomPushNotificationBuilder a;
    private final /* synthetic */ String b;

    e(CustomPushNotificationBuilder r1_CustomPushNotificationBuilder, String r2_String) {
        this.a = r1_CustomPushNotificationBuilder;
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