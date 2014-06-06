package com.crashlytics.android;

import java.util.concurrent.Callable;

// compiled from: SourceFile
final class c implements Callable<Boolean> {
    private /* synthetic */ ba a;

    c(ba r1_ba) {
        this.a = r1_ba;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Boolean.valueOf(ba.f(this.a).exists());
    }
}