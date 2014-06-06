package com.baidu.mobstat;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.baidu.mobstat.a.c;
import java.lang.ref.WeakReference;

class w implements Runnable {
    final /* synthetic */ q a;
    private long b;
    private long c;
    private WeakReference<Context> d;
    private WeakReference<Fragment> e;
    private WeakReference<Object> f;
    private int g;

    public w(q r2_q, long r3j, long r5j, Context r7_Context, Fragment r8_Fragment, Object r9_Object, int r10i) {
        this.a = r2_q;
        this.g = 1;
        this.b = r3j;
        this.c = r5j;
        this.d = new WeakReference(r7_Context);
        this.e = new WeakReference(r8_Fragment);
        this.f = new WeakReference(r9_Object);
        this.g = r10i;
    }

    public void run() {
        if (this.c - this.b < ((long) this.a.a()) || this.b <= 0) {
        } else if (!(this.d.get() == null && this.e.get() == null && this.f.get() == null)) {
            q.a(this.a).b(this.b);
            String r0_String = q.a(this.a).c().toString();
            c.a("stat", "new session:" + r0_String);
            DataCore.getInstance().putSession(r0_String);
            Context r1_Context = null;
            Context r0_Context;
            if (this.g == 1) {
                r0_Context = (Context) this.d.get();
                DataCore.getInstance().flush(r0_Context);
                r1_Context = r0_Context;
            } else if (this.g == 2) {
                DataCore.getInstance().flush(((Fragment) this.e.get()).getActivity());
            } else if (this.g == 3) {
                r0_Context = q.a(this.f.get());
                DataCore.getInstance().flush(r0_Context);
                r1_Context = r0_Context;
            }
            q.a(this.a).b();
            if (this.g == 1) {
                q.a(this.a, (Context) this.d.get());
            } else if (this.g == 2) {
                q.a(this.a, ((Fragment) this.e.get()).getActivity());
            } else {
                q.a(this.a, r1_Context);
            }
            k.a().b(r1_Context);
        }
    }
}