package com.crashlytics.android;

import com.crashlytics.android.internal.au;
import java.io.IOException;
import java.io.InputStream;

// compiled from: SourceFile
final class bd implements au {
    private /* synthetic */ byte[] a;
    private /* synthetic */ int[] b;

    bd(ba r1_ba, byte[] r2_byteA, int[] r3_intA) {
        this.a = r2_byteA;
        this.b = r3_intA;
    }

    public final void a(InputStream r4_InputStream, int r5i) throws IOException {
        r4_InputStream.read(this.a, this.b[0], r5i);
        int[] r0_intA = this.b;
        r0_intA[0] = r0_intA[0] + r5i;
        r4_InputStream.close();
    }
}