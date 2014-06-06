package android.support.v4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import qsbk.app.widget.listview.XListViewHeader;

public class PagerTabStrip extends PagerTitleStrip {
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private final Paint l;
    private final Rect m;
    private int n;
    private boolean o;
    private boolean p;
    private int q;
    private boolean r;
    private float s;
    private float t;
    private int u;

    public PagerTabStrip(Context r2_Context) {
        this(r2_Context, null);
    }

    public PagerTabStrip(Context r7_Context, AttributeSet r8_AttributeSet) {
        super(r7_Context, r8_AttributeSet);
        this.l = new Paint();
        this.m = new Rect();
        this.n = 255;
        this.o = false;
        this.p = false;
        this.f = this.e;
        this.l.setColor(this.f);
        float r0f = r7_Context.getResources().getDisplayMetrics().density;
        this.g = (int) (3.0f * r0f + 0.5f);
        this.h = (int) (6.0f * r0f + 0.5f);
        this.i = (int) (64.0f * r0f);
        this.k = (int) (16.0f * r0f + 0.5f);
        this.q = (int) (1.0f * r0f + 0.5f);
        this.j = (int) (r0f * 32.0f + 0.5f);
        this.u = ViewConfiguration.get(r7_Context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.b.setFocusable(true);
        this.b.setOnClickListener(new m(this));
        this.d.setFocusable(true);
        this.d.setOnClickListener(new n(this));
        if (getBackground() == null) {
            this.o = true;
        }
    }

    int a() {
        return Math.max(super.a(), this.j);
    }

    void a(int r7i, float r8f, boolean r9z) {
        Rect r0_Rect = this.m;
        int r1i = getHeight();
        int r4i = r1i - this.g;
        r0_Rect.set(this.c.getLeft() - this.k, r4i, this.c.getRight() + this.k, r1i);
        super.a(r7i, r8f, r9z);
        this.n = (int) ((Math.abs(r8f - 0.5f) * 2.0f) * 255.0f);
        r0_Rect.union(this.c.getLeft() - this.k, r4i, this.c.getRight() + this.k, r1i);
        invalidate(r0_Rect);
    }

    public boolean getDrawFullUnderline() {
        return this.o;
    }

    public int getTabIndicatorColor() {
        return this.f;
    }

    protected void onDraw(Canvas r9_Canvas) {
        super.onDraw(r9_Canvas);
        int r6i = getHeight();
        this.l.setColor((this.n << 24) | (this.f & 16777215));
        r9_Canvas.drawRect((float) (this.c.getLeft() - this.k), (float) (r6i - this.g), (float) (this.c.getRight() + this.k), (float) r6i, this.l);
        if (this.o) {
            this.l.setColor(-16777216 | (this.f & 16777215));
            r9_Canvas.drawRect((float) getPaddingLeft(), (float) (r6i - this.q), (float) (getWidth() - getPaddingRight()), (float) r6i, this.l);
        }
    }

    public boolean onTouchEvent(MotionEvent r6_MotionEvent) {
        int r2i = r6_MotionEvent.getAction();
        if (r2i != 0 && this.r) {
            return false;
        }
        float r3f = r6_MotionEvent.getX();
        float r4f = r6_MotionEvent.getY();
        switch (r2i) {
            case XListViewHeader.STATE_NORMAL:
                this.s = r3f;
                this.t = r4f;
                this.r = false;
                break;
            case XListViewHeader.STATE_READY:
                if (r3f < ((float) (this.c.getLeft() - this.k))) {
                    this.a.setCurrentItem(this.a.getCurrentItem() - 1);
                } else if (r3f > ((float) (this.c.getRight() + this.k))) {
                    this.a.setCurrentItem(this.a.getCurrentItem() + 1);
                }
                break;
            case XListViewHeader.STATE_REFRESHING:
                if (Math.abs(r3f - this.s) > ((float) this.u) || Math.abs(r4f - this.t) > ((float) this.u)) {
                    this.r = true;
                }
                break;
        }
        return true;
    }

    public void setBackgroundColor(int r2i) {
        super.setBackgroundColor(r2i);
        if (!this.p) {
            this.o = (-16777216 & r2i) == 0;
        }
    }

    public void setBackgroundDrawable(Drawable r2_Drawable) {
        super.setBackgroundDrawable(r2_Drawable);
        if (!this.p) {
            this.o = r2_Drawable == null;
        }
    }

    public void setBackgroundResource(int r2i) {
        super.setBackgroundResource(r2i);
        if (!this.p) {
            this.o = r2i == 0;
        }
    }

    public void setDrawFullUnderline(boolean r2z) {
        this.o = r2z;
        this.p = true;
        invalidate();
    }

    public void setPadding(int r2i, int r3i, int r4i, int r5i) {
        if (r5i < this.h) {
            r5i = this.h;
        }
        super.setPadding(r2i, r3i, r4i, r5i);
    }

    public void setTabIndicatorColor(int r3i) {
        this.f = r3i;
        this.l.setColor(this.f);
        invalidate();
    }

    public void setTabIndicatorColorResource(int r2i) {
        setTabIndicatorColor(getContext().getResources().getColor(r2i));
    }

    public void setTextSpacing(int r2i) {
        if (r2i < this.i) {
            r2i = this.i;
        }
        super.setTextSpacing(r2i);
    }
}