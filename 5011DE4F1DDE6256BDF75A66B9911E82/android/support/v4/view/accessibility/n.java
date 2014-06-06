package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

// compiled from: AccessibilityNodeProviderCompatKitKat.java
final class n extends AccessibilityNodeProvider {
    final /* synthetic */ a a;

    n(a r1_a) {
        this.a = r1_a;
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int r2i) {
        return (AccessibilityNodeInfo) this.a.createAccessibilityNodeInfo(r2i);
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String r2_String, int r3i) {
        return this.a.findAccessibilityNodeInfosByText(r2_String, r3i);
    }

    public AccessibilityNodeInfo findFocus(int r2i) {
        return (AccessibilityNodeInfo) this.a.findFocus(r2i);
    }

    public boolean performAction(int r2i, int r3i, Bundle r4_Bundle) {
        return this.a.performAction(r2i, r3i, r4_Bundle);
    }
}