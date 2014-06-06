package android.support.v4.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ScrollerCompat {
    static final a b;
    Object a;

    static interface a {
        public void abortAnimation(Object r1_Object);

        public boolean computeScrollOffset(Object r1_Object);

        public Object createScroller(Context r1_Context, Interpolator r2_Interpolator);

        public void fling(Object r1_Object, int r2i, int r3i, int r4i, int r5i, int r6i, int r7i, int r8i, int r9i);

        public void fling(Object r1_Object, int r2i, int r3i, int r4i, int r5i, int r6i, int r7i, int r8i, int r9i, int r10i, int r11i);

        public float getCurrVelocity(Object r1_Object);

        public int getCurrX(Object r1_Object);

        public int getCurrY(Object r1_Object);

        public int getFinalX(Object r1_Object);

        public int getFinalY(Object r1_Object);

        public boolean isFinished(Object r1_Object);

        public boolean isOverScrolled(Object r1_Object);

        public void notifyHorizontalEdgeReached(Object r1_Object, int r2i, int r3i, int r4i);

        public void notifyVerticalEdgeReached(Object r1_Object, int r2i, int r3i, int r4i);

        public void startScroll(Object r1_Object, int r2i, int r3i, int r4i, int r5i);

        public void startScroll(Object r1_Object, int r2i, int r3i, int r4i, int r5i, int r6i);
    }

    static class b implements a {
        b() {
        }

        public void abortAnimation(Object r1_Object) {
            ((Scroller) r1_Object).abortAnimation();
        }

        public boolean computeScrollOffset(Object r2_Object) {
            return ((Scroller) r2_Object).computeScrollOffset();
        }

        public Object createScroller(Context r2_Context, Interpolator r3_Interpolator) {
            return r3_Interpolator != null ? new Scroller(r2_Context, r3_Interpolator) : new Scroller(r2_Context);
        }

        public void fling(Object r10_Object, int r11i, int r12i, int r13i, int r14i, int r15i, int r16i, int r17i, int r18i) {
            ((Scroller) r10_Object).fling(r11i, r12i, r13i, r14i, r15i, r16i, r17i, r18i);
        }

        public void fling(Object r10_Object, int r11i, int r12i, int r13i, int r14i, int r15i, int r16i, int r17i, int r18i, int r19i, int r20i) {
            ((Scroller) r10_Object).fling(r11i, r12i, r13i, r14i, r15i, r16i, r17i, r18i);
        }

        public float getCurrVelocity(Object r2_Object) {
            return 0.0f;
        }

        public int getCurrX(Object r2_Object) {
            return ((Scroller) r2_Object).getCurrX();
        }

        public int getCurrY(Object r2_Object) {
            return ((Scroller) r2_Object).getCurrY();
        }

        public int getFinalX(Object r2_Object) {
            return ((Scroller) r2_Object).getFinalX();
        }

        public int getFinalY(Object r2_Object) {
            return ((Scroller) r2_Object).getFinalY();
        }

        public boolean isFinished(Object r2_Object) {
            return ((Scroller) r2_Object).isFinished();
        }

        public boolean isOverScrolled(Object r2_Object) {
            return false;
        }

        public void notifyHorizontalEdgeReached(Object r1_Object, int r2i, int r3i, int r4i) {
        }

        public void notifyVerticalEdgeReached(Object r1_Object, int r2i, int r3i, int r4i) {
        }

        public void startScroll(Object r1_Object, int r2i, int r3i, int r4i, int r5i) {
            ((Scroller) r1_Object).startScroll(r2i, r3i, r4i, r5i);
        }

        public void startScroll(Object r7_Object, int r8i, int r9i, int r10i, int r11i, int r12i) {
            ((Scroller) r7_Object).startScroll(r8i, r9i, r10i, r11i, r12i);
        }
    }

    static class c implements a {
        c() {
        }

        public void abortAnimation(Object r1_Object) {
            i.abortAnimation(r1_Object);
        }

        public boolean computeScrollOffset(Object r2_Object) {
            return i.computeScrollOffset(r2_Object);
        }

        public Object createScroller(Context r2_Context, Interpolator r3_Interpolator) {
            return i.createScroller(r2_Context, r3_Interpolator);
        }

        public void fling(Object r1_Object, int r2i, int r3i, int r4i, int r5i, int r6i, int r7i, int r8i, int r9i) {
            i.fling(r1_Object, r2i, r3i, r4i, r5i, r6i, r7i, r8i, r9i);
        }

        public void fling(Object r1_Object, int r2i, int r3i, int r4i, int r5i, int r6i, int r7i, int r8i, int r9i, int r10i, int r11i) {
            i.fling(r1_Object, r2i, r3i, r4i, r5i, r6i, r7i, r8i, r9i, r10i, r11i);
        }

        public float getCurrVelocity(Object r2_Object) {
            return 0.0f;
        }

        public int getCurrX(Object r2_Object) {
            return i.getCurrX(r2_Object);
        }

        public int getCurrY(Object r2_Object) {
            return i.getCurrY(r2_Object);
        }

        public int getFinalX(Object r2_Object) {
            return i.getFinalX(r2_Object);
        }

        public int getFinalY(Object r2_Object) {
            return i.getFinalY(r2_Object);
        }

        public boolean isFinished(Object r2_Object) {
            return i.isFinished(r2_Object);
        }

        public boolean isOverScrolled(Object r2_Object) {
            return i.isOverScrolled(r2_Object);
        }

        public void notifyHorizontalEdgeReached(Object r1_Object, int r2i, int r3i, int r4i) {
            i.notifyHorizontalEdgeReached(r1_Object, r2i, r3i, r4i);
        }

        public void notifyVerticalEdgeReached(Object r1_Object, int r2i, int r3i, int r4i) {
            i.notifyVerticalEdgeReached(r1_Object, r2i, r3i, r4i);
        }

        public void startScroll(Object r1_Object, int r2i, int r3i, int r4i, int r5i) {
            i.startScroll(r1_Object, r2i, r3i, r4i, r5i);
        }

        public void startScroll(Object r1_Object, int r2i, int r3i, int r4i, int r5i, int r6i) {
            i.startScroll(r1_Object, r2i, r3i, r4i, r5i, r6i);
        }
    }

    static class d extends c {
        d() {
        }

        public float getCurrVelocity(Object r2_Object) {
            return j.getCurrVelocity(r2_Object);
        }
    }

    static {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 14) {
            b = new d();
        } else if (r0i >= 9) {
            b = new c();
        } else {
            b = new b();
        }
    }

    ScrollerCompat(Context r2_Context, Interpolator r3_Interpolator) {
        this.a = b.createScroller(r2_Context, r3_Interpolator);
    }

    public static ScrollerCompat create(Context r1_Context) {
        return create(r1_Context, null);
    }

    public static ScrollerCompat create(Context r1_Context, Interpolator r2_Interpolator) {
        return new ScrollerCompat(r1_Context, r2_Interpolator);
    }

    public void abortAnimation() {
        b.abortAnimation(this.a);
    }

    public boolean computeScrollOffset() {
        return b.computeScrollOffset(this.a);
    }

    public void fling(int r11i, int r12i, int r13i, int r14i, int r15i, int r16i, int r17i, int r18i) {
        b.fling(this.a, r11i, r12i, r13i, r14i, r15i, r16i, r17i, r18i);
    }

    public void fling(int r13i, int r14i, int r15i, int r16i, int r17i, int r18i, int r19i, int r20i, int r21i, int r22i) {
        b.fling(this.a, r13i, r14i, r15i, r16i, r17i, r18i, r19i, r20i, r21i, r22i);
    }

    public float getCurrVelocity() {
        return b.getCurrVelocity(this.a);
    }

    public int getCurrX() {
        return b.getCurrX(this.a);
    }

    public int getCurrY() {
        return b.getCurrY(this.a);
    }

    public int getFinalX() {
        return b.getFinalX(this.a);
    }

    public int getFinalY() {
        return b.getFinalY(this.a);
    }

    public boolean isFinished() {
        return b.isFinished(this.a);
    }

    public boolean isOverScrolled() {
        return b.isOverScrolled(this.a);
    }

    public void notifyHorizontalEdgeReached(int r3i, int r4i, int r5i) {
        b.notifyHorizontalEdgeReached(this.a, r3i, r4i, r5i);
    }

    public void notifyVerticalEdgeReached(int r3i, int r4i, int r5i) {
        b.notifyVerticalEdgeReached(this.a, r3i, r4i, r5i);
    }

    public void startScroll(int r7i, int r8i, int r9i, int r10i) {
        b.startScroll(this.a, r7i, r8i, r9i, r10i);
    }

    public void startScroll(int r8i, int r9i, int r10i, int r11i, int r12i) {
        b.startScroll(this.a, r8i, r9i, r10i, r11i, r12i);
    }
}