package com.crashlytics.android.internal;

// compiled from: SourceFile
final class bb implements Runnable {
    private /* synthetic */ aK a;
    private /* synthetic */ String b;
    private /* synthetic */ ac c;

    bb(ac r1_ac, aK r2_aK, String r3_String) {
        this.c = r1_ac;
        this.a = r2_aK;
        this.b = r3_String;
    }

    public final void run() {
        try {
            this.c.a.a(this.a, this.b);
        } catch (Exception e) {
            ab.d("Crashlytics failed to set analytics settings data.");
        }
    }
}