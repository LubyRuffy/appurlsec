package com.crashlytics.android.internal;

import java.io.IOException;
import java.io.InputStream;

// compiled from: SourceFile
final class bv implements au {
    private boolean a;
    private /* synthetic */ StringBuilder b;

    bv(aq r2_aq, StringBuilder r3_StringBuilder) {
        this.b = r3_StringBuilder;
        this.a = true;
    }

    public final void a(InputStream r3_InputStream, int r4i) throws IOException {
        if (this.a) {
            this.a = false;
        } else {
            this.b.append(", ");
        }
        this.b.append(r4i);
    }
}