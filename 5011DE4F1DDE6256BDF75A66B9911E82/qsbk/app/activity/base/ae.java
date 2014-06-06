package qsbk.app.activity.base;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.widget.LinearLayout;

// compiled from: LoginRegisterBaseActivity.java
class ae implements Runnable {
    final /* synthetic */ LinearLayout a;
    final /* synthetic */ LoginRegisterBaseActivity b;

    ae(LoginRegisterBaseActivity r1_LoginRegisterBaseActivity, LinearLayout r2_LinearLayout) {
        this.b = r1_LoginRegisterBaseActivity;
        this.a = r2_LinearLayout;
    }

    public void run() {
        Rect r0_Rect = new Rect();
        this.b.r.getHitRect(r0_Rect);
        r0_Rect.bottom += 4;
        r0_Rect.top += 4;
        r0_Rect.left -= 100;
        this.a.setTouchDelegate(new TouchDelegate(r0_Rect, this.b.r));
    }
}