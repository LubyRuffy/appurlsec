package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.util.concurrent.Callable;

// compiled from: SourceFile
final class b implements Callable<Boolean> {
    private /* synthetic */ ba a;

    b(ba r1_ba) {
        this.a = r1_ba;
    }

    private Boolean a() throws Exception {
        try {
            boolean r0z = ba.f(this.a).delete();
            v.a().b().a(Crashlytics.TAG, new StringBuilder("Initialization marker file removed: ").append(r0z).toString());
            return Boolean.valueOf(r0z);
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Problem encountered deleting Crashlytics initialization marker.", e);
            return Boolean.valueOf(false);
        }
    }

    public final /* synthetic */ Object call() throws Exception {
        return a();
    }
}