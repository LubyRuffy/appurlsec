package com.baidu.android.pushservice.apiproxy;

import android.app.Notification;
import android.content.Context;
import android.net.Uri;

// compiled from: SourceFile
public abstract class PushNotificationBuilder {
    protected com.baidu.android.pushservice.internal.PushNotificationBuilder a;

    public PushNotificationBuilder() {
        this.a = new a(this);
    }

    public PushNotificationBuilder(com.baidu.android.pushservice.internal.PushNotificationBuilder r1_com_baidu_android_pushservice_internal_PushNotificationBuilder) {
        this.a = r1_com_baidu_android_pushservice_internal_PushNotificationBuilder;
    }

    public abstract Notification construct(Context r1_Context);

    public com.baidu.android.pushservice.internal.PushNotificationBuilder getInner() {
        return this.a;
    }

    public void setNotificationDefaults(int r2i) {
        this.a.setNotificationDefaults(r2i);
    }

    public void setNotificationFlags(int r2i) {
        this.a.setNotificationFlags(r2i);
    }

    public void setNotificationSound(Uri r2_Uri) {
        this.a.setNotificationSound(r2_Uri);
    }

    public void setNotificationText(String r2_String) {
        this.a.setNotificationText(r2_String);
    }

    public void setNotificationTitle(String r2_String) {
        this.a.setNotificationTitle(r2_String);
    }

    public void setNotificationVibrate(long[] r2_longA) {
        this.a.setNotificationVibrate(r2_longA);
    }

    public void setStatusbarIcon(int r2i) {
        this.a.setStatusbarIcon(r2i);
    }
}