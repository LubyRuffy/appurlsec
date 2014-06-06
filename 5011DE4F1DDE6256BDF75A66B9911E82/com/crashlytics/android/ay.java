package com.crashlytics.android;

import android.content.Context;
import com.crashlytics.android.internal.aa;
import com.crashlytics.android.internal.v;
import java.util.concurrent.CountDownLatch;

// compiled from: SourceFile
final class ay extends aa {
    private /* synthetic */ Context a;
    private /* synthetic */ float b;
    private /* synthetic */ CountDownLatch c;
    private /* synthetic */ Crashlytics d;

    ay(Crashlytics r1_Crashlytics, Context r2_Context, float r3f, CountDownLatch r4_CountDownLatch) {
        this.d = r1_Crashlytics;
        this.a = r2_Context;
        this.b = r3f;
        this.c = r4_CountDownLatch;
    }

    public final void a() {
        try {
            if (this.d.a(this.a, this.b)) {
                this.d.d.e();
            }
            this.c.countDown();
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Problem encountered during Crashlytics initialization.", e);
            this.c.countDown();
        }
    }
}