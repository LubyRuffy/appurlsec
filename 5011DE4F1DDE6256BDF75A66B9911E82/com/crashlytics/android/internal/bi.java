package com.crashlytics.android.internal;

// compiled from: SourceFile
final class bi implements Runnable {
    private final o a;
    private final be b;

    public bi(o r1_o, be r2_be) {
        this.a = r1_o;
        this.b = r2_be;
    }

    public final void run() {
        try {
            ab.c("Performing time based analytics file roll over.");
            if (!this.a.a()) {
                this.b.c();
            }
        } catch (Exception e) {
            ab.d("Crashlytics failed to roll over session analytics file");
        }
    }
}