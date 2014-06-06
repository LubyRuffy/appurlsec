package com.tencent.plus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

// compiled from: ProGuard
public class MaskView extends View {
    private static String a;
    private Rect b;
    private Paint c;

    static {
        a = "MaskView";
    }

    public MaskView(Context r1_Context) {
        super(r1_Context);
        b();
    }

    public MaskView(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
        b();
    }

    private void b() {
        this.c = new Paint();
    }

    public Rect a() {
        if (this.b == null) {
            this.b = new Rect();
            int r0i = getMeasuredWidth();
            int r1i = getMeasuredHeight();
            int r2i = Math.min(Math.min(r1i - 60 - 80, r0i), 640);
            r0i = (r0i - r2i) / 2;
            r1i = (r1i - r2i) / 2;
            this.b.set(r0i, r1i, r0i + r2i, r2i + r1i);
        }
        return this.b;
    }

    protected void onDraw(Canvas r12_Canvas) {
        super.onDraw(r12_Canvas);
        Rect r6_Rect = a();
        int r7i = getMeasuredWidth();
        int r8i = getMeasuredHeight();
        this.c.setStyle(Style.FILL);
        this.c.setColor(Color.argb(100, 0, 0, 0));
        r12_Canvas.drawRect(0.0f, 0.0f, (float) r7i, (float) r6_Rect.top, this.c);
        r12_Canvas.drawRect(0.0f, (float) r6_Rect.bottom, (float) r7i, (float) r8i, this.c);
        r12_Canvas.drawRect(0.0f, (float) r6_Rect.top, (float) r6_Rect.left, (float) r6_Rect.bottom, this.c);
        r12_Canvas.drawRect((float) r6_Rect.right, (float) r6_Rect.top, (float) r7i, (float) r6_Rect.bottom, this.c);
        r12_Canvas.drawColor(Color.argb(100, 0, 0, 0));
        this.c.setStyle(Style.STROKE);
        this.c.setColor(-1);
        r12_Canvas.drawRect((float) r6_Rect.left, (float) r6_Rect.top, (float) (r6_Rect.right - 1), (float) r6_Rect.bottom, this.c);
    }
}