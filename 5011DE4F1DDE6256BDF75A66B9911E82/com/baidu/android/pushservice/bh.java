package com.baidu.android.pushservice;

import android.net.Uri;
import qsbk.app.Constants;

// compiled from: SourceFile
class bh extends Thread {
    final /* synthetic */ PushNotificationBuilder a;
    private final /* synthetic */ Uri b;

    bh(PushNotificationBuilder r1_PushNotificationBuilder, Uri r2_Uri) {
        this.a = r1_PushNotificationBuilder;
        this.b = r2_Uri;
    }

    public void run() {
        while (this.a.a == null && !r1_PushNotificationBuilder.b) {
            this.a.a((int)Constants.CommentCount);
        }
        if (this.a.b) {
        } else {
            this.a.a.setNotificationSound(this.b);
        }
    }
}