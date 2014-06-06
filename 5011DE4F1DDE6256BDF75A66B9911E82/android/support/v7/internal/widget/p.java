package android.support.v7.internal.widget;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.AdapterViewICS.OnItemClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SpinnerICS.java
class p extends AbsSpinnerICS implements OnClickListener {
    int F;
    private d G;
    private b H;
    private int I;
    private Rect J;


    // compiled from: SpinnerICS.java
    private static interface d {
        public void dismiss();

        public CharSequence getHintText();

        public boolean isShowing();

        public void setAdapter(ListAdapter r1_ListAdapter);

        public void setPromptText(CharSequence r1_CharSequence);

        public void show();
    }

    // compiled from: SpinnerICS.java
    private class a implements OnClickListener, d {
        private AlertDialog b;
        private ListAdapter c;
        private CharSequence d;

        private a() {
        }

        public void dismiss() {
            this.b.dismiss();
            this.b = null;
        }

        public CharSequence getHintText() {
            return this.d;
        }

        public boolean isShowing() {
            return this.b != null ? this.b.isShowing() : false;
        }

        public void onClick(DialogInterface r5_DialogInterface, int r6i) {
            p.this.setSelection(r6i);
            if (p.this.t != null) {
                p.this.performItemClick(null, r6i, this.c.getItemId(r6i));
            }
            dismiss();
        }

        public void setAdapter(ListAdapter r1_ListAdapter) {
            this.c = r1_ListAdapter;
        }

        public void setPromptText(CharSequence r1_CharSequence) {
            this.d = r1_CharSequence;
        }

        public void show() {
            Builder r0_Builder = new Builder(p.this.getContext());
            if (this.d != null) {
                r0_Builder.setTitle(this.d);
            }
            this.b = r0_Builder.setSingleChoiceItems(this.c, p.this.getSelectedItemPosition(), this).show();
        }
    }

    // compiled from: SpinnerICS.java
    private static class b implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter a;
        private ListAdapter b;

