package com.baidu.frontia.api;

import android.content.Context;
import com.baidu.android.pushservice.internal.PushManager;
import com.baidu.android.pushservice.internal.PushSettings;
import com.baidu.frontia.FrontiaQuery;
import com.baidu.frontia.api.FrontiaPushListener.CommonMessageListener;
import com.baidu.frontia.api.FrontiaPushListener.DescribeMessageListener;
import com.baidu.frontia.api.FrontiaPushListener.ListMessageListener;
import com.baidu.frontia.api.FrontiaPushListener.PushMessageListener;
import com.baidu.frontia.api.FrontiaPushUtil.MessageContent;
import com.baidu.frontia.api.FrontiaPushUtil.Trigger;
import com.baidu.frontia.base.stat.StatUtils;
import com.baidu.frontia.framework.IModule;
import com.baidu.frontia.module.push.FrontiaPushImpl;
import com.baidu.frontia.module.push.FrontiaPushListenerImpl.CommonMessageListenerImpl;
import com.baidu.frontia.module.push.FrontiaPushListenerImpl.DescribeMessageListenerImpl;
import com.baidu.frontia.module.push.FrontiaPushListenerImpl.ListMessageListenerImpl;
import com.baidu.frontia.module.push.FrontiaPushListenerImpl.PushMessageListenerImpl;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.List;

public class FrontiaPush implements IModule {
    private static FrontiaPush a;
    private FrontiaPushImpl b;
    private Context c;

    class a {
        CommonMessageListenerImpl a;
        private CommonMessageListener c;

        a(CommonMessageListener r3_CommonMessageListener) {
            this.a = new a(this);
            this.c = r3_CommonMessageListener;
        }

        CommonMessageListenerImpl a_() {
            return this.a;
        }
    }

    class b {
        DescribeMessageListenerImpl a;
        private DescribeMessageListener c;

        b(DescribeMessageListener r3_DescribeMessageListener) {
            this.a = new b(this);
            this.c = r3_DescribeMessageListener;
        }

        DescribeMessageListenerImpl a() {
            return this.a;
        }
    }

    class c {
        ListMessageListenerImpl a;
        private ListMessageListener c;

        c(ListMessageListener r3_ListMessageListener) {
            this.a = new c(this);
            this.c = r3_ListMessageListener;
        }

        ListMessageListenerImpl a() {
            return this.a;
        }
    }

    class d {
        PushMessageListenerImpl a;
        private PushMessageListener c;

        d(PushMessageListener r3_PushMessageListener) {
            this.a = new d(this);
            this.c = r3_PushMessageListener;
        }

        PushMessageListenerImpl a() {
            return this.a;
        }
    }

    static {
        a = null;
    }

    private FrontiaPush(Context r2_Context) {
        this.c = r2_Context;
        this.b = new FrontiaPushImpl(r2_Context);
    }

    public static FrontiaPush newInstance(Context r2_Context) {
        if (r2_Context == null) {
            return null;
        }
        if (a == null) {
            synchronized (FrontiaPush.class) {
                if (a == null) {
                    a = new FrontiaPush(r2_Context);
                }
            }
        }
        return a;
    }

    public void deleteTags(List<String> r2_List_String) {
        this.b.deleteTags(r2_List_String);
    }

    public void describeMessage(String r3_String, DescribeMessageListener r4_DescribeMessageListener) {
        this.b.describeMessage(r3_String, new b(r4_DescribeMessageListener).a());
    }

    public void disableLbs() {
        this.b.disableLbs();
    }

    public void enableLbs() {
        this.b.enableLbs();
    }

    public void init(String r2_String) {
        this.b.init(r2_String);
    }

    public boolean isPushWorking() {
        return PushManager.isPushEnabled(this.c);
    }

    public void listMessage(FrontiaQuery r4_FrontiaQuery, ListMessageListener r5_ListMessageListener) {
        this.b.listMessage(r4_FrontiaQuery.toJSONObject(), new c(r5_ListMessageListener).a());
    }

    public void listTags() {
        this.b.listTags();
    }

    public void pushMessage(MessageContent r4_MessageContent, PushMessageListener r5_PushMessageListener) {
        this.b.pushMessage(r4_MessageContent.a(), new d(r5_PushMessageListener).a());
    }

    public void pushMessage(Trigger r5_Trigger, MessageContent r6_MessageContent, PushMessageListener r7_PushMessageListener) {
        this.b.pushMessage(r5_Trigger.a(), r6_MessageContent.a(), new d(r7_PushMessageListener).a());
    }

    public void pushMessage(String r4_String, MessageContent r5_MessageContent, PushMessageListener r6_PushMessageListener) {
        this.b.pushMessage(r4_String, r5_MessageContent.a(), new d(r6_PushMessageListener).a());
    }

    public void pushMessage(String r5_String, Trigger r6_Trigger, MessageContent r7_MessageContent, PushMessageListener r8_PushMessageListener) {
        this.b.pushMessage(r5_String, r6_Trigger.a(), r7_MessageContent.a(), new d(r8_PushMessageListener).a());
    }

    public void pushMessage(String r4_String, String r5_String, MessageContent r6_MessageContent, PushMessageListener r7_PushMessageListener) {
        this.b.pushMessage(r4_String, r5_String, r6_MessageContent.a(), new d(r7_PushMessageListener).a());
    }

    public void pushMessage(String r7_String, String r8_String, Trigger r9_Trigger, MessageContent r10_MessageContent, PushMessageListener r11_PushMessageListener) {
        this.b.pushMessage(r7_String, r8_String, r9_Trigger.a(), r10_MessageContent.a(), new d(r11_PushMessageListener).a());
    }

    public void removeMessage(String r3_String, CommonMessageListener r4_CommonMessageListener) {
        this.b.removeMessage(r3_String, new a(r4_CommonMessageListener).a());
    }

    public void replaceMessage(String r5_String, Trigger r6_Trigger, MessageContent r7_MessageContent, CommonMessageListener r8_CommonMessageListener) {
        this.b.replaceMessage(r5_String, r6_Trigger.a(), r7_MessageContent.a(), new a(r8_CommonMessageListener).a());
    }

    public void replaceMessage(String r7_String, String r8_String, Trigger r9_Trigger, MessageContent r10_MessageContent, CommonMessageListener r11_CommonMessageListener) {
        this.b.replaceMessage(r7_String, r8_String, r9_Trigger.a(), r10_MessageContent.a(), new a(r11_CommonMessageListener).a());
    }

    public void replaceMessage(String r8_String, String r9_String, String r10_String, Trigger r11_Trigger, MessageContent r12_MessageContent, CommonMessageListener r13_CommonMessageListener) {
        this.b.replaceMessage(r8_String, r9_String, r10_String, r11_Trigger.a(), r12_MessageContent.a(), new a(r13_CommonMessageListener).a());
    }

    public void resume() {
        PushManager.resumeWork(this.c);
    }

    public void setDebugModeEnabled(boolean r2z) {
        PushSettings.enableDebugMode(this.c, r2z);
    }

    public void setNotificationBuilder(int r3i, a r4_a) {
        if (r4_a != null) {
            PushManager.setNotificationBuilder(this.c, r3i, r4_a.a());
        }
    }

    public void setTags(List<String> r2_List_String) {
        this.b.setTags(r2_List_String);
    }

    public void start() {
        this.b.start();
    }

    public void start(String r2_String) {
        this.b.start(r2_String);
    }

    public void stop() {
        PushManager.stopWork(this.c);
        StatUtils.insertBehavior(this.c, "010702", 0, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, System.currentTimeMillis());
    }
}