package com.tencent.open.cgireport;

import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;

// compiled from: ProGuard
public class reportItem {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public reportItem() {
        this.a = RContactStorage.PRIMARY_KEY;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = RContactStorage.PRIMARY_KEY;
        this.d = RContactStorage.PRIMARY_KEY;
        this.e = RContactStorage.PRIMARY_KEY;
        this.f = RContactStorage.PRIMARY_KEY;
        this.g = RContactStorage.PRIMARY_KEY;
    }

    public reportItem(String r4_String, String r5_String, String r6_String, String r7_String, String r8_String, String r9_String, String r10_String) {
        this.a = r4_String + RContactStorage.PRIMARY_KEY;
        this.b = r5_String + RContactStorage.PRIMARY_KEY;
        this.c = r6_String + RContactStorage.PRIMARY_KEY;
        this.d = r7_String + RContactStorage.PRIMARY_KEY;
        this.e = r8_String + RContactStorage.PRIMARY_KEY;
        this.f = r9_String + RContactStorage.PRIMARY_KEY;
        this.g = r10_String + RContactStorage.PRIMARY_KEY;
        Log.i("report_debug", "reportItem apn=" + this.a + ",frequency=" + this.b + ",commandid=" + this.c + ",resultcode=" + this.d + "timecost" + this.e + ",reqsize=" + this.f + ",rspsize=" + this.g);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.g;
    }

    public String g() {
        return this.f;
    }
}