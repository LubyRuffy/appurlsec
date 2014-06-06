package android.support.v4.view.accessibility;

import android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat;

// compiled from: AccessibilityManagerCompat.java
class b implements a {
    final /* synthetic */ AccessibilityStateChangeListenerCompat a;
    final /* synthetic */ a b;

    b(a r1_a, AccessibilityStateChangeListenerCompat r2_AccessibilityStateChangeListenerCompat) {
        this.b = r1_a;
        this.a = r2_AccessibilityStateChangeListenerCompat;
    }

    public void onAccessibilityStateChanged(boolean r2z) {
        this.a.onAccessibilityStateChanged(r2z);
    }
}