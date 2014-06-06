package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import qsbk.app.widget.listview.XListViewHeader;

public class LinearLayoutICS extends LinearLayout {
    private final Drawable a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    public LinearLayoutICS(Context r5_Context, AttributeSet r6_AttributeSet) {
        boolean r0z = true;
        super(r5_Context, r6_AttributeSet);
        TypedArray r2_TypedArray = r5_Context.obtainStyledAttributes(r6_AttributeSet, R.styleable.LinearLayoutICS);
        this.a = r2_TypedArray.getDrawable(0);
        if (this.a != null) {
            this.b = this.a.getIntrinsicWidth();
            this.c = this.a.getIntrinsicHeight();
        } else {
            this.b = 0;
            this.c = 0;
        }
        this.d = r2_TypedArray.getInt(1, 0);
        this.e = r2_TypedArray.getDimensionPixelSize(XListViewHeader.STATE_REFRESHING, 0);
        r2_TypedArray.recycle();
        if (this.a == null) {
            setWillNotDraw(r0z);
        } else {
            r0z = false;
            setWillNotDraw(r0z);
        }
    }

    void a(Canvas r6_Canvas) {
        int r2i = getChildCount();
        int r1i = 0;
        while (r1i < r2i) {
            View r3_View = getChildAt(r1i);
            if (r3_View == null || r3_View.getVisibility() == 8 || (!a(r1i))) {
                r1i++;
            } else {
                a(r6_Canvas, r3_View.getTop() - ((LayoutParams) r3_View.getLayoutParams()).topMargin);
                r1i++;
            }
        }
        if (a(r2i)) {
            View r0_View = getChildAt(r2i - 1);
            a(r6_Canvas, r0_View == null ? getHeight() - getPaddingBottom() - this.c : r0_View.getBottom());
        }
    }

    void a(Canvas r5_Canvas, int r6i) {
        this.a.setBounds(getPaddingLeft() + this.e, r6i, getWidth() - getPaddingRight() - this.e, this.c + r6i);
        this.a.draw(r5_Canvas);
    }

    protected boolean a(int r6i) {
        if (r6i == 0) {
            return (this.d & 1) != 0;
        } else if (r6i == getChildCount()) {
            return (this.d & 4) != 0;
        } else {
            if ((this.d & 2) == 0) {
                return false;
            }
            int r2i = r6i - 1;
            while (r2i >= 0) {
                if (getChildAt(r2i).getVisibility() != 8) {
                    return true;
                }
                r2i--;
            }
            return false;
        }
    }

    void b(Canvas r6_Canvas) {
        int r2i = getChildCount();
        int r1i = 0;
        while (r1i < r2i) {
            View r3_View = getChildAt(r1i);
            if (r3_View == null || r3_View.getVisibility() == 8 || (!a(r1i))) {
                r1i++;
            } else {
                b(r6_Canvas, r3_View.getLeft() - ((LayoutParams) r3_View.getLayoutParams()).leftMargin);
                r1i++;
            }
        }
        if (a(r2i)) {
            View r0_View = getChildAt(r2i - 1);
            b(r6_Canvas, r0_View == null ? getWidth() - getPaddingRight() - this.b : r0_View.getRight());
        }
    }

    void b(Canvas r6_Canvas, int r7i) {
        this.a.setBounds(r7i, getPaddingTop() + this.e, this.b + r7i, getHeight() - getPaddingBottom() - this.e);
        this.a.draw(r6_Canvas);
    }

    public int getSupportDividerWidth() {
        return this.b;
    }

    protected void measureChildWithMargins(View r6_View, int r7i, int r8i, int r9i, int r10i) {
        if (this.a != null) {
            int r1i = indexOfChild(r6_View);
            int r2i = getChildCount();
            LayoutParams r0_LayoutParams = (LayoutParams) r6_View.getLayoutParams();
            if (getOrientation() == 1) {
                if (a(r1i)) {
                    r0_LayoutParams.topMargin = this.c;
                } else if (r1i == r2i - 1 && a(r2i)) {
                    r0_LayoutParams.bottomMargin = this.c;
                } else {
                    super.measureChildWithMargins(r6_View, r7i, r8i, r9i, r10i);
                }
            } else if (a(r1i)) {
                r0_LayoutParams.leftMargin = this.b;
            } else if (r1i == r2i - 1 && a(r2i)) {
                r0_LayoutParams.rightMargin = this.b;
            } else {
                super.measureChildWithMargins(r6_View, r7i, r8i, r9i, r10i);
            }
        }
        super.measureChildWithMargins(r6_View, r7i, r8i, r9i, r10i);
    }

    protected void onDraw(Canvas r3_Canvas) {
        if (this.a == null) {
        } else if (getOrientation() == 1) {
            a(r3_Canvas);
        } else {
            b(r3_Canvas);
        }
    }
}