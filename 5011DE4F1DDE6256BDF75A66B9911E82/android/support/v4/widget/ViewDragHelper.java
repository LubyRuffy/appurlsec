package android.support.v4.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.baidu.location.LocationClientOption;
import java.util.Arrays;
import qsbk.app.share.ShareUtils;

public class ViewDragHelper {
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final Interpolator v;
    private int a;
    private int b;
    private int c;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private ScrollerCompat q;
    private final Callback r;
    private View s;
    private boolean t;
    private final ViewGroup u;
    private final Runnable w;

    public static abstract class Callback {
        public int clampViewPositionHorizontal(View r2_View, int r3i, int r4i) {
            return STATE_IDLE;
        }

        public int clampViewPositionVertical(View r2_View, int r3i, int r4i) {
            return STATE_IDLE;
        }

        public int getOrderedChildIndex(int r1i) {
            return r1i;
        }

        public int getViewHorizontalDragRange(View r2_View) {
            return STATE_IDLE;
        }

        public int getViewVerticalDragRange(View r2_View) {
            return STATE_IDLE;
        }

        public void onEdgeDragStarted(int r1i, int r2i) {
        }

        public boolean onEdgeLock(int r2i) {
            return false;
        }

        public void onEdgeTouched(int r1i, int r2i) {
        }

        public void onViewCaptured(View r1_View, int r2i) {
        }

        public void onViewDragStateChanged(int r1i) {
        }

        public void onViewPositionChanged(View r1_View, int r2i, int r3i, int r4i, int r5i) {
        }

        public void onViewReleased(View r1_View, float r2f, float r3f) {
        }

        public abstract boolean tryCaptureView(View r1_View, int r2i);
    }

    static {
        v = new q();
    }

