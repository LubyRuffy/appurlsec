package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class SlidingPaneLayout extends ViewGroup {
    static final d a;
    private int b;
    private int c;
    private Drawable d;
    private final int e;
    private boolean f;
    private View g;
    private float h;
    private float i;
    private int j;
    private boolean k;
    private int l;
    private float m;
    private float n;
    private PanelSlideListener o;
    private final ViewDragHelper p;
    private boolean q;
    private boolean r;
    private final Rect s;
    private final ArrayList<b> t;


    public static class LayoutParams extends MarginLayoutParams {
        private static final int[] d;
        boolean a;
        boolean b;
        Paint c;
        public float weight;

        static {
            int[] r0_intA = new int[1];
            r0_intA[0] = 16843137;
            d = r0_intA;
        }

        public LayoutParams() {
            super(-1, -1);
            this.weight = 0.0f;
        }

        public LayoutParams(int r2i, int r3i) {
            super(r2i, r3i);
            this.weight = 0.0f;
        }

        public LayoutParams(Context r4_Context, AttributeSet r5_AttributeSet) {
            super(r4_Context, r5_AttributeSet);
            this.weight = 0.0f;
            TypedArray r0_TypedArray = r4_Context.obtainStyledAttributes(r5_AttributeSet, d);
            this.weight = r0_TypedArray.getFloat(0, 0.0f);
            r0_TypedArray.recycle();
        }

        public LayoutParams(android.support.v4.widget.SlidingPaneLayout.LayoutParams r2_android_support_v4_widget_SlidingPaneLayout_LayoutParams) {
            super(r2_android_support_v4_widget_SlidingPaneLayout_LayoutParams);
            this.weight = 0.0f;
            this.weight = r2_android_support_v4_widget_SlidingPaneLayout_LayoutParams.weight;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
            super(r2_android_view_ViewGroup_LayoutParams);
            this.weight = 0.0f;
        }

        public LayoutParams(MarginLayoutParams r2_MarginLayoutParams) {
            super(r2_MarginLayoutParams);
            this.weight = 0.0f;
        }
    }

    public static interface PanelSlideListener {
        public void onPanelClosed(View r1_View);

        public void onPanelOpened(View r1_View);

        public void onPanelSlide(View r1_View, float r2f);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        boolean a;

        static {
            CREATOR = new p();
        }

        private SavedState(Parcel r2_Parcel) {
            super(r2_Parcel);
            this.a = r2_Parcel.readInt() != 0;
        }

        SavedState(Parcelable r1_Parcelable) {
            super(r1_Parcelable);
        }

        public void writeToParcel(Parcel r2_Parcel, int r3i) {
            super.writeToParcel(r2_Parcel, r3i);
            r2_Parcel.writeInt(this.a ? 1 : 0);
        }
    }

    public static class SimplePanelSlideListener implements android.support.v4.widget.SlidingPaneLayout.PanelSlideListener {
        public void onPanelClosed(View r1_View) {
        }

        public void onPanelOpened(View r1_View) {
        }

        public void onPanelSlide(View r1_View, float r2f) {
        }
    }

    class a extends AccessibilityDelegateCompat {
        private final Rect c;

        a() {
            this.c = new Rect();
        }

        private void a_(AccessibilityNodeInfoCompat r2_AccessibilityNodeInfoCompat, AccessibilityNodeInfoCompat r3_AccessibilityNodeInfoCompat) {
            Rect r0_Rect = this.c;
            r3_AccessibilityNodeInfoCompat.getBoundsInParent(r0_Rect);
            r2_AccessibilityNodeInfoCompat.setBoundsInParent(r0_Rect);
            r3_AccessibilityNodeInfoCompat.getBoundsInScreen(r0_Rect);
            r2_AccessibilityNodeInfoCompat.setBoundsInScreen(r0_Rect);
            r2_AccessibilityNodeInfoCompat.setVisibleToUser(r3_AccessibilityNodeInfoCompat.isVisibleToUser());
            r2_AccessibilityNodeInfoCompat.setPackageName(r3_AccessibilityNodeInfoCompat.getPackageName());
            r2_AccessibilityNodeInfoCompat.setClassName(r3_AccessibilityNodeInfoCompat.getClassName());
            r2_AccessibilityNodeInfoCompat.setContentDescription(r3_AccessibilityNodeInfoCompat.getContentDescription());
            r2_AccessibilityNodeInfoCompat.setEnabled(r3_AccessibilityNodeInfoCompat.isEnabled());
            r2_AccessibilityNodeInfoCompat.setClickable(r3_AccessibilityNodeInfoCompat.isClickable());
            r2_AccessibilityNodeInfoCompat.setFocusable(r3_AccessibilityNodeInfoCompat.isFocusable());
            r2_AccessibilityNodeInfoCompat.setFocused(r3_AccessibilityNodeInfoCompat.isFocused());
            r2_AccessibilityNodeInfoCompat.setAccessibilityFocused(r3_AccessibilityNodeInfoCompat.isAccessibilityFocused());
            r2_AccessibilityNodeInfoCompat.setSelected(r3_AccessibilityNodeInfoCompat.isSelected());
            r2_AccessibilityNodeInfoCompat.setLongClickable(r3_AccessibilityNodeInfoCompat.isLongClickable());
            r2_AccessibilityNodeInfoCompat.addAction(r3_AccessibilityNodeInfoCompat.getActions());
            r2_AccessibilityNodeInfoCompat.setMovementGranularities(r3_AccessibilityNodeInfoCompat.getMovementGranularities());
        }

        public boolean filter(View r2_View) {
            return SlidingPaneLayout.this.e(r2_View);
        }

        public void onInitializeAccessibilityEvent(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
            super.onInitializeAccessibilityEvent(r2_View, r3_AccessibilityEvent);
            r3_AccessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(View r5_View, AccessibilityNodeInfoCompat r6_AccessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat r0_AccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(r6_AccessibilityNodeInfoCompat);
            super.onInitializeAccessibilityNodeInfo(r5_View, r0_AccessibilityNodeInfoCompat);
            a(r6_AccessibilityNodeInfoCompat, r0_AccessibilityNodeInfoCompat);
            r0_AccessibilityNodeInfoCompat.recycle();
            r6_AccessibilityNodeInfoCompat.setClassName(SlidingPaneLayout.class.getName());
            r6_AccessibilityNodeInfoCompat.setSource(r5_View);
            ViewParent r0_ViewParent = ViewCompat.getParentForAccessibility(r5_View);
            if (r0_ViewParent instanceof View) {
                r6_AccessibilityNodeInfoCompat.setParent((View) r0_ViewParent);
            }
            int r1i = SlidingPaneLayout.this.getChildCount();
            int r0i = 0;
            while (r0i < r1i) {
                View r2_View = SlidingPaneLayout.this.getChildAt(r0i);
                if (filter(r2_View) || r2_View.getVisibility() != 0) {
                    r0i++;
                } else {
                    ViewCompat.setImportantForAccessibility(r2_View, 1);
                    r6_AccessibilityNodeInfoCompat.addChild(r2_View);
                    r0i++;
                }
            }
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup r2_ViewGroup, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
            return filter(r3_View) ? false : super.onRequestSendAccessibilityEvent(r2_ViewGroup, r3_View, r4_AccessibilityEvent);
        }
    }

    private class b implements Runnable {
        final View a;

        b(View r2_View) {
            this.a = r2_View;
        }

        public void run() {
            if (this.a.getParent() == SlidingPaneLayout.this) {
                ViewCompat.setLayerType(this.a, 0, null);
                SlidingPaneLayout.this.g(this.a);
            }
            SlidingPaneLayout.this.t.remove(this);
        }
    }

    private class c extends Callback {
        private c() {
        }

        public int clampViewPositionHorizontal(View r3_View, int r4i, int r5i) {
            int r0i = ((android.support.v4.widget.SlidingPaneLayout.LayoutParams) SlidingPaneLayout.this.g.getLayoutParams()).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
            return Math.min(Math.max(r4i, r0i), SlidingPaneLayout.this.j + r0i);
        }

        public int getViewHorizontalDragRange(View r2_View) {
            return SlidingPaneLayout.this.j;
        }

        public void onEdgeDragStarted(int r3i, int r4i) {
            SlidingPaneLayout.this.p.captureChildView(SlidingPaneLayout.this.g, r4i);
        }

        public void onViewCaptured(View r2_View, int r3i) {
            SlidingPaneLayout.this.a();
        }

        public void onViewDragStateChanged(int r3i) {
            if (SlidingPaneLayout.this.p.getViewDragState() == 0) {
                if (SlidingPaneLayout.this.h == 0.0f) {
                    SlidingPaneLayout.this.d(SlidingPaneLayout.this.g);
                    SlidingPaneLayout.this.c(SlidingPaneLayout.this.g);
                    SlidingPaneLayout.this.q = false;
                } else {
                    SlidingPaneLayout.this.b(SlidingPaneLayout.this.g);
                    SlidingPaneLayout.this.q = true;
                }
            }
        }

        public void onViewPositionChanged(View r2_View, int r3i, int r4i, int r5i, int r6i) {
            SlidingPaneLayout.this.a(r3i);
            SlidingPaneLayout.this.invalidate();
        }

        public void onViewReleased(View r4_View, float r5f, float r6f) {
            int r0i = ((android.support.v4.widget.SlidingPaneLayout.LayoutParams) r4_View.getLayoutParams()).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
            if (r5f <= 0.0f) {
                if (r5f != 0.0f || SlidingPaneLayout.this.h <= 0.5f) {
                    SlidingPaneLayout.this.p.settleCapturedViewAt(r0i, r4_View.getTop());
                    SlidingPaneLayout.this.invalidate();
                } else {
                    r0i += SlidingPaneLayout.this.j;
                }
            } else {
                r0i += SlidingPaneLayout.this.j;
            }
            SlidingPaneLayout.this.p.settleCapturedViewAt(r0i, r4_View.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        public boolean tryCaptureView(View r2_View, int r3i) {
            return SlidingPaneLayout.this.k ? false : SlidingPaneLayout.this;
        }
    }

    static interface d {
        public void invalidateChildRegion(SlidingPaneLayout r1_SlidingPaneLayout, View r2_View);
    }

    static class e implements d {
        e() {
        }

        public void invalidateChildRegion(SlidingPaneLayout r5_SlidingPaneLayout, View r6_View) {
            ViewCompat.postInvalidateOnAnimation(r5_SlidingPaneLayout, r6_View.getLeft(), r6_View.getTop(), r6_View.getRight(), r6_View.getBottom());
        }
    }

    static class f extends e {
        private Method a;
        private Field b;

        f() {
            try {
                this.a = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
            } catch (NoSuchMethodException e) {
                Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", e);
            }
            try {
                this.b = View.class.getDeclaredField("mRecreateDisplayList");
                this.b.setAccessible(true);
            } catch (NoSuchFieldException e_2) {
                Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e_2);
            }
        }

        public void invalidateChildRegion(SlidingPaneLayout r4_SlidingPaneLayout, View r5_View) {
            if (this.a == null || this.b == null) {
                r5_View.invalidate();
            } else {
                try {
                    this.b.setBoolean(r5_View, true);
                    this.a.invoke(r5_View, (Object[]) null);
                } catch (Exception e) {
                    Log.e("SlidingPaneLayout", "Error refreshing display list state", e);
                }
                super.invalidateChildRegion(r4_SlidingPaneLayout, r5_View);
            }
        }
    }

    static class g extends e {
        g() {
        }

        public void invalidateChildRegion(SlidingPaneLayout r2_SlidingPaneLayout, View r3_View) {
            ViewCompat.setLayerPaint(r3_View, ((android.support.v4.widget.SlidingPaneLayout.LayoutParams) r3_View.getLayoutParams()).c);
        }
    }

    static {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 17) {
            a = new g();
        } else if (r0i >= 16) {
            a = new f();
        } else {
            a = new e();
        }
    }

    public SlidingPaneLayout(Context r2_Context) {
        this(r2_Context, null);
    }

    public SlidingPaneLayout(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, 0);
    }

    public SlidingPaneLayout(Context r6_Context, AttributeSet r7_AttributeSet, int r8i) {
        super(r6_Context, r7_AttributeSet, r8i);
        this.b = -858993460;
        this.r = true;
        this.s = new Rect();
        this.t = new ArrayList();
        float r0f = r6_Context.getResources().getDisplayMetrics().density;
        this.e = (int) (32.0f * r0f + 0.5f);
        ViewConfiguration.get(r6_Context);
        setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(this, new a());
        ViewCompat.setImportantForAccessibility(this, 1);
        this.p = ViewDragHelper.create(this, 0.5f, new c(null));
        this.p.setEdgeTrackingEnabled(1);
        this.p.setMinVelocity(r0f * 400.0f);
    }

    private void a(float r9f) {
        int r0i;
        int r1i = 0;
        LayoutParams r0_LayoutParams = (LayoutParams) this.g.getLayoutParams();
        r0i = ((!r0_LayoutParams.b) || r0_LayoutParams.leftMargin > 0) ? 0 : 1;
        int r2i = getChildCount();
        while (r1i < r2i) {
            View r3_View = getChildAt(r1i);
            if (r3_View == this.g) {
                r1i++;
            } else {
                this.i = r9f;
                r3_View.offsetLeftAndRight(((int) ((1.0f - this.i) * ((float) this.l))) - ((int) ((1.0f - r9f) * ((float) this.l))));
                if (r0i != 0) {
                    a(r3_View, 1.0f - this.i, this.c);
                }
                r1i++;
            }
        }
    }

    private void a(int r4i) {
        LayoutParams r0_LayoutParams = (LayoutParams) this.g.getLayoutParams();
        this.h = ((float) (r4i - (getPaddingLeft() + r0_LayoutParams.leftMargin))) / ((float) this.j);
        if (this.l != 0) {
            a(this.h);
        }
        if (r0_LayoutParams.b) {
            a(this.g, this.h, this.b);
        }
        a(this.g);
    }

    private void a(View r7_View, float r8f, int r9i) {
        LayoutParams r0_LayoutParams = (LayoutParams) r7_View.getLayoutParams();
        if (r8f <= 0.0f || r9i == 0) {
            if (ViewCompat.getLayerType(r7_View) != 0) {
                if (r0_LayoutParams.c != null) {
                    r0_LayoutParams.c.setColorFilter(null);
                }
                Runnable r0_Runnable = new b(r7_View);
                this.t.add(r0_Runnable);
                ViewCompat.postOnAnimation(this, r0_Runnable);
            }
        } else {
            int r1i = (((int) (((float) ((-16777216 & r9i) >>> 24)) * r8f)) << 24) | (16777215 & r9i);
            if (r0_LayoutParams.c == null) {
                r0_LayoutParams.c = new Paint();
            }
            r0_LayoutParams.c.setColorFilter(new PorterDuffColorFilter(r1i, Mode.SRC_OVER));
            if (ViewCompat.getLayerType(r7_View) != 2) {
                ViewCompat.setLayerType(r7_View, XListViewHeader.STATE_REFRESHING, r0_LayoutParams.c);
            }
            g(r7_View);
        }
    }

    private boolean a(View r3_View, int r4i) {
        boolean r0z = false;
        if (!(this.r) && !a(0.0f, r4i)) {
            return false;
        }
        this.q = r0z;
        return true;
    }

    private boolean b(View r3_View, int r4i) {
        if (!(this.r) && !a(1.0f, r4i)) {
            return false;
        }
        this.q = true;
        return true;
    }

    private static boolean f(View r4_View) {
        if (ViewCompat.isOpaque(r4_View)) {
            return true;
        }
        if (VERSION.SDK_INT >= 18) {
            return false;
        }
        Drawable r2_Drawable = r4_View.getBackground();
        if (r2_Drawable == null) {
            return false;
        }
        if (r2_Drawable.getOpacity() != -1) {
            return false;
        }
        return true;
    }

    private void g(View r2_View) {
        a.invalidateChildRegion(this, r2_View);
    }

    void a() {
        int r2i = getChildCount();
        int r0i = 0;
        while (r0i < r2i) {
            View r3_View = getChildAt(r0i);
            if (r3_View.getVisibility() == 4) {
                r3_View.setVisibility(0);
            }
            r0i++;
        }
    }

    void a(View r3_View) {
        if (this.o != null) {
            this.o.onPanelSlide(r3_View, this.h);
        }
    }

    boolean a(float r6f, int r7i) {
        if (!(this.f)) {
            return false;
        }
        if (!this.p.smoothSlideViewTo(this.g, (int) (((float) (((LayoutParams) this.g.getLayoutParams()).leftMargin + getPaddingLeft())) + ((float) this.j) * r6f), this.g.getTop())) {
            return false;
        }
        a();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    void b(View r2_View) {
        if (this.o != null) {
            this.o.onPanelOpened(r2_View);
        }
        sendAccessibilityEvent(Base64.ORDERED);
    }

    void c(View r2_View) {
        if (this.o != null) {
            this.o.onPanelClosed(r2_View);
        }
        sendAccessibilityEvent(Base64.ORDERED);
    }

    public boolean canSlide() {
        return this.f;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        return r2_android_view_ViewGroup_LayoutParams instanceof LayoutParams && super.checkLayoutParams(r2_android_view_ViewGroup_LayoutParams);
    }

    public boolean closePane() {
        return a(this.g, 0);
    }

    public void computeScroll() {
        if (this.p.continueSettling(true)) {
            if (this.f) {
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                this.p.abort();
            }
        }
    }

    void d(View r17_View) {
        int r4i;
        int r7i = getPaddingLeft();
        int r8i = getWidth() - getPaddingRight();
        int r9i = getPaddingTop();
        int r10i = getHeight() - getPaddingBottom();
        if (r17_View == null || (!f(r17_View))) {
            r1i = 0;
            r2i = 0;
            r3i = 0;
            r4i = 0;
        } else {
            r4i = r17_View.getLeft();
            r3i = r17_View.getRight();
            r2i = r17_View.getTop();
            r1i = r17_View.getBottom();
        }
        int r11i = getChildCount();
        int r6i = 0;
        while (r6i < r11i) {
            View r12_View = getChildAt(r6i);
            if (r12_View == r17_View) {
                return;
            }
            int r5i = Math.max(r7i, r12_View.getLeft());
            int r13i = Math.max(r9i, r12_View.getTop());
            int r14i = Math.min(r8i, r12_View.getRight());
            int r15i = Math.min(r10i, r12_View.getBottom());
            if (r5i < r4i || r13i < r2i || r14i > r3i || r15i > r1i) {
                r5i = 0;
                r12_View.setVisibility(r5i);
                r6i++;
            } else {
                r5i = XListViewFooter.STATE_NODATA;
                r12_View.setVisibility(r5i);
                r6i++;
            }
        }
    }

    public void draw(Canvas r6_Canvas) {
        View r0_View;
        super.draw(r6_Canvas);
        r0_View = getChildCount() > 1 ? getChildAt(1) : null;
        if (r0_View == null || this.d == null) {
        } else {
            int r1i = this.d.getIntrinsicWidth();
            int r2i = r0_View.getLeft();
            this.d.setBounds(r2i - r1i, r0_View.getTop(), r2i, r0_View.getBottom());
            this.d.draw(r6_Canvas);
        }
    }

    protected boolean drawChild(Canvas r7_Canvas, View r8_View, long r9j) {
        LayoutParams r0_LayoutParams = (LayoutParams) r8_View.getLayoutParams();
        int r2i = r7_Canvas.save(XListViewHeader.STATE_REFRESHING);
        boolean r0z;
        Bitmap r3_Bitmap;
        if ((!this.f) || r0_LayoutParams.a || this.g == null) {
            if (VERSION.SDK_INT < 11) {
                r0z = super.drawChild(r7_Canvas, r8_View, r9j);
            } else if ((!r0_LayoutParams.b) || this.h <= 0.0f) {
                if (!r8_View.isDrawingCacheEnabled()) {
                    r8_View.setDrawingCacheEnabled(false);
                }
                r0z = super.drawChild(r7_Canvas, r8_View, r9j);
            } else {
                if (r8_View.isDrawingCacheEnabled()) {
                    r8_View.setDrawingCacheEnabled(true);
                }
                r3_Bitmap = r8_View.getDrawingCache();
                if (r3_Bitmap != null) {
                    r7_Canvas.drawBitmap(r3_Bitmap, (float) r8_View.getLeft(), (float) r8_View.getTop(), r0_LayoutParams.c);
                    r0z = false;
                } else {
                    Log.e("SlidingPaneLayout", "drawChild: child view " + r8_View + " returned null drawing cache");
                    r0z = super.drawChild(r7_Canvas, r8_View, r9j);
                }
            }
            r7_Canvas.restoreToCount(r2i);
            return r0z;
        } else {
            r7_Canvas.getClipBounds(this.s);
            this.s.right = Math.min(this.s.right, this.g.getLeft());
            r7_Canvas.clipRect(this.s);
            if (VERSION.SDK_INT < 11) {
                if (r0_LayoutParams.b || this.h <= 0.0f) {
                    if (r8_View.isDrawingCacheEnabled()) {
                        r0z = super.drawChild(r7_Canvas, r8_View, r9j);
                    } else {
                        r8_View.setDrawingCacheEnabled(false);
                        r0z = super.drawChild(r7_Canvas, r8_View, r9j);
                    }
                } else if (r8_View.isDrawingCacheEnabled()) {
                    r3_Bitmap = r8_View.getDrawingCache();
                    if (r3_Bitmap != null) {
                        Log.e("SlidingPaneLayout", "drawChild: child view " + r8_View + " returned null drawing cache");
                        r0z = super.drawChild(r7_Canvas, r8_View, r9j);
                    } else {
                        r7_Canvas.drawBitmap(r3_Bitmap, (float) r8_View.getLeft(), (float) r8_View.getTop(), r0_LayoutParams.c);
                        r0z = false;
                    }
                } else {
                    r8_View.setDrawingCacheEnabled(true);
                    r3_Bitmap = r8_View.getDrawingCache();
                    if (r3_Bitmap != null) {
                        r7_Canvas.drawBitmap(r3_Bitmap, (float) r8_View.getLeft(), (float) r8_View.getTop(), r0_LayoutParams.c);
                        r0z = false;
                    } else {
                        Log.e("SlidingPaneLayout", "drawChild: child view " + r8_View + " returned null drawing cache");
                        r0z = super.drawChild(r7_Canvas, r8_View, r9j);
                    }
                }
            } else {
                r0z = super.drawChild(r7_Canvas, r8_View, r9j);
            }
            r7_Canvas.restoreToCount(r2i);
            return r0z;
        }
    }

    boolean e(View r4_View) {
        if (r4_View == null) {
            return false;
        }
        LayoutParams r0_LayoutParams = (LayoutParams) r4_View.getLayoutParams();
        boolean r0z;
        if (this.f && r0_LayoutParams.b && this.h > 0.0f) {
            r0z = true;
            return r0z;
        } else {
            r0z = false;
            return r0z;
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet r3_AttributeSet) {
        return new LayoutParams(getContext(), r3_AttributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        return r2_android_view_ViewGroup_LayoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) r2_android_view_ViewGroup_LayoutParams) : new LayoutParams(r2_android_view_ViewGroup_LayoutParams);
    }

    public int getCoveredFadeColor() {
        return this.c;
    }

    public int getParallaxDistance() {
        return this.l;
    }

    public int getSliderFadeColor() {
        return this.b;
    }

    public boolean isOpen() {
        return (!this.f) || this.h == 1.0f;
    }

    public boolean isSlideable() {
        return this.f;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.r = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.r = true;
        int r2i = this.t.size();
        int r1i = 0;
        while (r1i < r2i) {
            ((b) this.t.get(r1i)).run();
            r1i++;
        }
        this.t.clear();
    }

    public boolean onInterceptTouchEvent(MotionEvent r8_MotionEvent) {
        int r3i = MotionEventCompat.getActionMasked(r8_MotionEvent);
        float r0f;
        float r3f;
        if (this.f || r3i != 0 || getChildCount() <= 1) {
            if (!this.f) {
                if ((!this.k) || r3i == 0) {
                    if (r3i == 3 || r3i == 1) {
                        this.p.cancel();
                        return false;
                    } else {
                        switch (r3i) {
                            case XListViewHeader.STATE_NORMAL:
                                this.k = false;
                                r0f = r8_MotionEvent.getX();
                                r3f = r8_MotionEvent.getY();
                                this.m = r0f;
                                this.n = r3f;
                                r0i = (this.p.isViewUnder(this.g, (int) r0f, (int) r3f) && e(this.g)) ? 1 : 0;
                                return this.p.shouldInterceptTouchEvent(r8_MotionEvent) || r0i != 0;
                            case XListViewHeader.STATE_REFRESHING:
                                r0f = r8_MotionEvent.getX();
                                r3f = r8_MotionEvent.getY();
                                r0f = Math.abs(r0f - this.m);
                                r3f = Math.abs(r3f - this.n);
                                if (r0f <= ((float) this.p.getTouchSlop()) || r3f <= r0f) {
                                } else {
                                    this.p.cancel();
                                    this.k = true;
                                    return false;
                                }
                        }
                        if (this.p.shouldInterceptTouchEvent(r8_MotionEvent) || r0i != 0) {
                        }
                    }
                }
            }
            this.p.cancel();
            return super.onInterceptTouchEvent(r8_MotionEvent);
        } else {
            View r0_View = getChildAt(1);
            if (r0_View != null) {
                this.q = !this.p.isViewUnder(r0_View, (int) r8_MotionEvent.getX(), (int) r8_MotionEvent.getY());
            }
            if (this.f) {
                this.p.cancel();
                return super.onInterceptTouchEvent(r8_MotionEvent);
            } else if (this.k || r3i == 0) {
                if (r3i == 3 || r3i == 1) {
                    this.p.cancel();
                    return false;
                } else {
                    switch (r3i) {
                        case XListViewHeader.STATE_NORMAL:
                            this.k = false;
                            r0f = r8_MotionEvent.getX();
                            r3f = r8_MotionEvent.getY();
                            this.m = r0f;
                            this.n = r3f;
                            if (this.p.isViewUnder(this.g, (int) r0f, (int) r3f) || e(this.g)) {
                            }
                            if (this.p.shouldInterceptTouchEvent(r8_MotionEvent) || r0i != 0) {
                            }
                        case XListViewHeader.STATE_REFRESHING:
                            r0f = r8_MotionEvent.getX();
                            r3f = r8_MotionEvent.getY();
                            r0f = Math.abs(r0f - this.m);
                            r3f = Math.abs(r3f - this.n);
                            if (r0f <= ((float) this.p.getTouchSlop()) || r3f <= r0f) {
                            } else {
                                this.p.cancel();
                                this.k = true;
                                return false;
                            }
                    }
                    if (this.p.shouldInterceptTouchEvent(r8_MotionEvent) || r0i != 0) {
                    }
                }
            } else {
                this.p.cancel();
                return super.onInterceptTouchEvent(r8_MotionEvent);
            }
        }
    }

    protected void onLayout(boolean r15z, int r16i, int r17i, int r18i, int r19i) {
        int r0i;
        int r6i = r18i - r16i;
        int r3i = getPaddingLeft();
        int r7i = getPaddingRight();
        int r8i = getPaddingTop();
        int r9i = getChildCount();
        if (this.r) {
            float r0f;
            r0f = (this.f && this.q) ? 1.0f : 0.0f;
            this.h = r0f;
        }
        int r5i = 0;
        int r4i = r3i;
        while (r5i < r9i) {
            int r1i;
            View r10_View = getChildAt(r5i);
            if (r10_View.getVisibility() == 8) {
                r1i = r4i;
            } else {
                LayoutParams r0_LayoutParams = (LayoutParams) r10_View.getLayoutParams();
                int r11i = r10_View.getMeasuredWidth();
                if (r0_LayoutParams.a) {
                    int r12i = Math.min(r3i, r6i - r7i - this.e) - r4i - r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin;
                    this.j = r12i;
                    r0_LayoutParams.b = ((r0_LayoutParams.leftMargin + r4i) + r12i) + (r11i / 2) > r6i - r7i;
                    r1i = r0_LayoutParams.leftMargin + ((int) (((float) r12i) * this.h)) + r4i;
                    r0i = 0;
                } else if ((!this.f) || this.l == 0) {
                    r0i = 0;
                    r1i = r3i;
                } else {
                    r0i = (int) ((1.0f - this.h) * ((float) this.l));
                    r1i = r3i;
                }
                r0i = r1i - r0i;
                r10_View.layout(r0i, r8i, r0i + r11i, r10_View.getMeasuredHeight() + r8i);
                r3i += r10_View.getWidth();
            }
            r5i++;
            r4i = r1i;
        }
        if (this.r) {
            if (this.f) {
                if (this.l != 0) {
                    a(this.h);
                }
                if (((LayoutParams) this.g.getLayoutParams()).b) {
                    a(this.g, this.h, this.b);
                }
            } else {
                r0i = 0;
                while (r0i < r9i) {
                    a(getChildAt(r0i), 0.0f, this.b);
                    r0i++;
                }
            }
            d(this.g);
        }
        this.r = false;
    }

    protected void onMeasure(int r16i, int r17i) {
        int r9i;
        int r10i;
        boolean r6z;
        int r5i;
        int r11i;
        int r8i;
        int r7i;
        float r2f;
        View r12_View;
        LayoutParams r0_LayoutParams;
        float r3f;
        int r4i;
        boolean r2z;
        boolean r3z;
        int r12i;
        View r13_View;
        int r3i = MeasureSpec.getMode(r16i);
        int r2i = MeasureSpec.getSize(r16i);
        int r1i = MeasureSpec.getMode(r17i);
        int r0i = MeasureSpec.getSize(r17i);
        if (r3i != 1073741824) {
            if (isInEditMode()) {
                if (r3i == -2147483648) {
                    r9i = r1i;
                    r10i = r2i;
                    r2i = r0i;
                } else {
                    if (r3i == 0) {
                        r9i = r1i;
                        r10i = 300;
                        r2i = r0i;
                    }
                    r9i = r1i;
                    r10i = r2i;
                    r2i = r0i;
                }
            } else {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            }
        } else {
            if (r1i == 0) {
                if (isInEditMode()) {
                    if (r1i == 0) {
                        r9i = -2147483648;
                        r10i = r2i;
                        r2i = 300;
                    }
                } else {
                    throw new IllegalStateException("Height must not be UNSPECIFIED");
                }
            }
            r9i = r1i;
            r10i = r2i;
            r2i = r0i;
        }
        r1i = 0;
        r0i = -1;
        switch (r9i) {
            case ExploreByTouchHelper.INVALID_ID:
                r0i = r1i;
                r1i = r2i - getPaddingTop() - getPaddingBottom();
                r6z = false;
                r5i = r10i - getPaddingLeft() - getPaddingRight();
                r11i = getChildCount();
                if (r11i > XListViewHeader.STATE_REFRESHING) {
                    Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
                }
                this.g = null;
                r8i = 0;
                r7i = r0i;
                r2f = 0.0f;
                while (r8i < r11i) {
                    r12_View = getChildAt(r8i);
                    r0_LayoutParams = (LayoutParams) r12_View.getLayoutParams();
                    if (r12_View.getVisibility() != 8) {
                        r0_LayoutParams.b = false;
                        r0i = r5i;
                        r3f = r2f;
                        r4i = r7i;
                        r2z = r6z;
                    } else {
                        if (r0_LayoutParams.weight <= 0.0f) {
                            r2f += r0_LayoutParams.weight;
                            if (r0_LayoutParams.width != 0) {
                                r0i = r5i;
                                r3f = r2f;
                                r4i = r7i;
                                r2z = r6z;
                            }
                        }
                        r3i = r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin;
                        if (r0_LayoutParams.width != -2) {
                            r3i = MeasureSpec.makeMeasureSpec(r10i - r3i, ExploreByTouchHelper.INVALID_ID);
                        } else if (r0_LayoutParams.width != -1) {
                        }
                        if (r0_LayoutParams.height != -2) {
                            r4i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                        } else if (r0_LayoutParams.height != -1) {
                        }
                        r12_View.measure(r3i, r4i);
                        r3i = r12_View.getMeasuredWidth();
                        r4i = r12_View.getMeasuredHeight();
                        if (r9i != -2147483648 || r4i <= r7i) {
                            r4i = r5i - r3i;
                            r3z = r4i >= 0;
                            r0_LayoutParams.a = r3z;
                            r3z |= r6z;
                            if (!r0_LayoutParams.a) {
                                this.g = r12_View;
                            }
                            r0i = r4i;
                            r4i = r7i;
                            r2z = r3z;
                            r3f = r2f;
                        } else {
                            r7i = Math.min(r4i, r1i);
                            r4i = r5i - r3i;
                            if (r4i >= 0) {
                            }
                            r0_LayoutParams.a = r3z;
                            r3z |= r6z;
                            if (r0_LayoutParams.a) {
                                r0i = r4i;
                                r4i = r7i;
                                r2z = r3z;
                                r3f = r2f;
                            } else {
                                this.g = r12_View;
                                r0i = r4i;
                                r4i = r7i;
                                r2z = r3z;
                                r3f = r2f;
                            }
                        }
                    }
                    r8i++;
                    r6z = r2z;
                    r7i = r4i;
                    r2f = r3f;
                    r5i = r0i;
                }
                if (r6z || r2f > 0.0f) {
                    r12i = r10i - this.e;
                    r9i = 0;
                    while (r9i < r11i) {
                        r13_View = getChildAt(r9i);
                        if (r13_View.getVisibility() != 8) {
                            r9i++;
                        } else {
                            r0_LayoutParams = (LayoutParams) r13_View.getLayoutParams();
                            if (r13_View.getVisibility() == 8) {
                                r8i = (r0_LayoutParams.width != 0 || r0_LayoutParams.weight <= 0.0f) ? 0 : 1;
                                r4i = r8i == 0 ? 0 : r13_View.getMeasuredWidth();
                                if ((!r6z) || r13_View == this.g) {
                                    if (r0_LayoutParams.weight <= 0.0f) {
                                        if (r0_LayoutParams.width != 0) {
                                            if (r0_LayoutParams.height != -2) {
                                                r3i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                            } else if (r0_LayoutParams.height != -1) {
                                            }
                                        } else {
                                            r3i = MeasureSpec.makeMeasureSpec(r13_View.getMeasuredHeight(), 1073741824);
                                        }
                                        if (r6z) {
                                            r13_View.measure(MeasureSpec.makeMeasureSpec(((int) ((r0_LayoutParams.weight * ((float) Math.max(0, r5i))) / r2f)) + r4i, 1073741824), r3i);
                                        } else {
                                            r0i = r10i - r0_LayoutParams.rightMargin + r0_LayoutParams.leftMargin;
                                            r8i = MeasureSpec.makeMeasureSpec(r0i, 1073741824);
                                            if (r4i == r0i) {
                                                r13_View.measure(r8i, r3i);
                                            }
                                        }
                                    }
                                } else if (r0_LayoutParams.width >= 0) {
                                    if (r4i > r12i || r0_LayoutParams.weight > 0.0f) {
                                        if (r8i == 0) {
                                            if (r0_LayoutParams.height != -2) {
                                                r0i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                            } else if (r0_LayoutParams.height != -1) {
                                            }
                                        } else {
                                            r0i = MeasureSpec.makeMeasureSpec(r13_View.getMeasuredHeight(), 1073741824);
                                        }
                                        r13_View.measure(MeasureSpec.makeMeasureSpec(r12i, 1073741824), r0i);
                                    }
                                }
                            }
                            r9i++;
                        }
                    }
                    setMeasuredDimension(r10i, r7i);
                    this.f = r6z;
                    if (this.p.getViewDragState() == 0 || r6z) {
                    } else {
                        this.p.abort();
                    }
                } else {
                    setMeasuredDimension(r10i, r7i);
                    this.f = r6z;
                    if (this.p.getViewDragState() == 0 || r6z) {
                    } else {
                        this.p.abort();
                    }
                }
            case 1073741824:
                r0i = r2i - getPaddingTop() - getPaddingBottom();
                r1i = r0i;
                r6z = false;
                r5i = r10i - getPaddingLeft() - getPaddingRight();
                r11i = getChildCount();
                if (r11i > XListViewHeader.STATE_REFRESHING) {
                    this.g = null;
                    r8i = 0;
                    r7i = r0i;
                    r2f = 0.0f;
                    while (r8i < r11i) {
                        r12_View = getChildAt(r8i);
                        r0_LayoutParams = (LayoutParams) r12_View.getLayoutParams();
                        if (r12_View.getVisibility() != 8) {
                            if (r0_LayoutParams.weight <= 0.0f) {
                                r3i = r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin;
                                if (r0_LayoutParams.width != -2) {
                                    r3i = r0_LayoutParams.width != -1 ? MeasureSpec.makeMeasureSpec(r0_LayoutParams.width, 1073741824) : MeasureSpec.makeMeasureSpec(r10i - r3i, 1073741824);
                                } else {
                                    r3i = MeasureSpec.makeMeasureSpec(r10i - r3i, ExploreByTouchHelper.INVALID_ID);
                                }
                                if (r0_LayoutParams.height != -2) {
                                    r4i = r0_LayoutParams.height != -1 ? MeasureSpec.makeMeasureSpec(r0_LayoutParams.height, 1073741824) : MeasureSpec.makeMeasureSpec(r1i, 1073741824);
                                } else {
                                    r4i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                }
                                r12_View.measure(r3i, r4i);
                                r3i = r12_View.getMeasuredWidth();
                                r4i = r12_View.getMeasuredHeight();
                                if (r9i != -2147483648 || r4i <= r7i) {
                                    r4i = r5i - r3i;
                                    if (r4i >= 0) {
                                    }
                                    r0_LayoutParams.a = r3z;
                                    r3z |= r6z;
                                    if (r0_LayoutParams.a) {
                                        this.g = r12_View;
                                    }
                                    r0i = r4i;
                                    r4i = r7i;
                                    r2z = r3z;
                                    r3f = r2f;
                                } else {
                                    r7i = Math.min(r4i, r1i);
                                    r4i = r5i - r3i;
                                    if (r4i >= 0) {
                                    }
                                    r0_LayoutParams.a = r3z;
                                    r3z |= r6z;
                                    if (r0_LayoutParams.a) {
                                        r0i = r4i;
                                        r4i = r7i;
                                        r2z = r3z;
                                        r3f = r2f;
                                    } else {
                                        this.g = r12_View;
                                        r0i = r4i;
                                        r4i = r7i;
                                        r2z = r3z;
                                        r3f = r2f;
                                    }
                                }
                            } else {
                                r2f += r0_LayoutParams.weight;
                                if (r0_LayoutParams.width != 0) {
                                    r3i = r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin;
                                    if (r0_LayoutParams.width != -2) {
                                        r3i = MeasureSpec.makeMeasureSpec(r10i - r3i, ExploreByTouchHelper.INVALID_ID);
                                    } else if (r0_LayoutParams.width != -1) {
                                    }
                                    if (r0_LayoutParams.height != -2) {
                                        r4i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                    } else if (r0_LayoutParams.height != -1) {
                                    }
                                    r12_View.measure(r3i, r4i);
                                    r3i = r12_View.getMeasuredWidth();
                                    r4i = r12_View.getMeasuredHeight();
                                    if (r9i != -2147483648 || r4i <= r7i) {
                                        r4i = r5i - r3i;
                                        if (r4i >= 0) {
                                        }
                                        r0_LayoutParams.a = r3z;
                                        r3z |= r6z;
                                        if (r0_LayoutParams.a) {
                                            this.g = r12_View;
                                        }
                                        r0i = r4i;
                                        r4i = r7i;
                                        r2z = r3z;
                                        r3f = r2f;
                                    } else {
                                        r7i = Math.min(r4i, r1i);
                                        r4i = r5i - r3i;
                                        if (r4i >= 0) {
                                        }
                                        r0_LayoutParams.a = r3z;
                                        r3z |= r6z;
                                        if (r0_LayoutParams.a) {
                                            r0i = r4i;
                                            r4i = r7i;
                                            r2z = r3z;
                                            r3f = r2f;
                                        } else {
                                            this.g = r12_View;
                                            r0i = r4i;
                                            r4i = r7i;
                                            r2z = r3z;
                                            r3f = r2f;
                                        }
                                    }
                                } else {
                                    r0i = r5i;
                                    r3f = r2f;
                                    r4i = r7i;
                                    r2z = r6z;
                                }
                            }
                        } else {
                            r0_LayoutParams.b = false;
                            r0i = r5i;
                            r3f = r2f;
                            r4i = r7i;
                            r2z = r6z;
                        }
                        r8i++;
                        r6z = r2z;
                        r7i = r4i;
                        r2f = r3f;
                        r5i = r0i;
                    }
                    if (r6z || r2f > 0.0f) {
                        r12i = r10i - this.e;
                        r9i = 0;
                        while (r9i < r11i) {
                            r13_View = getChildAt(r9i);
                            if (r13_View.getVisibility() != 8) {
                                r0_LayoutParams = (LayoutParams) r13_View.getLayoutParams();
                                if (r13_View.getVisibility() == 8) {
                                    r9i++;
                                } else {
                                    if (r0_LayoutParams.width != 0 || r0_LayoutParams.weight <= 0.0f) {
                                    }
                                    if (r8i == 0) {
                                    }
                                    if (r6z || r13_View == this.g) {
                                        if (r0_LayoutParams.weight <= 0.0f) {
                                            r9i++;
                                        } else {
                                            if (r0_LayoutParams.width != 0) {
                                                r3i = MeasureSpec.makeMeasureSpec(r13_View.getMeasuredHeight(), 1073741824);
                                            } else if (r0_LayoutParams.height != -2) {
                                                r3i = r0_LayoutParams.height != -1 ? MeasureSpec.makeMeasureSpec(r0_LayoutParams.height, 1073741824) : MeasureSpec.makeMeasureSpec(r1i, 1073741824);
                                            } else {
                                                r3i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                            }
                                            if (r6z) {
                                                r13_View.measure(MeasureSpec.makeMeasureSpec(((int) ((r0_LayoutParams.weight * ((float) Math.max(0, r5i))) / r2f)) + r4i, 1073741824), r3i);
                                            } else {
                                                r0i = r10i - r0_LayoutParams.rightMargin + r0_LayoutParams.leftMargin;
                                                r8i = MeasureSpec.makeMeasureSpec(r0i, 1073741824);
                                                if (r4i == r0i) {
                                                    r9i++;
                                                } else {
                                                    r13_View.measure(r8i, r3i);
                                                }
                                            }
                                        }
                                    } else if (r0_LayoutParams.width >= 0) {
                                        r9i++;
                                    } else if (r4i > r12i || r0_LayoutParams.weight > 0.0f) {
                                        if (r8i == 0) {
                                            r0i = MeasureSpec.makeMeasureSpec(r13_View.getMeasuredHeight(), 1073741824);
                                        } else if (r0_LayoutParams.height != -2) {
                                            r0i = r0_LayoutParams.height != -1 ? MeasureSpec.makeMeasureSpec(r0_LayoutParams.height, 1073741824) : MeasureSpec.makeMeasureSpec(r1i, 1073741824);
                                        } else {
                                            r0i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                        }
                                        r13_View.measure(MeasureSpec.makeMeasureSpec(r12i, 1073741824), r0i);
                                    }
                                }
                            }
                            r9i++;
                        }
                        setMeasuredDimension(r10i, r7i);
                        this.f = r6z;
                        if (this.p.getViewDragState() == 0 || r6z) {
                        } else {
                            this.p.abort();
                        }
                    } else {
                        setMeasuredDimension(r10i, r7i);
                        this.f = r6z;
                        if (this.p.getViewDragState() == 0 || r6z) {
                        } else {
                            this.p.abort();
                        }
                    }
                } else {
                    Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
                    this.g = null;
                    r8i = 0;
                    r7i = r0i;
                    r2f = 0.0f;
                    while (r8i < r11i) {
                        r12_View = getChildAt(r8i);
                        r0_LayoutParams = (LayoutParams) r12_View.getLayoutParams();
                        if (r12_View.getVisibility() != 8) {
                            r0_LayoutParams.b = false;
                            r0i = r5i;
                            r3f = r2f;
                            r4i = r7i;
                            r2z = r6z;
                        } else {
                            if (r0_LayoutParams.weight <= 0.0f) {
                                r2f += r0_LayoutParams.weight;
                                if (r0_LayoutParams.width != 0) {
                                    r0i = r5i;
                                    r3f = r2f;
                                    r4i = r7i;
                                    r2z = r6z;
                                }
                            }
                            r3i = r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin;
                            if (r0_LayoutParams.width != -2) {
                                if (r0_LayoutParams.width != -1) {
                                }
                            } else {
                                r3i = MeasureSpec.makeMeasureSpec(r10i - r3i, ExploreByTouchHelper.INVALID_ID);
                            }
                            if (r0_LayoutParams.height != -2) {
                                if (r0_LayoutParams.height != -1) {
                                }
                            } else {
                                r4i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                            }
                            r12_View.measure(r3i, r4i);
                            r3i = r12_View.getMeasuredWidth();
                            r4i = r12_View.getMeasuredHeight();
                            if (r9i != -2147483648 || r4i <= r7i) {
                                r4i = r5i - r3i;
                                if (r4i >= 0) {
                                }
                                r0_LayoutParams.a = r3z;
                                r3z |= r6z;
                                if (r0_LayoutParams.a) {
                                    this.g = r12_View;
                                }
                                r0i = r4i;
                                r4i = r7i;
                                r2z = r3z;
                                r3f = r2f;
                            } else {
                                r7i = Math.min(r4i, r1i);
                                r4i = r5i - r3i;
                                if (r4i >= 0) {
                                }
                                r0_LayoutParams.a = r3z;
                                r3z |= r6z;
                                if (r0_LayoutParams.a) {
                                    r0i = r4i;
                                    r4i = r7i;
                                    r2z = r3z;
                                    r3f = r2f;
                                } else {
                                    this.g = r12_View;
                                    r0i = r4i;
                                    r4i = r7i;
                                    r2z = r3z;
                                    r3f = r2f;
                                }
                            }
                        }
                        r8i++;
                        r6z = r2z;
                        r7i = r4i;
                        r2f = r3f;
                        r5i = r0i;
                    }
                    if (r6z || r2f > 0.0f) {
                        r12i = r10i - this.e;
                        r9i = 0;
                        while (r9i < r11i) {
                            r13_View = getChildAt(r9i);
                            if (r13_View.getVisibility() != 8) {
                                r9i++;
                            } else {
                                r0_LayoutParams = (LayoutParams) r13_View.getLayoutParams();
                                if (r13_View.getVisibility() == 8) {
                                    if (r0_LayoutParams.width != 0 || r0_LayoutParams.weight <= 0.0f) {
                                    }
                                    if (r8i == 0) {
                                    }
                                    if (r6z || r13_View == this.g) {
                                        if (r0_LayoutParams.weight <= 0.0f) {
                                            if (r0_LayoutParams.width != 0) {
                                                if (r0_LayoutParams.height != -2) {
                                                    r3i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                                } else if (r0_LayoutParams.height != -1) {
                                                }
                                            } else {
                                                r3i = MeasureSpec.makeMeasureSpec(r13_View.getMeasuredHeight(), 1073741824);
                                            }
                                            if (r6z) {
                                                r0i = r10i - r0_LayoutParams.rightMargin + r0_LayoutParams.leftMargin;
                                                r8i = MeasureSpec.makeMeasureSpec(r0i, 1073741824);
                                                if (r4i == r0i) {
                                                    r13_View.measure(r8i, r3i);
                                                }
                                            } else {
                                                r13_View.measure(MeasureSpec.makeMeasureSpec(((int) ((r0_LayoutParams.weight * ((float) Math.max(0, r5i))) / r2f)) + r4i, 1073741824), r3i);
                                            }
                                        }
                                    } else if (r0_LayoutParams.width >= 0) {
                                        if (r4i > r12i || r0_LayoutParams.weight > 0.0f) {
                                            if (r8i == 0) {
                                                if (r0_LayoutParams.height != -2) {
                                                    r0i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                                } else if (r0_LayoutParams.height != -1) {
                                                }
                                            } else {
                                                r0i = MeasureSpec.makeMeasureSpec(r13_View.getMeasuredHeight(), 1073741824);
                                            }
                                            r13_View.measure(MeasureSpec.makeMeasureSpec(r12i, 1073741824), r0i);
                                        }
                                    }
                                }
                                r9i++;
                            }
                        }
                        setMeasuredDimension(r10i, r7i);
                        this.f = r6z;
                        if (this.p.getViewDragState() == 0 || r6z) {
                        } else {
                            this.p.abort();
                        }
                    } else {
                        setMeasuredDimension(r10i, r7i);
                        this.f = r6z;
                        if (this.p.getViewDragState() == 0 || r6z) {
                        } else {
                            this.p.abort();
                        }
                    }
                }
        }
        r0i = r1i;
        r1i = r0i;
        r6z = false;
        r5i = r10i - getPaddingLeft() - getPaddingRight();
        r11i = getChildCount();
        if (r11i > XListViewHeader.STATE_REFRESHING) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.g = null;
        r8i = 0;
        r7i = r0i;
        r2f = 0.0f;
        while (r8i < r11i) {
            r12_View = getChildAt(r8i);
            r0_LayoutParams = (LayoutParams) r12_View.getLayoutParams();
            if (r12_View.getVisibility() != 8) {
                if (r0_LayoutParams.weight <= 0.0f) {
                    r3i = r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin;
                    if (r0_LayoutParams.width != -2) {
                        r3i = MeasureSpec.makeMeasureSpec(r10i - r3i, ExploreByTouchHelper.INVALID_ID);
                    } else if (r0_LayoutParams.width != -1) {
                    }
                    if (r0_LayoutParams.height != -2) {
                        r4i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                    } else if (r0_LayoutParams.height != -1) {
                    }
                    r12_View.measure(r3i, r4i);
                    r3i = r12_View.getMeasuredWidth();
                    r4i = r12_View.getMeasuredHeight();
                    if (r9i != -2147483648 || r4i <= r7i) {
                        r4i = r5i - r3i;
                        if (r4i >= 0) {
                        }
                        r0_LayoutParams.a = r3z;
                        r3z |= r6z;
                        if (r0_LayoutParams.a) {
                            this.g = r12_View;
                        }
                        r0i = r4i;
                        r4i = r7i;
                        r2z = r3z;
                        r3f = r2f;
                    } else {
                        r7i = Math.min(r4i, r1i);
                        r4i = r5i - r3i;
                        if (r4i >= 0) {
                        }
                        r0_LayoutParams.a = r3z;
                        r3z |= r6z;
                        if (r0_LayoutParams.a) {
                            r0i = r4i;
                            r4i = r7i;
                            r2z = r3z;
                            r3f = r2f;
                        } else {
                            this.g = r12_View;
                            r0i = r4i;
                            r4i = r7i;
                            r2z = r3z;
                            r3f = r2f;
                        }
                    }
                } else {
                    r2f += r0_LayoutParams.weight;
                    if (r0_LayoutParams.width != 0) {
                        r3i = r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin;
                        if (r0_LayoutParams.width != -2) {
                            if (r0_LayoutParams.width != -1) {
                            }
                        } else {
                            r3i = MeasureSpec.makeMeasureSpec(r10i - r3i, ExploreByTouchHelper.INVALID_ID);
                        }
                        if (r0_LayoutParams.height != -2) {
                            if (r0_LayoutParams.height != -1) {
                            }
                        } else {
                            r4i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                        }
                        r12_View.measure(r3i, r4i);
                        r3i = r12_View.getMeasuredWidth();
                        r4i = r12_View.getMeasuredHeight();
                        if (r9i != -2147483648 || r4i <= r7i) {
                            r4i = r5i - r3i;
                            if (r4i >= 0) {
                            }
                            r0_LayoutParams.a = r3z;
                            r3z |= r6z;
                            if (r0_LayoutParams.a) {
                                this.g = r12_View;
                            }
                            r0i = r4i;
                            r4i = r7i;
                            r2z = r3z;
                            r3f = r2f;
                        } else {
                            r7i = Math.min(r4i, r1i);
                            r4i = r5i - r3i;
                            if (r4i >= 0) {
                            }
                            r0_LayoutParams.a = r3z;
                            r3z |= r6z;
                            if (r0_LayoutParams.a) {
                                r0i = r4i;
                                r4i = r7i;
                                r2z = r3z;
                                r3f = r2f;
                            } else {
                                this.g = r12_View;
                                r0i = r4i;
                                r4i = r7i;
                                r2z = r3z;
                                r3f = r2f;
                            }
                        }
                    } else {
                        r0i = r5i;
                        r3f = r2f;
                        r4i = r7i;
                        r2z = r6z;
                    }
                }
            } else {
                r0_LayoutParams.b = false;
                r0i = r5i;
                r3f = r2f;
                r4i = r7i;
                r2z = r6z;
            }
            r8i++;
            r6z = r2z;
            r7i = r4i;
            r2f = r3f;
            r5i = r0i;
        }
        if (r6z || r2f > 0.0f) {
            r12i = r10i - this.e;
            r9i = 0;
            while (r9i < r11i) {
                r13_View = getChildAt(r9i);
                if (r13_View.getVisibility() != 8) {
                    r0_LayoutParams = (LayoutParams) r13_View.getLayoutParams();
                    if (r13_View.getVisibility() == 8) {
                        r9i++;
                    } else {
                        if (r0_LayoutParams.width != 0 || r0_LayoutParams.weight <= 0.0f) {
                        }
                        if (r8i == 0) {
                        }
                        if (r6z || r13_View == this.g) {
                            if (r0_LayoutParams.weight <= 0.0f) {
                                r9i++;
                            } else {
                                if (r0_LayoutParams.width != 0) {
                                    r3i = MeasureSpec.makeMeasureSpec(r13_View.getMeasuredHeight(), 1073741824);
                                } else if (r0_LayoutParams.height != -2) {
                                    if (r0_LayoutParams.height != -1) {
                                    }
                                } else {
                                    r3i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                                }
                                if (r6z) {
                                    r13_View.measure(MeasureSpec.makeMeasureSpec(((int) ((r0_LayoutParams.weight * ((float) Math.max(0, r5i))) / r2f)) + r4i, 1073741824), r3i);
                                } else {
                                    r0i = r10i - r0_LayoutParams.rightMargin + r0_LayoutParams.leftMargin;
                                    r8i = MeasureSpec.makeMeasureSpec(r0i, 1073741824);
                                    if (r4i == r0i) {
                                        r9i++;
                                    } else {
                                        r13_View.measure(r8i, r3i);
                                    }
                                }
                            }
                        } else if (r0_LayoutParams.width >= 0) {
                            r9i++;
                        } else if (r4i > r12i || r0_LayoutParams.weight > 0.0f) {
                            if (r8i == 0) {
                                r0i = MeasureSpec.makeMeasureSpec(r13_View.getMeasuredHeight(), 1073741824);
                            } else if (r0_LayoutParams.height != -2) {
                                if (r0_LayoutParams.height != -1) {
                                }
                            } else {
                                r0i = MeasureSpec.makeMeasureSpec(r1i, ExploreByTouchHelper.INVALID_ID);
                            }
                            r13_View.measure(MeasureSpec.makeMeasureSpec(r12i, 1073741824), r0i);
                        }
                    }
                }
                r9i++;
            }
            setMeasuredDimension(r10i, r7i);
            this.f = r6z;
            if (this.p.getViewDragState() == 0 || r6z) {
            } else {
                this.p.abort();
            }
        } else {
            setMeasuredDimension(r10i, r7i);
            this.f = r6z;
            if (this.p.getViewDragState() == 0 || r6z) {
            } else {
                this.p.abort();
            }
        }
    }

    protected void onRestoreInstanceState(Parcelable r2_Parcelable) {
        SavedState r2_SavedState = (SavedState) r2_Parcelable;
        super.onRestoreInstanceState(r2_SavedState.getSuperState());
        if (r2_SavedState.a) {
            openPane();
        } else {
            closePane();
        }
        this.q = r2_SavedState.a;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable r1_Parcelable = new SavedState(super.onSaveInstanceState());
        r1_Parcelable.a = isSlideable() ? isOpen() : this.q;
        return r1_Parcelable;
    }

    protected void onSizeChanged(int r2i, int r3i, int r4i, int r5i) {
        super.onSizeChanged(r2i, r3i, r4i, r5i);
        if (r2i != r4i) {
            this.r = true;
        }
    }

    public boolean onTouchEvent(MotionEvent r7_MotionEvent) {
        if (!(this.f)) {
            return super.onTouchEvent(r7_MotionEvent);
        }
        this.p.processTouchEvent(r7_MotionEvent);
        boolean r0z = true;
        float r1f;
        float r2f;
        switch ((r7_MotionEvent.getAction() & 255)) {
            case XListViewHeader.STATE_NORMAL:
                r1f = r7_MotionEvent.getX();
                r2f = r7_MotionEvent.getY();
                this.m = r1f;
                this.n = r2f;
                return r0z;
            case XListViewHeader.STATE_READY:
                if (!e(this.g)) {
                    return r0z;
                }
                r1f = r7_MotionEvent.getX();
                r2f = r7_MotionEvent.getY();
                float r3f = r1f - this.m;
                float r4f = r2f - this.n;
                int r5i = this.p.getTouchSlop();
                if (r3f * r3f + r4f * r4f >= ((float) (r5i * r5i)) || (!this.p.isViewUnder(this.g, (int) r1f, (int) r2f))) {
                    return r0z;
                }
                a(this.g, 0);
                return r0z;
        }
        return r0z;
    }

    public boolean openPane() {
        return b(this.g, 0);
    }

    public void requestChildFocus(View r2_View, View r3_View) {
        super.requestChildFocus(r2_View, r3_View);
        if (isInTouchMode() || this.f) {
        } else {
            this.q = r2_View == this.g;
        }
    }

    public void setCoveredFadeColor(int r1i) {
        this.c = r1i;
    }

    public void setPanelSlideListener(PanelSlideListener r1_PanelSlideListener) {
        this.o = r1_PanelSlideListener;
    }

    public void setParallaxDistance(int r1i) {
        this.l = r1i;
        requestLayout();
    }

    public void setShadowDrawable(Drawable r1_Drawable) {
        this.d = r1_Drawable;
    }

    public void setShadowResource(int r2i) {
        setShadowDrawable(getResources().getDrawable(r2i));
    }

    public void setSliderFadeColor(int r1i) {
        this.b = r1i;
    }

    public void smoothSlideClosed() {
        closePane();
    }

    public void smoothSlideOpen() {
        openPane();
    }
}