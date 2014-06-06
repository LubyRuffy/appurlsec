package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

public class DrawerLayout extends ViewGroup {
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNLOCKED = 0;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final int[] a;
    private int b;
    private int c;
    private float d;
    private Paint e;
    private final ViewDragHelper f;
    private final ViewDragHelper g;
    private final b h;
    private final b i;
    private int j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private DrawerListener q;
    private float r;
    private float s;
    private Drawable t;
    private Drawable u;

    public static interface DrawerListener {
        public void onDrawerClosed(View r1_View);

        public void onDrawerOpened(View r1_View);

        public void onDrawerSlide(View r1_View, float r2f);

        public void onDrawerStateChanged(int r1i);
    }

    public static class LayoutParams extends MarginLayoutParams {
        float a;
        boolean b;
        boolean c;
        public int gravity;

        public LayoutParams(int r2i, int r3i) {
            super(r2i, r3i);
            this.gravity = 0;
        }

        public LayoutParams(int r1i, int r2i, int r3i) {
            this(r1i, r2i);
            this.gravity = r3i;
        }

        public LayoutParams(Context r3_Context, AttributeSet r4_AttributeSet) {
            super(r3_Context, r4_AttributeSet);
            this.gravity = 0;
            TypedArray r0_TypedArray = r3_Context.obtainStyledAttributes(r4_AttributeSet, a);
            this.gravity = r0_TypedArray.getInt(STATE_IDLE, STATE_IDLE);
            r0_TypedArray.recycle();
        }

        public LayoutParams(android.support.v4.widget.DrawerLayout.LayoutParams r2_android_support_v4_widget_DrawerLayout_LayoutParams) {
            super(r2_android_support_v4_widget_DrawerLayout_LayoutParams);
            this.gravity = 0;
            this.gravity = r2_android_support_v4_widget_DrawerLayout_LayoutParams.gravity;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
            super(r2_android_view_ViewGroup_LayoutParams);
            this.gravity = 0;
        }

        public LayoutParams(MarginLayoutParams r2_MarginLayoutParams) {
            super(r2_MarginLayoutParams);
            this.gravity = 0;
        }
    }

    protected static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int a;
        int b;
        int c;

        static {
            CREATOR = new d();
        }

        public SavedState(Parcel r2_Parcel) {
            super(r2_Parcel);
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.a = r2_Parcel.readInt();
        }

        public SavedState(Parcelable r2_Parcelable) {
            super(r2_Parcelable);
            this.a = 0;
            this.b = 0;
            this.c = 0;
        }

