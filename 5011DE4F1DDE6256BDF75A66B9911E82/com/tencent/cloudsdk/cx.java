package com.tencent.cloudsdk;

// compiled from: SourceFile
class cx implements Runnable {
    final /* synthetic */ dh a;

    private cx(dh r1_dh) {
        this.a = r1_dh;
    }

    public void run() {
        er.a("AbsCircleReport", "\u5468\u671f\u7edf\u8ba1\u4e0a\u62a5\u65f6\u95f4\u5230\uff0c\u5f00\u59cb\u4e0a\u62a5\u3002");
        dh.a(this.a);
        if (!dh.b(this.a).get()) {
            dh.c(this.a);
        }
        dh.d(this.a).remove(this);
    }
}