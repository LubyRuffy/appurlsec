package com.flurry.android;

// compiled from: SourceFile
final class x implements Runnable {
    private /* synthetic */ n a;

    x(n r1_n) {
        this.a = r1_n;
    }

    public final void run() {
        FlurryAgent.b(this.a.b, this.a.a);
    }
}