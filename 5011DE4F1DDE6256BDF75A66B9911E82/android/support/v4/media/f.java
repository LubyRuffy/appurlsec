package android.support.v4.media;

import android.view.ViewTreeObserver.OnWindowFocusChangeListener;

// compiled from: TransportMediatorJellybeanMR2.java
class f implements OnWindowFocusChangeListener {
    final /* synthetic */ d a;

    f(d r1_d) {
        this.a = r1_d;
    }

    public void onWindowFocusChanged(boolean r2z) {
        if (r2z) {
            this.a.b();
        } else {
            this.a.e();
        }
    }
}