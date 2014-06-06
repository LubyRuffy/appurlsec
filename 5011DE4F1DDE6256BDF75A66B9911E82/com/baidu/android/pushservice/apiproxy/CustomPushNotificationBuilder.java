package com.baidu.android.pushservice.apiproxy;

import android.app.Notification;
import android.content.Context;
import android.net.Uri;

// compiled from: SourceFile
public class CustomPushNotificationBuilder extends PushNotificationBuilder {
    public CustomPushNotificationBuilder(int r2i, int r3i, int r4i, int r5i) {
        this.a = new com.baidu.android.pushservice.internal.CustomPushNotificationBuilder(r2i, r3i, r4i, r5i);
    }

    public CustomPushNotificationBuilder(com.baidu.android.pushservice.internal.CustomPushNotificationBuilder r1_com_baidu_android_pushservice_internal_CustomPushNotificationBuilder) {
        this.a = r1_com_baidu_android_pushservice_internal_CustomPushNotificationBuilder;
    }

    public Notification construct(Context r2_Context) {
        return this.a.construct(r2_Context);
    }

    public com.baidu.android.pushservice.internal.CustomPushNotificationBuilder getInner() {
        return (com.baidu.android.pushservice.internal.CustomPushNotificationBuilder) this.a;
    }

    public void setLayoutDrawable(int r2i) {
        ((com.baidu.android.pushservice.internal.CustomPushNotificationBuilder) this.a).setLayoutDrawable(r2i);
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