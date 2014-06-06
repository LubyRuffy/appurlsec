package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.widget.AdapterViewICS.OnItemClickListener;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.utils.Base64;

public class ScrollingTabContainerView extends HorizontalScrollView implements OnItemClickListener {
    Runnable a;
    int b;
    int c;
    private b d;
    private LinearLayout e;
    private p f;
    private boolean g;
    private final LayoutInflater h;
    private int i;
    private int j;

    public static class TabView extends LinearLayout {
        private Tab a;
        private TextView b;
        private ImageView c;
        private View d;
        private ScrollingTabContainerView e;

        public TabView(Context r1_Context, AttributeSet r2_AttributeSet) {
            super(r1_Context, r2_AttributeSet);
        }

        void a(ScrollingTabContainerView r2_ScrollingTabContainerView, Tab r3_Tab, boolean r4z) {
            this.e = r2_ScrollingTabContainerView;
            this.a = r3_Tab;
            if (r4z) {
                setGravity(TimeUtils.HUNDRED_DAY_FIELD_LEN);
            }
            update();
        }

        public void bindTab(Tab r1_Tab) {
            this.a = r1_Tab;
            update();
        }

        public Tab getTab() {
            return this.a;
        }

        public void onMeasure(int r3i, int r4i) {
            int r0i;
            super.onMeasure(r3i, r4i);
            r0i = this.e != null ? this.e.b : 0;
            if (r0i <= 0 || getMeasuredWidth() <= r0i) {
            } else {
                super.onMeasure(MeasureSpec.makeMeasureSpec(r0i, 1073741824), r4i);
            }
        }

        public void update() {
            Tab r0_Tab = this.a;
            View r1_View = r0_Tab.getCustomView();
            if (r1_View != null) {
                android.support.v7.internal.widget.ScrollingTabContainerView.TabView r0_android_support_v7_internal_widget_ScrollingTabContainerView_TabView = r1_View.getParent();
                if (r0_android_support_v7_internal_widget_ScrollingTabContainerView_TabView != this) {
                    if (r0_android_support_v7_internal_widget_ScrollingTabContainerView_TabView != null) {
                        r0_android_support_v7_internal_widget_ScrollingTabContainerView_TabView.removeView(r1_View);
                    }
                    addView(r1_View);
                }
                this.d = r1_View;
                if (this.b != null) {
                    this.b.setVisibility(Base64.DONT_BREAK_LINES);
                }
                if (this.c != null) {
                    this.c.setVisibility(Base64.DONT_BREAK_LINES);
                    this.c.setImageDrawable(null);
                }
            } else {
                if (this.d != null) {
                    removeView(this.d);
                    this.d = null;
                }
                Drawable r1_Drawable = r0_Tab.getIcon();
                CharSequence r2_CharSequence = r0_Tab.getText();
                if (r1_Drawable != null) {
                    if (this.c == null) {
                        View r3_View = new ImageView(getContext());
                        LayoutParams r4_LayoutParams = new LinearLayout.LayoutParams(-2, -2);
                        r4_LayoutParams.gravity = 16;
                        r3_View.setLayoutParams(r4_LayoutParams);
                        addView(r3_View, 0);
                        this.c = r3_View;
                    }
                    this.c.setImageDrawable(r1_Drawable);
                    this.c.setVisibility(0);
                } else if (this.c != null) {
                    this.c.setVisibility(Base64.DONT_BREAK_LINES);
                    this.c.setImageDrawable(null);
                }
                if (r2_CharSequence != null) {
                    if (this.b == null) {
                        r1_View = new CompatTextView(getContext(), null, R.attr.actionBarTabTextStyle);
                        r1_View.setEllipsize(TruncateAt.END);
                        LayoutParams r3_LayoutParams = new LinearLayout.LayoutParams(-2, -2);
                        r3_LayoutParams.gravity = 16;
                        r1_View.setLayoutParams(r3_LayoutParams);
                        addView(r1_View);
                        this.b = r1_View;
                    }
                    this.b.setText(r2_CharSequence);
                    this.b.setVisibility(0);
                } else if (this.b != null) {
                    this.b.setVisibility(Base64.DONT_BREAK_LINES);
                    this.b.setText(null);
                }
                if (this.c != null) {
                    this.c.setContentDescription(r0_Tab.getContentDescription());
                }
            }
        }
    }

