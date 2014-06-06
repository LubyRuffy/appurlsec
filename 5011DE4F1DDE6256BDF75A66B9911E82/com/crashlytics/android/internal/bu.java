package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

// compiled from: SourceFile
final class bu extends aa {
    private /* synthetic */ String a;
    private /* synthetic */ ExecutorService b;
    private /* synthetic */ long c;
    private /* synthetic */ TimeUnit d;

    bu(String r1_String, ExecutorService r2_ExecutorService, long r3j, TimeUnit r5_TimeUnit) {
        this.a = r1_String;
        this.b = r2_ExecutorService;
        this.c = r3j;
        this.d = r5_TimeUnit;
    }

    public final void a() {
        try {
            v.a().b().a(Crashlytics.TAG, new StringBuilder("Executing shutdown hook for ").append(this.a).toString());
            this.b.shutdown();
            if (!this.b.awaitTermination(this.c, this.d)) {
                v.a().b().a(Crashlytics.TAG, this.a + " did not shut down in the allocated time. Requesting immediate shutdown.");
                this.b.shutdownNow();
            }
        } catch (InterruptedException e) {
            q r0_q = v.a().b();
            String r1_String = Crashlytics.TAG;
            Locale r2_Locale = Locale.US;
            Object[] r4_ObjectA = new Object[1];
            r4_ObjectA[0] = this.a;
            r0_q.a(r1_String, String.format(r2_Locale, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", r4_ObjectA));
            this.b.shutdownNow();
        }
    }
}