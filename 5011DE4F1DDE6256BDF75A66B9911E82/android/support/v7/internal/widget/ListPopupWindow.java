package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.util.TimeUtils;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import com.baidu.location.BDLocation;
import com.qiubai.library.adview.util.AdViewUtil;
import java.util.Locale;
import qsbk.app.activity.OneProfileActivity;

public class ListPopupWindow {
    public static final int FILL_PARENT = -1;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    public static final int WRAP_CONTENT = -2;
    private boolean A;
    int a;
    private Context b;
    private PopupWindow c;
    private ListAdapter d;
    private a e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private View m;
    private int n;
    private DataSetObserver o;
    private View p;
    private Drawable q;
    private OnItemClickListener r;
    private OnItemSelectedListener s;
    private final f t;
    private final e u;
    private final d v;
    private final b w;
    private Runnable x;
    private Handler y;
    private Rect z;

    private static class a extends ListView {
        public static final int INVALID_POSITION = -1;
        private boolean a;
        private boolean b;

        public a(Context r3_Context, boolean r4z) {
            super(r3_Context, null, R.attr.dropDownListViewStyle);
            this.b = r4z;
            setCacheColorHint(POSITION_PROMPT_ABOVE);
        }

        private int a_(int r5i, boolean r6z) {
            ListAdapter r1_ListAdapter = getAdapter();
            if (r1_ListAdapter == null || isInTouchMode()) {
                return -1;
            }
            int r2i = r1_ListAdapter.getCount();
            if (getAdapter().areAllItemsEnabled()) {
                return (r5i < 0 || r5i >= r2i) ? -1 : r5i;
            } else {
                if (r6z) {
                    r5i = Math.max(POSITION_PROMPT_ABOVE, r5i);
                    while (r5i < r2i && !r1_ListAdapter.isEnabled(r5i)) {
                        r5i++;
                    }
                } else {
                    r5i = Math.min(r5i, r2i - 1);
                    while (r5i >= 0 && !r1_ListAdapter.isEnabled(r5i)) {
                        r5i--;
                    }
                }
                return (r5i < 0 || r5i >= r2i) ? -1 : r5i;
            }
        }

        final int a_(int r13i, int r14i, int r15i, int r16i, int r17i) {
            int r2i = getListPaddingTop();
            int r3i = getListPaddingBottom();
            getListPaddingLeft();
            getListPaddingRight();
            int r1i = getDividerHeight();
            Drawable r4_Drawable = getDivider();
            ListAdapter r8_ListAdapter = getAdapter();
            if (r8_ListAdapter == null) {
                return r2i + r3i;
            }
            int r4i;
            View r6_View;
            int r5i;
            int r9i;
            int r7i;
            r3i += r2i;
            if (r1i <= 0 || r4_Drawable == null) {
                r1i = POSITION_PROMPT_ABOVE;
                r4i = POSITION_PROMPT_ABOVE;
                r6_View = null;
                r5i = POSITION_PROMPT_ABOVE;
                r9i = r8_ListAdapter.getCount();
                r7i = 0;
            } else {
                r4i = POSITION_PROMPT_ABOVE;
                r6_View = null;
                r5i = POSITION_PROMPT_ABOVE;
                r9i = r8_ListAdapter.getCount();
                r7i = 0;
            }
            while (r7i < r9i) {
                View r2_View;
                r2i = r8_ListAdapter.getItemViewType(r7i);
                if (r2i != r5i) {
                    r2_View = null;
                    r5i = r2i;
                } else {
                    r2_View = r6_View;
                }
                r6_View = r8_ListAdapter.getView(r7i, r2_View, this);
                r2i = r6_View.getLayoutParams().height;
                r6_View.measure(r13i, r2i > 0 ? MeasureSpec.makeMeasureSpec(r2i, 1073741824) : MeasureSpec.makeMeasureSpec(POSITION_PROMPT_ABOVE, POSITION_PROMPT_ABOVE));
                r2i = (r7i > 0 ? r3i + r1i : r3i) + r6_View.getMeasuredHeight();
                if (r2i >= r16i) {
                    return (r17i < 0 || r7i <= r17i || r4i <= 0 || r2i == r16i) ? r16i : r4i;
                } else if (r17i < 0 || r7i < r17i) {
                    r7i++;
                    r3i = r2i;
                } else {
                    r4i = r2i;
                    r7i++;
                    r3i = r2i;
                }
            }
            return r3i;
        }

        public boolean hasFocus() {
            return this.b || super.hasFocus();
        }