    private class a extends BaseAdapter {
        private a() {
        }

        public int getCount() {
            return ScrollingTabContainerView.this.e.getChildCount();
        }

        public Object getItem(int r2i) {
            return ((android.support.v7.internal.widget.ScrollingTabContainerView.TabView) ScrollingTabContainerView.this.e.getChildAt(r2i)).getTab();
        }

        public long getItemId(int r3i) {
            return (long) r3i;
        }

        public View getView(int r4i, View r5_View, ViewGroup r6_ViewGroup) {
            if (r5_View == null) {
                return ScrollingTabContainerView.this.a((Tab) getItem(r4i), true);
            }
            ((android.support.v7.internal.widget.ScrollingTabContainerView.TabView) r5_View).bindTab((Tab) getItem(r4i));
            return r5_View;
        }
    }

    private class b implements OnClickListener {
        private b() {
        }

        public void onClick(View r6_View) {
            ((android.support.v7.internal.widget.ScrollingTabContainerView.TabView) r6_View).getTab().select();
            int r3i = ScrollingTabContainerView.this.e.getChildCount();
            int r2i = 0;
            while (r2i < r3i) {
                View r4_View = ScrollingTabContainerView.this.e.getChildAt(r2i);
                r4_View.setSelected(r4_View == r6_View);
                r2i++;
            }
        }
    }

    public ScrollingTabContainerView(Context r5_Context) {
        super(r5_Context);
        this.h = LayoutInflater.from(r5_Context);
        setHorizontalScrollBarEnabled(false);
        ActionBarPolicy r0_ActionBarPolicy = ActionBarPolicy.get(r5_Context);
        setContentHeight(r0_ActionBarPolicy.getTabContainerHeight());
        this.c = r0_ActionBarPolicy.getStackedTabMaxWidth();
        this.e = (LinearLayout) this.h.inflate(R.layout.abc_action_bar_tabbar, this, false);
        addView(this.e, new LayoutParams(-2, -1));
    }

    private TabView a(Tab r6_Tab, boolean r7z) {
        TabView r0_TabView = (TabView) this.h.inflate(R.layout.abc_action_bar_tab, this.e, false);
        r0_TabView.a(this, r6_Tab, r7z);
        if (r7z) {
            r0_TabView.setBackgroundDrawable(null);
            r0_TabView.setLayoutParams(new AbsListView.LayoutParams(-1, this.i));
        } else {
            r0_TabView.setFocusable(true);
            if (this.d == null) {
                this.d = new b(null);
            }
            r0_TabView.setOnClickListener(this.d);
        }
        return r0_TabView;
    }

    private boolean a() {
        return this.f != null && this.f.getParent() == this;
    }

    private void b() {
        if (a()) {
        } else {
            if (this.f == null) {
                this.f = d();
            }
            removeView(this.e);
            addView(this.f, new LayoutParams(-2, -1));
            if (this.f.getAdapter() == null) {
                this.f.setAdapter(new a(null));
            }
            if (this.a != null) {
                removeCallbacks(this.a);
                this.a = null;
            }
            this.f.setSelection(this.j);
        }
    }

    private boolean c() {
        if (!a()) {
            return false;
        }
        removeView(this.f);
        addView(this.e, new LayoutParams(-2, -1));
        setTabSelected(this.f.getSelectedItemPosition());
        return false;
    }

    private p d() {
        p r0_p = new p(getContext(), null, R.attr.actionDropDownStyle);
        r0_p.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        r0_p.a((OnItemClickListener)this);
        return r0_p;
    }

    public void addTab(Tab r7_Tab, int r8i, boolean r9z) {
        View r1_View = a(r7_Tab, false);
        this.e.addView(r1_View, r8i, new LinearLayout.LayoutParams(0, -1, 1.0f));
        if (this.f != null) {
            ((a) this.f.getAdapter()).notifyDataSetChanged();
        }
        if (r9z) {
            r1_View.setSelected(true);
        }
        if (this.g) {
            requestLayout();
        }
    }

    public void addTab(Tab r7_Tab, boolean r8z) {
        View r1_View = a(r7_Tab, false);
        this.e.addView(r1_View, new LinearLayout.LayoutParams(0, -1, 1.0f));
        if (this.f != null) {
            ((a) this.f.getAdapter()).notifyDataSetChanged();
        }
        if (r8z) {
            r1_View.setSelected(true);
        }
        if (this.g) {
            requestLayout();
        }
    }

