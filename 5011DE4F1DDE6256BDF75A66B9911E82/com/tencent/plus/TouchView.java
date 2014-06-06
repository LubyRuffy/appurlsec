package com.tencent.plus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
public class TouchView extends ImageView {
    final String a;
    public boolean b;
    private Matrix c;
    private Matrix d;
    private int e;
    private float f;
    private float g;
    private Bitmap h;
    private boolean i;
    private float j;
    private float k;
    private PointF l;
    private PointF m;
    private float n;
    private float o;
    private Rect p;

    public TouchView(Context r4_Context) {
        super(r4_Context);
        this.c = new Matrix();
        this.d = new Matrix();
        this.e = 0;
        this.f = 1.0f;
        this.g = 1.0f;
        this.i = false;
        this.a = "TouchView";
        this.l = new PointF();
        this.m = new PointF();
        this.n = 1.0f;
        this.o = 0.0f;
        this.b = false;
        this.p = new Rect();
        getDrawingRect(this.p);
        a();
    }

    public TouchView(Context r4_Context, AttributeSet r5_AttributeSet) {
        super(r4_Context, r5_AttributeSet);
        this.c = new Matrix();
        this.d = new Matrix();
        this.e = 0;
        this.f = 1.0f;
        this.g = 1.0f;
        this.i = false;
        this.a = "TouchView";
        this.l = new PointF();
        this.m = new PointF();
        this.n = 1.0f;
        this.o = 0.0f;
        this.b = false;
        this.p = new Rect();
        getDrawingRect(this.p);
        a();
    }

    private float a(MotionEvent r5_MotionEvent) {
        int r2i = 0;
        if (r5_MotionEvent.getPointerCount() < 2) {
            return 0.0f;
        }
        float r0f = r5_MotionEvent.getX(r2i) - r5_MotionEvent.getX(1);
        float r1f = r5_MotionEvent.getY(r2i) - r5_MotionEvent.getY(1);
        return FloatMath.sqrt(r0f * r0f + r1f * r1f);
    }

    private void a() {
    }

    private void a(PointF r8_PointF) {
        float r1f = 1.0f;
        if (this.h == null) {
        } else {
            float[] r0_floatA = new float[9];
            this.c.getValues(r0_floatA);
            float r2f = r0_floatA[2];
            float r3f = r0_floatA[5];
            float r0f = r0_floatA[0];
            float r4f = ((float) this.h.getWidth()) * r0f;
            float r5f = ((float) this.h.getHeight()) * r0f;
            r0f = ((float) this.p.left) - r2f;
            if (r0f <= 1.0f) {
                r0f = 1.0f;
            }
            r2f = r2f + r4f - ((float) this.p.right);
            if (r2f <= 1.0f) {
                r2f = 1.0f;
            }
            r4f = (r0f * ((float) this.p.width())) / (r2f + r0f) + ((float) this.p.left);
            r2f = ((float) this.p.top) - r3f;
            r0f = r3f + r5f - ((float) this.p.bottom);
            if (r2f <= 1.0f) {
                r2f = 1.0f;
            }
            if (r0f <= 1.0f) {
                r8_PointF.set(r4f, (((float) this.p.height()) * r2f) / (r2f + r1f) + ((float) this.p.top));
            } else {
                r1f = r0f;
                r8_PointF.set(r4f, (((float) this.p.height()) * r2f) / (r2f + r1f) + ((float) this.p.top));
            }
        }
    }