        public boolean hasWindowFocus() {
            return this.b || super.hasWindowFocus();
        }

        public boolean isFocused() {
            return this.b || super.isFocused();
        }

        public boolean isInTouchMode() {
            return (this.b && this.a) || super.isInTouchMode();
        }
    }

    private class b implements Runnable {
        private b() {
        }

        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    private class c extends DataSetObserver {
        private c() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    private class d implements OnScrollListener {
        private d() {
        }

        public void onScroll(AbsListView r1_AbsListView, int r2i, int r3i, int r4i) {
        }

        public void onScrollStateChanged(AbsListView r3_AbsListView, int r4i) {
            if (r4i != 1 || ListPopupWindow.this.isInputMethodNotNeeded() || ListPopupWindow.this.c.getContentView() == null) {
            } else {
                ListPopupWindow.this.y.removeCallbacks(ListPopupWindow.this.t);
                ListPopupWindow.this.t.run();
            }
        }
    }

    private class e implements OnTouchListener {
        private e() {
        }

        public boolean onTouch(View r5_View, MotionEvent r6_MotionEvent) {
            int r0i = r6_MotionEvent.getAction();
            int r1i = (int) r6_MotionEvent.getX();
            int r2i = (int) r6_MotionEvent.getY();
            if (r0i != 0 || ListPopupWindow.this.c == null || (!ListPopupWindow.this.c.isShowing()) || r1i < 0 || r1i >= ListPopupWindow.this.c.getWidth() || r2i < 0 || r2i >= ListPopupWindow.this.c.getHeight()) {
                if (r0i == 1) {
                    ListPopupWindow.this.y.removeCallbacks(ListPopupWindow.this.t);
                }
            } else {
                ListPopupWindow.this.y.postDelayed(ListPopupWindow.this.t, 250);
            }
            return false;
        }
    }

    private class f implements Runnable {
        private f() {
        }

        public void run() {
            if (ListPopupWindow.this.e == null || ListPopupWindow.this.e.getCount() <= ListPopupWindow.this.e.getChildCount() || ListPopupWindow.this.e.getChildCount() > ListPopupWindow.this) {
            } else {
                ListPopupWindow.this.c.setInputMethodMode(INPUT_METHOD_NOT_NEEDED);
                ListPopupWindow.this.show();
            }
        }
    }

    public ListPopupWindow(Context r3_Context) {
        this(r3_Context, null, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context r4_Context, AttributeSet r5_AttributeSet, int r6i) {
        this.f = -2;
        this.g = -2;
        this.k = false;
        this.l = false;
        this.a = 2147483647;
        this.n = 0;
        this.t = new f(null);
        this.u = new e(null);
        this.v = new d(null);
        this.w = new b(null);
        this.y = new Handler();
        this.z = new Rect();
        this.b = r4_Context;
        this.c = new PopupWindow(r4_Context, r5_AttributeSet, r6i);
        this.c.setInputMethodMode(POSITION_PROMPT_BELOW);
        Locale r0_Locale = this.b.getResources().getConfiguration().locale;
    }

    private void a() {
        if (this.m != null) {
            ViewParent r0_ViewParent = this.m.getParent();
            if (r0_ViewParent instanceof ViewGroup) {
                ((ViewGroup) r0_ViewParent).removeView(this.m);
            }
        }
    }

    private int b() {
        int r0i;
        int r6i;
        int r7i;
        boolean r1z = true;
        View r4_View;
        LayoutParams r0_LayoutParams;
        if (this.e == null) {
            Context r5_Context = this.b;
            this.x = new l(this);
            this.e = new a(r5_Context, !(this.A));
            if (this.q != null) {
                this.e.setSelector(this.q);
            }
            this.e.setAdapter(this.d);
            this.e.setOnItemClickListener(this.r);
            this.e.setFocusable(true);
            this.e.setFocusableInTouchMode(true);
            this.e.setOnItemSelectedListener(new m(this));
            this.e.setOnScrollListener(this.v);
            if (this.s != null) {
                this.e.setOnItemSelectedListener(this.s);
            }
            View r0_View = this.e;
            View r6_View = this.m;
            if (r6_View != null) {
                r4_View = new LinearLayout(r5_Context);
                r4_View.setOrientation(POSITION_PROMPT_BELOW);
                ViewGroup.LayoutParams r5_ViewGroup_LayoutParams = new LayoutParams(-1, 0, 1.0f);
                switch (this.n) {
                    case POSITION_PROMPT_ABOVE:
                        r4_View.addView(r6_View);
                        r4_View.addView(r0_View, r5_ViewGroup_LayoutParams);
                        r6_View.measure(MeasureSpec.makeMeasureSpec(this.g, ExploreByTouchHelper.INVALID_ID), POSITION_PROMPT_ABOVE);
                        r0_LayoutParams = (LayoutParams) r6_View.getLayoutParams();
                        r0i = r0_LayoutParams.bottomMargin + r6_View.getMeasuredHeight() + r0_LayoutParams.topMargin;
                        break;
                    case POSITION_PROMPT_BELOW:
                        r4_View.addView(r0_View, r5_ViewGroup_LayoutParams);
                        r4_View.addView(r6_View);
                        r6_View.measure(MeasureSpec.makeMeasureSpec(this.g, ExploreByTouchHelper.INVALID_ID), POSITION_PROMPT_ABOVE);
                        r0_LayoutParams = (LayoutParams) r6_View.getLayoutParams();
                        r0i = r0_LayoutParams.bottomMargin + r6_View.getMeasuredHeight() + r0_LayoutParams.topMargin;
                        break;
                }
                Log.e("ListPopupWindow", "Invalid hint position " + this.n);
                r6_View.measure(MeasureSpec.makeMeasureSpec(this.g, ExploreByTouchHelper.INVALID_ID), POSITION_PROMPT_ABOVE);
                r0_LayoutParams = (LayoutParams) r6_View.getLayoutParams();
                r0i = r0_LayoutParams.bottomMargin + r6_View.getMeasuredHeight() + r0_LayoutParams.topMargin;
            } else {
                r4_View = r0_View;
                r0i = 0;
            }
            this.c.setContentView(r4_View);
            r6i = r0i;
        } else {
            ViewGroup r0_ViewGroup = (ViewGroup) this.c.getContentView();
            r4_View = this.m;
            if (r4_View != null) {
                r0_LayoutParams = (LayoutParams) r4_View.getLayoutParams();
                r6i = r0_LayoutParams.bottomMargin + r4_View.getMeasuredHeight() + r0_LayoutParams.topMargin;
            } else {
                r6i = 0;
            }
        }
        Drawable r0_Drawable = this.c.getBackground();
        if (r0_Drawable != null) {
            r0_Drawable.getPadding(this.z);
            r0i = this.z.top + this.z.bottom;
            if (this.j) {
                r7i = r0i;
            } else {
                this.i = -this.z.top;
                r7i = r0i;
            }
        } else {
            this.z.setEmpty();
            r7i = 0;
        }
        int r4i;
        int r1i;
        if (this.c.getInputMethodMode() == 2) {
            r4i = getMaxAvailableHeight(getAnchorView(), this.i, r1z);
            if (this.k || this.f == -1) {
                return r4i + r7i;
            }
            switch (this.g) {
                case WRAP_CONTENT:
                    r1i = MeasureSpec.makeMeasureSpec(this.b.getResources().getDisplayMetrics().widthPixels - this.z.left + this.z.right, ExploreByTouchHelper.INVALID_ID);
                    r0i = this.e.a(r1i, POSITION_PROMPT_ABOVE, FILL_PARENT, r4i - r6i, -1);
                    if (r0i <= 0) {
                        r6i += r7i;
                    }
                    return r0i + r6i;
                case FILL_PARENT:
                    r1i = MeasureSpec.makeMeasureSpec(this.b.getResources().getDisplayMetrics().widthPixels - this.z.left + this.z.right, 1073741824);
                    r0i = this.e.a(r1i, POSITION_PROMPT_ABOVE, FILL_PARENT, r4i - r6i, -1);
                    if (r0i <= 0) {
                        return r0i + r6i;
                    }
                    r6i += r7i;
                    return r0i + r6i;
            }
            r1i = MeasureSpec.makeMeasureSpec(this.g, 1073741824);
            r0i = this.e.a(r1i, POSITION_PROMPT_ABOVE, FILL_PARENT, r4i - r6i, -1);
            if (r0i <= 0) {
                r6i += r7i;
            }
            return r0i + r6i;
        } else {
            r1z = false;
            r4i = getMaxAvailableHeight(getAnchorView(), this.i, r1z);
            if (this.k || this.f == -1) {
                return r4i + r7i;
            }
            switch (this.g) {
                case WRAP_CONTENT:
                    r1i = MeasureSpec.makeMeasureSpec(this.b.getResources().getDisplayMetrics().widthPixels - this.z.left + this.z.right, ExploreByTouchHelper.INVALID_ID);
                    r0i = this.e.a(r1i, POSITION_PROMPT_ABOVE, FILL_PARENT, r4i - r6i, -1);
                    if (r0i <= 0) {
                        return r0i + r6i;
                    }
                    r6i += r7i;
                    return r0i + r6i;
                case FILL_PARENT:
                    r1i = MeasureSpec.makeMeasureSpec(this.b.getResources().getDisplayMetrics().widthPixels - this.z.left + this.z.right, 1073741824);
                    r0i = this.e.a(r1i, POSITION_PROMPT_ABOVE, FILL_PARENT, r4i - r6i, -1);
                    if (r0i <= 0) {
                        r6i += r7i;
                    }
                    return r0i + r6i;
            }
            r1i = MeasureSpec.makeMeasureSpec(this.g, 1073741824);
            r0i = this.e.a(r1i, POSITION_PROMPT_ABOVE, FILL_PARENT, r4i - r6i, -1);
            if (r0i <= 0) {
                return r0i + r6i;
            }
            r6i += r7i;
            return r0i + r6i;
        }
    }

    public void clearListSelection() {
        a r0_a = this.e;
        if (r0_a != null) {
            r0_a.a = true;
            r0_a.requestLayout();
        }
    }

    public void dismiss() {
        this.c.dismiss();
        a();
        this.c.setContentView(null);
        this.e = null;
        this.y.removeCallbacks(this.t);
    }

    public View getAnchorView() {
        return this.p;
    }

    public int getAnimationStyle() {
        return this.c.getAnimationStyle();
    }

    public Drawable getBackground() {
        return this.c.getBackground();
    }

    public int getHeight() {
        return this.f;
    }

    public int getHorizontalOffset() {
        return this.h;
    }

    public int getInputMethodMode() {
        return this.c.getInputMethodMode();
    }

    public ListView getListView() {
        return this.e;
    }

    public int getMaxAvailableHeight(View r7_View, int r8i, boolean r9z) {
        Rect r1_Rect = new Rect();
        r7_View.getWindowVisibleDisplayFrame(r1_Rect);
        int[] r2_intA = new int[2];
        r7_View.getLocationOnScreen(r2_intA);
        int r0i = r1_Rect.bottom;
        if (r9z) {
            r0i = r7_View.getContext().getResources().getDisplayMetrics().heightPixels;
        }
        r0i = Math.max(r0i - r2_intA[1] + r7_View.getHeight() - r8i, r2_intA[1] - r1_Rect.top + r8i);
        if (this.c.getBackground() == null) {
            return r0i;
        }
        this.c.getBackground().getPadding(this.z);
        return r0i - this.z.top + this.z.bottom;
    }

    public int getPromptPosition() {
        return this.n;
    }

    public Object getSelectedItem() {
        return isShowing() ? this.e.getSelectedItem() : null;
    }

    public long getSelectedItemId() {
        return isShowing() ? this.e.getSelectedItemId() : -9223372036854775808L;
    }

    public int getSelectedItemPosition() {
        return isShowing() ? this.e.getSelectedItemPosition() : FILL_PARENT;
    }

    public View getSelectedView() {
        return isShowing() ? this.e.getSelectedView() : null;
    }

    public int getSoftInputMode() {
        return this.c.getSoftInputMode();
    }

    public int getVerticalOffset() {
        return this.j ? this.i : POSITION_PROMPT_ABOVE;
    }

    public int getWidth() {
        return this.g;
    }

    public boolean isDropDownAlwaysVisible() {
        return this.k;
    }

    public boolean isInputMethodNotNeeded() {
        return this.c.getInputMethodMode() == 2;
    }

    public boolean isModal() {
        return this.A;
    }

    public boolean isShowing() {
        return this.c.isShowing();
    }

    public boolean onKeyDown(int r10i, KeyEvent r11_KeyEvent) {
        if ((!isShowing()) || r10i == 62) {
        } else {
            int r0i;
            if (this.e.getSelectedItemPosition() < 0) {
                if (r10i == 66 || r10i == 23) {
                }
            }
            int r5i = this.e.getSelectedItemPosition();
            r0i = this.c.isAboveAnchor() ? 0 : 1;
            ListAdapter r6_ListAdapter = this.d;
            int r4i = a.MAX_ACTIVITY_COUNT_UNLIMITED;
            int r3i = ExploreByTouchHelper.INVALID_ID;
            if (r6_ListAdapter != null) {
                boolean r3z = r6_ListAdapter.areAllItemsEnabled();
                r4i = r3z ? 0 : this.e.a((int)POSITION_PROMPT_ABOVE, true);
                r3i = r3z ? r6_ListAdapter.getCount() - 1 : this.e.a(r6_ListAdapter.getCount() - 1, false);
            }
            if (r0i == 0 || r10i != 19 || r5i > r4i) {
                if (r0i == 0 && r10i == 20 && r5i >= r3i) {
                    clearListSelection();
                    this.c.setInputMethodMode(POSITION_PROMPT_BELOW);
                    show();
                    return true;
                } else {
                    this.e.a = false;
                    if (this.e.onKeyDown(r10i, r11_KeyEvent)) {
                        this.c.setInputMethodMode(INPUT_METHOD_NOT_NEEDED);
                        this.e.requestFocusFromTouch();
                        show();
                        switch (r10i) {
                            case TimeUtils.HUNDRED_DAY_FIELD_LEN:
                            case OneProfileActivity.REQ_CODE_SHARE:
                            case AdViewUtil.NETWORK_TYPE_KUAIYOU:
                            case BDLocation.TypeOffLineLocation:
                                return true;
                        }
                    } else if (r0i == 0 || r10i != 20) {
                        return r0i == 0 && r10i == 19 && r5i == r4i;
                    } else {
                        if (r5i == r3i) {
                            return true;
                        }
                    }
                }
            } else {
                clearListSelection();
                this.c.setInputMethodMode(POSITION_PROMPT_BELOW);
                show();
                return true;
            }
        }
    }

    public boolean onKeyUp(int r2i, KeyEvent r3_KeyEvent) {
        if ((!isShowing()) || this.e.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean r0z = this.e.onKeyUp(r2i, r3_KeyEvent);
        if (!r0z) {
            return r0z;
        }
        switch (r2i) {
            case AdViewUtil.NETWORK_TYPE_KUAIYOU:
            case BDLocation.TypeOffLineLocation:
                dismiss();
                return r0z;
        }
        return r0z;
    }

    public boolean performItemClick(int r7i) {
        if (!isShowing()) {
            return false;
        }
        if (this.r != null) {
            AdapterView r1_AdapterView = this.e;
            this.r.onItemClick(r1_AdapterView, r1_AdapterView.getChildAt(r7i - r1_AdapterView.getFirstVisiblePosition()), r7i, r1_AdapterView.getAdapter().getItemId(r7i));
        }
        return true;
    }

    public void postShow() {
        this.y.post(this.x);
    }

    public void setAdapter(ListAdapter r3_ListAdapter) {
        if (this.o == null) {
            this.o = new c(null);
        } else if (this.d != null) {
            this.d.unregisterDataSetObserver(this.o);
        }
        this.d = r3_ListAdapter;
        if (this.d != null) {
            r3_ListAdapter.registerDataSetObserver(this.o);
        }
        if (this.e != null) {
            this.e.setAdapter(this.d);
        }
    }

    public void setAnchorView(View r1_View) {
        this.p = r1_View;
    }

    public void setAnimationStyle(int r2i) {
        this.c.setAnimationStyle(r2i);
    }

    public void setBackgroundDrawable(Drawable r2_Drawable) {
        this.c.setBackgroundDrawable(r2_Drawable);
    }

    public void setContentWidth(int r3i) {
        Drawable r0_Drawable = this.c.getBackground();
        if (r0_Drawable != null) {
            r0_Drawable.getPadding(this.z);
            this.g = this.z.left + this.z.right + r3i;
        } else {
            setWidth(r3i);
        }
    }

    public void setDropDownAlwaysVisible(boolean r1z) {
        this.k = r1z;
    }

    public void setForceIgnoreOutsideTouch(boolean r1z) {
        this.l = r1z;
    }

    public void setHeight(int r1i) {
        this.f = r1i;
    }

    public void setHorizontalOffset(int r1i) {
        this.h = r1i;
    }

    public void setInputMethodMode(int r2i) {
        this.c.setInputMethodMode(r2i);
    }

    public void setListSelector(Drawable r1_Drawable) {
        this.q = r1_Drawable;
    }

    public void setModal(boolean r2z) {
        this.A = true;
        this.c.setFocusable(r2z);
    }

    public void setOnDismissListener(OnDismissListener r2_OnDismissListener) {
        this.c.setOnDismissListener(r2_OnDismissListener);
    }

    public void setOnItemClickListener(OnItemClickListener r1_OnItemClickListener) {
        this.r = r1_OnItemClickListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener r1_OnItemSelectedListener) {
        this.s = r1_OnItemSelectedListener;
    }

    public void setPromptPosition(int r1i) {
        this.n = r1i;
    }

    public void setPromptView(View r2_View) {
        boolean r0z = isShowing();
        if (r0z) {
            a();
        }
        this.m = r2_View;
        if (r0z) {
            show();
        }
    }

    public void setSelection(int r3i) {
        a r0_a = this.e;
        if ((!isShowing()) || r0_a == null) {
        } else {
            r0_a.a = false;
            r0_a.setSelection(r3i);
            if (r0_a.getChoiceMode() != 0) {
                r0_a.setItemChecked(r3i, true);
            }
        }
    }

    public void setSoftInputMode(int r2i) {
        this.c.setSoftInputMode(r2i);
    }

    public void setVerticalOffset(int r2i) {
        this.i = r2i;
        this.j = true;
    }

    public void setWidth(int r1i) {
        this.g = r1i;
    }

    public void show() {
        boolean r3z = true;
        boolean r1z = false;
        int r0i = FILL_PARENT;
        int r5i = b();
        boolean r2z = isInputMethodNotNeeded();
        int r4i;
        PopupWindow r2_PopupWindow;
        if (this.c.isShowing()) {
            if (this.g == -1) {
                r4i = -1;
            } else if (this.g == -2) {
                r4i = getAnchorView().getWidth();
            } else {
                r4i = this.g;
            }
            if (this.f == -1) {
                if (r2z) {
                    if (r2z) {
                        this.c.setWindowLayoutMode(this.g != -1 ? -1 : 0, FILL_PARENT);
                    } else {
                        r2_PopupWindow = this.c;
                        if (this.g != -1) {
                            r2_PopupWindow.setWindowLayoutMode(r0i, POSITION_PROMPT_ABOVE);
                        } else {
                            r0i = 0;
                            r2_PopupWindow.setWindowLayoutMode(r0i, POSITION_PROMPT_ABOVE);
                        }
                    }
                } else {
                    r5i = -1;
                    if (r2z) {
                        if (this.g != -1) {
                        }
                        this.c.setWindowLayoutMode(this.g != -1 ? -1 : 0, FILL_PARENT);
                    } else {
                        r2_PopupWindow = this.c;
                        if (this.g != -1) {
                            r0i = 0;
                        }
                        r2_PopupWindow.setWindowLayoutMode(r0i, POSITION_PROMPT_ABOVE);
                    }
                }
            } else if (this.f != -2) {
                r5i = this.f;
            }
            PopupWindow r0_PopupWindow = this.c;
            if (this.l || this.k) {
                r0_PopupWindow.setOutsideTouchable(r1z);
                this.c.update(getAnchorView(), this.h, this.i, r4i, r5i);
            } else {
                r1z = true;
                r0_PopupWindow.setOutsideTouchable(r1z);
                this.c.update(getAnchorView(), this.h, this.i, r4i, r5i);
            }
        } else {
            int r2i;
            if (this.g == -1) {
                r2i = -1;
            } else if (this.g == -2) {
                this.c.setWidth(getAnchorView().getWidth());
                r2i = 0;
            } else {
                this.c.setWidth(this.g);
                r2i = 0;
            }
            if (this.f == -1) {
                r4i = -1;
            } else if (this.f == -2) {
                this.c.setHeight(r5i);
                r4i = 0;
            } else {
                this.c.setHeight(this.f);
                r4i = 0;
            }
            this.c.setWindowLayoutMode(r2i, r4i);
            r2_PopupWindow = this.c;
            if (this.l || this.k) {
                r3z = false;
                r2_PopupWindow.setOutsideTouchable(r3z);
                this.c.setTouchInterceptor(this.u);
                this.c.showAsDropDown(getAnchorView(), this.h, this.i);
                this.e.setSelection(r0i);
            } else {
                r2_PopupWindow.setOutsideTouchable(r3z);
                this.c.setTouchInterceptor(this.u);
                this.c.showAsDropDown(getAnchorView(), this.h, this.i);
                this.e.setSelection(r0i);
            }
            if ((!this.A) || this.e.isInTouchMode()) {
                clearListSelection();
            }
            if (!this.A) {
                this.y.post(this.w);
            }
        }
    }
}