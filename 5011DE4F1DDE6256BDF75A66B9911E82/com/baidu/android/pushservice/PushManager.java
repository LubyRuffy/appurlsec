package com.baidu.android.pushservice;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import java.util.List;

// compiled from: SourceFile
public class PushManager {
    public static void activityStarted(Activity r1_Activity) {
        LoadExecutor.excuteMethod(new ab(r1_Activity), r1_Activity);
    }

    public static void activityStoped(Activity r1_Activity) {
        LoadExecutor.excuteMethod(new am(r1_Activity), r1_Activity);
    }

    public static void bind(Context r1_Context, int r2i) {
        LoadExecutor.excuteMethod(new ay(r1_Context, r2i), r1_Context);
    }

    public static void bindGroup(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new w(r1_Context, r2_String), r1_Context);
    }

    public static void delTags(Context r1_Context, List<String> r2_List_String) {
        LoadExecutor.excuteMethod(new z(r1_Context, r2_List_String), r1_Context);
    }

    public static void deleteMessages(Context r1_Context, String[] r2_StringA) {
        LoadExecutor.excuteMethod(new u(r1_Context, r2_StringA), r1_Context);
    }

    public static void disableLbs(Context r1_Context) {
        LoadExecutor.excuteMethod(new ao(r1_Context), r1_Context);
    }

    public static void enableLbs(Context r1_Context) {
        LoadExecutor.excuteMethod(new an(r1_Context), r1_Context);
    }

    public static void fetchGroupMessages(Context r1_Context, String r2_String, int r3i, int r4i) {
        LoadExecutor.excuteMethod(new ae(r1_Context, r2_String, r3i, r4i), r1_Context);
    }

    public static void fetchMessages(Context r1_Context, int r2i, int r3i) {
        LoadExecutor.excuteMethod(new r(r1_Context, r2i, r3i), r1_Context);
    }

    public static void getGroupInfo(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new ac(r1_Context, r2_String), r1_Context);
    }

    public static void getGroupList(Context r1_Context) {
        LoadExecutor.excuteMethod(new ad(r1_Context), r1_Context);
    }

    public static void getGroupMessageCounts(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new ag(r1_Context, r2_String), r1_Context);
    }

    public static void getMessageCounts(Context r1_Context) {
        LoadExecutor.excuteMethod(new s(r1_Context), r1_Context);
    }

    public static void init(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new t(r1_Context, r2_String), r1_Context);
    }

    public static void init(Context r1_Context, String r2_String, String r3_String) {
        LoadExecutor.excuteMethod(new q(r1_Context, r2_String, r3_String), r1_Context);
    }

    public static void initFromAKSK(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new af(r1_Context, r2_String), r1_Context);
    }

    public static boolean isConnected(Context r2_Context) {
        try {
            r2_Context.getClassLoader().loadClass("com.baidu.android.pushservice.internal.PushManager");
            return com.baidu.android.pushservice.apiproxy.PushManager.isConnected(r2_Context);
        } catch (ClassNotFoundException e) {
            new ak(r2_Context).start();
            return false;
        }
    }

    public static boolean isPushEnabled(Context r2_Context) {
        try {
            r2_Context.getClassLoader().loadClass("com.baidu.android.pushservice.internal.PushManager");
            return com.baidu.android.pushservice.apiproxy.PushManager.isPushEnabled(r2_Context);
        } catch (ClassNotFoundException e) {
            new au(r2_Context).start();
            return false;
        }
    }

    public static void listTags(Context r1_Context) {
        LoadExecutor.excuteMethod(new y(r1_Context), r1_Context);
    }

    public static void resumeWork(Context r1_Context) {
        LoadExecutor.excuteMethod(new at(r1_Context), r1_Context);
    }

    public static void sdkBind(Context r1_Context, int r2i, String r3_String, int r4i) {
        LoadExecutor.excuteMethod(new az(r1_Context, r2i, r3_String, r4i), r1_Context);
    }

    public static void sdkStartWork(Context r1_Context, String r2_String, int r3i) {
        LoadExecutor.excuteMethod(new ar(r1_Context, r2_String, r3i), r1_Context);
    }

    public static void sendMsgToUser(Context r6_Context, String r7_String, String r8_String, String r9_String, String r10_String) {
        LoadExecutor.excuteMethod(new v(r6_Context, r7_String, r8_String, r9_String, r10_String), r6_Context);
    }

    public static void setAccessToken(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new av(r1_Context, r2_String), r1_Context);
    }

    public static void setApiKey(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new aw(r1_Context, r2_String), r1_Context);
    }

    public static void setBduss(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new ax(r1_Context, r2_String), r1_Context);
    }

    public static void setDefaultNotificationBuilder(Context r1_Context, PushNotificationBuilder r2_PushNotificationBuilder) {
        LoadExecutor.excuteMethod(new ah(r1_Context, r2_PushNotificationBuilder), r1_Context);
    }

    public static void setMediaNotificationBuilder(Context r1_Context, PushNotificationBuilder r2_PushNotificationBuilder) {
        LoadExecutor.excuteMethod(new aj(r1_Context, r2_PushNotificationBuilder), r1_Context);
    }

    public static void setNotificationBuilder(Context r1_Context, int r2i, PushNotificationBuilder r3_PushNotificationBuilder) {
        LoadExecutor.excuteMethod(new ai(r1_Context, r2i, r3_PushNotificationBuilder), r1_Context);
    }

    public static void setTags(Context r1_Context, List<String> r2_List_String) {
        LoadExecutor.excuteMethod(new x(r1_Context, r2_List_String), r1_Context);
    }

    public static void startWork(Context r1_Context, int r2i, String r3_String) {
        LoadExecutor.excuteMethod(new aq(r1_Context, r2i, r3_String), r1_Context);
    }

    public static void startWork(Context r2_Context, String r3_String, String r4_String) {
        Log.d("YYY", "AAA start work");
        LoadExecutor.excuteMethod(new ap(r2_Context, r3_String, r4_String), r2_Context);
    }

    public static void stopWork(Context r1_Context) {
        LoadExecutor.excuteMethod(new as(r1_Context), r1_Context);
    }

    public static void tryConnect(Context r1_Context) {
        LoadExecutor.excuteMethod(new al(r1_Context), r1_Context);
    }

    public static void unbind(Context r1_Context) {
        LoadExecutor.excuteMethod(new ba(r1_Context), r1_Context);
    }

    public static void unbindGroup(Context r1_Context, String r2_String) {
        LoadExecutor.excuteMethod(new aa(r1_Context, r2_String), r1_Context);
    }
}