package com.amap.api.location.core;

import android.content.Context;
import android.util.Log;
import com.aps.j;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.message.RMsgInfo;
import org.json.JSONObject;

// compiled from: AuthManager.java
public class a {
    public static int a;
    public static String b;
    public static int c;

    static {
        a = -1;
        b = RContactStorage.PRIMARY_KEY;
        c = 0;
    }

    private static String a_() {
        return d.c + "/log/init";
    }

    public static synchronized boolean a_(Context r5_Context) {
        boolean r0z;
        synchronized (a.class) {
            r0z = true;
            byte[] r1_byteA = b();
            try {
                String r1_String = j.a().a(r5_Context, a(), r1_byteA);
                if (r1_String != null) {
                    r0z = a(r1_String);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r0z;
    }

    private static boolean a_(String r4_String) {
        try {
            JSONObject r2_JSONObject = new JSONObject(r4_String);
            if (r2_JSONObject.has(RMsgInfo.COL_STATUS)) {
                int r3i = r2_JSONObject.getInt(RMsgInfo.COL_STATUS);
                if (r3i == 1) {
                    a = 1;
                } else if (r3i == 0) {
                    a = 0;
                }
            }
            if (r2_JSONObject.has("info")) {
                b = r2_JSONObject.getString("info");
            }
            if (a == 0) {
                Log.i("AuthFailure", b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a == 1;
    }

    private static byte[] b() {
        StringBuffer r0_StringBuffer = new StringBuffer();
        r0_StringBuffer.append("resType=json&encode=UTF-8");
        return r0_StringBuffer.toString().getBytes();
    }
}