package android.support.v4.view;

import android.view.animation.Interpolator;

// compiled from: ViewPager.java
final class ac implements Interpolator {
    ac() {
    }

    public float getInterpolation(float r4f) {
        float r0f = r4f - 1.0f;
        return r0f * (((r0f * r0f) * r0f) * r0f) + 1.0f;
    }
}