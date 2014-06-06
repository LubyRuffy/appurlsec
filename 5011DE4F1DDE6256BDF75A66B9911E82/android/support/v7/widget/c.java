package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

// compiled from: SearchView.java
class c implements OnItemSelectedListener {
    final /* synthetic */ SearchView a;

    c(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public void onItemSelected(AdapterView<?> r2_AdapterView_, View r3_View, int r4i, long r5j) {
        SearchView.a(this.a, r4i);
    }

    public void onNothingSelected(AdapterView<?> r1_AdapterView_) {
    }
}