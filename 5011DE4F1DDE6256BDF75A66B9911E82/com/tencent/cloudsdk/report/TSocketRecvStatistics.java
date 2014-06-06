package com.tencent.cloudsdk.report;

import com.tencent.cloudsdk.a;
import com.tencent.cloudsdk.d;

// compiled from: SourceFile
public class TSocketRecvStatistics {
    public static final int ERR_CODE_FAIL = -1;
    public static final int ERR_CODE_SUCC = 0;
    private static final String a;
    private d b;

    static {
        a = TSocketRecvStatistics.class.getName();
    }

    public TSocketRecvStatistics() {
        int r1i = 0;
        this.b = new d();
        if (this.b != null) {
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.report.TSocketRecvStatistics", "com.tencent.cloudsdk.defaultsdk.mna.report.TSocketRecvStatistics", new Class[r1i], new Object[r1i]);
        }
    }

    public void report(String r7_String, int r8i, long r9j) {
        int r3i = 1;
        int r2i = 0;
        if (this.b != null) {
            Class[] r0_ClassA = new Class[3];
            r0_ClassA[r2i] = String.class;
            r0_ClassA[r3i] = Integer.TYPE;
            r0_ClassA[2] = Long.TYPE;
            Object[] r1_ObjectA = new Object[3];
            r1_ObjectA[r2i] = r7_String;
            r1_ObjectA[r3i] = Integer.valueOf(r8i);
            r1_ObjectA[2] = Long.valueOf(r9j);
            this.b.a("report", r0_ClassA, r1_ObjectA);
        }
    }

    public void timeEnd() {
        int r1i = 0;
        if (this.b != null) {
            this.b.a("timeEnd", new Class[r1i], new Object[r1i]);
        }
    }

    public void timeStart() {
        int r1i = 0;
        if (this.b != null) {
            this.b.a("timeStart", new Class[r1i], new Object[r1i]);
        }
    }
}