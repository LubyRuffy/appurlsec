package android.support.v7.widget;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SearchView.java
class j implements OnClickListener {
    final /* synthetic */ SearchView a;

    j(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public void onClick(View r2_View) {
        if (r2_View == SearchView.e(this.a)) {
            SearchView.f(this.a);
        } else if (r2_View == SearchView.g(this.a)) {
            SearchView.h(this.a);
        } else if (r2_View == SearchView.i(this.a)) {
            SearchView.j(this.a);
        } else if (r2_View == SearchView.k(this.a)) {
            SearchView.l(this.a);
        } else {
            if (r2_View == SearchView.m(this.a)) {
                SearchView.n(this.a);
            }
        }
    }
}