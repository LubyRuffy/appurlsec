package com.crashlytics.android;

import java.util.Date;
import java.util.concurrent.Callable;

// compiled from: SourceFile
final class n implements Callable<Void> {
    private /* synthetic */ Date a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ ba d;

    n(ba r1_ba, Date r2_Date, Thread r3_Thread, Throwable r4_Throwable) {
        this.d = r1_ba;
        this.a = r2_Date;
        this.b = r3_Thread;
        this.c = r4_Throwable;
    }

    public final /* synthetic */ Object call() throws Exception {
        ba.a(this.d, this.a, this.b, this.c);
        return null;
    }
}