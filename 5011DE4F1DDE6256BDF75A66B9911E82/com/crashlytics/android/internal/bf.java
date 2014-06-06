package com.crashlytics.android.internal;

import java.util.Map;

// compiled from: SourceFile
final class bf {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final long i;
    public final bg j;
    public final Map<String, String> k;
    private String l;

    private bf(String r1_String, String r2_String, String r3_String, String r4_String, String r5_String, String r6_String, String r7_String, String r8_String, long r9j, bg r11_bg, Map<String, String> r12_Map_String__String) {
        this.a = r1_String;
        this.b = r2_String;
        this.c = r3_String;
        this.d = r4_String;
        this.e = r5_String;
        this.f = r6_String;
        this.g = r7_String;
        this.h = r8_String;
        this.i = r9j;
        this.j = r11_bg;
        this.k = r12_Map_String__String;
    }

    public static final bf a(String r13_String, String r14_String, String r15_String, String r16_String, String r17_String, String r18_String, String r19_String, String r20_String, bg r21_bg, Map<String, String> r22_Map_String__String) {
        return new bf(r13_String, r14_String, r15_String, r16_String, r17_String, r18_String, r19_String, r20_String, System.currentTimeMillis(), r21_bg, r22_Map_String__String);
    }

    public final String toString() {
        if (this.l == null) {
            StringBuilder r0_StringBuilder = new StringBuilder();
            r0_StringBuilder.append("[");
            r0_StringBuilder.append(getClass().getSimpleName());
            r0_StringBuilder.append(": appBundleId=");
            r0_StringBuilder.append(this.a);
            r0_StringBuilder.append(", executionId=");
            r0_StringBuilder.append(this.b);
            r0_StringBuilder.append(", installationId=");
            r0_StringBuilder.append(this.c);
            r0_StringBuilder.append(", androidId=");
            r0_StringBuilder.append(this.d);
            r0_StringBuilder.append(", osVersion=");
            r0_StringBuilder.append(this.e);
            r0_StringBuilder.append(", deviceModel=");
            r0_StringBuilder.append(this.f);
            r0_StringBuilder.append(", appVersionCode=");
            r0_StringBuilder.append(this.g);
            r0_StringBuilder.append(", appVersionName=");
            r0_StringBuilder.append(this.h);
            r0_StringBuilder.append(", timestamp=");
            r0_StringBuilder.append(this.i);
            r0_StringBuilder.append(", type=");
            r0_StringBuilder.append(this.j);
            r0_StringBuilder.append(", details=");
            r0_StringBuilder.append(this.k.toString());
            r0_StringBuilder.append("]");
            this.l = r0_StringBuilder.toString();
        }
        return this.l;
    }
}