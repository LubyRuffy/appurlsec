package com.flurry.android;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

// compiled from: SourceFile
final class d implements OnFocusChangeListener {
    private /* synthetic */ TextView a;
    private /* synthetic */ c b;

    d(c r1_c, TextView r2_TextView) {
        this.b = r1_c;
        this.a = r2_TextView;
    }

    public final void onFocusChange(View r3_View, boolean r4z) {
        this.a.setText(r4z ? this.b.b : this.b.a);
    }
}