package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.androidquery.util.Constants;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import com.tencent.mm.sdk.platformtools.Util;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import qsbk.app.R;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.image.Utils;
import qsbk.app.widget.listview.XListViewFooter;

public class ViewPager extends ViewGroup {
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int[] a;
    private static final f ag;
    private static final Comparator<b> c;
    private static final Interpolator d;
    private boolean A;
    private boolean B;
    private int C;
    private int D;
    private int E;
    private float F;
    private float G;
    private float H;
    private float I;
    private int J;
    private VelocityTracker K;
    private int L;
    private int M;
    private int N;
    private int O;
    private boolean P;
    private long Q;
    private EdgeEffectCompat R;
    private EdgeEffectCompat S;
    private boolean T;
    private boolean U;
    private boolean V;
    private int W;
    private OnPageChangeListener Z;
    private OnPageChangeListener aa;
    private d ab;
    private PageTransformer ac;
    private Method ad;
    private int ae;
    private ArrayList<View> af;
    private final Runnable ah;
    private int ai;
    private int b;
    private final ArrayList<b> e;
    private final b f;
    private final Rect g;
    private PagerAdapter h;
    private int i;
    private int j;
    private Parcelable k;
    private ClassLoader l;
    private Scroller m;
    private e n;
    private int o;
    private Drawable p;
    private int q;
    private int r;
    private float s;
    private float t;
    private int u;
    private int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private int z;

    static interface a {
    }

    public static interface OnPageChangeListener {
        public void onPageScrollStateChanged(int r1i);

        public void onPageScrolled(int r1i, float r2f, int r3i);

        public void onPageSelected(int r1i);
    }

    static interface d {
        public void onAdapterChanged(PagerAdapter r1_PagerAdapter, PagerAdapter r2_PagerAdapter);
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams {
        float a;
        boolean b;
        int c;
        int d;
        public int gravity;
        public boolean isDecor;

        public LayoutParams() {
            super(-1, -1);
            this.a = 0.0f;
        }

        public LayoutParams(Context r4_Context, AttributeSet r5_AttributeSet) {
            super(r4_Context, r5_AttributeSet);
            this.a = 0.0f;
            TypedArray r0_TypedArray = r4_Context.obtainStyledAttributes(r5_AttributeSet, a);
            this.gravity = r0_TypedArray.getInteger(SCROLL_STATE_IDLE, AdViewUtil.NETWORK_TYPE_ADUU);
            r0_TypedArray.recycle();
        }
    }

    public static interface PageTransformer {
        public void transformPage(View r1_View, float r2f);
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<android.support.v4.view.ViewPager.SavedState> CREATOR;
        int a;
        Parcelable b;
        ClassLoader c;

        static {
            CREATOR = ParcelableCompat.newCreator(new ae());
        }

        SavedState(Parcel r2_Parcel, ClassLoader r3_ClassLoader) {
            super(r2_Parcel);
            if (r3_ClassLoader == null) {
                r3_ClassLoader = getClass().getClassLoader();
            }
            this.a = r2_Parcel.readInt();
            this.b = r2_Parcel.readParcelable(r3_ClassLoader);
            this.c = r3_ClassLoader;
        }

