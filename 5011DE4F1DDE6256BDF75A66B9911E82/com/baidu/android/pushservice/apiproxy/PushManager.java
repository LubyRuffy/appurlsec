package com.baidu.android.pushservice.apiproxy;

import android.app.Activity;
import android.content.Context;
import java.util.List;

// compiled from: SourceFile
public class PushManager {
    public static void activityStarted(Activity r0_Activity) {
        com.baidu.android.pushservice.internal.PushManager.activityStarted(r0_Activity);
    }

    public static void activityStoped(Activity r0_Activity) {
        com.baidu.android.pushservice.internal.PushManager.activityStoped(r0_Activity);
    }

    public static void bind(Context r0_Context, int r1i) {
        com.baidu.android.pushservice.internal.PushManager.bind(r0_Context, r1i);
    }

    public static void bindGroup(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.bindGroup(r0_Context, r1_String);
    }

    public static void delTags(Context r0_Context, List<String> r1_List_String) {
        com.baidu.android.pushservice.internal.PushManager.delTags(r0_Context, r1_List_String);
    }

    public static void deleteMessages(Context r0_Context, String[] r1_StringA) {
        com.baidu.android.pushservice.internal.PushManager.deleteMessages(r0_Context, r1_StringA);
    }

    public static void disableLbs(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.disableLbs(r0_Context);
    }

    public static void enableLbs(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.enableLbs(r0_Context);
    }

    public static void fetchGroupMessages(Context r0_Context, String r1_String, int r2i, int r3i) {
        com.baidu.android.pushservice.internal.PushManager.fetchGroupMessages(r0_Context, r1_String, r2i, r3i);
    }

    public static void fetchMessages(Context r0_Context, int r1i, int r2i) {
        com.baidu.android.pushservice.internal.PushManager.fetchMessages(r0_Context, r1i, r2i);
    }

    public static void getGroupInfo(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.getGroupInfo(r0_Context, r1_String);
    }

    public static void getGroupList(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.getGroupList(r0_Context);
    }

    public static void getGroupMessageCounts(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.getGroupMessageCounts(r0_Context, r1_String);
    }

    public static void getMessageCounts(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.getMessageCounts(r0_Context);
    }

    public static void init(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.init(r0_Context, r1_String);
    }

    public static void init(Context r0_Context, String r1_String, String r2_String) {
        com.baidu.android.pushservice.internal.PushManager.init(r0_Context, r1_String, r2_String);
    }

    public static void initFromAKSK(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.initFromAKSK(r0_Context, r1_String);
    }

    public static boolean isConnected(Context r1_Context) {
        return com.baidu.android.pushservice.internal.PushManager.isConnected(r1_Context);
    }

    public static boolean isPushEnabled(Context r1_Context) {
        return com.baidu.android.pushservice.internal.PushManager.isPushEnabled(r1_Context);
    }

    public static void listTags(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.listTags(r0_Context);
    }

    public static void resumeWork(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.resumeWork(r0_Context);
    }

    public static void sdkBind(Context r0_Context, int r1i, String r2_String, int r3i) {
        com.baidu.android.pushservice.internal.PushManager.sdkBind(r0_Context, r1i, r2_String, r3i);
    }

    public static void sdkStartWork(Context r0_Context, String r1_String, int r2i) {
        com.baidu.android.pushservice.internal.PushManager.sdkStartWork(r0_Context, r1_String, r2i);
    }

    public static void sendMsgToUser(Context r0_Context, String r1_String, String r2_String, String r3_String, String r4_String) {
        com.baidu.android.pushservice.internal.PushManager.sendMsgToUser(r0_Context, r1_String, r2_String, r3_String, r4_String);
    }

    public static void setAccessToken(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.setAccessToken(r0_Context, r1_String);
    }

    public static void setApiKey(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.setApiKey(r0_Context, r1_String);
    }

    public static void setBduss(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.setBduss(r0_Context, r1_String);
    }

    public static void setDefaultNotificationBuilder(Context r1_Context, PushNotificationBuilder r2_PushNotificationBuilder) {
        com.baidu.android.pushservice.internal.PushManager.setDefaultNotificationBuilder(r1_Context, r2_PushNotificationBuilder.getInner());
    }

    public static void setMediaNotificationBuilder(Context r1_Context, PushNotificationBuilder r2_PushNotificationBuilder) {
        com.baidu.android.pushservice.internal.PushManager.setMediaNotificationBuilder(r1_Context, r2_PushNotificationBuilder.getInner());
    }

    public static void setNotificationBuilder(Context r1_Context, int r2i, PushNotificationBuilder r3_PushNotificationBuilder) {
        try {
            com.baidu.android.pushservice.internal.PushManager.setNotificationBuilder(r1_Context, r2i, r3_PushNotificationBuilder.getInner());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setTags(Context r0_Context, List<String> r1_List_String) {
        com.baidu.android.pushservice.internal.PushManager.setTags(r0_Context, r1_List_String);
    }

    public static void startWork(Context r0_Context, int r1i, String r2_String) {
        com.baidu.android.pushservice.internal.PushManager.startWork(r0_Context, r1i, r2_String);
    }

    public static void startWork(Context r0_Context, String r1_String, String r2_String) {
        com.baidu.android.pushservice.internal.PushManager.startWork(r0_Context, r1_String, r2_String);
    }

    public static void stopWork(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.stopWork(r0_Context);
    }

    public static void tryConnect(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.tryConnect(r0_Context);
    }

    public static void unbind(Context r0_Context) {
        com.baidu.android.pushservice.internal.PushManager.unbind(r0_Context);
    }

    public static void unbindGroup(Context r0_Context, String r1_String) {
        com.baidu.android.pushservice.internal.PushManager.unbindGroup(r0_Context, r1_String);
    }
}