package com.crashlytics.android;

import java.util.concurrent.CountDownLatch;

// compiled from: SourceFile
final class az {
    private boolean a;
    private final CountDownLatch b;

    private az(Crashlytics r3_Crashlytics) {
        this.a = false;
        this.b = new CountDownLatch(1);
    }

    final void a(boolean r2z) {
        this.a = r2z;
        this.b.countDown();
    }

    final boolean a() {
        return this.a;
    }

    final void b() {
        try {
            this.b.await();
        } catch (InterruptedException e) {
        }
    }
}