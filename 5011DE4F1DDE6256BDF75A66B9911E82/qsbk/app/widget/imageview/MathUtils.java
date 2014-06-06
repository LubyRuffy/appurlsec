package qsbk.app.widget.imageview;

import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;

public class MathUtils {
    public static float angle(float r4f, float r5f, float r6f, float r7f) {
        return (float) Math.atan2((double) (r7f - r5f), (double) (r6f - r4f));
    }

    public static float angle(PointF r4_PointF, PointF r5_PointF) {
        return angle(r4_PointF.x, r4_PointF.y, r5_PointF.x, r5_PointF.y);
    }

    public static float distance(float r2f, float r3f, float r4f, float r5f) {
        float r0f = r2f - r4f;
        float r1f = r3f - r5f;
        return FloatMath.sqrt(r0f * r0f + r1f * r1f);
    }

    public static float distance(PointF r3_PointF, PointF r4_PointF) {
        float r0f = r3_PointF.x - r4_PointF.x;
        float r1f = r3_PointF.y - r4_PointF.y;
        return FloatMath.sqrt(r0f * r0f + r1f * r1f);
    }

    public static float distance(MotionEvent r4_MotionEvent) {
        float r0f = r4_MotionEvent.getX(0) - r4_MotionEvent.getX(1);
        float r1f = r4_MotionEvent.getY(0) - r4_MotionEvent.getY(1);
        return FloatMath.sqrt(r0f * r0f + r1f * r1f);
    }

    public static void midpoint(float r2f, float r3f, float r4f, float r5f, PointF r6_PointF) {
        r6_PointF.x = (r2f + r4f) / 2.0f;
        r6_PointF.y = (r3f + r5f) / 2.0f;
    }

    public static void midpoint(MotionEvent r4_MotionEvent, PointF r5_PointF) {
        midpoint(r4_MotionEvent.getX(0), r4_MotionEvent.getY(0), r4_MotionEvent.getX(1), r4_MotionEvent.getY(1), r5_PointF);
    }

    public void rotate(PointF r8_PointF, PointF r9_PointF, float r10f) {
        float r0f = r8_PointF.x;
        float r1f = r8_PointF.y;
        float r2f = r9_PointF.x;
        float r3f = r9_PointF.y;
        r8_PointF.x = FloatMath.cos(r10f) * (r0f - r2f) - FloatMath.sin(r10f) * (r1f - r3f) + r2f;
        r8_PointF.y = (r0f - r2f) * FloatMath.sin(r10f) + (r1f - r3f) * FloatMath.cos(r10f) + r3f;
    }
}