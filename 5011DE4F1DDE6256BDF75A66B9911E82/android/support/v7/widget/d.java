package android.support.v7.widget;

import android.text.Editable;
import android.text.TextWatcher;

// compiled from: SearchView.java
class d implements TextWatcher {
    final /* synthetic */ SearchView a;

    d(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public void afterTextChanged(Editable r1_Editable) {
    }

    public void beforeTextChanged(CharSequence r1_CharSequence, int r2i, int r3i, int r4i) {
    }

    public void onTextChanged(CharSequence r2_CharSequence, int r3i, int r4i, int r5i) {
        SearchView.a(this.a, r2_CharSequence);
    }
}