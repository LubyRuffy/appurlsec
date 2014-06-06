package com.baidu.kirin;

import android.content.Context;

final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ PostChoiceListener c;

    b(Context r1_Context, int r2i, PostChoiceListener r3_PostChoiceListener) {
        this.a = r1_Context;
        this.b = r2i;
        this.c = r3_PostChoiceListener;
    }

    public void run() {
        StatUpdateAgent.b(this.a, this.b, this.c);
    }
}