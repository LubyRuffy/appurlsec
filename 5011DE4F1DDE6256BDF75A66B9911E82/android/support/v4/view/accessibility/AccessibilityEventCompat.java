package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityEventCompat {
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_GESTURE_DETECTION_END = 524288;
    public static final int TYPE_GESTURE_DETECTION_START = 262144;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 2097152;
    public static final int TYPE_TOUCH_INTERACTION_START = 1048576;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 65536;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 131072;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;
    private static final c a;

    static interface c {
        public void appendRecord(AccessibilityEvent r1_AccessibilityEvent, Object r2_Object);

        public Object getRecord(AccessibilityEvent r1_AccessibilityEvent, int r2i);

        public int getRecordCount(AccessibilityEvent r1_AccessibilityEvent);
    }

    static class b implements c {
        b() {
        }

        public void appendRecord(AccessibilityEvent r1_AccessibilityEvent, Object r2_Object) {
        }

        public Object getRecord(AccessibilityEvent r2_AccessibilityEvent, int r3i) {
            return null;
        }

        public int getRecordCount(AccessibilityEvent r2_AccessibilityEvent) {
            return 0;
        }
    }

    static class a extends b {
        a() {
        }

        public void appendRecord(AccessibilityEvent r1_AccessibilityEvent, Object r2_Object) {
            a.appendRecord(r1_AccessibilityEvent, r2_Object);
        }

        public Object getRecord(AccessibilityEvent r2_AccessibilityEvent, int r3i) {
            return a.getRecord(r2_AccessibilityEvent, r3i);
        }

        public int getRecordCount(AccessibilityEvent r2_AccessibilityEvent) {
            return a.getRecordCount(r2_AccessibilityEvent);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            a = new a();
        } else {
            a = new b();
        }
    }

    private AccessibilityEventCompat() {
    }

    public static void appendRecord(AccessibilityEvent r2_AccessibilityEvent, AccessibilityRecordCompat r3_AccessibilityRecordCompat) {
        a.appendRecord(r2_AccessibilityEvent, r3_AccessibilityRecordCompat.getImpl());
    }

    public static AccessibilityRecordCompat asRecord(AccessibilityEvent r1_AccessibilityEvent) {
        return new AccessibilityRecordCompat(r1_AccessibilityEvent);
    }

    public static AccessibilityRecordCompat getRecord(AccessibilityEvent r2_AccessibilityEvent, int r3i) {
        return new AccessibilityRecordCompat(a.getRecord(r2_AccessibilityEvent, r3i));
    }

    public static int getRecordCount(AccessibilityEvent r1_AccessibilityEvent) {
        return a.getRecordCount(r1_AccessibilityEvent);
    }
}