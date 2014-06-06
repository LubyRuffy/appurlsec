package android.support.v4.view.accessibility;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

// compiled from: AccessibilityNodeProviderCompat.java
class i implements a {
    final /* synthetic */ AccessibilityNodeProviderCompat a;
    final /* synthetic */ b b;

    i(b r1_b, AccessibilityNodeProviderCompat r2_AccessibilityNodeProviderCompat) {
        this.b = r1_b;
        this.a = r2_AccessibilityNodeProviderCompat;
    }

    public Object createAccessibilityNodeInfo(int r2i) {
        AccessibilityNodeInfoCompat r0_AccessibilityNodeInfoCompat = this.a.createAccessibilityNodeInfo(r2i);
        return r0_AccessibilityNodeInfoCompat == null ? null : r0_AccessibilityNodeInfoCompat.getInfo();
    }

    public List<Object> findAccessibilityNodeInfosByText(String r6_String, int r7i) {
        List r2_List = this.a.findAccessibilityNodeInfosByText(r6_String, r7i);
        List<Object> r3_List_Object = new ArrayList();
        int r4i = r2_List.size();
        int r1i = 0;
        while (r1i < r4i) {
            r3_List_Object.add(((AccessibilityNodeInfoCompat) r2_List.get(r1i)).getInfo());
            r1i++;
        }
        return r3_List_Object;
    }

    public boolean performAction(int r2i, int r3i, Bundle r4_Bundle) {
        return this.a.performAction(r2i, r3i, r4_Bundle);
    }
}