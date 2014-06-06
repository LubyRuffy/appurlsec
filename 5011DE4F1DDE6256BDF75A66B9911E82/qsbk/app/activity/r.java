package qsbk.app.activity;

import android.view.View;
import qsbk.app.R;

// compiled from: AuditNativeActivity.java
class r implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ a b;

    r(a r1_a, int r2i) {
        this.b = r1_a;
        this.a = r2i;
    }

    public void run() {
        this.b.b.setAdapter(null);
        this.b.e.addView((View) this.b.a.get(this.a));
        if (this.b.f.Y.get()) {
            this.b.f.h();
        } else {
            ((View) this.b.a.get(this.a)).findViewById(R.id.bottom_layout).setVisibility(0);
            this.b.e.setVisibility(0);
        }
    }
}