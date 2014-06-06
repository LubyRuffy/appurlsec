package com.crashlytics.android.internal;

import android.app.Activity;
import android.os.Looper;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

// compiled from: SourceFile
class ac implements ak {
    be a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final ScheduledExecutorService j;

    public ac(String r12_String, String r13_String, String r14_String, String r15_String, String r16_String, String r17_String, String r18_String, o r19_o, av r20_av) {
        this(r12_String, r13_String, r14_String, r15_String, r16_String, r17_String, r18_String, r19_o, ah.b("Crashlytics SAM"), r20_av);
    }

    ac(String r2_String, String r3_String, String r4_String, String r5_String, String r6_String, String r7_String, String r8_String, o r9_o, ScheduledExecutorService r10_ScheduledExecutorService, av r11_av) {
        this.b = r2_String;
        this.c = r3_String;
        this.d = r4_String;
        this.e = r5_String;
        this.f = r6_String;
        this.g = r7_String;
        this.h = r8_String;
        this.i = UUID.randomUUID().toString();
        this.j = r10_ScheduledExecutorService;
        this.a = new n(r10_ScheduledExecutorService, r9_o, r11_av);
        r9_o.a((ak)this);
    }

    private void a(bf r2_bf, boolean r3z) {
        a(new at(this, r2_bf, r3z));
    }

    private void a(bg r11_bg, Activity r12_Activity, boolean r13z) {
        a(bf.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, r11_bg, Collections.singletonMap("activity", r12_Activity.getClass().getName())), false);
    }

    private void a(Runnable r2_Runnable) {
        try {
            this.j.submit(r2_Runnable);
        } catch (Exception e) {
            ab.d("Crashlytics failed to submit analytics task");
        }
    }

    void a() {
        a(new bd(this));
    }

    public final void a(Activity r3_Activity) {
        a(bg.a, r3_Activity, false);
    }

    final void a(aK r2_aK, String r3_String) {
        a(new bb(this, r2_aK, r3_String));
    }

    public final void a(String r3_String) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("onCrash called from main thread!!!");
        } else {
            try {
                this.j.submit(new al(this, r3_String)).get();
            } catch (Exception e) {
                ab.d("Crashlytics failed to run analytics task");
            }
        }
    }

    public final void b() {
        a(bf.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, bg.j, new HashMap()), true);
    }

    public final void b(Activity r3_Activity) {
        a(bg.g, r3_Activity, false);
    }

    public final void b(String r11_String) {
        a(bf.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, bg.h, Collections.singletonMap("sessionId", r11_String)), false);
    }

    public final void c() {
        a(new bc(this));
    }

    public final void c(Activity r3_Activity) {
        a(bg.e, r3_Activity, false);
    }

    public final void d(Activity r3_Activity) {
        a(bg.c, r3_Activity, false);
    }

    public final void e(Activity r3_Activity) {
        a(bg.d, r3_Activity, false);
    }

    public final void f(Activity r3_Activity) {
        a(bg.b, r3_Activity, false);
    }

    public final void g(Activity r3_Activity) {
        a(bg.f, r3_Activity, false);
    }
}