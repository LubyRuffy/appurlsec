package com.zkmm.adsdk;

// compiled from: SourceFile
final class i implements Runnable {
    private /* synthetic */ h a;

    i(h r1_h) {
        this.a = r1_h;
    }

    public final void run() {
        E.a(this.a.a).loadUrl("javascript:adwoSaveToAlbumComplete();");
    }
}