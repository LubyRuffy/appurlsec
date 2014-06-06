package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ActionBarOverlayLayout extends FrameLayout {
    static final int[] a;
    private int b;
    private ActionBar c;
    private final Rect d;

    static {
        int[] r0_intA = new int[1];
        r0_intA[0] = R.attr.actionBarSize;
        a = r0_intA;
    }

    public ActionBarOverlayLayout(Context r3_Context) {
        super(r3_Context);
        this.d = new Rect(0, 0, 0, 0);
        a(r3_Context);
    }

    public ActionBarOverlayLayout(Context r3_Context, AttributeSet r4_AttributeSet) {
        super(r3_Context, r4_AttributeSet);
        this.d = new Rect(0, 0, 0, 0);
        a(r3_Context);
    }

    private void a(Context r4_Context) {
        TypedArray r0_TypedArray = getContext().getTheme().obtainStyledAttributes(a);
        this.b = r0_TypedArray.getDimensionPixelSize(0, 0);
        r0_TypedArray.recycle();
    }

    public void setActionBar(ActionBar r1_ActionBar) {
        this.c = r1_ActionBar;
    }
}