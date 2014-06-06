package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessibilityNodeInfoCompat {
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 65536;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 131072;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private static final b a;
    private final Object b;

    static interface b {
        public void addAction(Object r1_Object, int r2i);

        public void addChild(Object r1_Object, View r2_View);

        public void addChild(Object r1_Object, View r2_View, int r3i);

        public List<Object> findAccessibilityNodeInfosByText(Object r1_Object, String r2_String);

        public Object findFocus(Object r1_Object, int r2i);

        public Object focusSearch(Object r1_Object, int r2i);

        public int getActions(Object r1_Object);

        public void getBoundsInParent(Object r1_Object, Rect r2_Rect);

        public void getBoundsInScreen(Object r1_Object, Rect r2_Rect);

        public Object getChild(Object r1_Object, int r2i);

        public int getChildCount(Object r1_Object);

        public CharSequence getClassName(Object r1_Object);

        public CharSequence getContentDescription(Object r1_Object);

        public int getLiveRegion(Object r1_Object);

        public int getMovementGranularities(Object r1_Object);

        public CharSequence getPackageName(Object r1_Object);

        public Object getParent(Object r1_Object);

        public CharSequence getText(Object r1_Object);

        public String getViewIdResourceName(Object r1_Object);

        public int getWindowId(Object r1_Object);

        public boolean isAccessibilityFocused(Object r1_Object);

        public boolean isCheckable(Object r1_Object);

        public boolean isChecked(Object r1_Object);

        public boolean isClickable(Object r1_Object);

        public boolean isEnabled(Object r1_Object);

        public boolean isFocusable(Object r1_Object);

        public boolean isFocused(Object r1_Object);

        public boolean isLongClickable(Object r1_Object);

        public boolean isPassword(Object r1_Object);

        public boolean isScrollable(Object r1_Object);

        public boolean isSelected(Object r1_Object);

        public boolean isVisibleToUser(Object r1_Object);

        public Object obtain();

        public Object obtain(View r1_View);

        public Object obtain(View r1_View, int r2i);

        public Object obtain(Object r1_Object);

        public boolean performAction(Object r1_Object, int r2i);

        public boolean performAction(Object r1_Object, int r2i, Bundle r3_Bundle);

        public void recycle(Object r1_Object);

        public void setAccessibilityFocused(Object r1_Object, boolean r2z);

        public void setBoundsInParent(Object r1_Object, Rect r2_Rect);

        public void setBoundsInScreen(Object r1_Object, Rect r2_Rect);

        public void setCheckable(Object r1_Object, boolean r2z);

        public void setChecked(Object r1_Object, boolean r2z);

        public void setClassName(Object r1_Object, CharSequence r2_CharSequence);

        public void setClickable(Object r1_Object, boolean r2z);

        public void setContentDescription(Object r1_Object, CharSequence r2_CharSequence);

        public void setEnabled(Object r1_Object, boolean r2z);

        public void setFocusable(Object r1_Object, boolean r2z);

        public void setFocused(Object r1_Object, boolean r2z);

        public void setLiveRegion(Object r1_Object, int r2i);

        public void setLongClickable(Object r1_Object, boolean r2z);

        public void setMovementGranularities(Object r1_Object, int r2i);

        public void setPackageName(Object r1_Object, CharSequence r2_CharSequence);

        public void setParent(Object r1_Object, View r2_View);

        public void setParent(Object r1_Object, View r2_View, int r3i);

        public void setPassword(Object r1_Object, boolean r2z);

        public void setScrollable(Object r1_Object, boolean r2z);

        public void setSelected(Object r1_Object, boolean r2z);

        public void setSource(Object r1_Object, View r2_View);

        public void setSource(Object r1_Object, View r2_View, int r3i);

        public void setText(Object r1_Object, CharSequence r2_CharSequence);

        public void setViewIdResourceName(Object r1_Object, String r2_String);

        public void setVisibleToUser(Object r1_Object, boolean r2z);
    }

    static class f implements b {
        f() {
        }

        public void addAction(Object r1_Object, int r2i) {
        }

        public void addChild(Object r1_Object, View r2_View) {
        }

        public void addChild(Object r1_Object, View r2_View, int r3i) {
        }

        public List<Object> findAccessibilityNodeInfosByText(Object r2_Object, String r3_String) {
            return Collections.emptyList();
        }

        public Object findFocus(Object r2_Object, int r3i) {
            return null;
        }

        public Object focusSearch(Object r2_Object, int r3i) {
            return null;
        }

        public int getActions(Object r2_Object) {
            return 0;
        }

        public void getBoundsInParent(Object r1_Object, Rect r2_Rect) {
        }

        public void getBoundsInScreen(Object r1_Object, Rect r2_Rect) {
        }

        public Object getChild(Object r2_Object, int r3i) {
            return null;
        }

        public int getChildCount(Object r2_Object) {
            return 0;
        }

        public CharSequence getClassName(Object r2_Object) {
            return null;
        }

        public CharSequence getContentDescription(Object r2_Object) {
            return null;
        }

        public int getLiveRegion(Object r2_Object) {
            return 0;
        }

        public int getMovementGranularities(Object r2_Object) {
            return 0;
        }

        public CharSequence getPackageName(Object r2_Object) {
            return null;
        }

        public Object getParent(Object r2_Object) {
            return null;
        }

        public CharSequence getText(Object r2_Object) {
            return null;
        }

        public String getViewIdResourceName(Object r2_Object) {
            return null;
        }

        public int getWindowId(Object r2_Object) {
            return 0;
        }

        public boolean isAccessibilityFocused(Object r2_Object) {
            return false;
        }

        public boolean isCheckable(Object r2_Object) {
            return false;
        }

        public boolean isChecked(Object r2_Object) {
            return false;
        }

        public boolean isClickable(Object r2_Object) {
            return false;
        }

        public boolean isEnabled(Object r2_Object) {
            return false;
        }

        public boolean isFocusable(Object r2_Object) {
            return false;
        }

        public boolean isFocused(Object r2_Object) {
            return false;
        }

        public boolean isLongClickable(Object r2_Object) {
            return false;
        }

        public boolean isPassword(Object r2_Object) {
            return false;
        }

        public boolean isScrollable(Object r2_Object) {
            return false;
        }

        public boolean isSelected(Object r2_Object) {
            return false;
        }

        public boolean isVisibleToUser(Object r2_Object) {
            return false;
        }

        public Object obtain() {
            return null;
        }

        public Object obtain(View r2_View) {
            return null;
        }

        public Object obtain(View r2_View, int r3i) {
            return null;
        }

        public Object obtain(Object r2_Object) {
            return null;
        }

        public boolean performAction(Object r2_Object, int r3i) {
            return false;
        }

        public boolean performAction(Object r2_Object, int r3i, Bundle r4_Bundle) {
            return false;
        }

        public void recycle(Object r1_Object) {
        }

        public void setAccessibilityFocused(Object r1_Object, boolean r2z) {
        }

        public void setBoundsInParent(Object r1_Object, Rect r2_Rect) {
        }

        public void setBoundsInScreen(Object r1_Object, Rect r2_Rect) {
        }

        public void setCheckable(Object r1_Object, boolean r2z) {
        }

        public void setChecked(Object r1_Object, boolean r2z) {
        }

        public void setClassName(Object r1_Object, CharSequence r2_CharSequence) {
        }

        public void setClickable(Object r1_Object, boolean r2z) {
        }

        public void setContentDescription(Object r1_Object, CharSequence r2_CharSequence) {
        }

        public void setEnabled(Object r1_Object, boolean r2z) {
        }

        public void setFocusable(Object r1_Object, boolean r2z) {
        }

        public void setFocused(Object r1_Object, boolean r2z) {
        }

        public void setLiveRegion(Object r1_Object, int r2i) {
        }

        public void setLongClickable(Object r1_Object, boolean r2z) {
        }

        public void setMovementGranularities(Object r1_Object, int r2i) {
        }

        public void setPackageName(Object r1_Object, CharSequence r2_CharSequence) {
        }

        public void setParent(Object r1_Object, View r2_View) {
        }

        public void setParent(Object r1_Object, View r2_View, int r3i) {
        }

        public void setPassword(Object r1_Object, boolean r2z) {
        }

        public void setScrollable(Object r1_Object, boolean r2z) {
        }

        public void setSelected(Object r1_Object, boolean r2z) {
        }

        public void setSource(Object r1_Object, View r2_View) {
        }

        public void setSource(Object r1_Object, View r2_View, int r3i) {
        }

        public void setText(Object r1_Object, CharSequence r2_CharSequence) {
        }

        public void setViewIdResourceName(Object r1_Object, String r2_String) {
        }

        public void setVisibleToUser(Object r1_Object, boolean r2z) {
        }
    }

    static class a extends f {
        a() {
        }

        public void addAction(Object r1_Object, int r2i) {
            e.addAction(r1_Object, r2i);
        }

        public void addChild(Object r1_Object, View r2_View) {
            e.addChild(r1_Object, r2_View);
        }

        public List<Object> findAccessibilityNodeInfosByText(Object r2_Object, String r3_String) {
            return e.findAccessibilityNodeInfosByText(r2_Object, r3_String);
        }

        public int getActions(Object r2_Object) {
            return e.getActions(r2_Object);
        }

        public void getBoundsInParent(Object r1_Object, Rect r2_Rect) {
            e.getBoundsInParent(r1_Object, r2_Rect);
        }

        public void getBoundsInScreen(Object r1_Object, Rect r2_Rect) {
            e.getBoundsInScreen(r1_Object, r2_Rect);
        }

        public Object getChild(Object r2_Object, int r3i) {
            return e.getChild(r2_Object, r3i);
        }

        public int getChildCount(Object r2_Object) {
            return e.getChildCount(r2_Object);
        }

        public CharSequence getClassName(Object r2_Object) {
            return e.getClassName(r2_Object);
        }

        public CharSequence getContentDescription(Object r2_Object) {
            return e.getContentDescription(r2_Object);
        }

        public CharSequence getPackageName(Object r2_Object) {
            return e.getPackageName(r2_Object);
        }

        public Object getParent(Object r2_Object) {
            return e.getParent(r2_Object);
        }

        public CharSequence getText(Object r2_Object) {
            return e.getText(r2_Object);
        }

        public int getWindowId(Object r2_Object) {
            return e.getWindowId(r2_Object);
        }

        public boolean isCheckable(Object r2_Object) {
            return e.isCheckable(r2_Object);
        }

        public boolean isChecked(Object r2_Object) {
            return e.isChecked(r2_Object);
        }

        public boolean isClickable(Object r2_Object) {
            return e.isClickable(r2_Object);
        }

        public boolean isEnabled(Object r2_Object) {
            return e.isEnabled(r2_Object);
        }

        public boolean isFocusable(Object r2_Object) {
            return e.isFocusable(r2_Object);
        }

        public boolean isFocused(Object r2_Object) {
            return e.isFocused(r2_Object);
        }

        public boolean isLongClickable(Object r2_Object) {
            return e.isLongClickable(r2_Object);
        }

        public boolean isPassword(Object r2_Object) {
            return e.isPassword(r2_Object);
        }

        public boolean isScrollable(Object r2_Object) {
            return e.isScrollable(r2_Object);
        }

        public boolean isSelected(Object r2_Object) {
            return e.isSelected(r2_Object);
        }

        public Object obtain() {
            return e.obtain();
        }

        public Object obtain(View r2_View) {
            return e.obtain(r2_View);
        }

        public Object obtain(Object r2_Object) {
            return e.obtain(r2_Object);
        }

        public boolean performAction(Object r2_Object, int r3i) {
            return e.performAction(r2_Object, r3i);
        }

        public void recycle(Object r1_Object) {
            e.recycle(r1_Object);
        }

        public void setBoundsInParent(Object r1_Object, Rect r2_Rect) {
            e.setBoundsInParent(r1_Object, r2_Rect);
        }

        public void setBoundsInScreen(Object r1_Object, Rect r2_Rect) {
            e.setBoundsInScreen(r1_Object, r2_Rect);
        }

        public void setCheckable(Object r1_Object, boolean r2z) {
            e.setCheckable(r1_Object, r2z);
        }

        public void setChecked(Object r1_Object, boolean r2z) {
            e.setChecked(r1_Object, r2z);
        }

        public void setClassName(Object r1_Object, CharSequence r2_CharSequence) {
            e.setClassName(r1_Object, r2_CharSequence);
        }

        public void setClickable(Object r1_Object, boolean r2z) {
            e.setClickable(r1_Object, r2z);
        }

        public void setContentDescription(Object r1_Object, CharSequence r2_CharSequence) {
            e.setContentDescription(r1_Object, r2_CharSequence);
        }

        public void setEnabled(Object r1_Object, boolean r2z) {
            e.setEnabled(r1_Object, r2z);
        }

        public void setFocusable(Object r1_Object, boolean r2z) {
            e.setFocusable(r1_Object, r2z);
        }

        public void setFocused(Object r1_Object, boolean r2z) {
            e.setFocused(r1_Object, r2z);
        }

        public void setLongClickable(Object r1_Object, boolean r2z) {
            e.setLongClickable(r1_Object, r2z);
        }

        public void setPackageName(Object r1_Object, CharSequence r2_CharSequence) {
            e.setPackageName(r1_Object, r2_CharSequence);
        }

        public void setParent(Object r1_Object, View r2_View) {
            e.setParent(r1_Object, r2_View);
        }

        public void setPassword(Object r1_Object, boolean r2z) {
            e.setPassword(r1_Object, r2z);
        }

        public void setScrollable(Object r1_Object, boolean r2z) {
            e.setScrollable(r1_Object, r2z);
        }

        public void setSelected(Object r1_Object, boolean r2z) {
            e.setSelected(r1_Object, r2z);
        }

        public void setSource(Object r1_Object, View r2_View) {
            e.setSource(r1_Object, r2_View);
        }

        public void setText(Object r1_Object, CharSequence r2_CharSequence) {
            e.setText(r1_Object, r2_CharSequence);
        }
    }

    static class c extends a {
        c() {
        }

        public void addChild(Object r1_Object, View r2_View, int r3i) {
            f.addChild(r1_Object, r2_View, r3i);
        }

        public Object findFocus(Object r2_Object, int r3i) {
            return f.findFocus(r2_Object, r3i);
        }

        public Object focusSearch(Object r2_Object, int r3i) {
            return f.focusSearch(r2_Object, r3i);
        }

        public int getMovementGranularities(Object r2_Object) {
            return f.getMovementGranularities(r2_Object);
        }

        public boolean isAccessibilityFocused(Object r2_Object) {
            return f.isAccessibilityFocused(r2_Object);
        }

        public boolean isVisibleToUser(Object r2_Object) {
            return f.isVisibleToUser(r2_Object);
        }

        public Object obtain(View r2_View, int r3i) {
            return f.obtain(r2_View, r3i);
        }

        public boolean performAction(Object r2_Object, int r3i, Bundle r4_Bundle) {
            return f.performAction(r2_Object, r3i, r4_Bundle);
        }

        public void setAccessibilityFocused(Object r1_Object, boolean r2z) {
            f.setAccesibilityFocused(r1_Object, r2z);
        }

        public void setMovementGranularities(Object r1_Object, int r2i) {
            f.setMovementGranularities(r1_Object, r2i);
        }

        public void setParent(Object r1_Object, View r2_View, int r3i) {
            f.setParent(r1_Object, r2_View, r3i);
        }

        public void setSource(Object r1_Object, View r2_View, int r3i) {
            f.setSource(r1_Object, r2_View, r3i);
        }

        public void setVisibleToUser(Object r1_Object, boolean r2z) {
            f.setVisibleToUser(r1_Object, r2z);
        }
    }

    static class d extends c {
        d() {
        }

        public String getViewIdResourceName(Object r2_Object) {
            return g.getViewIdResourceName(r2_Object);
        }

        public void setViewIdResourceName(Object r1_Object, String r2_String) {
            g.setViewIdResourceName(r1_Object, r2_String);
        }
    }

    static class e extends d {
        e() {
        }

        public int getLiveRegion(Object r2_Object) {
            return h.getLiveRegion(r2_Object);
        }

        public void setLiveRegion(Object r1_Object, int r2i) {
            h.setLiveRegion(r1_Object, r2i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            a = new e();
        } else if (VERSION.SDK_INT >= 18) {
            a = new d();
        } else if (VERSION.SDK_INT >= 16) {
            a = new c();
        } else if (VERSION.SDK_INT >= 14) {
            a = new a();
        } else {
            a = new f();
        }
    }

    public AccessibilityNodeInfoCompat(Object r1_Object) {
        this.b = r1_Object;
    }

    static AccessibilityNodeInfoCompat a(Object r1_Object) {
        return r1_Object != null ? new AccessibilityNodeInfoCompat(r1_Object) : null;
    }

    private static String a(int r1i) {
        switch (r1i) {
            case MOVEMENT_GRANULARITY_CHARACTER:
                return "ACTION_FOCUS";
            case MOVEMENT_GRANULARITY_WORD:
                return "ACTION_CLEAR_FOCUS";
            case MOVEMENT_GRANULARITY_LINE:
                return "ACTION_SELECT";
            case MOVEMENT_GRANULARITY_PARAGRAPH:
                return "ACTION_CLEAR_SELECTION";
            case MOVEMENT_GRANULARITY_PAGE:
                return "ACTION_CLICK";
            case ACTION_LONG_CLICK:
                return "ACTION_LONG_CLICK";
            case ACTION_ACCESSIBILITY_FOCUS:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case ACTION_CLEAR_ACCESSIBILITY_FOCUS:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case ACTION_NEXT_AT_MOVEMENT_GRANULARITY:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case ACTION_NEXT_HTML_ELEMENT:
                return "ACTION_NEXT_HTML_ELEMENT";
            case ACTION_PREVIOUS_HTML_ELEMENT:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case ACTION_SCROLL_FORWARD:
                return "ACTION_SCROLL_FORWARD";
            case ACTION_SCROLL_BACKWARD:
                return "ACTION_SCROLL_BACKWARD";
            case ACTION_COPY:
                return "ACTION_COPY";
            case ACTION_PASTE:
                return "ACTION_PASTE";
            case ACTION_CUT:
                return "ACTION_CUT";
            case ACTION_SET_SELECTION:
                return "ACTION_SET_SELECTION";
        }
        return "ACTION_UNKNOWN";
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return a(a.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat r2_AccessibilityNodeInfoCompat) {
        return a(a.obtain(r2_AccessibilityNodeInfoCompat.b));
    }

    public static AccessibilityNodeInfoCompat obtain(View r1_View) {
        return a(a.obtain(r1_View));
    }

    public static AccessibilityNodeInfoCompat obtain(View r1_View, int r2i) {
        return a(a.obtain(r1_View, r2i));
    }

    public void addAction(int r3i) {
        a.addAction(this.b, r3i);
    }

    public void addChild(View r3_View) {
        a.addChild(this.b, r3_View);
    }

    public void addChild(View r3_View, int r4i) {
        a.addChild(this.b, r3_View, r4i);
    }

    public boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        AccessibilityNodeInfoCompat r5_AccessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) r5_Object;
        if (this.b == null) {
            return r5_AccessibilityNodeInfoCompat.b == null;
        } else {
            if (this.b.equals(r5_AccessibilityNodeInfoCompat.b)) {
                return true;
            }
            return false;
        }
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String r7_String) {
        List<AccessibilityNodeInfoCompat> r1_List_AccessibilityNodeInfoCompat = new ArrayList();
        List r2_List = a.findAccessibilityNodeInfosByText(this.b, r7_String);
        int r3i = r2_List.size();
        int r0i = 0;
        while (r0i < r3i) {
            r1_List_AccessibilityNodeInfoCompat.add(new AccessibilityNodeInfoCompat(r2_List.get(r0i)));
            r0i++;
        }
        return r1_List_AccessibilityNodeInfoCompat;
    }

    public AccessibilityNodeInfoCompat findFocus(int r3i) {
        return a(a.findFocus(this.b, r3i));
    }

    public AccessibilityNodeInfoCompat focusSearch(int r3i) {
        return a(a.focusSearch(this.b, r3i));
    }

    public int getActions() {
        return a.getActions(this.b);
    }

    public void getBoundsInParent(Rect r3_Rect) {
        a.getBoundsInParent(this.b, r3_Rect);
    }

    public void getBoundsInScreen(Rect r3_Rect) {
        a.getBoundsInScreen(this.b, r3_Rect);
    }

    public AccessibilityNodeInfoCompat getChild(int r3i) {
        return a(a.getChild(this.b, r3i));
    }

    public int getChildCount() {
        return a.getChildCount(this.b);
    }

    public CharSequence getClassName() {
        return a.getClassName(this.b);
    }

    public CharSequence getContentDescription() {
        return a.getContentDescription(this.b);
    }

    public Object getInfo() {
        return this.b;
    }

    public int getLiveRegion() {
        return a.getLiveRegion(this.b);
    }

    public int getMovementGranularities() {
        return a.getMovementGranularities(this.b);
    }

    public CharSequence getPackageName() {
        return a.getPackageName(this.b);
    }

    public AccessibilityNodeInfoCompat getParent() {
        return a(a.getParent(this.b));
    }

    public CharSequence getText() {
        return a.getText(this.b);
    }

    public String getViewIdResourceName() {
        return a.getViewIdResourceName(this.b);
    }

    public int getWindowId() {
        return a.getWindowId(this.b);
    }

    public int hashCode() {
        return this.b == null ? 0 : this.b.hashCode();
    }

    public boolean isAccessibilityFocused() {
        return a.isAccessibilityFocused(this.b);
    }

    public boolean isCheckable() {
        return a.isCheckable(this.b);
    }

    public boolean isChecked() {
        return a.isChecked(this.b);
    }

    public boolean isClickable() {
        return a.isClickable(this.b);
    }

    public boolean isEnabled() {
        return a.isEnabled(this.b);
    }

    public boolean isFocusable() {
        return a.isFocusable(this.b);
    }

    public boolean isFocused() {
        return a.isFocused(this.b);
    }

    public boolean isLongClickable() {
        return a.isLongClickable(this.b);
    }

    public boolean isPassword() {
        return a.isPassword(this.b);
    }

    public boolean isScrollable() {
        return a.isScrollable(this.b);
    }

    public boolean isSelected() {
        return a.isSelected(this.b);
    }

    public boolean isVisibleToUser() {
        return a.isVisibleToUser(this.b);
    }

    public boolean performAction(int r3i) {
        return a.performAction(this.b, r3i);
    }

    public boolean performAction(int r3i, Bundle r4_Bundle) {
        return a.performAction(this.b, r3i, r4_Bundle);
    }

    public void recycle() {
        a.recycle(this.b);
    }

    public void setAccessibilityFocused(boolean r3z) {
        a.setAccessibilityFocused(this.b, r3z);
    }

    public void setBoundsInParent(Rect r3_Rect) {
        a.setBoundsInParent(this.b, r3_Rect);
    }

    public void setBoundsInScreen(Rect r3_Rect) {
        a.setBoundsInScreen(this.b, r3_Rect);
    }

    public void setCheckable(boolean r3z) {
        a.setCheckable(this.b, r3z);
    }

    public void setChecked(boolean r3z) {
        a.setChecked(this.b, r3z);
    }

    public void setClassName(CharSequence r3_CharSequence) {
        a.setClassName(this.b, r3_CharSequence);
    }

    public void setClickable(boolean r3z) {
        a.setClickable(this.b, r3z);
    }

    public void setContentDescription(CharSequence r3_CharSequence) {
        a.setContentDescription(this.b, r3_CharSequence);
    }

    public void setEnabled(boolean r3z) {
        a.setEnabled(this.b, r3z);
    }

    public void setFocusable(boolean r3z) {
        a.setFocusable(this.b, r3z);
    }

    public void setFocused(boolean r3z) {
        a.setFocused(this.b, r3z);
    }

    public void setLiveRegion(int r3i) {
        a.setLiveRegion(this.b, r3i);
    }

    public void setLongClickable(boolean r3z) {
        a.setLongClickable(this.b, r3z);
    }

    public void setMovementGranularities(int r3i) {
        a.setMovementGranularities(this.b, r3i);
    }

    public void setPackageName(CharSequence r3_CharSequence) {
        a.setPackageName(this.b, r3_CharSequence);
    }

    public void setParent(View r3_View) {
        a.setParent(this.b, r3_View);
    }

    public void setParent(View r3_View, int r4i) {
        a.setParent(this.b, r3_View, r4i);
    }

    public void setPassword(boolean r3z) {
        a.setPassword(this.b, r3z);
    }

    public void setScrollable(boolean r3z) {
        a.setScrollable(this.b, r3z);
    }

    public void setSelected(boolean r3z) {
        a.setSelected(this.b, r3z);
    }

    public void setSource(View r3_View) {
        a.setSource(this.b, r3_View);
    }

    public void setSource(View r3_View, int r4i) {
        a.setSource(this.b, r3_View, r4i);
    }

    public void setText(CharSequence r3_CharSequence) {
        a.setText(this.b, r3_CharSequence);
    }

    public void setViewIdResourceName(String r3_String) {
        a.setViewIdResourceName(this.b, r3_String);
    }

    public void setVisibleToUser(boolean r3z) {
        a.setVisibleToUser(this.b, r3z);
    }

    public String toString() {
        StringBuilder r1_StringBuilder = new StringBuilder();
        r1_StringBuilder.append(super.toString());
        Rect r0_Rect = new Rect();
        getBoundsInParent(r0_Rect);
        r1_StringBuilder.append("; boundsInParent: " + r0_Rect);
        getBoundsInScreen(r0_Rect);
        r1_StringBuilder.append("; boundsInScreen: " + r0_Rect);
        r1_StringBuilder.append("; packageName: ").append(getPackageName());
        r1_StringBuilder.append("; className: ").append(getClassName());
        r1_StringBuilder.append("; text: ").append(getText());
        r1_StringBuilder.append("; contentDescription: ").append(getContentDescription());
        r1_StringBuilder.append("; viewId: ").append(getViewIdResourceName());
        r1_StringBuilder.append("; checkable: ").append(isCheckable());
        r1_StringBuilder.append("; checked: ").append(isChecked());
        r1_StringBuilder.append("; focusable: ").append(isFocusable());
        r1_StringBuilder.append("; focused: ").append(isFocused());
        r1_StringBuilder.append("; selected: ").append(isSelected());
        r1_StringBuilder.append("; clickable: ").append(isClickable());
        r1_StringBuilder.append("; longClickable: ").append(isLongClickable());
        r1_StringBuilder.append("; enabled: ").append(isEnabled());
        r1_StringBuilder.append("; password: ").append(isPassword());
        r1_StringBuilder.append("; scrollable: " + isScrollable());
        r1_StringBuilder.append("; [");
        int r0i = getActions();
        while (r0i != 0) {
            int r2i = 1 << Integer.numberOfTrailingZeros(r0i);
            r0i &= r2i ^ -1;
            r1_StringBuilder.append(a(r2i));
            if (r0i != 0) {
                r1_StringBuilder.append(", ");
            }
        }
        r1_StringBuilder.append("]");
        return r1_StringBuilder.toString();
    }
}