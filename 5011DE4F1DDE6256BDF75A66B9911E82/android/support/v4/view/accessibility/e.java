package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

// compiled from: AccessibilityNodeInfoCompatIcs.java
class e {
    public static void addAction(Object r0_Object, int r1i) {
        ((AccessibilityNodeInfo) r0_Object).addAction(r1i);
    }

    public static void addChild(Object r0_Object, View r1_View) {
        ((AccessibilityNodeInfo) r0_Object).addChild(r1_View);
    }

    public static List<Object> findAccessibilityNodeInfosByText(Object r1_Object, String r2_String) {
        return ((AccessibilityNodeInfo) r1_Object).findAccessibilityNodeInfosByText(r2_String);
    }

    public static int getActions(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getActions();
    }

    public static void getBoundsInParent(Object r0_Object, Rect r1_Rect) {
        ((AccessibilityNodeInfo) r0_Object).getBoundsInParent(r1_Rect);
    }

    public static void getBoundsInScreen(Object r0_Object, Rect r1_Rect) {
        ((AccessibilityNodeInfo) r0_Object).getBoundsInScreen(r1_Rect);
    }

    public static Object getChild(Object r1_Object, int r2i) {
        return ((AccessibilityNodeInfo) r1_Object).getChild(r2i);
    }

    public static int getChildCount(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getChildCount();
    }

    public static CharSequence getClassName(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getClassName();
    }

    public static CharSequence getContentDescription(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getContentDescription();
    }

    public static CharSequence getPackageName(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getPackageName();
    }

    public static Object getParent(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getParent();
    }

    public static CharSequence getText(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getText();
    }

    public static int getWindowId(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getWindowId();
    }

    public static boolean isCheckable(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isCheckable();
    }

    public static boolean isChecked(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isChecked();
    }

    public static boolean isClickable(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isClickable();
    }

    public static boolean isEnabled(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isEnabled();
    }

    public static boolean isFocusable(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isFocusable();
    }

    public static boolean isFocused(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isFocused();
    }

    public static boolean isLongClickable(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isLongClickable();
    }

    public static boolean isPassword(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isPassword();
    }

    public static boolean isScrollable(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isScrollable();
    }

    public static boolean isSelected(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isSelected();
    }

    public static Object obtain() {
        return AccessibilityNodeInfo.obtain();
    }

    public static Object obtain(View r1_View) {
        return AccessibilityNodeInfo.obtain(r1_View);
    }

    public static Object obtain(Object r1_Object) {
        return AccessibilityNodeInfo.obtain((AccessibilityNodeInfo) r1_Object);
    }

    public static boolean performAction(Object r1_Object, int r2i) {
        return ((AccessibilityNodeInfo) r1_Object).performAction(r2i);
    }

    public static void recycle(Object r0_Object) {
        ((AccessibilityNodeInfo) r0_Object).recycle();
    }

    public static void setBoundsInParent(Object r0_Object, Rect r1_Rect) {
        ((AccessibilityNodeInfo) r0_Object).setBoundsInParent(r1_Rect);
    }

    public static void setBoundsInScreen(Object r0_Object, Rect r1_Rect) {
        ((AccessibilityNodeInfo) r0_Object).setBoundsInScreen(r1_Rect);
    }

    public static void setCheckable(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setCheckable(r1z);
    }

    public static void setChecked(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setChecked(r1z);
    }

    public static void setClassName(Object r0_Object, CharSequence r1_CharSequence) {
        ((AccessibilityNodeInfo) r0_Object).setClassName(r1_CharSequence);
    }

    public static void setClickable(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setClickable(r1z);
    }

    public static void setContentDescription(Object r0_Object, CharSequence r1_CharSequence) {
        ((AccessibilityNodeInfo) r0_Object).setContentDescription(r1_CharSequence);
    }

    public static void setEnabled(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setEnabled(r1z);
    }

    public static void setFocusable(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setFocusable(r1z);
    }

    public static void setFocused(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setFocused(r1z);
    }

    public static void setLongClickable(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setLongClickable(r1z);
    }

    public static void setPackageName(Object r0_Object, CharSequence r1_CharSequence) {
        ((AccessibilityNodeInfo) r0_Object).setPackageName(r1_CharSequence);
    }

    public static void setParent(Object r0_Object, View r1_View) {
        ((AccessibilityNodeInfo) r0_Object).setParent(r1_View);
    }

    public static void setPassword(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setPassword(r1z);
    }

    public static void setScrollable(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setScrollable(r1z);
    }

    public static void setSelected(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setSelected(r1z);
    }

    public static void setSource(Object r0_Object, View r1_View) {
        ((AccessibilityNodeInfo) r0_Object).setSource(r1_View);
    }

    public static void setText(Object r0_Object, CharSequence r1_CharSequence) {
        ((AccessibilityNodeInfo) r0_Object).setText(r1_CharSequence);
    }
}