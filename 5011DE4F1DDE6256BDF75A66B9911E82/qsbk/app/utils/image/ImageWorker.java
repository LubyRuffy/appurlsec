package qsbk.app.utils.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import qsbk.app.cache.ImageCache;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;

public abstract class ImageWorker {
    private ImageCache a;
    private Bitmap b;
    protected Context c;
    protected ImageWorkerAdapter d;
    private boolean e;
    private boolean f;

    public static abstract class ImageWorkerAdapter {
        public abstract Object getItem(int r1i);

        public abstract int getSize();
    }

    private static class a extends BitmapDrawable {
        private final WeakReference<b> a;

        public a(Resources r2_Resources, Bitmap r3_Bitmap, b r4_b) {
            super(r2_Resources, r3_Bitmap);
            this.a = new WeakReference(r4_b);
        }

        public b getBitmapWorkerTask() {
            return (b) this.a.get();
        }
    }

    private class b extends AsyncTask<Object, Void, Bitmap> {
        private Object b;
        private final WeakReference<ImageView> c;

        public b(ImageView r3_ImageView) {
            this.c = new WeakReference(r3_ImageView);
        }

        private ImageView c() {
            ImageView r0_ImageView = (ImageView) this.c.get();
            return this == ImageWorker.b(r0_ImageView) ? r0_ImageView : null;
        }

        protected /* synthetic */ Object a(Object[] r2_ObjectA) {
            return c(r2_ObjectA);
        }

        protected void a(Bitmap r3_Bitmap) {
            ImageView r0_ImageView;
            if (isCancelled() || ImageWorker.this.f) {
                r3_Bitmap = null;
                r0_ImageView = c();
                if (r3_Bitmap == null || r0_ImageView == null) {
                } else {
                    ImageWorker.this.a(r0_ImageView, r3_Bitmap);
                }
            } else {
                r0_ImageView = c();
                if (r3_Bitmap == null || r0_ImageView == null) {
                } else {
                    ImageWorker.this.a(r0_ImageView, r3_Bitmap);
                }
            }
        }

        protected Bitmap c(Object ... r8_ObjectA) {
            this.b = r8_ObjectA[0];
            String r1_String = String.valueOf(this.b);
            Bitmap r0_Bitmap = null;
            try {
                Thread r2_Thread = Thread.currentThread();
                Object[] r4_ObjectA = new Object[1];
                r4_ObjectA[0] = r1_String;
                r2_Thread.setName(String.format("qsbk-AT-ImgWk-%s", r4_ObjectA));
            } catch (SecurityException e) {
            }
            if (ImageWorker.this == null || isCancelled() || c() == null || ImageWorker.this.f) {
                if (r0_Bitmap != null || isCancelled() || c() == null || ImageWorker.this.f) {
                    if (r0_Bitmap == null || ImageWorker.this == null) {
                        return r0_Bitmap;
                    }
                    ImageWorker.this.addBitmapToCache(Md5.MD5(r1_String), r0_Bitmap);
                    return r0_Bitmap;
                } else {
                    r0_Bitmap = ImageWorker.this.a(r8_ObjectA[0]);
                    if (r0_Bitmap == null || ImageWorker.this == null) {
                        return r0_Bitmap;
                    }
                    ImageWorker.this.addBitmapToCache(Md5.MD5(r1_String), r0_Bitmap);
                    return r0_Bitmap;
                }
            } else {
                r0_Bitmap = ImageWorker.this.getBitmapFromDiskCache(Md5.MD5(r1_String));
                if (r0_Bitmap != null || isCancelled() || c() == null || ImageWorker.this.f) {
                    if (r0_Bitmap == null || ImageWorker.this == null) {
                        return r0_Bitmap;
                    }
                    ImageWorker.this.addBitmapToCache(Md5.MD5(r1_String), r0_Bitmap);
                    return r0_Bitmap;
                } else {
                    r0_Bitmap = ImageWorker.this.a(r8_ObjectA[0]);
                    if (r0_Bitmap == null || ImageWorker.this == null) {
                        return r0_Bitmap;
                    }
                    ImageWorker.this.addBitmapToCache(Md5.MD5(r1_String), r0_Bitmap);
                    return r0_Bitmap;
                }
            }
        }
    }

    protected ImageWorker(Context r2_Context) {
        this.e = true;
        this.f = false;
        this.c = r2_Context;
    }

