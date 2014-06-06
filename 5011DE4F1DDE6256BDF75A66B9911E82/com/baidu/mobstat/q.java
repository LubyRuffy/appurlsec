package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import com.baidu.mobstat.a.b;
import com.baidu.mobstat.a.c;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

class q {
    private static HandlerThread a;
    private static Handler b;
    private static q k;
    private long c;
    private long d;
    private long e;
    private long f;
    private WeakReference<Context> g;
    private WeakReference<Fragment> h;
    private WeakReference<Object> i;
    private o j;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private String r;

    static {
        a = new HandlerThread("SessionAnalysisThread");
        k = new q();
    }

    private q() {
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.j = new o();
        this.l = -1;
        this.m = true;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = null;
        a.start();
        a.setPriority(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        b = new Handler(a.getLooper());
    }

    static Context a(Object r4_Object) {
        try {
            return (Context) r4_Object.getClass().getMethod("getActivity", new Class[0]).invoke(r4_Object, new Object[0]);
        } catch (Throwable th) {
            c.a(th.getMessage());
            return null;
        }
    }

    private void a(Context r4_Context) {
        if (r4_Context == null) {
            c.a("stat", "clearLastSession(Context context):context=null");
        } else {
            b.a(false, r4_Context, "__local_last_session.json", "{}", false);
        }
    }

    private void a(boolean r1z) {
        this.m = r1z;
    }

    public static q b() {
        return k;
    }

    private void c(Context r6_Context, long r7j) {
        c.a("stat", "flush current session to last_session.json");
        String r0_String = "{}";
        JSONObject r0_JSONObject = new JSONObject();
        r0_JSONObject = this.j.c();
        try {
            r0_JSONObject.put("e", r7j);
        } catch (JSONException e) {
            c.a("stat", "StatSession.flushSession() failed");
        }
        r0_String = r0_JSONObject.toString();
        c.a("stat", "cacheString=" + r0_String);
        b.a(false, r6_Context, "__local_last_session.json", r0_String, false);
    }

    private boolean e() {
        return this.m;
    }

    public int a() {
        if (this.l == -1) {
            this.l = 30000;
        }
        return this.l;
    }

    public void a(int r2i) {
        this.l = r2i * 1000;
    }

    public void a(Context r11_Context, long r12j) {
        c.a("stat", "AnalysisResume job");
        if (this.n) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "stat";
            r0_ObjectA[1] = "\u9057\u6f0fStatService.onPause() || missing StatService.onPause()";
            c.c(r0_ObjectA);
        }
        this.n = true;
        if (e()) {
            c.a("is_first_resume=true");
            a(false);
            b.post(new r(this));
        } else {
            c.a("stat", " is_first_resume=false");
        }
        b.post(new w(this, this.c, r12j, r11_Context, null, null, 1));
        this.g = new WeakReference(r11_Context);
        this.d = r12j;
    }

