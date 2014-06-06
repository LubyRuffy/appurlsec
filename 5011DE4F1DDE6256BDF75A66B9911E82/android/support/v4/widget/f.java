package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

// compiled from: EdgeEffectCompatIcs.java
class f {
    public static boolean draw(Object r1_Object, Canvas r2_Canvas) {
        return ((EdgeEffect) r1_Object).draw(r2_Canvas);
    }

    public static void finish(Object r0_Object) {
        ((EdgeEffect) r0_Object).finish();
    }

    public static boolean isFinished(Object r1_Object) {
        return ((EdgeEffect) r1_Object).isFinished();
    }

    public static Object newEdgeEffect(Context r1_Context) {
        return new EdgeEffect(r1_Context);
    }

    public static boolean onAbsorb(Object r1_Object, int r2i) {
        ((EdgeEffect) r1_Object).onAbsorb(r2i);
        return true;
    }

    public static boolean onPull(Object r1_Object, float r2f) {
        ((EdgeEffect) r1_Object).onPull(r2f);
        return true;
    }

    public static boolean onRelease(Object r1_Object) {
        EdgeEffect r1_EdgeEffect = (EdgeEffect) r1_Object;
        r1_EdgeEffect.onRelease();
        return r1_EdgeEffect.isFinished();
    }

    public static void setSize(Object r0_Object, int r1i, int r2i) {
        ((EdgeEffect) r0_Object).setSize(r1i, r2i);
    }
}