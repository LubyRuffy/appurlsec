package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener;

// compiled from: AccessibilityManagerCompatIcs.java
final class d implements AccessibilityStateChangeListener {
    final /* synthetic */ a a;

    d(a r1_a) {
        this.a = r1_a;
    }

    public void onAccessibilityStateChanged(boolean r2z) {
        this.a.onAccessibilityStateChanged(r2z);
    }
}