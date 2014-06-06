package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

// compiled from: ProGuard
public class KeyboardDetectorRelativeLayout extends RelativeLayout {
    private static final String a;
    private Rect b;
    private boolean c;
    private IKeyboardChanged d;

    // compiled from: ProGuard
    public interface IKeyboardChanged {
        public void onKeyboardHidden();

        public void onKeyboardShown(int r1i);
    }

    static {
        a = KeyboardDetectorRelativeLayout.class.getName();
    }

    public KeyboardDetectorRelativeLayout(Context r3_Context) {
        super(r3_Context);
        this.b = null;
        this.c = false;
        this.d = null;
        if (this.b == null) {
            this.b = new Rect();
        }
    }

    public KeyboardDetectorRelativeLayout(Context r3_Context, AttributeSet r4_AttributeSet) {
        super(r3_Context, r4_AttributeSet);
        this.b = null;
        this.c = false;
        this.d = null;
        if (this.b == null) {
            this.b = new Rect();
        }
    }

    public void a(IKeyboardChanged r1_IKeyboardChanged) {
        this.d = r1_IKeyboardChanged;
    }

    protected void onMeasure(int r5i, int r6i) {
        int r1i = MeasureSpec.getSize(r6i);
        Activity r0_Activity = (Activity) getContext();
        r0_Activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        int r0i = r0_Activity.getWindowManager().getDefaultDisplay().getHeight() - this.b.top - r1i;
        if (this.d == null || r1i == 0) {
            super.onMeasure(r5i, r6i);
        } else if (r0i > 100) {
            this.d.onKeyboardShown(Math.abs(this.b.height()) - getPaddingBottom() - getPaddingTop());
            super.onMeasure(r5i, r6i);
        } else {
            this.d.onKeyboardHidden();
            super.onMeasure(r5i, r6i);
        }
    }
}