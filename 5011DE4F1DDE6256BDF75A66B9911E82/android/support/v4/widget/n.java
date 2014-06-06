package android.support.v4.widget;

import android.widget.SearchView.OnQueryTextListener;

// compiled from: SearchViewCompatHoneycomb.java
final class n implements OnQueryTextListener {
    final /* synthetic */ b a;

    n(b r1_b) {
        this.a = r1_b;
    }

    public boolean onQueryTextChange(String r2_String) {
        return this.a.onQueryTextChange(r2_String);
    }

    public boolean onQueryTextSubmit(String r2_String) {
        return this.a.onQueryTextSubmit(r2_String);
    }
}