package com.baidu.android.pushservice;

import android.app.Notification;
import android.content.Context;

// compiled from: SourceFile
public class BasicPushNotificationBuilder {
    private com.baidu.android.pushservice.apiproxy.BasicPushNotificationBuilder a;

    public BasicPushNotificationBuilder(Context r2_Context) {
        new a(this, r2_Context).start();
    }

    public BasicPushNotificationBuilder(Context r1_Context, com.baidu.android.pushservice.apiproxy.BasicPushNotificationBuilder r2_com_baidu_android_pushservice_apiproxy_BasicPushNotificationBuilder) {
        this.a = r2_com_baidu_android_pushservice_apiproxy_BasicPushNotificationBuilder;
    }

    public Notification construct(Context r2_Context) {
        return LoadExecutor.loadPush(r2_Context) ? this.a.construct(r2_Context) : null;
    }

    public com.baidu.android.pushservice.apiproxy.BasicPushNotificationBuilder getInner() {
        return this.a;
    }
}