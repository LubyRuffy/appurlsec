package com.crashlytics.android;

import com.crashlytics.android.internal.aU;
import com.crashlytics.android.internal.aX;

// compiled from: SourceFile
final class aq implements aU<Boolean> {
    private /* synthetic */ Crashlytics a;

    aq(Crashlytics r1_Crashlytics) {
        this.a = r1_Crashlytics;
    }

    public final /* synthetic */ Object a(aX r3_aX) {
        boolean r0z = false;
        if (!(r3_aX.d.a)) {
            return Boolean.valueOf(r0z);
        }
        Crashlytics r1_Crashlytics = this.a;
        if (!Crashlytics.k()) {
            r0z = true;
        }
        return Boolean.valueOf(r0z);
    }
}