package com.tencent.mm.sdk.channel;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.Build;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;

public class MMessageAct {
    public static boolean send(Context r2_Context, String r3_String, String r4_String) {
        return send(r2_Context, r3_String, r3_String + ".wxapi.WXEntryActivity", r4_String, null);
    }

    public static boolean send(Context r2_Context, String r3_String, String r4_String, Bundle r5_Bundle) {
        return send(r2_Context, r3_String, r3_String + ".wxapi.WXEntryActivity", r4_String, r5_Bundle);
    }

    public static boolean send(Context r5_Context, String r6_String, String r7_String, String r8_String, Bundle r9_Bundle) {
        if (r5_Context == null || r6_String == null || r6_String.length() == 0 || r7_String == null || r7_String.length() == 0) {
            Log.e("MicroMsg.SDK.MMessageAct", "send fail, invalid arguments");
            return false;
        } else {
            Intent r1_Intent = new Intent();
            r1_Intent.setClassName(r6_String, r7_String);
            if (r9_Bundle != null) {
                r1_Intent.putExtras(r9_Bundle);
            }
            String r2_String = r5_Context.getPackageName();
            r1_Intent.putExtra(ConstantsMMessage.SDK_VERSION, Build.SDK_INT);
            r1_Intent.putExtra(ConstantsMMessage.APP_PACKAGE, r2_String);
            r1_Intent.putExtra(ConstantsMMessage.CONTENT, r8_String);
            r1_Intent.putExtra(ConstantsMMessage.CHECK_SUM, MMessageUtil.a(r8_String, r2_String));
            r1_Intent.addFlags(268435456).addFlags(134217728);
            try {
                r5_Context.startActivity(r1_Intent);
                Log.d("MicroMsg.SDK.MMessageAct", new StringBuilder("send mm message, intent=").append(r1_Intent).toString());
                return true;
            } catch (ActivityNotFoundException e) {
                Log.e("MicroMsg.SDK.MMessageAct", "send fail, target ActivityNotFound");
                return false;
            }
        }
    }

    public static boolean sendToWx(Context r3_Context, String r4_String) {
        return send(r3_Context, PluginIntent.APP_PACKAGE_PATTERN, "com.tencent.mm.plugin.base.stub.WXEntryActivity", r4_String, null);
    }

    public static boolean sendToWx(Context r2_Context, String r3_String, Bundle r4_Bundle) {
        return send(r2_Context, PluginIntent.APP_PACKAGE_PATTERN, "com.tencent.mm.plugin.base.stub.WXEntryActivity", r3_String, r4_Bundle);
    }
}