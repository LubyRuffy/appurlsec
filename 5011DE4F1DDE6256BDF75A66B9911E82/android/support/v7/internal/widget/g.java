package android.support.v7.internal.widget;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ActionBarView.java
class g implements OnClickListener {
    final /* synthetic */ ActionBarView a;

    g(ActionBarView r1_ActionBarView) {
        this.a = r1_ActionBarView;
    }

    public void onClick(View r4_View) {
        this.a.h.onMenuItemSelected(0, ActionBarView.c(this.a));
    }
}