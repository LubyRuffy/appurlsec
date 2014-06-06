package com.tencent.mm.sdk.channel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.Build;
import com.tencent.mm.sdk.platformtools.Log;
import java.util.HashMap;
import java.util.Map;

public class MMessage {

    public static interface CallBack {
        public void handleMessage(Intent r1_Intent);
    }

    public static final class Receiver extends BroadcastReceiver {
        public static final Map<String, com.tencent.mm.sdk.channel.MMessage.CallBack> callbacks;
        private final com.tencent.mm.sdk.channel.MMessage.CallBack a;

        static {
            callbacks = new HashMap();
        }

        public Receiver() {
            this(null);
        }

        public Receiver(com.tencent.mm.sdk.channel.MMessage.CallBack r1_com_tencent_mm_sdk_channel_MMessage_CallBack) {
            this.a = r1_com_tencent_mm_sdk_channel_MMessage_CallBack;
        }

        public static void registerCallBack(String r1_String, com.tencent.mm.sdk.channel.MMessage.CallBack r2_com_tencent_mm_sdk_channel_MMessage_CallBack) {
            callbacks.put(r1_String, r2_com_tencent_mm_sdk_channel_MMessage_CallBack);
        }

        public static void unregisterCallBack(String r1_String) {
            callbacks.remove(r1_String);
        }

        public final void onReceive(Context r4_Context, Intent r5_Intent) {
            Log.d("MicroMsg.SDK.MMessage", new StringBuilder("receive intent=").append(r5_Intent).toString());
            if (this.a != null) {
                this.a.handleMessage(r5_Intent);
                Log.d("MicroMsg.SDK.MMessage", "mm message self-handled");
            } else {
                com.tencent.mm.sdk.channel.MMessage.CallBack r0_com_tencent_mm_sdk_channel_MMessage_CallBack = (com.tencent.mm.sdk.channel.MMessage.CallBack) callbacks.get(r5_Intent.getAction());
                if (r0_com_tencent_mm_sdk_channel_MMessage_CallBack != null) {
                    r0_com_tencent_mm_sdk_channel_MMessage_CallBack.handleMessage(r5_Intent);
                    Log.d("MicroMsg.SDK.MMessage", "mm message handled");
                }
            }
        }
    }

    public static void send(Context r1_Context, String r2_String, String r3_String) {
        send(r1_Context, r2_String, ConstantsMMessage.ACTION_MESSAGE, r3_String);
    }

    public static void send(Context r1_Context, String r2_String, String r3_String, String r4_String) {
        send(r1_Context, r2_String, r3_String, r4_String, null);
    }

    public static boolean send(Context r5_Context, String r6_String, String r7_String, String r8_String, Bundle r9_Bundle) {
        String r0_String = r6_String + ".permission.MM_MESSAGE";
        Intent r1_Intent = new Intent(r7_String);
        if (r9_Bundle != null) {
            r1_Intent.putExtras(r9_Bundle);
        }
        String r2_String = r5_Context.getPackageName();
        r1_Intent.putExtra(ConstantsMMessage.SDK_VERSION, Build.SDK_INT);
        r1_Intent.putExtra(ConstantsMMessage.APP_PACKAGE, r2_String);
        r1_Intent.putExtra(ConstantsMMessage.CONTENT, r8_String);
        r1_Intent.putExtra(ConstantsMMessage.CHECK_SUM, MMessageUtil.a(r8_String, r2_String));
        r5_Context.sendBroadcast(r1_Intent, r0_String);
        Log.d("MicroMsg.SDK.MMessage", new StringBuilder("send mm message, intent=").append(r1_Intent).append(", perm=").append(r0_String).toString());
        return true;
    }
}