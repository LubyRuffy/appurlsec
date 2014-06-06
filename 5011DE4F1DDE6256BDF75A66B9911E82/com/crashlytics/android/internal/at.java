package com.crashlytics.android.internal;

// compiled from: SourceFile
final class at implements Runnable {
    private /* synthetic */ bf a;
    private /* synthetic */ boolean b;
    private /* synthetic */ ac c;

    at(ac r1_ac, bf r2_bf, boolean r3z) {
        this.c = r1_ac;
        this.a = r2_bf;
        this.b = r3z;
    }

    public final void run() {
        try {
            this.c.a.a(this.a);
            if (this.b) {
                this.c.a.d();
            }
        } catch (Exception e) {
            ab.d("Crashlytics failed to record session event.");
        }
    }
}