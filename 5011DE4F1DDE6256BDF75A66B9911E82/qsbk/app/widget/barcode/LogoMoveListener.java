package qsbk.app.widget.barcode;

import android.view.View;
import qsbk.app.widget.barcode.SimpleZoomListener.ControlType;

public class LogoMoveListener extends SimpleZoomListener {
    protected float a() {
        return 5.0f;
    }

    protected void a(View r2_View, float r3f, float r4f) {
        setControlType(ControlType.PAN);
    }
}