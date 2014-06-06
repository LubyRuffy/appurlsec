package com.crashlytics.android;

import java.util.concurrent.Callable;

// compiled from: SourceFile
final class o implements Callable<Boolean> {
    private /* synthetic */ ba a;

    o(ba r1_ba) {
        this.a = r1_ba;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (ba.a(this.a).get()) {
            return Boolean.valueOf(false);
        }
        ba.b(this.a);
        ba.c(this.a);
        return Boolean.valueOf(true);
    }
}