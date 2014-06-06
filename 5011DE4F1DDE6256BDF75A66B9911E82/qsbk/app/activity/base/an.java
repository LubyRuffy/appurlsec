package qsbk.app.activity.base;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.widget.FrameLayout;

// compiled from: SecDefaultActivity.java
class an implements Runnable {
    final /* synthetic */ FrameLayout a;
    final /* synthetic */ SecDefaultActivity b;

    an(SecDefaultActivity r1_SecDefaultActivity, FrameLayout r2_FrameLayout) {
        this.b = r1_SecDefaultActivity;
        this.a = r2_FrameLayout;
    }

    public void run() {
        Rect r0_Rect = new Rect();
        this.b.P.getHitRect(r0_Rect);
        r0_Rect.top -= 4;
        r0_Rect.bottom += 4;
        r0_Rect.right += 100;
        this.a.setTouchDelegate(new TouchDelegate(r0_Rect, this.b.P));
    }
}