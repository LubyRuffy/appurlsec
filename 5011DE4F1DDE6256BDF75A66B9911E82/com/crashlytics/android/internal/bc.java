package com.crashlytics.android.internal;

// compiled from: SourceFile
final class bc implements Runnable {
    private /* synthetic */ ac a;

    bc(ac r1_ac) {
        this.a = r1_ac;
    }

    public final void run() {
        try {
            this.a.a.a();
        } catch (Exception e) {
            ab.d("Crashlytics failed to send analytics files.");
        }
    }
}