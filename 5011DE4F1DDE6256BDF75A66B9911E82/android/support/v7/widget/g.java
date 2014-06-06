package android.support.v7.widget;

import android.view.View;
import android.view.View.OnFocusChangeListener;

// compiled from: SearchView.java
class g implements OnFocusChangeListener {
    final /* synthetic */ SearchView a;

    g(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public void onFocusChange(View r3_View, boolean r4z) {
        if (SearchView.c(this.a) != null) {
            SearchView.c(this.a).onFocusChange(this.a, r4z);
        }
    }
}