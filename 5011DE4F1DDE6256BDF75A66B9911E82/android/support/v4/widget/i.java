package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

// compiled from: ScrollerCompatGingerbread.java
class i {
    public static void abortAnimation(Object r0_Object) {
        ((OverScroller) r0_Object).abortAnimation();
    }

    public static boolean computeScrollOffset(Object r1_Object) {
        return ((OverScroller) r1_Object).computeScrollOffset();
    }

    public static Object createScroller(Context r1_Context, Interpolator r2_Interpolator) {
        return r2_Interpolator != null ? new OverScroller(r1_Context, r2_Interpolator) : new OverScroller(r1_Context);
    }

    public static void fling(Object r9_Object, int r10i, int r11i, int r12i, int r13i, int r14i, int r15i, int r16i, int r17i) {
        ((OverScroller) r9_Object).fling(r10i, r11i, r12i, r13i, r14i, r15i, r16i, r17i);
    }

    public static void fling(Object r11_Object, int r12i, int r13i, int r14i, int r15i, int r16i, int r17i, int r18i, int r19i, int r20i, int r21i) {
        ((OverScroller) r11_Object).fling(r12i, r13i, r14i, r15i, r16i, r17i, r18i, r19i, r20i, r21i);
    }

    public static int getCurrX(Object r1_Object) {
        return ((OverScroller) r1_Object).getCurrX();
    }

    public static int getCurrY(Object r1_Object) {
        return ((OverScroller) r1_Object).getCurrY();
    }

    public static int getFinalX(Object r1_Object) {
        return ((OverScroller) r1_Object).getFinalX();
    }

    public static int getFinalY(Object r1_Object) {
        return ((OverScroller) r1_Object).getFinalY();
    }

    public static boolean isFinished(Object r1_Object) {
        return ((OverScroller) r1_Object).isFinished();
    }

    public static boolean isOverScrolled(Object r1_Object) {
        return ((OverScroller) r1_Object).isOverScrolled();
    }

    public static void notifyHorizontalEdgeReached(Object r0_Object, int r1i, int r2i, int r3i) {
        ((OverScroller) r0_Object).notifyHorizontalEdgeReached(r1i, r2i, r3i);
    }

    public static void notifyVerticalEdgeReached(Object r0_Object, int r1i, int r2i, int r3i) {
        ((OverScroller) r0_Object).notifyVerticalEdgeReached(r1i, r2i, r3i);
    }

    public static void startScroll(Object r0_Object, int r1i, int r2i, int r3i, int r4i) {
        ((OverScroller) r0_Object).startScroll(r1i, r2i, r3i, r4i);
    }

    public static void startScroll(Object r6_Object, int r7i, int r8i, int r9i, int r10i, int r11i) {
        ((OverScroller) r6_Object).startScroll(r7i, r8i, r9i, r10i, r11i);
    }
}