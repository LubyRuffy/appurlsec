package qsbk.app.activity.security;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.widget.LinearLayout;

// compiled from: SecurityBindActivity.java
class h implements Runnable {
    final /* synthetic */ LinearLayout a;
    final /* synthetic */ SecurityBindActivity b;

    h(SecurityBindActivity r1_SecurityBindActivity, LinearLayout r2_LinearLayout) {
        this.b = r1_SecurityBindActivity;
        this.a = r2_LinearLayout;
    }

    public void run() {
        Rect r0_Rect = new Rect();
        SecurityBindActivity.a(this.b).getHitRect(r0_Rect);
        r0_Rect.top -= 4;
        r0_Rect.bottom += 4;
        r0_Rect.right += 100;
        this.a.setTouchDelegate(new TouchDelegate(r0_Rect, SecurityBindActivity.a(this.b)));
    }
}