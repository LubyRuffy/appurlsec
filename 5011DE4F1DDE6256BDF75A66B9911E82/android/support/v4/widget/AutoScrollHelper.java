package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import qsbk.app.widget.listview.XListViewFooter;

public abstract class AutoScrollHelper implements OnTouchListener {
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    public static final float NO_MAX = 3.4028235E38f;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    private static final int r;
    private final a a;
    private final Interpolator b;
    private final View c;
    private Runnable d;
    private float[] e;
    private float[] f;
    private int g;
    private int h;
    private float[] i;
    private float[] j;
    private float[] k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;


    private static class a {
        private int a;
        private int b;
        private float c;
        private float d;
        private long e;
        private long f;
        private int g;
        private int h;
        private long i;
        private float j;
        private int k;

        public a() {
            this.e = -9223372036854775808L;
            this.i = -1;
            this.f = 0;
            this.g = 0;
            this.h = 0;
        }

        private float a_(float r3f) {
            return (-4.0f * r3f) * r3f + 4.0f * r3f;
        }

        private float a_(long r7j) {
            float r0f = 0.0f;
            if (r7j < this.e) {
                return 0.0f;
            }
            if (this.i < 0 || r7j < this.i) {
                return AutoScrollHelper.b(((float) (r7j - this.e)) / ((float) this.a), r0f, 1.0f) * 0.5f;
            }
            return AutoScrollHelper.b(((float) (r7j - this.i)) / ((float) this.k), r0f, 1.0f) * this.j + 1.0f - this.j;
        }

        public void computeScrollDelta() {
            if (this.f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            } else {
                long r0j = AnimationUtils.currentAnimationTimeMillis();
                float r2f = a(a(r0j));
                long r3j = r0j - this.f;
                this.f = r0j;
                this.g = (int) ((((float) r3j) * r2f) * this.c);
                this.h = (int) ((((float) r3j) * r2f) * this.d);
            }
        }

        public int getDeltaX() {
            return this.g;
        }

        public int getDeltaY() {
            return this.h;
        }

        public int getHorizontalDirection() {
            return (int) (this.c / Math.abs(this.c));
        }

        public int getVerticalDirection() {
            return (int) (this.d / Math.abs(this.d));
        }

        public boolean isFinished() {
            return (this.i > 0 ? 1 : (this.i == 0? 0 : -1)) > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        public void requestStop() {
            long r0j = AnimationUtils.currentAnimationTimeMillis();
            this.k = AutoScrollHelper.b((int) (r0j - this.e), (int)EDGE_TYPE_INSIDE, this.b);
            this.j = a(r0j);
            this.i = r0j;
        }

        public void setRampDownDuration(int r1i) {
            this.b = r1i;
        }

        public void setRampUpDuration(int r1i) {
            this.a = r1i;
        }

        public void setTargetVelocity(float r1f, float r2f) {
            this.c = r1f;
            this.d = r2f;
        }

        public void start() {
            this.e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1;
            this.f = this.e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }
    }

    private class b implements Runnable {
        private b() {
        }

        public void run() {
            if (AutoScrollHelper.this.o) {
                if (AutoScrollHelper.this.m) {
                    AutoScrollHelper.this.m = false;
                    AutoScrollHelper.this.start();
                }
                a r0_a = AutoScrollHelper.this;
                if (r0_a.isFinished() || (!AutoScrollHelper.this.a())) {
                    AutoScrollHelper.this.o = false;
                } else {
                    if (AutoScrollHelper.this.n) {
                        AutoScrollHelper.this.n = false;
                        AutoScrollHelper.this.d();
                    }
                    r0_a.computeScrollDelta();
                    AutoScrollHelper.this.scrollTargetBy(r0_a.getDeltaX(), r0_a.getDeltaY());
                    ViewCompat.postOnAnimation(AutoScrollHelper.this.c, this);
                }
            }
        }
    }

    static {
        r = ViewConfiguration.getTapTimeout();
    }