    private void b() {
        if (this.h == null) {
        } else {
            float r4f = (float) this.p.width();
            float r5f = (float) this.p.height();
            float[] r6_floatA = new float[9];
            this.c.getValues(r6_floatA);
            float r3f = r6_floatA[2];
            float r2f = r6_floatA[5];
            float r7f = r6_floatA[0];
            Animation r0_Animation = null;
            if (r7f > this.f) {
                this.o = this.f / r7f;
                this.c.postScale(this.o, this.o, this.m.x, this.m.y);
                setImageMatrix(this.c);
                r0_Animation = new ScaleAnimation(1.0f / this.o, 1.0f, 1.0f / this.o, 1.0f, this.m.x, this.m.y);
            } else if (r7f < this.g) {
                this.o = this.g / r7f;
                this.c.postScale(this.o, this.o, this.m.x, this.m.y);
                r0_Animation = new ScaleAnimation(1.0f, this.o, 1.0f, this.o, this.m.x, this.m.y);
            } else {
                int r1i = 0;
                float r8f = ((float) this.h.getWidth()) * r7f;
                r7f *= (float) this.h.getHeight();
                float r9f = ((float) this.p.left) - r3f;
                float r10f = ((float) this.p.top) - r2f;
                if (r9f < 0.0f) {
                    r3f = (float) this.p.left;
                    r1i = 1;
                }
                if (r10f < 0.0f) {
                    r2f = (float) this.p.top;
                    r1i = 1;
                }
                r10f = r7f - r10f;
                if (r8f - r9f < r4f) {
                    r3f = ((float) this.p.left) - r8f - r4f;
                    r1i = 1;
                }
                if (r10f < r5f) {
                    r2f = ((float) this.p.top) - r7f - r5f;
                    r1i = 1;
                }
                if (r1i != 0) {
                    r6_floatA[2] = r3f;
                    r6_floatA[5] = r2f;
                    this.c.setValues(r6_floatA);
                    setImageMatrix(this.c);
                    r0_Animation = new TranslateAnimation(r6_floatA[2] - r3f, 0.0f, r6_floatA[5] - r2f, 0.0f);
                } else {
                    setImageMatrix(this.c);
                }
            }
            if (r0_Animation != null) {
                this.i = true;
                r0_Animation.setDuration(300);
                startAnimation(r0_Animation);
                new Thread(new d(this)).start();
            }
        }
    }

    private void c() {
        if (this.h == null) {
        } else {
            float[] r0_floatA = new float[9];
            this.c.getValues(r0_floatA);
            float r1f = Math.max(((float) this.p.width()) / ((float) this.h.getWidth()), ((float) this.p.height()) / ((float) this.h.getHeight()));
            this.j = ((float) this.p.left) - ((((float) this.h.getWidth()) * r1f) - ((float) this.p.width())) / 2.0f;
            this.k = ((float) this.p.top) - ((((float) this.h.getHeight()) * r1f) - ((float) this.p.height())) / 2.0f;
            r0_floatA[2] = this.j;
            r0_floatA[5] = this.k;
            r0_floatA[4] = r1f;
            r0_floatA[0] = r1f;
            this.c.setValues(r0_floatA);
            this.f = Math.min(2048.0f / ((float) this.h.getWidth()), 2048.0f / ((float) this.h.getHeight()));
            this.g = r1f;
            if (this.f < this.g) {
                this.f = this.g;
            }
            setImageMatrix(this.c);
        }
    }

    public void a(Rect r2_Rect) {
        this.p = r2_Rect;
        if (this.h != null) {
            c();
        }
    }

    public boolean onTouchEvent(MotionEvent r6_MotionEvent) {
        if (this.i) {
            return true;
        }
        switch ((r6_MotionEvent.getAction() & 255)) {
            case XListViewHeader.STATE_NORMAL:
                this.c.set(getImageMatrix());
                this.d.set(this.c);
                this.l.set(r6_MotionEvent.getX(), r6_MotionEvent.getY());
                this.e = 1;
                break;
            case XListViewHeader.STATE_READY:
            case ShareUtils.SHARE_COPY:
                b();
                this.e = 0;
                break;
            case XListViewHeader.STATE_REFRESHING:
                if (this.e == 1) {
                    this.c.set(this.d);
                    this.c.postTranslate(r6_MotionEvent.getX() - this.l.x, r6_MotionEvent.getY() - this.l.y);
                    setImageMatrix(this.c);
                } else if (this.e == 2) {
                    this.c.set(this.c);
                    float r0f = a(r6_MotionEvent);
                    if (r0f > 10.0f) {
                        this.c.set(this.d);
                        r0f /= this.n;
                        this.c.postScale(r0f, r0f, this.m.x, this.m.y);
                    }
                    setImageMatrix(this.c);
                }
                break;
            case ShareUtils.SHARE_SMS:
                this.n = a(r6_MotionEvent);
                if (this.n > 10.0f) {
                    this.d.set(this.c);
                    a(this.m);
                    this.e = 2;
                }
                break;
        }
        this.b = true;
        return true;
    }

    public void setImageBitmap(Bitmap r1_Bitmap) {
        super.setImageBitmap(r1_Bitmap);
        this.h = r1_Bitmap;
        if (r1_Bitmap != null) {
            this.h = r1_Bitmap;
        }
    }
}