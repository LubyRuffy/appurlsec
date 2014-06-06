package com.crashlytics.android.internal;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

// compiled from: SourceFile
final class bs implements ThreadFactory {
    private /* synthetic */ String a;
    private /* synthetic */ AtomicLong b;

    bs(String r1_String, AtomicLong r2_AtomicLong) {
        this.a = r1_String;
        this.b = r2_AtomicLong;
    }

    public final Thread newThread(Runnable r8_Runnable) {
        Thread r0_Thread = Executors.defaultThreadFactory().newThread(new bt(this, r8_Runnable));
        Locale r1_Locale = Locale.US;
        String r2_String = this.a;
        Object[] r3_ObjectA = new Object[1];
        r3_ObjectA[0] = Long.valueOf(this.b.getAndIncrement());
        r0_Thread.setName(String.format(r1_Locale, r2_String, r3_ObjectA));
        return r0_Thread;
    }
}