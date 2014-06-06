package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.report.HttpStatistics;

// compiled from: SourceFile
public class df implements Runnable {
    final /* synthetic */ HttpStatistics a;
    private String b;
    private int c;
    private long d;

    public df(HttpStatistics r1_HttpStatistics, String r2_String, int r3i, long r4j) {
        this.a = r1_HttpStatistics;
        this.b = r2_String;
        this.c = r3i;
        this.d = r4j;
    }

    public void run() {
        this.a.a(this.b, this.c, this.d);
    }
}