package com.tencent.mm.sdk.platformtools;

import android.os.Handler;

public abstract class SyncTask<R> {
    private R a;
    private Object b;
    private final long c;
    private long d;
    private long e;
    private Runnable f;

    public SyncTask() {
        this(0, null);
    }

    public SyncTask(long r2j, R r4_R) {
        this.b = new Object();
        this.f = new m(this);
        this.c = r2j;
        this.a = r4_R;
    }

    protected abstract R a();

    public R exec(Handler r9_Handler) {
        if (r9_Handler == null) {
            Log.d("MicroMsg.SDK.SyncTask", "null handler, task in exec thread, return now");
            return a();
        } else if (Thread.currentThread().getId() == r9_Handler.getLooper().getThread().getId()) {
            Log.d("MicroMsg.SDK.SyncTask", "same tid, task in exec thread, return now");
            return a();
        } else {
            this.d = Util.currentTicks();
            r9_Handler.post(this.f);
            try {
                synchronized (this.b) {
                    this.b.wait(this.c);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long r0j = Util.ticksToNow(this.d);
            Object[] r4_ObjectA = new Object[4];
            r4_ObjectA[0] = this.a;
            r4_ObjectA[1] = Long.valueOf(r0j);
            r4_ObjectA[2] = Long.valueOf(this.e);
            r4_ObjectA[3] = Long.valueOf(r0j - this.e);
            Log.v("MicroMsg.SDK.SyncTask", "sync task done, return=%s, cost=%d(wait=%d, run=%d)", r4_ObjectA);
            return this.a;
        }
    }

    public void setResult(R r3_R) {
        this.a = r3_R;
        synchronized (this.b) {
            this.b.notify();
        }
    }
}