        public b(SpinnerAdapter r2_SpinnerAdapter) {
            this.a = r2_SpinnerAdapter;
            if (r2_SpinnerAdapter instanceof ListAdapter) {
                this.b = (ListAdapter) r2_SpinnerAdapter;
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter r0_ListAdapter = this.b;
            return r0_ListAdapter != null ? r0_ListAdapter.areAllItemsEnabled() : true;
        }

        public int getCount() {
            return this.a == null ? 0 : this.a.getCount();
        }

        public View getDropDownView(int r2i, View r3_View, ViewGroup r4_ViewGroup) {
            return this.a == null ? null : this.a.getDropDownView(r2i, r3_View, r4_ViewGroup);
        }

        public Object getItem(int r2i) {
            return this.a == null ? null : this.a.getItem(r2i);
        }

        public long getItemId(int r3i) {
            return this.a == null ? -1 : this.a.getItemId(r3i);
        }

        public int getItemViewType(int r2i) {
            return 0;
        }

        public View getView(int r2i, View r3_View, ViewGroup r4_ViewGroup) {
            return getDropDownView(r2i, r3_View, r4_ViewGroup);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            return this.a != null && this.a.hasStableIds();
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }

        public boolean isEnabled(int r2i) {
            ListAdapter r0_ListAdapter = this.b;
            return r0_ListAdapter != null ? r0_ListAdapter.isEnabled(r2i) : true;
        }

        public void registerDataSetObserver(DataSetObserver r2_DataSetObserver) {
            if (this.a != null) {
                this.a.registerDataSetObserver(r2_DataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver r2_DataSetObserver) {
            if (this.a != null) {
                this.a.unregisterDataSetObserver(r2_DataSetObserver);
            }
        }
    }

    // compiled from: SpinnerICS.java
    private class c extends ListPopupWindow implements d {
        private CharSequence c;
        private ListAdapter d;

        public c(Context r4_Context, AttributeSet r5_AttributeSet, int r6i) {
            super(r4_Context, r5_AttributeSet, r6i);
            setAnchorView(p.this);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new b(new q(this, p.this)));
        }

        public CharSequence getHintText() {
            return this.c;
        }

        public void setAdapter(ListAdapter r1_ListAdapter) {
            super.setAdapter(r1_ListAdapter);
            this.d = r1_ListAdapter;
        }

        public void setPromptText(CharSequence r1_CharSequence) {
            this.c = r1_CharSequence;
        }

        public void show() {
            int r1i = p.this.getPaddingLeft();
            if (p.this.F == -2) {
                int r2i = p.this.getWidth();
                setContentWidth(Math.max(p.this.a((SpinnerAdapter) this.d, getBackground()), r2i - r1i - p.this.getPaddingRight()));
            } else if (p.this.F == -1) {
                setContentWidth(p.this.getWidth() - r1i - p.this.getPaddingRight());
            } else {
                setContentWidth(p.this.F);
            }
            Drawable r2_Drawable = getBackground();
            int r0i = 0;
            if (r2_Drawable != null) {
                r2_Drawable.getPadding(p.this.J);
                r0i = -p.this.J.left;
            }
            setHorizontalOffset(r0i + r1i);
            setInputMethodMode(XListViewHeader.STATE_REFRESHING);
            super.show();
            getListView().setChoiceMode(1);
            setSelection(p.this.getSelectedItemPosition());
        }
    }

    p(Context r2_Context, AttributeSet r3_AttributeSet, int r4i) {
        this(r2_Context, r3_AttributeSet, r4i, -1);
    }

    p(Context r7_Context, AttributeSet r8_AttributeSet, int r9i, int r10i) {
        super(r7_Context, r8_AttributeSet, r9i);
        this.J = new Rect();
        TypedArray r0_TypedArray = r7_Context.obtainStyledAttributes(r8_AttributeSet, R.styleable.Spinner, r9i, 0);
        if (r10i == -1) {
            r10i = r0_TypedArray.getInt(ShareUtils.SHARE_COLLECT, 0);
        }
        switch (r10i) {
            case XListViewHeader.STATE_NORMAL:
                this.G = new a(null);
                break;
            case XListViewHeader.STATE_READY:
                d r1_d = new c(r7_Context, r8_AttributeSet, r9i);
                this.F = r0_TypedArray.getLayoutDimension(XListViewFooter.STATE_NOMORE, RequestListener.DEFAULT_LOADED_SIZE);
                r1_d.setBackgroundDrawable(r0_TypedArray.getDrawable(XListViewHeader.STATE_REFRESHING));
                int r2i = r0_TypedArray.getDimensionPixelOffset(ShareUtils.SHARE_SMS, 0);
                if (r2i != 0) {
                    r1_d.setVerticalOffset(r2i);
                }
                r2i = r0_TypedArray.getDimensionPixelOffset(XListViewFooter.STATE_NODATA, 0);
                if (r2i != 0) {
                    r1_d.setHorizontalOffset(r2i);
                }
                this.G = r1_d;
                break;
        }
        this.I = r0_TypedArray.getInt(0, qsbk.app.R.styleable.ActionBar_progressBarPadding);
        this.G.setPromptText(r0_TypedArray.getString(ShareUtils.SHARE_COPY));
        r0_TypedArray.recycle();
        if (this.H != null) {
            this.G.setAdapter(this.H);
            this.H = null;
        }
    }

    private View c(int r3i) {
        View r0_View;
        if (!this.v) {
            r0_View = this.j.a(r3i);
            if (r0_View != null) {
                c(r0_View);
                return r0_View;
            }
        }
        r0_View = this.a.getView(r3i, null, this);
        c(r0_View);
        return r0_View;
    }

    private void c(View r7_View) {
        LayoutParams r0_LayoutParams = r7_View.getLayoutParams();
        if (r0_LayoutParams == null) {
            r0_LayoutParams = generateDefaultLayoutParams();
        }
        addViewInLayout(r7_View, 0, r0_LayoutParams);
        r7_View.setSelected(hasFocus());
        r7_View.measure(ViewGroup.getChildMeasureSpec(this.c, this.i.left + this.i.right, r0_LayoutParams.width), ViewGroup.getChildMeasureSpec(this.b, this.i.top + this.i.bottom, r0_LayoutParams.height));
        int r0i = this.i.top + (((getMeasuredHeight() - this.i.bottom) - this.i.top) - r7_View.getMeasuredHeight()) / 2;
        r7_View.layout(0, r0i, r7_View.getMeasuredWidth() + 0, r7_View.getMeasuredHeight() + r0i);
    }

    int a(SpinnerAdapter r11_SpinnerAdapter, Drawable r12_Drawable) {
        if (r11_SpinnerAdapter == null) {
            return 0;
        }
        int r6i = MeasureSpec.makeMeasureSpec(0, 0);
        int r7i = MeasureSpec.makeMeasureSpec(0, 0);
        int r1i = Math.max(0, getSelectedItemPosition());
        int r8i = Math.min(r11_SpinnerAdapter.getCount(), r1i + 15);
        int r5i = Math.max(0, r1i - 15 - r8i - r1i);
        View r3_View = null;
        int r4i = 0;
        r1i = 0;
        while (r5i < r8i) {
            View r1_View;
            int r0i = r11_SpinnerAdapter.getItemViewType(r5i);
            if (r0i != r1i) {
                r1_View = null;
            } else {
                r0i = r1i;
                r1_View = r3_View;
            }
            r3_View = r11_SpinnerAdapter.getView(r5i, r1_View, this);
            if (r3_View.getLayoutParams() == null) {
                r3_View.setLayoutParams(new LayoutParams(-2, -2));
            }
            r3_View.measure(r6i, r7i);
            r4i = Math.max(r4i, r3_View.getMeasuredWidth());
            r5i++;
            r1i = r0i;
        }
        if (r12_Drawable == null) {
            return r4i;
        }
        r12_Drawable.getPadding(this.J);
        return this.J.left + this.J.right + r4i;
    }

    void a(OnItemClickListener r1_OnItemClickListener) {
        super.setOnItemClickListener(r1_OnItemClickListener);
    }

    void b(int r7i, boolean r8z) {
        int r0i = this.i.left;
        int r1i = getRight() - getLeft() - this.i.left - this.i.right;
        if (this.v) {
            f();
        }
        if (this.A == 0) {
            a();
        } else {
            if (this.w >= 0) {
                a(this.w);
            }
            b();
            removeAllViewsInLayout();
            this.k = this.y;
            View r2_View = c(this.y);
            int r3i = r2_View.getMeasuredWidth();
            switch ((this.I & 7)) {
                case XListViewHeader.STATE_READY:
                    r0i = r0i + r1i / 2 - r3i / 2;
                    break;
                case ShareUtils.SHARE_SMS:
                    r0i = r0i + r1i - r3i;
                    break;
            }
            r2_View.offsetLeftAndRight(r0i);
            this.j.a();
            invalidate();
            g();
            this.v = false;
            this.p = false;
            b(this.y);
        }
    }

    public int getBaseline() {
        View r1_View = null;
        if (getChildCount() > 0) {
            r1_View = getChildAt(0);
        } else if (this.a != null) {
            if (this.a.getCount() > 0) {
                r1_View = c(0);
                this.j.put(0, r1_View);
                removeAllViewsInLayout();
            }
        }
        if (r1_View == null) {
            return -1;
        }
        int r2i = r1_View.getBaseline();
        return r2i >= 0 ? r1_View.getTop() + r2i : -1;
    }

    public CharSequence getPrompt() {
        return this.G.getHintText();
    }

    public void onClick(DialogInterface r1_DialogInterface, int r2i) {
        setSelection(r2i);
        r1_DialogInterface.dismiss();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.G == null || (!this.G.isShowing())) {
        } else {
            this.G.dismiss();
        }
    }

