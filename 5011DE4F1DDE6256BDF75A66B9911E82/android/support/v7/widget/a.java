package android.support.v7.widget;

import android.view.inputmethod.InputMethodManager;

// compiled from: SearchView.java
class a implements Runnable {
    final /* synthetic */ SearchView a;

    a(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public void run() {
        InputMethodManager r0_InputMethodManager = (InputMethodManager) this.a.getContext().getSystemService("input_method");
        if (r0_InputMethodManager != null) {
            SearchView.a.a(r0_InputMethodManager, this.a, 0);
        }
    }
}