    private ViewDragHelper(Context r4_Context, ViewGroup r5_ViewGroup, Callback r6_Callback) {
        this.c = -1;
        this.w = new r(this);
        if (r5_ViewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (r6_Callback == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.u = r5_ViewGroup;
            this.r = r6_Callback;
            ViewConfiguration r0_ViewConfiguration = ViewConfiguration.get(r4_Context);
            this.o = (int) (r4_Context.getResources().getDisplayMetrics().density * 20.0f + 0.5f);
            this.b = r0_ViewConfiguration.getScaledTouchSlop();
            this.m = (float) r0_ViewConfiguration.getScaledMaximumFlingVelocity();
            this.n = (float) r0_ViewConfiguration.getScaledMinimumFlingVelocity();
            this.q = ScrollerCompat.create(r4_Context, v);
        }
    }

    private float a(float r5f) {
        return (float) Math.sin((double) ((float) (((double) (r5f - 0.5f)) * 0.4712389167638204d)));
    }

    private float a(float r4f, float r5f, float r6f) {
        float r0f = 0.0f;
        float r1f = Math.abs(r4f);
        if (r1f < r5f) {
            return 0.0f;
        }
        if (r1f <= r6f) {
            return r4f;
        }
        if (r4f <= r0f) {
            return -r6f;
        }
        return r6f;
    }

    private int a(int r4i, int r5i) {
        int r0i = STATE_IDLE;
        if (r4i < this.u.getLeft() + this.o) {
            r0i = STATE_DRAGGING;
        }
        if (r5i < this.u.getTop() + this.o) {
            r0i |= 4;
        }
        if (r4i > this.u.getRight() - this.o) {
            r0i |= 2;
        }
        return r5i > this.u.getBottom() - this.o ? r0i | 8 : r0i;
    }

    private int a(int r5i, int r6i, int r7i) {
        if (r5i == 0) {
            return STATE_IDLE;
        }
        int r0i = this.u.getWidth();
        int r1i = r0i / 2;
        float r0f = a(Math.min(1.0f, ((float) Math.abs(r5i)) / ((float) r0i))) * ((float) r1i) + ((float) r1i);
        r1i = Math.abs(r6i);
        return Math.min(r1i > 0 ? Math.round(Math.abs(r0f / ((float) r1i)) * 1000.0f) * 4 : (int) (((((float) Math.abs(r5i)) / ((float) r7i)) + 1.0f) * 256.0f), 600);
    }

    private int a(View r9_View, int r10i, int r11i, int r12i, int r13i) {
        int r2i = b(r12i, (int) this.n, (int) this.m);
        int r3i = b(r13i, (int) this.n, (int) this.m);
        int r0i = Math.abs(r10i);
        int r4i = Math.abs(r11i);
        int r1i = Math.abs(r2i);
        int r5i = Math.abs(r3i);
        int r6i = r1i + r5i;
        int r7i = r0i + r4i;
        return (int) ((r3i != 0 ? ((float) r5i) / ((float) r6i) : ((float) r4i) / ((float) r7i)) * ((float) a(r11i, r3i, this.r.getViewVerticalDragRange(r9_View))) + (r2i != 0 ? ((float) r1i) / ((float) r6i) : ((float) r0i) / ((float) r7i)) * ((float) a(r10i, r2i, this.r.getViewHorizontalDragRange(r9_View))));
    }

    private void a() {
        if (this.d == null) {
        } else {
            Arrays.fill(this.d, 0.0f);
            Arrays.fill(this.e, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, STATE_IDLE);
            Arrays.fill(this.i, STATE_IDLE);
            Arrays.fill(this.j, STATE_IDLE);
            this.k = 0;
        }
    }

    private void a(float r5f, float r6f) {
        this.t = true;
        this.r.onViewReleased(this.s, r5f, r6f);
        this.t = false;
        if (this.a == 1) {
            a((int)STATE_IDLE);
        }
    }

    private void a(float r4f, float r5f, int r6i) {
        c(r6i);
        float[] r0_floatA = this.d;
        this.f[r6i] = r4f;
        r0_floatA[r6i] = r4f;
        r0_floatA = this.e;
        this.g[r6i] = r5f;
        r0_floatA[r6i] = r5f;
        this.h[r6i] = a((int) r4f, (int) r5f);
        this.k |= 1 << r6i;
    }

    private void a(MotionEvent r7_MotionEvent) {
        int r1i = MotionEventCompat.getPointerCount(r7_MotionEvent);
        int r0i = STATE_IDLE;
        while (r0i < r1i) {
            int r2i = MotionEventCompat.getPointerId(r7_MotionEvent, r0i);
            float r3f = MotionEventCompat.getX(r7_MotionEvent, r0i);
            float r4f = MotionEventCompat.getY(r7_MotionEvent, r0i);
            this.f[r2i] = r3f;
            this.g[r2i] = r4f;
            r0i++;
        }
    }

    private boolean a(float r5f, float r6f, int r7i, int r8i) {
        float r1f = Math.abs(r5f);
        float r2f = Math.abs(r6f);
        if ((this.h[r7i] & r8i) != r8i || (this.p & r8i) == 0 || (this.j[r7i] & r8i) == r8i || (this.i[r7i] & r8i) == r8i) {
            return false;
        }
        if (r1f <= ((float) this.b) && r2f <= ((float) this.b)) {
            return false;
        }
        if (r1f >= r2f * 0.5f || (!this.r.onEdgeLock(r8i))) {
            return (this.i[r7i] & r8i) == 0 && r1f > ((float) this.b);
        } else {
            int[] r1_intA = this.j;
            r1_intA[r7i] = r1_intA[r7i] | r8i;
            return false;
        }
    }

    private boolean a(int r11i, int r12i, int r13i, int r14i) {
        int r7i = this.s.getLeft();
        int r6i = this.s.getTop();
        int r2i = r11i - r7i;
        int r3i = r12i - r6i;
        if (r2i == 0 && r3i == 0) {
            this.q.abortAnimation();
            a((int)STATE_IDLE);
            return false;
        } else {
            this.q.startScroll(r7i, r6i, r2i, r3i, a(this.s, r2i, r3i, r13i, r14i));
            a((int)STATE_SETTLING);
            return true;
        }
    }

    private boolean a(View r6_View, float r7f, float r8f) {
        if (r6_View == null) {
            return false;
        }
        int r0i;
        int r3i;
        r0i = this.r.getViewHorizontalDragRange(r6_View) > 0 ? 1 : 0;
        r3i = this.r.getViewVerticalDragRange(r6_View) > 0 ? 1 : 0;
        if (r0i == 0 || r3i == 0) {
            if (r0i != 0) {
                return (Math.abs(r7f) > ((float) this.b) ? 1 : (Math.abs(r7f) == ((float) this.b)? 0 : -1)) > 0;
            } else {
                if (r3i == 0) {
                    return false;
                }
                if (Math.abs(r8f) <= ((float) this.b)) {
                    return false;
                }
                return true;
            }
        } else {
            if (r7f * r7f + r8f * r8f <= ((float) (this.b * this.b))) {
                return false;
            }
            return true;
        }
    }

    private int b(int r2i, int r3i, int r4i) {
        int r0i = Math.abs(r2i);
        if (r0i < r3i) {
            return STATE_IDLE;
        }
        if (r0i <= r4i) {
            return r2i;
        }
        if (r2i <= 0) {
            return -r4i;
        }
        return r4i;
    }

    private void b() {
        this.l.computeCurrentVelocity(LocationClientOption.MIN_SCAN_SPAN, this.m);
        a(a(VelocityTrackerCompat.getXVelocity(this.l, this.c), this.n, this.m), a(VelocityTrackerCompat.getYVelocity(this.l, this.c), this.n, this.m));
    }

    private void b(float r4f, float r5f, int r6i) {
        int r0i = STATE_DRAGGING;
        int[] r1_intA;
        if (a(r4f, r5f, r6i, (int)STATE_DRAGGING)) {
            if (!a(r5f, r4f, r6i, (int)EDGE_TOP)) {
                r0i |= 4;
            }
            if (a(r4f, r5f, r6i, (int)STATE_SETTLING)) {
                r0i |= 2;
            }
            if (!a(r5f, r4f, r6i, (int)EDGE_BOTTOM)) {
                r0i |= 8;
            }
            if (r0i == 0) {
                r1_intA = this.i;
                r1_intA[r6i] = r1_intA[r6i] | r0i;
                this.r.onEdgeDragStarted(r0i, r6i);
            }
        } else {
            r0i = 0;
            if (a(r5f, r4f, r6i, (int)EDGE_TOP)) {
                if (a(r4f, r5f, r6i, (int)STATE_SETTLING)) {
                    if (a(r5f, r4f, r6i, (int)EDGE_BOTTOM)) {
                        if (r0i == 0) {
                        } else {
                            r1_intA = this.i;
                            r1_intA[r6i] = r1_intA[r6i] | r0i;
                            this.r.onEdgeDragStarted(r0i, r6i);
                        }
                    } else {
                        r0i |= 8;
                        if (r0i == 0) {
                            r1_intA = this.i;
                            r1_intA[r6i] = r1_intA[r6i] | r0i;
                            this.r.onEdgeDragStarted(r0i, r6i);
                        }
                    }
                } else {
                    r0i |= 2;
                    if (a(r5f, r4f, r6i, (int)EDGE_BOTTOM)) {
                        r0i |= 8;
                    }
                    if (r0i == 0) {
                    } else {
                        r1_intA = this.i;
                        r1_intA[r6i] = r1_intA[r6i] | r0i;
                        this.r.onEdgeDragStarted(r0i, r6i);
                    }
                }
            } else {
                r0i |= 4;
                if (a(r4f, r5f, r6i, (int)STATE_SETTLING)) {
                    r0i |= 2;
                }
                if (a(r5f, r4f, r6i, (int)EDGE_BOTTOM)) {
                    if (r0i == 0) {
                        r1_intA = this.i;
                        r1_intA[r6i] = r1_intA[r6i] | r0i;
                        this.r.onEdgeDragStarted(r0i, r6i);
                    }
                } else {
                    r0i |= 8;
                    if (r0i == 0) {
                    } else {
                        r1_intA = this.i;
                        r1_intA[r6i] = r1_intA[r6i] | r0i;
                        this.r.onEdgeDragStarted(r0i, r6i);
                    }
                }
            }
        }
    }

    private void b(int r4i) {
        float r1f = 0.0f;
        if (this.d == null) {
        } else {
            this.d[r4i] = r1f;
            this.e[r4i] = r1f;
            this.f[r4i] = r1f;
            this.g[r4i] = r1f;
            this.h[r4i] = 0;
            this.i[r4i] = 0;
            this.j[r4i] = 0;
            this.k &= (1 << r4i) ^ -1;
        }
    }

    private void b(int r7i, int r8i, int r9i, int r10i) {
        int r2i;
        int r3i;
        int r0i = this.s.getLeft();
        int r1i = this.s.getTop();
        if (r9i != 0) {
            r2i = this.r.clampViewPositionHorizontal(this.s, r7i, r9i);
            this.s.offsetLeftAndRight(r2i - r0i);
        } else {
            r2i = r7i;
        }
        if (r10i != 0) {
            r3i = this.r.clampViewPositionVertical(this.s, r8i, r10i);
            this.s.offsetTopAndBottom(r3i - r1i);
        } else {
            r3i = r8i;
        }
        if (!(r9i == 0 && r10i == 0)) {
            this.r.onViewPositionChanged(this.s, r2i, r3i, r2i - r0i, r3i - r1i);
        }
    }

    private void c(int r11i) {
        if (this.d == null || this.d.length <= r11i) {
            Object r0_Object = new Object[(r11i + 1)];
            Object r1_Object = new Object[(r11i + 1)];
            Object r2_Object = new Object[(r11i + 1)];
            Object r3_Object = new Object[(r11i + 1)];
            Object r4_Object = new Object[(r11i + 1)];
            Object r5_Object = new Object[(r11i + 1)];
            Object r6_Object = new Object[(r11i + 1)];
            if (this.d != null) {
                System.arraycopy(this.d, STATE_IDLE, r0_Object, STATE_IDLE, this.d.length);
                System.arraycopy(this.e, STATE_IDLE, r1_Object, STATE_IDLE, this.e.length);
                System.arraycopy(this.f, STATE_IDLE, r2_Object, STATE_IDLE, this.f.length);
                System.arraycopy(this.g, STATE_IDLE, r3_Object, STATE_IDLE, this.g.length);
                System.arraycopy(this.h, STATE_IDLE, r4_Object, STATE_IDLE, this.h.length);
                System.arraycopy(this.i, STATE_IDLE, r5_Object, STATE_IDLE, this.i.length);
                System.arraycopy(this.j, STATE_IDLE, r6_Object, STATE_IDLE, this.j.length);
            }
            this.d = r0_Object;
            this.e = r1_Object;
            this.f = r2_Object;
            this.g = r3_Object;
            this.h = r4_Object;
            this.i = r5_Object;
            this.j = r6_Object;
        }
    }

    public static ViewDragHelper create(ViewGroup r3_ViewGroup, float r4f, Callback r5_Callback) {
        ViewDragHelper r0_ViewDragHelper = create(r3_ViewGroup, r5_Callback);
        r0_ViewDragHelper.b = (int) (((float) r0_ViewDragHelper.b) * (1.0f / r4f));
        return r0_ViewDragHelper;
    }

    public static ViewDragHelper create(ViewGroup r2_ViewGroup, Callback r3_Callback) {
        return new ViewDragHelper(r2_ViewGroup.getContext(), r2_ViewGroup, r3_Callback);
    }

    void a(int r2i) {
        if (this.a != r2i) {
            this.a = r2i;
            this.r.onViewDragStateChanged(r2i);
            if (r2i == 0) {
                this.s = null;
            }
        }
    }

    boolean a(View r3_View, int r4i) {
        if (r3_View == this.s && this.c == r4i) {
            return true;
        }
        if (r3_View == null || (!this.r.tryCaptureView(r3_View, r4i))) {
            return false;
        }
        this.c = r4i;
        captureChildView(r3_View, r4i);
        return true;
    }

    public void abort() {
        cancel();
        if (this.a == 2) {
            int r4i = this.q.getCurrX();
            int r5i = this.q.getCurrY();
            this.q.abortAnimation();
            int r2i = this.q.getCurrX();
            int r3i = this.q.getCurrY();
            this.r.onViewPositionChanged(this.s, r2i, r3i, r2i - r4i, r3i - r5i);
        }
        a((int)STATE_IDLE);
    }

    public void cancel() {
        this.c = -1;
        a();
        if (this.l != null) {
            this.l.recycle();
            this.l = null;
        }
    }

    public void captureChildView(View r4_View, int r5i) {
        if (r4_View.getParent() != this.u) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
        } else {
            this.s = r4_View;
            this.c = r5i;
            this.r.onViewCaptured(r4_View, r5i);
            a((int)STATE_DRAGGING);
        }
    }

