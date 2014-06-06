package com.baidu.android.pushservice;

import com.baidu.android.pushservice.apiproxy.CustomPushNotificationBuilder;
import qsbk.app.Constants;

// compiled from: SourceFile
class c extends Thread {
    final /* synthetic */ CustomPushNotificationBuilder a;
    private final /* synthetic */ int b;

    c(CustomPushNotificationBuilder r1_CustomPushNotificationBuilder, int r2i) {
        this.a = r1_CustomPushNotificationBuilder;
        this.b = r2i;
    }

    public void run() {
        while (this.a.a == null && !this.a.b) {
            this.a.a((int)Constants.CommentCount);
        }
        if (this.a.b) {
        } else {
            ((CustomPushNotificationBuilder) this.a.a).setLayoutDrawable(this.b);
        }
    }
}