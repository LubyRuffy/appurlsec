package android.support.v4.media;

import android.view.ViewTreeObserver.OnWindowAttachListener;

// compiled from: TransportMediatorJellybeanMR2.java
class e implements OnWindowAttachListener {
    final /* synthetic */ d a;

    e(d r1_d) {
        this.a = r1_d;
    }

    public void onWindowAttached() {
        this.a.a();
    }

    public void onWindowDetached() {
        this.a.f();
    }
}