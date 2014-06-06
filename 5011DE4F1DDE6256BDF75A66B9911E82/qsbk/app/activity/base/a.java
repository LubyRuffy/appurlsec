package qsbk.app.activity.base;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.widget.FrameLayout;

// compiled from: BaseActivity.java
class a implements Runnable {
    final /* synthetic */ FrameLayout a;
    final /* synthetic */ BaseActivity b;

    a(BaseActivity r1_BaseActivity, FrameLayout r2_FrameLayout) {
        this.b = r1_BaseActivity;
        this.a = r2_FrameLayout;
    }

    public void run() {
        Rect r0_Rect = new Rect();
        this.b.z.getHitRect(r0_Rect);
        r0_Rect.top -= 4;
        r0_Rect.bottom += 4;
        r0_Rect.right += 100;
        this.a.setTouchDelegate(new TouchDelegate(r0_Rect, this.b.z));
    }
}