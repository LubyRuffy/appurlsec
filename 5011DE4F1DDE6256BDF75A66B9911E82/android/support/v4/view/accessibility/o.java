package android.support.v4.view.accessibility;

import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

// compiled from: AccessibilityRecordCompatIcs.java
class o {
    public static int getAddedCount(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getAddedCount();
    }

    public static CharSequence getBeforeText(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getBeforeText();
    }

    public static CharSequence getClassName(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getClassName();
    }

    public static CharSequence getContentDescription(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getContentDescription();
    }

    public static int getCurrentItemIndex(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getCurrentItemIndex();
    }

    public static int getFromIndex(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getFromIndex();
    }

    public static int getItemCount(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getItemCount();
    }

    public static Parcelable getParcelableData(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getParcelableData();
    }

    public static int getRemovedCount(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getRemovedCount();
    }

    public static int getScrollX(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getScrollX();
    }

    public static int getScrollY(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getScrollY();
    }

    public static Object getSource(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getSource();
    }

    public static List<CharSequence> getText(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getText();
    }

    public static int getToIndex(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getToIndex();
    }

    public static int getWindowId(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getWindowId();
    }

    public static boolean isChecked(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).isChecked();
    }

    public static boolean isEnabled(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).isEnabled();
    }

    public static boolean isFullScreen(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).isFullScreen();
    }

    public static boolean isPassword(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).isPassword();
    }

    public static boolean isScrollable(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).isScrollable();
    }

    public static Object obtain() {
        return AccessibilityRecord.obtain();
    }

    public static Object obtain(Object r1_Object) {
        return AccessibilityRecord.obtain((AccessibilityRecord) r1_Object);
    }

    public static void recycle(Object r0_Object) {
        ((AccessibilityRecord) r0_Object).recycle();
    }

    public static void setAddedCount(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setAddedCount(r1i);
    }

    public static void setBeforeText(Object r0_Object, CharSequence r1_CharSequence) {
        ((AccessibilityRecord) r0_Object).setBeforeText(r1_CharSequence);
    }

    public static void setChecked(Object r0_Object, boolean r1z) {
        ((AccessibilityRecord) r0_Object).setChecked(r1z);
    }

    public static void setClassName(Object r0_Object, CharSequence r1_CharSequence) {
        ((AccessibilityRecord) r0_Object).setClassName(r1_CharSequence);
    }

    public static void setContentDescription(Object r0_Object, CharSequence r1_CharSequence) {
        ((AccessibilityRecord) r0_Object).setContentDescription(r1_CharSequence);
    }

    public static void setCurrentItemIndex(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setCurrentItemIndex(r1i);
    }

    public static void setEnabled(Object r0_Object, boolean r1z) {
        ((AccessibilityRecord) r0_Object).setEnabled(r1z);
    }

    public static void setFromIndex(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setFromIndex(r1i);
    }

    public static void setFullScreen(Object r0_Object, boolean r1z) {
        ((AccessibilityRecord) r0_Object).setFullScreen(r1z);
    }

    public static void setItemCount(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setItemCount(r1i);
    }

    public static void setParcelableData(Object r0_Object, Parcelable r1_Parcelable) {
        ((AccessibilityRecord) r0_Object).setParcelableData(r1_Parcelable);
    }

    public static void setPassword(Object r0_Object, boolean r1z) {
        ((AccessibilityRecord) r0_Object).setPassword(r1z);
    }

    public static void setRemovedCount(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setRemovedCount(r1i);
    }

    public static void setScrollX(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setScrollX(r1i);
    }

    public static void setScrollY(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setScrollY(r1i);
    }

    public static void setScrollable(Object r0_Object, boolean r1z) {
        ((AccessibilityRecord) r0_Object).setScrollable(r1z);
    }

    public static void setSource(Object r0_Object, View r1_View) {
        ((AccessibilityRecord) r0_Object).setSource(r1_View);
    }

    public static void setToIndex(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setToIndex(r1i);
    }
}