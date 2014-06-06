package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;
import android.widget.AdapterView;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

abstract class AdapterViewICS<T extends Adapter> extends ViewGroup {
    public static final int INVALID_POSITION = -1;
    public static final long INVALID_ROW_ID = -9223372036854775808L;
    int A;
    int B;
    int C;
    long D;
    boolean E;
    private int a;
    private View b;
    private boolean c;
    private boolean d;
    private c e;
    int k;
    int l;
    int m;
    long n;
    long o;
    boolean p;
    int q;
    boolean r;
    OnItemSelectedListener s;
    OnItemClickListener t;
    OnItemLongClickListener u;
    boolean v;
    int w;
    long x;
    int y;
    long z;


    public static class AdapterContextMenuInfo implements ContextMenuInfo {
        public long id;
        public int position;
        public View targetView;

        public AdapterContextMenuInfo(View r1_View, int r2i, long r3j) {
            this.targetView = r1_View;
            this.position = r2i;
            this.id = r3j;
        }
    }

    public static interface OnItemClickListener {
        public void onItemClick(AdapterViewICS<?> r1_AdapterViewICS_, View r2_View, int r3i, long r4j);
    }

    public static interface OnItemLongClickListener {
        public boolean onItemLongClick(AdapterViewICS<?> r1_AdapterViewICS_, View r2_View, int r3i, long r4j);
    }

    public static interface OnItemSelectedListener {
        public void onItemSelected(AdapterViewICS<?> r1_AdapterViewICS_, View r2_View, int r3i, long r4j);

        public void onNothingSelected(AdapterViewICS<?> r1_AdapterViewICS_);
    }

    class a extends DataSetObserver {
        private Parcelable b;

        a() {
            this.b = null;
        }

        public void clearSavedState() {
            this.b = null;
        }

        public void onChanged() {
            AdapterViewICS.this.v = true;
            AdapterViewICS.this.B = AdapterViewICS.this.A;
            AdapterViewICS.this.A = AdapterViewICS.this.getAdapter().getCount();
            if ((!AdapterViewICS.this.getAdapter().hasStableIds()) || this.b == null || AdapterViewICS.this.B != 0 || AdapterViewICS.this.A <= 0) {
                AdapterViewICS.this.i();
            } else {
                AdapterViewICS.this.onRestoreInstanceState(this.b);
                this.b = null;
            }
            AdapterViewICS.this.d();
            AdapterViewICS.this.requestLayout();
        }

        public void onInvalidated() {
            AdapterViewICS.this.v = true;
            if (AdapterViewICS.this.getAdapter().hasStableIds()) {
                this.b = AdapterViewICS.this.onSaveInstanceState();
            }
            AdapterViewICS.this.B = AdapterViewICS.this.A;
            AdapterViewICS.this.A = 0;
            AdapterViewICS.this.y = -1;
            AdapterViewICS.this.z = -9223372036854775808L;
            AdapterViewICS.this.w = -1;
            AdapterViewICS.this.x = -9223372036854775808L;
            AdapterViewICS.this.p = false;
            AdapterViewICS.this.d();
            AdapterViewICS.this.requestLayout();
        }
    }

    class b implements android.widget.AdapterView.OnItemClickListener {
        private final android.support.v7.internal.widget.AdapterViewICS.OnItemClickListener b;

        public b(android.support.v7.internal.widget.AdapterViewICS.OnItemClickListener r2_android_support_v7_internal_widget_AdapterViewICS_OnItemClickListener) {
            this.b = r2_android_support_v7_internal_widget_AdapterViewICS_OnItemClickListener;
        }

        public void onItemClick(AdapterView<?> r7_AdapterView_, View r8_View, int r9i, long r10j) {
            this.b.onItemClick(AdapterViewICS.this, r8_View, r9i, r10j);
        }
    }

    private class c implements Runnable {
        private c() {
        }

