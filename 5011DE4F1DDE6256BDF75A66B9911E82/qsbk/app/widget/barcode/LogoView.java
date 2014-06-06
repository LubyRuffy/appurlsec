package qsbk.app.widget.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import qsbk.app.widget.barcode.BarcodeView.OnCloseListener;

public class LogoView extends BarcodeView {
    public LogoView(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    public Bitmap getLeftTopImage() {
        return null;
    }

    public OnCloseListener getOnCloseListener() {
        return null;
    }

    public Bitmap getRightBottomImage() {
        return null;
    }

    public Rect getRightBottomRect() {
        return null;
    }

    protected void onDraw(Canvas r1_Canvas) {
        super.onDraw(r1_Canvas);
    }

    public void setLeftTopImage(Bitmap r1_Bitmap) {
    }

    public void setOnCloseListener(OnCloseListener r1_OnCloseListener) {
    }

    public void setRightBottomImage(Bitmap r1_Bitmap) {
    }
}