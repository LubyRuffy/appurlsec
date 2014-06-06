package com.baidu.android.pushservice.apiproxy;

import android.app.Notification;
import android.content.Context;

// compiled from: SourceFile
public class BasicPushNotificationBuilder {
    private com.baidu.android.pushservice.internal.BasicPushNotificationBuilder a;

    public BasicPushNotificationBuilder() {
        this.a = new com.baidu.android.pushservice.internal.BasicPushNotificationBuilder();
    }

    public BasicPushNotificationBuilder(com.baidu.android.pushservice.internal.BasicPushNotificationBuilder r1_com_baidu_android_pushservice_internal_BasicPushNotificationBuilder) {
        this.a = r1_com_baidu_android_pushservice_internal_BasicPushNotificationBuilder;
    }

    public Notification construct(Context r2_Context) {
        return this.a.construct(r2_Context);
    }

    public com.baidu.android.pushservice.internal.BasicPushNotificationBuilder getInner() {
        return this.a;
    }
}