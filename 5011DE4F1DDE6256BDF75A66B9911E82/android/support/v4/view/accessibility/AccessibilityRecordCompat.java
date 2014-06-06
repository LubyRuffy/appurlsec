package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.os.Parcelable;
import android.view.View;
import java.util.Collections;
import java.util.List;

public class AccessibilityRecordCompat {
    private static final c a;
    private final Object b;

    static interface c {
        public int getAddedCount(Object r1_Object);

        public CharSequence getBeforeText(Object r1_Object);

        public CharSequence getClassName(Object r1_Object);

        public CharSequence getContentDescription(Object r1_Object);

        public int getCurrentItemIndex(Object r1_Object);

        public int getFromIndex(Object r1_Object);

        public int getItemCount(Object r1_Object);

        public int getMaxScrollX(Object r1_Object);

        public int getMaxScrollY(Object r1_Object);

        public Parcelable getParcelableData(Object r1_Object);

        public int getRemovedCount(Object r1_Object);

        public int getScrollX(Object r1_Object);

        public int getScrollY(Object r1_Object);

        public AccessibilityNodeInfoCompat getSource(Object r1_Object);

        public List<CharSequence> getText(Object r1_Object);

        public int getToIndex(Object r1_Object);

        public int getWindowId(Object r1_Object);

        public boolean isChecked(Object r1_Object);

        public boolean isEnabled(Object r1_Object);

        public boolean isFullScreen(Object r1_Object);

        public boolean isPassword(Object r1_Object);

        public boolean isScrollable(Object r1_Object);

        public Object obtain();

        public Object obtain(Object r1_Object);

        public void recycle(Object r1_Object);

        public void setAddedCount(Object r1_Object, int r2i);

        public void setBeforeText(Object r1_Object, CharSequence r2_CharSequence);

        public void setChecked(Object r1_Object, boolean r2z);

        public void setClassName(Object r1_Object, CharSequence r2_CharSequence);

        public void setContentDescription(Object r1_Object, CharSequence r2_CharSequence);

        public void setCurrentItemIndex(Object r1_Object, int r2i);

        public void setEnabled(Object r1_Object, boolean r2z);

        public void setFromIndex(Object r1_Object, int r2i);

        public void setFullScreen(Object r1_Object, boolean r2z);

        public void setItemCount(Object r1_Object, int r2i);

        public void setMaxScrollX(Object r1_Object, int r2i);

        public void setMaxScrollY(Object r1_Object, int r2i);

        public void setParcelableData(Object r1_Object, Parcelable r2_Parcelable);

        public void setPassword(Object r1_Object, boolean r2z);

        public void setRemovedCount(Object r1_Object, int r2i);

        public void setScrollX(Object r1_Object, int r2i);

        public void setScrollY(Object r1_Object, int r2i);

        public void setScrollable(Object r1_Object, boolean r2z);

        public void setSource(Object r1_Object, View r2_View);

        public void setSource(Object r1_Object, View r2_View, int r3i);

        public void setToIndex(Object r1_Object, int r2i);
    }

    static class e implements c {
        e() {
        }

        public int getAddedCount(Object r2_Object) {
            return 0;
        }

        public CharSequence getBeforeText(Object r2_Object) {
            return null;
        }

        public CharSequence getClassName(Object r2_Object) {
            return null;
        }

        public CharSequence getContentDescription(Object r2_Object) {
            return null;
        }

        public int getCurrentItemIndex(Object r2_Object) {
            return 0;
        }

        public int getFromIndex(Object r2_Object) {
            return 0;
        }

        public int getItemCount(Object r2_Object) {
            return 0;
        }

        public int getMaxScrollX(Object r2_Object) {
            return 0;
        }

        public int getMaxScrollY(Object r2_Object) {
            return 0;
        }

        public Parcelable getParcelableData(Object r2_Object) {
            return null;
        }

        public int getRemovedCount(Object r2_Object) {
            return 0;
        }

        public int getScrollX(Object r2_Object) {
            return 0;
        }

        public int getScrollY(Object r2_Object) {
            return 0;
        }

        public AccessibilityNodeInfoCompat getSource(Object r2_Object) {
            return null;
        }

        public List<CharSequence> getText(Object r2_Object) {
            return Collections.emptyList();
        }

