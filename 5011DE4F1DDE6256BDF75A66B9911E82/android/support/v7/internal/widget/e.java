package android.support.v7.internal.widget;

import android.support.v7.internal.widget.AdapterViewICS.OnItemSelectedListener;
import android.view.View;

// compiled from: ActionBarView.java
class e implements OnItemSelectedListener {
    final /* synthetic */ ActionBarView a;

    e(ActionBarView r1_ActionBarView) {
        this.a = r1_ActionBarView;
    }

    public void onItemSelected(AdapterViewICS<?> r2_AdapterViewICS_, View r3_View, int r4i, long r5j) {
        if (ActionBarView.a(this.a) != null) {
            ActionBarView.a(this.a).onNavigationItemSelected(r4i, r5j);
        }
    }

    public void onNothingSelected(AdapterViewICS<?> r1_AdapterViewICS_) {
    }
}