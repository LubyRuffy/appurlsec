package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class PagerTitleStrip extends ViewGroup implements a {
    private static final int[] n;
    private static final int[] o;
    private static final b q;
    ViewPager a;
    TextView b;
    TextView c;
    TextView d;
    int e;
    private int f;
    private float g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private final a l;
    private WeakReference<PagerAdapter> m;
    private int p;


    private class a extends DataSetObserver implements OnPageChangeListener, d {
        private int b;

        private a() {
        }

        public void onAdapterChanged(PagerAdapter r2_PagerAdapter, PagerAdapter r3_PagerAdapter) {
            PagerTitleStrip.this.a(r2_PagerAdapter, r3_PagerAdapter);
        }

        public void onChanged() {
            float r0f = 0.0f;
            PagerTitleStrip.this.a(PagerTitleStrip.this.getCurrentItem(), PagerTitleStrip.this.getAdapter());
            if (PagerTitleStrip.this.g >= 0.0f) {
                r0f = PagerTitleStrip.this.g;
            }
            PagerTitleStrip.this.a(PagerTitleStrip.this.getCurrentItem(), r0f, true);
        }

        public void onPageScrollStateChanged(int r1i) {
            this.b = r1i;
        }

        public void onPageScrolled(int r3i, float r4f, int r5i) {
            if (r4f > 0.5f) {
                r3i++;
            }
            PagerTitleStrip.this.a(r3i, r4f, false);
        }

        public void onPageSelected(int r5i) {
            float r0f = 0.0f;
            if (this.b == 0) {
                PagerTitleStrip.this.a(PagerTitleStrip.this.getCurrentItem(), PagerTitleStrip.this.getAdapter());
                if (PagerTitleStrip.this.g >= 0.0f) {
                    r0f = PagerTitleStrip.this.g;
                }
                PagerTitleStrip.this.a(PagerTitleStrip.this.getCurrentItem(), r0f, true);
            }
        }
    }

    static interface b {
        public void setSingleLineAllCaps(TextView r1_TextView);
    }

    static class c implements b {
        c() {
        }

        public void setSingleLineAllCaps(TextView r1_TextView) {
            r1_TextView.setSingleLine();
        }
    }

    static class d implements b {
        d() {
        }

        public void setSingleLineAllCaps(TextView r1_TextView) {
            o.setSingleLineAllCaps(r1_TextView);
        }
    }

    static {
        n = new int[]{16842804, 16842901, 16842904, 16842927};
        int[] r0_intA = new int[1];
        r0_intA[0] = 16843660;
        o = r0_intA;
        if (VERSION.SDK_INT >= REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION) {
            q = new d();
        } else {
            q = new c();
        }
    }

    public PagerTitleStrip(Context r2_Context) {
        this(r2_Context, null);
    }

    public PagerTitleStrip(Context r6_Context, AttributeSet r7_AttributeSet) {
        int r4i = XListViewHeader.STATE_REFRESHING;
        boolean r0z = false;
        super(r6_Context, r7_AttributeSet);
        this.f = -1;
        this.g = -1.0f;
        this.l = new a(null);
        View r1_View = new TextView(r6_Context);
        this.b = r1_View;
        addView(r1_View);
        r1_View = new TextView(r6_Context);
        this.c = r1_View;
        addView(r1_View);
        r1_View = new TextView(r6_Context);
        this.d = r1_View;
        addView(r1_View);
        TypedArray r1_TypedArray = r6_Context.obtainStyledAttributes(r7_AttributeSet, n);
        int r2i = r1_TypedArray.getResourceId(0, 0);
        if (r2i != 0) {
            this.b.setTextAppearance(r6_Context, r2i);
            this.c.setTextAppearance(r6_Context, r2i);
            this.d.setTextAppearance(r6_Context, r2i);
        }
        int r3i = r1_TypedArray.getDimensionPixelSize(1, 0);
        if (r3i != 0) {
            setTextSize(0, (float) r3i);
        }
        if (r1_TypedArray.hasValue(XListViewHeader.STATE_REFRESHING)) {
            r3i = r1_TypedArray.getColor(r4i, 0);
            this.b.setTextColor(r3i);
            this.c.setTextColor(r3i);
            this.d.setTextColor(r3i);
        }
        this.i = r1_TypedArray.getInteger(XListViewFooter.STATE_NOMORE, 80);
        r1_TypedArray.recycle();
        this.e = this.c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.b.setEllipsize(TruncateAt.END);
        this.c.setEllipsize(TruncateAt.END);
        this.d.setEllipsize(TruncateAt.END);
        if (r2i != 0) {
            r1_TypedArray = r6_Context.obtainStyledAttributes(r2i, o);
            r0z = r1_TypedArray.getBoolean(r0z, r0z);
            r1_TypedArray.recycle();
        }
        if (r0z) {
            a(this.b);
            a(this.c);
            a(this.d);
        } else {
            this.b.setSingleLine();
            this.c.setSingleLine();
            this.d.setSingleLine();
        }
        this.h = (int) (r6_Context.getResources().getDisplayMetrics().density * 16.0f);
    }

    private static void a(TextView r1_TextView) {
        q.setSingleLineAllCaps(r1_TextView);
    }

    int a() {
        Drawable r1_Drawable = getBackground();
        return r1_Drawable != null ? r1_Drawable.getIntrinsicHeight() : 0;
    }

    void a(int r19i, float r20f, boolean r21z) {
        int r5i;
        int r3i;
        int r6i;
        int r2i;
        int r7i;
        int r4i;
        int r8i;
        int r9i;
        int r10i;
        int r11i;
        int r13i;
        int r12i;
        float r2f;
        if (r19i != this.f) {
            a(r19i, this.a.getAdapter());
        } else if (r21z || r20f != this.g) {
            this.k = true;
            r5i = this.b.getMeasuredWidth();
            r3i = this.c.getMeasuredWidth();
            r6i = this.d.getMeasuredWidth();
            r2i = r3i / 2;
            r7i = getWidth();
            r4i = getHeight();
            r8i = getPaddingLeft();
            r9i = getPaddingRight();
            r10i = getPaddingTop();
            r11i = getPaddingBottom();
            r13i = r9i + r2i;
            r12i = r7i - r8i + r2i - r13i;
            r2f = 0.5f + r20f;
        } else {
            return;
        }
        this.k = true;
        r5i = this.b.getMeasuredWidth();
        r3i = this.c.getMeasuredWidth();
        r6i = this.d.getMeasuredWidth();
        r2i = r3i / 2;
        r7i = getWidth();
        r4i = getHeight();
        r8i = getPaddingLeft();
        r9i = getPaddingRight();
        r10i = getPaddingTop();
        r11i = getPaddingBottom();
        r13i = r9i + r2i;
        r12i = r7i - r8i + r2i - r13i;
        r2f = 0.5f + r20f;
        if (r2f > 1.0f) {
            r2f -= 1.0f;
        }
        r12i = r7i - r13i - ((int) (r2f * ((float) r12i))) - r3i / 2;
        r13i = r12i + r3i;
        r2i = this.b.getBaseline();
        r3i = this.c.getBaseline();
        int r14i = this.d.getBaseline();
        int r15i = Math.max(Math.max(r2i, r3i), r14i);
        r2i = r15i - r2i;
        r3i = r15i - r3i;
        r14i = r15i - r14i;
        r15i = Math.max(Math.max(this.b.getMeasuredHeight() + r2i, this.c.getMeasuredHeight() + r3i), this.d.getMeasuredHeight() + r14i);
        switch ((this.i & 112)) {
            case Base64.URL_SAFE:
                r10i = (((r4i - r10i) - r11i) - r15i) / 2;
                r4i = r10i + r2i;
                r3i += r10i;
                r2i = r10i + r14i;
                this.c.layout(r12i, r3i, r13i, this.c.getMeasuredHeight() + r3i);
                r3i = Math.min(r8i, r12i - this.h - r5i);
                this.b.layout(r3i, r4i, r5i + r3i, this.b.getMeasuredHeight() + r4i);
                r3i = Math.max(r7i - r9i - r6i, this.h + r13i);
                this.d.layout(r3i, r2i, r3i + r6i, this.d.getMeasuredHeight() + r2i);
                this.g = r20f;
                this.k = false;
            case 80:
                r10i = r4i - r11i - r15i;
                r4i = r10i + r2i;
                r3i += r10i;
                r2i = r10i + r14i;
                this.c.layout(r12i, r3i, r13i, this.c.getMeasuredHeight() + r3i);
                r3i = Math.min(r8i, r12i - this.h - r5i);
                this.b.layout(r3i, r4i, r5i + r3i, this.b.getMeasuredHeight() + r4i);
                r3i = Math.max(r7i - r9i - r6i, this.h + r13i);
                this.d.layout(r3i, r2i, r3i + r6i, this.d.getMeasuredHeight() + r2i);
                this.g = r20f;
                this.k = false;
        }
        r4i = r10i + r2i;
        r3i += r10i;
        r2i = r10i + r14i;
        this.c.layout(r12i, r3i, r13i, this.c.getMeasuredHeight() + r3i);
        r3i = Math.min(r8i, r12i - this.h - r5i);
        this.b.layout(r3i, r4i, r5i + r3i, this.b.getMeasuredHeight() + r4i);
        r3i = Math.max(r7i - r9i - r6i, this.h + r13i);
        this.d.layout(r3i, r2i, r3i + r6i, this.d.getMeasuredHeight() + r2i);
        this.g = r20f;
        this.k = false;
    }

    void a(int r7i, PagerAdapter r8_PagerAdapter) {
        int r0i;
        CharSequence r2_CharSequence;
        CharSequence r3_CharSequence = null;
        r0i = r8_PagerAdapter != null ? r8_PagerAdapter.getCount() : 0;
        this.j = true;
        r2_CharSequence = (r7i < 1 || r8_PagerAdapter == null) ? null : r8_PagerAdapter.getPageTitle(r7i - 1);
        this.b.setText(r2_CharSequence);
        TextView r4_TextView = this.c;
        r2_CharSequence = (r8_PagerAdapter == null || r7i >= r0i) ? null : r8_PagerAdapter.getPageTitle(r7i);
        r4_TextView.setText(r2_CharSequence);
        int r2i;
        if (r7i + 1 >= r0i || r8_PagerAdapter == null) {
            this.d.setText(r3_CharSequence);
            r0i = MeasureSpec.makeMeasureSpec((int) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * 0.8f), ExploreByTouchHelper.INVALID_ID);
            r2i = MeasureSpec.makeMeasureSpec(getHeight() - getPaddingTop() - getPaddingBottom(), ExploreByTouchHelper.INVALID_ID);
            this.b.measure(r0i, r2i);
            this.c.measure(r0i, r2i);
            this.d.measure(r0i, r2i);
            this.f = r7i;
            if (this.k) {
                a(r7i, this.g, false);
            }
            this.j = false;
        } else {
            r3_CharSequence = r8_PagerAdapter.getPageTitle(r7i + 1);
            this.d.setText(r3_CharSequence);
            r0i = MeasureSpec.makeMeasureSpec((int) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * 0.8f), ExploreByTouchHelper.INVALID_ID);
            r2i = MeasureSpec.makeMeasureSpec(getHeight() - getPaddingTop() - getPaddingBottom(), ExploreByTouchHelper.INVALID_ID);
            this.b.measure(r0i, r2i);
            this.c.measure(r0i, r2i);
            this.d.measure(r0i, r2i);
            this.f = r7i;
            if (this.k) {
                this.j = false;
            } else {
                a(r7i, this.g, false);
                this.j = false;
            }
        }
    }

    void a(PagerAdapter r2_PagerAdapter, PagerAdapter r3_PagerAdapter) {
        if (r2_PagerAdapter != null) {
            r2_PagerAdapter.unregisterDataSetObserver(this.l);
            this.m = null;
        }
        if (r3_PagerAdapter != null) {
            r3_PagerAdapter.registerDataSetObserver(this.l);
            this.m = new WeakReference(r3_PagerAdapter);
        }
        if (this.a != null) {
            this.f = -1;
            this.g = -1.0f;
            a(this.a.getCurrentItem(), r3_PagerAdapter);
            requestLayout();
        }
    }

    public int getTextSpacing() {
        return this.h;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent r0_ViewParent = getParent();
        if (r0_ViewParent instanceof ViewPager) {
            ViewPager r0_ViewPager = (ViewPager) r0_ViewParent;
            PagerAdapter r1_PagerAdapter = r0_ViewPager.getAdapter();
            r0_ViewPager.a(this.l);
            r0_ViewPager.a(this.l);
            this.a = r0_ViewPager;
            a(this.m != null ? (PagerAdapter) this.m.get() : null, r1_PagerAdapter);
        } else {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            a(this.a.getAdapter(), null);
            this.a.a(null);
            this.a.a(null);
            this.a = null;
        }
    }

    protected void onLayout(boolean r4z, int r5i, int r6i, int r7i, int r8i) {
        float r0f = 0.0f;
        if (this.a != null) {
            if (this.g >= 0.0f) {
                r0f = this.g;
            }
            a(this.f, r0f, true);
        }
    }

    protected void onMeasure(int r11i, int r12i) {
        int r0i = MeasureSpec.getMode(r11i);
        int r1i = MeasureSpec.getMode(r12i);
        int r2i = MeasureSpec.getSize(r11i);
        int r3i = MeasureSpec.getSize(r12i);
        if (r0i != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        } else {
            r0i = a();
            int r4i = getPaddingTop() + getPaddingBottom();
            int r6i = MeasureSpec.makeMeasureSpec((int) (((float) r2i) * 0.8f), ExploreByTouchHelper.INVALID_ID);
            int r5i = MeasureSpec.makeMeasureSpec(r3i - r4i, ExploreByTouchHelper.INVALID_ID);
            this.b.measure(r6i, r5i);
            this.c.measure(r6i, r5i);
            this.d.measure(r6i, r5i);
            if (r1i == 1073741824) {
                setMeasuredDimension(r2i, r3i);
            } else {
                setMeasuredDimension(r2i, Math.max(r0i, this.c.getMeasuredHeight() + r4i));
            }
        }
    }

    public void requestLayout() {
        if (!this.j) {
            super.requestLayout();
        }
    }

    public void setGravity(int r1i) {
        this.i = r1i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float r4f) {
        this.p = ((int) (255.0f * r4f)) & 255;
        int r0i = (this.p << 24) | (this.e & 16777215);
        this.b.setTextColor(r0i);
        this.d.setTextColor(r0i);
    }

    public void setTextColor(int r4i) {
        this.e = r4i;
        this.c.setTextColor(r4i);
        int r0i = (this.p << 24) | (this.e & 16777215);
        this.b.setTextColor(r0i);
        this.d.setTextColor(r0i);
    }

    public void setTextSize(int r2i, float r3f) {
        this.b.setTextSize(r2i, r3f);
        this.c.setTextSize(r2i, r3f);
        this.d.setTextSize(r2i, r3f);
    }

    public void setTextSpacing(int r1i) {
        this.h = r1i;
        requestLayout();
    }
}