        public SavedState(Parcelable r1_Parcelable) {
            super(r1_Parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        public void writeToParcel(Parcel r2_Parcel, int r3i) {
            super.writeToParcel(r2_Parcel, r3i);
            r2_Parcel.writeInt(this.a);
            r2_Parcel.writeParcelable(this.b, r3i);
        }
    }

    public static class SimpleOnPageChangeListener implements android.support.v4.view.ViewPager.OnPageChangeListener {
        public void onPageScrollStateChanged(int r1i) {
        }

        public void onPageScrolled(int r1i, float r2f, int r3i) {
        }

        public void onPageSelected(int r1i) {
        }
    }

    static class b {
        Object a;
        int b;
        boolean c;
        float d;
        float e;

        b() {
        }
    }

    class c extends AccessibilityDelegateCompat {
        c() {
        }

        private boolean b() {
            return ViewPager.this.h != null && ViewPager.this.h.getCount() > 1;
        }

        public void onInitializeAccessibilityEvent(View r4_View, AccessibilityEvent r5_AccessibilityEvent) {
            super.onInitializeAccessibilityEvent(r4_View, r5_AccessibilityEvent);
            r5_AccessibilityEvent.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat r0_AccessibilityRecordCompat = AccessibilityRecordCompat.obtain();
            r0_AccessibilityRecordCompat.setScrollable(b());
            if (r5_AccessibilityEvent.getEventType() != 4096 || ViewPager.this.h == null) {
            } else {
                r0_AccessibilityRecordCompat.setItemCount(ViewPager.this.h.getCount());
                r0_AccessibilityRecordCompat.setFromIndex(ViewPager.this.i);
                r0_AccessibilityRecordCompat.setToIndex(ViewPager.this.i);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View r3_View, AccessibilityNodeInfoCompat r4_AccessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(r3_View, r4_AccessibilityNodeInfoCompat);
            r4_AccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
            r4_AccessibilityNodeInfoCompat.setScrollable(b());
            if (ViewPager.this.canScrollHorizontally(SCROLL_STATE_DRAGGING)) {
                r4_AccessibilityNodeInfoCompat.addAction(LVBuffer.LENGTH_ALLOC_PER_NEW);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                r4_AccessibilityNodeInfoCompat.addAction(Utils.IO_BUFFER_SIZE);
            }
        }

        public boolean performAccessibilityAction(View r5_View, int r6i, Bundle r7_Bundle) {
            if (super.performAccessibilityAction(r5_View, r6i, r7_Bundle)) {
                return true;
            }
            switch (r6i) {
                case LVBuffer.LENGTH_ALLOC_PER_NEW:
                    if (!ViewPager.this.canScrollHorizontally(SCROLL_STATE_DRAGGING)) {
                        return false;
                    }
                    ViewPager.this.setCurrentItem(ViewPager.this.i + 1);
                    return true;
                case Utils.IO_BUFFER_SIZE:
                    if (!ViewPager.this.canScrollHorizontally(-1)) {
                        return false;
                    }
                    ViewPager.this.setCurrentItem(ViewPager.this.i - 1);
                    return true;
            }
            return false;
        }
    }

    private class e extends DataSetObserver {
        private e() {
        }

        public void onChanged() {
            ViewPager.this.b();
        }

        public void onInvalidated() {
            ViewPager.this.b();
        }
    }

    static class f implements Comparator<View> {
        f() {
        }

        public int compare(View r5_View, View r6_View) {
            android.support.v4.view.ViewPager.LayoutParams r0_android_support_v4_view_ViewPager_LayoutParams = (android.support.v4.view.ViewPager.LayoutParams) r5_View.getLayoutParams();
            android.support.v4.view.ViewPager.LayoutParams r1_android_support_v4_view_ViewPager_LayoutParams = (android.support.v4.view.ViewPager.LayoutParams) r6_View.getLayoutParams();
            if (r0_android_support_v4_view_ViewPager_LayoutParams.isDecor == r1_android_support_v4_view_ViewPager_LayoutParams.isDecor) {
                return r0_android_support_v4_view_ViewPager_LayoutParams.c - r1_android_support_v4_view_ViewPager_LayoutParams.c;
            }
            if (r0_android_support_v4_view_ViewPager_LayoutParams.isDecor) {
                return SCROLL_STATE_DRAGGING;
            }
            return -1;
        }
    }

    static {
        int[] r0_intA = new int[1];
        r0_intA[0] = 16842931;
        a = r0_intA;
        c = new ab();
        d = new ac();
        ag = new f();
    }

    public ViewPager(Context r6_Context) {
        super(r6_Context);
        this.e = new ArrayList();
        this.f = new b();
        this.g = new Rect();
        this.j = -1;
        this.k = null;
        this.l = null;
        this.s = -3.4028235E38f;
        this.t = 3.4028235E38f;
        this.z = 1;
        this.J = -1;
        this.T = true;
        this.U = false;
        this.ah = new ad(this);
        this.ai = 0;
        a();
    }

    public ViewPager(Context r6_Context, AttributeSet r7_AttributeSet) {
        super(r6_Context, r7_AttributeSet);
        this.e = new ArrayList();
        this.f = new b();
        this.g = new Rect();
        this.j = -1;
        this.k = null;
        this.l = null;
        this.s = -3.4028235E38f;
        this.t = 3.4028235E38f;
        this.z = 1;
        this.J = -1;
        this.T = true;
        this.U = false;
        this.ah = new ad(this);
        this.ai = 0;
        a();
    }

    private int a(int r4i, float r5f, int r6i, int r7i) {
        if (Math.abs(r7i) <= this.N || Math.abs(r6i) <= this.L) {
            r4i = (int) ((r4i >= this.i ? 0.4f : 0.6f) + ((float) r4i) + r5f);
        } else if (r6i > 0) {
        } else {
            r4i++;
        }
        return this.e.size() > 0 ? Math.max(((b) this.e.get(SCROLL_STATE_IDLE)).b, Math.min(r4i, ((b) this.e.get(this.e.size() - 1)).b)) : r4i;
    }

    private Rect a(Rect r5_Rect, View r6_View) {
        Rect r1_Rect;
        int r0i = SCROLL_STATE_IDLE;
        r1_Rect = r5_Rect == null ? new Rect() : r5_Rect;
        if (r6_View == null) {
            r1_Rect.set(r0i, r0i, r0i, r0i);
            return r1_Rect;
        } else {
            r1_Rect.left = r6_View.getLeft();
            r1_Rect.right = r6_View.getRight();
            r1_Rect.top = r6_View.getTop();
            r1_Rect.bottom = r6_View.getBottom();
            ViewPager r0_ViewPager = r6_View.getParent();
            while (r0_ViewPager instanceof ViewGroup && r0_ViewPager != this) {
                ViewGroup r0_ViewGroup = r0_ViewPager;
                r1_Rect.left += r0_ViewGroup.getLeft();
                r1_Rect.right += r0_ViewGroup.getRight();
                r1_Rect.top += r0_ViewGroup.getTop();
                r1_Rect.bottom += r0_ViewGroup.getBottom();
                r0_ViewPager = r0_ViewGroup.getParent();
            }
            return r1_Rect;
        }
    }

    private void a(int r7i, int r8i, int r9i, int r10i) {
        if (r8i <= 0 || this.e.isEmpty()) {
            b r0_b = b(this.i);
            int r0i = (int) ((r0_b != null ? Math.min(r0_b.e, this.t) : 0.0f) * ((float) ((r7i - getPaddingLeft()) - getPaddingRight())));
            if (r0i != getScrollX()) {
                b(false);
                scrollTo(r0i, getScrollY());
            }
        } else {
            int r1i = (int) (((float) (((r7i - getPaddingLeft()) - getPaddingRight()) + r9i)) * (((float) getScrollX()) / ((float) (((r8i - getPaddingLeft()) - getPaddingRight()) + r10i))));
            scrollTo(r1i, getScrollY());
            if (!this.m.isFinished()) {
                this.m.startScroll(r1i, SCROLL_STATE_IDLE, (int) (b(this.i).e * ((float) r7i)), 0, this.m.getDuration() - this.m.timePassed());
            }
        }
    }

    private void a(int r6i, boolean r7z, int r8i, boolean r9z) {
        int r0i;
        b r0_b = b(r6i);
        r0i = r0_b != null ? (int) (Math.max(this.s, Math.min(r0_b.e, this.t)) * ((float) h())) : 0;
        if (r7z) {
            a(r0i, (int)SCROLL_STATE_IDLE, r8i);
            if ((!r9z) || this.Z == null) {
                if ((!r9z) || this.aa == null) {
                } else {
                    this.aa.onPageSelected(r6i);
                }
            } else {
                this.Z.onPageSelected(r6i);
                if (r9z || this.aa == null) {
                } else {
                    this.aa.onPageSelected(r6i);
                }
            }
        } else if ((!r9z) || this.Z == null) {
            if ((!r9z) || this.aa == null) {
                b(false);
                scrollTo(r0i, SCROLL_STATE_IDLE);
                d(r0i);
            } else {
                this.aa.onPageSelected(r6i);
                b(false);
                scrollTo(r0i, SCROLL_STATE_IDLE);
                d(r0i);
            }
        } else {
            this.Z.onPageSelected(r6i);
            if (r9z || this.aa == null) {
                b(false);
                scrollTo(r0i, SCROLL_STATE_IDLE);
                d(r0i);
            } else {
                this.aa.onPageSelected(r6i);
                b(false);
                scrollTo(r0i, SCROLL_STATE_IDLE);
                d(r0i);
            }
        }
    }

    private void a(b r12_b, int r13i, b r14_b) {
        float r6f;
        float r3f;
        int r1i;
        b r0_b;
        int r7i = this.h.getCount();
        int r0i = h();
        r6f = r0i > 0 ? ((float) this.o) / ((float) r0i) : 0.0f;
        if (r14_b != null) {
            r0i = r14_b.b;
            int r2i;
            if (r0i < r12_b.b) {
                r3f = r14_b.e + r14_b.d + r6f;
                r2i = r0i + 1;
                r1i = 0;
                while (r2i <= r12_b.b && r1i < this.e.size()) {
                    r0_b = (b) this.e.get(r1i);
                    while (r2i > r0_b.b && r1i < this.e.size() - 1) {
                        r1i++;
                        r0_b = (b) this.e.get(r1i);
                    }
                    while (r2i < r0_b.b) {
                        r3f += this.h.getPageWidth(r2i) + r6f;
                        r2i++;
                    }
                    r0_b.e = r3f;
                    r3f += r0_b.d + r6f;
                    r2i++;
                }
            } else if (r0i > r12_b.b) {
                r1i = this.e.size() - 1;
                r3f = r14_b.e;
                r2i = r0i - 1;
                while (r2i >= r12_b.b && r1i >= 0) {
                    r0_b = (b) this.e.get(r1i);
                    while (r2i < r0_b.b && r1i > 0) {
                        r1i--;
                        r0_b = (b) this.e.get(r1i);
                    }
                    while (r2i > r0_b.b) {
                        r3f -= this.h.getPageWidth(r2i) + r6f;
                        r2i--;
                    }
                    r3f -= r0_b.d + r6f;
                    r0_b.e = r3f;
                    r2i--;
                }
            }
        }
        int r8i = this.e.size();
        float r2f = r12_b.e;
        r1i = r12_b.b - 1;
        this.s = r12_b.b == 0 ? r12_b.e : -3.4028235E38f;
        this.t = r12_b.b == r7i + -1 ? r12_b.e + r12_b.d - 1.0f : Constants.RATIO_PRESERVE;
        int r5i = r13i - 1;
        while (r5i >= 0) {
            r0_b = (b) this.e.get(r5i);
            r3f = r2f;
            while (r1i > r0_b.b) {
                r3f -= this.h.getPageWidth(r1i) + r6f;
                r1i--;
            }
            r2f = r3f - r0_b.d + r6f;
            r0_b.e = r2f;
            if (r0_b.b == 0) {
                this.s = r2f;
            }
            r1i--;
            r5i--;
        }
        r2f = r12_b.e + r12_b.d + r6f;
        r1i = r12_b.b + 1;
        r5i = r13i + 1;
        while (r5i < r8i) {
            r0_b = (b) this.e.get(r5i);
            r3f = r2f;
            while (r1i < r0_b.b) {
                r3f = this.h.getPageWidth(r1i) + r6f + r3f;
                r1i++;
            }
            if (r0_b.b == r7i - 1) {
                this.t = r0_b.d + r3f - 1.0f;
            }
            r0_b.e = r3f;
            r2f = r3f + r0_b.d + r6f;
            r1i++;
            r5i++;
        }
        this.U = false;
    }

    private void a(MotionEvent r4_MotionEvent) {
        int r0i = MotionEventCompat.getActionIndex(r4_MotionEvent);
        if (MotionEventCompat.getPointerId(r4_MotionEvent, r0i) == this.J) {
            r0i = r0i == 0 ? SCROLL_STATE_DRAGGING : SCROLL_STATE_IDLE;
            this.F = MotionEventCompat.getX(r4_MotionEvent, r0i);
            this.J = MotionEventCompat.getPointerId(r4_MotionEvent, r0i);
            if (this.K != null) {
                this.K.clear();
            }
        }
    }

    private boolean a(float r4f, float r5f) {
        if (r4f < ((float) this.D) && r5f > 0.0f) {
            return true;
        }
        if (r4f <= ((float) (getWidth() - this.D)) || r5f >= 0.0f) {
            return false;
        }
        return true;
    }

    private void b(boolean r8z) {
        int r0i;
        int r1i;
        int r3i;
        r0i = this.ai == 2 ? 1 : 0;
        if (r0i != 0) {
            e(false);
            this.m.abortAnimation();
            r1i = getScrollX();
            r3i = getScrollY();
            int r5i = this.m.getCurrX();
            int r6i = this.m.getCurrY();
            if (!(r1i == r5i && r3i == r6i)) {
                scrollTo(r5i, r6i);
            }
        }
        this.y = false;
        r1i = 0;
        r3i = r0i;
        while (r1i < this.e.size()) {
            b r0_b = (b) this.e.get(r1i);
            if (r0_b.c) {
                r0_b.c = false;
                r3i = 1;
            }
            r1i++;
        }
        if (r3i != 0) {
            if (r8z) {
                ViewCompat.postOnAnimation(this, this.ah);
            } else {
                this.ah.run();
            }
        }
    }

    private boolean b(float r11f) {
        int r0i;
        float r1f;
        int r3i = SCROLL_STATE_DRAGGING;
        boolean r2z = false;
        this.F = r11f;
        float r5f = ((float) getScrollX()) + this.F - r11f;
        int r7i = h();
        float r4f = ((float) r7i) * this.s;
        float r6f = ((float) r7i) * this.t;
        b r0_b = (b) this.e.get(SCROLL_STATE_IDLE);
        b r1_b = (b) this.e.get(this.e.size() - 1);
        if (r0_b.b != 0) {
            r4f = r0_b.e * ((float) r7i);
            r0i = 0;
        } else {
            r0i = 1;
        }
        if (r1_b.b != this.h.getCount() - 1) {
            r1f = r1_b.e * ((float) r7i);
            r3i = 0;
        } else {
            r1f = r6f;
        }
        if (r5f < r4f) {
            if (r0i != 0) {
                r2z = this.R.onPull(Math.abs(r4f - r5f) / ((float) r7i));
            }
        } else if (r5f > r1f) {
            if (r3i != 0) {
                r2z = this.S.onPull(Math.abs(r5f - r1f) / ((float) r7i));
            }
            r4f = r1f;
        } else {
            r4f = r5f;
        }
        this.F += r4f - ((float) ((int) r4f));
        scrollTo((int) r4f, getScrollY());
        d((int) r4f);
        return r2z;
    }

    private void c(int r2i) {
        if (this.ai == r2i) {
        } else {
            this.ai = r2i;
            if (this.ac != null) {
                c(r2i != 0);
            }
            if (this.Z != null) {
                this.Z.onPageScrollStateChanged(r2i);
            }
        }
    }

    private void c(boolean r7z) {
        int r3i = getChildCount();
        int r2i = 0;
        while (r2i < r3i) {
            ViewCompat.setLayerType(getChildAt(r2i), r7z ? SCROLL_STATE_SETTLING : 0, null);
            r2i++;
        }
    }

    private void d(boolean r2z) {
        ViewParent r0_ViewParent = getParent();
        if (r0_ViewParent != null) {
            r0_ViewParent.requestDisallowInterceptTouchEvent(r2z);
        }
    }

    private boolean d(int r8i) {
        boolean r0z = false;
        if (this.e.size() == 0) {
            this.V = false;
            a((int)SCROLL_STATE_IDLE, 0.0f, (int)SCROLL_STATE_IDLE);
            if (this.V) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else {
            b r1_b = j();
            int r2i = h();
            int r5i = r1_b.b;
            float r1f = ((((float) r8i) / ((float) r2i)) - r1_b.e) / (r1_b.d + (((float) this.o) / ((float) r2i)));
            this.V = r0z;
            a(r5i, r1f, (int) (((float) (this.o + r2i)) * r1f));
            if (this.V) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    private void e(boolean r2z) {
        if (this.x != r2z) {
            this.x = r2z;
        }
    }

    private void g() {
        int r1i = 0;
        while (r1i < getChildCount()) {
            if (!((LayoutParams) getChildAt(r1i).getLayoutParams()).isDecor) {
                removeViewAt(r1i);
                r1i--;
            }
            r1i++;
        }
    }

    private int h() {
        return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    private void i() {
        if (this.ae != 0) {
            if (this.af == null) {
                this.af = new ArrayList();
            } else {
                this.af.clear();
            }
            int r1i = getChildCount();
            int r0i = SCROLL_STATE_IDLE;
            while (r0i < r1i) {
                this.af.add(getChildAt(r0i));
                r0i++;
            }
            Collections.sort(this.af, ag);
        }
    }

    private b j() {
        float r9f;
        float r1f;
        float r2f = 0.0f;
        int r1i = h();
        r9f = r1i > 0 ? ((float) getScrollX()) / ((float) r1i) : 0.0f;
        r1f = r1i > 0 ? ((float) this.o) / ((float) r1i) : 0.0f;
        float r6f = r2f;
        float r7f = r2f;
        int r8i = -1;
        int r2i = 0;
        int r5i = 1;
        b r4_b = null;
        while (r2i < this.e.size()) {
            int r0i;
            b r2_b;
            b r0_b = (b) this.e.get(r2i);
            if (r5i != 0 || r0_b.b == r8i + 1) {
                r0i = r2i;
                r2_b = r0_b;
            } else {
                r0_b = this.f;
                r0_b.e = r6f + r7f + r1f;
                r0_b.b = r8i + 1;
                r0_b.d = this.h.getPageWidth(r0_b.b);
                r0i = r2i - 1;
                r2_b = r0_b;
            }
            r6f = r2_b.e;
            r7f = r2_b.d + r6f + r1f;
            if (r5i == 0 && r9f < r6f) {
                return r4_b;
            }
            if (r9f < r7f || r0i == this.e.size() - 1) {
                return r2_b;
            }
            r7f = r6f;
            r8i = r2_b.b;
            r5i = 0;
            r6f = r2_b.d;
            r4_b = r2_b;
            r2i = r0i + 1;
        }
        return r4_b;
    }

    private void k() {
        this.A = false;
        this.B = false;
        if (this.K != null) {
            this.K.recycle();
            this.K = null;
        }
    }

    float a(float r5f) {
        return (float) Math.sin((double) ((float) (((double) (r5f - 0.5f)) * 0.4712389167638204d)));
    }

    OnPageChangeListener a(OnPageChangeListener r2_OnPageChangeListener) {
        OnPageChangeListener r0_OnPageChangeListener = this.aa;
        this.aa = r2_OnPageChangeListener;
        return r0_OnPageChangeListener;
    }

    b a(int r3i, int r4i) {
        b r0_b = new b();
        r0_b.b = r3i;
        r0_b.a = this.h.instantiateItem((ViewGroup)this, r3i);
        r0_b.d = this.h.getPageWidth(r3i);
        if (r4i < 0 || r4i >= this.e.size()) {
            this.e.add(r0_b);
        } else {
            this.e.add(r4i, r0_b);
        }
        return r0_b;
    }

    b a(View r5_View) {
        int r1i = 0;
        while (r1i < this.e.size()) {
            b r0_b = (b) this.e.get(r1i);
            if (this.h.isViewFromObject(r5_View, r0_b.a)) {
                return r0_b;
            }
            r1i++;
        }
        return null;
    }

    void a() {
        setWillNotDraw(false);
        setDescendantFocusability(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
        setFocusable(true);
        Context r0_Context = getContext();
        this.m = new Scroller(r0_Context, d);
        ViewConfiguration r1_ViewConfiguration = ViewConfiguration.get(r0_Context);
        float r2f = r0_Context.getResources().getDisplayMetrics().density;
        this.E = ViewConfigurationCompat.getScaledPagingTouchSlop(r1_ViewConfiguration);
        this.L = (int) (400.0f * r2f);
        this.M = r1_ViewConfiguration.getScaledMaximumFlingVelocity();
        this.R = new EdgeEffectCompat(r0_Context);
        this.S = new EdgeEffectCompat(r0_Context);
        this.N = (int) (25.0f * r2f);
        this.O = (int) (2.0f * r2f);
        this.C = (int) (16.0f * r2f);
        ViewCompat.setAccessibilityDelegate(this, new c());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, SCROLL_STATE_DRAGGING);
        }
    }

    void a(int r19i) {
        int r2i;
        b r4_b;
        b r3_b = null;
        if (this.i != r19i) {
            r2i = this.i < r19i ? BDLocation.TypeOffLineLocation : R.styleable.ActionBar_progressBarPadding;
            r3_b = b(this.i);
            this.i = r19i;
            r4_b = r3_b;
            r3i = r2i;
        } else {
            r4_b = r3_b;
            r3i = 2;
        }
        if (this.h == null) {
            i();
        } else if (this.y) {
            i();
        } else {
            if (getWindowToken() != null) {
                this.h.startUpdate((ViewGroup)this);
                r2i = this.z;
                int r11i = Math.max(SCROLL_STATE_IDLE, this.i - r2i);
                int r12i = this.h.getCount();
                int r13i = Math.min(r12i - 1, r2i + this.i);
                if (r12i != this.b) {
                    String r2_String;
                    try {
                        r2_String = getResources().getResourceName(getId());
                    } catch (NotFoundException e) {
                        r2_String = Integer.toHexString(getId());
                    }
                    throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.b + ", found: " + r12i + " Pager id: " + r2_String + " Pager class: " + getClass() + " Problematic adapter: " + this.h.getClass());
                } else {
                    b r2_b;
                    b r10_b;
                    float r9f;
                    int r8i;
                    int r4i;
                    View r6_View;
                    LayoutParams r2_LayoutParams;
                    b r6_b;
                    View r2_View;
                    View r4_View;
                    b r5_b;
                    float r6f;
                    int r5i = 0;
                    while (r5i < this.e.size()) {
                        r2_b = (b) this.e.get(r5i);
                        if (r2_b.b >= this.i) {
                            if (r2_b.b != this.i) goto L_0x033c;
                            break;
                        } else {
                            r5i++;
                        }
                    }
                    r2_b = null;
                    if (r2_b != null || r12i <= 0) {
                        r10_b = r2_b;
                        if (r10_b == null) {
                            r9f = 0.0f;
                            r8i = r5i - 1;
                        }
                        this.h.setPrimaryItem((ViewGroup)this, this.i, r10_b != null ? r10_b.a : null);
                        this.h.finishUpdate((ViewGroup)this);
                        r5i = getChildCount();
                        r4i = 0;
                        while (r4i < r5i) {
                            r6_View = getChildAt(r4i);
                            r2_LayoutParams = (LayoutParams) r6_View.getLayoutParams();
                            r2_LayoutParams.d = r4i;
                            if (r2_LayoutParams.isDecor || r2_LayoutParams.a != 0.0f) {
                                r4i++;
                            } else {
                                r6_b = a(r6_View);
                                if (r6_b != null) {
                                    r2_LayoutParams.a = r6_b.d;
                                    r2_LayoutParams.c = r6_b.b;
                                }
                                r4i++;
                            }
                        }
                        i();
                        if (hasFocus()) {
                            r2_View = findFocus();
                            r2_b = r2_View != null ? b(r2_View) : null;
                            if (r2_b == null || r2_b.b != this.i) {
                                r2i = SCROLL_STATE_IDLE;
                                while (r2i < getChildCount()) {
                                    r4_View = getChildAt(r2i);
                                    r5_b = a(r4_View);
                                    if (!(r5_b != null && r5_b.b == this.i && r4_View.requestFocus(r3i))) {
                                        r2i++;
                                    }
                                }
                            }
                        }
                    } else {
                        r10_b = a(this.i, r5i);
                        if (r10_b == null) {
                            if (r10_b != null) {
                            }
                            this.h.setPrimaryItem((ViewGroup)this, this.i, r10_b != null ? r10_b.a : null);
                            this.h.finishUpdate((ViewGroup)this);
                            r5i = getChildCount();
                            r4i = 0;
                            while (r4i < r5i) {
                                r6_View = getChildAt(r4i);
                                r2_LayoutParams = (LayoutParams) r6_View.getLayoutParams();
                                r2_LayoutParams.d = r4i;
                                if (r2_LayoutParams.isDecor || r2_LayoutParams.a != 0.0f) {
                                    r4i++;
                                } else {
                                    r6_b = a(r6_View);
                                    if (r6_b != null) {
                                        r4i++;
                                    } else {
                                        r2_LayoutParams.a = r6_b.d;
                                        r2_LayoutParams.c = r6_b.b;
                                        r4i++;
                                    }
                                }
                            }
                            i();
                            if (hasFocus()) {
                            } else {
                                r2_View = findFocus();
                                if (r2_View != null) {
                                }
                                if (r2_b == null || r2_b.b != this.i) {
                                    r2i = SCROLL_STATE_IDLE;
                                    while (r2i < getChildCount()) {
                                        r4_View = getChildAt(r2i);
                                        r5_b = a(r4_View);
                                        if (r5_b != null || r5_b.b == this.i || r4_View.requestFocus(r3i)) {
                                            r2i++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    r9f = 0.0f;
                    r8i = r5i - 1;
                    r2_b = r8i >= 0 ? (b) this.e.get(r8i) : null;
                    int r14i = h();
                    r6f = r14i <= 0 ? 0.0f : 2.0f - r10_b.d + ((float) getPaddingLeft()) / ((float) r14i);
                    float r7f = r9f;
                    int r9i = this.i - 1;
                    r8i = r5i;
                    r5i = r8i;
                    while (r9i >= 0) {
                        if (r7f < r6f || r9i >= r11i) {
                            if (r2_b == null || r9i != r2_b.b) {
                                r7f += a(r9i, r5i + 1).d;
                                r8i++;
                                if (r5i >= 0) {
                                    r2_b = (b) this.e.get(r5i);
                                    r9i--;
                                } else {
                                    r2_b = null;
                                    r9i--;
                                }
                            } else {
                                r7f += r2_b.d;
                                r5i--;
                                if (r5i >= 0) {
                                    r2_b = (b) this.e.get(r5i);
                                    r9i--;
                                } else {
                                    r2_b = null;
                                    r9i--;
                                }
                            }
                        } else if (r2_b == null) {
                            break;
                        } else if (r9i != r2_b.b || r2_b.c) {
                            r9i--;
                        } else {
                            this.e.remove(r5i);
                            this.h.destroyItem((ViewGroup)this, r9i, r2_b.a);
                            r5i--;
                            r8i--;
                            if (r5i >= 0) {
                                r2_b = (b) this.e.get(r5i);
                                r9i--;
                            } else {
                                r2_b = null;
                                r9i--;
                            }
                        }
                    }
                    r6f = r10_b.d;
                    r9i = r8i + 1;
                    if (r6f < 2.0f) {
                        b r7_b;
                        float r5f;
                        r7_b = r9i < this.e.size() ? (b) this.e.get(r9i) : null;
                        r5f = r14i <= 0 ? 0.0f : ((float) getPaddingRight()) / ((float) r14i) + 2.0f;
                        r2_b = r7_b;
                        int r7i = r9i;
                        r9i = this.i + 1;
                        while (r9i < r12i) {
                            float r2f;
                            if (r6f < r5f || r9i <= r13i) {
                                if (r2_b == null || r9i != r2_b.b) {
                                    r2_b = a(r9i, r7i);
                                    r7i++;
                                    r6_b = r7i < this.e.size() ? (b) this.e.get(r7i) : null;
                                    r2f = r6f + r2_b.d;
                                    r9i++;
                                    r2_b = r6_b;
                                    r6f = r2f;
                                } else {
                                    r7i++;
                                    r6_b = r7i < this.e.size() ? (b) this.e.get(r7i) : null;
                                    r2f = r6f + r2_b.d;
                                    r9i++;
                                    r2_b = r6_b;
                                    r6f = r2f;
                                }
                            } else if (r2_b == null) {
                                break;
                            } else if (r9i != r2_b.b || r2_b.c) {
                                r6_b = r2_b;
                                r2f = r6f;
                                r9i++;
                                r2_b = r6_b;
                                r6f = r2f;
                            } else {
                                this.e.remove(r7i);
                                this.h.destroyItem((ViewGroup)this, r9i, r2_b.a);
                                r6_b = r7i < this.e.size() ? (b) this.e.get(r7i) : null;
                                r2f = r6f;
                                r9i++;
                                r2_b = r6_b;
                                r6f = r2f;
                            }
                        }
                    }
                    a(r10_b, r8i, r4_b);
                    if (r10_b != null) {
                    }
                    this.h.setPrimaryItem((ViewGroup)this, this.i, r10_b != null ? r10_b.a : null);
                    this.h.finishUpdate((ViewGroup)this);
                    r5i = getChildCount();
                    r4i = 0;
                    while (r4i < r5i) {
                        r6_View = getChildAt(r4i);
                        r2_LayoutParams = (LayoutParams) r6_View.getLayoutParams();
                        r2_LayoutParams.d = r4i;
                        if (r2_LayoutParams.isDecor || r2_LayoutParams.a != 0.0f) {
                            r4i++;
                        } else {
                            r6_b = a(r6_View);
                            if (r6_b != null) {
                                r2_LayoutParams.a = r6_b.d;
                                r2_LayoutParams.c = r6_b.b;
                            }
                            r4i++;
                        }
                    }
                    i();
                    if (hasFocus()) {
                        r2_View = findFocus();
                        if (r2_View != null) {
                        }
                        if (r2_b == null || r2_b.b != this.i) {
                            r2i = SCROLL_STATE_IDLE;
                            while (r2i < getChildCount()) {
                                r4_View = getChildAt(r2i);
                                r5_b = a(r4_View);
                                if (r5_b != null || r5_b.b == this.i || r4_View.requestFocus(r3i)) {
                                    r2i++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected void a(int r12i, float r13f, int r14i) {
        int r1i;
        int r2i;
        int r4i;
        if (this.W > 0) {
            int r5i = getScrollX();
            r1i = getPaddingLeft();
            r2i = getPaddingRight();
            int r6i = getWidth();
            int r7i = getChildCount();
            r4i = 0;
            while (r4i < r7i) {
                View r8_View = getChildAt(r4i);
                LayoutParams r0_LayoutParams = (LayoutParams) r8_View.getLayoutParams();
                if (r0_LayoutParams.isDecor) {
                    int r0i;
                    switch ((r0_LayoutParams.gravity & 7)) {
                        case SCROLL_STATE_DRAGGING:
                            r0i = Math.max((r6i - r8_View.getMeasuredWidth()) / 2, r1i);
                            r2i = r1i;
                            r1i = r2i;
                            r0i = r0i + r5i - r8_View.getLeft();
                            if (r0i == 0) {
                                r8_View.offsetLeftAndRight(r0i);
                            }
                            break;
                        case XListViewFooter.STATE_NOMORE:
                            r1i = r2i;
                            r2i = r8_View.getWidth() + r1i;
                            r0i = r1i;
                            r0i = r0i + r5i - r8_View.getLeft();
                            if (r0i == 0) {
                                r4i++;
                                r1i = r2i;
                                r2i = r1i;
                            } else {
                                r8_View.offsetLeftAndRight(r0i);
                            }
                            break;
                        case ShareUtils.SHARE_SMS:
                            r0i = r6i - r2i - r8_View.getMeasuredWidth();
                            r2i = r1i;
                            r1i = r2i + r8_View.getMeasuredWidth();
                            r0i = r0i + r5i - r8_View.getLeft();
                            if (r0i == 0) {
                                r8_View.offsetLeftAndRight(r0i);
                            }
                            break;
                    }
                    r0i = r1i;
                    r2i = r1i;
                    r1i = r2i;
                    r0i = r0i + r5i - r8_View.getLeft();
                    if (r0i == 0) {
                        r4i++;
                        r1i = r2i;
                        r2i = r1i;
                    } else {
                        r8_View.offsetLeftAndRight(r0i);
                    }
                } else {
                    r2i = r1i;
                    r1i = r2i;
                }
                r4i++;
                r1i = r2i;
                r2i = r1i;
            }
        }
        if (this.Z != null) {
            this.Z.onPageScrolled(r12i, r13f, r14i);
        }
        if (this.aa != null) {
            this.aa.onPageScrolled(r12i, r13f, r14i);
        }
        if (this.ac != null) {
            r2i = getScrollX();
            r4i = getChildCount();
            r1i = 0;
            while (r1i < r4i) {
                View r3_View = getChildAt(r1i);
                if (((LayoutParams) r3_View.getLayoutParams()).isDecor) {
                    r1i++;
                } else {
                    this.ac.transformPage(r3_View, ((float) (r3_View.getLeft() - r2i)) / ((float) h()));
                    r1i++;
                }
            }
        }
        this.V = true;
    }

    void a(int r10i, int r11i, int r12i) {
        if (getChildCount() == 0) {
            e(false);
        } else {
            int r1i = getScrollX();
            int r2i = getScrollY();
            int r3i = r10i - r1i;
            int r4i = r11i - r2i;
            if (r3i == 0 && r4i == 0) {
                b(false);
                c();
                c((int)SCROLL_STATE_IDLE);
            } else {
                e(true);
                c((int)SCROLL_STATE_SETTLING);
                int r0i = h();
                int r5i = r0i / 2;
                float r5f = ((float) r5i) * a(Math.min(1.0f, (((float) Math.abs(r3i)) * 1.0f) / ((float) r0i))) + ((float) r5i);
                int r6i = Math.abs(r12i);
                this.m.startScroll(r1i, r2i, r3i, r4i, Math.min(r6i > 0 ? Math.round(1000.0f * Math.abs(r5f / ((float) r6i))) * 4 : (int) (((((float) Math.abs(r3i)) / ((((float) r0i) * this.h.getPageWidth(this.i)) + ((float) this.o))) + 1.0f) * 100.0f), 600));
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
    }

    void a(int r2i, boolean r3z, boolean r4z) {
        a(r2i, r3z, r4z, (int)SCROLL_STATE_IDLE);
    }

    void a(int r5i, boolean r6z, boolean r7z, int r8i) {
        boolean r1z = false;
        if (this.h == null || this.h.getCount() <= 0) {
            e(false);
        } else if (r7z || this.i != r5i || this.e.size() == 0) {
            if (r5i < 0) {
                r5i = 0;
            } else if (r5i >= this.h.getCount()) {
                r5i = this.h.getCount() - 1;
            }
            int r0i = this.z;
            if (r5i > this.i + r0i || r5i < this.i - r0i) {
                int r2i = 0;
                while (r2i < this.e.size()) {
                    ((b) this.e.get(r2i)).c = true;
                    r2i++;
                }
                if (this.i == r5i) {
                    r1z = true;
                }
                if (this.T) {
                    this.i = r5i;
                    if ((!r1z) || this.Z == null) {
                        if ((!r1z) || this.aa == null) {
                            requestLayout();
                        } else {
                            this.aa.onPageSelected(r5i);
                            requestLayout();
                        }
                    } else {
                        this.Z.onPageSelected(r5i);
                        if (r1z || this.aa == null) {
                            requestLayout();
                        } else {
                            this.aa.onPageSelected(r5i);
                            requestLayout();
                        }
                    }
                } else {
                    a(r5i);
                    a(r5i, r6z, r8i, r1z);
                }
            } else if (this.i == r5i) {
                if (this.T) {
                    a(r5i);
                    a(r5i, r6z, r8i, r1z);
                } else {
                    this.i = r5i;
                    if (r1z || this.Z == null) {
                        if (r1z || this.aa == null) {
                            requestLayout();
                        } else {
                            this.aa.onPageSelected(r5i);
                            requestLayout();
                        }
                    } else {
                        this.Z.onPageSelected(r5i);
                        if (r1z || this.aa == null) {
                            requestLayout();
                        } else {
                            this.aa.onPageSelected(r5i);
                            requestLayout();
                        }
                    }
                }
            } else {
                r1z = true;
                if (this.T) {
                    this.i = r5i;
                    if (r1z || this.Z == null) {
                        if (r1z || this.aa == null) {
                            requestLayout();
                        } else {
                            this.aa.onPageSelected(r5i);
                            requestLayout();
                        }
                    } else {
                        this.Z.onPageSelected(r5i);
                        if (r1z || this.aa == null) {
                            requestLayout();
                        } else {
                            this.aa.onPageSelected(r5i);
                            requestLayout();
                        }
                    }
                } else {
                    a(r5i);
                    a(r5i, r6z, r8i, r1z);
                }
            }
        } else {
            e(false);
        }
    }

    void a(d r1_d) {
        this.ab = r1_d;
    }

    void a(boolean r6z) {
        if (VERSION.SDK_INT >= 7) {
            if (this.ad == null) {
                try {
                    Class[] r2_ClassA = new Class[1];
                    r2_ClassA[0] = Boolean.TYPE;
                    this.ad = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", r2_ClassA);
                } catch (NoSuchMethodException e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                Method r0_Method = this.ad;
                Object[] r1_ObjectA = new Object[1];
                r1_ObjectA[0] = Boolean.valueOf(r6z);
                r0_Method.invoke(this, r1_ObjectA);
            } catch (Exception e_2) {
                Log.e("ViewPager", "Error changing children drawing order", e_2);
            }
        }
    }

    protected boolean a(View r11_View, boolean r12z, int r13i, int r14i, int r15i) {
        if (r11_View instanceof ViewGroup) {
            ViewGroup r6_ViewGroup = (ViewGroup) r11_View;
            int r8i = r11_View.getScrollX();
            int r9i = r11_View.getScrollY();
            int r7i = r6_ViewGroup.getChildCount() - 1;
            while (r7i >= 0) {
                View r1_View = r6_ViewGroup.getChildAt(r7i);
                if (r14i + r8i >= r1_View.getLeft() && r14i + r8i < r1_View.getRight() && r15i + r9i >= r1_View.getTop() && r15i + r9i < r1_View.getBottom() && a(r1_View, true, r13i, r14i + r8i - r1_View.getLeft(), r15i + r9i - r1_View.getTop())) {
                    return true;
                }
                r7i--;
            }
        }
        return r12z && ViewCompat.canScrollHorizontally(r11_View, -r13i);
    }

    public void addFocusables(ArrayList<View> r7_ArrayList_View, int r8i, int r9i) {
        int r1i = r7_ArrayList_View.size();
        int r2i = getDescendantFocusability();
        if (r2i != 393216) {
            int r0i = SCROLL_STATE_IDLE;
            while (r0i < getChildCount()) {
                View r3_View = getChildAt(r0i);
                if (r3_View.getVisibility() == 0) {
                    b r4_b = a(r3_View);
                    if (r4_b == null || r4_b.b != this.i) {
                        r0i++;
                    } else {
                        r3_View.addFocusables(r7_ArrayList_View, r8i, r9i);
                    }
                }
                r0i++;
            }
        }
        if ((r2i != 262144 || r1i == r7_ArrayList_View.size()) && isFocusable()) {
            if (!(((r9i & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) || r7_ArrayList_View == null)) {
                r7_ArrayList_View.add(this);
            }
        }
    }

    public void addTouchables(ArrayList<View> r5_ArrayList_View) {
        int r0i = SCROLL_STATE_IDLE;
        while (r0i < getChildCount()) {
            View r1_View = getChildAt(r0i);
            if (r1_View.getVisibility() == 0) {
                b r2_b = a(r1_View);
                if (r2_b == null || r2_b.b != this.i) {
                    r0i++;
                } else {
                    r1_View.addTouchables(r5_ArrayList_View);
                }
            }
            r0i++;
        }
    }

    public void addView(View r5_View, int r6i, android.view.ViewGroup.LayoutParams r7_android_view_ViewGroup_LayoutParams) {
        android.view.ViewGroup.LayoutParams r1_android_view_ViewGroup_LayoutParams;
        r1_android_view_ViewGroup_LayoutParams = checkLayoutParams(r7_android_view_ViewGroup_LayoutParams) ? r7_android_view_ViewGroup_LayoutParams : generateLayoutParams(r7_android_view_ViewGroup_LayoutParams);
        LayoutParams r0_LayoutParams = (LayoutParams) r1_android_view_ViewGroup_LayoutParams;
        r0_LayoutParams.isDecor |= r5_View instanceof a;
        if (this.w) {
            if (r0_LayoutParams == null || (!r0_LayoutParams.isDecor)) {
                r0_LayoutParams.b = true;
                addViewInLayout(r5_View, r6i, r1_android_view_ViewGroup_LayoutParams);
            } else {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
        } else {
            super.addView(r5_View, r6i, r1_android_view_ViewGroup_LayoutParams);
        }
    }

    public boolean arrowScroll(int r10i) {
        View r0_View;
        boolean r0z;
        View r2_View = findFocus();
        if (r2_View == this) {
            r0_View = null;
        } else {
            if (r2_View != null) {
                int r0i;
                ViewPager r0_ViewPager = r2_View.getParent();
                while (r0_ViewPager instanceof ViewGroup) {
                    if (r0_ViewPager == this) {
                        r0i = 1;
                        break;
                    } else {
                        r0_ViewPager = r0_ViewPager.getParent();
                    }
                }
                r0i = 0;
                if (r0i == 0) {
                    StringBuilder r5_StringBuilder = new StringBuilder();
                    r5_StringBuilder.append(r2_View.getClass().getSimpleName());
                    ViewParent r0_ViewParent = r2_View.getParent();
                    while (r0_ViewParent instanceof ViewGroup) {
                        r5_StringBuilder.append(" => ").append(r0_ViewParent.getClass().getSimpleName());
                        r0_ViewParent = r0_ViewParent.getParent();
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + r5_StringBuilder.toString());
                    r0_View = null;
                }
            }
            r0_View = r2_View;
        }
        View r1_View = FocusFinder.getInstance().findNextFocus(this, r0_View, r10i);
        if (r1_View == null || r1_View == r0_View) {
            if (r10i == 17 || r10i == 1) {
                r0z = d();
            } else if (r10i == 66 || r10i == 2) {
                r0z = e();
            } else {
                r0z = false;
            }
        } else if (r10i == 17) {
            r2i = a(this.g, r1_View).left;
            r3i = a(this.g, r0_View).left;
            r0z = (r0_View == null || r2i < r3i) ? r1_View.requestFocus() : d();
        } else {
            if (r10i == 66) {
                r2i = a(this.g, r1_View).left;
                r3i = a(this.g, r0_View).left;
                r0z = (r0_View == null || r2i > r3i) ? r1_View.requestFocus() : e();
            }
            r0z = false;
        }
        if (r0z) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(r10i));
        }
        return r0z;
    }

    b b(int r4i) {
        int r1i = 0;
        while (r1i < this.e.size()) {
            b r0_b = (b) this.e.get(r1i);
            if (r0_b.b == r4i) {
                return r0_b;
            }
            r1i++;
        }
        return null;
    }

    b b(View r3_View) {
        while (true) {
            ViewPager r0_ViewPager = r3_View.getParent();
            if (r0_ViewPager == this) {
                return a(r3_View);
            }
            if (r0_ViewPager == null || (!r0_ViewPager instanceof View)) {
                return null;
            }
            r3_View = r0_ViewPager;
        }
    }

    void b() {
        int r0i;
        int r8i = this.h.getCount();
        this.b = r8i;
        r0i = (this.e.size() >= (this.z * 2) + 1 || this.e.size() >= r8i) ? 0 : 1;
        int r4i = 0;
        int r5i = this.i;
        int r6i = r0i;
        int r3i = 0;
        while (r3i < this.e.size()) {
            b r0_b = (b) this.e.get(r3i);
            int r7i = this.h.getItemPosition(r0_b.a);
            if (r7i == -1) {
                r0i = r3i;
                r3i = r4i;
                r4i = r5i;
                r5i = r6i;
            } else if (r7i == -2) {
                this.e.remove(r3i);
                r3i--;
                if (r4i == 0) {
                    this.h.startUpdate((ViewGroup)this);
                    r4i = 1;
                }
                this.h.destroyItem((ViewGroup)this, r0_b.b, r0_b.a);
                if (this.i == r0_b.b) {
                    r0i = r3i;
                    r3i = r4i;
                    r4i = Math.max(SCROLL_STATE_IDLE, Math.min(this.i, r8i - 1));
                    r5i = 1;
                } else {
                    r0i = r3i;
                    r3i = r4i;
                    r4i = r5i;
                    r5i = 1;
                }
            } else if (r0_b.b != r7i) {
                if (r0_b.b == this.i) {
                    r5i = r7i;
                }
                r0_b.b = r7i;
                r0i = r3i;
                r3i = r4i;
                r4i = r5i;
                r5i = 1;
            } else {
                r0i = r3i;
                r3i = r4i;
                r4i = r5i;
                r5i = r6i;
            }
            r6i = r5i;
            r5i = r4i;
            r4i = r3i;
            r3i = r0i + 1;
        }
        if (r4i != 0) {
            this.h.finishUpdate((ViewGroup)this);
        }
        Collections.sort(this.e, c);
        if (r6i != 0) {
            r4i = getChildCount();
            r3i = 0;
            while (r3i < r4i) {
                LayoutParams r0_LayoutParams = (LayoutParams) getChildAt(r3i).getLayoutParams();
                if (!r0_LayoutParams.isDecor) {
                    r0_LayoutParams.a = 0.0f;
                }
                r3i++;
            }
            a(r5i, false, true);
            requestLayout();
        }
    }

    public boolean beginFakeDrag() {
        boolean r4z = false;
        if (this.A) {
            return false;
        }
        this.P = true;
        c((int)SCROLL_STATE_DRAGGING);
        this.F = 0.0f;
        this.H = 0.0f;
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        } else {
            this.K.clear();
        }
        long r0j = SystemClock.uptimeMillis();
        MotionEvent r2_MotionEvent = MotionEvent.obtain(r0j, r0j, r4z, 0.0f, 0.0f, r4z);
        this.K.addMovement(r2_MotionEvent);
        r2_MotionEvent.recycle();
        this.Q = r0j;
        return true;
    }

    void c() {
        a(this.i);
    }

    public boolean canScrollHorizontally(int r6i) {
        boolean r0z = true;
        if (this.h == null) {
            return false;
        }
        int r2i = h();
        int r3i = getScrollX();
        if (r6i < 0) {
            if (r3i > ((int) (((float) r2i) * this.s))) {
                return r0z;
            }
            r0z = false;
            return r0z;
        } else {
            if (r6i <= 0) {
                return false;
            }
            if (r3i < ((int) (((float) r2i) * this.t))) {
                return r0z;
            }
            r0z = false;
            return r0z;
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        return r2_android_view_ViewGroup_LayoutParams instanceof LayoutParams && super.checkLayoutParams(r2_android_view_ViewGroup_LayoutParams);
    }

    public void computeScroll() {
        if (this.m.isFinished() || (!this.m.computeScrollOffset())) {
            b(true);
        } else {
            int r0i = getScrollX();
            int r1i = getScrollY();
            int r2i = this.m.getCurrX();
            int r3i = this.m.getCurrY();
            if (r0i == r2i && r1i == r3i) {
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                scrollTo(r2i, r3i);
                if (!d(r2i)) {
                    this.m.abortAnimation();
                    scrollTo(SCROLL_STATE_IDLE, r3i);
                }
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
    }

    boolean d() {
        if (this.i <= 0) {
            return false;
        }
        setCurrentItem(this.i - 1, true);
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent r2_KeyEvent) {
        return super.dispatchKeyEvent(r2_KeyEvent) || executeKeyEvent(r2_KeyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r7_AccessibilityEvent) {
        if (r7_AccessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(r7_AccessibilityEvent);
        }
        int r2i = getChildCount();
        int r1i = 0;
        while (r1i < r2i) {
            View r3_View = getChildAt(r1i);
            if (r3_View.getVisibility() == 0) {
                b r4_b = a(r3_View);
                if (r4_b != null && r4_b.b == this.i && r3_View.dispatchPopulateAccessibilityEvent(r7_AccessibilityEvent)) {
                    return true;
                }
                r1i++;
            }
            r1i++;
        }
        return false;
    }

    public void draw(Canvas r8_Canvas) {
        super.draw(r8_Canvas);
        int r0i = SCROLL_STATE_IDLE;
        int r1i = ViewCompat.getOverScrollMode(this);
        int r2i;
        int r3i;
        if (r1i != 0) {
            if (r1i != 1 || this.h == null || this.h.getCount() <= 1) {
                this.R.finish();
                this.S.finish();
            } else {
                if (this.R.isFinished()) {
                    r1i = r8_Canvas.save();
                    r2i = getHeight() - getPaddingTop() - getPaddingBottom();
                    r3i = getWidth();
                    r8_Canvas.rotate(270.0f);
                    r8_Canvas.translate((float) ((-r2i) + getPaddingTop()), this.s * ((float) r3i));
                    this.R.setSize(r2i, r3i);
                    r0i |= this.R.draw(r8_Canvas);
                    r8_Canvas.restoreToCount(r1i);
                }
                if (!this.S.isFinished()) {
                    r1i = r8_Canvas.save();
                    r2i = getWidth();
                    r8_Canvas.rotate(90.0f);
                    r8_Canvas.translate((float) (-getPaddingTop()), (-(this.t + 1.0f)) * ((float) r2i));
                    this.S.setSize(getHeight() - getPaddingTop() - getPaddingBottom(), r2i);
                    r0i |= this.S.draw(r8_Canvas);
                    r8_Canvas.restoreToCount(r1i);
                }
            }
        } else if (this.R.isFinished()) {
            if (this.S.isFinished()) {
                if (r0i == 0) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            } else {
                r1i = r8_Canvas.save();
                r2i = getWidth();
                r8_Canvas.rotate(90.0f);
                r8_Canvas.translate((float) (-getPaddingTop()), (-(this.t + 1.0f)) * ((float) r2i));
                this.S.setSize(getHeight() - getPaddingTop() - getPaddingBottom(), r2i);
                r0i |= this.S.draw(r8_Canvas);
                r8_Canvas.restoreToCount(r1i);
            }
        } else {
            r1i = r8_Canvas.save();
            r2i = getHeight() - getPaddingTop() - getPaddingBottom();
            r3i = getWidth();
            r8_Canvas.rotate(270.0f);
            r8_Canvas.translate((float) ((-r2i) + getPaddingTop()), this.s * ((float) r3i));
            this.R.setSize(r2i, r3i);
            r0i |= this.R.draw(r8_Canvas);
            r8_Canvas.restoreToCount(r1i);
            if (this.S.isFinished()) {
                r1i = r8_Canvas.save();
                r2i = getWidth();
                r8_Canvas.rotate(90.0f);
                r8_Canvas.translate((float) (-getPaddingTop()), (-(this.t + 1.0f)) * ((float) r2i));
                this.S.setSize(getHeight() - getPaddingTop() - getPaddingBottom(), r2i);
                r0i |= this.S.draw(r8_Canvas);
                r8_Canvas.restoreToCount(r1i);
            }
        }
        if (r0i == 0) {
        } else {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable r0_Drawable = this.p;
        if (r0_Drawable == null || (!r0_Drawable.isStateful())) {
        } else {
            r0_Drawable.setState(getDrawableState());
        }
    }

    boolean e() {
        if (this.h == null || this.i >= this.h.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.i + 1, true);
        return true;
    }

    public void endFakeDrag() {
        if (this.P) {
            VelocityTracker r0_VelocityTracker = this.K;
            r0_VelocityTracker.computeCurrentVelocity(LocationClientOption.MIN_SCAN_SPAN, (float) this.M);
            int r0i = (int) VelocityTrackerCompat.getXVelocity(r0_VelocityTracker, this.J);
            this.y = true;
            int r1i = h();
            int r2i = getScrollX();
            b r3_b = j();
            a(a(r3_b.b, ((((float) r2i) / ((float) r1i)) - r3_b.e) / r3_b.d, r0i, (int) (this.F - this.H)), true, true, r0i);
            k();
            this.P = false;
        } else {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
    }

    public boolean executeKeyEvent(KeyEvent r5_KeyEvent) {
        if (r5_KeyEvent.getAction() != 0) {
            return false;
        }
        switch (r5_KeyEvent.getKeyCode()) {
            case AdViewUtil.NETWORK_TYPE_WOOBOO:
                return arrowScroll(R.styleable.ActionBar_progressBarPadding);
            case Util.BEGIN_TIME:
                return arrowScroll(BDLocation.TypeOffLineLocation);
            case BDLocation.TypeGpsLocation:
                if (VERSION.SDK_INT < 11) {
                    return false;
                }
                if (KeyEventCompat.hasNoModifiers(r5_KeyEvent)) {
                    return arrowScroll(SCROLL_STATE_SETTLING);
                }
                if (KeyEventCompat.hasModifiers(r5_KeyEvent, SCROLL_STATE_DRAGGING)) {
                    return arrowScroll(SCROLL_STATE_DRAGGING);
                }
                return false;
        }
        return false;
    }

    public void fakeDragBy(float r9f) {
        if (this.P) {
            float r0f;
            float r1f;
            this.F += r9f;
            float r3f = ((float) getScrollX()) - r9f;
            int r5i = h();
            float r4f = ((float) r5i) * this.t;
            b r0_b = (b) this.e.get(SCROLL_STATE_IDLE);
            b r1_b = (b) this.e.get(this.e.size() - 1);
            r0f = r0_b.b != 0 ? r0_b.e * ((float) r5i) : ((float) r5i) * this.s;
            r1f = r1_b.b != this.h.getCount() + -1 ? r1_b.e * ((float) r5i) : r4f;
            MotionEvent r0_MotionEvent;
            if (r3f < r0f) {
                this.F += r0f - ((float) ((int) r0f));
                scrollTo((int) r0f, getScrollY());
                d((int) r0f);
                r0_MotionEvent = MotionEvent.obtain(this.Q, SystemClock.uptimeMillis(), SCROLL_STATE_SETTLING, this.F, 0.0f, SCROLL_STATE_IDLE);
                this.K.addMovement(r0_MotionEvent);
                r0_MotionEvent.recycle();
            } else if (r3f > r1f) {
                r0f = r1f;
                this.F += r0f - ((float) ((int) r0f));
                scrollTo((int) r0f, getScrollY());
                d((int) r0f);
                r0_MotionEvent = MotionEvent.obtain(this.Q, SystemClock.uptimeMillis(), SCROLL_STATE_SETTLING, this.F, 0.0f, SCROLL_STATE_IDLE);
                this.K.addMovement(r0_MotionEvent);
                r0_MotionEvent.recycle();
            } else {
                r0f = r3f;
                this.F += r0f - ((float) ((int) r0f));
                scrollTo((int) r0f, getScrollY());
                d((int) r0f);
                r0_MotionEvent = MotionEvent.obtain(this.Q, SystemClock.uptimeMillis(), SCROLL_STATE_SETTLING, this.F, 0.0f, SCROLL_STATE_IDLE);
                this.K.addMovement(r0_MotionEvent);
                r0_MotionEvent.recycle();
            }
        } else {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet r3_AttributeSet) {
        return new LayoutParams(getContext(), r3_AttributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter() {
        return this.h;
    }

    protected int getChildDrawingOrder(int r3i, int r4i) {
        if (this.ae == 2) {
            r4i = r3i - 1 - r4i;
        }
        return ((LayoutParams) ((View) this.af.get(r4i)).getLayoutParams()).d;
    }

    public int getCurrentItem() {
        return this.i;
    }

    public int getOffscreenPageLimit() {
        return this.z;
    }

    public int getPageMargin() {
        return this.o;
    }

    public boolean isFakeDragging() {
        return this.P;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.T = true;
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.ah);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas r17_Canvas) {
        super.onDraw(r17_Canvas);
        if (this.o <= 0 || this.p == null || this.e.size() <= 0 || this.h == null) {
        } else {
            int r6i = getScrollX();
            int r7i = getWidth();
            float r8f = ((float) this.o) / ((float) r7i);
            b r1_b = (b) this.e.get(SCROLL_STATE_IDLE);
            float r4f = r1_b.e;
            int r9i = this.e.size();
            int r3i = r1_b.b;
            int r10i = ((b) this.e.get(r9i - 1)).b;
            int r2i = 0;
            int r5i = r3i;
            while (r5i < r10i) {
                float r3f;
                while (r5i > r1_b.b && r2i < r9i) {
                    r2i++;
                    r1_b = (b) this.e.get(r2i);
                }
                if (r5i == r1_b.b) {
                    r3f = (r1_b.e + r1_b.d) * ((float) r7i);
                    r4f = r1_b.e + r1_b.d + r8f;
                } else {
                    float r11f = this.h.getPageWidth(r5i);
                    r3f = (r4f + r11f) * ((float) r7i);
                    r4f += r11f + r8f;
                }
                if (((float) this.o) + r3f > ((float) r6i)) {
                    this.p.setBounds((int) r3f, this.q, (int) (((float) this.o) + r3f + 0.5f), this.r);
                    this.p.draw(r17_Canvas);
                }
                if (r3f > ((float) (r6i + r7i))) {
                } else {
                    r5i++;
                }
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent r14_MotionEvent) {
        int r0i = r14_MotionEvent.getAction() & 255;
        if (r0i == 3 || r0i == 1) {
            this.A = false;
            this.B = false;
            this.J = -1;
            if (this.K == null) {
                return false;
            }
            this.K.recycle();
            this.K = null;
            return false;
        } else {
            float r0f;
            float r7f;
            float r8f;
            float r9f;
            float r10f;
            float r11f;
            if (r0i != 0) {
                if (this.A) {
                    return true;
                }
                if (this.B) {
                    return false;
                }
                switch (r0i) {
                    case SCROLL_STATE_IDLE:
                        r0f = r14_MotionEvent.getX();
                        this.H = r0f;
                        this.F = r0f;
                        r0f = r14_MotionEvent.getY();
                        this.I = r0f;
                        this.G = r0f;
                        this.J = MotionEventCompat.getPointerId(r14_MotionEvent, SCROLL_STATE_IDLE);
                        this.B = false;
                        this.m.computeScrollOffset();
                        if (this.ai != 2 || Math.abs(this.m.getFinalX() - this.m.getCurrX()) <= this.O) {
                            b(false);
                            this.A = false;
                        } else {
                            this.m.abortAnimation();
                            this.y = false;
                            c();
                            this.A = true;
                            d(true);
                            c((int)SCROLL_STATE_DRAGGING);
                        }
                        if (this.K == null) {
                            this.K = VelocityTracker.obtain();
                        }
                        this.K.addMovement(r14_MotionEvent);
                        return this.A;
                    case SCROLL_STATE_SETTLING:
                        r0i = this.J;
                        if (r0i == -1) {
                            r0i = MotionEventCompat.findPointerIndex(r14_MotionEvent, r0i);
                            r7f = MotionEventCompat.getX(r14_MotionEvent, r0i);
                            r8f = r7f - this.F;
                            r9f = Math.abs(r8f);
                            r10f = MotionEventCompat.getY(r14_MotionEvent, r0i);
                            r11f = Math.abs(r10f - this.I);
                            if (r8f == 0.0f || a(this.F, r8f) || (!a(this, false, (int) r8f, (int) r7f, (int) r10f))) {
                                if (r9f <= ((float) this.E) || 0.5f * r9f <= r11f) {
                                    if (r11f <= ((float) this.E)) {
                                        this.B = true;
                                    }
                                    if (this.A) {
                                        if (b(r7f)) {
                                            ViewCompat.postInvalidateOnAnimation(this);
                                        }
                                    }
                                } else {
                                    this.A = true;
                                    d(true);
                                    c((int)SCROLL_STATE_DRAGGING);
                                    this.F = (r8f > 0.0f ? 1 : (r8f == 0.0f? 0 : -1)) <= 0 ? this.H + ((float) this.E) : this.H - ((float) this.E);
                                    this.G = r10f;
                                    e(true);
                                    if (this.A) {
                                    } else if (b(r7f)) {
                                    } else {
                                        ViewCompat.postInvalidateOnAnimation(this);
                                    }
                                }
                            } else {
                                this.F = r7f;
                                this.G = r10f;
                                this.B = true;
                                return false;
                            }
                        }
                    case ShareUtils.SHARE_COPY:
                        a(r14_MotionEvent);
                        break;
                }
            }
            switch (r0i) {
                case SCROLL_STATE_IDLE:
                    r0f = r14_MotionEvent.getX();
                    this.H = r0f;
                    this.F = r0f;
                    r0f = r14_MotionEvent.getY();
                    this.I = r0f;
                    this.G = r0f;
                    this.J = MotionEventCompat.getPointerId(r14_MotionEvent, SCROLL_STATE_IDLE);
                    this.B = false;
                    this.m.computeScrollOffset();
                    if (this.ai != 2 || Math.abs(this.m.getFinalX() - this.m.getCurrX()) <= this.O) {
                        b(false);
                        this.A = false;
                    } else {
                        this.m.abortAnimation();
                        this.y = false;
                        c();
                        this.A = true;
                        d(true);
                        c((int)SCROLL_STATE_DRAGGING);
                    }
                    if (this.K == null) {
                        this.K.addMovement(r14_MotionEvent);
                        return this.A;
                    } else {
                        this.K = VelocityTracker.obtain();
                        this.K.addMovement(r14_MotionEvent);
                        return this.A;
                    }
                case SCROLL_STATE_SETTLING:
                    r0i = this.J;
                    if (r0i == -1) {
                    } else {
                        r0i = MotionEventCompat.findPointerIndex(r14_MotionEvent, r0i);
                        r7f = MotionEventCompat.getX(r14_MotionEvent, r0i);
                        r8f = r7f - this.F;
                        r9f = Math.abs(r8f);
                        r10f = MotionEventCompat.getY(r14_MotionEvent, r0i);
                        r11f = Math.abs(r10f - this.I);
                        if (r8f == 0.0f || a(this.F, r8f) || a(this, false, (int) r8f, (int) r7f, (int) r10f)) {
                            if (r9f <= ((float) this.E) || 0.5f * r9f <= r11f) {
                                if (r11f <= ((float) this.E)) {
                                    if (this.A) {
                                        if (b(r7f)) {
                                            ViewCompat.postInvalidateOnAnimation(this);
                                        }
                                    }
                                } else {
                                    this.B = true;
                                    if (this.A) {
                                    } else if (b(r7f)) {
                                    } else {
                                        ViewCompat.postInvalidateOnAnimation(this);
                                    }
                                }
                            } else {
                                this.A = true;
                                d(true);
                                c((int)SCROLL_STATE_DRAGGING);
                                if ((r8f > 0.0f ? 1 : (r8f == 0.0f? 0 : -1)) <= 0) {
                                }
                                this.F = (r8f > 0.0f ? 1 : (r8f == 0.0f? 0 : -1)) <= 0 ? this.H + ((float) this.E) : this.H - ((float) this.E);
                                this.G = r10f;
                                e(true);
                                if (this.A) {
                                    if (b(r7f)) {
                                        ViewCompat.postInvalidateOnAnimation(this);
                                    }
                                }
                            }
                        } else {
                            this.F = r7f;
                            this.G = r10f;
                            this.B = true;
                            return false;
                        }
                    }
                case ShareUtils.SHARE_COPY:
                    a(r14_MotionEvent);
                    break;
            }
            if (this.K == null) {
                this.K = VelocityTracker.obtain();
            }
            this.K.addMovement(r14_MotionEvent);
            return this.A;
        }
    }

    protected void onLayout(boolean r18z, int r19i, int r20i, int r21i, int r22i) {
        LayoutParams r1_LayoutParams;
        int r7i;
        int r9i = getChildCount();
        int r10i = r21i - r19i;
        int r11i = r22i - r20i;
        int r6i = getPaddingLeft();
        int r2i = getPaddingTop();
        int r5i = getPaddingRight();
        int r3i = getPaddingBottom();
        int r12i = getScrollX();
        int r4i = SCROLL_STATE_IDLE;
        int r8i = 0;
        while (r8i < r9i) {
            int r1i;
            View r13_View = getChildAt(r8i);
            if (r13_View.getVisibility() != 8) {
                r1_LayoutParams = (LayoutParams) r13_View.getLayoutParams();
                if (r1_LayoutParams.isDecor) {
                    int r14i = r1_LayoutParams.gravity & 112;
                    switch ((r1_LayoutParams.gravity & 7)) {
                        case SCROLL_STATE_DRAGGING:
                            r7i = Math.max((r10i - r13_View.getMeasuredWidth()) / 2, r6i);
                            switch (r14i) {
                                case Base64.URL_SAFE:
                                    r1i = Math.max((r11i - r13_View.getMeasuredHeight()) / 2, r2i);
                                    r3i = r2i;
                                    r2i = r3i;
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                                case AdViewUtil.NETWORK_TYPE_ADUU:
                                    r2i = r3i;
                                    r3i = r13_View.getMeasuredHeight() + r2i;
                                    r1i = r2i;
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                                case 80:
                                    r1i = r11i - r3i - r13_View.getMeasuredHeight();
                                    r3i = r2i;
                                    r2i = r3i + r13_View.getMeasuredHeight();
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                            }
                            r1i = r2i;
                            r3i = r2i;
                            r2i = r3i;
                            r7i += r12i;
                            r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                            r1i = r4i + 1;
                            r4i = r3i;
                            r3i = r2i;
                            r2i = r5i;
                            r5i = r6i;
                            break;
                        case XListViewFooter.STATE_NOMORE:
                            r7i = r6i;
                            r6i = r13_View.getMeasuredWidth() + r6i;
                            switch (r14i) {
                                case Base64.URL_SAFE:
                                    r1i = Math.max((r11i - r13_View.getMeasuredHeight()) / 2, r2i);
                                    r3i = r2i;
                                    r2i = r3i;
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                                case AdViewUtil.NETWORK_TYPE_ADUU:
                                    r2i = r3i;
                                    r3i = r13_View.getMeasuredHeight() + r2i;
                                    r1i = r2i;
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                                case 80:
                                    r1i = r11i - r3i - r13_View.getMeasuredHeight();
                                    r3i = r2i;
                                    r2i = r3i + r13_View.getMeasuredHeight();
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                            }
                            r1i = r2i;
                            r3i = r2i;
                            r2i = r3i;
                            r7i += r12i;
                            r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                            r1i = r4i + 1;
                            r4i = r3i;
                            r3i = r2i;
                            r2i = r5i;
                            r5i = r6i;
                            break;
                        case ShareUtils.SHARE_SMS:
                            r5i += r13_View.getMeasuredWidth();
                            r7i = r10i - r5i - r13_View.getMeasuredWidth();
                            switch (r14i) {
                                case Base64.URL_SAFE:
                                    r1i = Math.max((r11i - r13_View.getMeasuredHeight()) / 2, r2i);
                                    r3i = r2i;
                                    r2i = r3i;
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                                case AdViewUtil.NETWORK_TYPE_ADUU:
                                    r2i = r3i;
                                    r3i = r13_View.getMeasuredHeight() + r2i;
                                    r1i = r2i;
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                                case 80:
                                    r1i = r11i - r3i - r13_View.getMeasuredHeight();
                                    r3i = r2i;
                                    r2i = r3i + r13_View.getMeasuredHeight();
                                    r7i += r12i;
                                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                                    r1i = r4i + 1;
                                    r4i = r3i;
                                    r3i = r2i;
                                    r2i = r5i;
                                    r5i = r6i;
                                    break;
                            }
                            r1i = r2i;
                            r3i = r2i;
                            r2i = r3i;
                            r7i += r12i;
                            r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                            r1i = r4i + 1;
                            r4i = r3i;
                            r3i = r2i;
                            r2i = r5i;
                            r5i = r6i;
                            break;
                    }
                    r7i = r6i;
                    switch (r14i) {
                        case Base64.URL_SAFE:
                            r1i = Math.max((r11i - r13_View.getMeasuredHeight()) / 2, r2i);
                            r3i = r2i;
                            r2i = r3i;
                            r7i += r12i;
                            r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                            r1i = r4i + 1;
                            r4i = r3i;
                            r3i = r2i;
                            r2i = r5i;
                            r5i = r6i;
                            break;
                        case AdViewUtil.NETWORK_TYPE_ADUU:
                            r2i = r3i;
                            r3i = r13_View.getMeasuredHeight() + r2i;
                            r1i = r2i;
                            r7i += r12i;
                            r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                            r1i = r4i + 1;
                            r4i = r3i;
                            r3i = r2i;
                            r2i = r5i;
                            r5i = r6i;
                            break;
                        case 80:
                            r1i = r11i - r3i - r13_View.getMeasuredHeight();
                            r3i = r2i;
                            r2i = r3i + r13_View.getMeasuredHeight();
                            r7i += r12i;
                            r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                            r1i = r4i + 1;
                            r4i = r3i;
                            r3i = r2i;
                            r2i = r5i;
                            r5i = r6i;
                            break;
                    }
                    r1i = r2i;
                    r3i = r2i;
                    r2i = r3i;
                    r7i += r12i;
                    r13_View.layout(r7i, r1i, r13_View.getMeasuredWidth() + r7i, r13_View.getMeasuredHeight() + r1i);
                    r1i = r4i + 1;
                    r4i = r3i;
                    r3i = r2i;
                    r2i = r5i;
                    r5i = r6i;
                }
                r1i = r4i;
                r4i = r2i;
                r2i = r5i;
                r5i = r6i;
            } else {
                r1i = r4i;
                r4i = r2i;
                r2i = r5i;
                r5i = r6i;
            }
            r8i++;
            r6i = r5i;
            r5i = r2i;
            r2i = r4i;
            r4i = r1i;
        }
        r7i = r10i - r6i - r5i;
        r5i = 0;
        while (r5i < r9i) {
            View r8_View = getChildAt(r5i);
            if (r8_View.getVisibility() != 8) {
                r1_LayoutParams = (LayoutParams) r8_View.getLayoutParams();
                if (!r1_LayoutParams.isDecor) {
                    b r10_b = a(r8_View);
                    if (r10_b != null) {
                        r10i = ((int) (r10_b.e * ((float) r7i))) + r6i;
                        if (r1_LayoutParams.b) {
                            r1_LayoutParams.b = false;
                            r8_View.measure(MeasureSpec.makeMeasureSpec((int) (r1_LayoutParams.a * ((float) r7i)), 1073741824), MeasureSpec.makeMeasureSpec(r11i - r2i - r3i, 1073741824));
                        }
                        r8_View.layout(r10i, r2i, r8_View.getMeasuredWidth() + r10i, r8_View.getMeasuredHeight() + r2i);
                    }
                }
            }
            r5i++;
        }
        this.q = r2i;
        this.r = r11i - r3i;
        this.W = r4i;
        if (this.T) {
            a(this.i, false, (int)SCROLL_STATE_IDLE, false);
        }
        this.T = false;
    }

    protected void onMeasure(int r14i, int r15i) {
        LayoutParams r0_LayoutParams;
        int r2i;
        int r1i;
        setMeasuredDimension(getDefaultSize(SCROLL_STATE_IDLE, r14i), getDefaultSize(SCROLL_STATE_IDLE, r15i));
        int r0i = getMeasuredWidth();
        this.D = Math.min(r0i / 10, this.C);
        int r3i = r0i - getPaddingLeft() - getPaddingRight();
        int r5i = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int r9i = getChildCount();
        int r8i = 0;
        while (r8i < r9i) {
            View r10_View = getChildAt(r8i);
            if (r10_View.getVisibility() != 8) {
                r0_LayoutParams = (LayoutParams) r10_View.getLayoutParams();
                if (r0_LayoutParams == null || (!r0_LayoutParams.isDecor)) {
                    r8i++;
                } else {
                    int r7i;
                    int r6i = r0_LayoutParams.gravity & 7;
                    int r4i = r0_LayoutParams.gravity & 112;
                    r2i = ExploreByTouchHelper.INVALID_ID;
                    r1i = ExploreByTouchHelper.INVALID_ID;
                    r7i = (r4i == 48 || r4i == 80) ? 1 : 0;
                    r6i = (r6i == 3 || r6i == 5) ? 1 : 0;
                    if (r7i != 0) {
                        r2i = 1073741824;
                    } else if (r6i != 0) {
                        r1i = 1073741824;
                    }
                    if (r0_LayoutParams.width != -2) {
                        r4i = 1073741824;
                        r2i = r0_LayoutParams.width != -1 ? r0_LayoutParams.width : r3i;
                    } else {
                        r4i = r2i;
                        r2i = r3i;
                    }
                    if (r0_LayoutParams.height != -2) {
                        r1i = 1073741824;
                        if (r0_LayoutParams.height != -1) {
                            r0i = r0_LayoutParams.height;
                        }
                        r0i = r5i;
                    } else {
                        r0i = r5i;
                    }
                    r10_View.measure(MeasureSpec.makeMeasureSpec(r2i, r4i), MeasureSpec.makeMeasureSpec(r0i, r1i));
                    if (r7i != 0) {
                        r5i -= r10_View.getMeasuredHeight();
                    } else if (r6i != 0) {
                        r3i -= r10_View.getMeasuredWidth();
                    }
                }
            }
            r8i++;
        }
        this.u = MeasureSpec.makeMeasureSpec(r3i, 1073741824);
        this.v = MeasureSpec.makeMeasureSpec(r5i, 1073741824);
        this.w = true;
        c();
        this.w = false;
        r2i = getChildCount();
        r1i = 0;
        while (r1i < r2i) {
            View r4_View = getChildAt(r1i);
            if (r4_View.getVisibility() != 8) {
                r0_LayoutParams = (LayoutParams) r4_View.getLayoutParams();
                if (r0_LayoutParams == null || (!r0_LayoutParams.isDecor)) {
                    r4_View.measure(MeasureSpec.makeMeasureSpec((int) (r0_LayoutParams.a * ((float) r3i)), 1073741824), this.v);
                }
            }
            r1i++;
        }
    }

    protected boolean onRequestFocusInDescendants(int r9i, Rect r10_Rect) {
        int r3i;
        int r1i = -1;
        int r0i = getChildCount();
        if ((r9i & 2) != 0) {
            r1i = 1;
            r3i = 0;
        } else {
            r3i = r0i - 1;
            r0i = -1;
        }
        while (r3i != r0i) {
            View r5_View = getChildAt(r3i);
            if (r5_View.getVisibility() == 0) {
                b r6_b = a(r5_View);
                if (r6_b != null && r6_b.b == this.i && r5_View.requestFocus(r9i, r10_Rect)) {
                    return true;
                }
                r3i += r1i;
            }
            r3i += r1i;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable r4_Parcelable) {
        if (r4_Parcelable instanceof SavedState) {
            SavedState r4_SavedState = (SavedState) r4_Parcelable;
            super.onRestoreInstanceState(r4_SavedState.getSuperState());
            if (this.h != null) {
                this.h.restoreState(r4_SavedState.b, r4_SavedState.c);
                a(r4_SavedState.a, false, true);
            } else {
                this.j = r4_SavedState.a;
                this.k = r4_SavedState.b;
                this.l = r4_SavedState.c;
            }
        } else {
            super.onRestoreInstanceState(r4_Parcelable);
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable r1_Parcelable = new SavedState(super.onSaveInstanceState());
        r1_Parcelable.a = this.i;
        if (this.h != null) {
            r1_Parcelable.b = this.h.saveState();
        }
        return r1_Parcelable;
    }

    protected void onSizeChanged(int r3i, int r4i, int r5i, int r6i) {
        super.onSizeChanged(r3i, r4i, r5i, r6i);
        if (r3i != r5i) {
            a(r3i, r5i, this.o, this.o);
        }
    }

    public boolean onTouchEvent(MotionEvent r8_MotionEvent) {
        int r2i = false;
        if (this.P) {
            return true;
        }
        if (r8_MotionEvent.getAction() == 0 && r8_MotionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.h == null || this.h.getCount() == 0) {
            return false;
        }
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        }
        this.K.addMovement(r8_MotionEvent);
        float r0f;
        int r0i;
        switch ((r8_MotionEvent.getAction() & 255)) {
            case SCROLL_STATE_IDLE:
                this.m.abortAnimation();
                this.y = false;
                c();
                r0f = r8_MotionEvent.getX();
                this.H = r0f;
                this.F = r0f;
                r0f = r8_MotionEvent.getY();
                this.I = r0f;
                this.G = r0f;
                this.J = MotionEventCompat.getPointerId(r8_MotionEvent, SCROLL_STATE_IDLE);
                break;
            case SCROLL_STATE_DRAGGING:
                if (this.A) {
                    VelocityTracker r0_VelocityTracker = this.K;
                    r0_VelocityTracker.computeCurrentVelocity(LocationClientOption.MIN_SCAN_SPAN, (float) this.M);
                    r0i = (int) VelocityTrackerCompat.getXVelocity(r0_VelocityTracker, this.J);
                    this.y = true;
                    r2i = h();
                    int r3i = getScrollX();
                    b r4_b = j();
                    a(a(r4_b.b, ((((float) r3i) / ((float) r2i)) - r4_b.e) / r4_b.d, r0i, (int) (MotionEventCompat.getX(r8_MotionEvent, MotionEventCompat.findPointerIndex(r8_MotionEvent, this.J)) - this.H)), true, true, r0i);
                    this.J = -1;
                    k();
                    r2i = this.S.onRelease() | this.R.onRelease();
                }
                break;
            case SCROLL_STATE_SETTLING:
                if (!this.A) {
                    r0i = MotionEventCompat.findPointerIndex(r8_MotionEvent, this.J);
                    float r3f = MotionEventCompat.getX(r8_MotionEvent, r0i);
                    float r4f = Math.abs(r3f - this.F);
                    float r5f = MotionEventCompat.getY(r8_MotionEvent, r0i);
                    r0f = Math.abs(r5f - this.G);
                    if (r4f <= ((float) this.E) || r4f <= r0f) {
                    } else {
                        this.A = true;
                        d(true);
                        this.F = ((r3f - this.H) > 0.0f ? 1 : ((r3f - this.H) == 0.0f? 0 : -1)) > 0 ? this.H + ((float) this.E) : this.H - ((float) this.E);
                        this.G = r5f;
                        c((int)SCROLL_STATE_DRAGGING);
                        e(true);
                        ViewParent r0_ViewParent = getParent();
                        if (r0_ViewParent != null) {
                            r0_ViewParent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.A) {
                    r2i |= b(MotionEventCompat.getX(r8_MotionEvent, MotionEventCompat.findPointerIndex(r8_MotionEvent, this.J)));
                }
                break;
            case XListViewFooter.STATE_NOMORE:
                if (this.A) {
                    a(this.i, true, r2i, (boolean)r2i);
                    this.J = -1;
                    k();
                    r2i = this.S.onRelease() | this.R.onRelease();
                }
                break;
            case ShareUtils.SHARE_SMS:
                r0i = MotionEventCompat.getActionIndex(r8_MotionEvent);
                this.F = MotionEventCompat.getX(r8_MotionEvent, r0i);
                this.J = MotionEventCompat.getPointerId(r8_MotionEvent, r0i);
                break;
            case ShareUtils.SHARE_COPY:
                a(r8_MotionEvent);
                this.F = MotionEventCompat.getX(r8_MotionEvent, MotionEventCompat.findPointerIndex(r8_MotionEvent, this.J));
                break;
        }
        if (r2i == true) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    public void removeView(View r2_View) {
        if (this.w) {
            removeViewInLayout(r2_View);
        } else {
            super.removeView(r2_View);
        }
    }

    public void setAdapter(PagerAdapter r8_PagerAdapter) {
        if (this.h != null) {
            this.h.unregisterDataSetObserver(this.n);
            this.h.startUpdate((ViewGroup)this);
            int r1i = 0;
            while (r1i < this.e.size()) {
                b r0_b = (b) this.e.get(r1i);
                this.h.destroyItem((ViewGroup)this, r0_b.b, r0_b.a);
                r1i++;
            }
            this.h.finishUpdate((ViewGroup)this);
            this.e.clear();
            g();
            this.i = 0;
            scrollTo(SCROLL_STATE_IDLE, SCROLL_STATE_IDLE);
        }
        PagerAdapter r0_PagerAdapter = this.h;
        this.h = r8_PagerAdapter;
        this.b = 0;
        if (this.h != null) {
            if (this.n == null) {
                this.n = new e(null);
            }
            this.h.registerDataSetObserver(this.n);
            this.y = false;
            boolean r1z = this.T;
            this.T = true;
            this.b = this.h.getCount();
            if (this.j >= 0) {
                this.h.restoreState(this.k, this.l);
                a(this.j, false, true);
                this.j = -1;
                this.k = null;
                this.l = null;
            } else if (r1z) {
                requestLayout();
            } else {
                c();
            }
        }
        if (this.ab == null || r0_PagerAdapter == r8_PagerAdapter) {
        } else {
            this.ab.onAdapterChanged(r0_PagerAdapter, r8_PagerAdapter);
        }
    }

    public void setCurrentItem(int r3i) {
        this.y = false;
        a(r3i, !(this.T), false);
    }

    public void setCurrentItem(int r2i, boolean r3z) {
        this.y = false;
        a(r2i, r3z, false);
    }

    public void setOffscreenPageLimit(int r5i) {
        if (r5i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + r5i + " too small; defaulting to " + SCROLL_STATE_DRAGGING);
            r5i = 1;
        }
        if (r5i != this.z) {
            this.z = r5i;
            c();
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener r1_OnPageChangeListener) {
        this.Z = r1_OnPageChangeListener;
    }

    public void setPageMargin(int r3i) {
        int r0i = this.o;
        this.o = r3i;
        int r1i = getWidth();
        a(r1i, r1i, r3i, r0i);
        requestLayout();
    }

    public void setPageMarginDrawable(int r2i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(r2i));
    }

    public void setPageMarginDrawable(Drawable r2_Drawable) {
        this.p = r2_Drawable;
        if (r2_Drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(r2_Drawable == null);
        invalidate();
    }

    public void setPageTransformer(boolean r5z, PageTransformer r6_PageTransformer) {
        int r1i = SCROLL_STATE_DRAGGING;
        if (VERSION.SDK_INT >= 11) {
            boolean r0z;
            int r3i;
            r0z = r6_PageTransformer != null;
            r3i = r0z != (this.ac != null) ? 1 : 0;
            this.ac = r6_PageTransformer;
            a(r0z);
            if (r0z) {
                if (r5z) {
                    r1i = SCROLL_STATE_SETTLING;
                }
                this.ae = r1i;
            } else {
                this.ae = 0;
            }
            if (r3i != 0) {
                c();
            }
        }
    }

    protected boolean verifyDrawable(Drawable r2_Drawable) {
        return super.verifyDrawable(r2_Drawable) || r2_Drawable == this.p;
    }
}