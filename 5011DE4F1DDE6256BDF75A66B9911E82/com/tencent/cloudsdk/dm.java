package com.tencent.cloudsdk;

import java.util.List;

// compiled from: SourceFile
class dm implements Runnable {
    final /* synthetic */ dx a;
    private List b;
    private ea c;

    public dm(dx r1_dx, List r2_List, ea r3_ea) {
        this.a = r1_dx;
        this.b = r2_List;
        this.c = r3_ea;
    }

    public void run() {
        this.a.a(this.b, this.c);
    }
}