        public int getToIndex(Object r2_Object) {
            return 0;
        }

        public int getWindowId(Object r2_Object) {
            return 0;
        }

        public boolean isChecked(Object r2_Object) {
            return false;
        }

        public boolean isEnabled(Object r2_Object) {
            return false;
        }

        public boolean isFullScreen(Object r2_Object) {
            return false;
        }

        public boolean isPassword(Object r2_Object) {
            return false;
        }

        public boolean isScrollable(Object r2_Object) {
            return false;
        }

        public Object obtain() {
            return null;
        }

        public Object obtain(Object r2_Object) {
            return null;
        }

        public void recycle(Object r1_Object) {
        }

        public void setAddedCount(Object r1_Object, int r2i) {
        }

        public void setBeforeText(Object r1_Object, CharSequence r2_CharSequence) {
        }

        public void setChecked(Object r1_Object, boolean r2z) {
        }

        public void setClassName(Object r1_Object, CharSequence r2_CharSequence) {
        }

        public void setContentDescription(Object r1_Object, CharSequence r2_CharSequence) {
        }

        public void setCurrentItemIndex(Object r1_Object, int r2i) {
        }

        public void setEnabled(Object r1_Object, boolean r2z) {
        }

        public void setFromIndex(Object r1_Object, int r2i) {
        }

        public void setFullScreen(Object r1_Object, boolean r2z) {
        }

        public void setItemCount(Object r1_Object, int r2i) {
        }

        public void setMaxScrollX(Object r1_Object, int r2i) {
        }

        public void setMaxScrollY(Object r1_Object, int r2i) {
        }

        public void setParcelableData(Object r1_Object, Parcelable r2_Parcelable) {
        }

        public void setPassword(Object r1_Object, boolean r2z) {
        }

        public void setRemovedCount(Object r1_Object, int r2i) {
        }

        public void setScrollX(Object r1_Object, int r2i) {
        }

        public void setScrollY(Object r1_Object, int r2i) {
        }

        public void setScrollable(Object r1_Object, boolean r2z) {
        }

        public void setSource(Object r1_Object, View r2_View) {
        }

        public void setSource(Object r1_Object, View r2_View, int r3i) {
        }

        public void setToIndex(Object r1_Object, int r2i) {
        }
    }

    static class a extends e {
        a() {
        }

        public int getAddedCount(Object r2_Object) {
            return o.getAddedCount(r2_Object);
        }

        public CharSequence getBeforeText(Object r2_Object) {
            return o.getBeforeText(r2_Object);
        }

        public CharSequence getClassName(Object r2_Object) {
            return o.getClassName(r2_Object);
        }

        public CharSequence getContentDescription(Object r2_Object) {
            return o.getContentDescription(r2_Object);
        }

        public int getCurrentItemIndex(Object r2_Object) {
            return o.getCurrentItemIndex(r2_Object);
        }

        public int getFromIndex(Object r2_Object) {
            return o.getFromIndex(r2_Object);
        }

        public int getItemCount(Object r2_Object) {
            return o.getItemCount(r2_Object);
        }

        public Parcelable getParcelableData(Object r2_Object) {
            return o.getParcelableData(r2_Object);
        }

        public int getRemovedCount(Object r2_Object) {
            return o.getRemovedCount(r2_Object);
        }

        public int getScrollX(Object r2_Object) {
            return o.getScrollX(r2_Object);
        }

        public int getScrollY(Object r2_Object) {
            return o.getScrollY(r2_Object);
        }

        public AccessibilityNodeInfoCompat getSource(Object r2_Object) {
            return AccessibilityNodeInfoCompat.a(o.getSource(r2_Object));
        }

        public List<CharSequence> getText(Object r2_Object) {
            return o.getText(r2_Object);
        }

        public int getToIndex(Object r2_Object) {
            return o.getToIndex(r2_Object);
        }

        public int getWindowId(Object r2_Object) {
            return o.getWindowId(r2_Object);
        }

        public boolean isChecked(Object r2_Object) {
            return o.isChecked(r2_Object);
        }

        public boolean isEnabled(Object r2_Object) {
            return o.isEnabled(r2_Object);
        }

        public boolean isFullScreen(Object r2_Object) {
            return o.isFullScreen(r2_Object);
        }