    public void animateToTab(int r3i) {
        View r0_View = this.e.getChildAt(r3i);
        if (this.a != null) {
            removeCallbacks(this.a);
        }
        this.a = new o(this, r0_View);
        post(this.a);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            post(this.a);
        }
    }

    protected void onConfigurationChanged(Configuration r3_Configuration) {
        ActionBarPolicy r0_ActionBarPolicy = ActionBarPolicy.get(getContext());
        setContentHeight(r0_ActionBarPolicy.getTabContainerHeight());
        this.c = r0_ActionBarPolicy.getStackedTabMaxWidth();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            removeCallbacks(this.a);
        }
    }

    public void onItemClick(AdapterViewICS<?> r2_AdapterViewICS_, View r3_View, int r4i, long r5j) {
        ((TabView) r3_View).getTab().select();
    }

    public void onMeasure(int r8i, int r9i) {
        boolean r0z;
        int r1i = 1;
        int r2i = 0;
        int r3i = MeasureSpec.getMode(r8i);
        r0z = r3i == 1073741824;
        setFillViewport(r0z);
        int r4i = this.e.getChildCount();
        if (r4i > 1) {
            if (r3i == 1073741824 || r3i == -2147483648) {
                if (r4i > 2) {
                    this.b = (int) (((float) MeasureSpec.getSize(r8i)) * 0.4f);
                } else {
                    this.b = MeasureSpec.getSize(r8i) / 2;
                }
                this.b = Math.min(this.b, this.c);
            } else {
                this.b = -1;
            }
        } else {
            this.b = -1;
        }
        r3i = MeasureSpec.makeMeasureSpec(this.i, 1073741824);
        if (r0z || (!this.g)) {
            r1i = 0;
            if (r1i == 0) {
                this.e.measure(r2i, r3i);
                if (this.e.getMeasuredWidth() <= MeasureSpec.getSize(r8i)) {
                    b();
                } else {
                    c();
                }
            } else {
                c();
            }
            r1i = getMeasuredWidth();
            super.onMeasure(r8i, r3i);
            r2i = getMeasuredWidth();
            if ((!r0z) || r1i == r2i) {
            } else {
                setTabSelected(this.j);
            }
        } else {
            if (r1i == 0) {
                c();
            } else {
                this.e.measure(r2i, r3i);
                if (this.e.getMeasuredWidth() <= MeasureSpec.getSize(r8i)) {
                    c();
                } else {
                    b();
                }
            }
            r1i = getMeasuredWidth();
            super.onMeasure(r8i, r3i);
            r2i = getMeasuredWidth();
            if (r0z || r1i == r2i) {
            } else {
                setTabSelected(this.j);
            }
        }
    }

    public void removeAllTabs() {
        this.e.removeAllViews();
        if (this.f != null) {
            ((a) this.f.getAdapter()).notifyDataSetChanged();
        }
        if (this.g) {
            requestLayout();
        }
    }

    public void removeTabAt(int r2i) {
        this.e.removeViewAt(r2i);
        if (this.f != null) {
            ((a) this.f.getAdapter()).notifyDataSetChanged();
        }
        if (this.g) {
            requestLayout();
        }
    }

    public void setAllowCollapse(boolean r1z) {
        this.g = r1z;
    }

    public void setContentHeight(int r1i) {
        this.i = r1i;
        requestLayout();
    }

    public void setTabSelected(int r6i) {
        this.j = r6i;
        int r3i = this.e.getChildCount();
        int r2i = 0;
        while (r2i < r3i) {
            boolean r0z;
            View r4_View = this.e.getChildAt(r2i);
            r0z = r2i == r6i;
            r4_View.setSelected(r0z);
            if (r0z) {
                animateToTab(r6i);
            }
            r2i++;
        }
        if (this.f == null || r6i < 0) {
        } else {
            this.f.setSelection(r6i);
        }
    }

    public void updateTab(int r2i) {
        ((TabView) this.e.getChildAt(r2i)).update();
        if (this.f != null) {
            ((a) this.f.getAdapter()).notifyDataSetChanged();
        }
        if (this.g) {
            requestLayout();
        }
    }
}