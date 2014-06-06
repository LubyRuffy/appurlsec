package com.google.analytics.tracking.android;

import android.content.Context;
import android.os.Handler;

public class GAServiceManager implements ServiceManager {
    private static final Object a;
    private static GAServiceManager j;
    private Context b;
    private d c;
    private volatile AnalyticsThread d;
    private int e;
    private boolean f;
    private e g;
    private Handler h;
    private boolean i;

    static {
        a = new Object();
    }

    private GAServiceManager() {
        this.e = 1800;
        this.f = true;
        this.g = new j(this);
        this.i = false;
    }

    private void c() {
        this.h = new Handler(this.b.getMainLooper(), new k(this));
        if (this.e > 0) {
            this.h.sendMessageDelayed(this.h.obtainMessage(1, a), (long) (this.e * 1000));
        }
    }

    public static GAServiceManager getInstance() {
        if (j == null) {
            j = new GAServiceManager();
        }
        return j;
    }

    synchronized d a() {
        if (this.c == null) {
            if (this.b == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            } else {
                this.c = new ag(this.g, this.b);
            }
        }
        if (this.h == null) {
            c();
        }
        return this.c;
    }

    synchronized void a(Context r2_Context, AnalyticsThread r3_AnalyticsThread) {
        if (this.b != null) {
        } else {
            this.b = r2_Context.getApplicationContext();
            if (this.d == null) {
                this.d = r3_AnalyticsThread;
                if (this.f) {
                    r3_AnalyticsThread.dispatch();
                }
            }
        }
    }

    synchronized void a(boolean r5z) {
        if (this.i == r5z) {
        } else {
            if (r5z) {
                if (this.e > 0) {
                    this.h.removeMessages(1, a);
                }
            }
            if (r5z || this.e <= 0) {
                z.e("PowerSaveMode " + (r5z ? "terminated." : "initiated."));
                this.i = r5z;
            } else {
                this.h.sendMessageDelayed(this.h.obtainMessage(1, a), (long) (this.e * 1000));
                if (r5z) {
                }
                z.e("PowerSaveMode " + (r5z ? "terminated." : "initiated."));
                this.i = r5z;
            }
        }
    }

    public synchronized void dispatch() {
        if (this.d == null) {
            z.h("dispatch call queued.  Need to call GAServiceManager.getInstance().initializea().");
            this.f = true;
        } else {
            this.d.dispatch();
        }
    }

    public synchronized void setDispatchPeriod(int r5i) {
        if (this.h == null) {
            z.h("Need to call initializea() and be in fallback mode to start dispatch.");
            this.e = r5i;
        } else if (this.i || this.e <= 0) {
            this.e = r5i;
            if (r5i <= 0 || this.i) {
            } else {
                this.h.sendMessageDelayed(this.h.obtainMessage(1, a), (long) (r5i * 1000));
            }
        } else {
            this.h.removeMessages(1, a);
            this.e = r5i;
            if (r5i <= 0 || this.i) {
            } else {
                this.h.sendMessageDelayed(this.h.obtainMessage(1, a), (long) (r5i * 1000));
            }
        }
    }
}