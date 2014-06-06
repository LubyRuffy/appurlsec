package com.crashlytics.android.internal;

// compiled from: SourceFile
final class bd implements Runnable {
    private /* synthetic */ ac a;

    bd(ac r1_ac) {
        this.a = r1_ac;
    }

    public final void run() {
        try {
            be r0_be = this.a.a;
            this.a.a = new j();
            r0_be.b();
        } catch (Exception e) {
            ab.d("Crashlytics failed to disable analytics.");
        }
    }
}