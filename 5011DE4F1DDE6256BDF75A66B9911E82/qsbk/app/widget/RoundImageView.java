package qsbk.app.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;

public class RoundImageView extends MaskedImage {
    private float a;
    private float b;

    public RoundImageView(Context r2_Context) {
        super(r2_Context);
        this.a = 10.0f;
        this.b = 10.0f;
    }

    public RoundImageView(Context r2_Context, AttributeSet r3_AttributeSet) {
        super(r2_Context, r3_AttributeSet);
        this.a = 10.0f;
        this.b = 10.0f;
    }

    public RoundImageView(Context r2_Context, AttributeSet r3_AttributeSet, int r4i) {
        super(r2_Context, r3_AttributeSet, r4i);
        this.a = 10.0f;
        this.b = 10.0f;
    }

    public Bitmap createMask() {
        int r0i = getWidth();
        int r1i = getHeight();
        Bitmap r2_Bitmap = Bitmap.createBitmap(r0i, r1i, Config.ARGB_4444);
        Canvas r3_Canvas = new Canvas(r2_Bitmap);
        Paint r4_Paint = new Paint(1);
        r4_Paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        r3_Canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) r0i, (float) r1i), this.a, this.b, r4_Paint);
        return r2_Bitmap;
    }

    public void setRoundX(float r1f) {
        this.a = r1f;
    }

    public void setRoundY(float r1f) {
        this.b = r1f;
    }
}