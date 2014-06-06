package qsbk.app.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;

public class CircularImage extends MaskedImage {
    public CircularImage(Context r1_Context) {
        super(r1_Context);
    }

    public CircularImage(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    public CircularImage(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        super(r1_Context, r2_AttributeSet, r3i);
    }

    public Bitmap createMask() {
        int r0i = getWidth();
        int r1i = getHeight();
        Bitmap r2_Bitmap = Bitmap.createBitmap(r0i, r1i, Config.ARGB_4444);
        Canvas r3_Canvas = new Canvas(r2_Bitmap);
        Paint r4_Paint = new Paint(1);
        r4_Paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        r3_Canvas.drawOval(new RectF(0.0f, 0.0f, (float) r0i, (float) r1i), r4_Paint);
        return r2_Bitmap;
    }
}