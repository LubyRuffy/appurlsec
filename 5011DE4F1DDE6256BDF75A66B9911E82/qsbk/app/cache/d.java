package qsbk.app.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import qsbk.app.utils.image.Utils;

// compiled from: ImageCache.java
class d extends LruCache<String, Bitmap> {
    final /* synthetic */ ImageCache a;

    d(ImageCache r1_ImageCache, int r2i) {
        this.a = r1_ImageCache;
        super(r2i);
    }

    protected int a(String r2_String, Bitmap r3_Bitmap) {
        return Utils.getBitmapSize(r3_Bitmap);
    }
}