    protected void onLayout(boolean r3z, int r4i, int r5i, int r6i, int r7i) {
        super.onLayout(r3z, r4i, r5i, r6i, r7i);
        this.r = true;
        b(0, false);
        this.r = false;
    }

    protected void onMeasure(int r4i, int r5i) {
        super.onMeasure(r4i, r5i);
        if (this.G == null || MeasureSpec.getMode(r4i) != -2147483648) {
        } else {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), MeasureSpec.getSize(r4i)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        boolean r0z = super.performClick();
        if (!r0z) {
            r0z = true;
            if (!this.G.isShowing()) {
                this.G.show();
            }
        }
        return r0z;
    }

    public void setAdapter(SpinnerAdapter r3_SpinnerAdapter) {
        super.setAdapter(r3_SpinnerAdapter);
        if (this.G != null) {
            this.G.setAdapter(new b(r3_SpinnerAdapter));
        } else {
            this.H = new b(r3_SpinnerAdapter);
        }
    }

    public void setGravity(int r2i) {
        if (this.I != r2i) {
            if ((r2i & 7) == 0) {
                r2i |= 3;
            }
            this.I = r2i;
            requestLayout();
        }
    }

    public void setOnItemClickListener(OnItemClickListener r3_OnItemClickListener) {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }

    public void setPrompt(CharSequence r2_CharSequence) {
        this.G.setPromptText(r2_CharSequence);
    }

    public void setPromptId(int r2i) {
        setPrompt(getContext().getText(r2i));
    }
}