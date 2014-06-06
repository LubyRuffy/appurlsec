package qsbk.app.utils.audit;

import android.support.v4.util.LruCache;

// compiled from: SimpleImageLoader.java
class b extends LruCache<String, byte[]> {
    final /* synthetic */ SimpleImageLoader a;

    b(SimpleImageLoader r1_SimpleImageLoader, int r2i) {
        this.a = r1_SimpleImageLoader;
        super(r2i);
    }

    protected int a(String r2_String, byte[] r3_byteA) {
        return r3_byteA.length;
    }
}