package com.qq.e.v2.util;

import android.util.Log;
import com.qq.e.comm.a;
import com.qq.e.v2.constants.Constants;
import com.qq.e.v2.constants.Constants.KEYS;
import com.qq.e.v2.net.GDTADNetClient;
import com.qq.e.v2.net.GDTSecurityADNetRequest;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GDTLogger {
    public static final boolean DEBUG_ENABLE = false;

    public static void d(String r0_String) {
    }

    public static void e(String r1_String) {
        Log.e("gdt_ad_mob", r1_String);
    }

    public static void e(String r1_String, Throwable r2_Throwable) {
        if (r2_Throwable == null) {
            Log.e("gdt_ad_mob", r1_String);
        } else {
            Log.e("gdt_ad_mob", r1_String, r2_Throwable);
        }
    }

    public static void i(String r1_String) {
        Log.i("gdt_ad_mob", r1_String);
    }

    public static void report(String r1_String) {
        report(r1_String, null);
    }

    public static void report(String r5_String, Throwable r6_Throwable) {
        e(r5_String, r6_Throwable);
        try {
            Map r0_Map = a.c();
            r0_Map.put(KEYS.CTXTSETTING, a.g());
            r0_Map.put(KEYS.APPINFO, a.f());
            Map r1_Map = new HashMap();
            r0_Map.put(KEYS.BIZ, r1_Map);
            if (r6_Throwable != null) {
                r1_Map.put("extype", r6_Throwable.getClass().getName());
                r1_Map.put("ext", r5_String + "\r" + r6_Throwable.getMessage() + "\r" + Arrays.toString(r6_Throwable.getStackTrace()));
            } else {
                r1_Map.put("extype", RContactStorage.PRIMARY_KEY);
                r1_Map.put("ex", r5_String);
            }
            GDTADNetClient.getInstance().excuteErrorReportTask(new GDTSecurityADNetRequest(Constants.ERR_URL, JsonUtil.mapToJson(r0_Map).toString(), null));
        } catch (Throwable th) {
            w("err while report", th);
        }
    }

    public static void w(String r1_String) {
        Log.e("gdt_ad_mob", r1_String);
    }

    public static void w(String r1_String, Throwable r2_Throwable) {
        if (r2_Throwable == null) {
            Log.w("gdt_ad_mob", r1_String);
        } else {
            Log.w("gdt_ad_mob", r1_String, r2_Throwable);
        }
    }
}