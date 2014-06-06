package com.crashlytics.android;

import android.app.Activity;
import com.crashlytics.android.internal.aU;
import com.crashlytics.android.internal.aX;
import com.crashlytics.android.internal.v;

// compiled from: SourceFile
final class ar implements aU<Boolean> {
    private /* synthetic */ Crashlytics a;

    ar(Crashlytics r1_Crashlytics) {
        this.a = r1_Crashlytics;
    }

    public final /* synthetic */ Object a(aX r4_aX) {
        boolean r0z = true;
        Activity r1_Activity = v.a().e();
        if (r1_Activity == null || r1_Activity.isFinishing() || (!this.a.j())) {
            return Boolean.valueOf(r0z);
        }
        r0z = Crashlytics.a(this.a, r1_Activity, r4_aX.c);
        return Boolean.valueOf(r0z);
    }
}