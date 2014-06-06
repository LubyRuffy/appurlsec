package com.tencent.cloudsdk.report;

import com.tencent.cloudsdk.a;
import com.tencent.cloudsdk.d;

// compiled from: SourceFile
public class StatisticsSetting {
    private static final String a;

    static {
        a = StatisticsSetting.class.getName();
    }

    public static void enableOnlyWifi(boolean r6z) {
        Class[] r4_ClassA = new Class[1];
        r4_ClassA[0] = Boolean.TYPE;
        Object[] r5_ObjectA = new Object[1];
        r5_ObjectA[0] = Boolean.valueOf(r6z);
        d.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.report.StatisticsSetting", "com.tencent.cloudsdk.defaultsdk.mna.report.StatisticsSetting", "enableOnlyWifi", r4_ClassA, r5_ObjectA);
    }
}