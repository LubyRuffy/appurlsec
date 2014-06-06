package com.baidu.android.pushservice;

import android.net.Uri;
import qsbk.app.Constants;

// compiled from: SourceFile
class i extends Thread {
    final /* synthetic */ CustomPushNotificationBuilder a;
    private final /* synthetic */ Uri b;

    i(CustomPushNotificationBuilder r1_CustomPushNotificationBuilder, Uri r2_Uri) {
        this.a = r1_CustomPushNotificationBuilder;
        this.b = r2_Uri;
    }

    public void run() {
        while (this.a.a == null && !this.a.b) {
            this.a.a((int)Constants.CommentCount);
        }
        if (this.a.b) {
        } else {
            this.a.a.setNotificationSound(this.b);
        }
    }
}