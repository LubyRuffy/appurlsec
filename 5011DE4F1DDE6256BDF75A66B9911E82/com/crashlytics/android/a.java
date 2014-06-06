package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.util.concurrent.Callable;

// compiled from: SourceFile
final class a implements Callable<Void> {
    private /* synthetic */ ba a;

    a(ba r1_ba) {
        this.a = r1_ba;
    }

    public final /* synthetic */ Object call() throws Exception {
        ba.f(this.a).createNewFile();
        v.a().b().a(Crashlytics.TAG, "Initialization marker file created.");
        return null;
    }
}