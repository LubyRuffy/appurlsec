package android.support.v7.widget;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

// compiled from: SearchView.java
class l implements OnEditorActionListener {
    final /* synthetic */ SearchView a;

    l(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public boolean onEditorAction(TextView r2_TextView, int r3i, KeyEvent r4_KeyEvent) {
        SearchView.j(this.a);
        return true;
    }
}