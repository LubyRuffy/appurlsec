package android.support.v7.widget;

import android.support.v4.view.KeyEventCompat;
import android.support.v7.widget.SearchView.SearchAutoComplete;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

// compiled from: SearchView.java
class k implements OnKeyListener {
    final /* synthetic */ SearchView a;

    k(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public boolean onKey(View r6_View, int r7i, KeyEvent r8_KeyEvent) {
        int r0i = false;
        if (SearchView.o(this.a) == null) {
            return false;
        }
        if (SearchView.m(this.a).isPopupShowing() && SearchView.m(this.a).getListSelection() != -1) {
            return SearchView.a(this.a, r6_View, r7i, r8_KeyEvent);
        }
        if (SearchAutoComplete.a(SearchView.m(this.a)) || !KeyEventCompat.hasNoModifiers(r8_KeyEvent) || r8_KeyEvent.getAction() != 1 || r7i != 66) {
            return false;
        }
        r6_View.cancelLongPress();
        SearchView.a(this.a, r0i, null, SearchView.m(this.a).getText().toString());
        return true;
    }
}