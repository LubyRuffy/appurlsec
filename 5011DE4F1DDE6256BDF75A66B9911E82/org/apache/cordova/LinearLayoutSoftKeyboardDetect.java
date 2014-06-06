package org.apache.cordova;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import org.apache.cordova.api.LOG;

public class LinearLayoutSoftKeyboardDetect extends LinearLayout {
    private static final String TAG = "SoftKeyboardDetect";
    private DroidGap app;
    private int oldHeight;
    private int oldWidth;
    private int screenHeight;
    private int screenWidth;

    public LinearLayoutSoftKeyboardDetect(Context r2_Context, int r3i, int r4i) {
        super(r2_Context);
        this.oldHeight = 0;
        this.oldWidth = 0;
        this.screenWidth = 0;
        this.screenHeight = 0;
        this.app = null;
        this.screenWidth = r3i;
        this.screenHeight = r4i;
        this.app = (DroidGap) r2_Context;
    }

    protected void onMeasure(int r9i, int r10i) {
        super.onMeasure(r9i, r10i);
        LOG.v(TAG, "We are in our onMeasure method");
        int r0i = MeasureSpec.getSize(r10i);
        int r1i = MeasureSpec.getSize(r9i);
        String r2_String = TAG;
        Object[] r4_ObjectA = new Object[1];
        r4_ObjectA[0] = Integer.valueOf(this.oldHeight);
        LOG.v(r2_String, "Old Height = %d", r4_ObjectA);
        r2_String = TAG;
        r4_ObjectA = new Object[1];
        r4_ObjectA[0] = Integer.valueOf(r0i);
        LOG.v(r2_String, "Height = %d", r4_ObjectA);
        r2_String = TAG;
        r4_ObjectA = new Object[1];
        r4_ObjectA[0] = Integer.valueOf(this.oldWidth);
        LOG.v(r2_String, "Old Width = %d", r4_ObjectA);
        r2_String = TAG;
        r4_ObjectA = new Object[1];
        r4_ObjectA[0] = Integer.valueOf(r1i);
        LOG.v(r2_String, "Width = %d", r4_ObjectA);
        if (this.oldHeight == 0 || this.oldHeight == r0i) {
            LOG.d(TAG, "Ignore this event");
        } else if (this.screenHeight == r1i) {
            int r2i = this.screenHeight;
            this.screenHeight = this.screenWidth;
            this.screenWidth = r2i;
            LOG.v(TAG, "Orientation Change");
        } else if (r0i > this.oldHeight) {
            if (this.app != null) {
                this.app.appView.sendJavascript("cordova.fireDocumentEvent('hidekeyboard');");
            }
        } else if (r0i >= this.oldHeight || this.app == null) {
            this.oldHeight = r0i;
            this.oldWidth = r1i;
        } else {
            this.app.appView.sendJavascript("cordova.fireDocumentEvent('showkeyboard');");
        }
        this.oldHeight = r0i;
        this.oldWidth = r1i;
    }
}