    private void a(ImageView r6_ImageView, Bitmap r7_Bitmap) {
        if (this.e) {
            Drawable[] r1_DrawableA = new Drawable[2];
            r1_DrawableA[0] = new ColorDrawable(17170445);
            r1_DrawableA[1] = new BitmapDrawable(this.c.getResources(), r7_Bitmap);
            Drawable r0_Drawable = new TransitionDrawable(r1_DrawableA);
            r6_ImageView.setImageDrawable(r0_Drawable);
            r0_Drawable.startTransition(200);
        } else {
            r6_ImageView.setImageBitmap(r7_Bitmap);
        }
    }

    private static b b(ImageView r2_ImageView) {
        if (r2_ImageView != null) {
            Drawable r0_Drawable = r2_ImageView.getDrawable();
            if (r0_Drawable instanceof a) {
                return ((a) r0_Drawable).getBitmapWorkerTask();
            }
        }
        return null;
    }

    public static boolean cancelPotentialWork(Object r3_Object, ImageView r4_ImageView) {
        b r1_b = b(r4_ImageView);
        if (r1_b == null) {
            return true;
        }
        Object r2_Object = r1_b.b;
        if (r2_Object != null && r2_Object.equals(r3_Object)) {
            return false;
        }
        r1_b.cancel(true);
        return true;
    }

    public static void cancelWork(ImageView r2_ImageView) {
        b r0_b = b(r2_ImageView);
        if (r0_b != null) {
            r0_b.cancel(true);
        }
    }

    protected abstract Bitmap a(Object r1_Object);

    public ImageWorkerAdapter getAdapter() {
        return this.d;
    }

    public Bitmap getBitmap(String r3_String) {
        if (this.a == null) {
            return null;
        }
        Bitmap r0_Bitmap = this.a.getBitmapFromMemCache(Md5.MD5(String.valueOf(r3_String)));
        return r0_Bitmap == null ? this.a.getBitmapFromDiskCache(Md5.MD5(String.valueOf(r3_String))) : r0_Bitmap;
    }

    public ImageCache getImageCache() {
        return this.a;
    }

    public boolean getMemoryBitmap(String r4_String, ImageView r5_ImageView) {
        if (this.a == null) {
            return false;
        }
        Bitmap r1_Bitmap = this.a.getBitmapFromMemCache(Md5.MD5(String.valueOf(r4_String)));
        if (r1_Bitmap == null) {
            return false;
        }
        r5_ImageView.setImageBitmap(r1_Bitmap);
        return true;
    }

    public boolean lazyLoadImage(Object r2_Object, ImageView r3_ImageView) {
        Bitmap r0_Bitmap = getBitmap(String.valueOf(r2_Object));
        if (r0_Bitmap == null) {
            return false;
        }
        r3_ImageView.setImageBitmap(r0_Bitmap);
        return true;
    }

    public void loadImage(int r3i, ImageView r4_ImageView) {
        if (this.d != null) {
            loadImage(this.d.getItem(r3i), r4_ImageView);
        } else {
            throw new NullPointerException("Data not set, must call setAdapter() first.");
        }
    }

    public void loadImage(Object r5_Object, ImageView r6_ImageView) {
        Bitmap r0_Bitmap = null;
        if (this.a != null) {
            r0_Bitmap = this.a.getBitmapFromMemCache(Md5.MD5(String.valueOf(r5_Object)));
        }
        if (r0_Bitmap != null) {
            r6_ImageView.setImageBitmap(r0_Bitmap);
        } else {
            if (cancelPotentialWork(r5_Object, r6_ImageView)) {
                b r0_b = new b(r6_ImageView);
                r6_ImageView.setImageDrawable(new a(this.c.getResources(), this.b, r0_b));
                Object[] r1_ObjectA = new Object[1];
                r1_ObjectA[0] = r5_Object;
                r0_b.execute(r1_ObjectA);
            }
        }
    }

    public void setAdapter(ImageWorkerAdapter r1_ImageWorkerAdapter) {
        this.d = r1_ImageWorkerAdapter;
    }

    public void setExitTasksEarly(boolean r1z) {
        this.f = r1z;
    }

    public void setImageCache(ImageCache r3_ImageCache) {
        LogUtil.d("set image cache:" + r3_ImageCache);
        this.a = r3_ImageCache;
    }

    public void setImageFadeIn(boolean r1z) {
        this.e = r1z;
    }

    public void setLoadingImage(int r2i) {
        this.b = BitmapFactory.decodeResource(this.c.getResources(), r2i);
    }

    public void setLoadingImage(Bitmap r1_Bitmap) {
        this.b = r1_Bitmap;
    }
}