        public void run() {
            if (AdapterViewICS.this.v) {
                if (AdapterViewICS.this.getAdapter() != null) {
                    AdapterViewICS.this.post(this);
                }
            } else {
                AdapterViewICS.this.a();
            }
        }
    }

    AdapterViewICS(Context r5_Context, AttributeSet r6_AttributeSet, int r7i) {
        super(r5_Context, r6_AttributeSet, r7i);
        this.k = 0;
        this.n = -9223372036854775808L;
        this.p = false;
        this.r = false;
        this.w = -1;
        this.x = -9223372036854775808L;
        this.y = -1;
        this.z = -9223372036854775808L;
        this.C = -1;
        this.D = -9223372036854775808L;
        this.E = false;
    }

    private void a() {
        if (this.s == null) {
        } else {
            int r3i = getSelectedItemPosition();
            if (r3i >= 0) {
                this.s.onItemSelected(this, getSelectedView(), r3i, getAdapter().getItemId(r3i));
            } else {
                this.s.onNothingSelected(this);
            }
        }
    }

    private void a(boolean r7z) {
        if (c()) {
            r7z = false;
        }
        if (r7z) {
            if (this.b != null) {
                this.b.setVisibility(0);
                setVisibility(Base64.DONT_BREAK_LINES);
            } else {
                setVisibility(0);
            }
            if (this.v) {
                onLayout(false, getLeft(), getTop(), getRight(), getBottom());
            }
        } else {
            if (this.b != null) {
                this.b.setVisibility(Base64.DONT_BREAK_LINES);
            }
            setVisibility(0);
        }
    }

    void a(int r3i) {
        this.y = r3i;
        this.z = getItemIdAtPosition(r3i);
    }

