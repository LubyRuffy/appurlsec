package android.support.v4.app;

import android.view.View;

// compiled from: Fragment.java
class g implements k {
    final /* synthetic */ Fragment a;

    g(Fragment r1_Fragment) {
        this.a = r1_Fragment;
    }

    public View findViewById(int r3i) {
        if (this.a.R != null) {
            return this.a.R.findViewById(r3i);
        }
        throw new IllegalStateException("Fragment does not have a view");
    }
}