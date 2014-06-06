package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;

// compiled from: SourceFile
public class ei implements Runnable {
    final /* synthetic */ TSocket a;
    private final /* synthetic */ cj b;
    private final /* synthetic */ int c;

    public ei(TSocket r1_TSocket, cj r2_cj, int r3i) {
        this.a = r1_TSocket;
        this.b = r2_cj;
        this.c = r3i;
    }

    public void run() {
        new dl().a(this.b);
        cf.a(GlobalContext.getContext()).a(this.b, -this.c);
    }
}