    public void addView(View r3_View) {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    public void addView(View r3_View, int r4i) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    public void addView(View r3_View, int r4i, LayoutParams r5_LayoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }

    public void addView(View r3_View, LayoutParams r4_LayoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
    }

    void b(int r3i) {
        this.w = r3i;
        this.x = getItemIdAtPosition(r3i);
        if (this.p && this.q == 0 && r3i >= 0) {
            this.m = r3i;
            this.n = this.x;
        }
    }

    int c(int r1i, boolean r2z) {
        return r1i;
    }

    boolean c() {
        return false;
    }

    protected boolean canAnimate() {
        return super.canAnimate() && this.A > 0;
    }

    void d() {
        boolean r0z;
        boolean r2z = false;
        Adapter r4_Adapter = getAdapter();
        int r0i = (r4_Adapter == null || r4_Adapter.getCount() == 0) ? 1 : 0;
        int r3i = (r0i == 0 || c()) ? 1 : 0;
        r0z = r3i != 0 && this.d;
        super.setFocusableInTouchMode(r0z);
        r0z = r3i != 0 && this.c;
        super.setFocusable(r0z);
        if (this.b != null) {
            if (r4_Adapter == null || r4_Adapter.isEmpty()) {
                r2z = true;
                a(r2z);
            } else {
                a(r2z);
            }
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r3_AccessibilityEvent) {
        View r0_View = getSelectedView();
        return r0_View != null && r0_View.getVisibility() == 0 && r0_View.dispatchPopulateAccessibilityEvent(r3_AccessibilityEvent);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> r1_SparseArray_Parcelable) {
        dispatchThawSelfOnly(r1_SparseArray_Parcelable);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> r1_SparseArray_Parcelable) {
        dispatchFreezeSelfOnly(r1_SparseArray_Parcelable);
    }

    void e() {
        if (this.s != null) {
            if (this.r || this.E) {
                if (this.e == null) {
                    this.e = new c(null);
                }
                post(this.e);
            } else {
                a();
            }
        }
        if (this.y == -1 || (!isShown()) || isInTouchMode()) {
        } else {
            sendAccessibilityEvent(XListViewFooter.STATE_NODATA);
        }
    }

    void f() {
        int r0i;
        int r4i = this.A;
        if (r4i > 0) {
            int r3i;
            if (this.p) {
                this.p = false;
                r0i = h();
                if (r0i < 0 || c(r0i, true) != r0i) {
                    r3i = 0;
                } else {
                    b(r0i);
                    r3i = 1;
                }
            } else {
                r3i = 0;
            }
            if (r3i == 0) {
                r0i = getSelectedItemPosition();
                if (r0i >= r4i) {
                    r0i = r4i - 1;
                }
                if (r0i < 0) {
                    r0i = 0;
                }
                r4i = c(r0i, true);
                r0i = r4i < 0 ? c(r0i, false) : r4i;
                if (r0i >= 0) {
                    b(r0i);
                    g();
                    r0i = 1;
                }
            }
            r0i = r3i;
        } else {
            r0i = 0;
        }
        if (r0i == 0) {
            this.y = -1;
            this.z = -9223372036854775808L;
            this.w = -1;
            this.x = -9223372036854775808L;
            this.p = false;
            g();
        }
    }

    void g() {
        if (!(this.y == this.C && this.z == this.D)) {
            e();
            this.C = this.y;
            this.D = this.z;
        }
    }

    public abstract T getAdapter();

    public int getCount() {
        return this.A;
    }

    public View getEmptyView() {
        return this.b;
    }

    public int getFirstVisiblePosition() {
        return this.k;
    }

    public Object getItemAtPosition(int r2i) {
        Adapter r0_Adapter = getAdapter();
        return (r0_Adapter == null || r2i < 0) ? null : r0_Adapter.getItem(r2i);
    }

    public long getItemIdAtPosition(int r3i) {
        Adapter r0_Adapter = getAdapter();
        return (r0_Adapter == null || r3i < 0) ? INVALID_ROW_ID : r0_Adapter.getItemId(r3i);
    }

    public int getLastVisiblePosition() {
        return this.k + getChildCount() - 1;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.t;
    }

    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.u;
    }

    public final OnItemSelectedListener getOnItemSelectedListener() {
        return this.s;
    }

    public int getPositionForView(View r5_View) {
        while (true) {
            try {
                View r0_View = (View) r5_View.getParent();
                if (r0_View.equals(this)) {
                    int r2i = getChildCount();
                    int r0i = 0;
                    while (r0i < r2i) {
                        if (getChildAt(r0i).equals(r5_View)) {
                            return r0i + this.k;
                        }
                        r0i++;
                    }
                    return -1;
                } else {
                    r5_View = r0_View;
                }
            } catch (ClassCastException e) {
                return -1;
            }
        }
    }

    public Object getSelectedItem() {
        Adapter r0_Adapter = getAdapter();
        int r1i = getSelectedItemPosition();
        return (r0_Adapter == null || r0_Adapter.getCount() <= 0 || r1i < 0) ? null : r0_Adapter.getItem(r1i);
    }

    public long getSelectedItemId() {
        return this.x;
    }

    public int getSelectedItemPosition() {
        return this.w;
    }

    public abstract View getSelectedView();

    int h() {
        int r9i = this.A;
        if (r9i == 0) {
            return -1;
        }
        long r10j = this.n;
        int r0i = this.m;
        if (r10j == -9223372036854775808L) {
            return -1;
        }
        r0i = Math.min(r9i - 1, Math.max(0, r0i));
        long r12j = SystemClock.uptimeMillis() + 100;
        Adapter r14_Adapter = getAdapter();
        if (r14_Adapter == null) {
            return -1;
        }
        int r3i = r0i;
        int r4i = r0i;
        int r5i = r0i;
        r0i = 0;
        while (SystemClock.uptimeMillis() <= r12j) {
            if (r14_Adapter.getItemId(r5i) == r10j) {
                return r5i;
            }
            int r8i;
            int r7i;
            r8i = r3i == r9i + -1 ? 1 : 0;
            r7i = r4i == 0 ? 1 : 0;
            if (r8i != 0) {
                if (r7i != 0) {
                    break;
                }
            }
            if (r7i == 0) {
                if (r0i == 0 || r8i != 0) {
                    if (r8i == 0) {
                        if (!(r0i == 0 && r7i == 0)) {
                        }
                    }
                    r0i = r4i - 1;
                    r4i = r0i;
                    r5i = r0i;
                    r0i = 1;
                } else {
                    r0i = r3i + 1;
                    r3i = r0i;
                    r5i = r0i;
                    r0i = 0;
                }
            } else {
                r0i = r3i + 1;
                r3i = r0i;
                r5i = r0i;
                r0i = 0;
            }
        }
        return -1;
    }

    void i() {
        if (getChildCount() > 0) {
            this.p = true;
            this.o = (long) this.a;
            View r0_View;
            if (this.y >= 0) {
                r0_View = getChildAt(this.y - this.k);
                this.n = this.x;
                this.m = this.w;
                if (r0_View != null) {
                    this.l = r0_View.getTop();
                }
                this.q = 0;
            } else {
                r0_View = getChildAt(0);
                Adapter r1_Adapter = getAdapter();
                if (this.k < 0 || this.k >= r1_Adapter.getCount()) {
                    this.n = -1;
                    this.m = this.k;
                } else {
                    this.n = r1_Adapter.getItemId(this.k);
                    this.m = this.k;
                }
                if (r0_View != null) {
                    this.l = r0_View.getTop();
                }
                this.q = 1;
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.e);
    }

    protected void onLayout(boolean r2z, int r3i, int r4i, int r5i, int r6i) {
        this.a = getHeight();
    }

    public boolean performItemClick(View r8_View, int r9i, long r10j) {
        if (this.t == null) {
            return false;
        }
        playSoundEffect(0);
        if (r8_View != null) {
            r8_View.sendAccessibilityEvent(1);
        }
        this.t.onItemClick(this, r8_View, r9i, r10j);
        return true;
    }

    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    public void removeView(View r3_View) {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    public void removeViewAt(int r3i) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    public abstract void setAdapter(T r1_T);

    public void setEmptyView(View r2_View) {
        boolean r0z;
        this.b = r2_View;
        Adapter r0_Adapter = getAdapter();
        r0z = r0_Adapter == null || r0_Adapter.isEmpty();
        a(r0z);
    }

    public void setFocusable(boolean r4z) {
        int r0i;
        boolean r2z = true;
        Adapter r0_Adapter = getAdapter();
        r0i = (r0_Adapter == null || r0_Adapter.getCount() == 0) ? 1 : 0;
        this.c = r4z;
        if (!r4z) {
            this.d = false;
        }
        if (r4z) {
            if (r0i == 0 || c()) {
                super.setFocusable(r2z);
            } else {
                r2z = false;
            }
        } else {
            r2z = false;
        }
        super.setFocusable(r2z);
    }

    public void setFocusableInTouchMode(boolean r4z) {
        int r0i;
        boolean r2z = true;
        Adapter r0_Adapter = getAdapter();
        r0i = (r0_Adapter == null || r0_Adapter.getCount() == 0) ? 1 : 0;
        this.d = r4z;
        if (r4z) {
            this.c = true;
        }
        if (r4z) {
            if (r0i == 0 || c()) {
                super.setFocusableInTouchMode(r2z);
            } else {
                r2z = false;
            }
        } else {
            r2z = false;
        }
        super.setFocusableInTouchMode(r2z);
    }

    public void setOnClickListener(OnClickListener r3_OnClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }

    public void setOnItemClickListener(OnItemClickListener r1_OnItemClickListener) {
        this.t = r1_OnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener r2_OnItemLongClickListener) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.u = r2_OnItemLongClickListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener r1_OnItemSelectedListener) {
        this.s = r1_OnItemSelectedListener;
    }

    public abstract void setSelection(int r1i);
}