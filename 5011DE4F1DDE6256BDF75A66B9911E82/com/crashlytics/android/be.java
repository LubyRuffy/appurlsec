package com.crashlytics.android;

import java.util.concurrent.Callable;

// compiled from: SourceFile
final class be implements Callable<Void> {
    private /* synthetic */ ba a;

    be(ba r1_ba) {
        this.a = r1_ba;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (!this.a.g()) {
            ba.c(this.a);
        }
        return null;
    }
}