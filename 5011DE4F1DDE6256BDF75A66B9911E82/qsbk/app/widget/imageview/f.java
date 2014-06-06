package qsbk.app.widget.imageview;

import android.graphics.Bitmap;

// compiled from: ImageViewTouchBaseView.java
class f implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ boolean b;
    final /* synthetic */ ImageViewTouchBaseView c;

    f(ImageViewTouchBaseView r1_ImageViewTouchBaseView, Bitmap r2_Bitmap, boolean r3z) {
        this.c = r1_ImageViewTouchBaseView;
        this.a = r2_Bitmap;
        this.b = r3z;
    }

    public void run() {
        this.c.setImageRotateBitmapReset(this.a, this.c.mRotation, this.b);
    }
}