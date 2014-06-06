package com.tencent.open;

import com.tencent.qc.stat.StatConfig;
import com.tencent.qc.stat.StatService;

// compiled from: ProGuard
public class TencentStat {
    public static void a(TContext r0_TContext, String r1_String) {
        b(r0_TContext);
        StatService.b(r1_String);
    }

    public static void a(TContext r1_TContext, String r2_String, String ... r3_StringA) {
        b(r1_TContext);
        StatService.a(r1_TContext.f(), r2_String, r3_StringA);
    }

    public static boolean a(TContext r2_TContext) {
        return OpenConfig.a(r2_TContext.f(), r2_TContext.d()).d("Common_ta_enable");
    }

    public static void b(TContext r1_TContext) {
        if (a(r1_TContext)) {
            StatConfig.a(true);
        } else {
            StatConfig.a(false);
        }
    }

    public static void b(TContext r1_TContext, String r2_String) {
        b(r1_TContext);
        if (r2_String != null) {
            StatService.a(r1_TContext.f(), r2_String);
        }
    }
}