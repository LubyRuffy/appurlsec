package com.crashlytics.android.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

// compiled from: SourceFile
public class ah {
    public static ExecutorService a(String r1_String) {
        ExecutorService r0_ExecutorService = Executors.newSingleThreadExecutor(c(r1_String));
        a(r1_String, r0_ExecutorService);
        return r0_ExecutorService;
    }

    private static void a(String r8_String, ExecutorService r9_ExecutorService) {
        Runtime.getRuntime().addShutdownHook(new Thread(new bu(r8_String, r9_ExecutorService, 2, TimeUnit.SECONDS), new StringBuilder("Crashlytics Shutdown Hook for ").append(r8_String).toString()));
    }

    public static ScheduledExecutorService b(String r1_String) {
        Object r0_Object = Executors.newSingleThreadScheduledExecutor(c(r1_String));
        a(r1_String, r0_Object);
        return r0_Object;
    }

    public static ThreadFactory c(String r3_String) {
        return new bs(r3_String, new AtomicLong(1));
    }

    public long a() {
        return System.currentTimeMillis();
    }
}