        public boolean isPassword(Object r2_Object) {
            return o.isPassword(r2_Object);
        }

        public boolean isScrollable(Object r2_Object) {
            return o.isScrollable(r2_Object);
        }

        public Object obtain() {
            return o.obtain();
        }

        public Object obtain(Object r2_Object) {
            return o.obtain(r2_Object);
        }

        public void recycle(Object r1_Object) {
            o.recycle(r1_Object);
        }

        public void setAddedCount(Object r1_Object, int r2i) {
            o.setAddedCount(r1_Object, r2i);
        }

        public void setBeforeText(Object r1_Object, CharSequence r2_CharSequence) {
            o.setBeforeText(r1_Object, r2_CharSequence);
        }

        public void setChecked(Object r1_Object, boolean r2z) {
            o.setChecked(r1_Object, r2z);
        }

        public void setClassName(Object r1_Object, CharSequence r2_CharSequence) {
            o.setClassName(r1_Object, r2_CharSequence);
        }

        public void setContentDescription(Object r1_Object, CharSequence r2_CharSequence) {
            o.setContentDescription(r1_Object, r2_CharSequence);
        }

        public void setCurrentItemIndex(Object r1_Object, int r2i) {
            o.setCurrentItemIndex(r1_Object, r2i);
        }

        public void setEnabled(Object r1_Object, boolean r2z) {
            o.setEnabled(r1_Object, r2z);
        }

        public void setFromIndex(Object r1_Object, int r2i) {
            o.setFromIndex(r1_Object, r2i);
        }

        public void setFullScreen(Object r1_Object, boolean r2z) {
            o.setFullScreen(r1_Object, r2z);
        }

        public void setItemCount(Object r1_Object, int r2i) {
            o.setItemCount(r1_Object, r2i);
        }

        public void setParcelableData(Object r1_Object, Parcelable r2_Parcelable) {
            o.setParcelableData(r1_Object, r2_Parcelable);
        }

        public void setPassword(Object r1_Object, boolean r2z) {
            o.setPassword(r1_Object, r2z);
        }

        public void setRemovedCount(Object r1_Object, int r2i) {
            o.setRemovedCount(r1_Object, r2i);
        }

        public void setScrollX(Object r1_Object, int r2i) {
            o.setScrollX(r1_Object, r2i);
        }

        public void setScrollY(Object r1_Object, int r2i) {
            o.setScrollY(r1_Object, r2i);
        }

        public void setScrollable(Object r1_Object, boolean r2z) {
            o.setScrollable(r1_Object, r2z);
        }

        public void setSource(Object r1_Object, View r2_View) {
            o.setSource(r1_Object, r2_View);
        }

        public void setToIndex(Object r1_Object, int r2i) {
            o.setToIndex(r1_Object, r2i);
        }
    }

    static class b extends a {
        b() {
        }

        public int getMaxScrollX(Object r2_Object) {
            return p.getMaxScrollX(r2_Object);
        }

        public int getMaxScrollY(Object r2_Object) {
            return p.getMaxScrollY(r2_Object);
        }

        public void setMaxScrollX(Object r1_Object, int r2i) {
            p.setMaxScrollX(r1_Object, r2i);
        }

        public void setMaxScrollY(Object r1_Object, int r2i) {
            p.setMaxScrollY(r1_Object, r2i);
        }
    }

    static class d extends b {
        d() {
        }

        public void setSource(Object r1_Object, View r2_View, int r3i) {
            q.setSource(r1_Object, r2_View, r3i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new d();
        } else if (VERSION.SDK_INT >= 15) {
            a = new b();
        } else if (VERSION.SDK_INT >= 14) {
            a = new a();
        } else {
            a = new e();
        }
    }

