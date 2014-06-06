package android.support.v4.widget;

import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;

// compiled from: SearchViewCompat.java
class l implements a {
    final /* synthetic */ OnCloseListenerCompat a;
    final /* synthetic */ a b;

    l(a r1_a, OnCloseListenerCompat r2_OnCloseListenerCompat) {
        this.b = r1_a;
        this.a = r2_OnCloseListenerCompat;
    }

    public boolean onClose() {
        return this.a.onClose();
    }
}