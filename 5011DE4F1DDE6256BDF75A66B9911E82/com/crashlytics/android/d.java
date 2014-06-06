package com.crashlytics.android;

import com.crashlytics.android.internal.aa;
import java.io.File;

// compiled from: SourceFile
final class d extends aa {
    private /* synthetic */ File a;

    d(ba r1_ba, File r2_File) {
        this.a = r2_File;
    }

    public final void a() {
        v r0_v = Crashlytics.getInstance().q();
        if (r0_v != null) {
            new ab(r0_v).a(new z(this.a, ba.j()));
        }
    }
}