        public void writeToParcel(Parcel r2_Parcel, int r3i) {
            super.writeToParcel(r2_Parcel, r3i);
            r2_Parcel.writeInt(this.a);
        }
    }

    public static abstract class SimpleDrawerListener implements android.support.v4.widget.DrawerLayout.DrawerListener {
        public void onDrawerClosed(View r1_View) {
        }

        public void onDrawerOpened(View r1_View) {
        }

        public void onDrawerSlide(View r1_View, float r2f) {
        }

        public void onDrawerStateChanged(int r1i) {
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
        }

        private void a_(AccessibilityNodeInfoCompat r5_AccessibilityNodeInfoCompat, ViewGroup r6_ViewGroup) {
            int r2i = r6_ViewGroup.getChildCount();
            int r1i = 0;
            while (r1i < r2i) {
                View r0_View = r6_ViewGroup.getChildAt(r1i);
                if (filter(r0_View)) {
                    r1i++;
                } else {
                    switch (ViewCompat.getImportantForAccessibility(r0_View)) {
                        case STATE_IDLE:
                            ViewCompat.setImportantForAccessibility(r0_View, STATE_DRAGGING);
                            break;
                        case STATE_DRAGGING:
                            break;
                        case STATE_SETTLING:
                            if (r0_View instanceof ViewGroup) {
                                a(r5_AccessibilityNodeInfoCompat, (ViewGroup) r0_View);
                            }
                            r1i++;
                            break;
                        case XListViewFooter.STATE_NODATA:
                            r1i++;
                            break;
                        default:
                            r1i++;
                            break;
                    }
                    r5_AccessibilityNodeInfoCompat.addChild(r0_View);
                    r1i++;
                }
            }
        }

        public boolean filter(View r2_View) {
            View r0_View = DrawerLayout.this.a();
            return r0_View != null && r0_View != r2_View;
        }

        public void onInitializeAccessibilityNodeInfo(View r4_View, AccessibilityNodeInfoCompat r5_AccessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat r1_AccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(r5_AccessibilityNodeInfoCompat);
            super.onInitializeAccessibilityNodeInfo(r4_View, r1_AccessibilityNodeInfoCompat);
            r5_AccessibilityNodeInfoCompat.setSource(r4_View);
            ViewParent r0_ViewParent = ViewCompat.getParentForAccessibility(r4_View);
            if (r0_ViewParent instanceof View) {
                r5_AccessibilityNodeInfoCompat.setParent((View) r0_ViewParent);
            }
            a(r5_AccessibilityNodeInfoCompat, r1_AccessibilityNodeInfoCompat);
            r1_AccessibilityNodeInfoCompat.recycle();
            a(r5_AccessibilityNodeInfoCompat, (ViewGroup) r4_View);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup r2_ViewGroup, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
            return filter(r3_View) ? false : super.onRequestSendAccessibilityEvent(r2_ViewGroup, r3_View, r4_AccessibilityEvent);
        }
    }

    private class b extends Callback {
        private final int b;
        private ViewDragHelper c;
        private final Runnable d;

        public b(int r3i) {
            this.d = new e(this);
            this.b = r3i;
        }

        private void a() {
            int r0i = XListViewFooter.STATE_NOMORE;
            if (this.b == 3) {
                r0i = ShareUtils.SHARE_SMS;
            }
            View r0_View = DrawerLayout.this.a(r0i);
            if (r0_View != null) {
                DrawerLayout.this.closeDrawer(r0_View);
            }
        }

        private void b_() {
            int r3i;
            View r2_View;
            int r1i;
            int r0i = STATE_IDLE;
            int r2i = this.c.getEdgeSize();
            r3i = this.b == 3 ? 1 : 0;
            if (r3i != 0) {
                View r1_View = DrawerLayout.this.a((int)XListViewFooter.STATE_NOMORE);
                if (r1_View != null) {
                    r0i = -r1_View.getWidth();
                }
                r2_View = r1_View;
                r1i = r0i + r2i;
            } else {
                r2_View = DrawerLayout.this.a((int)ShareUtils.SHARE_SMS);
                r1i = DrawerLayout.this.getWidth() - r2i;
            }
            if (r2_View != null) {
                if (r3i == 0 || r2_View.getLeft() >= r1i) {
                    if (r3i == 0 && r2_View.getLeft() > r1i && DrawerLayout.this.getDrawerLockMode(r2_View) == 0) {
                        this.c.smoothSlideViewTo(r2_View, r1i, r2_View.getTop());
                        ((android.support.v4.widget.DrawerLayout.LayoutParams) r2_View.getLayoutParams()).b = true;
                        DrawerLayout.this.invalidate();
                        a();
                        DrawerLayout.this.b();
                    }
                }
            }
        }

        public int clampViewPositionHorizontal(View r3_View, int r4i, int r5i) {
            if (DrawerLayout.this.a(r3_View, (int)XListViewFooter.STATE_NOMORE)) {
                return Math.max(-r3_View.getWidth(), Math.min(r4i, STATE_IDLE));
            }
            int r0i = DrawerLayout.this.getWidth();
            return Math.max(r0i - r3_View.getWidth(), Math.min(r4i, r0i));
        }

        public int clampViewPositionVertical(View r2_View, int r3i, int r4i) {
            return r2_View.getTop();
        }

        public int getViewHorizontalDragRange(View r2_View) {
            return r2_View.getWidth();
        }

        public void onEdgeDragStarted(int r3i, int r4i) {
            View r0_View;
            r0_View = (r3i & 1) == 1 ? DrawerLayout.this.a((int)XListViewFooter.STATE_NOMORE) : DrawerLayout.this.a((int)ShareUtils.SHARE_SMS);
            if (r0_View == null || DrawerLayout.this.getDrawerLockMode(r0_View) != 0) {
            } else {
                this.c.captureChildView(r0_View, r4i);
            }
        }

        public boolean onEdgeLock(int r2i) {
            return false;
        }

        public void onEdgeTouched(int r5i, int r6i) {
            DrawerLayout.this.postDelayed(this.d, 160);
        }

        public void onViewCaptured(View r3_View, int r4i) {
            ((android.support.v4.widget.DrawerLayout.LayoutParams) r3_View.getLayoutParams()).b = false;
            a();
        }

        public void onViewDragStateChanged(int r4i) {
            DrawerLayout.this.a(this.b, r4i, this.c.getCapturedView());
        }

        public void onViewPositionChanged(View r4_View, int r5i, int r6i, int r7i, int r8i) {
            float r0f;
            int r0i = r4_View.getWidth();
            r0f = DrawerLayout.this.a(r4_View, (int)XListViewFooter.STATE_NOMORE) ? ((float) (r0i + r5i)) / ((float) r0i) : ((float) (DrawerLayout.this.getWidth() - r5i)) / ((float) r0i);
            DrawerLayout.this.b(r4_View, r0f);
            r4_View.setVisibility((r0f > 0.0f ? 1 : (r0f == 0.0f? 0 : -1)) == 0 ? XListViewFooter.STATE_NODATA : STATE_IDLE);
            DrawerLayout.this.invalidate();
        }

        public void onViewReleased(View r7_View, float r8f, float r9f) {
            int r0i;
            float r1f = DrawerLayout.this.c(r7_View);
            int r2i = r7_View.getWidth();
            if (DrawerLayout.this.a(r7_View, (int)XListViewFooter.STATE_NOMORE)) {
                if (r8f <= 0.0f) {
                    if (r8f != 0.0f || r1f <= 0.5f) {
                        r0i = -r2i;
                    }
                }
                r0i = STATE_IDLE;
            } else {
                r0i = DrawerLayout.this.getWidth();
                if (r8f >= 0.0f) {
                    if (r8f != 0.0f || r1f <= 0.5f) {
                        this.c.settleCapturedViewAt(r0i, r7_View.getTop());
                        DrawerLayout.this.invalidate();
                    }
                }
                r0i -= r2i;
            }
            this.c.settleCapturedViewAt(r0i, r7_View.getTop());
            DrawerLayout.this.invalidate();
        }

        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(this.d);
        }

        public void setDragger(ViewDragHelper r1_ViewDragHelper) {
            this.c = r1_ViewDragHelper;
        }

        public boolean tryCaptureView(View r3_View, int r4i) {
            return DrawerLayout.this.f(r3_View) && DrawerLayout.this.a(r3_View, this.b) && DrawerLayout.this.getDrawerLockMode(r3_View) == 0;
        }
    }

    static {
        int[] r0_intA = new int[1];
        r0_intA[0] = 16842931;
        a = r0_intA;
    }

    public DrawerLayout(Context r2_Context) {
        this(r2_Context, null);
    }

    public DrawerLayout(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, 0);
    }

    public DrawerLayout(Context r6_Context, AttributeSet r7_AttributeSet, int r8i) {
        super(r6_Context, r7_AttributeSet, r8i);
        this.c = -1728053248;
        this.e = new Paint();
        this.l = true;
        float r0f = getResources().getDisplayMetrics().density;
        this.b = (int) (64.0f * r0f + 0.5f);
        r0f *= 400.0f;
        this.h = new b(3);
        this.i = new b(5);
        this.f = ViewDragHelper.create(this, 1.0f, this.h);
        this.f.setEdgeTrackingEnabled(STATE_DRAGGING);
        this.f.setMinVelocity(r0f);
        this.h.setDragger(this.f);
        this.g = ViewDragHelper.create(this, 1.0f, this.i);
        this.g.setEdgeTrackingEnabled(STATE_SETTLING);
        this.g.setMinVelocity(r0f);
        this.i.setDragger(this.g);
        setFocusableInTouchMode(true);
        ViewCompat.setAccessibilityDelegate(this, new a());
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
    }

    static String b(int r2i) {
        if ((r2i & 3) == 3) {
            return "LEFT";
        }
        if ((r2i & 5) == 5) {
            return "RIGHT";
        }
        return Integer.toHexString(r2i);
    }

    private boolean d() {
        int r3i = getChildCount();
        int r2i = 0;
        while (r2i < r3i) {
            if (((LayoutParams) getChildAt(r2i).getLayoutParams()).b) {
                return true;
            }
            r2i++;
        }
        return false;
    }

    private boolean e() {
        return f() != null;
    }

    private View f() {
        int r2i = getChildCount();
        int r1i = 0;
        while (r1i < r2i) {
            View r0_View = getChildAt(r1i);
            if (f(r0_View) && isDrawerVisible(r0_View)) {
                return r0_View;
            }
            r1i++;
        }
        return null;
    }

    private static boolean g(View r3_View) {
        Drawable r1_Drawable = r3_View.getBackground();
        return r1_Drawable != null && r1_Drawable.getOpacity() == -1;
    }

    View a() {
        int r3i = getChildCount();
        int r2i = 0;
        while (r2i < r3i) {
            View r1_View = getChildAt(r2i);
            if (((LayoutParams) r1_View.getLayoutParams()).c) {
                return r1_View;
            }
            r2i++;
        }
        return null;
    }

    View a(int r6i) {
        int r2i = GravityCompat.getAbsoluteGravity(r6i, ViewCompat.getLayoutDirection(this)) & 7;
        int r3i = getChildCount();
        int r1i = 0;
        while (r1i < r3i) {
            View r0_View = getChildAt(r1i);
            if ((d(r0_View) & 7) == r2i) {
                return r0_View;
            }
            r1i++;
        }
        return null;
    }

    void a(int r5i, int r6i, View r7_View) {
        int r1i = STATE_DRAGGING;
        int r2i = this.f.getViewDragState();
        int r3i = this.g.getViewDragState();
        LayoutParams r0_LayoutParams;
        if (r2i == 1 || r3i == 1) {
            if (r7_View == null || r6i != 0) {
                if (r1i != this.j) {
                    this.j = r1i;
                    if (this.q != null) {
                        this.q.onDrawerStateChanged(r1i);
                    }
                }
            } else {
                r0_LayoutParams = (LayoutParams) r7_View.getLayoutParams();
                if (r0_LayoutParams.a != 0.0f) {
                    a(r7_View);
                    if (r1i != this.j) {
                    } else {
                        this.j = r1i;
                        if (this.q != null) {
                        } else {
                            this.q.onDrawerStateChanged(r1i);
                        }
                    }
                } else {
                    if (r0_LayoutParams.a != 1.0f) {
                        b(r7_View);
                    }
                    if (r1i != this.j) {
                        this.j = r1i;
                        if (this.q != null) {
                            this.q.onDrawerStateChanged(r1i);
                        }
                    }
                }
            }
        } else {
            r1i = (r2i == 2 || r3i == 2) ? 2 : 0;
            if (r7_View == null || r6i != 0) {
                if (r1i != this.j) {
                } else {
                    this.j = r1i;
                    if (this.q != null) {
                    } else {
                        this.q.onDrawerStateChanged(r1i);
                    }
                }
            } else {
                r0_LayoutParams = (LayoutParams) r7_View.getLayoutParams();
                if (r0_LayoutParams.a != 0.0f) {
                    if (r0_LayoutParams.a != 1.0f) {
                        if (r1i != this.j) {
                            this.j = r1i;
                            if (this.q != null) {
                                this.q.onDrawerStateChanged(r1i);
                            }
                        }
                    } else {
                        b(r7_View);
                        if (r1i != this.j) {
                        } else {
                            this.j = r1i;
                            if (this.q != null) {
                            } else {
                                this.q.onDrawerStateChanged(r1i);
                            }
                        }
                    }
                } else {
                    a(r7_View);
                    if (r1i != this.j) {
                        this.j = r1i;
                        if (this.q != null) {
                            this.q.onDrawerStateChanged(r1i);
                        }
                    }
                }
            }
        }
    }

    void a(View r3_View) {
        LayoutParams r0_LayoutParams = (LayoutParams) r3_View.getLayoutParams();
        if (r0_LayoutParams.c) {
            r0_LayoutParams.c = false;
            if (this.q != null) {
                this.q.onDrawerClosed(r3_View);
            }
            sendAccessibilityEvent(Base64.ORDERED);
        }
    }

    void a(View r2_View, float r3f) {
        if (this.q != null) {
            this.q.onDrawerSlide(r2_View, r3f);
        }
    }

    void a(boolean r10z) {
        int r4i = getChildCount();
        int r2i = 0;
        int r1i = 0;
        while (r2i < r4i) {
            View r5_View = getChildAt(r2i);
            LayoutParams r0_LayoutParams = (LayoutParams) r5_View.getLayoutParams();
            if (f(r5_View)) {
                if ((!r10z) || r0_LayoutParams.b) {
                    r1i = a(r5_View, (int)XListViewFooter.STATE_NOMORE) ? r1i | this.f.smoothSlideViewTo(r5_View, -r5_View.getWidth(), r5_View.getTop()) : r1i | this.g.smoothSlideViewTo(r5_View, getWidth(), r5_View.getTop());
                    r0_LayoutParams.b = false;
                }
            }
            r2i++;
        }
        this.h.removeCallbacks();
        this.i.removeCallbacks();
        if (r1i != 0) {
            invalidate();
        }
    }

    boolean a(View r2_View, int r3i) {
        return (d(r2_View) & r3i) == r3i;
    }

    void b() {
        int r7i = STATE_IDLE;
        if (!this.p) {
            long r0j = SystemClock.uptimeMillis();
            MotionEvent r0_MotionEvent = MotionEvent.obtain(r0j, r0j, XListViewFooter.STATE_NOMORE, 0.0f, 0.0f, STATE_IDLE);
            int r1i = getChildCount();
            while (r7i < r1i) {
                getChildAt(r7i).dispatchTouchEvent(r0_MotionEvent);
                r7i++;
            }
            r0_MotionEvent.recycle();
            this.p = true;
        }
    }

    void b(View r3_View) {
        LayoutParams r0_LayoutParams = (LayoutParams) r3_View.getLayoutParams();
        if (!r0_LayoutParams.c) {
            r0_LayoutParams.c = true;
            if (this.q != null) {
                this.q.onDrawerOpened(r3_View);
            }
            r3_View.sendAccessibilityEvent(Base64.ORDERED);
        }
    }

    void b(View r3_View, float r4f) {
        LayoutParams r0_LayoutParams = (LayoutParams) r3_View.getLayoutParams();
        if (r4f == r0_LayoutParams.a) {
        } else {
            r0_LayoutParams.a = r4f;
            a(r3_View, r4f);
        }
    }

    float c(View r2_View) {
        return ((LayoutParams) r2_View.getLayoutParams()).a;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        return r2_android_view_ViewGroup_LayoutParams instanceof LayoutParams && super.checkLayoutParams(r2_android_view_ViewGroup_LayoutParams);
    }

    public void closeDrawer(int r4i) {
        View r0_View = a(r4i);
        if (r0_View == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + b(r4i));
        } else {
            closeDrawer(r0_View);
        }
    }

    public void closeDrawer(View r4_View) {
        if (f(r4_View)) {
            if (this.l) {
                LayoutParams r0_LayoutParams = (LayoutParams) r4_View.getLayoutParams();
                r0_LayoutParams.a = 0.0f;
                r0_LayoutParams.c = false;
            } else if (a(r4_View, (int)XListViewFooter.STATE_NOMORE)) {
                this.f.smoothSlideViewTo(r4_View, -r4_View.getWidth(), r4_View.getTop());
            } else {
                this.g.smoothSlideViewTo(r4_View, getWidth(), r4_View.getTop());
            }
            invalidate();
        } else {
            throw new IllegalArgumentException("View " + r4_View + " is not a sliding drawer");
        }
    }

    public void closeDrawers() {
        a(false);
    }

    public void computeScroll() {
        int r3i = getChildCount();
        float r2f = 0.0f;
        int r1i = 0;
        while (r1i < r3i) {
            r2f = Math.max(r2f, ((LayoutParams) getChildAt(r1i).getLayoutParams()).a);
            r1i++;
        }
        this.d = r2f;
        if ((this.f.continueSettling(true) | this.g.continueSettling(true)) != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    int d(View r3_View) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams) r3_View.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    protected boolean drawChild(Canvas r10_Canvas, View r11_View, long r12j) {
        int r0i;
        int r4i = getHeight();
        boolean r5z = e(r11_View);
        int r1i = STATE_IDLE;
        int r2i = getWidth();
        int r6i = r10_Canvas.save();
        if (r5z) {
            int r7i = getChildCount();
            int r3i = 0;
            while (r3i < r7i) {
                View r0_View = getChildAt(r3i);
                if (r0_View != r11_View && r0_View.getVisibility() == 0 && g(r0_View) && f(r0_View)) {
                    if (r0_View.getHeight() < r4i) {
                        r0i = r2i;
                    } else if (a(r0_View, (int)XListViewFooter.STATE_NOMORE)) {
                        r0i = r0_View.getRight();
                        if (r0i > r1i) {
                            r1i = r0i;
                            r0i = r2i;
                        } else {
                            r0i = r1i;
                            r1i = r0i;
                            r0i = r2i;
                        }
                    } else {
                        r0i = r0_View.getLeft();
                        if (r0i >= r2i) {
                            r0i = r2i;
                        }
                    }
                } else {
                    r0i = r2i;
                }
                r3i++;
                r2i = r0i;
            }
            r10_Canvas.clipRect(r1i, STATE_IDLE, r2i, getHeight());
        }
        r0i = r2i;
        boolean r7z = super.drawChild(r10_Canvas, r11_View, r12j);
        r10_Canvas.restoreToCount(r6i);
        if (this.d <= 0.0f || (!r5z)) {
            float r2f;
            if (this.t == null || (!a(r11_View, (int)XListViewFooter.STATE_NOMORE))) {
                if (this.u == null || (!a(r11_View, (int)ShareUtils.SHARE_SMS))) {
                    return r7z;
                }
                r0i = this.u.getIntrinsicWidth();
                r1i = r11_View.getLeft();
                r2f = Math.max(0.0f, Math.min(((float) (getWidth() - r1i)) / ((float) this.g.getEdgeSize()), 1.0f));
                this.u.setBounds(r1i - r0i, r11_View.getTop(), r1i, r11_View.getBottom());
                this.u.setAlpha((int) (255.0f * r2f));
                this.u.draw(r10_Canvas);
            } else {
                r0i = this.t.getIntrinsicWidth();
                r1i = r11_View.getRight();
                r2f = Math.max(0.0f, Math.min(((float) r1i) / ((float) this.f.getEdgeSize()), 1.0f));
                this.t.setBounds(r1i, r11_View.getTop(), r0i + r1i, r11_View.getBottom());
                this.t.setAlpha((int) (255.0f * r2f));
                this.t.draw(r10_Canvas);
            }
        } else {
            this.e.setColor((((int) (((float) ((this.c & -16777216) >>> 24)) * this.d)) << 24) | (this.c & 16777215));
            r10_Canvas.drawRect((float) r1i, 0.0f, (float) r0i, (float) getHeight(), this.e);
        }
        return r7z;
    }

    boolean e(View r2_View) {
        return ((LayoutParams) r2_View.getLayoutParams()).gravity == 0;
    }

    boolean f(View r3_View) {
        return (GravityCompat.getAbsoluteGravity(((LayoutParams) r3_View.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(r3_View)) & 7) != 0;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet r3_AttributeSet) {
        return new LayoutParams(getContext(), r3_AttributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        if (r2_android_view_ViewGroup_LayoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) r2_android_view_ViewGroup_LayoutParams);
        }
        if (r2_android_view_ViewGroup_LayoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) r2_android_view_ViewGroup_LayoutParams);
        }
        return new LayoutParams(r2_android_view_ViewGroup_LayoutParams);
    }

    public int getDrawerLockMode(int r3i) {
        int r0i = GravityCompat.getAbsoluteGravity(r3i, ViewCompat.getLayoutDirection(this));
        if (r0i == 3) {
            return this.m;
        }
        if (r0i == 5) {
            return this.n;
        }
        return STATE_IDLE;
    }

    public int getDrawerLockMode(View r3_View) {
        int r0i = d(r3_View);
        if (r0i == 3) {
            return this.m;
        }
        if (r0i == 5) {
            return this.n;
        }
        return STATE_IDLE;
    }

    public boolean isDrawerOpen(int r2i) {
        View r0_View = a(r2i);
        return r0_View != null ? isDrawerOpen(r0_View) : false;
    }

    public boolean isDrawerOpen(View r4_View) {
        if (f(r4_View)) {
            return ((LayoutParams) r4_View.getLayoutParams()).c;
        }
        throw new IllegalArgumentException("View " + r4_View + " is not a drawer");
    }

    public boolean isDrawerVisible(int r2i) {
        View r0_View = a(r2i);
        return r0_View != null ? isDrawerVisible(r0_View) : false;
    }

    public boolean isDrawerVisible(View r4_View) {
        if (f(r4_View)) {
            return (((LayoutParams) r4_View.getLayoutParams()).a > 0.0f ? 1 : (((LayoutParams) r4_View.getLayoutParams()).a == 0.0f? 0 : -1)) > 0;
        } else {
            throw new IllegalArgumentException("View " + r4_View + " is not a drawer");
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.l = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.l = true;
    }

    public boolean onInterceptTouchEvent(MotionEvent r8_MotionEvent) {
        int r3i = this.f.shouldInterceptTouchEvent(r8_MotionEvent) | this.g.shouldInterceptTouchEvent(r8_MotionEvent);
        switch (MotionEventCompat.getActionMasked(r8_MotionEvent)) {
            case STATE_IDLE:
                float r0f = r8_MotionEvent.getX();
                float r4f = r8_MotionEvent.getY();
                this.r = r0f;
                this.s = r4f;
                if (this.d <= 0.0f || (!e(this.f.findTopChildUnder((int) r0f, (int) r4f)))) {
                    r0i = 0;
                    this.o = false;
                    this.p = false;
                    return r3i != 0 || r0i != 0 || d() || this.p;
                } else {
                    r0i = 1;
                    this.o = false;
                    this.p = false;
                    if (r3i != 0 || r0i != 0 || d() || this.p) {
                    }
                }
            case STATE_DRAGGING:
            case XListViewFooter.STATE_NOMORE:
                a(true);
                this.o = false;
                this.p = false;
                break;
            case STATE_SETTLING:
                if (this.f.checkTouchSlop(XListViewFooter.STATE_NOMORE)) {
                    this.h.removeCallbacks();
                    this.i.removeCallbacks();
                    r0i = 0;
                    if (r3i != 0 || r0i != 0 || d() || this.p) {
                    }
                }
        }
        r0i = 0;
        if (r3i != 0 || r0i != 0 || d() || this.p) {
        }
    }

    public boolean onKeyDown(int r2i, KeyEvent r3_KeyEvent) {
        if (r2i != 4 || !e()) {
            return super.onKeyDown(r2i, r3_KeyEvent);
        }
        KeyEventCompat.startTracking(r3_KeyEvent);
        return true;
    }

    public boolean onKeyUp(int r3i, KeyEvent r4_KeyEvent) {
        if (r3i != 4) {
            return super.onKeyUp(r3i, r4_KeyEvent);
        }
        View r0_View = f();
        if (r0_View == null || getDrawerLockMode(r0_View) != 0) {
            return r0_View == null;
        } else {
            closeDrawers();
            if (r0_View == null) {
            }
        }
    }

    protected void onLayout(boolean r15z, int r16i, int r17i, int r18i, int r19i) {
        this.k = true;
        int r6i = r18i - r16i;
        int r7i = getChildCount();
        int r5i = 0;
        while (r5i < r7i) {
            View r8_View = getChildAt(r5i);
            if (r8_View.getVisibility() == 8) {
                r5i++;
            } else {
                LayoutParams r0_LayoutParams = (LayoutParams) r8_View.getLayoutParams();
                if (e(r8_View)) {
                    r8_View.layout(r0_LayoutParams.leftMargin, r0_LayoutParams.topMargin, r0_LayoutParams.leftMargin + r8_View.getMeasuredWidth(), r0_LayoutParams.topMargin + r8_View.getMeasuredHeight());
                    r5i++;
                } else {
                    int r2i;
                    float r1f;
                    int r3i;
                    int r0i;
                    int r9i = r8_View.getMeasuredWidth();
                    int r10i = r8_View.getMeasuredHeight();
                    if (a(r8_View, (int)XListViewFooter.STATE_NOMORE)) {
                        r2i = ((int) (((float) r9i) * r0_LayoutParams.a)) + (-r9i);
                        r1f = ((float) (r9i + r2i)) / ((float) r9i);
                    } else {
                        r2i = r6i - ((int) (((float) r9i) * r0_LayoutParams.a));
                        r1f = ((float) (r6i - r2i)) / ((float) r9i);
                    }
                    r3i = (r1f > r0_LayoutParams.a ? 1 : (r1f == r0_LayoutParams.a? 0 : -1)) != 0 ? STATE_DRAGGING : STATE_IDLE;
                    int r4i;
                    switch ((r0_LayoutParams.gravity & 112)) {
                        case Base64.URL_SAFE:
                            int r11i = r19i - r17i;
                            r4i = (r11i - r10i) / 2;
                            if (r4i < r0_LayoutParams.topMargin) {
                                r4i = r0_LayoutParams.topMargin;
                            } else if (r4i + r10i > r11i - r0_LayoutParams.bottomMargin) {
                                r4i = r11i - r0_LayoutParams.bottomMargin - r10i;
                            }
                            r8_View.layout(r2i, r4i, r9i + r2i, r10i + r4i);
                            if (r3i != 0) {
                                b(r8_View, r1f);
                            }
                            r0i = (r0_LayoutParams.a > 0.0f ? 1 : (r0_LayoutParams.a == 0.0f? 0 : -1)) <= 0 ? STATE_IDLE : XListViewFooter.STATE_NODATA;
                            if (r8_View.getVisibility() == r0i) {
                                r8_View.setVisibility(r0i);
                            }
                            r5i++;
                            break;
                        case 80:
                            r4i = r19i - r17i;
                            r8_View.layout(r2i, r4i - r0_LayoutParams.bottomMargin - r8_View.getMeasuredHeight(), r9i + r2i, r4i - r0_LayoutParams.bottomMargin);
                            if (r3i != 0) {
                                if ((r0_LayoutParams.a > 0.0f ? 1 : (r0_LayoutParams.a == 0.0f? 0 : -1)) <= 0) {
                                }
                                if (r8_View.getVisibility() == r0i) {
                                    r5i++;
                                } else {
                                    r8_View.setVisibility(r0i);
                                    r5i++;
                                }
                            } else {
                                b(r8_View, r1f);
                                if ((r0_LayoutParams.a > 0.0f ? 1 : (r0_LayoutParams.a == 0.0f? 0 : -1)) <= 0) {
                                }
                                if (r8_View.getVisibility() == r0i) {
                                    r8_View.setVisibility(r0i);
                                }
                                r5i++;
                            }
                            break;
                    }
                    r8_View.layout(r2i, r0_LayoutParams.topMargin, r9i + r2i, r10i + r0_LayoutParams.topMargin);
                    if (r3i != 0) {
                        b(r8_View, r1f);
                    }
                    if ((r0_LayoutParams.a > 0.0f ? 1 : (r0_LayoutParams.a == 0.0f? 0 : -1)) <= 0) {
                    }
                    if (r8_View.getVisibility() == r0i) {
                        r5i++;
                    } else {
                        r8_View.setVisibility(r0i);
                        r5i++;
                    }
                }
            }
        }
        this.k = false;
        this.l = false;
    }

    protected void onMeasure(int r12i, int r13i) {
        int r1i = 300;
        int r3i = MeasureSpec.getMode(r12i);
        int r5i = MeasureSpec.getMode(r13i);
        int r2i = MeasureSpec.getSize(r12i);
        int r0i = MeasureSpec.getSize(r13i);
        if (r3i == 1073741824 && r5i == 1073741824) {
            r1i = r0i;
        } else if (isInEditMode()) {
            if (r3i == -2147483648) {
                if (r5i != -2147483648) {
                    r1i = r0i;
                } else if (r5i == 0) {
                    r1i = r0i;
                }
            } else {
                if (r3i == 0) {
                    r2i = 300;
                }
                if (r5i != -2147483648) {
                    if (r5i == 0) {
                        setMeasuredDimension(r2i, r1i);
                        r5i = getChildCount();
                        r3i = 0;
                    }
                    r1i = r0i;
                } else {
                    r1i = r0i;
                }
            }
        } else {
            throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
        }
        setMeasuredDimension(r2i, r1i);
        r5i = getChildCount();
        r3i = 0;
        while (r3i < r5i) {
            View r6_View = getChildAt(r3i);
            if (r6_View.getVisibility() == 8) {
                r3i++;
            } else {
                LayoutParams r0_LayoutParams = (LayoutParams) r6_View.getLayoutParams();
                if (e(r6_View)) {
                    r6_View.measure(MeasureSpec.makeMeasureSpec(r2i - r0_LayoutParams.leftMargin - r0_LayoutParams.rightMargin, 1073741824), MeasureSpec.makeMeasureSpec(r1i - r0_LayoutParams.topMargin - r0_LayoutParams.bottomMargin, 1073741824));
                    r3i++;
                } else if (f(r6_View)) {
                    int r7i = d(r6_View) & 7;
                    if ((0 & r7i) != 0) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + b(r7i) + " but this " + "DrawerLayout" + " already has a " + "drawer view along that edge");
                    } else {
                        r6_View.measure(getChildMeasureSpec(r12i, this.b + r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin, r0_LayoutParams.width), getChildMeasureSpec(r13i, r0_LayoutParams.topMargin + r0_LayoutParams.bottomMargin, r0_LayoutParams.height));
                        r3i++;
                    }
                } else {
                    throw new IllegalStateException("Child " + r6_View + " at index " + r3i + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
                }
            }
        }
    }

    protected void onRestoreInstanceState(Parcelable r3_Parcelable) {
        SavedState r3_SavedState = (SavedState) r3_Parcelable;
        super.onRestoreInstanceState(r3_SavedState.getSuperState());
        if (r3_SavedState.a != 0) {
            View r0_View = a(r3_SavedState.a);
            if (r0_View != null) {
                openDrawer(r0_View);
            }
        }
        setDrawerLockMode(r3_SavedState.b, (int)XListViewFooter.STATE_NOMORE);
        setDrawerLockMode(r3_SavedState.c, (int)ShareUtils.SHARE_SMS);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable r2_Parcelable = new SavedState(super.onSaveInstanceState());
        int r3i = getChildCount();
        int r1i = 0;
        while (r1i < r3i) {
            View r0_View = getChildAt(r1i);
            if (f(r0_View)) {
                LayoutParams r0_LayoutParams = (LayoutParams) r0_View.getLayoutParams();
                if (r0_LayoutParams.c) {
                    r2_Parcelable.a = r0_LayoutParams.gravity;
                    break;
                }
                r1i++;
            } else {
                r1i++;
            }
        }
        r2_Parcelable.b = this.m;
        r2_Parcelable.c = this.n;
        return r2_Parcelable;
    }

    public boolean onTouchEvent(MotionEvent r8_MotionEvent) {
        this.f.processTouchEvent(r8_MotionEvent);
        this.g.processTouchEvent(r8_MotionEvent);
        float r0f;
        float r3f;
        switch ((r8_MotionEvent.getAction() & 255)) {
            case STATE_IDLE:
                r0f = r8_MotionEvent.getX();
                r3f = r8_MotionEvent.getY();
                this.r = r0f;
                this.s = r3f;
                this.o = false;
                this.p = false;
                break;
            case STATE_DRAGGING:
                boolean r0z;
                r0f = r8_MotionEvent.getX();
                r3f = r8_MotionEvent.getY();
                View r4_View = this.f.findTopChildUnder((int) r0f, (int) r3f);
                if (r4_View == null || (!e(r4_View))) {
                    r0z = true;
                } else {
                    r0f -= this.r;
                    r3f -= this.s;
                    int r4i = this.f.getTouchSlop();
                    if (r0f * r0f + r3f * r3f < ((float) (r4i * r4i))) {
                        View r0_View = a();
                        if (r0_View != null) {
                            r0z = getDrawerLockMode(r0_View) == 2;
                        }
                    }
                    r0z = true;
                }
                a(r0z);
                this.o = false;
                break;
            case XListViewFooter.STATE_NOMORE:
                a(true);
                this.o = false;
                this.p = false;
                break;
        }
        return true;
    }

    public void openDrawer(int r4i) {
        View r0_View = a(r4i);
        if (r0_View == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + b(r4i));
        } else {
            openDrawer(r0_View);
        }
    }

    public void openDrawer(View r4_View) {
        if (f(r4_View)) {
            if (this.l) {
                LayoutParams r0_LayoutParams = (LayoutParams) r4_View.getLayoutParams();
                r0_LayoutParams.a = 1.0f;
                r0_LayoutParams.c = true;
            } else if (a(r4_View, (int)XListViewFooter.STATE_NOMORE)) {
                this.f.smoothSlideViewTo(r4_View, STATE_IDLE, r4_View.getTop());
            } else {
                this.g.smoothSlideViewTo(r4_View, getWidth() - r4_View.getWidth(), r4_View.getTop());
            }
            invalidate();
        } else {
            throw new IllegalArgumentException("View " + r4_View + " is not a sliding drawer");
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean r2z) {
        super.requestDisallowInterceptTouchEvent(r2z);
        this.o = r2z;
        if (r2z) {
            a(true);
        }
    }

    public void requestLayout() {
        if (!this.k) {
            super.requestLayout();
        }
    }

    public void setDrawerListener(DrawerListener r1_DrawerListener) {
        this.q = r1_DrawerListener;
    }

    public void setDrawerLockMode(int r2i) {
        setDrawerLockMode(r2i, (int)XListViewFooter.STATE_NOMORE);
        setDrawerLockMode(r2i, (int)ShareUtils.SHARE_SMS);
    }

    public void setDrawerLockMode(int r4i, int r5i) {
        int r1i = GravityCompat.getAbsoluteGravity(r5i, ViewCompat.getLayoutDirection(this));
        if (r1i == 3) {
            this.m = r4i;
        } else if (r1i == 5) {
            this.n = r4i;
        }
        if (r4i != 0) {
            (r1i == 3 ? this.f : this.g).cancel();
        }
        View r0_View;
        switch (r4i) {
            case STATE_DRAGGING:
                r0_View = a(r1i);
                if (r0_View != null) {
                    closeDrawer(r0_View);
                }
                break;
            case STATE_SETTLING:
                r0_View = a(r1i);
                if (r0_View != null) {
                    openDrawer(r0_View);
                }
                break;
        }
    }

    public void setDrawerLockMode(int r4i, View r5_View) {
        if (f(r5_View)) {
            setDrawerLockMode(r4i, ((LayoutParams) r5_View.getLayoutParams()).gravity);
        } else {
            throw new IllegalArgumentException("View " + r5_View + " is not a " + "drawer with appropriate layout_gravity");
        }
    }

    public void setDrawerShadow(int r2i, int r3i) {
        setDrawerShadow(getResources().getDrawable(r2i), r3i);
    }

    public void setDrawerShadow(Drawable r4_Drawable, int r5i) {
        int r0i = GravityCompat.getAbsoluteGravity(r5i, ViewCompat.getLayoutDirection(this));
        if ((r0i & 3) == 3) {
            this.t = r4_Drawable;
            invalidate();
        }
        if ((r0i & 5) == 5) {
            this.u = r4_Drawable;
            invalidate();
        }
    }

    public void setScrimColor(int r1i) {
        this.c = r1i;
        invalidate();
    }
}