    public boolean checkTouchSlop(int r5i) {
        int r2i = this.d.length;
        int r1i = 0;
        while (r1i < r2i) {
            if (checkTouchSlop(r5i, r1i)) {
                return true;
            }
            r1i++;
        }
        return false;
    }

    public boolean checkTouchSlop(int r8i, int r9i) {
        if (!isPointerDown(r9i)) {
            return false;
        }
        int r3i;
        int r0i;
        r3i = (r8i & 1) == 1 ? 1 : 0;
        r0i = (r8i & 2) == 2 ? 1 : 0;
        float r4f = this.f[r9i] - this.d[r9i];
        float r5f = this.g[r9i] - this.e[r9i];
        if (r3i == 0 || r0i == 0) {
            if (r3i != 0) {
                return (Math.abs(r4f) > ((float) this.b) ? 1 : (Math.abs(r4f) == ((float) this.b)? 0 : -1)) > 0;
            } else {
                if (r0i == 0) {
                    return false;
                }
                if (Math.abs(r5f) <= ((float) this.b)) {
                    return false;
                }
                return true;
            }
        } else {
            if (r4f * r4f + r5f * r5f <= ((float) (this.b * this.b))) {
                return false;
            }
            return true;
        }
    }

    public boolean continueSettling(boolean r10z) {
        if (this.a == 2) {
            boolean r7z = this.q.computeScrollOffset();
            int r2i = this.q.getCurrX();
            int r3i = this.q.getCurrY();
            int r4i = r2i - this.s.getLeft();
            int r5i = r3i - this.s.getTop();
            if (r4i != 0) {
                this.s.offsetLeftAndRight(r4i);
            }
            if (r5i != 0) {
                this.s.offsetTopAndBottom(r5i);
            }
            boolean r0z;
            if (r4i == 0 && r5i == 0) {
                if (r7z && r2i == this.q.getFinalX() && r3i == this.q.getFinalY()) {
                    this.q.abortAnimation();
                    r0z = this.q.isFinished();
                } else {
                    r0z = r7z;
                }
                if (r0z) {
                } else if (r10z) {
                    this.u.post(this.w);
                } else {
                    a((int)STATE_IDLE);
                }
            } else {
                this.r.onViewPositionChanged(this.s, r2i, r3i, r4i, r5i);
                if (r7z || r2i == this.q.getFinalX() || r3i == this.q.getFinalY()) {
                    r0z = r7z;
                } else {
                    this.q.abortAnimation();
                    r0z = this.q.isFinished();
                }
                if (r0z) {
                    if (r10z) {
                        this.u.post(this.w);
                    } else {
                        a((int)STATE_IDLE);
                    }
                }
            }
        }
        return this.a == 2;
    }

