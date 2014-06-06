package qsbk.app.widget.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import java.util.Observable;
import java.util.Observer;
import qsbk.app.R;

public class BarcodeView extends View implements Observer {
    private static final String a;
    private final Paint b;
    private final Rect c;
    private final Rect d;
    private final Rect e;
    private Bitmap f;
    private float g;
    private ZoomState h;
    private Bitmap i;
    private final Rect j;
    private Bitmap k;
    private final Rect l;
    private final Paint m;
    private Rect n;
    private OnCloseListener o;

    public static interface OnCloseListener {
        public void onClose();
    }

    static {
        a = BarcodeView.class.getName();
    }

    public BarcodeView(Context r4_Context, AttributeSet r5_AttributeSet) {
        super(r4_Context, r5_AttributeSet);
        this.b = new Paint(2);
        this.c = new Rect();
        this.d = new Rect();
        this.e = new Rect();
        this.j = new Rect();
        this.l = new Rect();
        this.m = new Paint();
        this.m.setColor(getResources().getColor(R.color.qb_mask_bg));
    }

    private void a() {
        if (this.f != null) {
            this.g = (((float) this.f.getWidth()) / ((float) this.f.getHeight())) / (((float) getWidth()) / ((float) getHeight()));
        }
    }

    private void a(Canvas r6_Canvas, Rect r7_Rect) {
        if (this.k != null) {
            int r0i = this.k.getWidth();
            int r1i = this.k.getHeight();
            r7_Rect.left -= r0i / 2;
            r7_Rect.top -= r1i / 2;
            this.l.bottom = r1i + this.l.top;
            this.l.right = r0i + this.l.left;
            r6_Canvas.drawBitmap(this.k, null, this.l, this.b);
        }
    }

    private synchronized void b() {
        if (this.n != null) {
            int r0i = this.e.right - this.e.left;
            int r1i = this.e.bottom - this.e.top;
            if (this.e.left <= this.n.left) {
                this.e.left = this.n.left;
                this.e.right = this.e.left + r0i;
            }
            if (this.e.right >= this.n.right) {
                this.e.right = this.n.right;
                this.e.left = this.e.right - r0i;
            }
            if (this.e.top <= this.n.top) {
                this.e.top = this.n.top;
                this.e.bottom = this.e.top + r1i;
            }
            if (this.e.bottom >= this.n.bottom) {
                this.e.bottom = this.n.bottom;
                this.e.top = this.e.bottom - r1i;
            }
        }
    }

    private void b(Canvas r6_Canvas, Rect r7_Rect) {
        if (this.i != null) {
            int r0i = this.i.getWidth();
            int r1i = this.i.getHeight();
            r7_Rect.right += r0i / 2;
            r7_Rect.bottom += r1i / 2;
            this.j.top = this.j.bottom - r1i;
            this.j.left = this.j.right - r0i;
            r6_Canvas.drawBitmap(this.i, null, this.j, this.b);
        }
    }

    public Rect getInnerRect() {
        return this.e;
    }

    public Bitmap getLeftTopImage() {
        return this.k;
    }

    public OnCloseListener getOnCloseListener() {
        return this.o;
    }

    public Bitmap getRightBottomImage() {
        return this.i;
    }

    public Rect getRightBottomRect() {
        return this.j;
    }

    public ZoomState getZoomState() {
        return this.h;
    }

