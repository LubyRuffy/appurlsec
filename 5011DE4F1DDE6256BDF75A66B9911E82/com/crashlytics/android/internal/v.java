package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
public final class v extends p {
    private b a;
    private AtomicReference<q> b;
    private boolean c;
    private File d;
    private Application e;
    private WeakReference<Activity> f;
    private String g;
    private int h;
    private ConcurrentHashMap<Class<? extends u>, u> i;

    v() {
        this.b = new AtomicReference();
        this.h = 4;
        this.a = new B(m.a);
        this.i = new ConcurrentHashMap();
    }

    public static v a() {
        return cl.a;
    }

    private v a(Activity r2_Activity) {
        this.f = new WeakReference(r2_Activity);
        return this;
    }

    public static synchronized void a(Context r7_Context, u ... r8_uA) {
        synchronized (v.class) {
            if (cl.a.isInitialized()) {
            } else {
                v r0_v = cl.a;
                r0_v.e = r.b(r7_Context);
                v r2_v = r0_v.a(r.a(r7_Context));
                int r3i = r8_uA.length;
                int r0i = 0;
                while (r0i < r3i) {
                    Object r4_Object = r8_uA[r0i];
                    if (!r2_v.i.containsKey(r8_uA)) {
                        r2_v.i.putIfAbsent(r4_Object.getClass(), r4_Object);
                    }
                    r0i++;
                }
                r2_v.a(r7_Context);
            }
        }
    }

    public final <T extends u> T a(Class<T> r2_Class_T) {
        return (u) this.i.get(r2_Class_T);
    }

    public final void a(q r2_q) {
        this.b.set(r2_q);
    }

    public final void a(String r1_String) {
        this.g = r1_String;
    }

    public final void a(boolean r2z) {
        this.c = r2z;
        this.h = r2z ? XListViewFooter.STATE_NOMORE : XListViewFooter.STATE_NODATA;
    }

    public final q b() {
        q r0_q = (q) this.b.get();
        if (r0_q != null) {
            return r0_q;
        }
        r0_q = new r();
        return this.b.compareAndSet(null, r0_q) ? r0_q : (q) this.b.get();
    }

    protected final void c() {
        Context r1_Context = getContext();
        this.d = new File(r1_Context.getFilesDir(), "com.crashlytics.sdk.android");
        if (!this.d.exists()) {
            this.d.mkdirs();
        }
        if (VERSION.SDK_INT >= 14) {
            cj.a(new cj((byte) 0), this.e);
        }
        if (this.c && Log.isLoggable("CrashlyticsInternal", XListViewFooter.STATE_NOMORE)) {
            StringBuilder r2_StringBuilder = new StringBuilder();
            Iterator r3_Iterator = this.i.values().iterator();
            while (r3_Iterator.hasNext()) {
                p r0_p = (p) r3_Iterator.next();
                long r4j = System.nanoTime();
                r0_p.a(r1_Context);
                r2_StringBuilder.append("sdkPerfStart.").append(r0_p.getClass().getName()).append('=').append(System.nanoTime() - r4j).append('\n');
            }
            Log.d("CrashlyticsInternal", r2_StringBuilder.toString());
        } else {
            Iterator r2_Iterator = this.i.values().iterator();
            while (r2_Iterator.hasNext()) {
                ((p) r2_Iterator.next()).a(r1_Context);
            }
        }
    }

    public final Application d() {
        return this.e;
    }

    public final Activity e() {
        return this.f != null ? (Activity) this.f.get() : null;
    }

    public final boolean f() {
        return this.c;
    }

    public final int g() {
        return this.h;
    }

    public final String getVersion() {
        return "1.1.11.10";
    }

    public final File h() {
        return this.d;
    }

    public final String i() {
        return this.g;
    }
}