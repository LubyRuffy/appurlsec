package com.flurry.android;

// compiled from: SourceFile
final class e implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ ag b;

    e(ag r1_ag, int r2i) {
        this.b = r1_ag;
        this.a = r2i;
    }

    public final void run() {
        this.b.y.onAdsUpdated(new CallbackEvent(this.a));
    }
}