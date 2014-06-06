package android.support.v4.view.accessibility;

import android.os.Bundle;
import java.util.List;

// compiled from: AccessibilityNodeProviderCompatKitKat.java
class m {

    // compiled from: AccessibilityNodeProviderCompatKitKat.java
    static interface a {
        public Object createAccessibilityNodeInfo(int r1i);

        public List<Object> findAccessibilityNodeInfosByText(String r1_String, int r2i);

        public Object findFocus(int r1i);

        public boolean performAction(int r1i, int r2i, Bundle r3_Bundle);
    }

    public static Object newAccessibilityNodeProviderBridge(a r1_a) {
        return new n(r1_a);
    }
}