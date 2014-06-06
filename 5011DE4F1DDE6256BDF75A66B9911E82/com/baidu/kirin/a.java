package com.baidu.kirin;

import android.content.Context;

final class a implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;
    final /* synthetic */ CheckUpdateListener c;

    a(Context r1_Context, boolean r2z, CheckUpdateListener r3_CheckUpdateListener) {
        this.a = r1_Context;
        this.b = r2z;
        this.c = r3_CheckUpdateListener;
    }

    public void run() {
        StatUpdateAgent.b(this.a, this.b, this.c);
    }
}