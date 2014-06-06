package android.support.v7.internal.widget;

import android.support.v7.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ActionBarContextView.java
class d implements OnClickListener {
    final /* synthetic */ ActionMode a;
    final /* synthetic */ ActionBarContextView b;

    d(ActionBarContextView r1_ActionBarContextView, ActionMode r2_ActionMode) {
        this.b = r1_ActionBarContextView;
        this.a = r2_ActionMode;
    }

    public void onClick(View r2_View) {
        this.a.finish();
    }
}