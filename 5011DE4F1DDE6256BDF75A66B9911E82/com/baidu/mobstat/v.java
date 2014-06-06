package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.baidu.mobstat.a.c;
import java.lang.ref.WeakReference;

class v implements Runnable {
    final /* synthetic */ q a;
    private long b;
    private WeakReference<Context> c;
    private WeakReference<Fragment> d;
    private WeakReference<Object> e;
    private long f;
    private WeakReference<Context> g;
    private WeakReference<Fragment> h;
    private WeakReference<Object> i;
    private int j;
    private String k;

    public v(q r2_q, long r3j, Context r5_Context, Fragment r6_Fragment, long r7j, Context r9_Context, Fragment r10_Fragment, int r11i, String r12_String, Object r13_Object, Object r14_Object) {
        this.a = r2_q;
        this.k = null;
        this.b = r3j;
        if (r5_Context != null) {
            this.c = new WeakReference(r5_Context);
        }
        this.f = r7j;
        if (r9_Context != null) {
            this.g = new WeakReference(r9_Context);
        }
        if (r6_Fragment != null) {
            this.d = new WeakReference(r6_Fragment);
        }
        if (r10_Fragment != null) {
            this.h = new WeakReference(r10_Fragment);
        }
        if (r13_Object != null) {
            this.i = new WeakReference(r13_Object);
        }
        if (r14_Object != null) {
            this.e = new WeakReference(r14_Object);
        }
        this.j = r11i;
        this.k = r12_String;
    }

    public void run() {
        Object[] r0_ObjectA;
        long r2j;
        if (this.j == 1) {
            if (this.c.get() != this.g.get()) {
                if (this.k != null) {
                    r0_ObjectA = new Object[2];
                    r0_ObjectA[0] = "stat";
                    r0_ObjectA[1] = "onPageStart() \u6216 onPageEnd()\u5b89\u653e\u9519\u8bef  || onPageStart() or onPageEnd() install error.";
                    c.c(r0_ObjectA);
                } else {
                    r0_ObjectA = new Object[2];
                    r0_ObjectA[0] = "stat";
                    r0_ObjectA[1] = "onPause() \u6216 onResume()\u5b89\u653e\u9519\u8bef  ||  onPause() or onResume() install error.";
                    c.c(r0_ObjectA);
                }
            } else {
                r2j = this.b - this.f;
                Activity r0_Activity = (Activity) this.c.get();
                if (r0_Activity == null) {
                    r0_ObjectA = new Object[2];
                    r0_ObjectA[0] = "stat";
                    r0_ObjectA[1] = "onPause,WeakReference is already been released";
                    c.c(r0_ObjectA);
                } else {
                    StringBuilder r1_StringBuilder = new StringBuilder();
                    if (this.k != null) {
                        r1_StringBuilder.append(this.k);
                    } else {
                        r1_StringBuilder.append(r0_Activity.getComponentName().getShortClassName());
                        if (r1_StringBuilder.charAt(0) == '.') {
                            r1_StringBuilder.deleteCharAt(0);
                        }
                    }
                    c.a("stat", "new page view, page name = " + r1_StringBuilder.toString() + ",stay time = " + r2j + "(ms)");
                    this.a.j.a(r1_StringBuilder.toString(), r2j, this.f);
                    this.a.c((Context) this.c.get(), this.b);
                }
            }
        } else if (this.j == 2) {
            if (this.d.get() != this.h.get()) {
                r0_ObjectA = new Object[2];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = " Fragment onPause() \u6216 onResume()\u5b89\u653e\u9519\u8bef||onPause() or onResume() install error.";
                c.c(r0_ObjectA);
            } else {
                r2j = this.b - this.f;
                Fragment r0_Fragment = (Fragment) this.d.get();
                if (r0_Fragment == null) {
                    r0_ObjectA = new Object[2];
                    r0_ObjectA[0] = "stat";
                    r0_ObjectA[1] = "onPause,WeakReference is already been released";
                    c.c(r0_ObjectA);
                } else {
                    r0_String = r0_Fragment.getClass().getName().toString();
                    r1_String = r0_String.substring(r0_String.lastIndexOf(".") + 1);
                    c.a("stat", "Fragment new page view, page name = " + r0_String.toString() + ",stay time = " + r2j + "(ms)");
                    this.a.j.a(r1_String, r2j, this.f);
                    this.a.c(((Fragment) this.d.get()).getActivity(), this.b);
                }
            }
        } else {
            if (this.j == 3) {
                if (this.e.get() != this.i.get()) {
                    r0_ObjectA = new Object[2];
                    r0_ObjectA[0] = "stat";
                    r0_ObjectA[1] = " Fragment onPause() \u6216 onResume()\u5b89\u653e\u9519\u8bef||onPause() or onResume() install error.";
                    c.c(r0_ObjectA);
                } else {
                    r2j = this.b - this.f;
                    Object r0_Object = this.e.get();
                    if (r0_Object == null) {
                        r0_ObjectA = new Object[2];
                        r0_ObjectA[0] = "stat";
                        r0_ObjectA[1] = "onPause,WeakReference is already been released";
                        c.c(r0_ObjectA);
                    } else {
                        Context r6_Context = q.a(r0_Object);
                        r0_String = r0_Object.getClass().getName().toString();
                        r1_String = r0_String.substring(r0_String.lastIndexOf(".") + 1);
                        c.a("stat", "android.app.Fragment new page view, page name = " + r0_String.toString() + ",stay time = " + r2j + "(ms)");
                        this.a.j.a(r1_String, r2j, this.f);
                        this.a.c(r6_Context, this.b);
                    }
                }
            }
        }
    }
}