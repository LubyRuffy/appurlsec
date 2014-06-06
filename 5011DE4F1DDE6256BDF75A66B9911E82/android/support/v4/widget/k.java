package android.support.v4.widget;

import android.support.v4.widget.SearchViewCompat.OnQueryTextListenerCompat;

// compiled from: SearchViewCompat.java
class k implements b {
    final /* synthetic */ OnQueryTextListenerCompat a;
    final /* synthetic */ a b;

    k(a r1_a, OnQueryTextListenerCompat r2_OnQueryTextListenerCompat) {
        this.b = r1_a;
        this.a = r2_OnQueryTextListenerCompat;
    }

    public boolean onQueryTextChange(String r2_String) {
        return this.a.onQueryTextChange(r2_String);
    }

    public boolean onQueryTextSubmit(String r2_String) {
        return this.a.onQueryTextSubmit(r2_String);
    }
}