    public AutoScrollHelper(View r8_View) {
        this.a = new a();
        this.b = new AccelerateInterpolator();
        this.e = new float[]{0.0f, 0.0f};
        this.f = new float[]{3.4028235E38f, 3.4028235E38f};
        this.i = new float[]{0.0f, 0.0f};
        this.j = new float[]{0.0f, 0.0f};
        this.k = new float[]{3.4028235E38f, 3.4028235E38f};
        this.c = r8_View;
        DisplayMetrics r0_DisplayMetrics = Resources.getSystem().getDisplayMetrics();
        int r1i = (int) (1575.0f * r0_DisplayMetrics.density + 0.5f);
        int r0i = (int) (r0_DisplayMetrics.density * 315.0f + 0.5f);
        setMaximumVelocity((float) r1i, (float) r1i);
        setMinimumVelocity((float) r0i, (float) r0i);
        setEdgeType(EDGE_TYPE_INSIDE_EXTEND);
        setMaximumEdges(NO_MAX, NO_MAX);
        setRelativeEdges(0.2f, 0.2f);
        setRelativeVelocity(1.0f, 1.0f);
        setActivationDelay(r);
        setRampUpDuration(500);
        setRampDownDuration(500);
    }

    private float a(float r5f, float r6f) {
        if (r6f == 0.0f) {
            return 0.0f;
        }
        switch (this.g) {
            case EDGE_TYPE_INSIDE:
            case EDGE_TYPE_INSIDE_EXTEND:
                if (r5f >= r6f) {
                    return 0.0f;
                }
                if (r5f >= 0.0f) {
                    return 1.0f - r5f / r6f;
                }
                if (this.o && this.g == 1) {
                    return 1.0f;
                }
                return 0.0f;
            case EDGE_TYPE_OUTSIDE:
                return (r5f > 0.0f ? 1 : (r5f == 0.0f? 0 : -1)) < 0 ? r5f / (-r6f) : 0.0f;
        }
        return 0.0f;
    }

    private float a(float r5f, float r6f, float r7f, float r8f) {
        float r0f;
        float r1f = b(r5f * r6f, 0.0f, r7f);
        r1f = a(r6f - r8f, r1f) - a(r8f, r1f);
        if (r1f < 0.0f) {
            r0f = -this.b.getInterpolation(-r1f);
        } else {
            if (r1f <= 0.0f) {
                return 0.0f;
            }
            r0f = this.b.getInterpolation(r1f);
        }
        return b(r0f, -1.0f, 1.0f);
    }

    private float a(int r6i, float r7f, float r8f, float r9f) {
        float r0f = 0.0f;
        float r1f = a(this.e[r6i], r8f, this.f[r6i], r7f);
        if (r1f == 0.0f) {
            return 0.0f;
        }
        float r2f = this.i[r6i];
        float r3f = this.j[r6i];
        float r4f = this.k[r6i];
        r2f *= r9f;
        return (r1f > r0f ? 1 : (r1f == r0f? 0 : -1)) > 0 ? b(r1f * r2f, r3f, r4f) : -b((-r1f) * r2f, r3f, r4f);
    }

    private boolean a() {
        a r0_a = this.a;
        int r1i = r0_a.getVerticalDirection();
        int r0i = r0_a.getHorizontalDirection();
        if (r1i != 0 && canTargetScrollVertically(r1i)) {
            return true;
        }
        if (r0i == 0 || (!canTargetScrollHorizontally(r0i))) {
            return false;
        }
        return true;
    }

    private static float b(float r1f, float r2f, float r3f) {
        if (r1f > r3f) {
            return r3f;
        }
        if (r1f < r2f) {
            return r2f;
        }
        return r1f;
    }

    private static int b(int r0i, int r1i, int r2i) {
        if (r0i > r2i) {
            return r2i;
        }
        if (r0i < r1i) {
            return r1i;
        }
        return r0i;
    }

    private void b() {
        if (this.d == null) {
            this.d = new b(null);
        }
        this.o = true;
        this.m = true;
        if (this.l || this.h <= 0) {
            this.d.run();
        } else {
            ViewCompat.postOnAnimationDelayed(this.c, this.d, (long) this.h);
        }
        this.l = true;
    }

    private void c() {
        if (this.m) {
            this.o = false;
        } else {
            this.a.requestStop();
        }
    }

