package com.google.analytics.tracking.android;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionReporter implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler a;
    private final Tracker b;
    private final ServiceManager c;
    private ExceptionParser d;

    public ExceptionReporter(Tracker r3_Tracker, ServiceManager r4_ServiceManager, UncaughtExceptionHandler r5_UncaughtExceptionHandler) {
        if (r3_Tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (r4_ServiceManager == null) {
            throw new NullPointerException("serviceManager cannot be null");
        } else {
            this.a = r5_UncaughtExceptionHandler;
            this.b = r3_Tracker;
            this.c = r4_ServiceManager;
            z.e("ExceptionReporter created, original handler is " + (r5_UncaughtExceptionHandler == null ? "null" : r5_UncaughtExceptionHandler.getClass().getName()));
        }
    }

    public ExceptionParser getExceptionParser() {
        return this.d;
    }

    public void setExceptionParser(ExceptionParser r1_ExceptionParser) {
        this.d = r1_ExceptionParser;
    }

    public void uncaughtException(Thread r4_Thread, Throwable r5_Throwable) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (this.d == null) {
            r0_String = r5_Throwable.getMessage();
        } else {
            r0_String = this.d.getDescription(r4_Thread != null ? r4_Thread.getName() : null, r5_Throwable);
        }
        z.e("Tracking Exception: " + r0_String);
        this.b.trackException(r0_String, true);
        this.c.dispatch();
        if (this.a != null) {
            z.e("Passing exception to original handler.");
            this.a.uncaughtException(r4_Thread, r5_Throwable);
        }
    }
}