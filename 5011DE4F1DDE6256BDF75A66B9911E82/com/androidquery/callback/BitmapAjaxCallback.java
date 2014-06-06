package com.androidquery.callback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.ExifInterface;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.androidquery.auth.AccountHandle;
import com.androidquery.util.AQUtility;
import com.androidquery.util.BitmapCache;
import com.androidquery.util.Common;
import com.androidquery.util.Constants;
import com.androidquery.util.RatioDrawable;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.http.HttpHost;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.ProfileHeaderListView;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class BitmapAjaxCallback extends AbstractAjaxCallback<Bitmap, BitmapAjaxCallback> {
    private static Bitmap F;
    private static Bitmap G;
    private static int i;
    private static int j;
    private static int k;
    private static int l;
    private static int m;
    private static boolean n;
    private static Map<String, Bitmap> o;
    private static Map<String, Bitmap> p;
    private static Map<String, Bitmap> q;
    private static HashMap<String, WeakHashMap<ImageView, BitmapAjaxCallback>> r;
    private int A;
    private boolean B;
    private float C;
    private boolean D;
    private boolean E;
    private WeakReference<ImageView> s;
    private int t;
    private int u;
    private File v;
    private Bitmap w;
    private int x;
    private Bitmap y;
    private float z;

    static {
        i = 20;
        j = 20;
        k = 2500;
        l = 160000;
        m = 1000000;
        n = false;
        r = new HashMap();
        F = Bitmap.createBitmap(1, 1, Config.ALPHA_8);
        G = Bitmap.createBitmap(1, 1, Config.ALPHA_8);
    }

    public BitmapAjaxCallback() {
        this.B = true;
        this.C = 3.4028235E38f;
        ((BitmapAjaxCallback) ((BitmapAjaxCallback) ((BitmapAjaxCallback) type(Bitmap.class)).memCache(true)).fileCache(true)).url(RContactStorage.PRIMARY_KEY);
    }

    private static int a(int r3i, int r4i) {
        int r1i = 1;
        int r0i = 0;
        while (r0i < 10 && r3i >= r4i * 2) {
            r3i /= 2;
            r1i *= 2;
            r0i++;
        }
        return r1i;
    }

    private static Bitmap a(Bitmap r8_Bitmap, int r9i) {
        Bitmap r0_Bitmap = Bitmap.createBitmap(r8_Bitmap.getWidth(), r8_Bitmap.getHeight(), Config.ARGB_8888);
        Canvas r1_Canvas = new Canvas(r0_Bitmap);
        Paint r2_Paint = new Paint();
        Rect r3_Rect = new Rect(0, 0, r8_Bitmap.getWidth(), r8_Bitmap.getHeight());
        RectF r4_RectF = new RectF(r3_Rect);
        float r5f = (float) r9i;
        r2_Paint.setAntiAlias(true);
        r1_Canvas.drawARGB(0, 0, 0, 0);
        r2_Paint.setColor(-12434878);
        r1_Canvas.drawRoundRect(r4_RectF, r5f, r5f, r2_Paint);
        r2_Paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        r1_Canvas.drawBitmap(r8_Bitmap, r3_Rect, r3_Rect, r2_Paint);
        return r0_Bitmap;
    }

    private static Bitmap a(View r2_View, Bitmap r3_Bitmap, int r4i) {
        if (r3_Bitmap == null || r3_Bitmap.getWidth() != 1 || r3_Bitmap.getHeight() != 1 || r3_Bitmap == F) {
            if (r3_Bitmap == null) {
                r2_View.setVisibility(0);
            } else if (r4i != -2) {
                r2_View.setVisibility(Base64.DONT_BREAK_LINES);
            } else if (r4i != -1) {
                r2_View.setVisibility(XListViewFooter.STATE_NODATA);
            }
            return r3_Bitmap;
        } else {
            r3_Bitmap = null;
            if (r3_Bitmap == null) {
                if (r4i != -2) {
                    if (r4i != -1) {
                        return r3_Bitmap;
                    }
                    r2_View.setVisibility(XListViewFooter.STATE_NODATA);
                } else {
                    r2_View.setVisibility(Base64.DONT_BREAK_LINES);
                }
            } else {
                r2_View.setVisibility(0);
            }
            return r3_Bitmap;
        }
    }

    private static Bitmap a(String r4_String, int r5i, int r6i) {
        String r2_String = b(r4_String, r5i, r6i);
        Bitmap r0_Bitmap = (Bitmap) g().get(r2_String);
        if (r0_Bitmap == null) {
            r0_Bitmap = (Bitmap) h().get(r2_String);
        }
        if (r0_Bitmap != null) {
            return r0_Bitmap;
        }
        r0_Bitmap = (Bitmap) i().get(r2_String);
        if (r0_Bitmap == null || d() != 200) {
            return r0_Bitmap;
        }
        q = null;
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static Bitmap a(String r5_String, Options r6_Options, boolean r7z) {
        /*
        r0 = 1;
        r1 = 0;
        if (r6 != 0) goto L_0x0009;
    L_0x0004:
        r6 = new android.graphics.BitmapFactory$Options;
        r6.<init>();
    L_0x0009:
        r6.inInputShareable = r0;
        r6.inPurgeable = r0;
        r2 = new java.io.FileInputStream;	 //Catch:{ IOException -> 0x0027, all -> 0x0033 }
        r2.<init>(r5);	 //Catch:{ IOException -> 0x0027, all -> 0x0033 }
        r0 = r2.getFD();	 //Catch:{ IOException -> 0x003b }
        r3 = 0;
        r0 = android.graphics.BitmapFactory.decodeFileDescriptor(r0, r3, r6);	 //Catch:{ IOException -> 0x003b }
        if (r0 == 0) goto L_0x0023;
    L_0x001d:
        if (r7 == 0) goto L_0x0023;
    L_0x001f:
        r0 = b(r5, r0);	 //Catch:{ IOException -> 0x0040 }
    L_0x0023:
        com.androidquery.util.AQUtility.close(r2);
    L_0x0026:
        return r0;
    L_0x0027:
        r0 = move-exception;
        r2 = r1;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x002c:
        com.androidquery.util.AQUtility.report(r1);	 //Catch:{ all -> 0x0039 }
        com.androidquery.util.AQUtility.close(r2);
        goto L_0x0026;
    L_0x0033:
        r0 = move-exception;
        r2 = r1;
    L_0x0035:
        com.androidquery.util.AQUtility.close(r2);
        throw r0;
    L_0x0039:
        r0 = move-exception;
        goto L_0x0035;
    L_0x003b:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
        goto L_0x002c;
    L_0x0040:
        r1 = move-exception;
        goto L_0x002c;
        */

    }

    private Bitmap a(String r7_String, byte[] r8_byteA) {
        return getResizedImage(r7_String, r8_byteA, this.t, this.B, this.A, this.E);
    }

    private static Bitmap a(String r2_String, byte[] r3_byteA, Options r4_Options, boolean r5z) {
        Bitmap r0_Bitmap = null;
        if (r2_String != null) {
            r0_Bitmap = a(r2_String, r4_Options, r5z);
        } else if (r3_byteA != null) {
            r0_Bitmap = BitmapFactory.decodeByteArray(r3_byteA, 0, r3_byteA.length, r4_Options);
        }
        if (r0_Bitmap != null || r4_Options == null || r4_Options.inJustDecodeBounds) {
            return r0_Bitmap;
        }
        AQUtility.debug("decode image failed", r2_String);
        return r0_Bitmap;
    }

    private static Matrix a(int r6i) {
        Matrix r0_Matrix = new Matrix();
        switch (r6i) {
            case XListViewHeader.STATE_REFRESHING:
                r0_Matrix.setScale(-1.0f, 1.0f);
                break;
            case XListViewFooter.STATE_NOMORE:
                r0_Matrix.setRotate(180.0f);
                break;
            case XListViewFooter.STATE_NODATA:
                r0_Matrix.setRotate(180.0f);
                r0_Matrix.postScale(-1.0f, 1.0f);
                break;
            case ShareUtils.SHARE_SMS:
                r0_Matrix.setRotate(90.0f);
                r0_Matrix.postScale(-1.0f, 1.0f);
                break;
            case ShareUtils.SHARE_COPY:
                r0_Matrix.setRotate(90.0f);
                break;
            case ShareUtils.SHARE_COLLECT:
                r0_Matrix.setRotate(-90.0f);
                r0_Matrix.postScale(-1.0f, 1.0f);
                break;
            case Base64.DONT_BREAK_LINES:
                r0_Matrix.setRotate(-90.0f);
                break;
        }
        return r0_Matrix;
    }

    private static Drawable a(ImageView r6_ImageView, Bitmap r7_Bitmap, float r8f, float r9f) {
        return (r8f > 0.0f ? 1 : (r8f == 0.0f? 0 : -1)) > 0 ? new RatioDrawable(r6_ImageView.getResources(), r7_Bitmap, r6_ImageView, r8f, r9f) : new BitmapDrawable(r6_ImageView.getResources(), r7_Bitmap);
    }

    private static void a(ImageView r6_ImageView, Bitmap r7_Bitmap, Bitmap r8_Bitmap, int r9i, int r10i, float r11f, float r12f, int r13i) {
        Bitmap r0_Bitmap = a((View)r6_ImageView, r7_Bitmap, r9i);
        if (r0_Bitmap == null) {
            r6_ImageView.setImageBitmap(null);
        } else {
            Animation r0_Animation;
            Drawable r2_Drawable = a(r6_ImageView, r0_Bitmap, r11f, r12f);
            if (b(r10i, r13i)) {
                if (r8_Bitmap == null) {
                    r0_Animation = new AlphaAnimation(0.0f, 1.0f);
                    r0_Animation.setInterpolator(new DecelerateInterpolator());
                    r0_Animation.setDuration(300);
                } else {
                    Drawable[] r3_DrawableA = new Drawable[2];
                    r3_DrawableA[0] = a(r6_ImageView, r8_Bitmap, r11f, r12f);
                    r3_DrawableA[1] = r2_Drawable;
                    Drawable r0_Drawable = new TransitionDrawable(r3_DrawableA);
                    r0_Drawable.setCrossFadeEnabled(true);
                    r0_Drawable.startTransition(300);
                    r2_Drawable = r0_Drawable;
                    r0_Animation = null;
                }
            } else if (r10i > 0) {
                r0_Animation = AnimationUtils.loadAnimation(r6_ImageView.getContext(), r10i);
            } else {
                r0_Animation = null;
            }
            r6_ImageView.setImageDrawable(r2_Drawable);
            if (r0_Animation != null) {
                r0_Animation.setStartTime(AnimationUtils.currentAnimationTimeMillis());
                r6_ImageView.startAnimation(r0_Animation);
            } else {
                r6_ImageView.setAnimation(null);
            }
        }
    }

    private void a(BitmapAjaxCallback r3_BitmapAjaxCallback, String r4_String, ImageView r5_ImageView, Bitmap r6_Bitmap, AjaxStatus r7_AjaxStatus) {
        if (r5_ImageView == null || r3_BitmapAjaxCallback == null) {
        } else {
            if (r4_String.equals(r5_ImageView.getTag(Constants.TAG_URL))) {
                if (r5_ImageView instanceof ImageView) {
                    r3_BitmapAjaxCallback.a(r4_String, r5_ImageView, r6_Bitmap, r7_AjaxStatus);
                } else {
                    r3_BitmapAjaxCallback.a(r4_String, r5_ImageView, r6_Bitmap, false);
                }
            }
            r3_BitmapAjaxCallback.a(false);
        }
    }

    private static void a(String r2_String, int r3i, int r4i, Bitmap r5_Bitmap, boolean r6z) {
        if (r5_Bitmap == null) {
        } else {
            Map r0_Map;
            int r0i = r5_Bitmap.getWidth() * r5_Bitmap.getHeight();
            if (r6z) {
                r0_Map = i();
            } else if (r0i <= k) {
                r0_Map = h();
            } else {
                r0_Map = g();
            }
            if (r3i > 0 || r4i > 0) {
                r0_Map.put(b(r2_String, r3i, r4i), r5_Bitmap);
                if (!r0_Map.containsKey(r2_String)) {
                    r0_Map.put(r2_String, null);
                }
            } else {
                r0_Map.put(r2_String, r5_Bitmap);
            }
        }
    }

    private void a(String r4_String, ImageView r5_ImageView) {
        if (!(r4_String.equals(r5_ImageView.getTag(Constants.TAG_URL)) && this.y == null)) {
            r5_ImageView.setTag(Constants.TAG_URL, r4_String);
            if (this.y == null || a(r5_ImageView.getContext())) {
                a(r4_String, r5_ImageView, null, true);
            } else {
                a(r4_String, r5_ImageView, this.y, true);
            }
        }
    }

    private void a(String r9_String, ImageView r10_ImageView, Bitmap r11_Bitmap, boolean r12z) {
        if (r11_Bitmap == null) {
            r10_ImageView.setImageDrawable(null);
        } else if (r12z) {
            r10_ImageView.setImageDrawable(a(r10_ImageView, r11_Bitmap, this.z, this.C));
        } else {
            if (this.f != null) {
                a(r10_ImageView, r11_Bitmap, this.y, this.u, this.x, this.z, this.C, this.f.getSource());
            }
        }
    }

    public static void async(Activity r19_Activity, Context r20_Context, ImageView r21_ImageView, String r22_String, Object r23_Object, AccountHandle r24_AccountHandle, ImageOptions r25_ImageOptions, HttpHost r26_HttpHost, String r27_String) {
        async(r19_Activity, r20_Context, r21_ImageView, r22_String, r25_ImageOptions.memCache, r25_ImageOptions.fileCache, r25_ImageOptions.targetWidth, r25_ImageOptions.fallback, r25_ImageOptions.preset, r25_ImageOptions.animation, r25_ImageOptions.ratio, r25_ImageOptions.anchor, r23_Object, r24_AccountHandle, r25_ImageOptions.policy, r25_ImageOptions.round, r26_HttpHost, r27_String);
    }

    public static void async(Activity r9_Activity, Context r10_Context, ImageView r11_ImageView, String r12_String, boolean r13z, boolean r14z, int r15i, int r16i, Bitmap r17_Bitmap, int r18i, float r19f, float r20f, Object r21_Object, AccountHandle r22_AccountHandle, int r23i, int r24i, HttpHost r25_HttpHost, String r26_String) {
        Bitmap r2_Bitmap = null;
        if (r13z) {
            r2_Bitmap = a(r12_String, r15i, r24i);
        }
        if (r2_Bitmap != null) {
            r11_ImageView.setTag(Constants.TAG_URL, r12_String);
            Common.showProgress(r21_Object, r12_String, false);
            a(r11_ImageView, r2_Bitmap, r17_Bitmap, r16i, r18i, r19f, r20f, XListViewFooter.STATE_NODATA);
        } else {
            BitmapAjaxCallback r2_BitmapAjaxCallback = new BitmapAjaxCallback();
            ((BitmapAjaxCallback) ((BitmapAjaxCallback) ((BitmapAjaxCallback) ((BitmapAjaxCallback) ((BitmapAjaxCallback) ((BitmapAjaxCallback) r2_BitmapAjaxCallback.url(r12_String)).imageView(r11_ImageView).memCache(r13z)).fileCache(r14z)).targetWidth(r15i).fallback(r16i).preset(r17_Bitmap).animation(r18i).ratio(r19f).anchor(r20f).progress(r21_Object)).auth(r22_AccountHandle)).policy(r23i)).round(r24i).networkUrl(r26_String);
            if (r25_HttpHost != null) {
                r2_BitmapAjaxCallback.proxy(r25_HttpHost.getHostName(), r25_HttpHost.getPort());
            }
            if (r9_Activity != null) {
                r2_BitmapAjaxCallback.async(r9_Activity);
            } else {
                r2_BitmapAjaxCallback.async(r10_Context);
            }
        }
    }

    private static Bitmap b(String r7_String, Bitmap r8_Bitmap) {
        int r1i = 0;
        if (r8_Bitmap == null) {
            return null;
        }
        int r0i;
        try {
            r0i = new ExifInterface(r7_String).getAttributeInt("Orientation", 1);
        } catch (Exception e) {
            AQUtility.debug(e);
            r0i = 1;
        }
        if (r0i <= 0) {
            return r8_Bitmap;
        }
        Bitmap r0_Bitmap = Bitmap.createBitmap(r8_Bitmap, r1i, r1i, r8_Bitmap.getWidth(), r8_Bitmap.getHeight(), a(r0i), true);
        AQUtility.debug("before", new StringBuilder(String.valueOf(r8_Bitmap.getWidth())).append(":").append(r8_Bitmap.getHeight()).toString());
        AQUtility.debug("after", new StringBuilder(String.valueOf(r0_Bitmap.getWidth())).append(":").append(r0_Bitmap.getHeight()).toString());
        if (r8_Bitmap != r0_Bitmap) {
            r8_Bitmap.recycle();
        }
        return r0_Bitmap;
    }

    private static String b(String r2_String, int r3i, int r4i) {
        String r0_String;
        r0_String = r3i > 0 ? new StringBuilder(String.valueOf(r2_String)).append("#").append(r3i).toString() : r2_String;
        return r4i > 0 ? new StringBuilder(String.valueOf(r0_String)).append("#").append(r4i).toString() : r0_String;
    }

    private void b(String r3_String, ImageView r4_ImageView) {
        WeakHashMap r0_WeakHashMap = (WeakHashMap) r.get(r3_String);
        if (r0_WeakHashMap == null) {
            if (r.containsKey(r3_String)) {
                r0_WeakHashMap = new WeakHashMap();
                r0_WeakHashMap.put(r4_ImageView, this);
                r.put(r3_String, r0_WeakHashMap);
            } else {
                r.put(r3_String, null);
            }
        } else {
            r0_WeakHashMap.put(r4_ImageView, this);
        }
    }

    private static boolean b(int r2i, int r3i) {
        switch (r2i) {
            case com.tencent.tauth.Constants.ERROR_URL:
                if (r3i == 3) {
                    return true;
                }
                if (r3i == 1) {
                }
            case RequestListener.DEFAULT_LOADED_SIZE:
                break;
            case ProfileHeaderListView.INVALID_TAB_ID:
                return true;
            default:
                break;
        }
        return r3i == 1;
    }

    public static void clearCache() {
        p = null;
        o = null;
        q = null;
    }

    protected static void e() {
        r.clear();
    }

    private Bitmap f() {
        View r0_View = (View) this.s.get();
        if (r0_View == null) {
            return null;
        }
        String r2_String = Integer.toString(this.u);
        Bitmap r1_Bitmap = c(r2_String);
        if (r1_Bitmap != null) {
            return r1_Bitmap;
        }
        Bitmap r0_Bitmap = BitmapFactory.decodeResource(r0_View.getResources(), this.u);
        if (r0_Bitmap == null) {
            return r0_Bitmap;
        }
        a(r2_String, r0_Bitmap);
        return r0_Bitmap;
    }

    private static Map<String, Bitmap> g() {
        if (p == null) {
            p = Collections.synchronizedMap(new BitmapCache(j, l, m));
        }
        return p;
    }

    public static Bitmap getEmptyBitmap() {
        return F;
    }

    public static Bitmap getMemoryCached(Context r3_Context, int r4i) {
        String r1_String = Integer.toString(r4i);
        Bitmap r0_Bitmap = a(r1_String, 0, 0);
        if (r0_Bitmap == null) {
            r0_Bitmap = BitmapFactory.decodeResource(r3_Context.getResources(), r4i);
            if (r0_Bitmap != null) {
                a(r1_String, 0, 0, r0_Bitmap, false);
            }
        }
        return r0_Bitmap;
    }

    public static Bitmap getMemoryCached(String r1_String, int r2i) {
        return a(r1_String, r2i, 0);
    }

    public static Bitmap getResizedImage(String r6_String, byte[] r7_byteA, int r8i, boolean r9z, int r10i) {
        return getResizedImage(r6_String, r7_byteA, r8i, r9z, r10i, false);
    }

    public static Bitmap getResizedImage(String r3_String, byte[] r4_byteA, int r5i, boolean r6z, int r7i, boolean r8z) {
        if (r3_String == null && r4_byteA == null) {
            return null;
        }
        Options r0_Options;
        Bitmap r0_Bitmap;
        if (r5i > 0) {
            Options r2_Options = new Options();
            r2_Options.inJustDecodeBounds = true;
            a(r3_String, r4_byteA, r2_Options, r8z);
            int r0i = r2_Options.outWidth;
            if (!r6z) {
                r0i = Math.max(r0i, r2_Options.outHeight);
            }
            int r2i = a(r0i, r5i);
            r0_Options = new Options();
            r0_Options.inSampleSize = r2i;
        } else {
            r0_Options = null;
        }
        try {
            r0_Bitmap = a(r3_String, r4_byteA, r0_Options, r8z);
        } catch (OutOfMemoryError e) {
            clearCache();
            AQUtility.report(e);
            r0_Bitmap = null;
        }
        if (r7i > 0) {
            r0_Bitmap = a(r0_Bitmap, r7i);
        }
        return r0_Bitmap;
    }

    private static Map<String, Bitmap> h() {
        if (o == null) {
            o = Collections.synchronizedMap(new BitmapCache(i, k, 250000));
        }
        return o;
    }

    private static Map<String, Bitmap> i() {
        if (q == null) {
            q = Collections.synchronizedMap(new BitmapCache(100, l, 250000));
        }
        return q;
    }

    public static boolean isMemoryCached(String r1_String) {
        return g().containsKey(r1_String) || h().containsKey(r1_String) || i().containsKey(r1_String);
    }

    public static void setCacheLimit(int r0i) {
        j = r0i;
        clearCache();
    }

    public static void setDelayWrite(boolean r0z) {
        n = r0z;
    }

    public static void setIconCacheLimit(int r0i) {
        i = r0i;
        clearCache();
    }

    public static void setMaxPixelLimit(int r0i) {
        m = r0i;
        clearCache();
    }

    public static void setPixelLimit(int r0i) {
        l = r0i;
        clearCache();
    }

    public static void setSmallPixel(int r0i) {
        k = r0i;
        clearCache();
    }

    protected File a(File r2_File, String r3_String) {
        return (this.v == null || (!this.v.exists())) ? super.a(r2_File, r3_String) : this.v;
    }

    protected /* synthetic */ Object a(String r2_String, File r3_File, AjaxStatus r4_AjaxStatus) {
        return b(r2_String, r3_File, r4_AjaxStatus);
    }

    protected void a(String r4_String, Bitmap r5_Bitmap) {
        a(r4_String, this.t, this.A, r5_Bitmap, this.D);
    }

    protected void a(String r2_String, Bitmap r3_Bitmap, AjaxStatus r4_AjaxStatus) {
        r.remove(r2_String);
    }

    protected void a(String r2_String, ImageView r3_ImageView, Bitmap r4_Bitmap, AjaxStatus r5_AjaxStatus) {
        a(r2_String, r3_ImageView, r4_Bitmap, false);
    }

    public BitmapAjaxCallback anchor(float r1f) {
        this.C = r1f;
        return this;
    }

    public BitmapAjaxCallback animation(int r1i) {
        this.x = r1i;
        return this;
    }

    public void async(Context r5_Context) {
        String r1_String = getUrl();
        ImageView r0_ImageView = (ImageView) this.s.get();
        if (r1_String == null) {
            a(false);
            a(r1_String, r0_ImageView, null, false);
        } else {
            Bitmap r2_Bitmap = c(r1_String);
            if (r2_Bitmap != null) {
                r0_ImageView.setTag(Constants.TAG_URL, r1_String);
                this.f = new AjaxStatus().a((int)XListViewFooter.STATE_NODATA).done();
                callback(r1_String, r2_Bitmap, this.f);
            } else {
                a(r1_String, r0_ImageView);
                if (r.containsKey(r1_String)) {
                    a(true);
                    b(r1_String, r0_ImageView);
                } else {
                    b(r1_String, r0_ImageView);
                    super.async(r0_ImageView.getContext());
                }
            }
        }
    }

    protected Bitmap b(String r3_String, File r4_File, AjaxStatus r5_AjaxStatus) {
        return a(r4_File.getAbsolutePath(), null);
    }

    protected /* synthetic */ Object b(String r2_String) {
        return c(r2_String);
    }

    public BitmapAjaxCallback bitmap(Bitmap r1_Bitmap) {
        this.w = r1_Bitmap;
        return this;
    }

    protected Bitmap c(String r3_String) {
        if (this.w != null) {
            return this.w;
        }
        if (this.h) {
            return a(r3_String, this.t, this.A);
        }
        return null;
    }

    protected boolean c() {
        return !(n);
    }

    public final void callback(String r9_String, Bitmap r10_Bitmap, AjaxStatus r11_AjaxStatus) {
        ImageView r3_ImageView = (ImageView) this.s.get();
        WeakHashMap r6_WeakHashMap = (WeakHashMap) r.remove(r9_String);
        Iterator r7_Iterator;
        BitmapAjaxCallback r1_BitmapAjaxCallback;
        if (r6_WeakHashMap == null || (!r6_WeakHashMap.containsKey(r3_ImageView))) {
            a(this, r9_String, r3_ImageView, r10_Bitmap, r11_AjaxStatus);
            if (r6_WeakHashMap == null) {
                r7_Iterator = r6_WeakHashMap.keySet().iterator();
                while (r7_Iterator.hasNext()) {
                    r3_ImageView = (ImageView) r7_Iterator.next();
                    r1_BitmapAjaxCallback = (BitmapAjaxCallback) r6_WeakHashMap.get(r3_ImageView);
                    r1_BitmapAjaxCallback.f = r11_AjaxStatus;
                    a(r1_BitmapAjaxCallback, r9_String, r3_ImageView, r10_Bitmap, r11_AjaxStatus);
                }
            }
        } else if (r6_WeakHashMap == null) {
        } else {
            r7_Iterator = r6_WeakHashMap.keySet().iterator();
            while (r7_Iterator.hasNext()) {
                r3_ImageView = (ImageView) r7_Iterator.next();
                r1_BitmapAjaxCallback = (BitmapAjaxCallback) r6_WeakHashMap.get(r3_ImageView);
                r1_BitmapAjaxCallback.f = r11_AjaxStatus;
                a(r1_BitmapAjaxCallback, r9_String, r3_ImageView, r10_Bitmap, r11_AjaxStatus);
            }
        }
    }

    public BitmapAjaxCallback fallback(int r1i) {
        this.u = r1i;
        return this;
    }

    public BitmapAjaxCallback file(File r1_File) {
        this.v = r1_File;
        return this;
    }

    public BitmapAjaxCallback imageView(ImageView r2_ImageView) {
        this.s = new WeakReference(r2_ImageView);
        return this;
    }

    public BitmapAjaxCallback preset(Bitmap r1_Bitmap) {
        this.y = r1_Bitmap;
        return this;
    }

    public BitmapAjaxCallback ratio(float r1f) {
        this.z = r1f;
        return this;
    }

    public BitmapAjaxCallback rotate(boolean r1z) {
        this.E = r1z;
        return this;
    }

    public BitmapAjaxCallback round(int r1i) {
        this.A = r1i;
        return this;
    }

    public BitmapAjaxCallback targetWidth(int r1i) {
        this.t = r1i;
        return this;
    }

    public Bitmap transform(String r6_String, byte[] r7_byteA, AjaxStatus r8_AjaxStatus) {
        String r0_String = null;
        File r1_File = r8_AjaxStatus.f();
        if (r1_File != null) {
            r0_String = r1_File.getAbsolutePath();
        }
        Bitmap r0_Bitmap = a(r0_String, r7_byteA);
        if (r0_Bitmap == null) {
            if (this.u > 0) {
                r0_Bitmap = f();
            } else if (this.u == -2 || this.u == -1) {
                r0_Bitmap = G;
            } else if (this.u == -3) {
                r0_Bitmap = this.y;
            }
            if (r8_AjaxStatus.getCode() != 200) {
                this.D = true;
            }
            if (r8_AjaxStatus.getSource() != 1 || r1_File == null) {
                return r0_Bitmap;
            }
            AQUtility.debug((Object)"invalid bm from net");
            r1_File.delete();
        }
        return r0_Bitmap;
    }
}