package com.baidu.android.pushservice;

import android.app.Notification;
import android.content.Context;
import android.net.Uri;

// compiled from: SourceFile
public abstract class PushNotificationBuilder {
    protected com.baidu.android.pushservice.apiproxy.PushNotificationBuilder a;
    private boolean b;

    public PushNotificationBuilder(Context r2_Context) {
        this.b = false;
        this.a = null;
        new bb(this, r2_Context).start();
    }

    public PushNotificationBuilder(Context r2_Context, com.baidu.android.pushservice.apiproxy.PushNotificationBuilder r3_com_baidu_android_pushservice_apiproxy_PushNotificationBuilder) {
        this.b = false;
        this.a = null;
        this.a = r3_com_baidu_android_pushservice_apiproxy_PushNotificationBuilder;
    }

    private void a(int r3i) {
        try {
            Thread.sleep((long) r3i);
        } catch (Exception e) {
        }
    }

    public abstract Notification construct(Context r1_Context);

    public com.baidu.android.pushservice.apiproxy.PushNotificationBuilder getInner() {
        return this.a;
    }

    public void setNotificationDefaults(int r2i) {
        new bg(this, r2i).start();
    }

    public void setNotificationFlags(int r2i) {
        new bf(this, r2i).start();
    }

    public void setNotificationSound(Uri r2_Uri) {
        new bh(this, r2_Uri).start();
    }

    public void setNotificationText(String r2_String) {
        new be(this, r2_String).start();
    }

    public void setNotificationTitle(String r2_String) {
        new bd(this, r2_String).start();
    }

    public void setNotificationVibrate(long[] r2_longA) {
        new bi(this, r2_longA).start();
    }

    public void setStatusbarIcon(int r2i) {
        new bc(this, r2i).start();
    }
}