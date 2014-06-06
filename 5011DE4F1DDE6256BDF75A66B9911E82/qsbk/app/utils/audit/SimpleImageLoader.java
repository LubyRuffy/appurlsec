package qsbk.app.utils.audit;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import java.util.LinkedList;
import java.util.List;

public class SimpleImageLoader {
    private static final String a;
    private static SimpleImageLoader b;
    private static boolean f;
    private final RequestManager c;
    private final LruCache<String, byte[]> d;
    private final List<String> e;
    private Handler g;
    private Runnable h;
    private Runnable i;
    private Runnable j;
    private Runnable k;
    private int l;

    public static interface onCallback {
        public void onFailed(View r1_View, String r2_String, Throwable r3_Throwable);

        public void onSuccess(View r1_View, String r2_String);
    }

    static {
        a = SimpleImageLoader.class.getName();
        b = null;
        f = false;
    }

    private SimpleImageLoader() {
        this.g = new Handler(Looper.getMainLooper());
        this.l = 0;
        this.c = new RequestManager();
        this.c.start();
        this.d = new b(this, 349525);
        this.e = new LinkedList();
    }

    private void a(ImageView r5_ImageView, ProgressBar r6_ProgressBar, Drawable r7_Drawable) {
        if (this.j == null) {
            this.j = new f(this, r6_ProgressBar, r5_ImageView, r7_Drawable);
        }
        this.g.postDelayed(this.j, (long) this.l);
    }

    private void a(ImageView r8_ImageView, ProgressBar r9_ProgressBar, Drawable r10_Drawable, int r11i) {
        this.g.postDelayed(new g(this, r9_ProgressBar, r11i, r8_ImageView, r10_Drawable), (long) this.l);
    }

    private void a(ImageView r9_ImageView, ProgressBar r10_ProgressBar, byte[] r11_byteA, int r12i, int r13i, Drawable r14_Drawable) {
        if (this.k == null) {
            this.k = new e(this, r11_byteA, r12i, r13i, r9_ImageView, r14_Drawable, r10_ProgressBar);
        }
        this.g.postDelayed(this.k, (long) this.l);
    }

    private void a(ProgressBar r6_ProgressBar, int r7i, int r8i) {
        this.g.postDelayed(new h(this, r6_ProgressBar, r8i, (int) (((double) r7i) * 0.07d + 1.0d)), (long) this.l);
    }

    public static synchronized SimpleImageLoader getInstance() {
        SimpleImageLoader r0_SimpleImageLoader;
        synchronized (SimpleImageLoader.class) {
            if (b == null) {
                b = new SimpleImageLoader();
            }
            r0_SimpleImageLoader = b;
        }
        return r0_SimpleImageLoader;
    }

    public void cancel(String r3_String) {
        this.c.cancelAll(new i(this, r3_String));
    }

    public void cancelAll() {
        this.c.cancelAll(new c(this));
    }

    public Request loadImage(ImageView r11_ImageView, ProgressBar r12_ProgressBar, String r13_String, Drawable r14_Drawable, Drawable r15_Drawable, int r16i, int r17i, onCallback r18_onCallback) {
        a(r11_ImageView, r12_ProgressBar, r14_Drawable, 100);
        Request r1_Request = new Request(r13_String, new d(this, r11_ImageView, r12_ProgressBar, r16i, r17i, r15_Drawable, r18_onCallback, r13_String, r14_Drawable));
        this.c.add(r1_Request);
        return r1_Request;
    }

    public void stop() {
        this.c.stop();
    }
}