package com.tencent.plus;

// compiled from: ProGuard
class a implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ g b;

    a(g r1_g, String r2_String) {
        this.b = r1_g;
        this.a = r2_String;
    }

    public void run() {
        ImageActivity.b(this.b.a, this.a);
    }
}