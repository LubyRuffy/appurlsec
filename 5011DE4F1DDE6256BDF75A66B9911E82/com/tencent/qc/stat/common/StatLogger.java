package com.tencent.qc.stat.common;

import android.util.Log;
import com.crashlytics.android.internal.b;
import com.tencent.qc.stat.StatConfig;

// compiled from: ProGuard
public final class StatLogger {
    private String a;
    private boolean b;
    private int c;

    public StatLogger() {
        this.a = b.a;
        this.b = true;
        this.c = 2;
    }

    public StatLogger(String r2_String) {
        this.a = b.a;
        this.b = true;
        this.c = 2;
        this.a = r2_String;
    }

    private String b() {
        StackTraceElement[] r2_StackTraceElementA = Thread.currentThread().getStackTrace();
        if (r2_StackTraceElementA == null) {
            return null;
        }
        int r3i = r2_StackTraceElementA.length;
        int r1i = 0;
        while (r1i < r3i) {
            StackTraceElement r4_StackTraceElement = r2_StackTraceElementA[r1i];
            if (!r4_StackTraceElement.isNativeMethod() && !r4_StackTraceElement.getClassName().equals(Thread.class.getName()) && !r4_StackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + r4_StackTraceElement.getFileName() + ":" + r4_StackTraceElement.getLineNumber() + "]";
            }
            r1i++;
        }
        return null;
    }

    public void a(Exception r8_Exception) {
        if (this.c <= 6) {
            StringBuffer r1_StringBuffer = new StringBuffer();
            String r0_String = b();
            StackTraceElement[] r2_StackTraceElementA = r8_Exception.getStackTrace();
            if (r0_String != null) {
                r1_StringBuffer.append(r0_String + " - " + r8_Exception + "\r\n");
            } else {
                r1_StringBuffer.append(r8_Exception + "\r\n");
            }
            if (r2_StackTraceElementA == null || r2_StackTraceElementA.length <= 0) {
                Log.e(this.a, r1_StringBuffer.toString());
            } else {
                int r3i = r2_StackTraceElementA.length;
                int r0i = 0;
                while (r0i < r3i) {
                    StackTraceElement r4_StackTraceElement = r2_StackTraceElementA[r0i];
                    if (r4_StackTraceElement != null) {
                        r1_StringBuffer.append("[ " + r4_StackTraceElement.getFileName() + ":" + r4_StackTraceElement.getLineNumber() + " ]\r\n");
                    }
                    r0i++;
                }
                Log.e(this.a, r1_StringBuffer.toString());
            }
        }
    }

    public void a(Object r3_Object) {
        if (this.c <= 4) {
            String r0_String = b();
            Log.i(this.a, r0_String == null ? r3_Object.toString() : r0_String + " - " + r3_Object);
        }
    }

    public void a(boolean r1z) {
        this.b = r1z;
    }

    public boolean a() {
        return this.b;
    }

    public void b(Exception r2_Exception) {
        if (StatConfig.b()) {
            a(r2_Exception);
        }
    }

    public void b(Object r2_Object) {
        if (a()) {
            a(r2_Object);
        }
    }

    public void c(Object r3_Object) {
        if (this.c <= 5) {
            String r0_String = b();
            Log.w(this.a, r0_String == null ? r3_Object.toString() : r0_String + " - " + r3_Object);
        }
    }

    public void d(Object r2_Object) {
        if (a()) {
            c(r2_Object);
        }
    }

    public void e(Object r3_Object) {
        if (this.c <= 6) {
            String r0_String = b();
            Log.e(this.a, r0_String == null ? r3_Object.toString() : r0_String + " - " + r3_Object);
        }
    }

    public void f(Object r2_Object) {
        if (a()) {
            e(r2_Object);
        }
    }

    public void g(Object r3_Object) {
        if (this.c <= 3) {
            String r0_String = b();
            Log.d(this.a, r0_String == null ? r3_Object.toString() : r0_String + " - " + r3_Object);
        }
    }

    public void h(Object r2_Object) {
        if (a()) {
            g(r2_Object);
        }
    }
}