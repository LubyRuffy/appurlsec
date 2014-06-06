package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public class ViewCompat {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    public static final int LAYER_TYPE_HARDWARE = 2;
    public static final int LAYER_TYPE_NONE = 0;
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    public static final int MEASURED_SIZE_MASK = 16777215;
    public static final int MEASURED_STATE_MASK = -16777216;
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;
    public static final int OVER_SCROLL_ALWAYS = 0;
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    public static final int OVER_SCROLL_NEVER = 2;
    static final i a;

    static interface i {
        public boolean canScrollHorizontally(View r1_View, int r2i);

        public boolean canScrollVertically(View r1_View, int r2i);

        public int getAccessibilityLiveRegion(View r1_View);

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View r1_View);

        public float getAlpha(View r1_View);

        public int getImportantForAccessibility(View r1_View);

        public int getLabelFor(View r1_View);

        public int getLayerType(View r1_View);

        public int getLayoutDirection(View r1_View);

        public int getMeasuredHeightAndState(View r1_View);

        public int getMeasuredState(View r1_View);

        public int getMeasuredWidthAndState(View r1_View);

        public int getOverScrollMode(View r1_View);

        public ViewParent getParentForAccessibility(View r1_View);

        public boolean hasTransientState(View r1_View);

        public boolean isOpaque(View r1_View);

        public void onInitializeAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent);

        public void onInitializeAccessibilityNodeInfo(View r1_View, AccessibilityNodeInfoCompat r2_AccessibilityNodeInfoCompat);

        public void onPopulateAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent);

        public boolean performAccessibilityAction(View r1_View, int r2i, Bundle r3_Bundle);

        public void postInvalidateOnAnimation(View r1_View);

        public void postInvalidateOnAnimation(View r1_View, int r2i, int r3i, int r4i, int r5i);

        public void postOnAnimation(View r1_View, Runnable r2_Runnable);

        public void postOnAnimationDelayed(View r1_View, Runnable r2_Runnable, long r3j);

        public int resolveSizeAndState(int r1i, int r2i, int r3i);

        public void setAccessibilityDelegate(View r1_View, AccessibilityDelegateCompat r2_AccessibilityDelegateCompat);

        public void setAccessibilityLiveRegion(View r1_View, int r2i);

        public void setHasTransientState(View r1_View, boolean r2z);

        public void setImportantForAccessibility(View r1_View, int r2i);

        public void setLabelFor(View r1_View, int r2i);

        public void setLayerPaint(View r1_View, Paint r2_Paint);

        public void setLayerType(View r1_View, int r2i, Paint r3_Paint);

        public void setLayoutDirection(View r1_View, int r2i);

        public void setOverScrollMode(View r1_View, int r2i);
    }

    static class a implements i {
        a() {
        }

        long a_() {
            return 10;
        }

        public boolean canScrollHorizontally(View r2_View, int r3i) {
            return false;
        }

        public boolean canScrollVertically(View r2_View, int r3i) {
            return false;
        }

        public int getAccessibilityLiveRegion(View r2_View) {
            return OVER_SCROLL_ALWAYS;
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View r2_View) {
            return null;
        }

        public float getAlpha(View r2_View) {
            return 1.0f;
        }

        public int getImportantForAccessibility(View r2_View) {
            return OVER_SCROLL_ALWAYS;
        }

        public int getLabelFor(View r2_View) {
            return OVER_SCROLL_ALWAYS;
        }

        public int getLayerType(View r2_View) {
            return OVER_SCROLL_ALWAYS;
        }

        public int getLayoutDirection(View r2_View) {
            return OVER_SCROLL_ALWAYS;
        }

        public int getMeasuredHeightAndState(View r2_View) {
            return r2_View.getMeasuredHeight();
        }

        public int getMeasuredState(View r2_View) {
            return OVER_SCROLL_ALWAYS;
        }

        public int getMeasuredWidthAndState(View r2_View) {
            return r2_View.getMeasuredWidth();
        }

        public int getOverScrollMode(View r2_View) {
            return OVER_SCROLL_NEVER;
        }

        public ViewParent getParentForAccessibility(View r2_View) {
            return r2_View.getParent();
        }

        public boolean hasTransientState(View r2_View) {
            return false;
        }

        public boolean isOpaque(View r4_View) {
            Drawable r1_Drawable = r4_View.getBackground();
            return r1_Drawable != null && r1_Drawable.getOpacity() == -1;
        }

        public void onInitializeAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
        }

        public void onInitializeAccessibilityNodeInfo(View r1_View, AccessibilityNodeInfoCompat r2_AccessibilityNodeInfoCompat) {
        }

        public void onPopulateAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
        }

        public boolean performAccessibilityAction(View r2_View, int r3i, Bundle r4_Bundle) {
            return false;
        }

        public void postInvalidateOnAnimation(View r3_View) {
            r3_View.postInvalidateDelayed(a());
        }

        public void postInvalidateOnAnimation(View r8_View, int r9i, int r10i, int r11i, int r12i) {
            r8_View.postInvalidateDelayed(a(), r9i, r10i, r11i, r12i);
        }

        public void postOnAnimation(View r3_View, Runnable r4_Runnable) {
            r3_View.postDelayed(r4_Runnable, a());
        }

        public void postOnAnimationDelayed(View r3_View, Runnable r4_Runnable, long r5j) {
            r3_View.postDelayed(r4_Runnable, a() + r5j);
        }

        public int resolveSizeAndState(int r2i, int r3i, int r4i) {
            return View.resolveSize(r2i, r3i);
        }

        public void setAccessibilityDelegate(View r1_View, AccessibilityDelegateCompat r2_AccessibilityDelegateCompat) {
        }

        public void setAccessibilityLiveRegion(View r1_View, int r2i) {
        }

        public void setHasTransientState(View r1_View, boolean r2z) {
        }

        public void setImportantForAccessibility(View r1_View, int r2i) {
        }

        public void setLabelFor(View r1_View, int r2i) {
        }

        public void setLayerPaint(View r1_View, Paint r2_Paint) {
        }

        public void setLayerType(View r1_View, int r2i, Paint r3_Paint) {
        }

        public void setLayoutDirection(View r1_View, int r2i) {
        }

        public void setOverScrollMode(View r1_View, int r2i) {
        }
    }

    static class b extends a {
        b() {
        }

        public boolean isOpaque(View r2_View) {
            return r.isOpaque(r2_View);
        }
    }

    static class c extends b {
        c() {
        }

        public int getOverScrollMode(View r2_View) {
            return s.getOverScrollMode(r2_View);
        }

        public void setOverScrollMode(View r1_View, int r2i) {
            s.setOverScrollMode(r1_View, r2i);
        }
    }

    static class d extends c {
        d() {
        }

        long a() {
            return t.a();
        }

        public float getAlpha(View r2_View) {
            return t.getAlpha(r2_View);
        }

        public int getLayerType(View r2_View) {
            return t.getLayerType(r2_View);
        }

        public int getMeasuredHeightAndState(View r2_View) {
            return t.getMeasuredHeightAndState(r2_View);
        }

        public int getMeasuredState(View r2_View) {
            return t.getMeasuredState(r2_View);
        }

        public int getMeasuredWidthAndState(View r2_View) {
            return t.getMeasuredWidthAndState(r2_View);
        }

        public int resolveSizeAndState(int r2i, int r3i, int r4i) {
            return t.resolveSizeAndState(r2i, r3i, r4i);
        }

        public void setLayerPaint(View r2_View, Paint r3_Paint) {
            setLayerType(r2_View, getLayerType(r2_View), r3_Paint);
            r2_View.invalidate();
        }

        public void setLayerType(View r1_View, int r2i, Paint r3_Paint) {
            t.setLayerType(r1_View, r2i, r3_Paint);
        }
    }

    static class e extends d {
        e() {
        }

        public boolean canScrollHorizontally(View r2_View, int r3i) {
            return u.canScrollHorizontally(r2_View, r3i);
        }

        public boolean canScrollVertically(View r2_View, int r3i) {
            return u.canScrollVertically(r2_View, r3i);
        }

        public void onInitializeAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
            u.onInitializeAccessibilityEvent(r1_View, r2_AccessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(View r2_View, AccessibilityNodeInfoCompat r3_AccessibilityNodeInfoCompat) {
            u.onInitializeAccessibilityNodeInfo(r2_View, r3_AccessibilityNodeInfoCompat.getInfo());
        }

        public void onPopulateAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
            u.onPopulateAccessibilityEvent(r1_View, r2_AccessibilityEvent);
        }

        public void setAccessibilityDelegate(View r2_View, AccessibilityDelegateCompat r3_AccessibilityDelegateCompat) {
            u.setAccessibilityDelegate(r2_View, r3_AccessibilityDelegateCompat.a());
        }
    }

    static class f extends e {
        f() {
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View r3_View) {
            Object r1_Object = v.getAccessibilityNodeProvider(r3_View);
            return r1_Object != null ? new AccessibilityNodeProviderCompat(r1_Object) : null;
        }

        public int getImportantForAccessibility(View r2_View) {
            return v.getImportantForAccessibility(r2_View);
        }

        public ViewParent getParentForAccessibility(View r2_View) {
            return v.getParentForAccessibility(r2_View);
        }

        public boolean hasTransientState(View r2_View) {
            return v.hasTransientState(r2_View);
        }

        public boolean performAccessibilityAction(View r2_View, int r3i, Bundle r4_Bundle) {
            return v.performAccessibilityAction(r2_View, r3i, r4_Bundle);
        }

        public void postInvalidateOnAnimation(View r1_View) {
            v.postInvalidateOnAnimation(r1_View);
        }

        public void postInvalidateOnAnimation(View r1_View, int r2i, int r3i, int r4i, int r5i) {
            v.postInvalidateOnAnimation(r1_View, r2i, r3i, r4i, r5i);
        }

        public void postOnAnimation(View r1_View, Runnable r2_Runnable) {
            v.postOnAnimation(r1_View, r2_Runnable);
        }

        public void postOnAnimationDelayed(View r1_View, Runnable r2_Runnable, long r3j) {
            v.postOnAnimationDelayed(r1_View, r2_Runnable, r3j);
        }

        public void setHasTransientState(View r1_View, boolean r2z) {
            v.setHasTransientState(r1_View, r2z);
        }

        public void setImportantForAccessibility(View r1_View, int r2i) {
            v.setImportantForAccessibility(r1_View, r2i);
        }
    }

    static class g extends f {
        g() {
        }

        public int getLabelFor(View r2_View) {
            return w.getLabelFor(r2_View);
        }

        public int getLayoutDirection(View r2_View) {
            return w.getLayoutDirection(r2_View);
        }

        public void setLabelFor(View r1_View, int r2i) {
            w.setLabelFor(r1_View, r2i);
        }

        public void setLayerPaint(View r1_View, Paint r2_Paint) {
            w.setLayerPaint(r1_View, r2_Paint);
        }

        public void setLayoutDirection(View r1_View, int r2i) {
            w.setLayoutDirection(r1_View, r2i);
        }
    }

    static class h extends g {
        h() {
        }

        public int getAccessibilityLiveRegion(View r2_View) {
            return ViewCompatKitKat.getAccessibilityLiveRegion(r2_View);
        }

        public void setAccessibilityLiveRegion(View r1_View, int r2i) {
            ViewCompatKitKat.setAccessibilityLiveRegion(r1_View, r2i);
        }
    }

    static {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 19) {
            a = new h();
        } else if (r0i >= 17) {
            a = new g();
        } else if (r0i >= 16) {
            a = new f();
        } else if (r0i >= 14) {
            a = new e();
        } else if (r0i >= 11) {
            a = new d();
        } else if (r0i >= 9) {
            a = new c();
        } else {
            a = new a();
        }
    }

    public static boolean canScrollHorizontally(View r1_View, int r2i) {
        return a.canScrollHorizontally(r1_View, r2i);
    }

    public static boolean canScrollVertically(View r1_View, int r2i) {
        return a.canScrollVertically(r1_View, r2i);
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View r1_View) {
        return a.getAccessibilityNodeProvider(r1_View);
    }

    public static float getAlpha(View r1_View) {
        return a.getAlpha(r1_View);
    }

    public static int getImportantForAccessibility(View r1_View) {
        return a.getImportantForAccessibility(r1_View);
    }

    public static int getLabelFor(View r1_View) {
        return a.getLabelFor(r1_View);
    }

    public static int getLayerType(View r1_View) {
        return a.getLayerType(r1_View);
    }

    public static int getLayoutDirection(View r1_View) {
        return a.getLayoutDirection(r1_View);
    }

    public static int getMeasuredHeightAndState(View r1_View) {
        return a.getMeasuredHeightAndState(r1_View);
    }

    public static int getMeasuredState(View r1_View) {
        return a.getMeasuredState(r1_View);
    }

    public static int getMeasuredWidthAndState(View r1_View) {
        return a.getMeasuredWidthAndState(r1_View);
    }

    public static int getOverScrollMode(View r1_View) {
        return a.getOverScrollMode(r1_View);
    }

    public static ViewParent getParentForAccessibility(View r1_View) {
        return a.getParentForAccessibility(r1_View);
    }

    public static boolean hasTransientState(View r1_View) {
        return a.hasTransientState(r1_View);
    }

    public static boolean isOpaque(View r1_View) {
        return a.isOpaque(r1_View);
    }

    public static void onInitializeAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
        a.onInitializeAccessibilityEvent(r1_View, r2_AccessibilityEvent);
    }

    public static void onInitializeAccessibilityNodeInfo(View r1_View, AccessibilityNodeInfoCompat r2_AccessibilityNodeInfoCompat) {
        a.onInitializeAccessibilityNodeInfo(r1_View, r2_AccessibilityNodeInfoCompat);
    }

    public static void onPopulateAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
        a.onPopulateAccessibilityEvent(r1_View, r2_AccessibilityEvent);
    }

    public static boolean performAccessibilityAction(View r1_View, int r2i, Bundle r3_Bundle) {
        return a.performAccessibilityAction(r1_View, r2i, r3_Bundle);
    }

    public static void postInvalidateOnAnimation(View r1_View) {
        a.postInvalidateOnAnimation(r1_View);
    }

    public static void postInvalidateOnAnimation(View r6_View, int r7i, int r8i, int r9i, int r10i) {
        a.postInvalidateOnAnimation(r6_View, r7i, r8i, r9i, r10i);
    }

    public static void postOnAnimation(View r1_View, Runnable r2_Runnable) {
        a.postOnAnimation(r1_View, r2_Runnable);
    }

    public static void postOnAnimationDelayed(View r1_View, Runnable r2_Runnable, long r3j) {
        a.postOnAnimationDelayed(r1_View, r2_Runnable, r3j);
    }

    public static int resolveSizeAndState(int r1i, int r2i, int r3i) {
        return a.resolveSizeAndState(r1i, r2i, r3i);
    }

    public static void setAccessibilityDelegate(View r1_View, AccessibilityDelegateCompat r2_AccessibilityDelegateCompat) {
        a.setAccessibilityDelegate(r1_View, r2_AccessibilityDelegateCompat);
    }

    public static void setHasTransientState(View r1_View, boolean r2z) {
        a.setHasTransientState(r1_View, r2z);
    }

    public static void setImportantForAccessibility(View r1_View, int r2i) {
        a.setImportantForAccessibility(r1_View, r2i);
    }

    public static void setLabelFor(View r1_View, int r2i) {
        a.setLabelFor(r1_View, r2i);
    }

    public static void setLayerPaint(View r1_View, Paint r2_Paint) {
        a.setLayerPaint(r1_View, r2_Paint);
    }

    public static void setLayerType(View r1_View, int r2i, Paint r3_Paint) {
        a.setLayerType(r1_View, r2i, r3_Paint);
    }

    public static void setLayoutDirection(View r1_View, int r2i) {
        a.setLayoutDirection(r1_View, r2i);
    }

    public static void setOverScrollMode(View r1_View, int r2i) {
        a.setOverScrollMode(r1_View, r2i);
    }

    public int getAccessibilityLiveRegion(View r2_View) {
        return a.getAccessibilityLiveRegion(r2_View);
    }

    public void setAccessibilityLiveRegion(View r2_View, int r3i) {
        a.setAccessibilityLiveRegion(r2_View, r3i);
    }
}