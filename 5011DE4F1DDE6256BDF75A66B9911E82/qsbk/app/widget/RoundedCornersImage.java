package qsbk.app.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import qsbk.app.R;

public class RoundedCornersImage extends MaskedImage {
    private final int a;

    public RoundedCornersImage(Context r2_Context) {
        super(r2_Context);
        this.a = 0;
    }

    public RoundedCornersImage(Context r3_Context, AttributeSet r4_AttributeSet) {
        super(r3_Context, r4_AttributeSet);
        this.a = r3_Context.obtainStyledAttributes(r4_AttributeSet, R.styleable.RoundedCornersImage).getDimensionPixelSize(0, 0);
    }

    public RoundedCornersImage(Context r3_Context, AttributeSet r4_AttributeSet, int r5i) {
        super(r3_Context, r4_AttributeSet, r5i);
        this.a = r3_Context.obtainStyledAttributes(r4_AttributeSet, R.styleable.RoundedCornersImage).getDimensionPixelSize(0, 0);
    }

    public Bitmap createMask() {
        int r0i = getWidth();
        int r1i = getHeight();
        Bitmap r2_Bitmap = Bitmap.createBitmap(r0i, r1i, Config.ARGB_8888);
        Canvas r3_Canvas = new Canvas(r2_Bitmap);
        Paint r4_Paint = new Paint(1);
        r4_Paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        r3_Canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) r0i, (float) r1i), (float) this.a, (float) this.a, r4_Paint);
        return r2_Bitmap;
    }
}