package android.support.v4.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.androidquery.util.Constants;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.ProfileHeaderListView;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    public static final int INVALID_ID = -2147483648;
    private static final String b;
    private final Rect c;
    private final Rect d;
    private final Rect e;
    private final int[] f;
    private final AccessibilityManager g;
    private final View h;
    private a i;
    private int j;
    private int k;


    private class a extends AccessibilityNodeProviderCompat {
        private a() {
        }

        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int r2i) {
            return ExploreByTouchHelper.this.c(r2i);
        }

        public boolean performAction(int r2i, int r3i, Bundle r4_Bundle) {
            return ExploreByTouchHelper.this.b(r2i, r3i, r4_Bundle);
        }
    }

    static {
        b = View.class.getName();
    }

    public ExploreByTouchHelper(View r3_View) {
        this.c = new Rect();
        this.d = new Rect();
        this.e = new Rect();
        this.f = new int[2];
        this.j = -2147483648;
        this.k = -2147483648;
        if (r3_View == null) {
            throw new IllegalArgumentException("View may not be null");
        } else {
            this.h = r3_View;
            this.g = (AccessibilityManager) r3_View.getContext().getSystemService("accessibility");
        }
    }

    private AccessibilityEvent a(int r2i, int r3i) {
        switch (r2i) {
            case ProfileHeaderListView.INVALID_TAB_ID:
                return b(r3i);
        }
        return b(r2i, r3i);
    }

    private void a(int r3i) {
        if (this.k == r3i) {
        } else {
            int r0i = this.k;
            this.k = r3i;
            sendEventForVirtualView(r3i, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            sendEventForVirtualView(r0i, AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        }
    }

    private boolean a(int r2i, Bundle r3_Bundle) {
        return ViewCompat.performAccessibilityAction(this.h, r2i, r3_Bundle);
    }

    private boolean a(Rect r5_Rect) {
        if (r5_Rect == null || r5_Rect.isEmpty()) {
            return false;
        }
        if (this.h.getWindowVisibility() != 0) {
            return false;
        }
        ViewParent r0_ViewParent = this.h.getParent();
        while (r0_ViewParent instanceof View) {
            View r0_View = (View) r0_ViewParent;
            if (ViewCompat.getAlpha(r0_View) <= 0.0f || r0_View.getVisibility() != 0) {
                return false;
            }
            r0_ViewParent = r0_View.getParent();
        }
        if (r0_ViewParent == null) {
            return false;
        }
        if (this.h.getLocalVisibleRect(this.e)) {
            return r5_Rect.intersect(this.e);
        }
        return false;
    }

    private AccessibilityNodeInfoCompat b() {
        AccessibilityNodeInfoCompat r1_AccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(this.h);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.h, r1_AccessibilityNodeInfoCompat);
        List r0_List = new LinkedList();
        a(r0_List);
        Iterator r2_Iterator = r0_List.iterator();
        while (r2_Iterator.hasNext()) {
            r1_AccessibilityNodeInfoCompat.addChild(this.h, ((Integer) r2_Iterator.next()).intValue());
        }
        return r1_AccessibilityNodeInfoCompat;
    }

    private AccessibilityEvent b(int r3i) {
        AccessibilityEvent r0_AccessibilityEvent = AccessibilityEvent.obtain(r3i);
        ViewCompat.onInitializeAccessibilityEvent(this.h, r0_AccessibilityEvent);
        return r0_AccessibilityEvent;
    }

    private AccessibilityEvent b(int r4i, int r5i) {
        AccessibilityEvent r0_AccessibilityEvent = AccessibilityEvent.obtain(r5i);
        r0_AccessibilityEvent.setEnabled(true);
        r0_AccessibilityEvent.setClassName(b);
        a(r4i, r0_AccessibilityEvent);
        if (r0_AccessibilityEvent.getText().isEmpty() && r0_AccessibilityEvent.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        } else {
            r0_AccessibilityEvent.setPackageName(this.h.getContext().getPackageName());
            AccessibilityEventCompat.asRecord(r0_AccessibilityEvent).setSource(this.h, r4i);
            return r0_AccessibilityEvent;
        }
    }

    private boolean b(int r2i, int r3i, Bundle r4_Bundle) {
        switch (r2i) {
            case ProfileHeaderListView.INVALID_TAB_ID:
                return a(r3i, r4_Bundle);
        }
        return c(r2i, r3i, r4_Bundle);
    }

    private AccessibilityNodeInfoCompat c(int r2i) {
        switch (r2i) {
            case ProfileHeaderListView.INVALID_TAB_ID:
                return b();
        }
        return d(r2i);
    }

    private boolean c(int r2i, int r3i, Bundle r4_Bundle) {
        switch (r3i) {
            case RContact.MM_CONTACTFLAG_FAVOURCONTACT:
            case AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS:
                return d(r2i, r3i, r4_Bundle);
        }
        return a(r2i, r3i, r4_Bundle);
    }

    private AccessibilityNodeInfoCompat d(int r6i) {
        boolean r4z = false;
        boolean r3z = true;
        AccessibilityNodeInfoCompat r0_AccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain();
        r0_AccessibilityNodeInfoCompat.setEnabled(true);
        r0_AccessibilityNodeInfoCompat.setClassName(b);
        a(r6i, r0_AccessibilityNodeInfoCompat);
        if (r0_AccessibilityNodeInfoCompat.getText() == null && r0_AccessibilityNodeInfoCompat.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        } else {
            r0_AccessibilityNodeInfoCompat.getBoundsInParent(this.d);
            if (this.d.isEmpty()) {
                throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
            } else {
                int r1i = r0_AccessibilityNodeInfoCompat.getActions();
                if ((r1i & 64) != 0) {
                    throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
                } else if ((r1i & 128) != 0) {
                    throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
                } else {
                    r0_AccessibilityNodeInfoCompat.setPackageName(this.h.getContext().getPackageName());
                    r0_AccessibilityNodeInfoCompat.setSource(this.h, r6i);
                    r0_AccessibilityNodeInfoCompat.setParent(this.h);
                    if (this.j == r6i) {
                        r0_AccessibilityNodeInfoCompat.setAccessibilityFocused(true);
                        r0_AccessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                    } else {
                        r0_AccessibilityNodeInfoCompat.setAccessibilityFocused(false);
                        r0_AccessibilityNodeInfoCompat.addAction(RContact.MM_CONTACTFLAG_FAVOURCONTACT);
                    }
                    if (a(this.d)) {
                        r0_AccessibilityNodeInfoCompat.setVisibleToUser(true);
                        r0_AccessibilityNodeInfoCompat.setBoundsInParent(this.d);
                    }
                    this.h.getLocationOnScreen(this.f);
                    r1i = this.f[r4z];
                    int r2i = this.f[r3z];
                    this.c.set(this.d);
                    this.c.offset(r1i, r2i);
                    r0_AccessibilityNodeInfoCompat.setBoundsInScreen(this.c);
                    return r0_AccessibilityNodeInfoCompat;
                }
            }
        }
    }

    private boolean d(int r2i, int r3i, Bundle r4_Bundle) {
        switch (r3i) {
            case RContact.MM_CONTACTFLAG_FAVOURCONTACT:
                return f(r2i);
            case AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS:
                return g(r2i);
        }
        return false;
    }

    private boolean e(int r2i) {
        return this.j == r2i;
    }

    private boolean f(int r3i) {
        if (!this.g.isEnabled() || !AccessibilityManagerCompat.isTouchExplorationEnabled(this.g) || e(r3i)) {
            return false;
        }
        this.j = r3i;
        this.h.invalidate();
        sendEventForVirtualView(r3i, AccessibilityNodeInfoCompat.ACTION_PASTE);
        return true;
    }

    private boolean g(int r2i) {
        if (!e(r2i)) {
            return false;
        }
        this.j = -2147483648;
        this.h.invalidate();
        sendEventForVirtualView(r2i, Constants.FLAG_ACTIVITY_NO_ANIMATION);
        return true;
    }

    protected abstract int a(float r1f, float r2f);

    protected abstract void a(int r1i, AccessibilityNodeInfoCompat r2_AccessibilityNodeInfoCompat);

    protected abstract void a(int r1i, AccessibilityEvent r2_AccessibilityEvent);

    protected abstract void a(List<Integer> r1_List_Integer);

    protected abstract boolean a(int r1i, int r2i, Bundle r3_Bundle);

    public boolean dispatchHoverEvent(MotionEvent r6_MotionEvent) {
        boolean r0z = true;
        if (!this.g.isEnabled() || !AccessibilityManagerCompat.isTouchExplorationEnabled(this.g)) {
            return false;
        }
        switch (r6_MotionEvent.getAction()) {
            case ShareUtils.SHARE_COLLECT:
            case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                int r2i = a(r6_MotionEvent.getX(), r6_MotionEvent.getY());
                a(r2i);
                if (r2i != -2147483648) {
                    return r0z;
                }
                r0z = false;
                return r0z;
            case REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
                if (this.j == -2147483648) {
                    return false;
                }
                a((int)INVALID_ID);
                return true;
        }
        return false;
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View r3_View) {
        if (this.i == null) {
            this.i = new a(null);
        }
        return this.i;
    }

    public int getFocusedVirtualView() {
        return this.j;
    }

    public void invalidateRoot() {
        invalidateVirtualView(-1);
    }

    public void invalidateVirtualView(int r2i) {
        sendEventForVirtualView(r2i, LVBuffer.MAX_STRING_LENGTH);
    }

    public boolean sendEventForVirtualView(int r4i, int r5i) {
        if (r4i == -2147483648 || (!this.g.isEnabled())) {
            return false;
        }
        ViewParent r1_ViewParent = this.h.getParent();
        if (r1_ViewParent == null) {
            return false;
        }
        return ViewParentCompat.requestSendAccessibilityEvent(r1_ViewParent, this.h, a(r4i, r5i));
    }
}