    public View findTopChildUnder(int r4i, int r5i) {
        int r1i = this.u.getChildCount() - 1;
        while (r1i >= 0) {
            View r0_View = this.u.getChildAt(this.r.getOrderedChildIndex(r1i));
            if (r4i >= r0_View.getLeft() && r4i < r0_View.getRight() && r5i >= r0_View.getTop() && r5i < r0_View.getBottom()) {
                return r0_View;
            }
            r1i--;
        }
        return null;
    }

    public void flingCapturedView(int r10i, int r11i, int r12i, int r13i) {
        if (this.t) {
            this.q.fling(this.s.getLeft(), this.s.getTop(), (int) VelocityTrackerCompat.getXVelocity(this.l, this.c), (int) VelocityTrackerCompat.getYVelocity(this.l, this.c), r10i, r12i, r11i, r13i);
            a((int)STATE_SETTLING);
        } else {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
    }

    public int getActivePointerId() {
        return this.c;
    }

    public View getCapturedView() {
        return this.s;
    }

    public int getEdgeSize() {
        return this.o;
    }

    public float getMinVelocity() {
        return this.n;
    }

    public int getTouchSlop() {
        return this.b;
    }

    public int getViewDragState() {
        return this.a;
    }

    public boolean isCapturedViewUnder(int r2i, int r3i) {
        return isViewUnder(this.s, r2i, r3i);
    }

    public boolean isEdgeTouched(int r5i) {
        int r2i = this.h.length;
        int r1i = 0;
        while (r1i < r2i) {
            if (isEdgeTouched(r5i, r1i)) {
                return true;
            }
            r1i++;
        }
        return false;
    }

    public boolean isEdgeTouched(int r2i, int r3i) {
        return isPointerDown(r3i) && (this.h[r3i] & r2i) != 0;
    }

    public boolean isPointerDown(int r4i) {
        return (this.k & (1 << r4i)) != 0;
    }

    public boolean isViewUnder(View r3_View, int r4i, int r5i) {
        return r3_View != null && r4i >= r3_View.getLeft() && r4i < r3_View.getRight() && r5i >= r3_View.getTop() && r5i < r3_View.getBottom();
    }

    public void processTouchEvent(MotionEvent r10_MotionEvent) {
        int r0i = STATE_IDLE;
        int r2i = MotionEventCompat.getActionMasked(r10_MotionEvent);
        int r3i = MotionEventCompat.getActionIndex(r10_MotionEvent);
        if (r2i == 0) {
            cancel();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(r10_MotionEvent);
        float r1f;
        float r2f;
        View r3_View;
        int r1i;
        switch (r2i) {
            case STATE_IDLE:
                r1f = r10_MotionEvent.getX();
                r2f = r10_MotionEvent.getY();
                r0i = MotionEventCompat.getPointerId(r10_MotionEvent, r0i);
                r3_View = findTopChildUnder((int) r1f, (int) r2f);
                a(r1f, r2f, r0i);
                a(r3_View, r0i);
                r1i = this.h[r0i];
                if ((this.p & r1i) != 0) {
                    this.r.onEdgeTouched(r1i & this.p, r0i);
                }
                break;
            case STATE_DRAGGING:
                if (this.a == 1) {
                    b();
                }
                cancel();
                break;
            case STATE_SETTLING:
                if (this.a == 1) {
                    r0i = MotionEventCompat.findPointerIndex(r10_MotionEvent, this.c);
                    r1f = MotionEventCompat.getX(r10_MotionEvent, r0i);
                    r1i = (int) (r1f - this.f[this.c]);
                    r0i = (int) (MotionEventCompat.getY(r10_MotionEvent, r0i) - this.g[this.c]);
                    b(this.s.getLeft() + r1i, this.s.getTop() + r0i, r1i, r0i);
                    a(r10_MotionEvent);
                } else {
                    r1i = MotionEventCompat.getPointerCount(r10_MotionEvent);
                    while (r0i < r1i) {
                        r2i = MotionEventCompat.getPointerId(r10_MotionEvent, r0i);
                        float r3f = MotionEventCompat.getX(r10_MotionEvent, r0i);
                        float r4f = MotionEventCompat.getY(r10_MotionEvent, r0i);
                        float r5f = r3f - this.d[r2i];
                        float r6f = r4f - this.e[r2i];
                        b(r5f, r6f, r2i);
                        if (this.a == 1) {
                            break;
                        } else {
                            r3_View = findTopChildUnder((int) r3f, (int) r4f);
                            if (a(r3_View, r5f, r6f) && a(r3_View, r2i)) {
                                break;
                            } else {
                                r0i++;
                            }
                        }
                    }
                    a(r10_MotionEvent);
                }
                break;
            case DIRECTION_ALL:
                if (this.a == 1) {
                    a(0.0f, 0.0f);
                }
                cancel();
                break;
            case ShareUtils.SHARE_SMS:
                r0i = MotionEventCompat.getPointerId(r10_MotionEvent, r3i);
                r1f = MotionEventCompat.getX(r10_MotionEvent, r3i);
                r2f = MotionEventCompat.getY(r10_MotionEvent, r3i);
                a(r1f, r2f, r0i);
                if (this.a == 0) {
                    a(findTopChildUnder((int) r1f, (int) r2f), r0i);
                    r1i = this.h[r0i];
                    if ((this.p & r1i) != 0) {
                        this.r.onEdgeTouched(r1i & this.p, r0i);
                    }
                } else if (isCapturedViewUnder((int) r1f, (int) r2f)) {
                    a(this.s, r0i);
                }
                break;
            case ShareUtils.SHARE_COPY:
                r2i = MotionEventCompat.getPointerId(r10_MotionEvent, r3i);
                if (this.a == 1 && r2i == this.c) {
                    r3i = MotionEventCompat.getPointerCount(r10_MotionEvent);
                    while (r0i < r3i) {
                        int r4i = MotionEventCompat.getPointerId(r10_MotionEvent, r0i);
                        if (r4i == this.c) {
                            r0i++;
                        } else {
                            if (findTopChildUnder((int) MotionEventCompat.getX(r10_MotionEvent, r0i), (int) MotionEventCompat.getY(r10_MotionEvent, r0i)) == this.s && a(this.s, r4i)) {
                                r0i = this.c;
                                break;
                            } else {
                                r0i++;
                            }
                        }
                    }
                    r0i = -1;
                    if (r0i == -1) {
                        b();
                    }
                    b(r2i);
                } else {
                    b(r2i);
                }
                break;
        }
    }

    public void setEdgeTrackingEnabled(int r1i) {
        this.p = r1i;
    }

    public void setMinVelocity(float r1f) {
        this.n = r1f;
    }

    public boolean settleCapturedViewAt(int r4i, int r5i) {
        if (this.t) {
            return a(r4i, r5i, (int) VelocityTrackerCompat.getXVelocity(this.l, this.c), (int) VelocityTrackerCompat.getYVelocity(this.l, this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    public boolean shouldInterceptTouchEvent(MotionEvent r11_MotionEvent) {
        int r2i = MotionEventCompat.getActionMasked(r11_MotionEvent);
        int r3i = MotionEventCompat.getActionIndex(r11_MotionEvent);
        if (r2i == 0) {
            cancel();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(r11_MotionEvent);
        float r3f;
        int r4i;
        switch (r2i) {
            case STATE_IDLE:
                float r2f = r11_MotionEvent.getX();
                r3f = r11_MotionEvent.getY();
                r4i = MotionEventCompat.getPointerId(r11_MotionEvent, STATE_IDLE);
                a(r2f, r3f, r4i);
                View r2_View = findTopChildUnder((int) r2f, (int) r3f);
                if (r2_View == this.s && this.a == 2) {
                    a(r2_View, r4i);
                    r2i = this.h[r4i];
                } else {
                    r2i = this.h[r4i];
                }
                if ((this.p & r2i) != 0) {
                    this.r.onEdgeTouched(r2i & this.p, r4i);
                }
                break;
            case STATE_DRAGGING:
            case DIRECTION_ALL:
                cancel();
                break;
            case STATE_SETTLING:
                r3i = MotionEventCompat.getPointerCount(r11_MotionEvent);
                r2i = 0;
                while (r2i < r3i) {
                    r4i = MotionEventCompat.getPointerId(r11_MotionEvent, r2i);
                    float r5f = MotionEventCompat.getX(r11_MotionEvent, r2i);
                    float r6f = MotionEventCompat.getY(r11_MotionEvent, r2i);
                    float r7f = r5f - this.d[r4i];
                    float r8f = r6f - this.e[r4i];
                    b(r7f, r8f, r4i);
                    if (this.a == 1) {
                        break;
                    } else {
                        View r5_View = findTopChildUnder((int) r5f, (int) r6f);
                        if (r5_View != null && a(r5_View, r7f, r8f) && a(r5_View, r4i)) {
                            break;
                        } else {
                            r2i++;
                        }
                    }
                }
                a(r11_MotionEvent);
                break;
            case ShareUtils.SHARE_SMS:
                r2i = MotionEventCompat.getPointerId(r11_MotionEvent, r3i);
                float r4f = MotionEventCompat.getX(r11_MotionEvent, r3i);
                r3f = MotionEventCompat.getY(r11_MotionEvent, r3i);
                a(r4f, r3f, r2i);
                if (this.a == 0) {
                    r3i = this.h[r2i];
                    if ((this.p & r3i) != 0) {
                        this.r.onEdgeTouched(r3i & this.p, r2i);
                    }
                } else if (this.a == 2) {
                    View r3_View = findTopChildUnder((int) r4f, (int) r3f);
                    if (r3_View == this.s) {
                        a(r3_View, r2i);
                    }
                }
                break;
            case ShareUtils.SHARE_COPY:
                b(MotionEventCompat.getPointerId(r11_MotionEvent, r3i));
                break;
        }
        return this.a == 1;
    }

    public boolean smoothSlideViewTo(View r3_View, int r4i, int r5i) {
        this.s = r3_View;
        this.c = -1;
        return a(r4i, r5i, (int)STATE_IDLE, (int)STATE_IDLE);
    }
}