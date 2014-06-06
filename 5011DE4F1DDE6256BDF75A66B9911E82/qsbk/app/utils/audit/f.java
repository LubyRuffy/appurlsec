package qsbk.app.utils.audit;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SimpleImageLoader.java
class f implements Runnable {
    final /* synthetic */ ProgressBar a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ Drawable c;
    final /* synthetic */ SimpleImageLoader d;

    f(SimpleImageLoader r1_SimpleImageLoader, ProgressBar r2_ProgressBar, ImageView r3_ImageView, Drawable r4_Drawable) {
        this.d = r1_SimpleImageLoader;
        this.a = r2_ProgressBar;
        this.b = r3_ImageView;
        this.c = r4_Drawable;
    }

    public void run() {
        if (this.a != null) {
            this.a.setVisibility(XListViewFooter.STATE_NODATA);
        }
        this.b.setImageDrawable(this.c);
        this.d.j = null;
    }
}