    private void d() {
        long r0j = SystemClock.uptimeMillis();
        MotionEvent r0_MotionEvent = MotionEvent.obtain(r0j, r0j, XListViewFooter.STATE_NOMORE, 0.0f, 0.0f, EDGE_TYPE_INSIDE);
        this.c.onTouchEvent(r0_MotionEvent);
        r0_MotionEvent.recycle();
    }

    public abstract boolean canTargetScrollHorizontally(int r1i);

    public abstract boolean canTargetScrollVertically(int r1i);

    public boolean isEnabled() {
        return this.p;
    }

    public boolean isExclusive() {
        return this.q;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(View r7_View, MotionEvent r8_MotionEvent) {
        /*
        r6_this = this;
        r0 = 1;
        r1 = 0;
        r2 = r6.p;
        if (r2 != 0) goto L_0x0007;
    L_0x0006:
        return r1;
    L_0x0007:
        r2 = android.support.v4.view.MotionEventCompat.getActionMasked(r8);
        switch(r2) {
            case 0: goto L_0x0018;
            case 1: goto L_0x0057;
            case 2: goto L_0x001c;
            case 3: goto L_0x0057;
            default: goto L_0x000e;
        };
    L_0x000e:
        r2 = r6.q;
        if (r2 == 0) goto L_0x005b;
    L_0x0012:
        r2 = r6.o;
        if (r2 == 0) goto L_0x005b;
    L_0x0016:
        r1 = r0;
        goto L_0x0006;
    L_0x0018:
        r6.n = r0;
        r6.l = r1;
    L_0x001c:
        r2 = r8.getX();
        r3 = r7.getWidth();
        r3 = (float) r3;
        r4 = r6.c;
        r4 = r4.getWidth();
        r4 = (float) r4;
        r2 = r6.a(r1, r2, r3, r4);
        r3 = r8.getY();
        r4 = r7.getHeight();
        r4 = (float) r4;
        r5 = r6.c;
        r5 = r5.getHeight();
        r5 = (float) r5;
        r3 = r6.a(r0, r3, r4, r5);
        r4 = r6.a;
        r4.setTargetVelocity(r2, r3);
        r2 = r6.o;
        if (r2 != 0) goto L_0x000e;
    L_0x004d:
        r2 = r6.a();
        if (r2 == 0) goto L_0x000e;
    L_0x0053:
        r6.b();
        goto L_0x000e;
    L_0x0057:
        r6.c();
        goto L_0x000e;
    L_0x005b:
        r0 = r1;
        goto L_0x0016;
        */

    }

    public abstract void scrollTargetBy(int r1i, int r2i);

    public AutoScrollHelper setActivationDelay(int r1i) {
        this.h = r1i;
        return this;
    }

    public AutoScrollHelper setEdgeType(int r1i) {
        this.g = r1i;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean r2z) {
        if ((!this.p) || r2z) {
            this.p = r2z;
            return this;
        } else {
            c();
            this.p = r2z;
            return this;
        }
    }

    public AutoScrollHelper setExclusive(boolean r1z) {
        this.q = r1z;
        return this;
    }

    public AutoScrollHelper setMaximumEdges(float r3f, float r4f) {
        this.f[0] = r3f;
        this.f[1] = r4f;
        return this;
    }

    public AutoScrollHelper setMaximumVelocity(float r5f, float r6f) {
        this.k[0] = r5f / 1000.0f;
        this.k[1] = r6f / 1000.0f;
        return this;
    }

    public AutoScrollHelper setMinimumVelocity(float r5f, float r6f) {
        this.j[0] = r5f / 1000.0f;
        this.j[1] = r6f / 1000.0f;
        return this;
    }

    public AutoScrollHelper setRampDownDuration(int r2i) {
        this.a.setRampDownDuration(r2i);
        return this;
    }

    public AutoScrollHelper setRampUpDuration(int r2i) {
        this.a.setRampUpDuration(r2i);
        return this;
    }

    public AutoScrollHelper setRelativeEdges(float r3f, float r4f) {
        this.e[0] = r3f;
        this.e[1] = r4f;
        return this;
    }

    public AutoScrollHelper setRelativeVelocity(float r5f, float r6f) {
        this.i[0] = r5f / 1000.0f;
        this.i[1] = r6f / 1000.0f;
        return this;
    }
}