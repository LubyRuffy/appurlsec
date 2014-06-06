package com.tencent.plus;

// compiled from: ProGuard
class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ ImageActivity c;

    e(ImageActivity r1_ImageActivity, String r2_String, int r3i) {
        this.c = r1_ImageActivity;
        this.a = r2_String;
        this.b = r3i;
    }

    public void run() {
        ImageActivity.a(this.c, this.a, this.b);
    }
}