    protected void onDraw(Canvas r15_Canvas) {
        if (this.f == null || this.h == null) {
        } else {
            int r0i = getWidth();
            int r1i = getHeight();
            int r2i = this.f.getWidth();
            int r3i = this.f.getHeight();
            float r4f = this.h.getPanX();
            float r5f = this.h.getPanY();
            float r6f = (this.h.getZoomX(this.g) * ((float) r0i)) / ((float) r2i);
            float r7f = (this.h.getZoomY(this.g) * ((float) r1i)) / ((float) r3i);
            this.c.left = (int) (r4f * ((float) r2i) - ((float) r0i) / (r6f * 2.0f));
            this.c.top = (int) (r5f * ((float) r3i) - ((float) r1i) / (r7f * 2.0f));
            this.c.right = (int) (((float) r0i) / r6f + ((float) this.c.left));
            this.c.bottom = (int) (((float) r1i) / r7f + ((float) this.c.top));
            if (this.c.left >= 0 || this.c.top >= 0 || this.c.bottom <= r3i || this.c.right <= r2i) {
                this.c.left = this.d.left;
                this.c.bottom = this.d.bottom;
                this.c.right = this.d.right;
                this.c.top = this.d.top;
                if (this.c.left >= 0) {
                    this.c.left = 1;
                }
                if (this.c.right <= r2i) {
                    this.c.right = r2i + 1;
                }
                if (this.c.top >= 0) {
                    this.c.top = 1;
                }
                if (this.c.bottom <= r3i) {
                    this.c.bottom = r3i;
                }
                r15_Canvas.drawRect(this.e, this.m);
                r15_Canvas.drawBitmap(this.f, this.c, this.e, this.b);
                a(r15_Canvas, this.e);
                b(r15_Canvas, this.e);
            } else {
                Rect r0_Rect;
                this.e.left = getLeft();
                this.e.top = getTop();
                this.e.right = getRight();
                this.e.bottom = getBottom();
                if (this.c.left < 0) {
                    r0_Rect = this.e;
                    r0_Rect.left = (int) (((float) r0_Rect.left) + ((float) (-this.c.left)) * r6f);
                    this.c.left = 0;
                }
                if (this.c.right > r2i) {
                    r0_Rect = this.e;
                    r0_Rect.right = (int) (((float) r0_Rect.right) - ((float) (this.c.right - r2i)) * r6f);
                    this.c.right = r2i;
                }
                if (this.c.top < 0) {
                    r0_Rect = this.e;
                    r0_Rect.top = (int) (((float) r0_Rect.top) + ((float) (-this.c.top)) * r7f);
                    this.c.top = 0;
                }
                if (this.c.bottom > r3i) {
                    r0_Rect = this.e;
                    r0_Rect.bottom = (int) (((float) r0_Rect.bottom) - ((float) (this.c.bottom - r3i)) * r7f);
                    this.c.bottom = r3i;
                }
                b();
                this.d.left = this.c.left;
                this.d.bottom = this.c.bottom;
                this.d.right = this.c.right;
                this.d.top = this.c.top;
                r15_Canvas.drawRect(this.e, this.m);
                r15_Canvas.drawBitmap(this.f, this.c, this.e, this.b);
                a(r15_Canvas, this.e);
                b(r15_Canvas, this.e);
            }
        }
    }

    protected void onLayout(boolean r1z, int r2i, int r3i, int r4i, int r5i) {
        super.onLayout(r1z, r2i, r3i, r4i, r5i);
        a();
    }

    public void setBoundsRect(Rect r1_Rect) {
        this.n = r1_Rect;
    }

    public void setImage(Bitmap r1_Bitmap) {
        this.f = r1_Bitmap;
        a();
        invalidate();
    }

    public void setLeftTopImage(Bitmap r1_Bitmap) {
        this.k = r1_Bitmap;
    }

    public void setOnCloseListener(OnCloseListener r1_OnCloseListener) {
        this.o = r1_OnCloseListener;
    }

    public void setRightBottomImage(Bitmap r1_Bitmap) {
        this.i = r1_Bitmap;
    }

    public void setZoomState(ZoomState r2_ZoomState) {
        if (this.h != null) {
            this.h.deleteObserver(this);
        }
        this.h = r2_ZoomState;
        this.h.addObserver(this);
        invalidate();
    }

    public void update(Observable r1_Observable, Object r2_Object) {
        invalidate();
    }
}