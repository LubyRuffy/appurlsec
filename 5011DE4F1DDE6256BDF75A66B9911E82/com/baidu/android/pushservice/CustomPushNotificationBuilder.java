package com.baidu.android.pushservice;

import android.app.Notification;
import android.content.Context;
import android.net.Uri;
import qsbk.app.Constants;

// compiled from: SourceFile
public class CustomPushNotificationBuilder extends PushNotificationBuilder {
    private boolean b;

    public CustomPushNotificationBuilder(Context r8_Context, int r9i, int r10i, int r11i, int r12i) {
        super(r8_Context);
        this.b = false;
        if (LoadExecutor.isPushLibLoaded(r8_Context)) {
            this.a = new com.baidu.android.pushservice.apiproxy.CustomPushNotificationBuilder(r9i, r10i, r11i, r12i);
        } else {
            new b(this, r8_Context, r9i, r10i, r11i, r12i).start();
        }
    }

    public CustomPushNotificationBuilder(Context r2_Context, com.baidu.android.pushservice.apiproxy.CustomPushNotificationBuilder r3_com_baidu_android_pushservice_apiproxy_CustomPushNotificationBuilder) {
        super(r2_Context);
        this.b = false;
        this.a = r3_com_baidu_android_pushservice_apiproxy_CustomPushNotificationBuilder;
    }

    private void a(int r3i) {
        try {
            Thread.sleep((long) r3i);
        } catch (Exception e) {
        }
    }

    public Notification construct(Context r2_Context) {
        return LoadExecutor.loadPush(r2_Context) ? this.a.construct(r2_Context) : null;
    }

    public com.baidu.android.pushservice.apiproxy.CustomPushNotificationBuilder getInner() {
        while (this.a == null && !(this.b)) {
            a((int)Constants.CommentCount);
        }
        return this.b ? null : (com.baidu.android.pushservice.apiproxy.CustomPushNotificationBuilder) this.a;
    }

    public void setLayoutDrawable(int r2i) {
        if (this.a != null) {
            ((com.baidu.android.pushservice.apiproxy.CustomPushNotificationBuilder) this.a).setLayoutDrawable(r2i);
        } else {
            new c(this, r2i).start();
        }
    }

    public void setNotificationDefaults(int r2i) {
        if (this.a != null) {
            this.a.setNotificationDefaults(r2i);
        } else {
            new h(this, r2i).start();
        }
    }

    public void setNotificationFlags(int r2i) {
        if (this.a != null) {
            this.a.setNotificationFlags(r2i);
        } else {
            new g(this, r2i).start();
        }
    }

    public void setNotificationSound(Uri r2_Uri) {
        if (this.a != null) {
            this.a.setNotificationSound(r2_Uri);
        } else {
            new i(this, r2_Uri).start();
        }
    }

    public void setNotificationText(String r2_String) {
        if (this.a != null) {
            this.a.setNotificationText(r2_String);
        } else {
            new f(this, r2_String).start();
        }
    }

    public void setNotificationTitle(String r2_String) {
        if (this.a != null) {
            this.a.setNotificationTitle(r2_String);
        } else {
            new e(this, r2_String).start();
        }
    }

    public void setNotificationVibrate(long[] r2_longA) {
        if (this.a != null) {
            this.a.setNotificationVibrate(r2_longA);
        } else {
            new j(this, r2_longA).start();
        }
    }

    public void setStatusbarIcon(int r2i) {
        if (this.a != null) {
            this.a.setStatusbarIcon(r2i);
        } else {
            new d(this, r2i).start();
        }
    }
}