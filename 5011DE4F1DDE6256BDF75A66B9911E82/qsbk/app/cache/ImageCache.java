package qsbk.app.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;
import java.io.File;
import qsbk.app.utils.image.RetainFragment;

public class ImageCache {
    private static final CompressFormat a;
    private DiskLruCache b;
    private LruCache<String, Bitmap> c;

    public static class ImageCacheParams {
        public boolean clearDiskCacheOnStart;
        public CompressFormat compressFormat;
        public int compressQuality;
        public boolean diskCacheEnabled;
        public int diskCacheSize;
        public int memCacheSize;
        public boolean memoryCacheEnabled;
        public String uniqueName;

        public ImageCacheParams(String r3_String) {
            this.memCacheSize = 3145728;
            this.diskCacheSize = 5242880;
            this.compressFormat = a;
            this.compressQuality = 85;
            this.memoryCacheEnabled = true;
            this.diskCacheEnabled = true;
            this.clearDiskCacheOnStart = false;
            this.uniqueName = r3_String;
        }
    }

    static {
        a = CompressFormat.JPEG;
    }

    public ImageCache(Context r2_Context, String r3_String, int r4i) {
        a(r2_Context, new ImageCacheParams(r3_String), r4i);
    }

    public ImageCache(Context r1_Context, ImageCacheParams r2_ImageCacheParams, int r3i) {
        a(r1_Context, r2_ImageCacheParams, r3i);
    }

    private void a(Context r4_Context, ImageCacheParams r5_ImageCacheParams, int r6i) {
        File r0_File = DiskLruCache.getDiskCacheDir(r4_Context, r5_ImageCacheParams.uniqueName);
        if (r5_ImageCacheParams.diskCacheEnabled) {
            this.b = DiskLruCache.openCache(r4_Context, r0_File, (long) r5_ImageCacheParams.diskCacheSize, r6i);
            if (this.b == null) {
                return;
            }
            this.b.setCompressParams(r5_ImageCacheParams.compressFormat, r5_ImageCacheParams.compressQuality);
            if (r5_ImageCacheParams.clearDiskCacheOnStart) {
                this.b.clearCache();
            }
        }
        if (r5_ImageCacheParams.memoryCacheEnabled) {
            this.c = new d(this, r5_ImageCacheParams.memCacheSize);
        }
    }

    public static ImageCache findOrCreateCache(FragmentActivity r1_FragmentActivity, String r2_String, int r3i) {
        return findOrCreateCache(r1_FragmentActivity, new ImageCacheParams(r2_String), r3i);
    }

    public static ImageCache findOrCreateCache(FragmentActivity r2_FragmentActivity, ImageCacheParams r3_ImageCacheParams, int r4i) {
        RetainFragment r1_RetainFragment = RetainFragment.findOrCreateRetainFragment(r2_FragmentActivity.getSupportFragmentManager());
        ImageCache r0_ImageCache = (ImageCache) r1_RetainFragment.getObject();
        if (r0_ImageCache != null) {
            return r0_ImageCache;
        }
        r0_ImageCache = new ImageCache((Context)r2_FragmentActivity, r3_ImageCacheParams, r4i);
        r1_RetainFragment.setObject(r0_ImageCache);
        return r0_ImageCache;
    }

    public void addBitmapToCache(String r2_String, Bitmap r3_Bitmap) {
        if (r2_String == null || r3_Bitmap == null) {
        } else {
            if (this.c == null || this.c.get(r2_String) != null) {
            } else {
                this.c.put(r2_String, r3_Bitmap);
            }
            if (this.b == null || this.b.containsKey(r2_String)) {
            } else {
                this.b.put(r2_String, r3_Bitmap);
            }
        }
    }

    public void clearCaches() {
        this.c.evictAll();
    }

    public Bitmap getBitmapFromDiskCache(String r2_String) {
        return this.b != null ? this.b.get(r2_String) : null;
    }

    public Bitmap getBitmapFromMemCache(String r2_String) {
        if (this.c != null) {
            Bitmap r0_Bitmap = (Bitmap) this.c.get(r2_String);
            if (r0_Bitmap != null) {
                return r0_Bitmap;
            }
        }
        return null;
    }

    public DiskLruCache getDiskLruCache() {
        return this.b;
    }
}