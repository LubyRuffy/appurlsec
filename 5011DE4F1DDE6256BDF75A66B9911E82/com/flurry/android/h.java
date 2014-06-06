package com.flurry.android;

import android.os.Handler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
final class h {
    private List a;
    private Handler b;
    private Handler c;
    private int d;
    private Runnable e;

    h(Handler r2_Handler, int r3i) {
        this.a = new ArrayList();
        this.b = r2_Handler;
        this.c = new Handler();
        this.d = r3i;
        this.e = new w(this);
        b();
    }

    private synchronized void a() {
        List r1_List = new ArrayList();
        Iterator r2_Iterator = this.a.iterator();
        while (r2_Iterator.hasNext()) {
            aa r0_aa = (aa) ((WeakReference) r2_Iterator.next()).get();
            if (r0_aa != null) {
                r1_List.add(r0_aa);
            }
        }
        this.c.post(new v(r1_List));
        b();
    }

    private synchronized void b() {
        Iterator r1_Iterator = this.a.iterator();
        while (r1_Iterator.hasNext()) {
            if (((WeakReference) r1_Iterator.next()).get() == null) {
                r1_Iterator.remove();
            }
        }
        this.b.removeCallbacks(this.e);
        this.b.postDelayed(this.e, (long) this.d);
    }

    final synchronized void a(aa r3_aa) {
        r3_aa.a();
        this.a.add(new WeakReference(r3_aa));
    }
}