package qsbk.app.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.qiubai.library.adview.util.AdViewUtil;

public abstract class MaskedImage extends ImageView {
    private static final Xfermode a;
    private Bitmap b;
    private Paint c;

    static {
        a = new PorterDuffXfermode(Mode.DST_IN);
    }

    public MaskedImage(Context r1_Context) {
        super(r1_Context);
    }

    public MaskedImage(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    public MaskedImage(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        super(r1_Context, r2_AttributeSet, r3i);
    }

    public abstract Bitmap createMask();

    protected void onDraw(Canvas r10_Canvas) {
        Drawable r7_Drawable = getDrawable();
        if (r7_Drawable == null) {
        } else {
            if (this.c == null) {
                this.c = new Paint();
                this.c.setFilterBitmap(false);
                this.c.setXfermode(a);
            }
            float r3f = (float) getWidth();
            float r4f = (float) getHeight();
            int r0i = r10_Canvas.saveLayer(0.0f, 0.0f, r3f, r4f, null, AdViewUtil.NETWORK_TYPE_VPON);
            r7_Drawable.setBounds(0, 0, (int) r3f, (int) r4f);
            r7_Drawable.draw(r10_Canvas);
            if (this.b == null || this.b.isRecycled()) {
                this.b = createMask();
                r10_Canvas.drawBitmap(this.b, 0.0f, 0.0f, this.c);
                r10_Canvas.restoreToCount(r0i);
            } else {
                r10_Canvas.drawBitmap(this.b, 0.0f, 0.0f, this.c);
                r10_Canvas.restoreToCount(r0i);
            }
        }
    }
}