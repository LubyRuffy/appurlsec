package com.crashlytics.android;

import java.util.Date;

// compiled from: SourceFile
final class p implements Runnable {
    private /* synthetic */ Date a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ ba d;

    p(ba r1_ba, Date r2_Date, Thread r3_Thread, Throwable r4_Throwable) {
        this.d = r1_ba;
        this.a = r2_Date;
        this.b = r3_Thread;
        this.c = r4_Throwable;
    }

    public final void run() {
        if (!ba.a(this.d).get()) {
            ba.b(this.d, this.a, this.b, this.c);
        }
    }
}