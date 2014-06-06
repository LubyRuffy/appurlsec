package qsbk.app.utils.audit;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SimpleImageLoader.java
class e implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ ImageView d;
    final /* synthetic */ Drawable e;
    final /* synthetic */ ProgressBar f;
    final /* synthetic */ SimpleImageLoader g;

    e(SimpleImageLoader r1_SimpleImageLoader, byte[] r2_byteA, int r3i, int r4i, ImageView r5_ImageView, Drawable r6_Drawable, ProgressBar r7_ProgressBar) {
        this.g = r1_SimpleImageLoader;
        this.a = r2_byteA;
        this.b = r3i;
        this.c = r4i;
        this.d = r5_ImageView;
        this.e = r6_Drawable;
        this.f = r7_ProgressBar;
    }

    public void run() {
        Bitmap r1_Bitmap = BitmapUtil.decodeBitmap(this.a, this.b, this.c, false);
        if (r1_Bitmap != null) {
            LayoutParams r0_LayoutParams = this.d.getLayoutParams();
            int r2i = BitmapUtil.calDesiredHeight(this.a, this.b, this.c);
            if (r0_LayoutParams == null) {
                r0_LayoutParams = new LayoutParams(this.b, r2i);
            } else {
                r0_LayoutParams.width = this.b;
                r0_LayoutParams.height = r2i;
            }
            this.d.setLayoutParams(r0_LayoutParams);
            this.d.setImageBitmap(r1_Bitmap);
        } else {
            this.d.setImageDrawable(this.e);
        }
        if (this.f != null) {
            this.f.setProgress(this.f.getMax());
            this.f.setVisibility(XListViewFooter.STATE_NODATA);
        }
        this.g.k = null;
    }
}