    public AccessibilityRecordCompat(Object r1_Object) {
        this.b = r1_Object;
    }

    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(a.obtain());
    }

    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat r3_AccessibilityRecordCompat) {
        return new AccessibilityRecordCompat(a.obtain(r3_AccessibilityRecordCompat.b));
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
        AccessibilityRecordCompat r5_AccessibilityRecordCompat = (AccessibilityRecordCompat) r5_Object;
        if (this.b == null) {
            return r5_AccessibilityRecordCompat.b == null;
        } else {
            if (this.b.equals(r5_AccessibilityRecordCompat.b)) {
                return true;
            }
            return false;
        }
    }

    public int getAddedCount() {
        return a.getAddedCount(this.b);
    }

    public CharSequence getBeforeText() {
        return a.getBeforeText(this.b);
    }

    public CharSequence getClassName() {
        return a.getClassName(this.b);
    }

    public CharSequence getContentDescription() {
        return a.getContentDescription(this.b);
    }

    public int getCurrentItemIndex() {
        return a.getCurrentItemIndex(this.b);
    }

    public int getFromIndex() {
        return a.getFromIndex(this.b);
    }

    public Object getImpl() {
        return this.b;
    }

    public int getItemCount() {
        return a.getItemCount(this.b);
    }

    public int getMaxScrollX() {
        return a.getMaxScrollX(this.b);
    }

    public int getMaxScrollY() {
        return a.getMaxScrollY(this.b);
    }

    public Parcelable getParcelableData() {
        return a.getParcelableData(this.b);
    }

    public int getRemovedCount() {
        return a.getRemovedCount(this.b);
    }

    public int getScrollX() {
        return a.getScrollX(this.b);
    }

    public int getScrollY() {
        return a.getScrollY(this.b);
    }

    public AccessibilityNodeInfoCompat getSource() {
        return a.getSource(this.b);
    }

    public List<CharSequence> getText() {
        return a.getText(this.b);
    }

    public int getToIndex() {
        return a.getToIndex(this.b);
    }

    public int getWindowId() {
        return a.getWindowId(this.b);
    }

    public int hashCode() {
        return this.b == null ? 0 : this.b.hashCode();
    }

    public boolean isChecked() {
        return a.isChecked(this.b);
    }

    public boolean isEnabled() {
        return a.isEnabled(this.b);
    }

    public boolean isFullScreen() {
        return a.isFullScreen(this.b);
    }

    public boolean isPassword() {
        return a.isPassword(this.b);
    }

    public boolean isScrollable() {
        return a.isScrollable(this.b);
    }

    public void recycle() {
        a.recycle(this.b);
    }

    public void setAddedCount(int r3i) {
        a.setAddedCount(this.b, r3i);
    }

    public void setBeforeText(CharSequence r3_CharSequence) {
        a.setBeforeText(this.b, r3_CharSequence);
    }

    public void setChecked(boolean r3z) {
        a.setChecked(this.b, r3z);
    }

    public void setClassName(CharSequence r3_CharSequence) {
        a.setClassName(this.b, r3_CharSequence);
    }

    public void setContentDescription(CharSequence r3_CharSequence) {
        a.setContentDescription(this.b, r3_CharSequence);
    }

    public void setCurrentItemIndex(int r3i) {
        a.setCurrentItemIndex(this.b, r3i);
    }

    public void setEnabled(boolean r3z) {
        a.setEnabled(this.b, r3z);
    }

    public void setFromIndex(int r3i) {
        a.setFromIndex(this.b, r3i);
    }

    public void setFullScreen(boolean r3z) {
        a.setFullScreen(this.b, r3z);
    }

    public void setItemCount(int r3i) {
        a.setItemCount(this.b, r3i);
    }

    public void setMaxScrollX(int r3i) {
        a.setMaxScrollX(this.b, r3i);
    }

    public void setMaxScrollY(int r3i) {
        a.setMaxScrollY(this.b, r3i);
    }

    public void setParcelableData(Parcelable r3_Parcelable) {
        a.setParcelableData(this.b, r3_Parcelable);
    }

    public void setPassword(boolean r3z) {
        a.setPassword(this.b, r3z);
    }

    public void setRemovedCount(int r3i) {
        a.setRemovedCount(this.b, r3i);
    }

    public void setScrollX(int r3i) {
        a.setScrollX(this.b, r3i);
    }

    public void setScrollY(int r3i) {
        a.setScrollY(this.b, r3i);
    }

    public void setScrollable(boolean r3z) {
        a.setScrollable(this.b, r3z);
    }

    public void setSource(View r3_View) {
        a.setSource(this.b, r3_View);
    }

    public void setSource(View r3_View, int r4i) {
        a.setSource(this.b, r3_View, r4i);
    }

    public void setToIndex(int r3i) {
        a.setToIndex(this.b, r3i);
    }
}