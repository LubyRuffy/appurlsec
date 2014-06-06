package com.crashlytics.android.internal;

import java.util.concurrent.ConcurrentLinkedQueue;

// compiled from: SourceFile
final class ca extends ThreadLocal<ConcurrentLinkedQueue<cc>> {
    ca(b r1_b) {
    }

    protected final /* synthetic */ Object initialValue() {
        return new ConcurrentLinkedQueue();
    }
}