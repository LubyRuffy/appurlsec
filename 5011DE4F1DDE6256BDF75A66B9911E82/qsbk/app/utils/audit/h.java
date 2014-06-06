package qsbk.app.utils.audit;

import android.widget.ProgressBar;

// compiled from: SimpleImageLoader.java
class h implements Runnable {
    final /* synthetic */ ProgressBar a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ SimpleImageLoader d;

    h(SimpleImageLoader r1_SimpleImageLoader, ProgressBar r2_ProgressBar, int r3i, int r4i) {
        this.d = r1_SimpleImageLoader;
        this.a = r2_ProgressBar;
        this.b = r3i;
        this.c = r4i;
    }

    public void run() {
        if (this.a != null) {
            this.a.setVisibility(0);
            if (this.b < this.c) {
                this.a.setProgress(this.c);
            } else {
                this.a.setProgress(this.b);
            }
        }
        this.d.i = null;
    }
}