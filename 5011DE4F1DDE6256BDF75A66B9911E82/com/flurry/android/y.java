package com.flurry.android;

// compiled from: SourceFile
final class y implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ l b;

    y(l r1_l, String r2_String) {
        this.b = r1_l;
        this.a = r2_String;
    }

    public final void run() {
        if (this.a != null) {
            ag.a(this.b.d, this.b.b, this.a);
            this.b.c.a(new r((byte) 8, this.b.d.k()));
        } else {
            String r0_String = "Unable to launch in app market: " + this.b.a;
            i.d(ag.a, r0_String);
            this.b.d.e(r0_String);
        }
    }
}