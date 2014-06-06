package com.androidquery.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.lang.ref.WeakReference;

public class RatioDrawable extends BitmapDrawable {
    private float a;
    private WeakReference<ImageView> b;
    private boolean c;
    private Matrix d;
    private int e;
    private float f;

    public RatioDrawable(Resources r2_Resources, Bitmap r3_Bitmap, ImageView r4_ImageView, float r5f, float r6f) {
        super(r2_Resources, r3_Bitmap);
        this.b = new WeakReference(r4_ImageView);
        this.a = r5f;
        this.f = r6f;
        r4_ImageView.setScaleType(ScaleType.MATRIX);
        r4_ImageView.setImageMatrix(new Matrix());
        a(r4_ImageView, r3_Bitmap, false);
    }

    private float a(int r6i, int r7i) {
        return (this.f > 3.4028235E38f ? 1 : (this.f == 3.4028235E38f? 0 : -1)) != 0 ? (1.0f - this.f) / 2.0f : (1.5f - Math.max(1.0f, Math.min(1.5f, ((float) r7i) / ((float) r6i)))) / 2.0f + 0.25f;
    }

    private int a(int r4i, int r5i, int r6i) {
        float r0f = this.a;
        if (this.a == 3.4028235E38f) {
            r0f = ((float) r5i) / ((float) r4i);
        }
        return (int) (r0f * ((float) r6i));
    }

    private int a(ImageView r3_ImageView) {
        int r0i = 0;
        LayoutParams r1_LayoutParams = r3_ImageView.getLayoutParams();
        if (r1_LayoutParams != null) {
            r0i = r1_LayoutParams.width;
        }
        if (r0i <= 0) {
            r0i = r3_ImageView.getWidth();
        }
        return r0i > 0 ? r0i - r3_ImageView.getPaddingLeft() - r3_ImageView.getPaddingRight() : r0i;
    }

    private Matrix a(ImageView r9_ImageView, Bitmap r10_Bitmap) {
        float r0f = 0.0f;
        int r3i = r10_Bitmap.getWidth();
        if (this.d != null && r3i == this.e) {
            return this.d;
        }
        int r1i = r10_Bitmap.getHeight();
        int r4i = a(r9_ImageView);
        int r5i = a(r3i, r1i, r4i);
        if (r3i <= 0 || r1i <= 0 || r4i <= 0 || r5i <= 0) {
            return null;
        }
        if (this.d != null && r3i == this.e) {
            return this.d;
        }
        float r2f;
        float r1f;
        this.d = new Matrix();
        if (r3i * r5i >= r4i * r1i) {
            r2f = ((float) r5i) / ((float) r1i);
            r1f = (((float) r4i) - (((float) r3i) * r2f)) * 0.5f;
        } else {
            r2f = ((float) r4i) / ((float) r3i);
            r1f = r0f;
            r0f = (((float) r5i) - (((float) r1i) * r2f)) * a(r3i, r1i);
        }
        this.d.setScale(r2f, r2f);
        this.d.postTranslate(r1f, r0f);
        this.e = r3i;
        return this.d;
    }

    private void a(Canvas r6_Canvas, ImageView r7_ImageView, Bitmap r8_Bitmap) {
        Matrix r0_Matrix = a(r7_ImageView, r8_Bitmap);
        if (r0_Matrix != null) {
            int r1i = r7_ImageView.getPaddingTop() + r7_ImageView.getPaddingBottom();
            int r2i = r7_ImageView.getPaddingLeft() + r7_ImageView.getPaddingRight();
            if (r1i > 0 || r2i > 0) {
                r6_Canvas.clipRect(0, 0, r7_ImageView.getWidth() - r2i, r7_ImageView.getHeight() - r1i);
                r6_Canvas.drawBitmap(r8_Bitmap, r0_Matrix, getPaint());
            } else {
                r6_Canvas.drawBitmap(r8_Bitmap, r0_Matrix, getPaint());
            }
        }
        if (!this.c) {
            a(r7_ImageView, r8_Bitmap, true);
        }
    }

    private void a(ImageView r4_ImageView, Bitmap r5_Bitmap, boolean r6z) {
        int r0i = a(r4_ImageView);
        if (r0i <= 0) {
        } else {
            r0i = a(r5_Bitmap.getWidth(), r5_Bitmap.getHeight(), r0i) + r4_ImageView.getPaddingTop() + r4_ImageView.getPaddingBottom();
            LayoutParams r1_LayoutParams = r4_ImageView.getLayoutParams();
            if (r1_LayoutParams != null) {
                if (r0i != r1_LayoutParams.height) {
                    r1_LayoutParams.height = r0i;
                    r4_ImageView.setLayoutParams(r1_LayoutParams);
                }
                if (r6z) {
                    this.c = true;
                }
            }
        }
    }

    public void draw(Canvas r4_Canvas) {
        ImageView r0_ImageView = null;
        if (this.b != null) {
            r0_ImageView = (ImageView) this.b.get();
        }
        if (this.a == 0.0f || r0_ImageView == null) {
            super.draw(r4_Canvas);
        } else {
            a(r4_Canvas, r0_ImageView, getBitmap());
        }
    }
}