package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

// compiled from: SearchView.java
class b implements OnItemClickListener {
    final /* synthetic */ SearchView a;

    b(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public void onItemClick(AdapterView<?> r4_AdapterView_, View r5_View, int r6i, long r7j) {
        SearchView.a(this.a, r6i, 0, null);
    }
}