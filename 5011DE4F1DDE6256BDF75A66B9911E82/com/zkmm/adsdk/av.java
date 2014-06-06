package com.zkmm.adsdk;

// compiled from: SourceFile
final class av extends Thread {
    private final /* synthetic */ j a;

    av(au r1_au, j r2_j) {
        this.a = r2_j;
    }

    public final void run() {
        int r2i = this.a.f.size();
        if (r2i != 0) {
            int r1i = 0;
            while (r1i < r2i) {
                s.a((String) this.a.f.get(r1i));
                r1i++;
            }
        }
    }
}