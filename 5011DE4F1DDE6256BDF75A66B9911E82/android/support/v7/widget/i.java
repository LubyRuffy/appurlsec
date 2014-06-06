package android.support.v7.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: SearchView.java
class i implements OnGlobalLayoutListener {
    final /* synthetic */ SearchView a;

    i(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public void onGlobalLayout() {
        SearchView.d(this.a);
    }
}