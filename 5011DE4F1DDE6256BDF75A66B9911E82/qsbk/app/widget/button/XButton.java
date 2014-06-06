package qsbk.app.widget.button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import com.tencent.mm.sdk.platformtools.Util;
import qsbk.app.widget.listview.XListViewHeader;

public class XButton extends Button {
    private Bitmap a;
    private Drawable b;

    public XButton(Context r2_Context) {
        super(r2_Context);
        this.a = null;
        this.b = null;
    }

    public XButton(Context r2_Context, AttributeSet r3_AttributeSet) {
        super(r2_Context, r3_AttributeSet);
        this.a = null;
        this.b = null;
    }

    public XButton(Context r2_Context, AttributeSet r3_AttributeSet, int r4i) {
        super(r2_Context, r3_AttributeSet, r4i);
        this.a = null;
        this.b = null;
    }

    private int a(float r3f) {
        int r1i = (int) r3f;
        return r1i > 255 ? Util.MASK_8BIT : r1i;
    }

    private int a(int r8i) {
        return a((((float) (r8i & 255)) * 0.5f) + ((float) true)) | ((a((((float) ((65280 & r8i) >> 8)) * 0.5f) + ((float) true)) << 8) | ((a((((float) ((16711680 & r8i) >> 16)) * 0.5f) + ((float) true)) << 16) | ((r8i >> 24) << 24)));
    }

    public boolean onTouchEvent(MotionEvent r9_MotionEvent) {
        int r2i = 0;
        if (this.b == null) {
            this.b = getBackground();
        }
        if (this.a == null) {
            int r3i = getWidth();
            int r7i = getHeight();
            Bitmap r0_Bitmap = Bitmap.createBitmap(r3i, r7i, Config.ARGB_8888);
            Canvas r1_Canvas = new Canvas(r0_Bitmap);
            layout(0, 0, r3i, r7i);
            draw(r1_Canvas);
            int[] r1_intA = new int[(r3i * r7i)];
            r0_Bitmap.getPixels(r1_intA, 0, r3i, 0, 0, r3i, r7i);
            while (r2i != r1_intA.length) {
                r1_intA[r2i] = a(r1_intA[r2i]);
                r2i++;
            }
            this.a = Bitmap.createBitmap(r1_intA, r3i, r7i, Config.ARGB_8888);
            setWidth(r3i);
            setHeight(r7i);
        }
        switch (r9_MotionEvent.getAction()) {
            case XListViewHeader.STATE_NORMAL:
            case XListViewHeader.STATE_REFRESHING:
                setBackgroundDrawable(new BitmapDrawable(this.a));
                return super.onTouchEvent(r9_MotionEvent);
        }
        setBackgroundDrawable(this.b);
        return super.onTouchEvent(r9_MotionEvent);
    }

    public void reset() {
        this.b = null;
        this.a = null;
    }
}