    public void a(Context r11_Context, long r12j, String r14_String) {
        c.a("stat", "AnalysisPageStart");
        if (this.q) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "stat";
            r0_ObjectA[1] = "\u9057\u6f0fStatService.onPageEnd() || missing StatService.onPageEnd()";
            c.c(r0_ObjectA);
        }
        this.q = true;
        if (e()) {
            c.b("PPPPPPPPPPPPP is_first_resume=true");
            a(false);
            b.post(new s(this));
        } else {
            c.a("stat", " is_first_resume=false");
        }
        b.post(new w(this, this.c, r12j, r11_Context, null, null, 1));
        this.r = r14_String;
        this.g = new WeakReference(r11_Context);
        this.d = r12j;
    }

    public void a(Fragment r11_Fragment, long r12j) {
        c.a("stat", "post resume job");
        if (this.o) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "stat";
            r0_ObjectA[1] = "\u9057\u6f0fStatService.onPause() || missing StatService.onPause()";
            c.c(r0_ObjectA);
        }
        this.o = true;
        if (e()) {
            c.a("stat", "is_first_resume=true");
            a(false);
            b.post(new t(this));
        } else {
            c.a("stat", "is_first_resume=false");
        }
        b.post(new w(this, this.c, r12j, null, r11_Fragment, null, 2));
        this.h = new WeakReference(r11_Fragment);
        this.e = r12j;
    }

    public void a(Object r11_Object, long r12j) {
        c.a("stat", "post resume job");
        if (this.p) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "stat";
            r0_ObjectA[1] = "\u9057\u6f0fStatService.onPause() || missing StatService.onPause()";
            c.c(r0_ObjectA);
        }
        this.p = true;
        if (e()) {
            c.a("stat", "is_first_resume=true");
            a(false);
            b.post(new u(this));
        } else {
            c.a("stat", "is_first_resume=false");
        }
        b.post(new w(this, this.c, r12j, null, null, r11_Object, 3));
        this.i = new WeakReference(r11_Object);
        this.f = r12j;
    }

    public void b(Context r18_Context, long r19j) {
        c.a("stat", "post pause job");
        if (this.n) {
            this.n = false;
            b.post(new v(this, r19j, r18_Context, null, this.d, (Context) this.g.get(), null, 1, null, null, null));
            this.c = r19j;
        } else {
            Object[] r3_ObjectA = new Object[2];
            r3_ObjectA[0] = "stat";
            r3_ObjectA[1] = "\u9057\u6f0fStatService.onResume() || missing StatService.onResume()";
            c.c(r3_ObjectA);
        }
    }

    public void b(Context r18_Context, long r19j, String r21_String) {
        c.a("stat", "post pause job");
        Object[] r3_ObjectA;
        if (this.q) {
            this.q = false;
            if (this.r == null || (!this.r.equals(r21_String))) {
                r3_ObjectA = new Object[2];
                r3_ObjectA[0] = "stat";
                r3_ObjectA[1] = "Please check the reason : (1)\u9057\u6f0fStatService.onPageStart() || missing StatService.onPageStart() || (2)\u9875\u9762\u7684\u8d77\u59cb\u548c\u7ed3\u675f\u4e0d\u662f\u540c\u4e00\u9875\u9762 || The page " + r21_String + " name is not equal to the page end " + this.r + RContactStorage.PRIMARY_KEY;
                c.c(r3_ObjectA);
            } else {
                b.post(new v(this, r19j, r18_Context, null, this.d, (Context) this.g.get(), null, 1, r21_String, null, null));
                this.c = r19j;
            }
        } else {
            r3_ObjectA = new Object[2];
            r3_ObjectA[0] = "stat";
            r3_ObjectA[1] = "Please check (1)\u9057\u6f0fStatService.onPageStart() || missing StatService.onPageStart()";
            c.c(r3_ObjectA);
        }
    }

    public void b(Fragment r18_Fragment, long r19j) {
        c.a("stat", "post pause job");
        if (this.o) {
            this.o = false;
            b.post(new v(this, r19j, null, r18_Fragment, this.e, null, (Fragment) this.h.get(), 2, null, null, null));
            this.c = r19j;
        } else {
            Object[] r3_ObjectA = new Object[2];
            r3_ObjectA[0] = "stat";
            r3_ObjectA[1] = "\u9057\u6f0fandroid.support.v4.app.Fragment StatService.onResume() || android.support.v4.app.Fragment missing StatService.onResume()";
            c.c(r3_ObjectA);
        }
    }

    public void b(Object r18_Object, long r19j) {
        c.a("stat", "post pause job");
        if (this.p) {
            this.p = false;
            b.post(new v(this, r19j, null, null, this.f, null, null, 3, null, this.i.get(), r18_Object));
            this.c = r19j;
        } else {
            Object[] r3_ObjectA = new Object[2];
            r3_ObjectA[0] = "stat";
            r3_ObjectA[1] = "\u9057\u6f0fandroid.app.Fragment StatService.onResume() || android.app.Fragment missing StatService.onResume()";
            c.c(r3_ObjectA);
        }
    }

    public void c() {
        this.j.a(this.j.d() + 1);
    }

    public long d() {
        return this.j.a();
    }
}