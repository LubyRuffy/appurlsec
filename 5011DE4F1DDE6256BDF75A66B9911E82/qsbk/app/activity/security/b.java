package qsbk.app.activity.security;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.widget.LinearLayout;

// compiled from: EmailBindActivity.java
class b implements Runnable {
    final /* synthetic */ LinearLayout a;
    final /* synthetic */ EmailBindActivity b;

    b(EmailBindActivity r1_EmailBindActivity, LinearLayout r2_LinearLayout) {
        this.b = r1_EmailBindActivity;
        this.a = r2_LinearLayout;
    }

    public void run() {
        Rect r0_Rect = new Rect();
        this.b.b.getHitRect(r0_Rect);
        r0_Rect.bottom += 4;
        r0_Rect.top += 4;
        r0_Rect.left -= 100;
        this.a.setTouchDelegate(new TouchDelegate(r0_Rect, this.b.b));
    }
}