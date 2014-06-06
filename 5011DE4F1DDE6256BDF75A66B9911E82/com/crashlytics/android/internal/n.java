package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// compiled from: SourceFile
final class n implements be {
    private final ScheduledExecutorService a;
    private final o b;
    private final av c;
    private ScheduledFuture<?> d;
    private int e;
    private y f;

    public n(ScheduledExecutorService r2_ScheduledExecutorService, o r3_o, av r4_av) {
        this.e = -1;
        this.a = r2_ScheduledExecutorService;
        this.b = r3_o;
        this.c = r4_av;
    }

    private void a(int r8i, int r9i) {
        try {
            Runnable r1_Runnable = new bi(this.b, this);
            ab.c(new StringBuilder("Scheduling time based file roll over every ").append(r9i).append(" seconds").toString());
            this.d = this.a.scheduleAtFixedRate(r1_Runnable, (long) r8i, (long) r9i, TimeUnit.SECONDS);
        } catch (RejectedExecutionException e) {
            ab.d("Crashlytics failed to schedule time based analytics file roll over");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
        r8_this = this;
        r1 = 0;
        r0 = r8.f;
        if (r0 != 0) goto L_0x000b;
    L_0x0005:
        r0 = "skipping analytics files send because we don't yet know the target endpoint";
        com.crashlytics.android.internal.ab.c(r0);
    L_0x000a:
        return;
    L_0x000b:
        r0 = "Sending all analytics files";
        com.crashlytics.android.internal.ab.c(r0);
        r0 = r8.b;
        r0 = r0.b();
        r2 = r0;
        r0 = r1;
    L_0x0018:
        r1 = r2.size();	 //Catch:{ Exception -> 0x006b }
        if (r1 <= 0) goto L_0x0086;
    L_0x001e:
        r1 = r8.f;	 //Catch:{ Exception -> 0x006b }
        r3 = com.crashlytics.android.internal.D.a();	 //Catch:{ Exception -> 0x006b }
        r3 = r3.getContext();	 //Catch:{ Exception -> 0x006b }
        r4 = 0;
        r3 = com.crashlytics.android.internal.r.a(r3, r4);	 //Catch:{ Exception -> 0x006b }
        r3 = r1.a(r3, r2);	 //Catch:{ Exception -> 0x006b }
        if (r3 == 0) goto L_0x003e;
    L_0x0033:
        r1 = r2.size();	 //Catch:{ Exception -> 0x006b }
        r1 = r1 + r0;
        r0 = r8.b;	 //Catch:{ Exception -> 0x008f }
        r0.a(r2);	 //Catch:{ Exception -> 0x008f }
        r0 = r1;
    L_0x003e:
        r4 = java.util.Locale.US;	 //Catch:{ Exception -> 0x006b }
        r5 = "attempt to send batch of %d analytics files %s";
        r1 = 2;
        r6 = new java.lang.Object[r1];	 //Catch:{ Exception -> 0x006b }
        r1 = 0;
        r2 = r2.size();	 //Catch:{ Exception -> 0x006b }
        r2 = java.lang.Integer.valueOf(r2);	 //Catch:{ Exception -> 0x006b }
        r6[r1] = r2;	 //Catch:{ Exception -> 0x006b }
        r2 = 1;
        if (r3 == 0) goto L_0x0068;
    L_0x0053:
        r1 = "succeeded";
    L_0x0055:
        r6[r2] = r1;	 //Catch:{ Exception -> 0x006b }
        r1 = java.lang.String.format(r4, r5, r6);	 //Catch:{ Exception -> 0x006b }
        com.crashlytics.android.internal.ab.c(r1);	 //Catch:{ Exception -> 0x006b }
        if (r3 == 0) goto L_0x0086;
    L_0x0060:
        r1 = r8.b;	 //Catch:{ Exception -> 0x006b }
        r1 = r1.b();	 //Catch:{ Exception -> 0x006b }
        r2 = r1;
        goto L_0x0018;
    L_0x0068:
        r1 = "did not succeed";
        goto L_0x0055;
    L_0x006b:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x006f:
        r2 = new java.lang.StringBuilder;
        r3 = "Crashlytics failed to send batch of analytics files to server: ";
        r2.<init>(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.crashlytics.android.internal.ab.d(r0);
        r0 = r1;
    L_0x0086:
        if (r0 != 0) goto L_0x000a;
    L_0x0088:
        r0 = r8.b;
        r0.d();
        goto L_0x000a;
    L_0x008f:
        r0 = move-exception;
        goto L_0x006f;
        */

    }

    public final void a(aK r4_aK, String r5_String) {
        this.f = new i(r5_String, r4_aK.a, this.c);
        this.b.a(r4_aK);
        this.e = r4_aK.b;
        a(0, this.e);
    }

    public final void a(bf r5_bf) {
        int r0i;
        int r1i = 1;
        ab.c(r5_bf.toString());
        try {
            this.b.a(r5_bf);
        } catch (IOException e) {
            ab.d("Crashlytics failed to write session event.");
        }
        r0i = this.e != -1 ? 1 : 0;
        if (this.d == null) {
            if (r0i == 0 || r1i == 0) {
            } else {
                a(this.e, this.e);
            }
        } else {
            r1i = 0;
            if (r0i == 0 || r1i == 0) {
            } else {
                a(this.e, this.e);
            }
        }
    }

    public final void b() {
        this.b.c();
    }

    public final void c() {
        if (this.d != null) {
            ab.c("Cancelling time-based rollover because no events are currently being generated.");
            this.d.cancel(false);
            this.d = null;
        }
    }

    public final void d() {
        try {
            this.b.a();
        } catch (IOException e) {
            ab.d("Crashlytics failed to roll analytics file over.");
        }
    }
}