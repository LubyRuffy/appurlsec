package android.support.v4.widget;

import android.view.animation.Interpolator;

// compiled from: ViewDragHelper.java
final class q implements Interpolator {
    q() {
    }

    public float getInterpolation(float r4f) {
        float r0f = r4f - 1.0f;
        return r0f * (((r0f * r0f) * r0f) * r0f) + 1.0f;
    }
}