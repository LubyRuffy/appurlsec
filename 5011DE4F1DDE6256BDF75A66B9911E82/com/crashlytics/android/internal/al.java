package com.crashlytics.android.internal;

import java.util.Collections;

// compiled from: SourceFile
final class al implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ ac b;

    al(ac r1_ac, String r2_String) {
        this.b = r1_ac;
        this.a = r2_String;
    }

    public final void run() {
        try {
            this.b.a.a(bf.a(this.b.b, this.b.i, this.b.c, this.b.d, this.b.e, this.b.f, this.b.g, this.b.h, bg.i, Collections.singletonMap("sessionId", this.a)));
        } catch (Exception e) {
            ab.d("Crashlytics failed to record crash event");
        }
    }
}