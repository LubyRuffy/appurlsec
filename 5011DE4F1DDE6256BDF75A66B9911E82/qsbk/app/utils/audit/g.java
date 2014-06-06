package qsbk.app.utils.audit;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;

// compiled from: SimpleImageLoader.java
class g implements Runnable {
    final /* synthetic */ ProgressBar a;
    final /* synthetic */ int b;
    final /* synthetic */ ImageView c;
    final /* synthetic */ Drawable d;
    final /* synthetic */ SimpleImageLoader e;

    g(SimpleImageLoader r1_SimpleImageLoader, ProgressBar r2_ProgressBar, int r3i, ImageView r4_ImageView, Drawable r5_Drawable) {
        this.e = r1_SimpleImageLoader;
        this.a = r2_ProgressBar;
        this.b = r3i;
        this.c = r4_ImageView;
        this.d = r5_Drawable;
    }

    public void run() {
        if (this.a != null) {
            this.a.setVisibility(0);
            this.a.setProgress((int) (((double) this.b) * 0.07d + 1.0d));
            this.a.setMax(this.b);
        }
        this.c.setImageDrawable(this.d);
        this.e.h = null;
    }
}