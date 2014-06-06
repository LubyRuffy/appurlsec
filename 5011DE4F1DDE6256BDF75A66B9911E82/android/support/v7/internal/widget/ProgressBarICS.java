package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ProgressBarICS extends View {
    private static final int[] f;
    int a;
    int b;
    int c;
    int d;
    Bitmap e;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private Transformation n;
    private AlphaAnimation o;
    private Drawable p;
    private Drawable q;
    private Drawable r;
    private boolean s;
    private Interpolator t;
    private a u;
    private long v;
    private boolean w;
    private long x;
    private boolean y;


    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int a;
        int b;

        static {
            CREATOR = new n();
        }

        private SavedState(Parcel r2_Parcel) {
            super(r2_Parcel);
            this.a = r2_Parcel.readInt();
            this.b = r2_Parcel.readInt();
        }

        SavedState(Parcelable r1_Parcelable) {
            super(r1_Parcelable);
        }

        public void writeToParcel(Parcel r2_Parcel, int r3i) {
            super.writeToParcel(r2_Parcel, r3i);
            r2_Parcel.writeInt(this.a);
            r2_Parcel.writeInt(this.b);
        }
    }

    private class a implements Runnable {
        private int b;
        private int c;
        private boolean d;

        a(int r2i, int r3i, boolean r4z) {
            this.b = r2i;
            this.c = r3i;
            this.d = r4z;
        }

        public void run() {
            ProgressBarICS.this.a(this.b, this.c, this.d, true);
            ProgressBarICS.this.u = this;
        }

        public void setup(int r1i, int r2i, boolean r3z) {
            this.b = r1i;
            this.c = r2i;
            this.d = r3z;
        }
    }

    static {
        f = new int[]{16843062, 16843063, 16843064, 16843065, 16843066, 16843067, 16843068, 16843069, 16843070, 16843071, 16843039, 16843072, 16843040, 16843073};
    }

    public ProgressBarICS(Context r7_Context, AttributeSet r8_AttributeSet, int r9i, int r10i) {
        boolean r0z = false;
        super(r7_Context, r8_AttributeSet, r9i);
        this.v = Thread.currentThread().getId();
        d();
        TypedArray r2_TypedArray = r7_Context.obtainStyledAttributes(r8_AttributeSet, f, r9i, r10i);
        this.s = true;
        setMax(r2_TypedArray.getInt(0, this.i));
        setProgress(r2_TypedArray.getInt(1, this.g));
        setSecondaryProgress(r2_TypedArray.getInt(XListViewHeader.STATE_REFRESHING, this.h));
        boolean r3z = r2_TypedArray.getBoolean(XListViewFooter.STATE_NOMORE, this.l);
        this.m = r2_TypedArray.getBoolean(XListViewFooter.STATE_NODATA, this.m);
        Drawable r4_Drawable = r2_TypedArray.getDrawable(ShareUtils.SHARE_SMS);
        if (r4_Drawable != null) {
            setIndeterminateDrawable(a(r4_Drawable));
        }
        r4_Drawable = r2_TypedArray.getDrawable(ShareUtils.SHARE_COPY);
        if (r4_Drawable != null) {
            setProgressDrawable(a(r4_Drawable, false));
        }
        this.k = r2_TypedArray.getInt(ShareUtils.SHARE_COLLECT, this.k);
        this.j = r2_TypedArray.getInt(Base64.DONT_BREAK_LINES, this.j);
        this.a = r2_TypedArray.getDimensionPixelSize(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY, this.a);
        this.b = r2_TypedArray.getDimensionPixelSize(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO, this.b);
        this.c = r2_TypedArray.getDimensionPixelSize(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE, this.c);
        this.d = r2_TypedArray.getDimensionPixelSize(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH, this.d);
        int r4i = r2_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, 17432587);
        if (r4i > 0) {
            setInterpolator(r7_Context, r4i);
        }
        r2_TypedArray.recycle();
        this.s = false;
        if (this.m || r3z) {
            r0z = true;
            setIndeterminate(r0z);
        } else {
            setIndeterminate(r0z);
        }
    }

    private Drawable a(Drawable r7_Drawable) {
        if (!(r7_Drawable instanceof AnimationDrawable)) {
            return r7_Drawable;
        }
        AnimationDrawable r7_AnimationDrawable = (AnimationDrawable) r7_Drawable;
        int r2i = r7_AnimationDrawable.getNumberOfFrames();
        Drawable r0_Drawable = new AnimationDrawable();
        r0_Drawable.setOneShot(r7_AnimationDrawable.isOneShot());
        int r1i = 0;
        while (r1i < r2i) {
            Drawable r3_Drawable = a(r7_AnimationDrawable.getFrame(r1i), true);
            r3_Drawable.setLevel(10000);
            r0_Drawable.addFrame(r3_Drawable, r7_AnimationDrawable.getDuration(r1i));
            r1i++;
        }
        r0_Drawable.setLevel(10000);
        return r0_Drawable;
    }

    private Drawable a(Drawable r9_Drawable, boolean r10z) {
        int r1i = 0;
        if (r9_Drawable instanceof LayerDrawable) {
            LayerDrawable r9_LayerDrawable = (LayerDrawable) r9_Drawable;
            int r4i = r9_LayerDrawable.getNumberOfLayers();
            Drawable[] r5_DrawableA = new Drawable[r4i];
            int r3i = 0;
            while (r3i < r4i) {
                boolean r0z;
                int r0i = r9_LayerDrawable.getId(r3i);
                Drawable r6_Drawable = r9_LayerDrawable.getDrawable(r3i);
                r0z = r0i == 16908301 || r0i == 16908303;
                r5_DrawableA[r3i] = a(r6_Drawable, r0z);
                r3i++;
            }
            Drawable r0_Drawable = new LayerDrawable(r5_DrawableA);
            while (r1i < r4i) {
                r0_Drawable.setId(r1i, r9_LayerDrawable.getId(r1i));
                r1i++;
            }
            return r0_Drawable;
        } else {
            if (!(r9_Drawable instanceof BitmapDrawable)) {
                return r9_Drawable;
            }
            Bitmap r0_Bitmap = ((BitmapDrawable) r9_Drawable).getBitmap();
            if (this.e == null) {
                this.e = r0_Bitmap;
            }
            Drawable r1_Drawable = new ShapeDrawable(a());
            r1_Drawable.getPaint().setShader(new BitmapShader(r0_Bitmap, TileMode.REPEAT, TileMode.CLAMP));
            return r10z ? new ClipDrawable(r1_Drawable, 3, 1) : r1_Drawable;
        }
    }

    private void a(int r7i, int r8i) {
        int r4i;
        int r3i = r7i - getPaddingRight() - getPaddingLeft();
        int r2i = r8i - getPaddingBottom() - getPaddingTop();
        if (this.p != null) {
            int r0i;
            if ((!this.m) || this.p instanceof AnimationDrawable) {
                r0i = 0;
                r4i = r3i;
                r3i = r2i;
                r2i = 0;
            } else {
                float r0f = ((float) this.p.getIntrinsicWidth()) / ((float) this.p.getIntrinsicHeight());
                float r4f = ((float) r7i) / ((float) r8i);
                if (r0f != r4f) {
                    if (r4f > r0f) {
                        r3i = (int) (r0f * ((float) r8i));
                        r0i = (r7i - r3i) / 2;
                        r4i = r3i + r0i;
                        r3i = r2i;
                        r2i = 0;
                    } else {
                        r2i = (int) ((1.0f / r0f) * ((float) r7i));
                        r0i = (r8i - r2i) / 2;
                        r4i = r3i;
                        r3i = r2i + r0i;
                        r2i = r0i;
                        r0i = 0;
                    }
                }
                r0i = 0;
                r4i = r3i;
                r3i = r2i;
                r2i = 0;
            }
            this.p.setBounds(r0i, r2i, r4i, r3i);
        } else {
            r4i = r3i;
            r3i = r2i;
        }
        if (this.q != null) {
            this.q.setBounds(0, 0, r4i, r3i);
        }
    }

    private synchronized void a(int r5i, int r6i, boolean r7z) {
        if (this.v == Thread.currentThread().getId()) {
            a(r5i, r6i, r7z, true);
        } else {
            Runnable r0_Runnable;
            if (this.u != null) {
                r0_Runnable = this.u;
                this.u = null;
                r0_Runnable.setup(r5i, r6i, r7z);
            } else {
                r0_Runnable = new a(r5i, r6i, r7z);
            }
            post(r0_Runnable);
        }
    }

    private synchronized void a(int r6i, int r7i, boolean r8z, boolean r9z) {
        float r3f;
        r3f = this.i > 0 ? ((float) r7i) / ((float) this.i) : 0.0f;
        Drawable r2_Drawable = this.r;
        if (r2_Drawable != null) {
            Drawable r1_Drawable = null;
            if (r2_Drawable instanceof LayerDrawable) {
                r1_Drawable = ((LayerDrawable) r2_Drawable).findDrawableByLayerId(r6i);
            }
            int r3i = (int) (r3f * 10000.0f);
            if (r1_Drawable != null) {
                r2_Drawable = r1_Drawable;
            }
            r2_Drawable.setLevel(r3i);
        } else {
            invalidate();
        }
    }

    private void d() {
        this.i = 100;
        this.g = 0;
        this.h = 0;
        this.l = false;
        this.m = false;
        this.k = 4000;
        this.j = 1;
        this.a = 24;
        this.b = 48;
        this.c = 24;
        this.d = 48;
    }

    private void e() {
        int[] r0_intA = getDrawableState();
        if (this.q == null || (!this.q.isStateful())) {
            if (this.p == null || (!this.p.isStateful())) {
            } else {
                this.p.setState(r0_intA);
            }
        } else {
            this.q.setState(r0_intA);
            if (this.p == null || this.p.isStateful()) {
            } else {
                this.p.setState(r0_intA);
            }
        }
    }

    Shape a() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    synchronized void a(int r3i, boolean r4z) {
        if (this.l) {
        } else {
            int r0i;
            r0i = r3i < 0 ? 0 : r3i;
            if (r0i > this.i) {
                r0i = this.i;
            }
            if (r0i != this.g) {
                this.g = r0i;
                a(16908301, this.g, r4z);
            }
        }
    }

    void b() {
        if (getVisibility() != 0) {
        } else {
            if (this.p instanceof Animatable) {
                this.w = true;
                this.o = null;
            } else {
                if (this.t == null) {
                    this.t = new LinearInterpolator();
                }
                this.n = new Transformation();
                this.o = new AlphaAnimation(0.0f, 1.0f);
                this.o.setRepeatMode(this.j);
                this.o.setRepeatCount(-1);
                this.o.setDuration((long) this.k);
                this.o.setInterpolator(this.t);
                this.o.setStartTime(-1);
            }
            postInvalidate();
        }
    }

    void c() {
        this.o = null;
        this.n = null;
        if (this.p instanceof Animatable) {
            ((Animatable) this.p).stop();
            this.w = false;
        }
        postInvalidate();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e();
    }

    public Drawable getIndeterminateDrawable() {
        return this.p;
    }

    public Interpolator getInterpolator() {
        return this.t;
    }

    public synchronized int getMax() {
        return this.i;
    }

    public synchronized int getProgress() {
        return this.l ? 0 : this.g;
    }

    public Drawable getProgressDrawable() {
        return this.q;
    }

    public synchronized int getSecondaryProgress() {
        return this.l ? 0 : this.h;
    }

    public final synchronized void incrementProgressBy(int r2i) {
        setProgress(this.g + r2i);
    }

    public final synchronized void incrementSecondaryProgressBy(int r2i) {
        setSecondaryProgress(this.h + r2i);
    }

    public void invalidateDrawable(Drawable r7_Drawable) {
        if (!this.y) {
            if (verifyDrawable(r7_Drawable)) {
                Rect r0_Rect = r7_Drawable.getBounds();
                int r1i = getScrollX() + getPaddingLeft();
                int r2i = getScrollY() + getPaddingTop();
                invalidate(r0_Rect.left + r1i, r0_Rect.top + r2i, r1i + r0_Rect.right, r0_Rect.bottom + r2i);
            } else {
                super.invalidateDrawable(r7_Drawable);
            }
        }
    }

    public synchronized boolean isIndeterminate() {
        return this.l;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.l) {
            b();
        }
    }

    protected void onDetachedFromWindow() {
        if (this.l) {
            c();
        }
        if (this.u != null) {
            removeCallbacks(this.u);
        }
        super.onDetachedFromWindow();
    }

    protected synchronized void onDraw(Canvas r8_Canvas) {
        try {
            super.onDraw(r8_Canvas);
            Drawable r0_Drawable = this.r;
            if (r0_Drawable != null) {
                r8_Canvas.save();
                r8_Canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
                long r1j = getDrawingTime();
                if (this.o != null) {
                    this.o.getTransformation(r1j, this.n);
                    float r1f = this.n.getAlpha();
                    this.y = true;
                    r0_Drawable.setLevel((int) (r1f * 10000.0f));
                    this.y = false;
                    if (SystemClock.uptimeMillis() - this.x >= 200) {
                        this.x = SystemClock.uptimeMillis();
                        postInvalidateDelayed(200);
                    }
                }
                r0_Drawable.draw(r8_Canvas);
                r8_Canvas.restore();
                if (this.w && r0_Drawable instanceof Animatable) {
                    ((Animatable) r0_Drawable).start();
                    this.w = false;
                }
            }
        } catch (Throwable th) {
        }
    }

    protected synchronized void onMeasure(int r5i, int r6i) {
        int r0i = 0;
        synchronized (this) {
            int r1i;
            Drawable r2_Drawable = this.r;
            if (r2_Drawable != null) {
                r1i = Math.max(this.a, Math.min(this.b, r2_Drawable.getIntrinsicWidth()));
                r0i = Math.max(this.c, Math.min(this.d, r2_Drawable.getIntrinsicHeight()));
            } else {
                r1i = 0;
            }
            e();
            setMeasuredDimension(resolveSize(r1i + getPaddingLeft() + getPaddingRight(), r5i), resolveSize(r0i + getPaddingTop() + getPaddingBottom(), r6i));
        }
    }

    public void onRestoreInstanceState(Parcelable r2_Parcelable) {
        SavedState r2_SavedState = (SavedState) r2_Parcelable;
        super.onRestoreInstanceState(r2_SavedState.getSuperState());
        setProgress(r2_SavedState.a);
        setSecondaryProgress(r2_SavedState.b);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable r1_Parcelable = new SavedState(super.onSaveInstanceState());
        r1_Parcelable.a = this.g;
        r1_Parcelable.b = this.h;
        return r1_Parcelable;
    }

    protected void onSizeChanged(int r1i, int r2i, int r3i, int r4i) {
        a(r1i, r2i);
    }

    protected void onVisibilityChanged(View r2_View, int r3i) {
        super.onVisibilityChanged(r2_View, r3i);
        if (this.l) {
            if (r3i == 8 || r3i == 4) {
                c();
            } else {
                b();
            }
        }
    }

    public void postInvalidate() {
        if (!this.s) {
            super.postInvalidate();
        }
    }

    public synchronized void setIndeterminate(boolean r2z) {
        if (!((this.m && this.l) || r2z == this.l)) {
            this.l = r2z;
            if (r2z) {
                this.r = this.p;
                b();
            } else {
                this.r = this.q;
                c();
            }
        }
    }

    public void setIndeterminateDrawable(Drawable r2_Drawable) {
        if (r2_Drawable != null) {
            r2_Drawable.setCallback(this);
        }
        this.p = r2_Drawable;
        if (this.l) {
            this.r = r2_Drawable;
            postInvalidate();
        }
    }

    public void setInterpolator(Context r2_Context, int r3i) {
        setInterpolator(AnimationUtils.loadInterpolator(r2_Context, r3i));
    }

    public void setInterpolator(Interpolator r1_Interpolator) {
        this.t = r1_Interpolator;
    }

    public synchronized void setMax(int r4i) {
        if (r4i < 0) {
            r4i = 0;
        }
        if (r4i != this.i) {
            this.i = r4i;
            postInvalidate();
            if (this.g > r4i) {
                this.g = r4i;
            }
            a(16908301, this.g, false);
        }
    }

    public synchronized void setProgress(int r2i) {
        a(r2i, false);
    }

    public void setProgressDrawable(Drawable r5_Drawable) {
        int r0i;
        if (this.q == null || r5_Drawable == this.q) {
            r0i = 0;
        } else {
            this.q.setCallback(null);
            r0i = 1;
        }
        if (r5_Drawable != null) {
            r5_Drawable.setCallback(this);
            int r2i = r5_Drawable.getMinimumHeight();
            if (this.d < r2i) {
                this.d = r2i;
                requestLayout();
            }
        }
        this.q = r5_Drawable;
        if (!this.l) {
            this.r = r5_Drawable;
            postInvalidate();
        }
        if (r0i != 0) {
            a(getWidth(), getHeight());
            e();
            a(16908301, this.g, false, false);
            a(16908303, this.h, false, false);
        }
    }

    public synchronized void setSecondaryProgress(int r4i) {
        int r0i = 0;
        synchronized (this) {
            if (this.l) {
            } else if (r4i < 0) {
                if (r0i <= this.i) {
                    r0i = this.i;
                }
                if (r0i != this.h) {
                    this.h = r0i;
                    a(16908303, this.h, false);
                }
            } else {
                r0i = r4i;
                if (r0i <= this.i) {
                    if (r0i != this.h) {
                    } else {
                        this.h = r0i;
                        a(16908303, this.h, false);
                    }
                } else {
                    r0i = this.i;
                    if (r0i != this.h) {
                        this.h = r0i;
                        a(16908303, this.h, false);
                    }
                }
            }
        }
    }

    public void setVisibility(int r2i) {
        if (getVisibility() != r2i) {
            super.setVisibility(r2i);
            if (this.l) {
                if (r2i == 8 || r2i == 4) {
                    c();
                } else {
                    b();
                }
            }
        }
    }

    protected boolean verifyDrawable(Drawable r2_Drawable) {
        return r2_Drawable == this.q || r2_Drawable == this.p || super.verifyDrawable(r2_Drawable);
    }
}