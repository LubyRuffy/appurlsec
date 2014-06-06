package android.support.v4.widget;

import android.widget.SearchView.OnCloseListener;

// compiled from: SearchViewCompatHoneycomb.java
final class o implements OnCloseListener {
    final /* synthetic */ a a;

    o(a r1_a) {
        this.a = r1_a;
    }

    public boolean onClose() {
        return this.a.onClose();
    }
}