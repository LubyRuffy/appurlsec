package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.report.TSocketRecvStatistics;

// compiled from: SourceFile
public class dg implements Runnable {
    final /* synthetic */ TSocketRecvStatistics a;
    private String b;
    private int c;
    private long d;

    public dg(TSocketRecvStatistics r1_TSocketRecvStatistics, String r2_String, int r3i, long r4j) {
        this.a = r1_TSocketRecvStatistics;
        this.b = r2_String;
        this.c = r3i;
        this.d = r4j;
    }

    public void run() {
        this.a.a(this.b, this.c, this.d);
    }
}